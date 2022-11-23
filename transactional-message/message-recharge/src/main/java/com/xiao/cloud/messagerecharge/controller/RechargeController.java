package com.xiao.cloud.messagerecharge.controller;

import com.xiao.cloud.cloudcommon.common.CommonResult;
import com.xiao.cloud.cloudcommon.message.recharge.dto.RechargeDto;
import com.xiao.cloud.cloudcommon.message.recharge.entity.TbTx;
import com.xiao.cloud.messagerecharge.service.RechargeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

/**
 * @author aloneMan
 * @projectName distributed-transaction
 * @createTime 2022-11-23 17:35:53
 * @description
 */
@RestController
@RequestMapping("/message/recharge")
@Slf4j(topic = "RechargeController")
public class RechargeController {

    private final RechargeService rechargeService;

    public RechargeController(RechargeService rechargeService) {
        this.rechargeService = rechargeService;
    }

    @PostMapping("/add")
    public void add(@RequestBody RechargeDto rechargeDto) {
        log.info(">>>>> {} ", rechargeDto);
        rechargeService.addIntegral(rechargeDto);
    }

    @GetMapping("/get")
    public CommonResult<TbTx> getTbTx(String txId) {
        TbTx txById = rechargeService.getTxById(txId);
        return new CommonResult<>(0x00001L, "查询事务信息成功", txById);
    }
}
