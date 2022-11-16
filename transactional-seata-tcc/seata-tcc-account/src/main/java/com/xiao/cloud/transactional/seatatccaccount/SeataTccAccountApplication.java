package com.xiao.cloud.transactional.seatatccaccount;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author aloneman
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableTransactionManagement
public class SeataTccAccountApplication {

    public static void main(String[] args) {
        SpringApplication.run(SeataTccAccountApplication.class, args);
    }

}
