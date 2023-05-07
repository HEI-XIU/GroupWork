package com.example.zuccknowledge.service;

import com.example.zuccknowledge.formbean.KnowledgeDto;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public interface KnowledgeService {
    /**
     * 获取所有知识点
     *
     * @return
     */
    List<KnowledgeDto> getAll();

    /**
     * 根据id获取知识点
     *
     * @param id
     * @return
     */
    KnowledgeDto getById(Integer id, Integer reader);

    /**
     * 根据courseid获取其所有知识点
     *
     * @param CId
     * @return
     */
    List<KnowledgeDto> getByCId(Integer CId);

    /**
     * 根据名称关键词模糊查询知识点
     *
     * @param nameLike
     * @return
     */
    List<KnowledgeDto> getByNameLike(String nameLike);

    /**
     * 添加/修改知识点
     *
     * @param knowledgeDto
     * @return
     */
    void saveKnowledge(KnowledgeDto knowledgeDto);

    /**
     * 根据id删除知识点
     *
     * @param id
     * @return
     */
    void deleteKnowledge(int id);

    /**
     * 获取知识点点赞排名
     *
     * @auther zzt
     */
    Collection<ZSetOperations.TypedTuple<String>> getTop20Knowledges();
}
