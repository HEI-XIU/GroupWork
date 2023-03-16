package com.example.mapper;

import com.example.entity.Course;
import com.example.entity.Tag;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface TagMapper {
    List<Tag> queryTagList();
    Tag SearchTag( String str);
    int UpdateTag(Tag tag);
    int LinkTagGroup(Tag tag);
    int InsertTag(Tag tag);
    int deleteTag(String id);
}
