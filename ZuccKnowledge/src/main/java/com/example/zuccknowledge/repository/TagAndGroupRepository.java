package com.example.zuccknowledge.repository;

import com.example.zuccknowledge.entity.TagGroupEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;


public interface TagAndGroupRepository  extends JpaRepository<TagGroupEntity,Integer> {

//    @Transactional
//    @Modifying
//    @Query(value="INSERT into tag_group (tid,gid)VALUES((SELECT tagid from tag WHERE tagname = ?1 ),(SELECT gid FROM taggroup WHERE gname =?2))",nativeQuery = true)
//    Void findTnameAndGname(String tname,String gname);
    //未解决问题
    @Query(value = "select count(*) from tag_group where tid =?1 and gid =?2",nativeQuery = true)
    int countByTidAndGid(int tid,int gid);

//    @Query(value = "delete tag_group tid =?1",nativeQuery = true)
    @Modifying
    @Transactional
    int deleteByTid(int id);

//    @Query(value = "delete tag_group gid =?1",nativeQuery = true)
    @Modifying
    @Transactional
    int deleteByGid(int id);
}
