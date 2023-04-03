package com.example.zuccknowledge.controller;

import com.example.zuccknowledge.entity.KnowledgeEntity;
import com.example.zuccknowledge.formbean.Knowledge;
import com.example.zuccknowledge.repository.CasesRepository;
import com.example.zuccknowledge.repository.KnowledgeRepository;
import com.example.zuccknowledge.repository.PreRelationRepository;
import com.example.zuccknowledge.repository.TagAndKnowledgeRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/knowledge")
public class KnowledgeController {
    @Autowired
    private KnowledgeRepository knowledgeRepository;
    @Autowired
    private PreRelationRepository preRelationRepository;
    @Autowired
    private CasesRepository casesRepository;
    @Autowired
    private TagAndKnowledgeRepository tagAndKnowledgeRepository;

    /**
     * 获取所有知识点
     *
     * @return
     */
    @GetMapping("/all")
    List<Knowledge> getAll() {
        return convert(knowledgeRepository.findAll());
    }

    /**
     * 根据id获取知识点
     *
     * @param id
     * @return
     */
    @GetMapping("/byId/{id}")
    Knowledge getById(@PathVariable Integer id) {
        KnowledgeEntity knowledgeEntity = knowledgeRepository.getReferenceById(id);
        Knowledge knowledge = new Knowledge();
        BeanUtils.copyProperties(knowledgeEntity, knowledge);

        return knowledge;
    }

    @GetMapping("/byCId/{CId}")
    List<Knowledge> getByCId(@PathVariable Integer CId) {
        return convert(knowledgeRepository.findByCourseid(CId));
    }

    /**
     * 根据名称关键词模糊查询知识点
     *
     * @param nameLike
     * @return
     */
    @GetMapping("/byName/{nameLike}")
    List<Knowledge> getByNameLike(@PathVariable String nameLike) {
        return convert(knowledgeRepository.getByNameLike("%" + nameLike + "%"));
    }

    /**
     * 添加/修改知识点
     *
     * @param knowledge
     * @return
     */
    @PostMapping("/save")
    public int saveKnowledge(@RequestBody Knowledge knowledge) {
        try {
            KnowledgeEntity knowledgeEntity = new KnowledgeEntity();
            BeanUtils.copyProperties(knowledge, knowledgeEntity);
            knowledgeRepository.save(knowledgeEntity);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
        return 1;
    }

    /**
     * 根据id删除知识点
     *
     * @param id
     * @return
     */
    @Transactional
    @DeleteMapping("/delete/{id}")
    public int deleteKnowledge(@PathVariable("id") int id) {
        try {
            casesRepository.deleteByKnowledgeid(id);
            preRelationRepository.deleteByKid(id);
            tagAndKnowledgeRepository.deleteByKid(id);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
        knowledgeRepository.deleteById(id);
        return 1;
    }

    private List<Knowledge> convert(List<KnowledgeEntity> entityList) {
        List<Knowledge> knowledgeList = new ArrayList<>();
        entityList.stream().forEach(item -> {
            Knowledge knowledge = new Knowledge();
            BeanUtils.copyProperties(item, knowledge);
            knowledgeList.add(knowledge);
        });

        return knowledgeList;
    }
}
