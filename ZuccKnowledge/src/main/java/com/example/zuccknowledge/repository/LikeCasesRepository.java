package com.example.zuccknowledge.repository;

import com.example.zuccknowledge.entity.LikeCasesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LikeCasesRepository extends JpaRepository<LikeCasesEntity,Integer> {

    @Query(value = "SELECT * from like_cases\n" +
            "WHERE (Username LIKE ?1 AND caseId = ?2)", nativeQuery = true)
    List<LikeCasesEntity> getByNameAndCase(String name, Integer caseid);

    @Query(value = "SELECT * from like_cases\n" +
            "WHERE Username LIKE ?", nativeQuery = true)
    List<LikeCasesEntity> getByName(String name);

    @Query(value = "SELECT * from like_cases\n" +
            "WHERE caseId = ?", nativeQuery = true)
    List<LikeCasesEntity> getByCId(int caseid);

}
