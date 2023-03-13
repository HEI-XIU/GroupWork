package com.example.entity;

import lombok.Data;

@Data
public class Knowledge {
    private int knowledgeId;
    private String knowledgeName;
    private String description;
    private String emphasis;
    private String preKnowledges;
    private String labels;
}
