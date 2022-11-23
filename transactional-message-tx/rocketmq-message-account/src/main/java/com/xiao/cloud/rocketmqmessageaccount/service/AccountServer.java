package com.xiao.cloud.rocketmqmessageaccount.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xiao.cloud.cloudcommon.message_tx.account.dto.AccountDTO;
import com.xiao.cloud.cloudcommon.message_tx.account.entity.MessageTxAccount;

/**
 * @author aloneMan
 * @projectName distributed-transaction
 * @createTime 2022-11-22 18:36:49
 * @description
 */
public interface AccountServer extends IService<MessageTxAccount> {
    /**
     * 扣减金额
     *
     * @param accountDTO
     * @param transactionalId
     */
    public void deductionAmount(AccountDTO accountDTO,String producerId, String transactionalId);
}
