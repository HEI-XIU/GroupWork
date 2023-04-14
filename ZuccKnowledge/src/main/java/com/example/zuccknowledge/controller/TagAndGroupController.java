package com.example.zuccknowledge.controller;

import com.example.zuccknowledge.entity.TagEntity;
import com.example.zuccknowledge.entity.TagGroupEntity;
import com.example.zuccknowledge.entity.TgroupEntity;
import com.example.zuccknowledge.formbean.PrerelationView;
import com.example.zuccknowledge.formbean.TagAndGroup;
import com.example.zuccknowledge.repository.GroupRepository;
import com.example.zuccknowledge.repository.TagAndGroupRepository;
import com.example.zuccknowledge.repository.TagRepository;
import com.example.zuccknowledge.utils.RequestParam;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tagc")//tag and group controller
public class TagAndGroupController {
    @Autowired
    private TagAndGroupRepository tagAndGroupRepository;
    @Autowired
    private TagRepository tagRepository;
    @Autowired
    private GroupRepository groupRepository;
    //考虑到没有修改标签的对应关系需求（或者说需求不明显）， 这里就没编写，如果哦有需求可进行添加
    /**
     * 获得所有的标签与标签组的对应关系
     * @return
     */
    @GetMapping("/rbtag")//relationship between tag and group
    List<TagGroupEntity> getAll(){return tagAndGroupRepository.findAll();}
    /**
     * 标签组的操作
     * 根据标签的id删除标签组与标签的对应关系
     * @param tid
     */
    @PostMapping("/deletetag/{tid}")
    void deleteTag(@PathVariable int tid){ tagAndGroupRepository.deleteByTid(tid);}
    /**
     * 标签组的操作
     * 根据标签组的id查所包含的标签
     * @param gid
     * @return
     */
    @GetMapping("/bytid/{gid}")
    List<TagEntity> getByGid(@PathVariable Integer gid) {
        return tagRepository.getByGid(gid);
    }
    /**
     * 根据tagid和groupid来建立两者的关系
     * @param tagAndGroup
     * @return
     */
    @PostMapping("/linkbyid")
    public int linkTagGroupById(@RequestBody TagAndGroup tagAndGroup){
        TagGroupEntity tagGroupEntity =new TagGroupEntity();
//        System.out.println(tagAndGroupRepository.findByTidAndGid(tagAndGroup.getTid(),tagAndGroup.getGid()));
        System.out.println(tagAndGroup.getTid()+" "+tagAndGroup.getGid());
        if(tagAndGroupRepository.countByTidAndGid(tagAndGroup.getTid(),tagAndGroup.getGid())==0){
            BeanUtils.copyProperties(tagAndGroup,tagGroupEntity);
            tagAndGroupRepository.save(tagGroupEntity);
        }
        return 1;
    }
    /**
     * 根据tagname和groupname来建立两者的关系(模糊查询)
     * @param requestParam
     * @return
     */
    @PostMapping("/linkbyname")
    public int linkTagGroupByName( @RequestBody RequestParam requestParam){
        TagGroupEntity tagGroupEntity = new TagGroupEntity();
        int tid = tagRepository.findByTagname(requestParam.getTname().toString());
        int gid = groupRepository.findByGname(requestParam.getGname().toString());
        tagGroupEntity.setTid(tid);
        tagGroupEntity.setGid(gid);
        System.out.println(tagGroupEntity.getTid());
        if(tagAndGroupRepository.countByTidAndGid(tagGroupEntity.getTid(),tagGroupEntity.getGid())==0){
            tagAndGroupRepository.save(tagGroupEntity);
        }
        return 1;
    }
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /**
     * 标签的操作
     * 根据标签组id删除所有标签与标签组的对应关系
     * @param gid
     * @return
     */
    @PostMapping("/deletegroup/{gid}")
    int deleteGroup(@PathVariable int gid){return tagAndGroupRepository.deleteByGid(gid);}
    /**
     * 标签的操作
     * 根据标签id查所在的标签组有哪些组
     * @param tid
     * @return
     */
    @GetMapping("/bygid/{tid}")
    List<TgroupEntity> getByTid(@PathVariable Integer tid) {
        return groupRepository.getByTid(tid);
    }
}
