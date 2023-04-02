package com.example.zuccknowledge.repository;

import com.example.zuccknowledge.entity.TagKonwledgeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;

public interface TagAndKnowledgeRepository extends JpaRepository<TagKonwledgeEntity,Integer> {
//    @Query(value = "delete tag_knowledge tid =?1",nativeQuery = true)
    @Modifying
    @Transactional
    int deleteByTid(int id);

//    @Query(value = "delete tag_knowledge kid =?1",nativeQuery = true)
    @Modifying
    @Transactional
    int deleteByKid(int id);
}
