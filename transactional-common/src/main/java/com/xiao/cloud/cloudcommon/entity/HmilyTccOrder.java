package com.xiao.cloud.cloudcommon.entity;

import java.util.Date;

import lombok.*;

/**
 * @projectName distributed-transaction
 * @author aloneMan
 * @createTime 2022-11-17 15:47:53
 * @description 
 */
@Data
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class HmilyTccOrder {
    private Long id;

    private Date createTime;

    private String number;

    private Byte status;

    private String productId;

    private Long totalAmount;

    private Integer count;

    private String userId;
}