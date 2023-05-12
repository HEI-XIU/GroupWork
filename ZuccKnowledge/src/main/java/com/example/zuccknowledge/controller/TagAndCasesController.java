package com.example.zuccknowledge.controller;

import com.example.zuccknowledge.entity.TagCasesEntity;
import com.example.zuccknowledge.formbean.TagAndCasesDto;
import com.example.zuccknowledge.repository.TagAndCasesRepository;
import com.example.zuccknowledge.repository.TagRepository;
import com.example.zuccknowledge.result.zk.ReturnCode;
import com.example.zuccknowledge.result.zk.ReturnVO;
import com.example.zuccknowledge.service.TagAndCaseService;
import com.example.zuccknowledge.service.TagService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tag_cases/v1")
public class TagAndCasesController {
    @Autowired
    TagAndCasesRepository tagAndCasesRepository;
    @Autowired
    private TagAndCaseService tagAndCaseService;

    /**
     * @return 所有标签
     */
    @GetMapping()
    ReturnVO getAll(){
        List<TagAndCasesDto> tagcases;
        try{
            tagcases= tagAndCaseService.getAll();
        }catch (Exception e){
            e.printStackTrace();
            return new ReturnVO(ReturnCode.FAIL);
        }
        return new ReturnVO(tagcases);
    }

    /**
     *
     * @param cid
     * @return
     */
    @PostMapping("/cases")
    ReturnVO isok( int cid){
        try{
            if(tagAndCaseService.isok(cid)){
                return new ReturnVO(ReturnCode.FAIL,"未设置内置标签");
            }
        }catch (Exception e){
            e.printStackTrace();
            return new ReturnVO(ReturnCode.FAIL);
        }
        return new ReturnVO();
    }

    /**
     * 添加案例与标签的关系
     *
     * @param tagAndCasesDto
     * @return
     */
    @PostMapping("/save")
    ReturnVO saveCases(@RequestBody TagAndCasesDto tagAndCasesDto) {
        try {
            tagAndCaseService.save(tagAndCasesDto);
        }catch (Exception e){
            e.printStackTrace();
            return new ReturnVO(ReturnCode.FAIL);
        }
//        TagCasesEntity tagCasesEntity = new TagCasesEntity();
//        BeanUtils.copyProperties(tagAndCasesDto, tagCasesEntity);
//        tagAndCaseService.save(tagCasesEntity);
        return new ReturnVO();
    }

    /**
     * 根据id删除案例与标签的关系
     *
     * @param id
     * @return
     */
    @DeleteMapping("/delete/{id}")
    ReturnVO deleteCasess(@PathVariable("id") int id) {
        try{
            tagAndCaseService.delete(id);
        }catch (Exception e){
            e.printStackTrace();
            return new ReturnVO(ReturnCode.FAIL);
        }
        return new ReturnVO();
    }

}
