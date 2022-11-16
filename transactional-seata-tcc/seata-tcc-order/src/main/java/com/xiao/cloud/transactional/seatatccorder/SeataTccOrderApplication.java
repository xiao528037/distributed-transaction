package com.xiao.cloud.transactional.seatatccorder;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@ComponentScan(basePackages = {"com.xiao.cloud.cloudcommon","com.xiao.cloud.transactional.seatatccorder"})
public class SeataTccOrderApplication {

    public static void main(String[] args) {
        SpringApplication.run(SeataTccOrderApplication.class, args);
    }

}
