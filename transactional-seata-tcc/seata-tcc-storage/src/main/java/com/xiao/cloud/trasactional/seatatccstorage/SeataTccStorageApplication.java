package com.xiao.cloud.trasactional.seatatccstorage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author aloneman
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class SeataTccStorageApplication {

    public static void main(String[] args) {
        SpringApplication.run(SeataTccStorageApplication.class, args);
    }

}
