package com.example.zuccknowledge.service.impl;

import com.example.zuccknowledge.entity.CasesEntity;
import com.example.zuccknowledge.entity.LikeCasesEntity;
import com.example.zuccknowledge.exception.EchoServiceException;
import com.example.zuccknowledge.formbean.CasesDto;
import com.example.zuccknowledge.formbean.LikeCases;
import com.example.zuccknowledge.repository.LikeCasesRepository;
import com.example.zuccknowledge.service.LikeCasesService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class LikeCasesServiceImpl implements LikeCasesService {

    @Autowired
    private LikeCasesRepository likeCasesRepository;

    /**
     * 获取所有点赞
     *
     * @return
     */
    @Override
    public List<LikeCases> getAll() {
        return convert(likeCasesRepository.findAll());
    }

    @Override
    public List<LikeCases> getByNameAndCase(String name, Integer caseid) {
        List<LikeCasesEntity> likeCasesEntities = likeCasesRepository.getByNameAndCase(name, caseid);
        if (likeCasesEntities == null) {
            throw new EchoServiceException("没有找到用户 " + name + "给id为" + caseid + "的案例点赞的记录");
        }

        return convert(likeCasesEntities);
    }

    @Override
    public List<LikeCases> getByName(String name) {
        List<LikeCasesEntity> likeCasesEntities = likeCasesRepository.getByName(name);
        if (likeCasesEntities == null) {
            throw new EchoServiceException("没有找到用户 " + name + "的案例点赞的记录");
        }

        return convert(likeCasesEntities);
    }

    @Override
    public List<LikeCases> getByCId(int caseid) {
        List<LikeCasesEntity> likeCasesEntities = likeCasesRepository.getByCId(caseid);
        if (likeCasesEntities == null) {
            throw new EchoServiceException("没有找到对ID为" + caseid + "的案例点赞的记录");
        }

        return convert(likeCasesEntities);
    }

    @Override
    public void saveLikeCases(LikeCases likeCases) {
        try {
            LikeCasesEntity likeCasesEntity = new LikeCasesEntity();
            BeanUtils.copyProperties(likeCases, likeCasesEntity);
            likeCasesRepository.save(likeCasesEntity);
        } catch (Exception e) {
            e.printStackTrace();
            throw new EchoServiceException("添加/修改失败");
        }
    }

    private List<LikeCases> convert(List<LikeCasesEntity> entityList) {
        List<LikeCases> likeCasesList = new ArrayList<>();
        entityList.stream().forEach(item -> {
            LikeCases likeCases = new LikeCases();
            BeanUtils.copyProperties(item, likeCases);
            likeCasesList.add(likeCases);
        });

        return likeCasesList;
    }

}
