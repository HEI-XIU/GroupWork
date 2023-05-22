package com.example.zuccknowledge.bean;

import com.example.zuccknowledge.service.KnowledgeService;
import lombok.extern.slf4j.Slf4j;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 的定时任务
 */
@Slf4j
public class HotTask extends QuartzJobBean {
    @Autowired
    KnowledgeService knowledgeService;

    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        log.info("HotTask-------- {}", sdf.format(new Date()));

        //将 Redis 里的点赞信息同步到数据库里
        knowledgeService.insert();
    }
}