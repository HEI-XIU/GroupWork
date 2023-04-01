package com.example.zuccknowledge.controller;


import com.example.zuccknowledge.entity.TagEntity;
import com.example.zuccknowledge.entity.TagGroupEntity;
import com.example.zuccknowledge.formbean.Tag;
import com.example.zuccknowledge.formbean.TagAndGroup;
import com.example.zuccknowledge.repository.GroupRepository;
import com.example.zuccknowledge.repository.TagAndGroupRepository;
import com.example.zuccknowledge.repository.TagRepository;
import com.example.zuccknowledge.utils.RequestParam;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/tag")
public class TagController {
    @Autowired
    private TagRepository tagRepository;
    @Autowired
    private TagAndGroupRepository tagAndGroupRepository;
    @Autowired
    private GroupRepository groupRepository;

    @GetMapping("/all")
    List<Tag> getAll() {
        return convert(tagRepository.findAll());
    }

    @GetMapping("/byId/{id}")
    Optional<TagEntity> getById(@PathVariable Integer id) {
        Optional<TagEntity> tagEntity = tagRepository.findById(id);
        System.out.println(tagEntity.toString());
//        Tag tag = new Tag();
//        BeanUtils.copyProperties(tagEntity,tag);
        return tagEntity;
    }

    @GetMapping("/byName/{nameLike}")
    List<Tag> getByName(@PathVariable String nameLike) {
        return convert(tagRepository.getNameLike("%" + nameLike + "%"));
    }

    //修改以及存储
    @PostMapping("/save")
    public int saveTag(@RequestBody Tag tag) {
        TagEntity tagEntity = new TagEntity();
        BeanUtils.copyProperties(tag, tagEntity);
        tagRepository.save(tagEntity);
        return 1;
    }

    @PostMapping("/delete/{id}")
    public int deleteTag(@PathVariable int id) {
        tagRepository.deleteById(id);
        return 1;
    }

    @PostMapping("/linkById")
    public int linkTagGroupById(@RequestBody TagAndGroup tagAndGroup) {
        TagGroupEntity tagGroupEntity = new TagGroupEntity();
//        System.out.println(tagAndGroupRepository.findByTidAndGid(tagAndGroup.getTid(),tagAndGroup.getGid()));
        System.out.println(tagAndGroup.getTid() + " " + tagAndGroup.getGid());
        if (tagAndGroupRepository.countByTidAndGid(tagAndGroup.getTid(), tagAndGroup.getGid()) == 0) {
            BeanUtils.copyProperties(tagAndGroup, tagGroupEntity);
            tagAndGroupRepository.save(tagGroupEntity);
        }
        return 1;
    }

    @PostMapping("/linkByName")
    public int linkTagGroupByName(@RequestBody RequestParam requestParam) {
        TagGroupEntity tagGroupEntity = new TagGroupEntity();
        int tid = tagRepository.findByTagname(requestParam.getTname().toString());
        int gid = groupRepository.findByGname(requestParam.getGname().toString());
        tagGroupEntity.setTid(tid);
        tagGroupEntity.setGid(gid);
        System.out.println(tagGroupEntity.getTid());
        if (tagAndGroupRepository.countByTidAndGid(tagGroupEntity.getTid(), tagGroupEntity.getGid()) == 0) {
            tagAndGroupRepository.save(tagGroupEntity);
        }
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
