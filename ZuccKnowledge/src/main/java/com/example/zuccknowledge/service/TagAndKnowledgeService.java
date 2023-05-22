package com.example.zuccknowledge.service;

import com.example.zuccknowledge.entity.TagKnowledgeEntity;
import com.example.zuccknowledge.formbean.KnowledgeDto;
import com.example.zuccknowledge.formbean.TagAndKnowledgeDto;
import com.example.zuccknowledge.formbean.TagDto;

import java.util.List;

public interface TagAndKnowledgeService {
    List<TagAndKnowledgeDto> getAll();
    void knowledgeDeleteTag(int id);
    void knowledgAddTag(int kid, int tid);
    void knowledgUpdateTag(int kid, int tid);
    List<TagDto> getByKid(int id);
    void tagDeleteKnowledge(int id);
    List<KnowledgeDto> getByTid(int id);

}
