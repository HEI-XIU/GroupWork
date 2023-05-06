package com.example.zuccknowledge.service.impl;

import com.example.zuccknowledge.entity.KReadRecordEntity;
import com.example.zuccknowledge.exception.EchoServiceException;
import com.example.zuccknowledge.formbean.KReadRecordDto;
import com.example.zuccknowledge.formbean.KViewReadCount;
import com.example.zuccknowledge.repository.KReadRecordRepository;
import com.example.zuccknowledge.service.KReadRecordService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class KreadRecordImpl implements KReadRecordService {
    @Autowired
    private KReadRecordRepository kReadRecordRepository;

    /**
     * 获取所有知识点阅读次数
     *
     * @return
     */
    @Override
    public List<KViewReadCount> getKReadCount() {
        return kReadRecordRepository.getKReadCount();
    }

    /**
     * 获取所有知识点阅读记录，返回limit条
     *
     * @param limit
     * @return
     */
    @Override
    public List<KReadRecordDto> getKRecordLimit(Integer limit) {
        return convert(kReadRecordRepository.getKRecordLimit(limit));
    }

    /**
     * 根据kId获取对应知识点阅读记录，返回limit条
     *
     * @param kId
     * @param limit
     * @return
     */
    @Override
    public List<KReadRecordDto> getKRecordByKidLimit(Integer kId, Integer limit) {
        return convert(kReadRecordRepository.getKRecordByKidLimit(kId, limit));
    }

    /**
     * 根据reader获取对应读者阅读记录，返回limit条
     *
     * @param reader
     * @param limit
     * @return
     */
    @Override
    public List<KReadRecordDto> getKRecordByReaderLimit(Integer reader, Integer limit) {
        return convert(kReadRecordRepository.getKRecordByReaderLimit(reader, limit));
    }

    /**
     * 根据kId和reader获取某读者阅读某知识点记录，返回limit条
     *
     * @param reader
     * @param kId
     * @param limit
     * @return
     */
    @Override
    public List<KReadRecordDto> getKRecordByKidAndReaderLimit(Integer reader, Integer kId, Integer limit) {
        return convert(kReadRecordRepository.getKRecordByKidAndReaderLimit(reader, kId, limit));
    }

    /**
     * 添加阅读记录
     *
     * @param kReadRecordDto
     */
    @Override
    public void saveKReadRecord(KReadRecordDto kReadRecordDto) {
        try {
            KReadRecordEntity kReadRecordEntity = new KReadRecordEntity();
            BeanUtils.copyProperties(kReadRecordDto, kReadRecordEntity);
            kReadRecordRepository.save(kReadRecordEntity);
        } catch (Exception e) {
            e.printStackTrace();
            throw new EchoServiceException("添加失败");
        }
    }

    private List<KReadRecordDto> convert(List<KReadRecordEntity> entityList) {
        List<KReadRecordDto> kReadRecordDtoList = new ArrayList<>();
        entityList.stream().forEach(item -> {
            KReadRecordDto kReadRecordDto = new KReadRecordDto();
            BeanUtils.copyProperties(item, kReadRecordDto);
            kReadRecordDtoList.add(kReadRecordDto);
        });

        return kReadRecordDtoList;
    }
}
