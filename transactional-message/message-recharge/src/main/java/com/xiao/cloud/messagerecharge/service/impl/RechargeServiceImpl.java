package com.xiao.cloud.messagerecharge.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xiao.cloud.cloudcommon.message.account.dto.AccountDto;
import com.xiao.cloud.cloudcommon.message.recharge.dto.RechargeDto;
import com.xiao.cloud.cloudcommon.message.recharge.entity.TbRecharge;
import com.xiao.cloud.cloudcommon.message.recharge.entity.TbTx;
import com.xiao.cloud.cloudcommon.message.recharge.mapper.RechargeMapper;
import com.xiao.cloud.messagerecharge.service.RechargeService;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

/**
 * @author aloneMan
 * @projectName distributed-transaction
 * @createTime 2022-11-23 17:48:41
 * @description
 */
@Slf4j(topic = "RechargeServiceImpl")
@Service("RechargeServiceImpl")
public class RechargeServiceImpl extends ServiceImpl<RechargeMapper, TbRecharge> implements RechargeService {

    private final RechargeMapper rechargeMapper;

    private final RocketMQTemplate rocketMQTemplate;

    public RechargeServiceImpl(RechargeMapper rechargeMapper, RocketMQTemplate rocketMQTemplate) {
        this.rechargeMapper = rechargeMapper;
        this.rocketMQTemplate = rocketMQTemplate;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void addIntegral(RechargeDto rechargeDto) {
        int i = rechargeMapper.addIntegral(rechargeDto);
        if (i > 0) {
            rechargeDto.setIsSuccess(true);
        } else {
            rechargeDto.setIsSuccess(false);
        }
        rechargeMapper.addTransactional(rechargeDto);
        AccountDto accountDto = buildAccountDto(rechargeDto);
        Message<AccountDto> message = MessageBuilder.withPayload(accountDto).build();
        rocketMQTemplate.send("recharge_topic", message);
    }

    @Override
    @Transactional(readOnly = true)
    public TbTx getTxById(String txId) {
        TbTx tx = rechargeMapper.getTx(txId);
        return tx;
    }

    public AccountDto buildAccountDto(RechargeDto rechargeDto) {
        AccountDto accountDto = new AccountDto();
        accountDto.setAmount(rechargeDto.getIntegral().divide(new BigDecimal(10)));
        accountDto.setUsername(rechargeDto.getUsername());
        accountDto.setTransactionalId(rechargeDto.getTransactionalId());
        return accountDto;
    }
}
