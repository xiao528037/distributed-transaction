package com.xiao.cloud.messagerecharge;

import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
@EnableDiscoveryClient
@MapperScan("com.xiao.cloud.cloudcommon.message.recharge.mapper")
public class MessageRechargeApplication {

    public static void main(String[] args) {
        SpringApplication.run(MessageRechargeApplication.class, args);
    }

}
