package com.xiao.cloud.hmilytccorder;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author aloneman
 */
@EnableFeignClients
@SpringBootApplication
@EnableDiscoveryClient
@EnableTransactionManagement
public class HmilyTccOrderApplication {

    public static void main(String[] args) {
        SpringApplication.run(HmilyTccOrderApplication.class, args);
    }

}
