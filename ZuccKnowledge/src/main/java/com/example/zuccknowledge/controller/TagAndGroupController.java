package com.example.zuccknowledge.controller;

import com.example.zuccknowledge.entity.TagEntity;
import com.example.zuccknowledge.entity.TagGroupEntity;
import com.example.zuccknowledge.entity.TgroupEntity;
import com.example.zuccknowledge.formbean.TagAndGroup;
import com.example.zuccknowledge.repository.GroupRepository;
import com.example.zuccknowledge.repository.TagAndGroupRepository;
import com.example.zuccknowledge.repository.TagRepository;
import com.example.zuccknowledge.utils.RequestParam;
import com.example.zuccknowledge.result.zk.ReturnCode;
import com.example.zuccknowledge.result.zk.ReturnVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/taggroups")//tag and group controller
public class TagAndGroupController {
    @Autowired
    private TagAndGroupRepository tagAndGroupRepository;
    @Autowired
    private TagRepository tagRepository;
    @Autowired
    private GroupRepository groupRepository;

    /**
     * 获得所有的标签与标签组的对应关系
     * @return
     */
    @GetMapping()//relationship between tag and group
    ReturnVO getAll(){
        List<TagGroupEntity> tags;
        try {
            tags=tagAndGroupRepository.findAll();
        }  catch (Exception e) {
            e.printStackTrace();
            return new ReturnVO(ReturnCode.FAIL);
        }
        return new ReturnVO(tags);
}
    /**
     * 标签组的操作
     * 根据标签的id删除标签组与标签的对应关系
     * @param tid
     * //后期修改位用事务进行处理
     */
    @DeleteMapping("tags/{tid}")
    ReturnVO deleteTag(@PathVariable int tid){
        try{
            tagAndGroupRepository.deleteByTid(tid);
        }catch (Exception e){
            e.printStackTrace();
            return new ReturnVO(ReturnCode.FAIL);
        }
        return new ReturnVO();
       }
    /**
     * 标签组的操作
     * 根据标签组的id查所包含的标签
     * @param gid
     * @return
     */
    @GetMapping("/tags/{gid}")
    ReturnVO getByGid(@PathVariable Integer gid) {
        List<TagEntity> tags;
        try{
           tags = tagRepository.getByGid(gid);
        }catch (Exception e){
            e.printStackTrace();
            return new ReturnVO(ReturnCode.FAIL);
        }
        return new ReturnVO(tags);
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
    @DeleteMapping ("/tags/{gid}")
    ReturnVO deleteGroup(@PathVariable int gid){
        try {
            tagAndGroupRepository.deleteByGid(gid);
        }catch (Exception e){
            e.printStackTrace();
            return new ReturnVO(ReturnCode.FAIL);
        }
        return new ReturnVO();
    }
    /**
     * 标签的操作
     * 根据标签id查所在的标签组有哪些组
     * @param tid
     * @return
     */
    @GetMapping("/groups/{tid}")
    ReturnVO getByTid(@PathVariable Integer tid) {
        List<TgroupEntity> groups;
        try{
            groups = groupRepository.getByTid(tid);
        }catch (Exception e){
            e.printStackTrace();
            return new ReturnVO(ReturnCode.FAIL);
        }
        return new ReturnVO(groups);
    }
}
