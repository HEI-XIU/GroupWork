package com.example.zuccknowledge.repository;

import com.example.zuccknowledge.entity.TagGroupEntity;
import com.example.zuccknowledge.entity.TgroupEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

public interface GroupRepository extends JpaRepository<TgroupEntity,Integer> {
    @Query(value = "select gid from tgroup where gname = ?",nativeQuery = true)
    int findByGname(String s);
    @Query(value = "select DISTINCT * from tgroup g ,tag_group tg WHERE g.gid = tg.gid and tg.tid = ?1 ", nativeQuery = true)
    List<TgroupEntity> getByTid(Integer id);
    @Query(value="select * from tgroup where gname like ?",nativeQuery = true)
    List<TgroupEntity> getNameLike(String s);

    @Transactional
    @Modifying
//    @Query(value = "DELETE FROM tgroup WHERE gid=?1 and 0=(SELECT * FROM(SELECT COUNT(tid) FROM tgroup t1,tag_group t2 WHERE t1.gid=t2.gid and t1.gid=?1) a ) ",nativeQuery = true)
    void deleteById(int id);

}
