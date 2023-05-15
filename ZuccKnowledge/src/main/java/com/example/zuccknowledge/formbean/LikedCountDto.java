package com.example.zuccknowledge.formbean;

import lombok.Data;

@Data
public class LikedCountDto {
    private  int id;
    private int Score;
    private String Member;

    public LikedCountDto(String key, Integer value) {
        this.Member =key;
        this.Score = value;
    }
}
