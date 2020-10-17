package com.didispace.zookeeper.curator;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Test {
    public static void main(String[] args) {
        final ExecutorService threadpool = Executors.newCachedThreadPool();
        System.out.println("开始购买...");
        for (int i = 0; i <2 ; i++) {
            threadpool.execute(new Runnable() {
                public void run() {
                    System.out.println("我是线程:"+Thread.currentThread().getName()+"我开始抢购了...");
                     ZkLockUtil.getLock();
                    System.out.println(Thread.currentThread().getName()+":我正在疯狂的剁手购买中...");
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName()+":我买完了,有请下一位...");
                    try {
                        ZkLockUtil.addWatcher2Path("/zk-lock");
                        System.out.println("添加完毕...");
                        ZkLockUtil.release("/zk-lock/distribute-lock");
                        System.out.println("释放完毕...");
                        Thread.sleep(1000);

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }
}