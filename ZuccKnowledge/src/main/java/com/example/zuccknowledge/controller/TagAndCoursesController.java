package com.example.zuccknowledge.controller;

import com.example.zuccknowledge.entity.TagCourseEntity;
import com.example.zuccknowledge.formbean.TagAndCoursesDto;
import com.example.zuccknowledge.repository.TagAndCoursesRepository;
import com.example.zuccknowledge.result.zk.ReturnCode;
import com.example.zuccknowledge.result.zk.ReturnVO;
import com.example.zuccknowledge.service.TagAndCourseService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/tag_courses/v1/")
public class TagAndCoursesController {
    @Autowired
    private TagAndCoursesRepository tagAndCoursesRepository;
    @Autowired
    private TagAndCourseService tagAndCourseService;
    /**
     * 添加课程与标签的关系
     *
     * @param tagAndCoursesDto
     * @return
     */
    @PostMapping("save")
    ReturnVO saveKnowledge(@RequestBody TagAndCoursesDto tagAndCoursesDto) {
//        TagCourseEntity tagCourseEntity = new TagCourseEntity();
//        BeanUtils.copyProperties(tagAndCoursesDto, tagCourseEntity);
        try{
            tagAndCourseService.saveKnowledge(tagAndCoursesDto);
        }catch (Exception e){
            e.printStackTrace();
             return new ReturnVO(ReturnCode.FAIL);
        }
        return new ReturnVO();
    }

    /**
     * 根据id删除课程与标签的关系
     *
     * @param id
     * @return
     */
    @DeleteMapping("courses/{id}")
    public ReturnVO deleteCourses(@PathVariable("id") int id) {
        try {
            tagAndCourseService.deleteCourses(id);
        }catch (Exception e){
            e.printStackTrace();
            return new ReturnVO(ReturnCode.FAIL);
        }
        return new ReturnVO();
    }
}
