package com.example.zuccknowledge.service;

import com.example.zuccknowledge.entity.TagEntity;
import com.example.zuccknowledge.formbean.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TagService {
    Page<TagEntity> ListTag(Pageable pageable);

    TagEntity saveTag(TagEntity tag);

    void deleteTag(Long id);

    TagEntity getTagByName(String name);

    TagEntity getTag(Long id);

    TagEntity updateTag(Long id,TagEntity tag);

}

