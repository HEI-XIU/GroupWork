package com.example.zuccknowledge.repository;


import com.example.zuccknowledge.entity.TagEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TagRepository extends JpaRepository<TagEntity,Integer>{
//    List<TagEntity> findAll();

//    TagEntity getReferenceById(Integer id);
    @Query(value="select * from tag where tagname like ?",nativeQuery = true)
    List<TagEntity> getNameLike(String s);
}
