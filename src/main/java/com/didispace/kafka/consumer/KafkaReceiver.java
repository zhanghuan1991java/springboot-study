package com.didispace.kafka.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class KafkaReceiver {
   private static final Logger logger = LoggerFactory.getLogger(KafkaReceiver.class);

   @KafkaListener(topics = "java-topic")
   public void listen(@Payload String message){
       logger.info("received message={}",message);
   }
}