package com.example.zuccknowledge.controller;

import com.example.zuccknowledge.entity.TagCourseEntity;
import com.example.zuccknowledge.formbean.TagAndCourses;
import com.example.zuccknowledge.repository.TagAndCoursesRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tacc")
public class TagAndCoursesController {
    @Autowired
    private TagAndCoursesRepository tagAndCoursesRepository;

    /**
     * 添加课程与标签的关系
     *
     * @param tagAndCourses
     * @return
     */
    @PostMapping("/save")
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
    @DeleteMapping("/delete/{id}")
    public int deleteCourses(@PathVariable("id") int id) {
        tagAndCoursesRepository.deleteById(id);
        return 1;
    }
}
