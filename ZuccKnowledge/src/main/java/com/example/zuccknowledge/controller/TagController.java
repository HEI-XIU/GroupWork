package com.example.zuccknowledge.controller;


import com.example.zuccknowledge.entity.TagEntity;
import com.example.zuccknowledge.entity.TagGroupEntity;
import com.example.zuccknowledge.utils.RequestParam;
import com.example.zuccknowledge.formbean.Tag;
import com.example.zuccknowledge.formbean.TagAndGroup;
import com.example.zuccknowledge.repository.TagAndGroupRepository;
import com.example.zuccknowledge.repository.GroupRepository;
import com.example.zuccknowledge.repository.TagRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/tag")
public class TagController {
    @Autowired
    private TagRepository tagRepository;
    /**
     * 获得所有标签（仅做测试使用）
     * @return
     */
    @GetMapping("/all")
    List<Tag> getAll() {
        return convert(tagRepository.findAll());
    }

    /**
     * 根据标签id进行检索标签
     * @param id
     * @return
     */
    @GetMapping("/byId/{id}")
    Optional<TagEntity> getById(@PathVariable Integer id){
        Optional<TagEntity> tagEntity = tagRepository.findById(id);
        System.out.println(tagEntity.toString());
//        Tag tag = new Tag();
//        BeanUtils.copyProperties(tagEntity,tag);
        return tagEntity;
    }

    /**
     * 根据标签名字进行模糊查询标签
     * @param nameLike
     * @return
     */
    @GetMapping("/byName/{nameLike}")
    List<Tag> getByName(@PathVariable String nameLike){
        return convert(tagRepository.getNameLike("%"+nameLike+"%"));
    }

    /**
     * 对tag进行修改以及存储
     * @param tag
     * @return
     */
    @PostMapping("/save")
    public int saveTag(@RequestBody Tag tag){
        TagEntity tagEntity = new TagEntity();
        BeanUtils.copyProperties(tag,tagEntity);
        tagRepository.save(tagEntity);
        return 1;
    }

    /**
     * 根据标签id进行删除tag
     * @param id
     * @return
     */
    @PostMapping("/delete/{id}")
    public int deleteTag(@PathVariable int id){
        tagRepository.deleteById(id);
        return 1;
    }

    /**
     *
     * @param entityList
     * @return
     */
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
