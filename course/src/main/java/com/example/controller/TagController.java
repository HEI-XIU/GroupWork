package com.example.controller;

import com.example.entity.Tag;
import com.example.mapper.TagMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Tag")
public class TagController {
    @Autowired
    TagMapper tagMapper;

    @GetMapping("/queryTagList")
    public List<Tag> queryTagList() {
        List<Tag> tagList = tagMapper.queryTagList();
        if (tagList == null) {
            return null;
        }
        return tagList;
    }
    @GetMapping("/searchTag")
    public Tag SearchTag(@RequestBody String str) {
        Tag tag = tagMapper.SearchTag(str);
        System.out.println(str);
        System.out.println(tag);
        return tag;
    }

    //增
    @PostMapping("/insertTag")
    public int InsertTag(@RequestBody Tag tag) {
//        System.out.println(tag);
        return tagMapper.InsertTag(tag);
    }
    //postman中只能使用json格式传输，否则会报错

    //    改
    @PostMapping("/updateTag")
    public int updateTag(@RequestBody Tag tag) {
//        tag.setUpdateTime(new Date());
//        System.out.println(tag);
        return tagMapper.UpdateTag(tag);
    }
    @PostMapping("/linkTagGroup")
    public int linkTagGroup(@RequestBody Tag tag) {
//        tag.setUpdateTime(new Date());
//        System.out.println(tag);
        return tagMapper.LinkTagGroup(tag);
    }
    //    删
    @PostMapping("/deleteTag")
    public int deleteTag(@RequestBody String str) {
//        tag.setUpdateTime(new Date());
        System.out.println(str);
        return tagMapper.deleteTag(str);
    }
}
