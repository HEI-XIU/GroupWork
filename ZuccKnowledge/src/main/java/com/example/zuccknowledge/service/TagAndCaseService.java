package com.example.zuccknowledge.service;

import com.example.zuccknowledge.formbean.TagAndCasesDto;

import java.util.List;

public interface TagAndCaseService {
    List<TagAndCasesDto> getAll();
    boolean isok(int cid);

    void save(TagAndCasesDto tagAndCasesDto);

    void delete(int id);
}
