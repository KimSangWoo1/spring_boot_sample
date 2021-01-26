package com.plantynet.tech2.config;

import java.nio.charset.Charset;
import java.time.Duration;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

@Configuration
public class AppConfig
{
    //SpringBoot 1.4+ 부터는 RestTemplate 대신 RestTemplateBuilder만 지원
    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) 
    {
        return builder        		
                .setConnectTimeout(Duration.ofMillis(3000))
                .setReadTimeout(Duration.ofMillis(6000))
                .additionalMessageConverters(new StringHttpMessageConverter(Charset.forName("UTF-8")))
                .build();
    }
    
    /*@Bean
    public RestTemplate restTemplate() 
    {
        OkHttp3ClientHttpRequestFactory httpFactory = new OkHttp3ClientHttpRequestFactory();
        httpFactory.setConnectTimeout(1000);
        httpFactory.setWriteTimeout(2000);
        httpFactory.setReadTimeout(2000);
        
        return new RestTemplate(httpFactory);
    }*/
}
