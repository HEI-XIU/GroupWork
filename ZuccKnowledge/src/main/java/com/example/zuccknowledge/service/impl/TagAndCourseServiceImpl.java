package com.example.zuccknowledge.service.impl;

import com.example.zuccknowledge.entity.TagCourseEntity;
import com.example.zuccknowledge.exception.EchoServiceException;
import com.example.zuccknowledge.formbean.TagAndCoursesDto;
import com.example.zuccknowledge.repository.TagAndCoursesRepository;
import com.example.zuccknowledge.service.TagAndCourseService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TagAndCourseServiceImpl implements TagAndCourseService {
    @Autowired
    private TagAndCoursesRepository tagAndCoursesRepository;
    @Override
    public void saveKnowledge(TagAndCoursesDto tagAndCoursesDto) {
        try {
            TagCourseEntity tagCourseEntity = new TagCourseEntity();
            BeanUtils.copyProperties(tagAndCoursesDto, tagCourseEntity);
            tagAndCoursesRepository.save(tagCourseEntity);
        } catch (Exception e) {
            e.printStackTrace();
            throw new EchoServiceException("添加/修改失败");
        }
    }

    @Override
    public void deleteCourses( int id) {
        try {
            tagAndCoursesRepository.deleteById(id);
        } catch (Exception e) {
            e.printStackTrace();
            throw new EchoServiceException("删除失败");
        }
    }
}
