package com.xiao.cloud.hmilytccaccount.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xiao.cloud.cloudcommon.hmily_tcc.account.dto.AccountDTO;
import com.xiao.cloud.cloudcommon.hmily_tcc.account.dto.AccountNestedDTO;
import com.xiao.cloud.cloudcommon.hmily_tcc.account.entity.HmilyTccAccount;
import org.dromara.hmily.annotation.HmilyTCC;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

/**
 * @author aloneMan
 * @projectName distributed-transaction
 * @createTime 2022-11-17 15:50:31
 * @description
 */
public interface AccountService extends IService<HmilyTccAccount> {

    /**
     * 余额扣减
     *
     * @param accountDTO
     * @return 返回支付信息
     */
    HmilyTccAccount payment(AccountDTO accountDTO);

    /**
     * 支付异常
     *
     * @param accountDTO
     * @return 返回支付信息
     */
    HmilyTccAccount paymentWithTryException(AccountDTO accountDTO);

    /**
     * 支付超时
     *
     * @param accountDTO
     * @return 返回支付信息
     */
    HmilyTccAccount paymentWithTryTimeOut(AccountDTO accountDTO);

    /**
     * 支付嵌套调用库存扣减接口
     *
     * @param accountNestedDTO
     * @return 返回支付信息
     */
    HmilyTccAccount paymentWithNested(AccountNestedDTO accountNestedDTO);

    /**
     * 支付嵌套调用库存扣减接口异常
     *
     * @param accountNestedDTO
     * @return 返回支付信息
     */
    HmilyTccAccount paymentWithNestedException(AccountNestedDTO accountNestedDTO);

/*    *//**
     * 余额扣减提交
     *
     * @param accountDTO
     * @return 返回支付信息
     *//*
    HmilyTccAccount commit(AccountDTO accountDTO);

    *//**
     * 余额扣减回滚
     *
     * @param accountDTO
     * @return 返回支付信息
     *//*
    HmilyTccAccount rollback(AccountDTO accountDTO);*/

    /**
     * 嵌套调用提交
     *
     * @param accountNestedDTO
     * @return 支付信息
     */
    @Transactional(rollbackFor = Exception.class)
    HmilyTccAccount commitNested(AccountNestedDTO accountNestedDTO);

    /**
     * 嵌套调用回滚
     *
     * @param accountNestedDTO
     * @return 支付信息
     */
    @Transactional(rollbackFor = Exception.class)
    HmilyTccAccount rollbackNested(AccountNestedDTO accountNestedDTO);

}
