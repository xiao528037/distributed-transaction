package com.xiao.cloud.cloudcommon.entity;

import lombok.*;

import java.io.Serializable;
import java.util.Date;

/**
 * @author aloneMan
 * @projectName distributed-transaction
 * @createTime 2022-11-17 15:45:00
 * @description
 */
@Data
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class HmilyTccAccount implements Serializable {

    private static final long serialVersionUID = -8551347266419380333L;

    private Long id;

    private String userId;

    /**
     * 用户余额
     */
    private Long balance;

    /**
     * 冻结金额，扣款暂存余额
     */
    private Long freezeAmount;

    private Date createTime;

    private Date updateTime;
}