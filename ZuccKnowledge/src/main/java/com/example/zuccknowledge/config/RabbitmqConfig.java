package com.example.zuccknowledge.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitmqConfig {
    //创建队列
    @Bean
    public Queue kRankQueue() {
        return new Queue("KRankQueue");
    }

    @Bean
    public Queue CasesHello(){
        return new Queue("cases.hello");
    }

    // 创建交换机
    @Bean
    public DirectExchange directExchange() {
        return new DirectExchange("routingExchange");
    }

    //绑定队列
    @Bean
    Binding bingingKRankQueue(Queue kRankQueue, DirectExchange directExchange) {
        return BindingBuilder.bind(kRankQueue).to(directExchange).with("kRank");
    }
}
