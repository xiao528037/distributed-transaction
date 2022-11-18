package com.xiao.cloud.transactional.seatatccorder.config;


import feign.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author aloneMan
 * @projectName distributed-transaction
 * @createTime 2022-11-16 15:51:02
 * @description
 */
@Configuration
public class FeignConfig {
    @Bean
    public Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }
}
