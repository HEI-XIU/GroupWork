package com.example.zuccknowledge.formbean;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class LikeCases {
    private Timestamp time;
    private String username;
    private int caseid;
    private int liked;
    private int likeid;
}
