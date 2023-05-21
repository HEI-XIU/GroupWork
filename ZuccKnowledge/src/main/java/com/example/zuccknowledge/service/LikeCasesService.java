package com.example.zuccknowledge.service;

import com.example.zuccknowledge.formbean.CasesDto;
import com.example.zuccknowledge.formbean.LikeCases;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    /**
     * 添加/修改案例-RabbitMQ
     *
     * @param likeCases
     * @return
     */
    void saveLikecases(LikeCases likeCases);

    /**
     * 保存点赞记录
     * @param likeCases
     * @return
     */
    void save(LikeCases likeCases);

    /**
     * 批量保存
     * @param list
     * @return
     */
    void saveAll(List<LikeCases> list);

    Page<LikeCases> getLikedListByLikedUserId(String likedUserId, Pageable pageable);

    /**
     * 根据点赞人的id查询点赞列表（即查询这个人都给谁点赞过）
     * @param likedPostId
     * @param pageable
     * @return
     */
    Page<LikeCases> getLikedListByLikedPostId(String likedPostId, Pageable pageable);

    /**
     * 通过被点赞人和点赞人id查询是否存在点赞记录
     * @param likedUserId
     * @param likedPostId
     * @return
     */
    LikeCases getByLikedUserIdAndLikedPostId(String likedUserId, String likedPostId);

    /**
     * 根据id删除案例
     *
     * @param id
     * @return
     */
    void deleteLikeCases(int id);

    void save1LikeCases(LikeCases likeCases);

    void transLikedFromRedis();
    void transLikedCountFromRedis();
}
