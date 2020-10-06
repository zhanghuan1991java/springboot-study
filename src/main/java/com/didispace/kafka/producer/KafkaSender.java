package com.didispace.kafka.producer;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.IdUtil;
import com.didispace.kafka.beans.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class KafkaSender {
    private static final Logger logger = LoggerFactory.getLogger(KafkaSender.class);

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    //发送消息的方法
    public void send() {
        Message message = new Message();
        message.setId(IdUtil.randomUUID());
        message.setMsg("Hello Kafka!!!" + IdUtil.fastSimpleUUID());
        message.setSendTime(DateUtil.now());
        logger.info("+++++++++++++++++++ message = {}", message.toStringPretty());
        kafkaTemplate.send("java-topic", message.toString());
    }
}