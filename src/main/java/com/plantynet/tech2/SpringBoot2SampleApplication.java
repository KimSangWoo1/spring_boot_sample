package com.plantynet.tech2;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@MapperScan( basePackages = "com.plantynet.tech2.dao" )
@EnableTransactionManagement //트랜잭션 매니저 사용
@EnableAsync //Async 사용
@EnableAspectJAutoProxy //<aop:aspectj-autoproxy/>
public class SpringBoot2SampleApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBoot2SampleApplication.class, args);
	}

}
