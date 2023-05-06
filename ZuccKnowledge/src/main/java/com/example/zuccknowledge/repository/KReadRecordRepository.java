package com.example.zuccknowledge.repository;

import com.example.zuccknowledge.entity.KReadRecordEntity;
import com.example.zuccknowledge.formbean.KViewReadCount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface KReadRecordRepository extends JpaRepository<KReadRecordEntity, Integer> {
    @Query(value = "SELECT kid, COUNT(*) readcount\n" +
            "FROM k_read_record\n" +
            "GROUP BY kid\n" +
            "ORDER BY readcount DESC", nativeQuery = true)
    List<KViewReadCount> getKReadCount();

    @Query(value = "SELECT *\n" +
            "FROM k_read_record\n" +
            "LIMIT ?", nativeQuery = true)
    List<KReadRecordEntity> getKRecordLimit(Integer limit);

    @Query(value = "SELECT *\n" +
            "FROM k_read_record\n" +
            "WHERE kid = ?\n" +
            "LIMIT ?", nativeQuery = true)
    List<KReadRecordEntity> getKRecordByKidLimit(Integer kId, Integer limit);

    @Query(value = "SELECT *\n" +
            "FROM k_read_record\n" +
            "WHERE reader = ?\n" +
            "LIMIT ?", nativeQuery = true)
    List<KReadRecordEntity> getKRecordByReaderLimit(Integer reader, Integer limit);

    @Query(value = "SELECT *\n" +
            "FROM k_read_record\n" +
            "WHERE reader = ? AND kid = ?\n" +
            "LIMIT ?", nativeQuery = true)
    List<KReadRecordEntity> getKRecordByKidAndReaderLimit(Integer reader, Integer kId, Integer limit);
}
