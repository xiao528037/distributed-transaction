package com.xiao.cloud.cloudcommon.message.recharge.entity;

import java.math.BigDecimal;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @projectName distributed-transaction
 * @author aloneMan
 * @createTime 2022-11-23 17:20:01
 * @description 
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TbRecharge {
    private Long id;

    /**
    * 用户名
    */
    private String username;

    /**
    * 积分
    */
    private BigDecimal integral;

    private Date createTime;
}