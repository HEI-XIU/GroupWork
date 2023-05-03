package com.example.zuccknowledge.service;

import com.example.zuccknowledge.formbean.PrerelationDto;
import com.example.zuccknowledge.formbean.PrerelationView;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PreRelationService {
    /**
     * 查询所有知识点与知识点关系
     *
     * @return
     */
    List<PrerelationView> getAll();

    /**
     * 根据id查询其所有前置知识点,即查询给定知识点的“依赖”的知识点
     *
     * @param id
     * @return
     */
    List<PrerelationView> getPre(Integer id);

    /**
     * 根据id查询其所有后置知识点,即查询给定知识点的“被依赖”的知识点
     *
     * @param id
     * @return
     */
    List<PrerelationView> getPostposition(Integer id);

    /**
     * 添加/修改知识点间关系
     *
     * @param prerelationDto
     * @return
     */
    void savePreRelation(PrerelationDto prerelationDto);

    /**
     * 根据id删除知识点与知识点关系
     *
     * @param id
     * @return
     */
    void deletePreRelation(int id);

    /**
     * 根据课程把知识点关联在一起
     *
     * @param kname
     * @return
     */
    List<PrerelationView> getContactThroughKname(String kname);

}
