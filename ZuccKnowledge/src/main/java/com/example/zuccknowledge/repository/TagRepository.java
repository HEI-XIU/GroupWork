package com.example.zuccknowledge.repository;


import com.example.zuccknowledge.entity.TagEntity;
import com.example.zuccknowledge.formbean.PrerelationView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TagRepository extends JpaRepository<TagEntity,Integer>{
//    List<TagEntity> findAll();

//    TagEntity getReferenceById(Integer id);
    @Query(value="select * from tag where tagname like ?",nativeQuery = true)
    List<TagEntity> getNameLike(String s);

    @Query(value = "select tagid from tag where tagname = ?",nativeQuery = true)
    int findByTagname(String s);

    @Query(value = "select DISTINCT * from tag t ,tag_group tg WHERE t.tagid = tg.tid and tg.gid = ?1 ", nativeQuery = true)
    List<TagEntity> getByGid(Integer id);

    @Query(value = "select DISTINCT * from tag t ,tag_knowlege tk WHERE t.tagid = tk.tid and tk.kid = ?1 ", nativeQuery = true)
    List<TagEntity> getByKid(Integer id);

}
