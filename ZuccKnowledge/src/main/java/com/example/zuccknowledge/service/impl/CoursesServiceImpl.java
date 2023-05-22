package com.example.zuccknowledge.service.impl;

import com.example.zuccknowledge.entity.CoursesEntity;
import com.example.zuccknowledge.exception.EchoServiceException;
import com.example.zuccknowledge.formbean.CoursesDto;
import com.example.zuccknowledge.repository.CoursesRepository;
import com.example.zuccknowledge.repository.KnowledgeRepository;
import com.example.zuccknowledge.repository.TagAndCoursesRepository;
import com.example.zuccknowledge.service.CoursesService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class CoursesServiceImpl implements CoursesService {
    @Autowired
    private CoursesRepository coursesRepository;
    @Autowired
    private TagAndCoursesRepository tagAndCoursesRepository;
    @Autowired
    private KnowledgeRepository knowledgeRepository;

    /**
     * 获取所有课程
     *
     * @return
     */
    @Override
    public List<CoursesDto> getAll() {
        return convert(coursesRepository.findAll());
    }

    /**
     * 根据id获取课程
     *
     * @param id
     * @return
     */
    @Override
    public CoursesDto getById(Integer id) {
        CoursesEntity coursesEntity = coursesRepository.getReferenceById(id);
        if (coursesEntity == null) {
            throw new EchoServiceException("没有找到id为 " + id + " 的课程");
        }

        CoursesDto coursesDto = new CoursesDto();
        BeanUtils.copyProperties(coursesEntity, coursesDto);

        return coursesDto;
    }

    /**
     * 根据名称关键词模糊查询课程
     *
     * @param nameLike
     * @return
     */
    @Override
    public List<CoursesDto> getByNameLike(String nameLike) {
        List<CoursesEntity> coursesEntities = coursesRepository.getByNameLike("%" + nameLike + "%");
        if (coursesEntities == null) {
            throw new EchoServiceException("没有找到关键词为 " + nameLike + " 的课程");
        }

        return convert(coursesEntities);
    }

    /**
     * 添加/修改课程
     *
     * @param coursesDto
     * @return
     */
    @Override
    public void saveKnowledge(CoursesDto coursesDto) {
        try {
            CoursesEntity coursesEntity = new CoursesEntity();
            BeanUtils.copyProperties(coursesDto, coursesEntity);
            coursesRepository.save(coursesEntity);
        } catch (Exception e) {
            e.printStackTrace();
            throw new EchoServiceException("添加/修改失败");
        }
    }

    /**
     * 根据id删除课程
     *
     * @param id
     * @return
     */
    @Transactional
    @Override
    public void deleteCourses(int id) {
        try {
            tagAndCoursesRepository.deleteByCid(id);
            knowledgeRepository.deleteByCourseid(id);
        } catch (Exception e) {
            e.printStackTrace();
            throw new EchoServiceException("删除失败");
        }
        coursesRepository.deleteById(id);
    }

    private List<CoursesDto> convert(List<CoursesEntity> entityList) {
        List<CoursesDto> coursesDtoList = new ArrayList<>();
        entityList.stream().forEach(item -> {
            CoursesDto coursesDto = new CoursesDto();
            BeanUtils.copyProperties(item, coursesDto);
            coursesDtoList.add(coursesDto);
        });

        return coursesDtoList;
    }
}
