package com.example.zuccknowledge.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.zuccknowledge.entity.KReadRecordEntity;
import com.example.zuccknowledge.entity.KnowledgeEntity;
import com.example.zuccknowledge.exception.EchoServiceException;
import com.example.zuccknowledge.formbean.KRecordRankDto;
import com.example.zuccknowledge.formbean.KnowledgeDto;
import com.example.zuccknowledge.repository.*;
import com.example.zuccknowledge.service.KnowledgeService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@Service
public class KnowledgeServiceImpl implements KnowledgeService {
    @Autowired
    KReadRecordRepository kReadRecordRepository;
    @Autowired
    private KnowledgeRepository knowledgeRepository;
    @Autowired
    private PreRelationRepository preRelationRepository;
    @Autowired
    private CasesRepository casesRepository;
    @Autowired
    private TagAndKnowledgeRepository tagAndKnowledgeRepository;
    @Autowired
    private StringRedisTemplate redisTemplate;

    public static final String HOT_RANK = "hot_rank";

    /**
     * 获取所有知识点
     *
     * @return
     */
    @Override
    public List<KnowledgeDto> getAll() {
        return convert(knowledgeRepository.findAll());
    }

    /**
     * 根据id获取知识点
     *
     * @param id
     * @return
     */
    @Override
    public KnowledgeDto getById(Integer id, Integer reader) {
        Set<ZSetOperations.TypedTuple<String>> rangeWithScores = redisTemplate.opsForZSet().reverseRangeWithScores(HOT_RANK, 0, -1);
        for (ZSetOperations.TypedTuple<String> rangeWithScore : rangeWithScores) {
            System.out.println(rangeWithScore.getValue());
            JSONObject value = JSONObject.parseObject(rangeWithScore.getValue());
            System.out.println(value.getIntValue("kid") == id);
            if (value.getIntValue("kid") == id) {
                System.out.println(rangeWithScore.getScore());
                redisTemplate.opsForZSet().incrementScore(HOT_RANK, rangeWithScore.getValue(), 1);
                break;
            }
        }

        KnowledgeEntity knowledgeEntity = knowledgeRepository.getReferenceById(id);

        if (knowledgeEntity == null) {
            throw new EchoServiceException("没有找到id为 " + id + " 的知识点");
        }

        try {
            KReadRecordEntity kReadRecordEntity = new KReadRecordEntity();
            kReadRecordEntity.setKid(id);
            kReadRecordEntity.setReader(reader);
            kReadRecordEntity.setOpentime(new Timestamp(System.currentTimeMillis()));
            kReadRecordRepository.save(kReadRecordEntity);
        } catch (Exception e) {
            e.printStackTrace();
            throw new EchoServiceException("添加失败");
        }

        KnowledgeDto knowledgeDto = new KnowledgeDto();
        BeanUtils.copyProperties(knowledgeEntity, knowledgeDto);

        return knowledgeDto;
    }

    /**
     * 根据courseid获取其所有知识点
     *
     * @param CId
     * @return
     */
    @Override
    public List<KnowledgeDto> getByCId(Integer CId) {
        List<KnowledgeEntity> knowledgeEntities = knowledgeRepository.findByCourseid(CId);
        if (knowledgeEntities == null) {
            throw new EchoServiceException("没有找到id为 " + CId + " 的课程的知识点");
        }

        return convert(knowledgeEntities);
    }

    /**
     * 根据名称关键词模糊查询知识点
     *
     * @param nameLike
     * @return
     */
    @Override
    public List<KnowledgeDto> getByNameLike(String nameLike) {
        List<KnowledgeEntity> knowledgeEntities = knowledgeRepository.getByNameLike("%" + nameLike + "%");
        if (knowledgeEntities == null) {
            throw new EchoServiceException("没有找到关键词为 " + nameLike + " 的知识点");
        }

        return convert(knowledgeEntities);
    }

    /**
     * 添加/修改知识点
     *
     * @param knowledgeDto
     * @return
     */
    @Override
    public void saveKnowledge(KnowledgeDto knowledgeDto) {
        try {
            KnowledgeEntity knowledgeEntity = new KnowledgeEntity();
            BeanUtils.copyProperties(knowledgeDto, knowledgeEntity);
            knowledgeRepository.save(knowledgeEntity);
        } catch (Exception e) {
            e.printStackTrace();
            throw new EchoServiceException("添加/修改失败");
        }
    }

    /**
     * 根据id删除知识点
     *
     * @param id
     * @return
     */
    @Transactional
    @Override
    public void deleteKnowledge(int id) {
        try {
            casesRepository.deleteByKnowledgeid(id);
            preRelationRepository.deleteByKid(id);
            tagAndKnowledgeRepository.deleteByKid(id);
        } catch (Exception e) {
            e.printStackTrace();
            throw new EchoServiceException("删除失败");
        }
        knowledgeRepository.deleteById(id);
    }

    /**
     * 获取知识点热度排名方法实现类
     *
     * @auther zzt
     */
    @Override
    public Collection<ZSetOperations.TypedTuple<String>> getTop20Knowledges() {
        hotRankRedis();

        Set<ZSetOperations.TypedTuple<String>> rangeWithScores = redisTemplate.opsForZSet().reverseRangeWithScores(HOT_RANK, 0, 19);
        System.out.println("获取到的排行和分数列表:" + JSON.toJSONString(rangeWithScores));

        return rangeWithScores;
    }

    private void hotRankRedis() {
        Set<ZSetOperations.TypedTuple<String>> rangeWithScores = redisTemplate.opsForZSet().reverseRangeWithScores(HOT_RANK, 0, 19);
        System.out.println("获取到的排行和分数列表:" + JSON.toJSONString(rangeWithScores));
        System.out.println(rangeWithScores.size());
        if (rangeWithScores.size() == 0) {
            List<KRecordRankDto> knowledgeEntityList = knowledgeRepository.getKRecordRank();
            for (KRecordRankDto kRecordRankDto : knowledgeEntityList) {
                redisTemplate.opsForZSet().add(HOT_RANK, JSON.toJSONString(kRecordRankDto), kRecordRankDto.getRank());
            }
            System.out.println("当前使用mysql：获取所有知识点信息");
        }
    }

    private List<KnowledgeDto> convert(List<KnowledgeEntity> entityList) {
        List<KnowledgeDto> knowledgeDtoList = new ArrayList<>();
        entityList.stream().forEach(item -> {
            KnowledgeDto knowledgeDto = new KnowledgeDto();
            BeanUtils.copyProperties(item, knowledgeDto);
            knowledgeDtoList.add(knowledgeDto);
        });

        return knowledgeDtoList;
    }
}
