package com.example.zuccknowledge.service;

import com.example.zuccknowledge.formbean.LikeCases;
import com.example.zuccknowledge.formbean.LikedCountDto;

import java.util.List;

public interface RedisService {
    /**
     * 喜欢,状态为1
     * @param likeUsername
     * @param LikedCaseId
     */
    void saveLikedRedis(String likeUsername, String LikedCaseId) ;

    /**
     * 取消喜欢，状态变为0
     * @param likeUsername
     * @param LikedCaseId
     */
    void unlikedFromRedis(String likeUsername, String LikedCaseId);
    /**
     * 该案例的点喜欢数加1
     * @param LikedCaseId
     */
    void incrementLikedCount(String LikedCaseId);

    /**
     * 该用户的点喜欢数减1
     * @param LikedCaseId
     */
    void decrementLikedCount(String LikedCaseId);

    /**
     * 获取redis中存储的所有点赞数据
     * @return
     */
    List<LikeCases> getLikedDataFromRedis();

    /**
     * 获取Redis中存储的所有点赞数量
     * @return
     */
    List<LikedCountDto> getLikedCountFromRedis();

}
