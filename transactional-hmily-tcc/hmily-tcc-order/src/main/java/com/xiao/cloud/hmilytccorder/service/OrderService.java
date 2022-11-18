package com.xiao.cloud.hmilytccorder.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xiao.cloud.cloudcommon.hmily_tcc.account.entity.HmilyTccAccount;
import com.xiao.cloud.cloudcommon.hmily_tcc.inventory.entity.HmilyTccInventory;
import com.xiao.cloud.cloudcommon.hmily_tcc.order.entity.HmilyTccOrder;
import org.dromara.hmily.annotation.HmilyTCC;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

/**
 * @author aloneMan
 * @projectName distributed-transaction
 * @createTime 2022-11-18 10:15:16
 * @description
 */
public interface OrderService extends IService<HmilyTccOrder> {

    /**
     * 订单支付-正常
     *
     * @param count
     *         数量
     * @param amount
     *         金额
     * @return 订单信息
     */
    HmilyTccOrder orderPay(Integer count, BigDecimal amount);

    /**
     * 订单支付-扣减库存try阶段异常
     *
     * @param count
     *         数量
     * @param amount
     *         金额
     * @return 订单信息
     */
    HmilyTccOrder inventoryTryException(Integer count, BigDecimal amount);

    /**
     * 订单支付-扣减账户余额try阶段异常
     *
     * @param count
     *         数量
     * @param amount
     *         金额
     * @return 订单信息
     */
    HmilyTccOrder accountTryException(Integer count, BigDecimal amount);

    /**
     * 订单支付-扣减库存try阶段超时
     *
     * @param count
     *         数量
     * @param amount
     *         金额
     * @return 订单信息
     */
    HmilyTccOrder inventoryTryTimeout(Integer count, BigDecimal amount);

    /**
     * 订单支付-扣减账户金额try阶段超时
     *
     * @param count
     *         数量
     * @param amount
     *         数量
     * @return 订单信息
     */
    HmilyTccOrder accountTryTimeout(Integer count, BigDecimal amount);

    /**
     * 订单支付-嵌套调用-正常
     *
     * @param count
     *         数量
     * @param amount
     *         数量
     * @return 订单信息
     */
    HmilyTccOrder orderPayNested(Integer count, BigDecimal amount);

    /**
     * 订单支付-嵌套调用-异常
     *
     * @param count
     *         数量
     * @param amount
     *         数量
     * @return 订单信息
     */
    HmilyTccOrder orderPayNestedException(Integer count, BigDecimal amount);

    /**
     * 查询用户信息
     *
     * @param userId
     *         用户ID
     * @return 用户信息
     */
    HmilyTccAccount getAccountById(String userId);

    /**
     * 查询库存信息
     *
     * @param productId
     *         产品ID
     * @return 库存信息
     */
    HmilyTccInventory getInventoryById(String productId);
}
