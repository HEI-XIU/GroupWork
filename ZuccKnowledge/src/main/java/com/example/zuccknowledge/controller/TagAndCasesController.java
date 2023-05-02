package com.example.zuccknowledge.controller;

import com.example.zuccknowledge.entity.TagCaseEntity;
import com.example.zuccknowledge.repository.TagAndCasesRepository;
import com.example.zuccknowledge.repository.TagRepository;
import com.example.zuccknowledge.result.zk.ReturnCode;
import com.example.zuccknowledge.result.zk.ReturnVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/tag_cases/v1/")
public class TagAndCasesController {
    @Autowired
    TagRepository tagRepository;
    @Autowired
    TagAndCasesRepository tagAndCasesRepository;

    @GetMapping()
    ReturnVO getAll(){
        List<TagCaseEntity> tagcases;
        try{
            tagcases= tagAndCasesRepository.findAll();
        }catch (Exception e){
            e.printStackTrace();
            return new ReturnVO(ReturnCode.FAIL);
        }
        return new ReturnVO(tagcases);
    }
    @PostMapping("cases")
    ReturnVO isok( int cid){
        try{
            if(tagAndCasesRepository.countByCasesid(cid)==0){
                return new ReturnVO(ReturnCode.FAIL,"未设置内置标签");
            }
        }catch (Exception e){
            e.printStackTrace();
            return new ReturnVO(ReturnCode.FAIL);
        }
        return new ReturnVO();
    }

}
