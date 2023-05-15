package com.example.zuccknowledge.controller;

import com.example.zuccknowledge.formbean.*;
import com.example.zuccknowledge.entity.CasesEntity;
import com.example.zuccknowledge.repository.CasesRepository;
import com.example.zuccknowledge.result.ResponseData;
import com.example.zuccknowledge.result.ResponseMsg;
import com.example.zuccknowledge.result.zk.ReturnCode;
import com.example.zuccknowledge.result.zk.ReturnVO;
import com.example.zuccknowledge.service.LikeCasesService;
import com.example.zuccknowledge.service.RedisService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/cases_like/v1")
public class LikeCasesController {
    @Autowired
    private LikeCasesService likeCasesService;
    @Autowired
    private RedisService redisService;
    /**
     * 获取所有点赞
     *
     * @return
     */
    @GetMapping("/all")
    public ResponseData getAll() {
        List<LikeCases> likeCases = likeCasesService.getAll();
        return new ResponseData(ResponseMsg.SUCCESS, likeCases);
    }

    /**
     * 添加/修改案例
     *
     * @param likeCases
     * @return
     */
    @PostMapping("/save")
    public ResponseData saveLikeCases(@RequestBody LikeCases likeCases) {
        likeCasesService.saveLikeCases(likeCases);
        return new ResponseData(ResponseMsg.SUCCESS);
    }

    /**
     * 根据User和caseId查找点赞记录
     *
     * @param likeCases
     * @return
     */
    @PostMapping("/savelikes")
    public ResponseData save1LikeCases(@RequestBody LikeCases likeCases) {
        likeCasesService.save1LikeCases(likeCases);
        return new ResponseData(ResponseMsg.SUCCESS);
    }

    /**
     * 根据User和caseId查找点赞记录
     *
     * @param name
     * @param caseid
     * @return
     */
    @GetMapping("/bynameandcase/{name}/{caseid}")
    public ResponseData getByNameAndCase(@PathVariable("name") String name,@PathVariable("caseid") Integer caseid) {
        List<LikeCases> likeCases = likeCasesService.getByNameAndCase(name,caseid);
        return new ResponseData(ResponseMsg.SUCCESS, likeCases);
    }

    /**
     * 根据User查找点赞记录
     *
     * @param name
     * @return
     */
    @GetMapping("/byname/{name}")
    public ResponseData getByName(@PathVariable String name) {
        List<LikeCases> likeCases = likeCasesService.getByName(name);
        return new ResponseData(ResponseMsg.SUCCESS, likeCases);
    }

    /**
     * 根据caseid查找点赞记录
     *
     * @param caseid
     * @return
     */
    @GetMapping("/bycaseid/{caseid}")
    public ResponseData getByCId(@PathVariable int caseid) {
        List<LikeCases> likeCases = likeCasesService.getByCId(caseid);
        return new ResponseData(ResponseMsg.SUCCESS, likeCases);
    }

    @PostMapping("/likecases")

    public ReturnVO LikeCases(@RequestBody LikeOrUnlikeBody likeOrUnlikeBody) {
        try{
        redisService.saveLikedRedis(likeOrUnlikeBody.getUername(),likeOrUnlikeBody.getCaseid());
        redisService.incrementLikedCount(likeOrUnlikeBody.getCaseid());
        }catch (Exception e){
            e.printStackTrace();
            return new ReturnVO(ReturnCode.FAIL);
        }
            return new ReturnVO();
        }
    @PostMapping("/unlikecases")
    public ReturnVO UnLikeCases(@RequestBody LikeOrUnlikeBody likeOrUnlikeBody) {
        try{
            redisService.unlikedFromRedis(likeOrUnlikeBody.getUername(),likeOrUnlikeBody.getCaseid());
            redisService.decrementLikedCount(likeOrUnlikeBody.getCaseid());
        }catch (Exception e){
            e.printStackTrace();
            return new ReturnVO(ReturnCode.FAIL);
        }
        return new ReturnVO();
    }
}
