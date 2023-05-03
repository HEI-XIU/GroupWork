package com.example.zuccknowledge.controller;

import com.example.zuccknowledge.formbean.CoursesDto;
import com.example.zuccknowledge.result.ResponseData;
import com.example.zuccknowledge.result.ResponseMsg;
import com.example.zuccknowledge.service.CoursesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import javax.transaction.Transactional;

@RestController
@RequestMapping("/api/courses/v1")
public class CoursesController {
    @Autowired
    private CoursesService coursesService;

    /**
     * 获取所有课程
     *
     * @return
     */
    @GetMapping("/all")
    public ResponseData getAll() {
        List<CoursesDto> coursesDto = coursesService.getAll();
        return new ResponseData(ResponseMsg.SUCCESS, coursesDto);
    }

    /**
     * 根据id获取课程
     *
     * @param id
     * @return
     */
    @GetMapping("/byid/{id}")
    public ResponseData getById(@PathVariable Integer id) {
        CoursesDto coursesDto = coursesService.getById(id);
        return new ResponseData(ResponseMsg.SUCCESS, coursesDto);
    }

    /**
     * 根据名称关键词模糊查询课程
     *
     * @param nameLike
     * @return
     */
    @GetMapping("/byname/{nameLike}")
    public ResponseData getByNameLike(@PathVariable String nameLike) {
        List<CoursesDto> coursesDtos = coursesService.getByNameLike(nameLike);
        return new ResponseData(ResponseMsg.SUCCESS, coursesDtos);
    }

    /**
     * 添加/修改课程
     *
     * @param coursesDto
     * @return
     */
    @PostMapping("/save")
    public ResponseData saveKnowledge(@RequestBody CoursesDto coursesDto) {
        coursesService.saveKnowledge(coursesDto);
        return new ResponseData(ResponseMsg.SUCCESS);
    }

    /**
     * 根据id删除课程
     *
     * @param id
     * @return
     */
    @Transactional
    @DeleteMapping("/delete/{id}")
    public ResponseData deleteCourses(@PathVariable("id") int id) {
        coursesService.deleteCourses(id);
        return new ResponseData(ResponseMsg.SUCCESS);
    }
}