package com.example.zuccknowledge.service.impl;

import com.example.zuccknowledge.formbean.LikeCases;
import com.example.zuccknowledge.formbean.LikedCountDto;
import com.example.zuccknowledge.service.RedisService;
import com.example.zuccknowledge.utils.LikedStatusEnum;
import com.example.zuccknowledge.utils.RedisKeyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.*;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Service
public class RedisServiceImpl implements RedisService {
    @Autowired
    StringRedisTemplate redisTemplate;
    @Override
    public void saveLikedRedis(String likeUsername, String likedCaseId) {
        String key = RedisKeyUtils.getLikedKey(likeUsername,likedCaseId);

        redisTemplate.opsForHash().put(RedisKeyUtils.MAP_KEY_USER_LIKED,key, LikedStatusEnum.LIKE.getCode());
    }

    @Override
    public void unlikedFromRedis(String likeUsername, String likedCaseId) {
        String key = RedisKeyUtils.getLikedKey(likeUsername, likedCaseId);
        redisTemplate.opsForHash().put(RedisKeyUtils.MAP_KEY_USER_LIKED, key, LikedStatusEnum.UNLIKE.getCode());

    }

    @Override
    public void incrementLikedCount(String likedCaseId) {
//        redisTemplate.opsForHash().increment(RedisKeyUtils.MAP_KEY_USER_LIKED_COUNT, likedCaseId, 1);
        redisTemplate.opsForZSet().incrementScore(RedisKeyUtils.SCORE_RANK, likedCaseId, 1);
    }

    @Override
    public void decrementLikedCount(String likedCaseId) {
//        redisTemplate.opsForHash().increment(RedisKeyUtils.MAP_KEY_USER_LIKED_COUNT, likedCaseId, -1);
        redisTemplate.opsForZSet().incrementScore(RedisKeyUtils.SCORE_RANK, likedCaseId, 1);
    }

    @Override
    public List<LikeCases> getLikedDataFromRedis() {
        Cursor<Map.Entry<Object, Object>> cursor = redisTemplate.opsForHash().scan(RedisKeyUtils.MAP_KEY_USER_LIKED, ScanOptions.NONE);
        List<LikeCases> list = new ArrayList<>();
        while (cursor.hasNext()) {
            Map.Entry<Object, Object> entry = cursor.next();
            String key = (String) entry.getKey();
            //分离出 likedUserId，likedPostId
            String[] split = key.split("::");
            String likedCaseId = split[0];
            String likeUsername = split[1];
            String value = (String) entry.getValue();
//System.out.println("split"+ Arrays.toString(split));
//System.out.println(likedCaseId);
            //组装成 UserLike 对象
            long datetime = System.currentTimeMillis();
            Timestamp timestamp = new Timestamp(datetime);

            LikeCases likeCases = new LikeCases(likedCaseId, likeUsername, value,timestamp);
//System.out.println("likeCases"+likeCases);
            list.add(likeCases);

            //存到 list 后从 Redis 中删除
            redisTemplate.opsForHash().delete(RedisKeyUtils.MAP_KEY_USER_LIKED, key);

        }
        return list;
    }

    @Override
    public List<LikedCountDto> getLikedCountFromRedis() {
        Cursor<ZSetOperations.TypedTuple<String>> cursor = redisTemplate.opsForZSet().scan(RedisKeyUtils.SCORE_RANK, ScanOptions.NONE);
        List<LikedCountDto> list = new ArrayList<>();

        while (cursor.hasNext()){

            ZSetOperations.TypedTuple<String> map =  cursor.next();

            //将点赞数量存储在 LikedCountDT
            String key = map.getValue();
            LikedCountDto dto = new LikedCountDto(key, map.getScore().intValue());
            list.add(dto);
            //从Redis中删除这条记录
//            redisTemplate.opsForHash().delete(RedisKeyUtils.MAP_KEY_USER_LIKED_COUNT, key);
        }
//        System.out.println("getLikedCountFromRedis"+list);
        return list;
    }

}
