package com.example.zuccknowledge.controller;

import com.example.zuccknowledge.entity.CoursesEntity;
import com.example.zuccknowledge.formbean.Courses;
import com.example.zuccknowledge.repository.CoursesRepository;
import com.example.zuccknowledge.repository.KnowledgeRepository;
import com.example.zuccknowledge.repository.TagAndCoursesRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/courses")
public class CoursesController {
    @Autowired
    private CoursesRepository coursesRepository;
    @Autowired
    private TagAndCoursesRepository tagAndCoursesRepository;
    @Autowired
    private KnowledgeRepository knowledgeRepository;

    /**
     * 获取所有课程
     *
     * @return
     */
    @GetMapping("/all")
    List<Courses> getAll() {
        return convert(coursesRepository.findAll());
    }

    /**
     * 根据id获取课程
     *
     * @param id
     * @return
     */
    @GetMapping("/byid/{id}")
    Courses getById(@PathVariable Integer id) {
        CoursesEntity coursesEntity = coursesRepository.getReferenceById(id);
        Courses courses = new Courses();
        BeanUtils.copyProperties(coursesEntity, courses);

        return courses;
    }

    /**
     * 根据名称关键词模糊查询课程
     *
     * @param nameLike
     * @return
     */
    @GetMapping("/byname/{nameLike}")
    List<Courses> getByNameLike(@PathVariable String nameLike) {
        return convert(coursesRepository.getByNameLike("%" + nameLike + "%"));
    }

    /**
     * 添加/修改课程
     *
     * @param courses
     * @return
     */
    @PostMapping("/save")
    public int saveKnowledge(@RequestBody Courses courses) {
        CoursesEntity coursesEntity = new CoursesEntity();
        BeanUtils.copyProperties(courses, coursesEntity);
        coursesRepository.save(coursesEntity);

        return 1;
    }

    /**
     * 根据id删除课程
     *
     * @param id
     * @return
     */
    @DeleteMapping("/delete/{id}")
    @Transactional
    public int deleteCourses(@PathVariable("id") int id) {
        tagAndCoursesRepository.deleteByCid(id);
        knowledgeRepository.deleteByCourseid(id);
        coursesRepository.deleteById(id);
        return 1;
    }

    private List<Courses> convert(List<CoursesEntity> entityList) {
        List<Courses> coursesList = new ArrayList<>();
        entityList.stream().forEach(item -> {
            Courses courses = new Courses();
            BeanUtils.copyProperties(item, courses);
            coursesList.add(courses);
        });

        return coursesList;
    }
}
