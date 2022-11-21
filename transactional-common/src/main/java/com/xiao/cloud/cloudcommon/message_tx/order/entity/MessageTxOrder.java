package com.xiao.cloud.cloudcommon.message_tx.order.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author aloneMan
 * @projectName distributed-transaction
 * @createTime 2022-11-18 10:14:02
 * @description
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MessageTxOrder {
    private Long id;

    private Date createTime;

    private String number;

    private Integer status;

    private String productId;

    private BigDecimal totalAmount;

    private Integer count;

    private String userId;
}