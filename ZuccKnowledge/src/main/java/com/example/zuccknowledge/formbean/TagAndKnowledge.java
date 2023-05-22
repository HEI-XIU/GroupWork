package com.example.zuccknowledge.formbean;

import lombok.Data;

@Data
public class TagAndKnowledge {
    private int tkid;
    private int tid;
    private int kid;

    public TagAndKnowledge(int kid, int tid) {
        this.kid=kid;
        this.tid = tid;
    }
}
