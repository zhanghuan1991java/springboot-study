package com.didispace.kafka.consumer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

//@Component
@Slf4j
public class KafkaReceiver {

   @KafkaListener(topics = "java-topic")
   public void listen(@Payload String message){
       log.info("received message={}",message);
   }

}