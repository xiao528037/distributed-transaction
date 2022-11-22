package com.xiao.cloud.cloudcommon.message_tx.account.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import com.xiao.cloud.cloudcommon.message_tx.account.dto.AccountDTO;
import com.xiao.cloud.cloudcommon.message_tx.account.entity.MessageTxAccount;
import org.apache.ibatis.annotations.*;

/**
 * @author aloneMan
 * @projectName distributed-transaction
 * @createTime 2022-11-17 15:50:02
 * @description
 */
@Mapper
@SuppressWarnings("all")
public interface AccountMapper extends BaseMapper<MessageTxAccount> {

    /**
     * 冻结账户余额
     *
     * @param accountDTO
     * @return 更新数据数量
     */
    @Update("UPDATE message_tx_account.message_tx_account SET balance=balance-#{amount,jdbcType=DECIMAL}," +
            "freeze_amount = freeze_amount + #{amount,jdbcType=DECIMAL},update_time=now() WHERE user_id = #{userId,jdbcType=VARCHAR} " +
            "AND balance >= #{amount,jdbcType=DECIMAL}")
    int update(AccountDTO accountDTO);

    /**
     * 扣除账户余额
     *
     * @param accountDTO
     * @return 更新数据数量
     */
    @Update("UPDATE message_tx_account.message_tx_account SET freeze_amount=freeze_amount - #{amount} " +
            "WHERE user_id= #{userId,jdbcType=VARCHAR} AND freeze_amount >= #{amount,jdbcType=DECIMAL}")
    int commit(AccountDTO accountDTO);

    /**
     * 回滚账户余额
     *
     * @param accountDTO
     * @return 更新数据数量
     */
    @Update("UPDATE message_tx_account.message_tx_account SET balance=balance+#{amount,jdbcType=DECIMAL}," +
            "freeze_amount=freeze_amount-#{amount,jdbcType=DECIMAL} WHERE user_id = #{userId,jdbcType=VARCHAR} " +
            "AND freeze_amount >= #{amount,jdbcType=DECIMAL}")
    int rollback(AccountDTO accountDTO);

    /**
     * 查询事务是否存在
     *
     * @param transactionalId
     *         事务ID
     * @return 存在返回1，没有返回false
     */
    @Select("SELECT count(0) FROM message_tx_account.transactional_record" +
            " WHERE transactional_id = #{transactionalId,jdbcType=VARCHAR}")
    int isExist(String transactionalId);

    /**
     *
     * 添加事务信息
     *
     * @param productId 订单ID
     * @param transactionalId 事务ID
     * @return 添加数量
     */
    @Insert("INSERT INTO message_tx_account.transactional_record(order_number,transactional_id) VALUES " +
            "(#{productId,jdbcType=VARCHAR},#{transactionalId,jdbcType=VARCHAR})")
    int saveTransactional(@Param("productId") String productId, @Param("transactionalId") String transactionalId);
}
