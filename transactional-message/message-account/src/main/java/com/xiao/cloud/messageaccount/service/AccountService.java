package com.xiao.cloud.messageaccount.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xiao.cloud.cloudcommon.message.account.dto.AccountDto;
import com.xiao.cloud.cloudcommon.message.account.entity.TbAccount;
import com.xiao.cloud.cloudcommon.message.recharge.entity.TbTx;

import java.math.BigDecimal;

/**
 * @author aloneMan
 * @projectName distributed-transaction
 * @createTime 2022-11-23 17:16:13
 * @description
 */
public interface AccountService extends IService<TbAccount> {

    /**
     * 调用充值服务
     * @param username 用户名
     * @param amount 金额
     */
    public void recharge(String username, BigDecimal amount);

    /**
     * 扣减账户余额
     * @param accountDto
     */
    public void deductionBalance(AccountDto accountDto);

    /**
     * 获取事务信息
     * @param transactionalId 事务ID
     * @return 事务信息
     */
    TbTx getTx(String transactionalId);
}
