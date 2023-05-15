//package com.example.zuccknowledge.bean;
//
//import com.example.zuccknowledge.service.LikeCasesService;
//import lombok.extern.slf4j.Slf4j;
//import org.apache.commons.lang.time.DateUtils;
//import org.quartz.JobExecutionContext;
//import org.quartz.JobExecutionException;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.scheduling.quartz.QuartzJobBean;
//
//import java.text.SimpleDateFormat;
//import java.util.Date;
//
///**
// * 点赞的定时任务
// */
//@Slf4j
//public class HotTask extends QuartzJobBean {
//    @Autowired
//    LikeCasesService likedService;
//
//    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//
//    @Override
//    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
//        log.info("HotTask-------- {}", sdf.format(new Date()));
//
//        //将 Redis 里的点赞信息同步到数据库里
//        likedService.transLikedFromRedis();
//        likedService.transLikedCountFromRedis();
//    }
//}