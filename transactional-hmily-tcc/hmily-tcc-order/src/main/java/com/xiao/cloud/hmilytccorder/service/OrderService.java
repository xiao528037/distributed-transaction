package com.xiao.cloud.hmilytccorder.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xiao.cloud.cloudcommon.entity.HmilyTccOrder;
import org.dromara.hmily.annotation.HmilyTCC;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author aloneMan
 * @projectName distributed-transaction
 * @createTime 2022-11-18 10:15:16
 * @description
 */
public interface OrderService extends IService<HmilyTccOrder> {

    /**
     * 生成订单
     *
     * @param hmilyTccOrder
     * @return 订单信息
     */
    @HmilyTCC(confirmMethod = "commit", cancelMethod = "rollback")
    @Transactional(rollbackFor = Exception.class)
    HmilyTccOrder addOrder(HmilyTccOrder hmilyTccOrder);

    /**
     * 订单提交
     *
     * @param hmilyTccOrder
     * @return 订单信息
     */
    HmilyTccOrder commit(HmilyTccOrder hmilyTccOrder);

    /**
     * 生成订单失败回滚
     *
     * @param hmilyTccOrder
     * @return 订单信息
     */
    HmilyTccOrder rollback(HmilyTccOrder hmilyTccOrder);
}
