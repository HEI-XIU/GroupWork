package com.example.controller;

import com.example.entity.Tag;
import com.example.entity.TagGroup;
import com.example.mapper.TagGroupMapper;
import com.example.mapper.TagMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping
public class TagGroupController {
    @Autowired
    TagGroupMapper tagGroupMapper;

    @GetMapping("/queryTagGroupList")
    public List<TagGroup> queryTagGroupList() {
        List<TagGroup> tagGroupList = tagGroupMapper.queryTagGroupList();
        if (tagGroupList == null) {
            return null;
        }
        return tagGroupList;
    }
    @GetMapping("/searchTagGroup")
    public TagGroup SearchTagGroup(@RequestBody String str) {
        TagGroup tagGroup = tagGroupMapper.SearchTagGroup(str);
        System.out.println(str);
        System.out.println(tagGroup);
        return tagGroup;
    }

    //增
    @PostMapping("/insertTagGroup")
    public int InsertTagGroup(@RequestBody TagGroup tagGroup) {
//        System.out.println(tag);
        return tagGroupMapper.InsertTagGroup(tagGroup);
    }
    //postman中只能使用json格式传输，否则会报错

    //    改
    @PostMapping("/updateTagGroup")
    public int UpdateTagGroup(@RequestBody TagGroup tagGroup) {
//        tag.setUpdateTime(new Date());
//        System.out.println(tag);
        return tagGroupMapper.UpdateTagGroup(tagGroup);
    }
    //    删
    @PostMapping("/deleteTagGroup")
    public int deleteTagGroup(@RequestBody String str) {
//        tag.setUpdateTime(new Date());
        System.out.println(str);
        return tagGroupMapper.deleteTagGroup(str);
    }
}
