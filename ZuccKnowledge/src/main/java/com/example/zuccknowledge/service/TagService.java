package com.example.zuccknowledge.service;

import com.example.zuccknowledge.formbean.TagDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TagService {
    /**
     * 分页查询标签
     * @param
     * @return
     */
//    Page<TagEntity> ListTag(Pageable pageable);

    List<TagDto> getAll();
    /**
     * 保存或修改标签
     * @param tag
     * @return
     */
    void saveTag(TagDto tag);

    /**
     * 根据id删除标签
     * @param id
     */
    void deleteTag(int id);

    /**
     * 根据名字查询标签的tagid
     * @param name
     * @return
     */
    int getTagByName(String name);

    /**
     * 根据id查询标签
     * @param id
     * @return
     */
    TagDto getTag(int id);

    /**
     * 更新tag标签的操作
     * @param id
     * @param tag
     * @return
     */
    TagDto updateTag(int id,TagDto tag);

    /**
     * 模糊查询
     * @param nameLike
     * @return
     */
    List<TagDto> getByNameLike(String nameLike);


}

