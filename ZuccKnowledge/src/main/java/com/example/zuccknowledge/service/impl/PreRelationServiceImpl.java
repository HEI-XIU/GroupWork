package com.example.zuccknowledge.service.impl;

import com.example.zuccknowledge.entity.PrerelationEntity;
import com.example.zuccknowledge.exception.EchoServiceException;
import com.example.zuccknowledge.formbean.PrerelationDto;
import com.example.zuccknowledge.formbean.PrerelationView;
import com.example.zuccknowledge.repository.PreRelationRepository;
import com.example.zuccknowledge.service.PreRelationService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class PreRelationServiceImpl implements PreRelationService {
    @Autowired
    private PreRelationRepository preRelationRepository;

    /**
     * 查询所有知识点与知识点关系
     *
     * @return
     */
    @Override
    public List<PrerelationView> getAll() {
        return preRelationRepository.getAll();
    }

    /**
     * 根据id查询其所有前置知识点,即查询给定知识点的“依赖”的知识点
     *
     * @param id
     * @return
     */
    @Override
    public List<PrerelationView> getPre(@PathVariable Integer id) {
        List<PrerelationView> prerelationViews = preRelationRepository.getByKId(id);
        if (prerelationViews == null) {
            throw new EchoServiceException("没有找到id为 " + id + " 的知识点的前置知识点");
        }

        return prerelationViews;
    }

    /**
     * 根据id查询其所有后置知识点,即查询给定知识点的“被依赖”的知识点
     *
     * @param id
     * @return
     */
    @Override
    public List<PrerelationView> getPostposition(@PathVariable Integer id) {
        List<PrerelationView> prerelationViews = preRelationRepository.getByPreKId(id);
        if (prerelationViews == null) {
            throw new EchoServiceException("没有找到id为 " + id + " 的知识点的后置知识点");
        }

        return prerelationViews;
    }

    /**
     * 添加/修改知识点间关系
     *
     * @param prerelationDto
     * @return
     */
    @Override
    public void savePreRelation(@RequestBody PrerelationDto prerelationDto) {
        try {
            PrerelationEntity prerelationEntity = new PrerelationEntity();
            BeanUtils.copyProperties(prerelationDto, prerelationEntity);
            preRelationRepository.save(prerelationEntity);
        } catch (Exception e) {
            e.printStackTrace();
            throw new EchoServiceException("添加/修改失败");
        }
    }

    /**
     * 根据id删除知识点与知识点关系
     *
     * @param id
     * @return
     */
    @Override
    public void deletePreRelation(@PathVariable("id") int id) {
        try {
            preRelationRepository.deleteById(id);
        } catch (Exception e) {
            e.printStackTrace();
            throw new EchoServiceException("删除失败");
        }
    }

    /**
     * 根据课程把知识点关联在一起
     *
     * @param kname
     * @return
     */
    @Override
    public List<PrerelationView> getContactThroughKname(@PathVariable String kname) {
        List<PrerelationView> prerelationDtos = preRelationRepository.contactThroughKname(kname);
        if (prerelationDtos == null) {
            throw new EchoServiceException("没有找到与 " + kname + " 的知识点同课程下的其他知识点");
        }

        return prerelationDtos;
    }
}
