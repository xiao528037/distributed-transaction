package com.xiao.cloud.messageaccount;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author aloneMan
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@MapperScan("com.xiao.cloud.cloudcommon.message.account.mapper")
public class MessageAccountApplication {

    public static void main(String[] args) {
        SpringApplication.run(MessageAccountApplication.class, args);
    }

}
