package com.example.zuccknowledge.repository;

import com.example.zuccknowledge.entity.TgroupEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface GroupRepository extends JpaRepository<TgroupEntity, Integer> {
    @Query(value = "select gid from tgroup where gname = ?", nativeQuery = true)
    int findByGname(String s);

    @Query(value = "select DISTINCT * from tgroup g ,tag_group tg WHERE g.gid = tg.gid and tg.tid = ?1 ", nativeQuery = true)
    List<TgroupEntity> getByTid(Integer id);
}
