package com.example.mapper;

import com.example.entity.Tag;
import com.example.entity.TagGroup;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
@Mapper
public interface TagGroupMapper {
    List<TagGroup> queryTagGroupList();
    TagGroup SearchTagGroup(String str);
    int UpdateTagGroup(TagGroup tagGroup);
    int InsertTagGroup(TagGroup tagGroup);
    int deleteTagGroup(String id);
}
