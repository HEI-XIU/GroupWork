package com.example.zuccknowledge.utils;

public class RedisKeyUtils {

    //保存用户点赞数据的key
    public static final String MAP_KEY_USER_LIKED = "MAP_USER_LIKED";
    //保存用户被点赞数量的key
    public static final String MAP_KEY_USER_LIKED_COUNT = "MAP_USER_LIKED_COUNT";

    public static final String SCORE_RANK = "score_rank";
    /**
     * 拼接被点赞的用户id和点赞的人的id作为key。格式 222222::333333
     * @param LikedCaseId 被点赞的案例id
     * @param likeUsername 点赞的人的name
     * @return
     */
    public static String getLikedKey(String likeUsername, String LikedCaseId){
        StringBuilder builder = new StringBuilder();
        builder.append(LikedCaseId);
        builder.append("::");
        builder.append(likeUsername);
        return builder.toString();
    }
}
