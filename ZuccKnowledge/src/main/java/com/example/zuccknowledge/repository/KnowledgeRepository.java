package com.example.zuccknowledge.repository;

import com.example.zuccknowledge.entity.KnowledgeEntity;
import com.example.zuccknowledge.entity.TgroupEntity;
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

    @Query(value = "select DISTINCT * from knowledge k ,tag_knowledge tk WHERE k.kid = tk.kid and tk.tid = ?1 ", nativeQuery = true)
    List<KnowledgeEntity> getByTid(Integer id);
}
