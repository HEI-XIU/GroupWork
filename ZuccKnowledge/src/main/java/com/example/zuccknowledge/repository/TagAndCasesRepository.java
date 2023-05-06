package com.example.zuccknowledge.repository;

import com.example.zuccknowledge.entity.TagCasesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TagAndCasesRepository extends JpaRepository<TagCasesEntity, Integer> {
    @Query(value = "select * from tag_cases where casesid = ?1 and(tagid=1 or tagid = 2 or tagid = 3)",nativeQuery = true)
    int countByCasesid(int casesid);
}
