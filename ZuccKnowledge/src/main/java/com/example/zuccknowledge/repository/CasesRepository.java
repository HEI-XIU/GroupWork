package com.example.zuccknowledge.repository;

import com.example.zuccknowledge.entity.CasesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CasesRepository extends JpaRepository<CasesEntity, Integer> {
    /**
     * 模糊查询案例名称
     *
     * @param nameLike
     * @return
     */
    @Query(value = "SELECT * FROM cases WHERE casename LIKE ?", nativeQuery = true)
    List<CasesEntity> getByNameLike(String nameLike);

    @Query(value = "SELECT * FROM cases WHERE knowledgeid LIKE ?", nativeQuery = true)
    List<CasesEntity> getByKIdLike(String KIdLike);
    @Query(value = "SELECT * FROM cases WHERE caseid = ?", nativeQuery = true)
    CasesEntity getByCasesId(int id);
    void deleteByKnowledgeid(Integer KId);

}