package com.example.zuccknowledge.service;

import com.example.zuccknowledge.formbean.KReadRecordDto;
import com.example.zuccknowledge.formbean.KViewReadCount;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface KReadRecordService {
    /**
     * 获取所有知识点阅读次数
     *
     * @return
     */
    List<KViewReadCount> getKReadCount();

    /**
     * 获取所有知识点阅读记录，返回limit条
     *
     * @param limit
     * @return
     */
    List<KReadRecordDto> getKRecordLimit(Integer limit);

    /**
     * 根据kId获取对应知识点阅读记录，返回limit条
     *
     * @param kId
     * @param limit
     * @return
     */
    List<KReadRecordDto> getKRecordByKidLimit(Integer kId, Integer limit);

    /**
     * 根据reader获取对应读者阅读记录，返回limit条
     *
     * @param reader
     * @param limit
     * @return
     */
    List<KReadRecordDto> getKRecordByReaderLimit(Integer reader, Integer limit);

    /**
     * 根据kId和reader获取某读者阅读某知识点记录，返回limit条
     *
     * @param reader
     * @param kId
     * @param limit
     * @return
     */
    List<KReadRecordDto> getKRecordByKidAndReaderLimit(Integer reader, Integer kId, Integer limit);

    /**
     * 添加阅读记录
     *
     * @param kReadRecordDto
     */
    void saveKReadRecord(KReadRecordDto kReadRecordDto);
}
