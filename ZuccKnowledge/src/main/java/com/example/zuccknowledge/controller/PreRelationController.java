package com.example.zuccknowledge.controller;

import com.example.zuccknowledge.entity.PrerelationEntity;
import com.example.zuccknowledge.formbean.Prerelation;
import com.example.zuccknowledge.formbean.PrerelationView;
import com.example.zuccknowledge.repository.PreRelationRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/preRelation")
public class PreRelationController {
    @Autowired
    private PreRelationRepository preRelationRepository;

    /**
     * 查询所有知识点与知识点关系
     *
     * @return
     */
    @GetMapping("/all")
    List<PrerelationView> getAll() {
        return preRelationRepository.getAll();
    }

    /**
     * 根据id查询其所有前置知识点
     *
     * @param id
     * @return
     */
    @GetMapping("/byId/{id}")
    List<PrerelationView> getById(@PathVariable Integer id) {
        return preRelationRepository.getByKId(id);
    }

    /**
     * 添加/修改知识点间关系
     *
     * @param prerelation
     * @return
     */
    @PostMapping("/save")
    public int savePreRelation(@RequestBody Prerelation prerelation) {
        try {
            PrerelationEntity prerelationEntity = new PrerelationEntity();
            BeanUtils.copyProperties(prerelation, prerelationEntity);
            preRelationRepository.save(prerelationEntity);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
        return 1;
    }

    /**
     * 根据id删除知识点与知识点关系
     *
     * @param id
     * @return
     */
    @DeleteMapping("/delete/{id}")
    public int deletePreRelation(@PathVariable("id") int id) {
        try {
            preRelationRepository.deleteById(id);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
        return 1;
    }
}
