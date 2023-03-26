package com.example.zuccknowledge.controller;


import com.example.zuccknowledge.entity.TagGroupEntity;
import com.example.zuccknowledge.entity.TgroupEntity;
import com.example.zuccknowledge.formbean.TagGroup;
import com.example.zuccknowledge.repository.TagGroupRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/tagGroup")
public class TagGroupController {
    @Autowired
    private TagGroupRepository tgRepository;


    @GetMapping("/all")
    List<TagGroup> getAll() {
        return convert(tgRepository.findAll());
    }
    @GetMapping("/byId/{id}")
    Optional<TgroupEntity> getById(@PathVariable Integer id){
        Optional<TgroupEntity> tgEntity = tgRepository.findById(id);
        return tgEntity;
    }
    @GetMapping("/byName/{nameLike}")
    List<TagGroup> getByName(@PathVariable String nameLike){
        return convert(tgRepository.getNameLike("%"+nameLike+"%"));
    }
    //新增和修改
    @PostMapping("/save")
    public int saveTagGroup(@RequestBody TagGroup tg){
        TgroupEntity tgEntity = new TgroupEntity();
        BeanUtils.copyProperties(tg,tgEntity);
        tgRepository.save(tgEntity);
        return 1;
    }
    @PostMapping("/delete/{id}")
    public int deleteTagGroup(@PathVariable int id){
        tgRepository.deleteById(id);
        return 1;
    }

    private List<TagGroup> convert(List<TgroupEntity> entityList) {
        List<TagGroup> TgList = new ArrayList<>();
        entityList.stream().forEach(item -> {
            TagGroup tg = new TagGroup();
            BeanUtils.copyProperties(item, tg);
            TgList.add(tg);
        });
        return TgList;
    }
}