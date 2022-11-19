package com.xiao.cloud.hmilytccorder.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xiao.cloud.cloudcommon.common.CommonResult;
import com.xiao.cloud.cloudcommon.hmily_tcc.account.dto.AccountDTO;
import com.xiao.cloud.cloudcommon.hmily_tcc.account.dto.AccountNestedDTO;
import com.xiao.cloud.cloudcommon.hmily_tcc.account.entity.HmilyTccAccount;
import com.xiao.cloud.cloudcommon.hmily_tcc.inventory.dto.InventoryDTO;
import com.xiao.cloud.cloudcommon.hmily_tcc.inventory.entity.HmilyTccInventory;
import com.xiao.cloud.cloudcommon.hmily_tcc.order.entity.HmilyTccOrder;
import com.xiao.cloud.cloudcommon.hmily_tcc.order.enums.OrderStatusEnum;
import com.xiao.cloud.cloudcommon.hmily_tcc.order.mapper.OrderMapper;
import com.xiao.cloud.hmilytccorder.openapi.AccountApi;
import com.xiao.cloud.hmilytccorder.openapi.InventoryApi;
import com.xiao.cloud.hmilytccorder.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.dromara.hmily.annotation.HmilyTCC;
import org.dromara.hmily.core.context.HmilyContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author aloneMan
 * @projectName distributed-transaction
 * @createTime 2022-11-18 15:03:32
 * @description
 */
@SuppressWarnings("all")
@Service("paymentService")
@Slf4j(topic = "PaymentServiceImpl")
public class PaymentServiceImpl implements PaymentService {

    private final OrderMapper orderMapper;

    private final AccountApi accountApi;

    private final InventoryApi inventoryApi;

    public PaymentServiceImpl(OrderMapper orderMapper, AccountApi accountApi, InventoryApi inventoryApi) {
        this.orderMapper = orderMapper;
        this.accountApi = accountApi;
        this.inventoryApi = inventoryApi;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    @HmilyTCC(confirmMethod = "confirmOrderStatus", cancelMethod = "cancelOrderStatus")
    public Boolean makePayment(HmilyTccOrder hmilyTccOrder) {
        log.info("调用 Order支付方法 事务ID为 >>> {} ", HmilyContextHolder.get().getTransId());
        //更新订单状态
        updateOrderStatus(hmilyTccOrder, OrderStatusEnum.PAYING);
        //扣减金额-正常
        CommonResult<HmilyTccAccount> paymentResult = accountApi.payment(buildAccountDTO(hmilyTccOrder));
        //扣减库存-正常
        CommonResult<HmilyTccInventory> inventoryResult = inventoryApi.decrease(buildInventoryDTO(hmilyTccOrder));
        return Boolean.TRUE;
    }

    @Override
    @HmilyTCC(confirmMethod = "confirmOrderStatus", cancelMethod = "cancelOrderStatus")
    public Boolean makePaymentInventoryTryException(HmilyTccOrder hmilyTccOrder) {
        //更新订单状态
        updateOrderStatus(hmilyTccOrder, OrderStatusEnum.PAYING);
        //扣除用户金额-正常
        accountApi.payment(buildAccountDTO(hmilyTccOrder));
        //扣减库存数量-异常
        inventoryApi.decreaseException(buildInventoryDTO(hmilyTccOrder));
        return Boolean.TRUE;
    }

    @Override
    @HmilyTCC(confirmMethod = "confirmOrderStatus", cancelMethod = "cancelOrderStatus")
    public Boolean makePaymentAccountTryException(HmilyTccOrder hmilyTccOrder) {
        //更新订单状态
        updateOrderStatus(hmilyTccOrder, OrderStatusEnum.PAYING);
        //扣除用户金额-正常
        accountApi.paymentException(buildAccountDTO(hmilyTccOrder));
        //扣减库存数量-异常
        inventoryApi.decrease(buildInventoryDTO(hmilyTccOrder));
        return Boolean.TRUE;
    }

    @Override
    @HmilyTCC(confirmMethod = "confirmOrderStatus", cancelMethod = "cancelOrderStatus")
    public Boolean makePaymentInventoryTryTimeout(HmilyTccOrder hmilyTccOrder) {
        //更新订单状态
        updateOrderStatus(hmilyTccOrder, OrderStatusEnum.PAYING);
        //扣除用户金额-正常
        accountApi.payment(buildAccountDTO(hmilyTccOrder));
        //扣减库存数量-超时
        inventoryApi.decreaseTimeout(buildInventoryDTO(hmilyTccOrder));
        return Boolean.TRUE;
    }

    @Override
    @HmilyTCC(confirmMethod = "confirmOrderStatus", cancelMethod = "cancelOrderStatus")
    public Boolean makePaymentAccountTryTimeout(HmilyTccOrder hmilyTccOrder) {
        //更新订单状态
        updateOrderStatus(hmilyTccOrder, OrderStatusEnum.PAYING);
        //扣除用户金额-超时
        accountApi.paymentTimeout(buildAccountDTO(hmilyTccOrder));
        //扣减库存数量-正常
        inventoryApi.decreaseException(buildInventoryDTO(hmilyTccOrder));
        return Boolean.TRUE;
    }

    @Override
    @HmilyTCC(confirmMethod = "confirmOrderStatus", cancelMethod = "cancelOrderStatus")
    public Boolean makePaymentNested(HmilyTccOrder hmilyTccOrder) {
        //更新订单状态
        updateOrderStatus(hmilyTccOrder, OrderStatusEnum.PAYING);
        //判断用户余额是否充足
        CommonResult<HmilyTccAccount> accountResult = accountApi.getAccount(hmilyTccOrder.getUserId());
        HmilyTccAccount account = accountResult.getData();
        if (account.getBalance().compareTo(hmilyTccOrder.getTotalAmount()) <= 0) {
            throw new RuntimeException(account.getUserId() + ">> 用户,扣减" + hmilyTccOrder.getTotalAmount().doubleValue() + ",余额不足");
        }
        //嵌套调用扣除用户金额-正常 扣减余额接口--->扣减库存接口
        accountApi.paymentWithNested(buildAccountNestedDTO(hmilyTccOrder));
        return Boolean.TRUE;
    }

    @Override
    @HmilyTCC(confirmMethod = "confirmOrderStatus", cancelMethod = "cancelOrderStatus")
    public Boolean makePaymentNestedException(HmilyTccOrder hmilyTccOrder) {
        //更新订单状态
        updateOrderStatus(hmilyTccOrder, OrderStatusEnum.PAYING);
        //判断用户余额是否充足
        CommonResult<HmilyTccAccount> accountResult = accountApi.getAccount(hmilyTccOrder.getUserId());
        HmilyTccAccount account = accountResult.getData();
        if (account.getBalance().compareTo(hmilyTccOrder.getTotalAmount()) <= 0) {
            throw new RuntimeException(account.getUserId() + ">> 用户,扣减" + hmilyTccOrder.getTotalAmount().doubleValue() + ",余额不足");
        }
        //嵌套调用扣除用户金额-异常 扣减余额接口--->扣减库存接口
        accountApi.paymentNestedException(buildAccountNestedDTO(hmilyTccOrder));
        return Boolean.TRUE;
    }


    @Override
    public Boolean confirmOrderStatus(HmilyTccOrder hmilyTccOrder) {
        updateOrderStatus(hmilyTccOrder, OrderStatusEnum.PAY_SUCCESS);
        return Boolean.TRUE;
    }

    @Override
    public Boolean cancelOrderStatus(HmilyTccOrder hmilyTccOrder) {
        updateOrderStatus(hmilyTccOrder, OrderStatusEnum.PAY_FAIL);
        return Boolean.TRUE;
    }

    @Override
    public void updateOrderStatus(HmilyTccOrder hmilyTccOrder, OrderStatusEnum paying) {
        hmilyTccOrder.setStatus(paying.getCode());
        orderMapper.update(hmilyTccOrder);
    }

    private AccountDTO buildAccountDTO(HmilyTccOrder order) {
        AccountDTO accountDTO = new AccountDTO();
        accountDTO.setAmount(order.getTotalAmount());
        accountDTO.setUserId(order.getUserId());
        return accountDTO;
    }

    private InventoryDTO buildInventoryDTO(HmilyTccOrder order) {
        InventoryDTO inventoryDTO = new InventoryDTO();
        inventoryDTO.setCount(order.getCount());
        inventoryDTO.setProductId(order.getProductId());
        return inventoryDTO;
    }

    private AccountNestedDTO buildAccountNestedDTO(HmilyTccOrder order) {
        AccountNestedDTO nestedDTO = new AccountNestedDTO();
        nestedDTO.setAmount(order.getTotalAmount());
        nestedDTO.setUserId(order.getUserId());
        nestedDTO.setProductId(order.getProductId());
        nestedDTO.setCount(order.getCount());
        return nestedDTO;
    }
}
