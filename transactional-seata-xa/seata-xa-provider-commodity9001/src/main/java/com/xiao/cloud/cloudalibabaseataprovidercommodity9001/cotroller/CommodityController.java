package com.xiao.cloud.cloudalibabaseataprovidercommodity9001.cotroller;

import com.xiao.cloud.cloudalibabaseataprovidercommodity9001.service.CommodityService;
import com.xiao.cloud.cloudcommon.common.CommonResult;
import com.xiao.cloud.cloudcommon.entity.CommodityDetails;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author aloneMan
 * @projectName spring-cloud-learn-02
 * @createTime 2022-10-30 15:16:18
 * @description
 */
@RestController
@RequestMapping("/commodity")
@Slf4j
public class CommodityController {

    @Resource
    private CommodityService commodityService;

    @GetMapping("/get/{id}")
    @GlobalTransactional(rollbackFor = Exception.class)
    @Transactional(readOnly = true)
    public CommonResult<CommodityDetails> get(@PathVariable("id") Long id) {
        CommodityDetails com = commodityService.getById(id);
        return new CommonResult<>(0x000001L, "请求成功", com);
    }
}
