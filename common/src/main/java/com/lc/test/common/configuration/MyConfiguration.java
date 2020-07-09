package com.lc.test.common.configuration;

import com.lorne.tx.springcloud.feign.TransactionRestTemplateInterceptor;
import feign.RequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyConfiguration {

    @Bean
    public RequestInterceptor requestInterceptor() {
        return new TransactionRestTemplateInterceptor();
    }
}
