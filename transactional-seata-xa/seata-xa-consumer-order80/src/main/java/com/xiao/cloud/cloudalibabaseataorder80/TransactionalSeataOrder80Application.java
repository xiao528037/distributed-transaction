package com.xiao.cloud.cloudalibabaseataorder80;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class TransactionalSeataOrder80Application {

    public static void main(String[] args) {
        SpringApplication.run(TransactionalSeataOrder80Application.class, args);
    }

}
