package com.xiao.cloud.cloudcommon.message.recharge.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @projectName distributed-transaction
 * @author aloneMan
 * @createTime 2022-11-23 17:01:41
 * @description 
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TbTx {
    private Long id;

    private String txid;

    private String username;

    private BigDecimal amount;

    private Date createTime;
}