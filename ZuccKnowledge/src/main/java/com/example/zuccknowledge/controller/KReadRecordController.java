package com.example.zuccknowledge.controller;

import com.example.zuccknowledge.formbean.KReadRecordDto;
import com.example.zuccknowledge.formbean.KViewReadCount;
import com.example.zuccknowledge.result.ResponseData;
import com.example.zuccknowledge.result.ResponseMsg;
import com.example.zuccknowledge.service.KReadRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/krecord/v1")
public class KReadRecordController {
    @Autowired
    private KReadRecordService kReadRecordService;

    /**
     * 获取所有知识点阅读次数
     *
     * @return
     */
    @GetMapping("/count")
    public ResponseData getKReadCount() {
        List<KViewReadCount> kViewReadCountList = kReadRecordService.getKReadCount();
        return new ResponseData(ResponseMsg.SUCCESS, kViewReadCountList);
    }

    /**
     * 获取所有知识点阅读记录，返回limit条
     *
     * @param limit
     * @return
     */
    @GetMapping("/all/{limit}")
    public ResponseData getKRecordLimit(@PathVariable("limit") Integer limit) {
        List<KReadRecordDto> kReadRecordDtoList = kReadRecordService.getKRecordLimit(limit);
        return new ResponseData(ResponseMsg.SUCCESS, kReadRecordDtoList);
    }

    /**
     * 根据kId获取对应知识点阅读记录，返回limit条
     *
     * @param kId
     * @param limit
     * @return
     */
    @GetMapping("/bykid/{kId}/{limit}")
    public ResponseData getKRecordByKidLimit(@PathVariable("kId") Integer kId, @PathVariable("limit") Integer limit) {
        List<KReadRecordDto> kReadRecordDtoList = kReadRecordService.getKRecordByKidLimit(kId, limit);
        return new ResponseData(ResponseMsg.SUCCESS, kReadRecordDtoList);
    }

    /**
     * 根据reader获取对应读者阅读记录，返回limit条
     *
     * @param reader
     * @param limit
     * @return
     */
    @GetMapping("/byreader/{reader}/{limit}")
    public ResponseData getKRecordByReaderLimit(@PathVariable("reader") Integer reader, @PathVariable("limit") Integer limit) {
        List<KReadRecordDto> kReadRecordDtoList = kReadRecordService.getKRecordByReaderLimit(reader, limit);
        return new ResponseData(ResponseMsg.SUCCESS, kReadRecordDtoList);
    }

    /**
     * 根据kId和reader获取某读者阅读某知识点记录，返回limit条
     *
     * @param reader
     * @param kId
     * @param limit
     * @return
     */
    @GetMapping("/byreaderandkid/{reader}/{kId}/{limit}")
    public ResponseData getKRecordByKidAndReaderLimit(@PathVariable("reader") Integer reader, @PathVariable("kId") Integer kId, @PathVariable("limit") Integer limit) {
        List<KReadRecordDto> kReadRecordDtoList = kReadRecordService.getKRecordByKidAndReaderLimit(reader, kId, limit);
        return new ResponseData(ResponseMsg.SUCCESS, kReadRecordDtoList);
    }

    /**
     * 添加阅读记录
     *
     * @param kReadRecordDto
     */
    @PostMapping("/add")
    public ResponseData addKReadRecord(@RequestBody KReadRecordDto kReadRecordDto) {
        kReadRecordService.saveKReadRecord(kReadRecordDto);
        return new ResponseData(ResponseMsg.SUCCESS);
    }
}
