package com.example.zuccknowledge.controller;


import com.example.zuccknowledge.entity.TgroupEntity;
import com.example.zuccknowledge.formbean.TagGroupDto;
import com.example.zuccknowledge.repository.GroupRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/taggroup")
public class TagGroupController {
    @Autowired
    private GroupRepository tgRepository;

    //获取所有标签组
    @GetMapping("/all")
    List<TagGroupDto> getAll() {
        return convert(tgRepository.findAll());
    }
    //根据id获取标签组
    @GetMapping("/byid/{id}")
    Optional<TgroupEntity> getById(@PathVariable Integer id){
        Optional<TgroupEntity> tgEntity = tgRepository.findById(id);
        return tgEntity;
    }
    //根据标签组名模糊获取标签组
    @GetMapping("/byname/{nameLike}")
    List<TagGroupDto> getByName(@PathVariable String nameLike){
        return convert(tgRepository.getNameLike("%"+nameLike+"%"));
    }
    //新增和修改标签组
    @PostMapping("/save")
    public int saveTagGroup(@RequestBody TagGroupDto tg){
        TgroupEntity tgEntity = new TgroupEntity();
        BeanUtils.copyProperties(tg,tgEntity);
        tgRepository.save(tgEntity);
        return 1;
    }
    //根据id删除标签组
    @PostMapping("/delete/{id}")
    public int deleteTagGroup(@PathVariable int id){
        tgRepository.deleteById(id);
        return 1;
    }

    private List<TagGroupDto> convert(List<TgroupEntity> entityList) {
        List<TagGroupDto> TgList = new ArrayList<>();
        entityList.stream().forEach(item -> {
            TagGroupDto tg = new TagGroupDto();
            BeanUtils.copyProperties(item, tg);
            TgList.add(tg);
        });
        return TgList;
    }
}
