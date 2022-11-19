package com.xiao.cloud.cloudcommon.hmily_tcc.account.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author aloneMan
 * @projectName distributed-transaction
 * @createTime 2022-11-18 11:51:58
 * @description
 */
@Data
public class HmilyTccAccount implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long id;

    private String userId;

    /**
     * 用户余额
     */
    private BigDecimal balance;

    /**
     * 冻结金额，扣款暂存余额
     */
    private BigDecimal freezeAmount;

    private Date createTime;

    private Date updateTime;
}