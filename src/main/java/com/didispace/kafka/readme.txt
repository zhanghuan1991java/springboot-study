1   特别注意： 必须先去官网  查看  kafka软件， springboot版本，  版本是否兼容
    本项目采用版本  kafka2.11_1.0.2.tgz   ,   springboot 2.0.1

2   引入jar包依赖：
    <dependency>
        <groupId>org.springframework.kafka</groupId>
        <artifactId>spring-kafka</artifactId>
    </dependency>

3   application.properties 增加配置文件：

#kafka server address
spring.kafka.bootstrap-servers=192.168.75.100:9092

# Provider
spring.kafka.producer.retries=0
spring.kafka.producer.batch-size=16384
# 指生产者的key和value的编码方式
spring.kafka.producer.key-serializer=org.apache.kafka.common.serialization.StringSerializer
spring.kafka.producer.value-serializer=org.apache.kafka.common.serialization.StringSerializer

# Consumer
#消费者组
spring.kafka.consumer.group-id=test-consumer-group
spring.kafka.consumer.auto-offset-reset=earliest
# 指定消费者的解码方式
spring.kafka.consumer.key-deserializer=org.apache.kafka.common.serialization.StringDeserializer
spring.kafka.consumer.value-deserializer=org.apache.kafka.common.serialization.StringDeserializer




