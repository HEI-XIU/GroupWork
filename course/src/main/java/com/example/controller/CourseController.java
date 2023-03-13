package com.example.controller;

import com.example.entity.Course;
import com.example.mapper.CourseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
@RequestMapping("/course")
public class CourseController {
    @Autowired
    CourseMapper courseMapper;

    @GetMapping("/queryCourseList")
    public List<Course> queryCourseList() {
        List<Course> courseList = courseMapper.queryCourseList();
        if (courseList == null) {
            return null;
        }
//        for(Course course:courseList){
//            System.out.println(course);
//        }
        return courseList;
    }

    @GetMapping("/SearchCourse")
    public Course SearchCourse(@RequestBody String str) {
        Course course = courseMapper.SearchCourse(str);
        System.out.println(str);
        System.out.println(course);
        return course;
    }

    //增
    @PostMapping("/insertCourse")
    public int InsertCourse(@RequestBody Course course) {
//        System.out.println(course);
        return courseMapper.InsertCourse(course);
    }
    //postman中只能使用json格式传输，否则会报错

    //    改
    @PostMapping("/updateCourse")
    public int updateCourse(@RequestBody Course course) {
//        course.setUpdateTime(new Date());
//        System.out.println(course);
        return courseMapper.UpdateCourse(course);
    }
    //    删
    @PostMapping("/deleteCourse")
    public int deleteCourse(@RequestBody String str) {
//        course.setUpdateTime(new Date());
        System.out.println(str);
        return courseMapper.deleteCourse(str);
    }
}
