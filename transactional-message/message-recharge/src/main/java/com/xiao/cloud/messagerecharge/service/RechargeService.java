package com.xiao.cloud.messagerecharge.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xiao.cloud.cloudcommon.message.recharge.dto.RechargeDto;
import com.xiao.cloud.cloudcommon.message.recharge.entity.TbRecharge;
import com.xiao.cloud.cloudcommon.message.recharge.entity.TbTx;

/**
 * @author aloneMan
 * @projectName distributed-transaction
 * @createTime 2022-11-23 17:48:30
 * @description
 */
public interface RechargeService extends IService<TbRecharge> {

    /**
     * 增加积分
     *
     * @param rechargeDto
     */
    void addIntegral(RechargeDto rechargeDto);

    /**
     * 查询事务信息
     * @param txId 事务ID
     * @return 事务信息
     */
    TbTx getTxById(String txId);
}
