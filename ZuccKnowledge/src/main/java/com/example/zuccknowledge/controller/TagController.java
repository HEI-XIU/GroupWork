package com.example.zuccknowledge.controller;


import com.example.zuccknowledge.entity.TagEntity;
import com.example.zuccknowledge.entity.TagGroupEntity;
import com.example.zuccknowledge.formbean.Tag;
import com.example.zuccknowledge.formbean.TagAndGroup;
import com.example.zuccknowledge.repository.TagAndGroupRepository;
import com.example.zuccknowledge.repository.GroupRepository;
import com.example.zuccknowledge.repository.TagRepository;
import com.example.zuccknowledge.result.zk.ReturnCode;
import com.example.zuccknowledge.result.zk.ReturnVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/tags/v1/")

public class TagController {
    @Autowired
    private TagRepository tagRepository;
    @Autowired
    private TagAndGroupRepository tagAndGroupRepository;
    @Autowired
    private GroupRepository groupRepository;

    /**
     * 获取所有的标签信息
     * @return
     */
    @GetMapping()
    ReturnVO getAll() {
        List<Tag> tags;
        try {
            tags= convert(tagRepository.findAll());
        }  catch (Exception e) {
            e.printStackTrace();
            return new ReturnVO(ReturnCode.FAIL);
        }
        return new ReturnVO(tags);
    }


    /**
     * 获取指定id的标签
     * @param id
     * @return
     */
    @GetMapping("{id}")
    ReturnVO getById(@PathVariable Integer id){
        Optional<TagEntity> tagEntity;
        try {
             tagEntity = tagRepository.findById(id);
        }  catch (Exception e) {
            e.printStackTrace();
            return new ReturnVO(ReturnCode.FAIL);
        }
        return new ReturnVO(tagEntity);
    }

    /**
     *
     * @param nameLike
     * @return
     * 对应前端功能描述：对输入字进行模糊查询，并返回给下拉列表，当为空时，侧获取所有的tags
     */
    @GetMapping("{nameLike}")
    List<Tag> getByName(@PathVariable String nameLike){
        return convert(tagRepository.getNameLike("%"+nameLike+"%"));
    }


    /**
     * 修改以及存储
     * @param tag
     * @return
     */
    @PostMapping("save")
    public ReturnVO saveTag(@RequestBody Tag tag){
        try {
            TagEntity tagEntity = new TagEntity();
            BeanUtils.copyProperties(tag,tagEntity);
            tagRepository.save(tagEntity);
        }  catch (Exception e) {
            e.printStackTrace();
            return new ReturnVO(ReturnCode.FAIL);
        }
        return new ReturnVO();
    }


    /**
     * 根据id删除标签
     * @param id
     * @return
     */
    @DeleteMapping("{id}")
    public ReturnVO deleteTag(@PathVariable int id){
        try {
            tagRepository.deleteById(id);
        }  catch (Exception e) {
            e.printStackTrace();
            return new ReturnVO(ReturnCode.FAIL);
        }
        return new ReturnVO();
    }

    @PostMapping("linkbyid")
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
    @PostMapping("linkbyname")
//    public int linkTagGroupByName( @RequestBody RequestParam requestParam){
//        TagGroupEntity tagGroupEntity = new TagGroupEntity();
//        int tid = tagRepository.findByTagname(requestParam.getTname().toString());
//        int gid = groupRepository.findByGname(requestParam.getGname().toString());
//        tagGroupEntity.setTid(tid);
//        tagGroupEntity.setGid(gid);
//        System.out.println(tagGroupEntity.getTid());
//        if(tagAndGroupRepository.countByTidAndGid(tagGroupEntity.getTid(),tagGroupEntity.getGid())==0){
//            tagAndGroupRepository.save(tagGroupEntity);
//        }
//        return 1;
//    }
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






//
//@Controller
//@RequestMapping("/admin")
//public class TagController {
//
//    @Autowired
//    private TagService tagService;
//
//    @RequestMapping("/tags")
//    public String tags(@PageableDefault(size = 3,sort = {"id"},direction = Sort.Direction.DESC)
//                       Pageable pageable, Model model){
//        model.addAttribute("page",tagService.ListTag(pageable));
//        return "admin/tags";
//    }
//
//    @GetMapping("/tags/input")
//    public String input(Model model){
//        model.addAttribute("tag",new Tag());
//        return "admin/tags-input";
//    }
//
//    @PostMapping("/tags/add")
//    public String add(@Valid Tag tag, BindingResult result, RedirectAttributes attributes){
//        Tag tag1 = tagService.getTagByName(tag.getName());
//        if(tag1!=null){
//            result.rejectValue("name","nameError","不能添加相同的标签");
//        }
//        if(result.hasErrors()){
//            return "admin/tags-input";
//        }
//        Tag tag2 = tagService.saveTag(tag);
//        System.out.println(tag2);
//        if(tag2==null){
//            attributes.addFlashAttribute("message","新增失败");
//        }else{
//            attributes.addFlashAttribute("message","新增成功");
//        }
//        return "redirect:/admin/tags";
//    }
//
//    @RequestMapping("/tags/{id}/toUpdate")
//    public String toUpdate(@PathVariable Long id, Model model){
//        model.addAttribute("tag",tagService.getTag(id));
//        return "admin/tags-input";
//    }
//
//
//    @RequestMapping("/tags/update/{id}")
//    public String update(Tag tag, @PathVariable Long id, RedirectAttributes attributes, BindingResult result){
//        Tag tag1 = tagService.getTagByName(tag.getName());
//        if(tag1!=null){
//            result.rejectValue("name","nameError","不能添加重复的类");
//        }
//        if(result.hasErrors()){
//            return "admin/tags-input";
//        }
//        Tag tag2 = tagService.updateTag(id,tag);
//        if(tag2!=null){
//            attributes.addFlashAttribute("message","更新成功");
//        }else{
//            attributes.addFlashAttribute("message","更新失败");
//        }
//        return "redirect:/admin/tags";
//    }
//}

