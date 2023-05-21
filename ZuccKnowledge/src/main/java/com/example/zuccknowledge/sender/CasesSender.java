package com.example.zuccknowledge.sender;


import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CasesSender {

    @Autowired
    private AmqpTemplate amqpTemplate;

    public void sendMessage(){
        //发送消息
        amqpTemplate.convertAndSend("cases.hello", "hello simple");
    }

}
