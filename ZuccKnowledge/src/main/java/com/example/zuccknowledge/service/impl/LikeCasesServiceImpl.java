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

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
