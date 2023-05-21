package com.example.zuccknowledge.receiver;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = "cases.hello")
public class CasesReceiver {

    @RabbitHandler
    public void handle(String in){
        System.out.println("新增一条案例");
    }

}
