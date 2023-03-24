package com.example.zuccknowledge.repository;

import com.example.zuccknowledge.entity.KnowledgeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface KnowledgeRepository extends JpaRepository<KnowledgeEntity, Integer> {
    /**
     * 模糊查询知识点名称
     *
     * @param nameLike
     * @return
     */
    @Query(value = "SELECT * FROM knowledge WHERE kname LIKE ?", nativeQuery = true)
    List<KnowledgeEntity> getByNameLike(String nameLike);
}
