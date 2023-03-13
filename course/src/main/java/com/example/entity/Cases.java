package com.example.entity;

import lombok.Data;

@Data
public class Cases {
    private int caseId;
    private int knowledgeId;
    private String caseInfo;
    private int isCode;
    private String type;
}
