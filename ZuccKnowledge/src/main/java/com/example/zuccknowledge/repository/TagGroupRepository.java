package com.example.zuccknowledge.repository;


import com.example.zuccknowledge.entity.TgroupEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TagGroupRepository extends JpaRepository<TgroupEntity, Integer> {
    @Query(value = "select * from tgroup where gname like ?", nativeQuery = true)
    List<TgroupEntity> getNameLike(String s);

    @Query(value = "select gid from tgroup where gname = ?", nativeQuery = true)
    int findByGname(String s);

}