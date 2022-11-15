package com.xiao.cloud.cloudalibabaseataprovicerstock8001;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;



@SpringBootApplication
@EnableDiscoveryClient
@EnableTransactionManagement
public class SeataProviderStock8001Application {

    public static void main(String[] args) {
        SpringApplication.run(SeataProviderStock8001Application.class, args);
    }

}
