package com.example.zuccknowledge.service.impl;

import com.example.zuccknowledge.entity.KnowledgeEntity;
import com.example.zuccknowledge.entity.TagEntity;
import com.example.zuccknowledge.entity.TagKnowledgeEntity;
import com.example.zuccknowledge.exception.EchoServiceException;
import com.example.zuccknowledge.formbean.KnowledgeDto;
import com.example.zuccknowledge.formbean.TagAndKnowledgeDto;
import com.example.zuccknowledge.formbean.TagDto;
import com.example.zuccknowledge.repository.KnowledgeRepository;
import com.example.zuccknowledge.repository.TagAndKnowledgeRepository;
import com.example.zuccknowledge.repository.TagRepository;
import com.example.zuccknowledge.service.TagAndKnowledgeService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class TagAndKnowledgeServiceIpml implements TagAndKnowledgeService {
    @Autowired
    private TagAndKnowledgeRepository tagAndKnowledgeRepository;
    @Autowired
    private TagRepository tagRepository;
    @Autowired
    private KnowledgeRepository knowledgeRepository;
    @Override
    public List<TagAndKnowledgeDto> getAll() {
        List<TagAndKnowledgeDto> tagAndKnowledgeDtoList = null;
        try{
            tagAndKnowledgeDtoList =  convert(tagAndKnowledgeRepository.findAll());
        }catch (Exception e){
            e.printStackTrace();
        }
        return tagAndKnowledgeDtoList;
    }

    @Override
    public void knowledgeDeleteTag(int id) {
        try {
            tagAndKnowledgeRepository.deleteByTid(id);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void knowledgAddTag(int kid, int tid) {
        TagAndKnowledgeDto tagAndKnowledgeDto = null;
        TagKnowledgeEntity tagKnowledgeEntity = new TagKnowledgeEntity();
        try{
            tagAndKnowledgeDto = new TagAndKnowledgeDto(kid,tid);
            BeanUtils.copyProperties(tagAndKnowledgeDto,tagKnowledgeEntity);
            if(tagAndKnowledgeRepository.countByKidAndTid(kid,tid)==0){
                tagAndKnowledgeRepository.save(tagKnowledgeEntity);
            } else {
                throw  new EchoServiceException("已存在该关系的建立");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void knowledgUpdateTag(int kid, int tid) {
        TagAndKnowledgeDto tagAndKnowledgeDto = null;
        TagKnowledgeEntity tagKnowledgeEntity = new TagKnowledgeEntity();
        try {
            tagAndKnowledgeDto = new TagAndKnowledgeDto(kid, tid);
            BeanUtils.copyProperties(tagAndKnowledgeDto, tagKnowledgeEntity);
            if (tagAndKnowledgeRepository.countByKidAndTid(kid, tid) == 1) {
                tagAndKnowledgeRepository.save(tagKnowledgeEntity);
            } else {
                throw  new EchoServiceException("不存在对应关系");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<TagDto> getByKid(int id) {
        List<TagEntity> tags = new ArrayList<>();
        try{
            tags = tagRepository.getByKid(id);
        }catch (Exception e){
            e.printStackTrace();
        }
        return convert1(tags);
    }

    @Override
    public void tagDeleteKnowledge(int id) {
        try{
            tagAndKnowledgeRepository.deleteByKid(id);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public List<KnowledgeDto> getByTid(int id) {
        List<KnowledgeEntity> knowledges = new ArrayList<>();
        try{
            knowledges=knowledgeRepository.getByTid(id);
        }catch (Exception e){
            e.printStackTrace();
        }
        return convert2(knowledges);
    }
    private List<TagAndKnowledgeDto> convert(List<TagKnowledgeEntity> entityList) {
        List<TagAndKnowledgeDto> tagAndKnowledgeDtoArrayList = new ArrayList<>();
        entityList.stream().forEach(item->{
            TagAndKnowledgeDto tagAndKnowledgeDto = new TagAndKnowledgeDto();
            BeanUtils.copyProperties(item,tagAndKnowledgeDto);
            tagAndKnowledgeDtoArrayList.add(tagAndKnowledgeDto);
        });
        return tagAndKnowledgeDtoArrayList;
    }
    private List<TagDto> convert1(List<TagEntity> entityList) {
        List<TagDto> tagDtoList = new ArrayList<>();
        entityList.stream().forEach(item -> {
            TagDto tagDto = new TagDto();
            BeanUtils.copyProperties(item, tagDto);
            tagDtoList.add(tagDto);
        });

        return tagDtoList;
    }
    private List<KnowledgeDto> convert2(List<KnowledgeEntity> entityList) {
        List<KnowledgeDto> knowledgeDtoList = new ArrayList<>();
        entityList.stream().forEach(item -> {
            KnowledgeDto knowledgeDto = new KnowledgeDto();
            BeanUtils.copyProperties(item, knowledgeDto);
            knowledgeDtoList.add(knowledgeDto);
        });

        return knowledgeDtoList;
    }
}
