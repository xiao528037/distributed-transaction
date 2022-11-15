package com.xiao.cloud.cloudalibabaseataprovidercommodity9001.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xiao.cloud.cloudalibabaseataprovidercommodity9001.mapper.CommodityMapper;
import com.xiao.cloud.cloudalibabaseataprovidercommodity9001.service.CommodityService;
import com.xiao.cloud.cloudcommon.entity.CommodityDetails;
import org.springframework.stereotype.Service;

/**
 * @author aloneMan
 * @projectName spring-cloud-learn-02
 * @createTime 2022-10-30 15:11:31
 * @description
 */

@Service("commodityService")
public class CommodityServiceImpl extends ServiceImpl<CommodityMapper, CommodityDetails> implements CommodityService {
}
