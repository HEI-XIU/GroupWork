package com.example.zuccknowledge.utils;

import lombok.Getter;

/**
 * 用户点赞的状态
 */
@Getter
public enum LikedStatusEnum {
    LIKE("1", "点赞"),
    UNLIKE("0", "取消点赞/未点赞"),
    ;

    private String code;

    private String msg;

    LikedStatusEnum(String  code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}

