package com.example.controller;

import com.example.entity.Knowledge;
import com.example.service.KnowledgeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/knowledge")
public class KnowledgeController {
    @Autowired
    KnowledgeService knowledgeService;

    @GetMapping("/all")
    public List<Knowledge> getAll() {
        return knowledgeService.getAllKnowledge();
    }

    @GetMapping("/byName/{name}")
    public List<Knowledge> getKnowledgeByName(@PathVariable("name") String name) {
        return knowledgeService.getKnowledgeByName(name);
    }

    @PostMapping("/insert")
    public int insertKnowledge(@RequestBody Knowledge knowledge) {
        return knowledgeService.insertKnowledge(knowledge);
    }

    @PutMapping("/update")
    public int updateKnowledge(@RequestBody Knowledge knowledge) {
        return knowledgeService.updateKnowledge(knowledge);
    }

    @DeleteMapping("/delete/{name}")
    public int deleteKnowledge(@PathVariable("name") String name) {
        return knowledgeService.deleteKnowledge(name);
    }
}
