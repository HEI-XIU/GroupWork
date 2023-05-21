package com.example.zuccknowledge.service.impl;

import com.example.zuccknowledge.entity.CasesEntity;
import com.example.zuccknowledge.entity.LikeCasesEntity;
import com.example.zuccknowledge.exception.EchoServiceException;
import com.example.zuccknowledge.formbean.CasesDto;
import com.example.zuccknowledge.formbean.LikeCases;
import com.example.zuccknowledge.formbean.LikedCountDto;
import com.example.zuccknowledge.repository.CasesRepository;
import com.example.zuccknowledge.repository.LikeCasesRepository;
import com.example.zuccknowledge.service.CasesService;
import com.example.zuccknowledge.service.LikeCasesService;
import com.example.zuccknowledge.service.RedisService;
import com.example.zuccknowledge.utils.LikedStatusEnum;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Queue;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.sql.Timestamp;
import java.time.Instant;
@Service
public class LikeCasesServiceImpl implements LikeCasesService {

    @Autowired
    private LikeCasesRepository likeCasesRepository;
    @Autowired
    private RedisService redisService;
    @Autowired
    private CasesService casesService;
    @Autowired
    private CasesRepository casesRepository;
    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Autowired
    private MessageConverter messageConverter;
    @Override
    public void saveLikecases(LikeCases likeCases) {
        MessageProperties messageProperties = new MessageProperties();
        messageProperties.setContentType("application/json");
        Message message = messageConverter.toMessage(likeCases, messageProperties);
        LikeCasesEntity likeCasesentity = new LikeCasesEntity();
        likeCasesentity.setUsername(likeCases.getUsername());
        likeCasesentity.setCaseid(likeCases.getCaseid());
        likeCasesentity.setTime(Timestamp.from(Instant.now()));
        likeCasesentity.setLiked(likeCases.getLiked());

        likeCasesRepository.save(likeCasesentity);

        rabbitTemplate.convertAndSend("LikeQueue", message);
    }
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

    @Override
    public void deleteLikeCases(int id) {
        try {
            likeCasesRepository.deleteById(id);
        } catch (Exception e) {
            e.printStackTrace();
            throw new EchoServiceException("删除失败");
        }
    }

    @Override
    public void save1LikeCases(LikeCases likeCases) {
        try {
            List<LikeCasesEntity> likeCasesEntities = likeCasesRepository.getByNameAndCase(likeCases.getUsername(), Integer.parseInt(likeCases.getCaseid()));
            //该用户没有点赞记录
            if (likeCasesEntities.size() == 0) {
                System.out.println("ok insert");
                LikeCasesEntity likeCasesEntity = new LikeCasesEntity();
                BeanUtils.copyProperties(likeCases, likeCasesEntity);
                likeCasesRepository.save(likeCasesEntity);
            }else {
                System.out.println("likeid:" + likeCases.getLikeid());
                System.out.println(likeCasesEntities.get(0).getLikeid());
                likeCases.setLikeid(likeCasesEntities.get(0).getLikeid());
                List<LikeCasesEntity> likeCasesEntities1 = likeCasesRepository.judgelike(likeCases.getUsername(), Integer.parseInt(likeCases.getCaseid()));
                //有记录但现在liked为0
                if (likeCasesEntities1.size() == 0){
                    likeCasesRepository.deleteById(likeCasesEntities.get(0).getLikeid());
                    System.out.println("0 -> 1");
                    LikeCasesEntity likeCasesEntity = new LikeCasesEntity();
                    BeanUtils.copyProperties(likeCases, likeCasesEntity);
                    likeCasesRepository.save(likeCasesEntity);
                }else {
                    likeCasesRepository.deleteById(likeCasesEntities.get(0).getLikeid());
                    System.out.println("1 -> 0");
                    likeCases.setLiked("0");
                    LikeCasesEntity likeCasesEntity = new LikeCasesEntity();
                    BeanUtils.copyProperties(likeCases, likeCasesEntity);
                    likeCasesRepository.save(likeCasesEntity);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new EchoServiceException("添加/修改失败");
        }
    }

    @Override
    public void save(LikeCases likeCases) {
        try{
            LikeCasesEntity likeCasesEntity = new LikeCasesEntity();
//            System.out.println("saveLike"+likeCases);
            BeanUtils.copyProperties(likeCases,likeCasesEntity);
//            System.out.println("savelikeCasesEntity"+likeCasesEntity);
            likeCasesRepository.save(likeCasesEntity);
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    @Override
    public void saveAll(List<LikeCases> list) {
      try{

      }catch (Exception e){
          e.printStackTrace();
      }
    }



    public Page<LikeCases> getLikedListByLikedUserId(String caseid, Pageable pageable) {
        return likeCasesRepository.findByCaseidAndLiked(caseid, Integer.parseInt(LikedStatusEnum.LIKE.getCode()), pageable);
    }

    @Override
    public Page<LikeCases> getLikedListByLikedPostId(String username, Pageable pageable) {
        return likeCasesRepository.findByUsernameAndLiked(username, Integer.parseInt(LikedStatusEnum.LIKE.getCode()), pageable);
    }

    @Override
    public LikeCases getByLikedUserIdAndLikedPostId(String caseid, String username) {
        LikeCasesEntity likeCasesEntity  = likeCasesRepository.findByCaseidAndUsername(caseid, username);
        LikeCases likeCases = new LikeCases();
        if (likeCasesEntity!=null){
            BeanUtils.copyProperties(likeCasesEntity,likeCases);
            return likeCases;
        }else {
            return null;
        }

    }

    @Override
    public void transLikedFromRedis() {
        List<LikeCases> list = redisService.getLikedDataFromRedis();
//System.out.println("list"+list);
        for (LikeCases like : list) {
//System.out.println("like"+like);
            LikeCases ul = getByLikedUserIdAndLikedPostId(like.getCaseid(), like.getUsername());
//System.out.println("ul"+ul);
            if (ul == null){
                //没有记录，直接存入
                save(like);
            }else{
                //有记录，需要更新
                ul.setLiked(like.getLiked());
                save(ul);
            }
        }
    }

    @Override
    public void transLikedCountFromRedis() {
        List<LikedCountDto> list = redisService.getLikedCountFromRedis();
//        System.out.println("transLikedCountFromRedis+list"+list);
        for (LikedCountDto dto : list) {
//            System.out.println("dto"+dto);
            int id = Integer.parseInt(dto.getMember());
            CasesEntity casesEntity = casesRepository.getByCasesId(id);
            CasesDto cases = new CasesDto();
            BeanUtils.copyProperties(casesEntity,cases);
//            CasesDto cases = casesService.getById(id);
            //点赞数量属于无关紧要的操作，出错无需抛异常
            if (cases!=null){
                Integer likeNum = dto.getScore();
                cases.setLikes (String.valueOf(likeNum));
                //更新点赞数量
                casesService.saveCases(cases);
            }
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
