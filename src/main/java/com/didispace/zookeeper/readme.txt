1   增加依赖：
    <!--集成zookeeper-->
    <dependency>
        <groupId>org.apache.zookeeper</groupId>
        <artifactId>zookeeper</artifactId>
        <version>3.4.12</version>
    </dependency>

2   增加zk配置

3   增加配置类：ZookeeperConfig.java

4   增加操作目录结构的ZkApi.java

5   这里的操作，都是原生zookeeper的操作，仅供学习使用 ， 真正使用其高级api ，Curator封装的方法。




使用：
ZkApi 里面注释了注解：@Component
ZookeeperConfig里面注释了初始化代码


