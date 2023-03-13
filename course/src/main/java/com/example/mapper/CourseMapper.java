package com.example.mapper;

import com.example.entity.Course;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface CourseMapper {
    List<Course> queryCourseList();
    Course SearchCourse(String str);
    int UpdateCourse(Course course);
    int InsertCourse(Course course);
    int deleteCourse(String id);

}
