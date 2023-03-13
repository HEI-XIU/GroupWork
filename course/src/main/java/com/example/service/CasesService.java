package com.example.service;

import com.example.entity.Cases;
import com.example.mapper.CasesMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CasesService {
    @Autowired
    CasesMapper casesMapper;

    public List<Cases> getAllCases() {
        return casesMapper.getAllCases();
    }

    public List<Cases> getCasesByKId(int knowledgeId) {
        return casesMapper.getCasesByKId(knowledgeId);
    }

    public int insertCases(Cases cases) {
        return casesMapper.insertCases(cases);
    }

    public int updateCases(Cases cases) {
        return casesMapper.updateCases(cases);
    }

    public int deleteCases(int casesId) {
        return casesMapper.deleteCases(casesId);
    }
}
