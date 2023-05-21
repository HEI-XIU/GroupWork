package com.example.zuccknowledge.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Set;

@Component
public class RabbitmqUtils {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private AmqpTemplate amqpTemplate;
    @Resource
    RedisUtils redisUtils;

    public void sendRoutingMessage(String type, Object message) {
        amqpTemplate.convertAndSend("routingExchange", type, message);
    }

    @RabbitListener(queues = "KRankQueue")
    public void receiveKRankQueue(JSONObject message) {
        logger.info("KRankQueue Received message: " + message);

        Set<ZSetOperations.TypedTuple<String>> rangeWithScores = redisUtils.getOrderedData(message.getString("key"), -1);
        for (ZSetOperations.TypedTuple<String> rangeWithScore : rangeWithScores) {
            if (Integer.parseInt(rangeWithScore.getValue()) == message.getIntValue("id")) {
                redisUtils.increment(message.getString("key"), rangeWithScore.getValue(), 1);
                break;
            }
        }
        redisUtils.rightPush("k_record", message.getString("readRecord"));
    }
}
