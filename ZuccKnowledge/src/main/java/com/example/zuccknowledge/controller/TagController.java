package com.example.zuccknowledge.controller;


import com.example.zuccknowledge.entity.TagEntity;
import com.example.zuccknowledge.formbean.Tag;
import com.example.zuccknowledge.repository.TagRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/tag")
public class TagController {
    @Autowired
    private TagRepository tagRepository;


    @GetMapping("/all")
    List<Tag> getAll() {
        return convert(tagRepository.findAll());
    }
    @GetMapping("/byId/{id}")
    Tag getById(@PathVariable Integer id){
        TagEntity tagEntity = tagRepository.getOne(id);
        Tag tag = new Tag();
        BeanUtils.copyProperties(tagEntity,tag);
        return tag;
    }
    @GetMapping("/byName/{nameLike}")
    List<Tag> getByName(@PathVariable String nameLike){
        return convert(tagRepository.getNameLike("%"+nameLike+"%"));
    }
    //修改以及存储
    @PostMapping("/save")
    public int saveTag(@RequestBody Tag tag){
        TagEntity tagEntity = new TagEntity();
        BeanUtils.copyProperties(tag,tagEntity);
        tagRepository.save(tagEntity);
        return 1;
    }
    @PostMapping("/link")
    public int linkTagGroup(@RequestBody Tag tag){
        TagEntity tagEntity = new TagEntity();
        BeanUtils.copyProperties(tag,tagEntity);
        tagRepository.save(tagEntity);
        return 1;
    }
    @PostMapping("/delete/{id}")
    public int deleteTag(@PathVariable int id){
        tagRepository.deleteById(id);
        return 1;
    }
    private List<Tag> convert(List<TagEntity> entityList) {
        List<Tag> TagList = new ArrayList<>();
        entityList.stream().forEach(item -> {
            Tag tag = new Tag();
            BeanUtils.copyProperties(item, tag);
            TagList.add(tag);
        });

        return TagList;
    }

}
