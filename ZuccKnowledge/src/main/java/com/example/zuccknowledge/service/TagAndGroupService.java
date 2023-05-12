package com.example.zuccknowledge.service;

import com.example.zuccknowledge.formbean.TagAndGroupDto;
import com.example.zuccknowledge.utils.RequestParam;

import java.util.List;

public interface TagAndGroupService {
    List<TagAndGroupDto> getAll();
    void deleteTag(int tid);
    List<TagAndGroupDto> getByGid(int gid);
    void linkTagGroupById(TagAndGroupDto tagAndGroupDto);
    void linkTagGroupByName(RequestParam requestParam);
    void deleteGroup(int gid);
    void getByTid(int tid);
}
