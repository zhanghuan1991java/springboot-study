package zookeeper;

import cn.hutool.core.util.StrUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.zookeeper.*;
import org.apache.zookeeper.Watcher.Event.KeeperState;
import org.apache.zookeeper.ZooDefs.Ids;
import org.apache.zookeeper.data.Stat;

import java.util.Arrays;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

import static org.apache.zookeeper.Watcher.Event.*;

@Slf4j
public class MyZKAuth implements Watcher {
	/** 连接地址 */
	final static String ZK_ADDR = "192.168.75.100:2181";

	/** 失效时间 */
	static final int TIME_OUT = 2000;

	/** 测试路径 */
	final static String PATH = "/testAuth";

	final static String PATH_DEL = "/testAuth/delNode";

	/** 认证类型 */
	final static String AUTH_TYPE = "digest";

	/** 认证正确方法 */
	final static String AUTH_STRING = "123456";

	/** 认证错误方法 */
	final static String WRONG_AUTH_STRING = "654321";
	
	static ZooKeeper  zk = null;

	/** 计时器 */
	AtomicInteger seq = new AtomicInteger();
	
	private CountDownLatch connectedSemaphore = new CountDownLatch(1);



	@Override
	public void process(WatchedEvent event) {
		try {
			Thread.sleep(200);
		} catch (InterruptedException e) {
			log.info("InterruptedException...",e);
		}

		if (event==null) {
			return;
		}

		// 连接状态
		KeeperState keeperState = event.getState();

		// 事件类型
		EventType eventType = event.getType();

		// 受影响的path
		String path = event.getPath();
		
		String logPrefix = "【Watcher-" + this.seq.incrementAndGet() + "】";

		log.info(logPrefix + "收到Watcher通知");
		log.info(logPrefix + "连接状态:\t" + keeperState.toString());
		log.info(logPrefix + "事件类型:\t" + eventType.toString());

		if (KeeperState.SyncConnected == keeperState) {
			// 成功连接上ZK服务器
			if (EventType.None == eventType) {
				log.info(logPrefix + "成功连接上ZK服务器");
				connectedSemaphore.countDown();
			} 
		} else if (KeeperState.Disconnected == keeperState) {
			log.info(logPrefix + "与ZK服务器断开连接");
		} else if (KeeperState.AuthFailed == keeperState) {
			log.info(logPrefix + "权限检查失败");
		} else if (KeeperState.Expired == keeperState) {
			log.info(logPrefix + "会话失效");
		}
		log.info("-----------process end------------------------");
	}
	/**
	 * 创建ZK连接
	 * 
	 * @param connectString
	 *            ZK服务器地址列表
	 * @param sessionTimeout
	 *            Session超时时间
	 */
	public void createConnectionByAuth(String connectString, int sessionTimeout,String authType,String authString) {

		this.releaseConnection();

		try {
			zk = new ZooKeeper(connectString, sessionTimeout, this);
			/**
			 * 添加节点授权
			 * digest：最常用的权限控制模式，类似于“username：password”形式的权限标识进行权限配置.
			 * Zookeeper对形成的权限标识先后进行两次编码处理：SHA-1加密算法、Base64编码；
			 */
			zk.addAuthInfo(authType,authString.getBytes());

			log.info("【Main】开始连接ZK服务器");

			//倒数等待
			connectedSemaphore.await();

		} catch (Exception e) {
			log.info("createConnection exception...",e);
		}

	}
	
	/**
	 * 关闭ZK连接
	 */
	public void releaseConnection() {
		if (this.zk!=null) {
			try {
				this.zk.close();
			} catch (InterruptedException e) {
			}
		}
	}
	

	public static void main(String[] args) throws Exception {
		
		MyZKAuth myZKAuth = new MyZKAuth();

		myZKAuth.createConnectionByAuth(ZK_ADDR, TIME_OUT, AUTH_TYPE, AUTH_STRING);

		try {
			zk.create(PATH, "init content".getBytes(), Ids.CREATOR_ALL_ACL, CreateMode.PERSISTENT);
		} catch (Exception e) {
			log.info("zk.create(PATH exception...",e);
		}

		try {
			zk.create(PATH_DEL, "will be deleted! ".getBytes(), Ids.CREATOR_ALL_ACL, CreateMode.PERSISTENT);
		} catch (Exception e) {
			log.info("zk.create(PATH_DEL exception...",e);
		}

		//1、获取数据
		{
			String ret = getDataByAuth(null, null, PATH);
			log.info(ret);
		}
		{
			String ret = getDataByAuth(AUTH_TYPE, WRONG_AUTH_STRING, PATH);
			log.info(ret);
		}
		{
			String ret = getDataByAuth(AUTH_TYPE, AUTH_STRING, PATH);
			log.info(ret);
		}

		//2、更新数据
		updateDataByAuth(AUTH_TYPE,AUTH_STRING,PATH,"update data");

		//3、删除数据 , 先删子节点，
		deleteNodeByAuth(AUTH_TYPE,AUTH_STRING,PATH_DEL);
		//才能删父节点
		deleteNodeByAuth(AUTH_TYPE,AUTH_STRING,PATH);

		//释放连接
		myZKAuth.releaseConnection();
	}

	/**
	 * 使用指定 authType , authString 获取数据
	 * @param authType
	 * @param authString
	 * @return
	 */
	static String getDataByAuth(String authType , String authString , String path){
		try {
//			ZooKeeper zk = new ZooKeeper(ZK_ADDR, TIME_OUT, null);

			if(StrUtil.isNotBlank(authType) && StrUtil.isNotBlank(authString)){
				zk.addAuthInfo(authType,authString.getBytes());
			}

			return Arrays.toString(zk.getData(path, false, null));
		} catch (Exception e) {
			log.info("getDataUseAuth ...",e);

			return "";
		}
	}
	/**
	 * 更新数据
	 * @param authType
	 * @param authString
	 * @param path
	 */
	static void updateDataByAuth(String authType , String authString , String path ,String value) {
		try {
//			ZooKeeper zk = new ZooKeeper(ZK_ADDR, TIME_OUT, null);

			if(StrUtil.isNotBlank(authType) && StrUtil.isNotBlank(authString)){
				zk.addAuthInfo(authType,authString.getBytes());
			}

			Stat stat = zk.exists(path, false);
			if (stat!=null) {
				zk.setData(path, value.getBytes(), -1);
				log.info(value + "更新成功");
			}
		} catch (Exception e) {
			log.info(value + "获取数据["+PATH+"]失败", e);
		}
	}


	/**
	 * 删除数据
	 * @param authType
	 * @param authString
	 * @param path
	 */
	static void deleteNodeByAuth(String authType , String authString , String path){

		try {
//			ZooKeeper zk = new ZooKeeper(ZK_ADDR, TIME_OUT, null);

			if(StrUtil.isNotBlank(authType) && StrUtil.isNotBlank(authString)){
				zk.addAuthInfo(authType,authString.getBytes());
			}

			Stat stat = zk.exists(path, false);

			if (stat!=null) {
				zk.delete(path, -1);
				log.info(path + "删除成功");
			}
		} catch (Exception e) {
			log.info(path + "删除失败" ,e);
		}
	}

}