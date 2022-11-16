package com.xiao.cloud.cloudcommon.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @projectName distributed-transaction
 * @author aloneMan
 * @createTime 2022-11-15 22:56:44
 * @description
 */

/**
 * 用户相关表
 */
@Data
@NoArgsConstructor
@ToString
public class TccAccount {
    private Long id;

    /**
     * 用户名称
     */
    private String username;

    private BigDecimal userOver;

    private BigDecimal frozenOver;

    private Date createTime;

}