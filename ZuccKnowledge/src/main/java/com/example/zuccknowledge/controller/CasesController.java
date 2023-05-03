package com.example.zuccknowledge.controller;

import com.example.zuccknowledge.formbean.CasesDto;
import com.example.zuccknowledge.entity.CasesEntity;
import com.example.zuccknowledge.formbean.CoursesDto;
import com.example.zuccknowledge.formbean.KnowledgeDto;
import com.example.zuccknowledge.repository.CasesRepository;
import com.example.zuccknowledge.result.ResponseData;
import com.example.zuccknowledge.result.ResponseMsg;
import com.example.zuccknowledge.service.CasesService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/cases/v1")
public class CasesController {
    @Autowired
    private CasesService casesService;

//    public CasesController(CasesService casesService) {
//        this.casesService = casesService;
//    }

    /**
     * 获取所有案例
     *
     * @return
     */
    @GetMapping("/all")
    public ResponseData getAll() {
        List<CasesDto> casesDto = casesService.getAll();
        return new ResponseData(ResponseMsg.SUCCESS, casesDto);
    }

    /**
     * 根据id获取案例
     *
     * @param id
     * @return
     */
    @GetMapping("/byid/{id}")
//    CasesDto getById(@PathVariable Integer id) {
//        CasesEntity casesEntity = casesRepository.getReferenceById(id);
//        CasesDto cases = new CasesDto();
//        BeanUtils.copyProperties(casesEntity, cases);
//
//        return cases;
//    }
    public ResponseData getById(@PathVariable Integer id) {
        CasesDto casesDto = casesService.getById(id);
        return new ResponseData(ResponseMsg.SUCCESS, casesDto);
    }

    /**
     * 根据名称关键词模糊查询案例
     *
     * @param nameLike
     * @return
     */
    @GetMapping("/byname/{nameLike}")
    public ResponseData getByNameLike(@PathVariable String nameLike) {
        List<CasesDto> casesDtos = casesService.getByNameLike("%" + nameLike + "%");
        return new ResponseData(ResponseMsg.SUCCESS, casesDtos);
    }


    /**
     * 根据KnowledgeID模糊查询案例
     *
     * @param KIdLike
     * @return
     */
    @GetMapping("/bykid/{KIdLike}")
//    List<CasesDto> getByKIdLike(@PathVariable String KIdLike) {
//        return convert(casesRepository.getByKIdLike("%" + KIdLike + "%"));
//    }
    public ResponseData getByKIdLike(@PathVariable String KIdLike) {
        List<CasesDto> casesDto = casesService.getByKIdLike(KIdLike);
        return new ResponseData(ResponseMsg.SUCCESS, casesDto);
    }

    /**
     * 添加/修改案例
     *
     * @param casesDto
     * @return
     */
    @PostMapping("/save")
//    public int saveCases(@RequestBody CasesDto cases) {
//        CasesEntity casesEntity = new CasesEntity();
//        BeanUtils.copyProperties(cases, casesEntity);
//        casesRepository.save(casesEntity);
//
//        return 1;
//    }
    public ResponseData saveCases(@RequestBody CasesDto casesDto) {
        casesService.saveCases(casesDto);
        return new ResponseData(ResponseMsg.SUCCESS);
    }

    /**
     * 根据id删除案例
     *
     * @param id
     * @return
     */
    @Transactional
    @DeleteMapping("/delete/{id}")
//    public int deleteCases(@PathVariable("id") int id) {
//        casesRepository.deleteById(id);
//        return 1;
//    }
    public ResponseData deleteCases(@PathVariable("id") int id) {
        casesService.deleteCases(id);
        return new ResponseData(ResponseMsg.SUCCESS);
    }

    private List<CasesDto> convert(List<CasesEntity> entityList) {
        List<CasesDto> casesList = new ArrayList<>();
        entityList.stream().forEach(item -> {
            CasesDto cases = new CasesDto();
            BeanUtils.copyProperties(item, cases);
            casesList.add(cases);
        });

        return casesList;
    }

}
