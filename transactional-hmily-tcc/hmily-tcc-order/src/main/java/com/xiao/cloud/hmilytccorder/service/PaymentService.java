package com.xiao.cloud.hmilytccorder.service;

import com.xiao.cloud.cloudcommon.hmily_tcc.order.entity.HmilyTccOrder;
import com.xiao.cloud.cloudcommon.hmily_tcc.order.enums.OrderStatusEnum;
import org.dromara.hmily.annotation.HmilyTCC;

/**
 * @author aloneMan
 * @projectName distributed-transaction
 * @createTime 2022-11-18 15:01:15
 * @description
 */
public interface PaymentService {
    /**
     * 订单处理.
     *
     * @param hmilyTccOrder
     *         订单实体
     * @return 状态
     */

    Boolean makePayment(HmilyTccOrder hmilyTccOrder);

    /**
     * 订单处理-扣减库存异常
     *
     * @param hmilyTccOrder
     * @return 状态
     */

    Boolean makePaymentInventoryTryException(HmilyTccOrder hmilyTccOrder);

    /**
     * 订单处理-扣减用户余额异常
     *
     * @param hmilyTccOrder
     * @return 状态
     */

    Boolean makePaymentAccountTryException(HmilyTccOrder hmilyTccOrder);

    /**
     * 订单处理-扣减库存超时
     *
     * @param hmilyTccOrder
     * @return 状态
     */

    Boolean makePaymentInventoryTryTimeout(HmilyTccOrder hmilyTccOrder);
    
    /**
     * 订单处理-扣减用户余额超时
     *
     * @param hmilyTccOrder
     * @return 状态
     */

    Boolean makePaymentAccountTryTimeout(HmilyTccOrder hmilyTccOrder);

    /**
     * 订单处理-嵌套调用-正常
     * @param hmilyTccOrder
     * @return 状态
     */

    Boolean makePaymentNested(HmilyTccOrder hmilyTccOrder);

    /**
     * 订单处理-嵌套调用-异常
     * @param hmilyTccOrder
     * @return 状态
     */

    Boolean makePaymentNestedException(HmilyTccOrder hmilyTccOrder);
    
    /**
     * 订单成功提交
     *
     * @param hmilyTccOrder
     * @return boolean
     */
    Boolean confirmOrderStatus(HmilyTccOrder hmilyTccOrder);

    /**
     * 订单失败回滚
     *
     * @param hmilyTccOrder
     * @return boolean
     */
    Boolean cancelOrderStatus(HmilyTccOrder hmilyTccOrder);

    /**
     * 更新订单处理状态
     *
     * @param hmilyTccOrder
     *         订单信息
     * @param paying
     *         支付状态
     */
    void updateOrderStatus(HmilyTccOrder hmilyTccOrder, OrderStatusEnum paying);
}
