package com.example.service;

import com.example.entity.Knowledge;
import com.example.mapper.KnowledgeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KnowledgeService {
    @Autowired
    KnowledgeMapper knowledgeMapper;

    public List<Knowledge> getAllKnowledge() {
        return knowledgeMapper.getAllKnowledge();
    }

    public List<Knowledge> getKnowledgeByName(String name) {
        return knowledgeMapper.getKnowledgeByName(name);
    }

    public int insertKnowledge(Knowledge knowledge) {
        return knowledgeMapper.insertKnowledge(knowledge);
    }

    public int updateKnowledge(Knowledge knowledge) {
        return knowledgeMapper.updateKnowledge(knowledge);
    }

    public int deleteKnowledge(String name) {
        return knowledgeMapper.deleteKnowledge(name);
    }
}
