package com.xiao.cloud.trasactional.seatatccstorage;

import com.alibaba.cloud.nacos.NacosDiscoveryProperties;
import com.alibaba.cloud.nacos.NacosServiceManager;
import com.alibaba.nacos.api.exception.NacosException;
import com.alibaba.nacos.api.naming.NamingMaintainService;
import com.alibaba.nacos.api.naming.NamingService;
import com.alibaba.nacos.api.naming.pojo.Instance;
import com.alibaba.nacos.api.naming.pojo.Service;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;

import java.util.List;


@SpringBootTest
@Slf4j
class SeataTccStorageApplicationTests {

    @Autowired
    private LoadBalancerClient loadBalancerClient;

    @Test
    void t1() {
        ServiceInstance choose = loadBalancerClient.choose("tcc-storage-service");
        log.info("{} ", choose.getServiceId());
        log.info("{} ", choose.getPort());
    }

    @Autowired
    private NacosServiceManager nacosServiceManager;

    @Autowired
    private NacosDiscoveryProperties nacosDiscoveryProperties;

    @Test
    void t2() throws NacosException {
        NamingService namingService = nacosServiceManager.getNamingService();
        List<Instance> allInstances = namingService.getAllInstances("tcc-storage-service", "tcc-storage-group");
        for (Instance instance : allInstances) {
            log.info("ip 地址 >>> {} port 端口 >>> {} ", instance.getIp(), instance.getPort());
        }
    }
}
