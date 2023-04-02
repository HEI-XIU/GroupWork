package com.example.zuccknowledge.repository;

import com.example.zuccknowledge.entity.TagKnowledgeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;

public interface TagAndKnowledgeRepository extends JpaRepository<TagKnowledgeEntity,Integer> {
//    @Query(value = "delete tag_knowledge tid =?1",nativeQuery = true)
    @Modifying
    @Transactional
    int deleteByTid(int id);

//    @Query(value = "delete tag_knowledge kid =?1",nativeQuery = true)
    @Modifying
    @Transactional
    int deleteByKid(int id);

    @Query(value = "select count(*) from tag_knowledge where kid =?1 and tid =?2",nativeQuery = true)
    int countByKidAndTid(int kid,int tid);
    @Query(value = "select count(*) from tag_knowledge where tkid =?1 ",nativeQuery = true)
    int countByTkid(int tkid);
}
