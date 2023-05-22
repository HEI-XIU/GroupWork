package com.example.zuccknowledge.service;

import com.example.zuccknowledge.formbean.CoursesDto;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public interface CoursesService {

    /**
     * 获取所有课程
     *
     * @return
     */
    List<CoursesDto> getAll();

    /**
     * 根据id获取课程
     *
     * @param id
     * @return
     */
    CoursesDto getById(Integer id);

    /**
     * 根据名称关键词模糊查询课程
     *
     * @param nameLike
     * @return
     */
    List<CoursesDto> getByNameLike(String nameLike);

    /**
     * 添加/修改课程
     *
     * @param coursesDto
     * @return
     */
    void saveKnowledge(CoursesDto coursesDto);

    /**
     * 根据id删除课程
     *
     * @param id
     * @return
     */
    void deleteCourses(int id);
}
