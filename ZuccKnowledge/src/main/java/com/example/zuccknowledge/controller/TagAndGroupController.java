package com.example.zuccknowledge.controller;

import com.example.zuccknowledge.entity.TagEntity;
import com.example.zuccknowledge.entity.TagGroupEntity;
import com.example.zuccknowledge.entity.TgroupEntity;
import com.example.zuccknowledge.repository.GroupRepository;
import com.example.zuccknowledge.repository.TagAndGroupRepository;
import com.example.zuccknowledge.repository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tagc")//tag and group controller
public class TagAndGroupController {
    @Autowired
    private TagAndGroupRepository tagAndGroupRepository;
    @Autowired
    private TagRepository tagRepository;
    @Autowired
    private GroupRepository groupRepository;

    @GetMapping("/rbtag")
//relationship between tag and group
    List<TagGroupEntity> getAll() {
        return tagAndGroupRepository.findAll();
    }

    //组删除标签
    @PostMapping("/deleteTag/{tid}")
    void deleteTag(@PathVariable int tid) {
        tagAndGroupRepository.deleteByTid(tid);
    }

    //标签删除组
    @PostMapping("/deleteGroup/{id}")
    int deleteGroup(@PathVariable int id) {
        return tagAndGroupRepository.deleteByGid(id);
    }

    //根据标签组的id查所包含的标签
    @GetMapping("/byTid/{id}")
    List<TagEntity> getByGid(@PathVariable Integer id) {
        return tagRepository.getByGid(id);
    }

    //根据标签id查所在组
    @GetMapping("/byGid/{id}")
    List<TgroupEntity> getByTid(@PathVariable Integer id) {
        return groupRepository.getByTid(id);
    }

}
