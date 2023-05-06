package com.example.zuccknowledge.service;

import com.example.zuccknowledge.formbean.CasesDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CasesService {

    /**
     * 获取所有案例
     *
     * @return
     */
    List<CasesDto> getAll();

    /**
     * 根据id获取案例
     *
     * @param id
     * @return
     */
    CasesDto getById(Integer id);

    /**
     * 根据名称关键词模糊查询案例
     *
     * @param nameLike
     * @return
     */
    List<CasesDto> getByNameLike(String nameLike);

    /**
     * 根据KnowledgeID模糊查询案例
     *
     * @param KIdLike
     * @return
     */
    List<CasesDto> getByKIdLike(String KIdLike);

    /**
     * 添加/修改案例
     *
     * @param casesDto
     * @return
     */
    void saveCases(CasesDto casesDto);

    /**
     * 根据id删除案例
     *
     * @param id
     * @return
     */
    void deleteCases(int id);

}
