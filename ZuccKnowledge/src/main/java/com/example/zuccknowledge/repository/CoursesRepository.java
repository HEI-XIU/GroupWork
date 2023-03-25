package com.example.zuccknowledge.repository;

import com.example.zuccknowledge.entity.CoursesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CoursesRepository extends JpaRepository<CoursesEntity, Integer> {
    /**
     * 模糊查询课程名称
     *
     * @param nameLike
     * @return
     */
    @Query(value = "SELECT * FROM courses WHERE coursename LIKE ?", nativeQuery = true)
    List<CoursesEntity> getByNameLike(String nameLike);
}
