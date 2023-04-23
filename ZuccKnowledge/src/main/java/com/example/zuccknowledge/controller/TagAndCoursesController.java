package com.example.zuccknowledge.controller;

import com.example.zuccknowledge.entity.TagCourseEntity;
import com.example.zuccknowledge.formbean.TagAndCourses;
import com.example.zuccknowledge.repository.TagAndCoursesRepository;
import com.example.zuccknowledge.utils.ReturnCode;
import com.example.zuccknowledge.utils.ReturnVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/tag_courses/")
public class TagAndCoursesController {
    @Autowired
    private TagAndCoursesRepository tagAndCoursesRepository;

    /**
     * 添加课程与标签的关系
     *
     * @param tagAndCourses
     * @return
     */
    @PostMapping("save")
    public int saveKnowledge(@RequestBody TagAndCourses tagAndCourses) {
        TagCourseEntity tagCourseEntity = new TagCourseEntity();
        BeanUtils.copyProperties(tagAndCourses, tagCourseEntity);
        tagAndCoursesRepository.save(tagCourseEntity);

        return 1;
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
            tagAndCoursesRepository.deleteById(id);
        }catch (Exception e){
            e.printStackTrace();
            return new ReturnVO(ReturnCode.FAIL);
        }
        return new ReturnVO();
    }
}
