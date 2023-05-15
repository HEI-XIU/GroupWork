package com.example.zuccknowledge.service.impl;

import com.alibaba.fastjson.JSON;
import com.example.zuccknowledge.entity.CasesEntity;
import com.example.zuccknowledge.exception.EchoServiceException;
import com.example.zuccknowledge.formbean.CasesDto;
import com.example.zuccknowledge.repository.CasesRepository;
import com.example.zuccknowledge.service.CasesService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@Service
public class CasesServiceImpl implements CasesService {
    @Autowired
    private CasesRepository casesRepository;
    @Autowired
    private StringRedisTemplate redisTemplate;

    public static final String SCORE_RANK = "score_rank";


//    @Autowired
//    private TagAndCoursesRepository tagAndCoursesRepository;


    /**
     * 获取所有案例
     *
     * @return
     */
    @Override
    public List<CasesDto> getAll() {
        return convert(casesRepository.findAll());
    }

    /**
     * 根据id获取课程
     *
     * @param id
     * @return
     */
    @Override
    public CasesDto getById(Integer id) {
        CasesEntity casesEntity = casesRepository.getById(id);
        if (casesEntity == null) {
            throw new EchoServiceException("没有找到id为 " + id + " 的案例");
        }

        CasesDto casesDto = new CasesDto();
//        System.out.println("casesEntity"+casesRepository.getById(id));
        BeanUtils.copyProperties(casesEntity, casesDto);

        return casesDto;
    }

    /**
     * 根据名称关键词模糊查询课程
     *
     * @param nameLike
     * @return
     */
    @Override
    public List<CasesDto> getByNameLike(String nameLike) {
        List<CasesEntity> casesEntities = casesRepository.getByNameLike("%" + nameLike + "%");
        if (casesEntities == null) {
            throw new EchoServiceException("没有找到关键词为 " + nameLike + " 的案例");
        }

        return convert(casesEntities);
    }

    @Override
    public List<CasesDto> getByKIdLike(String KIdLike) {
        List<CasesEntity> casesEntities = casesRepository.getByKIdLike("%" + KIdLike + "%");
        if (casesEntities == null) {
            throw new EchoServiceException("没有找到ID为 " + KIdLike + " 的案例");
        }

        return convert(casesEntities);
    }

    @Override
    public void saveCases(CasesDto casesDto) {
        try {
            CasesEntity casesEntity = new CasesEntity();
            BeanUtils.copyProperties(casesDto, casesEntity);
            casesRepository.save(casesEntity);
        } catch (Exception e) {
            e.printStackTrace();
            throw new EchoServiceException("添加/修改失败");
        }
    }

    /**
     * 根据id删除案例
     *
     * @param id
     * @return
     */

    @Transactional
    @Override
    public void deleteCases(int id) {
        try {
            casesRepository.deleteById(id);
        } catch (Exception e) {
            e.printStackTrace();
            throw new EchoServiceException("删除失败");
        }
    }

    /**
     * 获取案例点赞排名方法实现类
     *
     * @auther HEI-XIU
     */
    @Override
    public Collection<ZSetOperations.TypedTuple<String>> getTop20Cases() {
//        redisTemplate.opsForValue();
        Set<String> range = redisTemplate.opsForZSet().reverseRange(SCORE_RANK, 0, 19);
        System.out.println("获取到的排行列表:" + JSON.toJSONString(range));
        Set<ZSetOperations.TypedTuple<String>> rangeWithScores = redisTemplate.opsForZSet().reverseRangeWithScores(SCORE_RANK, 0, 19);
        System.out.println("获取到的排行和分数列表:" + JSON.toJSONString(rangeWithScores));

        return rangeWithScores;

    }

    private List<CasesDto> convert(List<CasesEntity> entityList) {
        List<CasesDto> casesDtoList = new ArrayList<>();
        entityList.stream().forEach(item -> {
            CasesDto casesDto = new CasesDto();
            BeanUtils.copyProperties(item, casesDto);
            casesDtoList.add(casesDto);
        });

        return casesDtoList;
    }
}
