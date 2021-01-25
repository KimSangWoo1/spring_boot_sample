package com.plantynet.tech2.config;

import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.plantynet.tech2.support.schedule.ScheduleJob;

@Configuration
@Profile("batch")
public class ScheduleConfig
{
    //https://brunch.co.kr/@springboot/53
    //https://github.com/spring-projects/spring-boot/blob/master/spring-boot-samples/spring-boot-sample-quartz/src/main/java/sample/quartz/ScheduleJob.java
    
    @Value("${custom.schedule}")
    private String schedule;
    
    @Bean
    public JobDetail scheduleJobDetail()
    {
        /*
        return JobBuilder
                .newJob(ScheduleJob.class).withIdentity("scheduleJob").usingJobData("name", "World").storeDurably()
                .build();
        */
        
        //ScheduleJob.java에서 Autowied가 안 먹히는 경우는 아래 처럼 서비스를 Map으로 전달해서 처리
        /*
        JobDataMap jobMap = new JobDataMap();
        
        jobMap.put("name", "world!!");
        jobMap.put("testService", testService);
        
        return JobBuilder
                .newJob(ScheduleJob.class).withIdentity("scheduleJob").usingJobData(new JobDataMap(map)).storeDurably()
                .build();
        */
        
        return JobBuilder
                .newJob(ScheduleJob.class).withIdentity("scheduleJob").storeDurably()
                .build();
    }
    
    @Bean
    public Trigger scheduleJobTrigger()
    {
        //simple(일정 시간마다...)
        /*SimpleScheduleBuilder scheduleBuilder = SimpleScheduleBuilder.simpleSchedule()
                .withIntervalInSeconds(2).repeatForever();
        
        return TriggerBuilder
                .newTrigger().forJob(scheduleJobDetail()).withIdentity("sampleTrigger").withSchedule(scheduleBuilder)
                .build();*/
        
        //cron
        CronScheduleBuilder cronBuilder = CronScheduleBuilder.cronSchedule(schedule);
        
        return TriggerBuilder
                .newTrigger().forJob(scheduleJobDetail()).withIdentity("scheduleTrigger").withSchedule(cronBuilder)
                .build();
    }

    /*@Bean
    public JobDetail dailyScheduleJobDetail()
    {
        return JobBuilder
                .newJob(DailyScheduleJob.class).withIdentity("dailyScheduleJob").storeDurably()
                .build();
    }

    @Bean
    public Trigger dailyScheduleJobTrigger()
    {
        //cron
        CronScheduleBuilder cronBuilder = CronScheduleBuilder.cronSchedule(adCheckDailySchedule);
        
        return TriggerBuilder
                .newTrigger().forJob(dailyScheduleJobDetail()).withIdentity("dailyScheduleTrigger").withSchedule(cronBuilder)
                .build();
    }*/
}
