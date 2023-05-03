package com.example.zuccknowledge.repository;

import com.example.zuccknowledge.entity.PrerelationEntity;
import com.example.zuccknowledge.formbean.PrerelationView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PreRelationRepository extends JpaRepository<PrerelationEntity, Integer> {
    @Query(value = "SELECT preid, p.kid, prekid, kname preKName, courseid preCId, description preDes, emphasis preEmphasis\n" +
            "FROM prerelation p LEFT JOIN knowledge k ON (p.prekid = k.kid)", nativeQuery = true)
    List<PrerelationView> getAll();

    @Query(value = "SELECT preid, p.kid, prekid, kname preKName, courseid preCId, description preDes, emphasis preEmphasis\n" +
            "FROM prerelation p LEFT JOIN knowledge k ON (p.prekid = k.kid)" +
            "WHERE p.kid = ?", nativeQuery = true)
    List<PrerelationView> getByKId(Integer id);

    @Query(value = "SELECT preid, p.kid, prekid, kname preKName, courseid preCId, description preDes, emphasis preEmphasis\n" +
            "FROM prerelation p LEFT JOIN knowledge k ON (p.kid = k.kid)\n" +
            "WHERE p.prekid = ?", nativeQuery = true)
    List<PrerelationView> getByPreKId(Integer preKId);

    void deleteByKid(Integer Kid);

    /**
     * 根据课程把知识点关联在一起
     *
     * @param kname
     * @return
     */
    @Query(value = "SELECT * FROM knowledge\n" +
            "WHERE courseid = (SELECT courseid FROM knowledge WHERE kname = ?)", nativeQuery = true)
    List<PrerelationView> contactThroughKname(String kname);
}
