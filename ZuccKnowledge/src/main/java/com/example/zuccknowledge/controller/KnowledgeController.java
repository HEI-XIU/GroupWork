package com.example.zuccknowledge.controller;

import com.example.zuccknowledge.formbean.KnowledgeDto;
import com.example.zuccknowledge.result.ResponseData;
import com.example.zuccknowledge.result.ResponseMsg;
import com.example.zuccknowledge.service.KnowledgeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.transaction.Transactional;
import java.util.List;

@RestController
@RequestMapping("/api/knowledge/v1")
public class KnowledgeController {
    @Autowired
    private KnowledgeService knowledgeService;

    /**
     * 获取所有知识点
     *
     * @return
     */
    @GetMapping("/all")
    public ResponseData getAll() {
        List<KnowledgeDto> knowledgeDto = knowledgeService.getAll();
        return new ResponseData(ResponseMsg.SUCCESS, knowledgeDto);
    }

    /**
     * 根据id获取知识点
     *
     * @param id
     * @return
     */
    @GetMapping("/byid/{id}")
    public ResponseData getById(@PathVariable Integer id) {
        KnowledgeDto knowledgeDto = knowledgeService.getById(id);
        return new ResponseData(ResponseMsg.SUCCESS, knowledgeDto);
    }

    /**
     * 根据courseid获取其所有知识点
     *
     * @param CId
     * @return
     */
    @GetMapping("/bycid/{CId}")
    public ResponseData getByCId(@PathVariable Integer CId) {
        List<KnowledgeDto> knowledgeDtos = knowledgeService.getByCId(CId);
        return new ResponseData(ResponseMsg.SUCCESS, knowledgeDtos);
    }

    /**
     * 根据名称关键词模糊查询知识点
     *
     * @param nameLike
     * @return
     */
    @GetMapping("/byname/{nameLike}")
    public ResponseData getByNameLike(@PathVariable String nameLike) {
        List<KnowledgeDto> knowledgeDtos = knowledgeService.getByNameLike(nameLike);
        return new ResponseData(ResponseMsg.SUCCESS, knowledgeDtos);
    }

    /**
     * 添加/修改知识点
     *
     * @param knowledgeDto
     * @return
     */
    @PostMapping("/save")
    public ResponseData saveKnowledge(@RequestBody KnowledgeDto knowledgeDto) {
        knowledgeService.saveKnowledge(knowledgeDto);
        return new ResponseData(ResponseMsg.SUCCESS);
    }

    /**
     * 根据id删除知识点
     *
     * @param id
     * @return
     */
    @Transactional
    @DeleteMapping("/delete/{id}")
    public ResponseData deleteKnowledge(@PathVariable("id") int id) {
        knowledgeService.deleteKnowledge(id);
        return new ResponseData(ResponseMsg.SUCCESS);
    }
}
