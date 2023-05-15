package com.example.zuccknowledge.repository;

import com.example.zuccknowledge.entity.LikeCasesEntity;
import com.example.zuccknowledge.formbean.LikeCases;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    @Query(value = "SELECT * from like_cases\n" +
            "WHERE (caseid = ?1 AND code = ?2)", nativeQuery = true)
    Page<LikeCases> findByCaseidAndLiked(String caseid, Integer code, Pageable pageable);
    @Query(value = "SELECT * from like_cases\n" +
            "WHERE (username = ?1 AND code = ?2)", nativeQuery = true)
    Page<LikeCases> findByUsernameAndLiked(String username, Integer code, Pageable pageable);
    @Query(value = "SELECT * from like_cases\n" +
            "WHERE (caseid = ?1 AND username = ?2)", nativeQuery = true)
    LikeCasesEntity findByCaseidAndUsername(String caseid, String username);
}
