package com.xiao.cloud.hmilytccaccount;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.autoconfigure.metrics.mongo.MongoMetricsAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author aloneman
 */
@EnableFeignClients
@EnableDiscoveryClient
@EnableTransactionManagement
@MapperScan("com.xiao.cloud.cloudcommon.hmily_tcc.account.mapper")
@SpringBootApplication(exclude = {MongoAutoConfiguration.class, MongoMetricsAutoConfiguration.class, MongoDataAutoConfiguration.class})
public class HmilyTccAccountApplication {

    public static void main(String[] args) {
        SpringApplication.run(HmilyTccAccountApplication.class, args);
    }

}
