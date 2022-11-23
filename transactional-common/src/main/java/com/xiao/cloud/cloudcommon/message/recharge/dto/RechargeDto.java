package com.xiao.cloud.cloudcommon.message.recharge.dto;

import lombok.*;

import java.math.BigDecimal;

/**
 * @author aloneMan
 * @projectName distributed-transaction
 * @createTime 2022-11-23 17:20:41
 * @description
 */

@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class RechargeDto {
    /**
     * 用户名
     */
    private String username;
    /**
     * 积分
     */
    private BigDecimal integral;

    private Boolean isSuccess;

    private String transactionalId;
}
