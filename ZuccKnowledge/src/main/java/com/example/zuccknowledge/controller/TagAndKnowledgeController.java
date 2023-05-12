package com.example.zuccknowledge.controller;

import com.example.zuccknowledge.entity.*;
import com.example.zuccknowledge.exception.EchoServiceException;
import com.example.zuccknowledge.formbean.KnowledgeDto;
import com.example.zuccknowledge.formbean.TagAndKnowledgeDto;
import com.example.zuccknowledge.formbean.TagDto;
import com.example.zuccknowledge.repository.KnowledgeRepository;
import com.example.zuccknowledge.repository.TagAndKnowledgeRepository;
import com.example.zuccknowledge.repository.TagRepository;
import com.example.zuccknowledge.result.zk.ReturnCode;
import com.example.zuccknowledge.result.zk.ReturnVO;
import com.example.zuccknowledge.service.TagAndKnowledgeService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/tag_knowledges/v1") //tag and knowledge controller
public class TagAndKnowledgeController {
    @Autowired
    private TagAndKnowledgeRepository tagAndKnowledgeRepository;
    @Autowired
    private TagRepository tagRepository;
    @Autowired
    private KnowledgeRepository knowledgeRepository;
    @Autowired
    TagAndKnowledgeService tagAndKnowledgeService;
    /**
     * 获得所有相关信息
     * @return
     */
    @GetMapping("")//relationship between tag and group
    ReturnVO getAll(){
        List<TagAndKnowledgeDto> tag_knowledges;
        try {
            tag_knowledges = tagAndKnowledgeService.getAll();
        }catch (Exception e){
            e.printStackTrace();
            return new ReturnVO(ReturnCode.FAIL);
        }
        return new ReturnVO(tag_knowledges);
    }


    /**
     * 知识点操作
     * 知识点根据tigid删除知识点对应标签的关系
     * @param tid
     * @return
     */
    @DeleteMapping("/knowledges/{tid}")
    ReturnVO knowledgeDeleteTag(@PathVariable int tid){
        try{
            tagAndKnowledgeService.knowledgeDeleteTag(tid);
//            tagAndKnowledgeRepository.deleteByTid(tid);
        }catch (Exception e){
            e.printStackTrace();
            return new ReturnVO(ReturnCode.FAIL);
        }
        return new ReturnVO();
    }


    /**
     * 知识点操作
     * 知识点增加相应的tag
     * @return
     */
    @PostMapping("/knowledges")
    ReturnVO knowledgAddTag(int kid, int tid){
        try{
            tagAndKnowledgeService.knowledgAddTag(kid,tid);
        }catch (EchoServiceException e){
            e.printStackTrace();
            return new ReturnVO(e);
        }
       return new ReturnVO();
//        TagAndKnowledgeDto tagAndKnowledgeDto = null;
//        TagKnowledgeEntity tagKnowledgeEntity = new TagKnowledgeEntity();
//        try{
//            tagAndKnowledgeDto = new TagAndKnowledgeDto(kid,tid);
//            BeanUtils.copyProperties(tagAndKnowledgeDto,tagKnowledgeEntity);
//            if(tagAndKnowledgeRepository.countByKidAndTid(kid,tid)==0){
//                tagAndKnowledgeRepository.save(tagKnowledgeEntity);
//            } else {
//                return new ReturnVO(ReturnCode.FAIL ,"已存在该关系的建立");
//            }
//        }catch (Exception e){
//            e.printStackTrace();
//            return new ReturnVO(ReturnCode.FAIL, tagKnowledgeEntity.toString());
//        }
//        return new ReturnVO(tagKnowledgeEntity);
    }


    /**
     * 知识点操作
     * 知识点修改相应的tag
     * @return
     */
    @PutMapping("/knowledges")
    ReturnVO knowledgUpdateTag(int kid, int tid) {
        try{
            tagAndKnowledgeService.knowledgUpdateTag(kid,tid);
        }catch (EchoServiceException e){
            e.printStackTrace();
            return new ReturnVO(e);
        }
        return new ReturnVO();

//        TagAndKnowledgeDto tagAndKnowledgeDto = null;
//        TagKnowledgeEntity tagKnowledgeEntity = new TagKnowledgeEntity();
//        try {
//            tagAndKnowledgeDto = new TagAndKnowledgeDto(kid, tid);
//            BeanUtils.copyProperties(tagAndKnowledgeDto, tagKnowledgeEntity);
//            if (tagAndKnowledgeRepository.countByKidAndTid(kid, tid) == 1) {
//                tagAndKnowledgeRepository.save(tagKnowledgeEntity);
//            } else {
//                return new ReturnVO(ReturnCode.FAIL, "不存在对应关系");
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//            return new ReturnVO(ReturnCode.FAIL, tagKnowledgeEntity.toString());
//        }
//        return new ReturnVO(tagKnowledgeEntity);
    }


    /**
     * 知识点操作
     * 根据知识点的kid查对应的所有标签
     * @return
     */
    @GetMapping("/knowledges/{kid}")
    ReturnVO getByKid(@PathVariable Integer id) {
        List<TagDto> tags;
        try{
            tags = tagAndKnowledgeService.getByKid(id);
        }catch (Exception e){
            e.printStackTrace();
            return new ReturnVO(ReturnCode.FAIL);
        }
        return new ReturnVO(tags);
    }


    /**
     *
     * 标签根据kid删除所有标签对应知识点的关系
     * @param kid
     * @return
     */
    @PostMapping("/tags/{kid}")
    ReturnVO tagDeleteKnowledge(@PathVariable int kid){
        try{
            tagAndKnowledgeService.tagDeleteKnowledge(kid);
        }catch (Exception e){
            e.printStackTrace();
            return new ReturnVO(ReturnCode.FAIL);
        }
        return new ReturnVO();
    }


    /**
     * 根据标签id查所在哪些知识点下
     * @param tid
     * @return
     */
    @GetMapping("/knowledges/{tid}")
    ReturnVO getByTid(@PathVariable Integer tid) {
        List<KnowledgeDto> knowledges;
        try{
            knowledges=tagAndKnowledgeService.getByTid(tid);
        }catch (Exception e){
            e.printStackTrace();
            return new ReturnVO(ReturnCode.FAIL);
        }
        return new ReturnVO(knowledges);
    }
}
