package com.example.zuccknowledge.controller;

import com.example.zuccknowledge.formbean.PrerelationDto;
import com.example.zuccknowledge.formbean.PrerelationView;
import com.example.zuccknowledge.result.ResponseData;
import com.example.zuccknowledge.result.ResponseMsg;
import com.example.zuccknowledge.service.PreRelationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/prerelation/v1")
public class PreRelationController {
    @Autowired
    private PreRelationService preRelationService;

    /**
     * 查询所有知识点与知识点关系
     *
     * @return
     */
    @GetMapping("/all")
    public ResponseData getAll() {
        List<PrerelationView> prerelationViews = preRelationService.getAll();
        return new ResponseData(ResponseMsg.SUCCESS, prerelationViews);
    }

    /**
     * 根据id查询其所有前置知识点,即查询给定知识点的“依赖”的知识点
     *
     * @param id
     * @return
     */
    @GetMapping("/kid/{id}")
    public ResponseData getById(@PathVariable Integer id) {
        List<PrerelationView> prerelationViews = preRelationService.getPre(id);
        return new ResponseData(ResponseMsg.SUCCESS, prerelationViews);
    }

    /**
     * 根据id查询其所有后置知识点,即查询给定知识点的“被依赖”的知识点
     *
     * @param id
     * @return
     */
    @GetMapping("/prekid/{id}")
    public ResponseData getPostposition(@PathVariable Integer id) {
        List<PrerelationView> prerelationViews = preRelationService.getPostposition(id);
        return new ResponseData(ResponseMsg.SUCCESS, prerelationViews);
    }

    /**
     * 添加/修改知识点间关系
     *
     * @param prerelationDto
     * @return
     */
    @PostMapping("/save")
    public ResponseData savePreRelation(@RequestBody PrerelationDto prerelationDto) {
        preRelationService.savePreRelation(prerelationDto);
        return new ResponseData(ResponseMsg.SUCCESS);
    }

    /**
     * 根据id删除知识点与知识点关系
     *
     * @param id
     * @return
     */
    @DeleteMapping("/delete/{id}")
    public ResponseData deletePreRelation(@PathVariable("id") int id) {
        preRelationService.deletePreRelation(id);
        return new ResponseData(ResponseMsg.SUCCESS);
    }

    /**
     * 根据课程把知识点关联在一起
     *
     * @param kname
     * @return
     */
    @GetMapping("/contact/{kname}")
    public ResponseData getContactThroughKname(@PathVariable String kname) {
        List<PrerelationView> prerelationViews = preRelationService.getContactThroughKname(kname);
        return new ResponseData(ResponseMsg.SUCCESS, prerelationViews);
    }
}
