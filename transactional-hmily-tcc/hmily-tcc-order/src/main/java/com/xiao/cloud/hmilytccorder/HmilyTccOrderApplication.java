package com.xiao.cloud.hmilytccorder;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author aloneman
 */
@EnableFeignClients
//@EnableDiscoveryClient
@EnableTransactionManagement
@MapperScan("com.xiao.cloud.cloudcommon.hmily_tcc.order.mapper")
@SpringBootApplication(exclude = {MongoAutoConfiguration.class, MongoDataAutoConfiguration.class})
public class HmilyTccOrderApplication {

    public static void main(String[] args) {
        SpringApplication.run(HmilyTccOrderApplication.class, args);
    }

}
