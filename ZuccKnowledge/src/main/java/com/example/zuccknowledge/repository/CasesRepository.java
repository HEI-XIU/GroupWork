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
}