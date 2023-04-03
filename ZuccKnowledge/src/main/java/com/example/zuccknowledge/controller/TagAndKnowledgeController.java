package com.example.zuccknowledge.controller;

import com.example.zuccknowledge.entity.*;
import com.example.zuccknowledge.formbean.TagAndKnowledge;
import com.example.zuccknowledge.repository.KnowledgeRepository;
import com.example.zuccknowledge.repository.TagAndKnowledgeRepository;
import com.example.zuccknowledge.repository.TagRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/takc") //tag and knowledge controller
public class TagAndKnowledgeController {
    @Autowired
    private TagAndKnowledgeRepository tagAndKnowledgeRepository;
    @Autowired
    private TagRepository tagRepository;
    @Autowired
    private KnowledgeRepository knowledgeRepository;
    @GetMapping("/rbtag")//relationship between tag and group
    List<TagKonwledgeEntity> getAll(){return tagAndKnowledgeRepository.findAll();}
    /**
     * 知识点操作
     * 知识点根据tigid删除知识点对应标签的关系
     * @param tid
     * @return
     */
    @PostMapping("/knowledgeDeleteTag/{tid}")
    int knowledgeDeleteTag(@PathVariable int tid){return tagAndKnowledgeRepository.deleteByTid(tid);}
    /**
     * 知识点操作
     * 知识点增加相应的tag
     * @param tagAndKnowledge
     * @return
     */
    @PostMapping("/knowledgAddTag")
    int knowledgAddTag(@RequestBody TagAndKnowledge tagAndKnowledge){
        TagKonwledgeEntity tagKonwledgeEntity = new TagKonwledgeEntity();
        if(tagAndKnowledgeRepository.countByKidAndTid(tagAndKnowledge.getKid(),tagAndKnowledge.getTid())==0){
            BeanUtils.copyProperties(tagAndKnowledge,tagKonwledgeEntity);
            tagAndKnowledgeRepository.save(tagKonwledgeEntity);
        }
        return 1;
    }
    /**
     * 知识点操作
     * 知识点修改相应的tag
     * @param tagAndKnowledge
     * @return
     */
    @PostMapping("/knowledgUpdateTag")
    int knowledgUpdateTag(@RequestBody TagAndKnowledge tagAndKnowledge){
        TagKonwledgeEntity tagKonwledgeEntity = new TagKonwledgeEntity();
        if(tagAndKnowledgeRepository.countByTkid(tagAndKnowledge.getTkid())!=0){
            BeanUtils.copyProperties(tagAndKnowledge,tagKonwledgeEntity);
            tagAndKnowledgeRepository.save(tagKonwledgeEntity);
        }
        return 1;
    }
    /**
     * 知识点操作
     * 根据知识点的kid查对应的所有标签
     * @param id
     * @return
     */
    @GetMapping("/byKid/{id}")
    List<TagEntity> getByKid(@PathVariable Integer id) {
        return tagRepository.getByKid(id);
    }
    /**
     *
     * 标签根据kid删除所有标签对应知识点的关系
     * @param kid
     * @return
     */
    @PostMapping("/tagDeleteKnowledge/{kid}")
    int tagDeleteKnowledge(@PathVariable int kid){return tagAndKnowledgeRepository.deleteByKid(kid);}

    /**
     * 根据标签id查所在哪些知识点下
     * @param tid
     * @return
     */
    @GetMapping("/byGid/{tid}")
    List<KnowledgeEntity> getByTid(@PathVariable Integer tid) {
        return knowledgeRepository.getByTid(tid);
    }}
