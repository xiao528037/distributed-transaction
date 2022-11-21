package com.xiao.cloud.cloudcommon.message_tx.account.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xiao.cloud.cloudcommon.hmily_tcc.account.dto.AccountDTO;
import com.xiao.cloud.cloudcommon.hmily_tcc.account.entity.HmilyTccAccount;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

/**
 * @author aloneMan
 * @projectName distributed-transaction
 * @createTime 2022-11-17 15:50:02
 * @description
 */
@Mapper
@SuppressWarnings("all")
public interface AccountMapper extends BaseMapper<HmilyTccAccount> {

    /**
     * 冻结账户余额
     *
     * @param accountDTO
     * @return 更新数据数量
     */
    @Update("UPDATE hmily_tcc_account.hmily_tcc_account SET balance=balance-#{amount,jdbcType=DECIMAL}," +
            "freeze_amount = freeze_amount + #{amount,jdbcType=DECIMAL},update_time=now() WHERE user_id = #{userId,jdbcType=VARCHAR} " +
            "AND balance >= #{amount,jdbcType=DECIMAL}")
    int update(AccountDTO accountDTO);

    /**
     * 扣除账户余额
     *
     * @param accountDTO
     * @return 更新数据数量
     */
    @Update("UPDATE hmily_tcc_account.hmily_tcc_account SET freeze_amount=freeze_amount - #{amount} " +
            "WHERE user_id= #{userId,jdbcType=VARCHAR} AND freeze_amount >= #{amount,jdbcType=DECIMAL}")
    int commit(AccountDTO accountDTO);

    /**
     * 回滚账户余额
     *
     * @param accountDTO
     * @return 更新数据数量
     */
    @Update("UPDATE hmily_tcc_account.hmily_tcc_account SET balance=balance+#{amount,jdbcType=DECIMAL}," +
            "freeze_amount=freeze_amount-#{amount,jdbcType=DECIMAL} WHERE user_id = #{userId,jdbcType=VARCHAR} " +
            "AND freeze_amount >= #{amount,jdbcType=DECIMAL}")
    int rollback(AccountDTO accountDTO);
}
