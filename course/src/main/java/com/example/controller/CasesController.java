package com.example.controller;

import com.example.entity.Cases;
import com.example.service.CasesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cases")
public class CasesController {
    @Autowired
    CasesService casesService;

    @GetMapping("/all")
    public List<Cases> getAll() {
        return casesService.getAllCases();
    }

    @GetMapping("/byKId/{knowledgeId}")
    public List<Cases> getCasesByKId(@PathVariable("knowledgeId") int knowledgeId) {
        return casesService.getCasesByKId(knowledgeId);
    }

    @PostMapping("/insert")
    public int insertCases(@RequestBody Cases cases) {
        return casesService.insertCases(cases);
    }

    @PutMapping("/update")
    public int updateCases(@RequestBody Cases cases) {
        return casesService.updateCases(cases);
    }

    @DeleteMapping("/delete/{casesId}")
    public int deleteCases(@PathVariable("casesId") int casesId) {
        return casesService.deleteCases(casesId);
    }
}
