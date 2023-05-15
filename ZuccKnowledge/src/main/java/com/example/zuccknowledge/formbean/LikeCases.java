package com.example.zuccknowledge.formbean;

import com.example.zuccknowledge.utils.LikedStatusEnum;
import lombok.Data;

import java.sql.Timestamp;

@Data
public class LikeCases {
    private Timestamp time;
    private String username;
    private String caseid;
    private String liked = LikedStatusEnum.UNLIKE.getCode();
    private int likeid;

    public LikeCases(String likedCaseId, String  likeUsername, String value,Timestamp timestamp) {
        this.caseid = likedCaseId;
        this.username = likeUsername;
        this.liked = value;
        this.time = timestamp;
    }

    public LikeCases() {

    }
}
