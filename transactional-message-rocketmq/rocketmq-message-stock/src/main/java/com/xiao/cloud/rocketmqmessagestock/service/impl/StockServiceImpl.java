package com.xiao.cloud.rocketmqmessagestock.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xiao.cloud.cloudcommon.message_tx.inventory.entity.MessageTxInventory;
import com.xiao.cloud.cloudcommon.message_tx.inventory.mapper.InventoryMapper;
import com.xiao.cloud.rocketmqmessagestock.service.StockService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author aloneMan
 * @projectName distributed-transaction
 * @createTime 2022-11-21 17:03:10
 * @description
 */
@Service("stockServiceImpl")
@Slf4j(topic = "StockServiceImpl")
public class StockServiceImpl extends ServiceImpl<InventoryMapper,MessageTxInventory> implements StockService {

    public StockServiceImpl(){}
}
