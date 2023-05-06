package com.example.zuccknowledge.formbean;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class KReadRecordDto {
    private int krecordid;
    private int kid;
    private int reader;
    private Timestamp opentime;
}
