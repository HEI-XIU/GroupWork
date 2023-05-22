package com.example.zuccknowledge.service.impl;

import com.example.zuccknowledge.entity.TagEntity;
import com.example.zuccknowledge.entity.TagGroupEntity;
import com.example.zuccknowledge.formbean.TagAndGroupDto;
import com.example.zuccknowledge.formbean.TagDto;
import com.example.zuccknowledge.formbean.TagGroupDto;
import com.example.zuccknowledge.repository.TagAndGroupRepository;
import com.example.zuccknowledge.service.TagAndGroupService;
import com.example.zuccknowledge.utils.RequestParam;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class TagAndGroupServiceImpl implements TagAndGroupService {
    @Autowired
    private TagAndGroupRepository tagAndGroupRepository;
    @Override
    public List<TagAndGroupDto> getAll() {
        List<TagAndGroupDto>  tagAndGroupDtoList = null;
        try {
             convert(tagAndGroupRepository.findAll());
        }catch (Exception e){
            e.printStackTrace();
        }
        return tagAndGroupDtoList;
    }

    @Override
    public void deleteTag(int tid) {

    }

    @Override
    public List<TagAndGroupDto> getByGid(int gid) {
        return null;
    }

    @Override
    public void linkTagGroupById(TagAndGroupDto tagAndGroupDto) {

    }

    @Override
    public void linkTagGroupByName(RequestParam requestParam) {

    }

    @Override
    public void deleteGroup(int gid) {

    }

    @Override
    public void getByTid(int tid) {

    }
    List<TagAndGroupDto> convert(List<TagGroupEntity> entityList){
        List<TagAndGroupDto> tagAndGroupDtoList =new ArrayList<>();
        entityList.stream().forEach(item->{
            TagAndGroupDto tagAndGroupDto = new TagAndGroupDto();
            BeanUtils.copyProperties(item,tagAndGroupDto);
            tagAndGroupDtoList.add(tagAndGroupDto);
        });
        return tagAndGroupDtoList;
    }
    List<TagDto> convert1(List<TagEntity> entityList){
        List<TagDto> tagDtoList =new ArrayList<>();
        entityList.stream().forEach(item->{
            TagDto tagDto = new TagDto();
            BeanUtils.copyProperties(item,tagDto);
            tagDtoList.add(tagDto);
        });
        return tagDtoList;
    }
    List<TagGroupDto> convert2(List<TagGroupEntity> entityList){
        List<TagGroupDto> tagGroupDtoList =new ArrayList<>();
        entityList.stream().forEach(item->{
            TagGroupDto tagGroupDto = new TagGroupDto();
            BeanUtils.copyProperties(item,tagGroupDto);
            tagGroupDtoList.add(tagGroupDto);
        });
        return tagGroupDtoList;
    }
}
