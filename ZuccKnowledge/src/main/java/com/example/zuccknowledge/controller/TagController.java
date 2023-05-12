package com.example.zuccknowledge.controller;


import com.example.zuccknowledge.entity.TagEntity;
import com.example.zuccknowledge.entity.TagGroupEntity;
import com.example.zuccknowledge.formbean.TagDto;
import com.example.zuccknowledge.formbean.TagAndGroupDto;
import com.example.zuccknowledge.repository.TagAndGroupRepository;
import com.example.zuccknowledge.repository.GroupRepository;
import com.example.zuccknowledge.repository.TagRepository;
import com.example.zuccknowledge.result.zk.ReturnCode;
import com.example.zuccknowledge.result.zk.ReturnVO;
import com.example.zuccknowledge.service.TagService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/tags/v1/")

public class TagController {
    @Autowired
    private TagService tagService;
//    @Autowired
//    private TagRepository tagRepository;
    @Autowired
    private TagAndGroupRepository tagAndGroupRepository;
//    @Autowired
//    private GroupRepository groupRepository;

    /**
     * 获取所有的标签信息
     * @return
     */
    @GetMapping()
    ReturnVO getAll() {
        List<TagDto> tagDtos;
        try {
            tagDtos = tagService.getAll();
//            tagDtos = convert(tagRepository.findAll());
        }  catch (Exception e) {
            e.printStackTrace();
            return new ReturnVO(ReturnCode.FAIL);
        }
        return new ReturnVO(tagDtos);
    }


    /**
     * 获取指定id的标签
     * @param id
     * @return
     */
    @GetMapping("{id}")
    ReturnVO getById(@PathVariable Integer id){
        TagDto  tagDto = new TagDto();
        try {
            tagDto = tagService.getTag(id);
//             tagEntity = tagRepository.findById(id);
        }  catch (Exception e) {
            e.printStackTrace();
            return new ReturnVO(ReturnCode.FAIL);
        }
        return new ReturnVO(tagDto);
    }

    /**
     *
     * @param nameLike
     * @return
     * 对应前端功能描述：对输入字进行模糊查询，并返回给下拉列表，当为空时，侧获取所有的tags
     */
    @GetMapping("namelike/{nameLike}")
    ReturnVO getByName(@PathVariable String nameLike){
//        return convert(tagRepository.getNameLike("%"+nameLike+"%"));
        List<TagDto> tagDtoList = null;
        try{
            tagDtoList  = tagService.getByNameLike("%"+nameLike+"%");
        }catch (Exception e){
            e.printStackTrace();
            return new ReturnVO(ReturnCode.FAIL);
        }
        return new ReturnVO(tagDtoList);
    }


    /**
     * 修改以及存储
     * @param tagDto
     * @return
     */
    @PostMapping("save")
    public ReturnVO saveTag(@RequestBody TagDto tagDto){
        try {
            tagService.saveTag(tagDto);
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
            tagService.deleteTag(id);
//            tagRepository.deleteById(id);
        }  catch (Exception e) {
            e.printStackTrace();
            return new ReturnVO(ReturnCode.FAIL);
        }
        return new ReturnVO();
    }

    @PostMapping("linkbyid")
    public int linkTagGroupById(@RequestBody TagAndGroupDto tagAndGroupDto){
        TagGroupEntity tagGroupEntity =new TagGroupEntity();
//        System.out.println(tagAndGroupRepository.findByTidAndGid(tagAndGroupDto.getTid(),tagAndGroupDto.getGid()));
        System.out.println(tagAndGroupDto.getTid()+" "+ tagAndGroupDto.getGid());
        if(tagAndGroupRepository.countByTidAndGid(tagAndGroupDto.getTid(), tagAndGroupDto.getGid())==0){
            BeanUtils.copyProperties(tagAndGroupDto,tagGroupEntity);
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
    private List<TagDto> convert(List<TagEntity> entityList) {
        List<TagDto> tagDtoList = new ArrayList<>();
        entityList.stream().forEach(item -> {
            TagDto tagDto = new TagDto();
            BeanUtils.copyProperties(item, tagDto);
            tagDtoList.add(tagDto);
        });

        return tagDtoList;
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
//        model.addAttribute("tag",new TagDto());
//        return "admin/tags-input";
//    }
//
//    @PostMapping("/tags/add")
//    public String add(@Valid TagDto tag, BindingResult result, RedirectAttributes attributes){
//        TagDto tag1 = tagService.getTagByName(tag.getName());
//        if(tag1!=null){
//            result.rejectValue("name","nameError","不能添加相同的标签");
//        }
//        if(result.hasErrors()){
//            return "admin/tags-input";
//        }
//        TagDto tag2 = tagService.saveTag(tag);
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
//    public String update(TagDto tag, @PathVariable Long id, RedirectAttributes attributes, BindingResult result){
//        TagDto tag1 = tagService.getTagByName(tag.getName());
//        if(tag1!=null){
//            result.rejectValue("name","nameError","不能添加重复的类");
//        }
//        if(result.hasErrors()){
//            return "admin/tags-input";
//        }
//        TagDto tag2 = tagService.updateTag(id,tag);
//        if(tag2!=null){
//            attributes.addFlashAttribute("message","更新成功");
//        }else{
//            attributes.addFlashAttribute("message","更新失败");
//        }
//        return "redirect:/admin/tags";
//    }
//}

