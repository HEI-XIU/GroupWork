package com.example.zuccknowledge.service.impl;

import com.example.zuccknowledge.entity.TagCasesEntity;
import com.example.zuccknowledge.exception.EchoServiceException;
import com.example.zuccknowledge.formbean.TagAndCasesDto;
import com.example.zuccknowledge.repository.TagAndCasesRepository;
import com.example.zuccknowledge.service.TagAndCaseService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class TagAndCaseServiceImpl implements TagAndCaseService {
    @Autowired
     private TagAndCasesRepository tagAndCasesRepository;

    @Override
    public List<TagAndCasesDto> getAll() {
        List<TagAndCasesDto> list = null;
        try {
            list = convert(tagAndCasesRepository.findAll());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public boolean isok(int cid) {
        return tagAndCasesRepository.countByCasesid(cid)==0;
    }

    @Override
    public void save(TagAndCasesDto tagAndCasesDto) {
        try {
            TagCasesEntity tagCasesEntity = new TagCasesEntity();
            BeanUtils.copyProperties(tagAndCasesDto, tagCasesEntity);
            tagAndCasesRepository.save(tagCasesEntity);
        } catch (Exception e) {
            e.printStackTrace();
            throw new EchoServiceException("添加/修改失败");
        }
    }

    @Override
    public void delete(int id) {
        try{
            tagAndCasesRepository.deleteById(id);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private List<TagAndCasesDto> convert(List<TagCasesEntity> entityList) {
        List<TagAndCasesDto> tagAndCasesDtoList = new ArrayList<>();
        entityList.stream().forEach(item -> {
            TagAndCasesDto tagAndCasesDto = new TagAndCasesDto();
            BeanUtils.copyProperties(item, tagAndCasesDto);
            tagAndCasesDtoList.add(tagAndCasesDto);
        });

        return tagAndCasesDtoList;
    }
}
