package com.example.zuccknowledge.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

@Component
public class RedisUtils {
    @Autowired
    StringRedisTemplate redisTemplate;

    public void add(String k, String v, Integer v1) {
        redisTemplate.opsForZSet().add(k, v, v1);
    }

    public void remove(String k, Object value) {
        redisTemplate.opsForZSet().remove(k, value);
    }

    public void increment(String k, String v, double num) {
        redisTemplate.opsForZSet().incrementScore(k, v, num);
    }

    public void rightPush(String k, String value) {
        redisTemplate.opsForList().rightPush(k, value);
    }

    public void leftPop(String k) {
        redisTemplate.opsForList().leftPop(k);
    }

    public Set<ZSetOperations.TypedTuple<String>> getOrderedData(String k, int max) {
        Set<ZSetOperations.TypedTuple<String>> rangeWithScores = redisTemplate.opsForZSet().reverseRangeWithScores(k, 0, max);
        return rangeWithScores;
    }

    public List<String> getList(String k) {
        return redisTemplate.opsForList().range(k, 0, -1);
    }
}
