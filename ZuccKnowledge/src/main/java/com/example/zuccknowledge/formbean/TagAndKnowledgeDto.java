package com.example.zuccknowledge.formbean;

import lombok.Data;

@Data
public class TagAndKnowledgeDto {
    private int tkid;
    private int tid;
    private int kid;

    public TagAndKnowledgeDto(int kid, int tid) {
        this.kid=kid;
        this.tid = tid;
    }

    public TagAndKnowledgeDto() {
    }
}
