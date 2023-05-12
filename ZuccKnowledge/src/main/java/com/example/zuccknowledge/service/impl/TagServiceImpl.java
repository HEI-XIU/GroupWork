package com.example.zuccknowledge.service.impl;

import com.example.zuccknowledge.entity.CasesEntity;
import com.example.zuccknowledge.entity.TagEntity;
import com.example.zuccknowledge.exception.EchoServiceException;
import com.example.zuccknowledge.formbean.CasesDto;
import com.example.zuccknowledge.formbean.TagDto;
import com.example.zuccknowledge.repository.TagRepository;
import com.example.zuccknowledge.service.TagService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TagServiceImpl implements TagService {

    @Autowired
    private TagRepository tagRepository;


    @Override
    public List<TagDto> getAll() {
        return convert(tagRepository.findAll());
    }

    @Override
    public void saveTag(TagDto tagDto) {
        try{
            TagEntity tagEntity = new TagEntity();
            BeanUtils.copyProperties(tagDto, tagEntity);
            tagRepository.save(tagEntity);
        }catch (Exception e){
            e.printStackTrace();
            throw new EchoServiceException("添加/修改失败");
        }
    }

    @Override
    public void deleteTag(int id) {
        try{
            if( tagRepository.getReferenceById(id).getTagroot() ==1){
                throw new EchoServiceException("该标签内置标签，无法删除");
            }
            tagRepository.deleteById(id);
        }catch (Exception e){
            e.printStackTrace();
            throw new EchoServiceException("删除失败");
        }

    }

    @Override
    public int getTagByName(String name) {

        return tagRepository.findByTagname(name);
    }

    @Override
    public TagDto getTag(int id) {
        TagEntity tagEntity = tagRepository.findById(id).orElse(null);
        if(tagEntity==null){
            throw  new EchoServiceException("没有找到id为"+id+"的标签");
        }
        TagDto tagDto = new TagDto();
        BeanUtils.copyProperties(tagEntity,tagDto);
        return tagDto;
    }

    @Override
    public TagDto updateTag(int id, TagDto tagDto) {
        TagEntity tagEntity = tagRepository.findById(id).orElse(null);
        if(tagEntity==null){
            System.out.println("未获得更新对象");
            return null;
        }else{
            BeanUtils.copyProperties(tagEntity,tagDto);
            tagRepository.save(tagEntity);
        }
        return tagDto;

    }

    @Override
    public List<TagDto> getByNameLike(String nameLike) {
        List<TagEntity> tagDtos = tagRepository.getNameLike(nameLike);
        if (tagDtos == null) {
            throw new EchoServiceException("没有找到关键词为 " + nameLike + " 的标签");
        }

        return convert(tagDtos);
    }

    private List<TagDto> convert(List<TagEntity> entityList) {
        List<TagDto> tagDtoList = new ArrayList<>();
        entityList.stream().forEach(item -> {
            TagDto tagDto = new TagDto();
            BeanUtils.copyProperties(item, tagDto);
            tagDtoList.add(tagDto);
        });

        return tagDtoList;
    }
}


