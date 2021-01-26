package com.plantynet.tech2.support.schedule;

import org.quartz.DisallowConcurrentExecution;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobKey;
import org.quartz.PersistJobDataAfterExecution;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.plantynet.tech2.service.TestService;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@PersistJobDataAfterExecution /*첫 실행시 전달한 값을 변경해서 계속 변경해서 사용이 가능하게... 이게 없으면 항상 첫 실행시 전달한 값으로 바뀜 */
@DisallowConcurrentExecution /*중복 실행 방지*/
public class ScheduleJob extends QuartzJobBean
{    
	@Autowired
	private Environment env;
    //@Autowired -> 생성자 주입
    private TestService testService;
    
    //@Autowired -> spring 4.3+ 생성자 하나인 경우 자동으로 설정됨
    public ScheduleJob(TestService testService)
    {
        this.testService = testService;
    }

    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException 
    {
        
    	JobKey jobKey = context.getJobDetail().getKey();
    	System.out.println("###quartz start###");
        
        String[] profiles = env.getActiveProfiles();
        
        if(profiles.length==0) {
        	log.error("No Profile ... Setting Default Profiles!");
        	 profiles= env.getDefaultProfiles();
        }
        for(int i=0; i<profiles.length;i++) {
        	log.info("Active Profile Name : {}",profiles[i]);
        }
        System.out.println("JobKey : "+jobKey);
        System.out.println("Job Detail : "+ context.getJobDetail().toString());
        testService.asyncTest();
        
    	System.out.println("###quartz stop###");
    }
}
