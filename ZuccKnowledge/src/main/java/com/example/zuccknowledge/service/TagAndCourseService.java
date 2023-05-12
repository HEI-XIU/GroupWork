package com.example.zuccknowledge.service;

import com.example.zuccknowledge.formbean.TagAndCoursesDto;

public interface TagAndCourseService {
    void saveKnowledge(TagAndCoursesDto tagAndCoursesDto);
    void deleteCourses( int id);

}
