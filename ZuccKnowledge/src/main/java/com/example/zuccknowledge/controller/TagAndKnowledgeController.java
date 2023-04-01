package com.example.zuccknowledge.controller;

import com.example.zuccknowledge.entity.KnowledgeEntity;
import com.example.zuccknowledge.entity.TagEntity;
import com.example.zuccknowledge.entity.TagKonwledgeEntity;
import com.example.zuccknowledge.repository.KnowledgeRepository;
import com.example.zuccknowledge.repository.TagAndKnowledgeRepository;
import com.example.zuccknowledge.repository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/takc") //tag and knowledge controller
public class TagAndKnowledgeController {
    @Autowired
    private TagAndKnowledgeRepository tagAndKnowledgeRepository;
    @Autowired
    private TagRepository tagRepository;
    @Autowired
    private KnowledgeRepository knowledgeRepository;

    @GetMapping("/rbtag")
//relationship between tag and group
    List<TagKonwledgeEntity> getAll() {
        return tagAndKnowledgeRepository.findAll();
    }

    //知识点删除标签
    @PostMapping("/knowledgeDeleteTag/{id}")
    int knowledgeDeleteTag(@PathVariable int id) {
        return tagAndKnowledgeRepository.deleteByTid(id);
    }

    //标签删除知识点
//    @PostMapping("/tagDeleteKnowledge/{id}")
//    int tagDeleteKnowledge(@PathVariable int id) {
//        return tagAndKnowledgeRepository.deleteByKid(id);
//    }

    //根据知识点的id查所包含的标签
    @GetMapping("/byKid/{id}")
    List<TagEntity> getByKid(@PathVariable Integer id) {
        return tagRepository.getByKid(id);
    }

    //根据标签id查所在知识点
    @GetMapping("/byGid/{id}")
    List<KnowledgeEntity> getByTid(@PathVariable Integer id) {
        return knowledgeRepository.getByTid(id);
    }
}
