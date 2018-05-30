package com.example.kafka.process;

import com.example.kafka.config.KafkaBindings;
import com.example.kafka.domain.People;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.stereotype.Component;

/**
 * Created by ZJ on 2018/5/30.
 */
@Component
public class TestProcess {

    @Qualifier(KafkaBindings.TEST)
    @Autowired
    MessageChannel testOut;

   public void start(){
       People people = new People(1L,"zhangsan");
       Message message  = MessageBuilder
               .withPayload(people)
               .setHeader("type","TestEvent")
               .build();
       testOut.send(message);
   }
}
