package com.example.zuccknowledge.repository;

import com.example.zuccknowledge.entity.TagCourseEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagAndCoursesRepository  extends JpaRepository<TagCourseEntity, Integer> {
    /**
     * 根据课程id删除所有标签与该课程的联系
     *
     * @param id
     * @return
     */
    void deleteByCid(Integer id);
}
