package com.example.zuccknowledge.controller;

import com.example.zuccknowledge.formbean.Cases;
import com.example.zuccknowledge.entity.CasesEntity;
import com.example.zuccknowledge.repository.CasesRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/cases")
public class CasesController {
    @Autowired
    private CasesRepository casesRepository;

    /**
     * 获取所有案例
     *
     * @return
     */
    @GetMapping("/all")
    List<Cases> getAll() {
        return convert(casesRepository.findAll());
    }

    /**
     * 根据id获取案例
     *
     * @param id
     * @return
     */
    @GetMapping("/byId/{id}")
    Cases getById(@PathVariable Integer id) {
        CasesEntity casesEntity = casesRepository.getReferenceById(id);
        Cases cases = new Cases();
        BeanUtils.copyProperties(casesEntity, cases);

        return cases;
    }

    /**
     * 根据名称关键词模糊查询案例
     *
     * @param nameLike
     * @return
     */
    @GetMapping("/byName/{nameLike}")
    List<Cases> getByNameLike(@PathVariable String nameLike) {
        return convert(casesRepository.getByNameLike("%" + nameLike + "%"));
    }

    /**
     * 添加/修改案例
     *
     * @param cases
     * @return
     */
    @PostMapping("/save")
    public int saveKnowledge(@RequestBody Cases cases) {
        CasesEntity casesEntity = new CasesEntity();
        BeanUtils.copyProperties(cases, casesEntity);
        casesRepository.save(casesEntity);

        return 1;
    }

    /**
     * 根据id删除案例
     *
     * @param id
     * @return
     */
    @DeleteMapping("/delete/{id}")
    public int deleteCases(@PathVariable("id") int id) {
        casesRepository.deleteById(id);
        return 1;
    }

    private List<Cases> convert(List<CasesEntity> entityList) {
        List<Cases> casesList = new ArrayList<>();
        entityList.stream().forEach(item -> {
            Cases cases = new Cases();
            BeanUtils.copyProperties(item, cases);
            casesList.add(cases);
        });

        return casesList;
    }

}
