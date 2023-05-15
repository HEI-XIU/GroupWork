package com.example.zuccknowledge.utils;

import com.example.zuccknowledge.bean.HotTask;
import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HotConfig {
    private static final String HOT_TASK_IDENTITY = "HotTaskQuartz";

    @Bean
    public JobDetail hotDetail() {
        return JobBuilder.newJob(HotTask.class).withIdentity(HOT_TASK_IDENTITY).storeDurably().build();
    }

    @Bean
    public Trigger hotTrigger() {
        SimpleScheduleBuilder scheduleBuilder = SimpleScheduleBuilder.simpleSchedule()
                .withIntervalInSeconds(10000)  //设置时间周期单位秒
//                .withIntervalInHours(2)  //两个小时执行一次
                .repeatForever();
        return TriggerBuilder.newTrigger().forJob(hotDetail())
                .withIdentity(HOT_TASK_IDENTITY)
                .withSchedule(scheduleBuilder)
                .build();
    }
}
