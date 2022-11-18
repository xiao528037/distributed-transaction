package com.xiao.cloud.hmilytccorder.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xiao.cloud.cloudcommon.common.CommonResult;
import com.xiao.cloud.cloudcommon.entity.HmilyTccAccount;
import com.xiao.cloud.cloudcommon.entity.HmilyTccInventory;
import com.xiao.cloud.cloudcommon.entity.HmilyTccOrder;
import org.dromara.hmily.annotation.HmilyTCC;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

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
    @Transactional(rollbackFor = Exception.class)
    @HmilyTCC(confirmMethod = "commit", cancelMethod = "rollback")
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

    /**
     * 查询用户信息
     * @param userId 用户ID
     * @return 用户信息
     */
    public HmilyTccAccount getAccountById( String userId);

    /**
     * 查询库存信息
     * @param productId 产品ID
     * @return 库存信息
     */
    public HmilyTccInventory getInventoryById(String productId);
}
