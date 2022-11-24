package com.xiao.cloud.messageaccount.api;

import com.xiao.cloud.cloudcommon.common.CommonResult;
import com.xiao.cloud.cloudcommon.message.recharge.dto.RechargeDto;
import com.xiao.cloud.cloudcommon.message.recharge.entity.TbRecharge;
import com.xiao.cloud.cloudcommon.message.recharge.entity.TbTx;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author aloneMan
 * @projectName distributed-transaction
 * @createTime 2022-11-23 17:19:07
 * @description
 */
@FeignClient(value = "message-recharge-service", path = "/message/recharge")
public interface RechargeApi {

    /**
     * 充值
     *
     * @param rechargeDto
     *         充值信息
     * @return 是否
     */
    @PostMapping("/add")
    void add(RechargeDto rechargeDto);

    /**
     * 获取事务信息
     *
     * @param txId
     *         事务ID
     * @return 事务信息
     */
    @GetMapping("/get")
    CommonResult<TbTx> getTbTx(@RequestParam("txId") String txId);
}
