package com.xiao.cloud.rocketmqmessageaccount;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
@MapperScan("com.xiao.cloud.cloudcommon.message_tx.account.mapper")
public class RocketmqMessageAccountApplication {

    public static void main(String[] args) {
        SpringApplication.run(RocketmqMessageAccountApplication.class, args);
    }

}
