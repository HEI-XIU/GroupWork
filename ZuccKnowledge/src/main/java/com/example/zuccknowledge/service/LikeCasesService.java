package com.example.zuccknowledge.service;

import com.example.zuccknowledge.formbean.CasesDto;
import com.example.zuccknowledge.formbean.LikeCases;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface LikeCasesService {

    /**
     * 获取所有案例
     *
     * @return
     */
    List<LikeCases> getAll();

    /**
     * 根据KnowledgeID模糊查询案例
     *
     * @param name
     * @param caseid
     * @return
     */
    List<LikeCases> getByNameAndCase(String name, Integer caseid);

    /**
     * 根据caseid查询案例
     *
     * @param caseid
     * @return
     */
    List<LikeCases> getByCId(int caseid);

    /**
     * 根据username模糊查询案例
     *
     * @param name
     * @return
     */
    List<LikeCases> getByName(String name);

    /**
     * 添加/修改案例
     *
     * @param likeCases
     * @return
     */
    void saveLikeCases(LikeCases likeCases);
}
