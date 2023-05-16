package com.example.zuccknowledge.controller;

import com.example.zuccknowledge.formbean.KnowledgeDto;
import com.example.zuccknowledge.result.ResponseData;
import com.example.zuccknowledge.result.ResponseMsg;
import com.example.zuccknowledge.result.zk.ReturnCode;
import com.example.zuccknowledge.result.zk.ReturnVO;
import com.example.zuccknowledge.service.KnowledgeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.Collection;
import java.util.List;

import static com.example.zuccknowledge.service.impl.KnowledgeServiceImpl.HOT_RANK;

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
    @GetMapping("/byid/{id}/{reader}")
    public ResponseData getById(@PathVariable Integer id, @PathVariable Integer reader) {
        KnowledgeDto knowledgeDto = knowledgeService.getById(id, reader);
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

    /**
     * 获取知识点点赞排名
     *
     * @auther zzt
     */
    @GetMapping("/top20knowledges")
    public ReturnVO getTop20Knowledges() {
        Collection<ZSetOperations.TypedTuple<String>> tags;
        try {
            tags = knowledgeService.getTop20Knowledges();
        } catch (Exception e) {
            e.printStackTrace();
            return new ReturnVO(ReturnCode.FAIL);
        }
        return new ReturnVO(tags);
    }

    /**
     * 单个新增
     */
    @Autowired
    private StringRedisTemplate redisTemplate;

    @PostMapping("/test")
    public void add() {
        redisTemplate.opsForZSet().add(HOT_RANK, "知识点1", 20);
    }
}
