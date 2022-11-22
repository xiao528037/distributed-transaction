package com.xiao.cloud.cloudcommon.message_tx.order.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xiao.cloud.cloudcommon.hmily_tcc.order.entity.HmilyTccOrder;
import com.xiao.cloud.cloudcommon.message_tx.order.entity.MessageTxOrder;
import org.apache.ibatis.annotations.*;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author aloneMan
 * @projectName distributed-transaction
 * @createTime 2022-11-18 10:14:42
 * @description
 */
@Mapper
public interface OrderMapper extends BaseMapper<MessageTxOrder> {

    /**
     * 添加订单信息
     *
     * @param messageTxOrder
     *         订单信息
     * @return 数量
     */
    @Insert("INSERT INTO message_tx_order.message_tx_order(create_time,number,status,product_id,total_amount,count,user_id)" +
            "VALUES (#{createTime,jdbcType=TIMESTAMP}," +
            "#{number,jdbcType=VARCHAR}," +
            "#{status,jdbcType=INTEGER}," +
            "#{productId,jdbcType=VARCHAR}," +
            "#{totalAmount,jdbcType=DECIMAL}," +
            "#{count,jdbcType=INTEGER}," +
            "#{userId,jdbcType=VARCHAR})")
    int save(MessageTxOrder messageTxOrder);

    /**
     * 更新订单状态
     *
     * @param messageTxOrder
     *         订单对象
     * @return 数量
     */
    @Update("UPDATE message_tx_order.message_tx_order SET status = #{status,jdbcType=INTEGER} WHERE number = #{number,jdbcType=VARCHAR}")
    int update(MessageTxOrder messageTxOrder);

    /**
     * 查询事务是否存在
     *
     * @param transactionalId
     *         事务ID
     * @return 存在返回1，没有返回false
     */
    @Select("SELECT count(0) FROM message_tx_order.transactional_record" +
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
    @Insert("INSERT INTO message_tx_order.transactional_record(order_number,transactional_id) VALUES " +
            "(#{productId,jdbcType=VARCHAR},#{transactionalId,jdbcType=VARCHAR})")
    int saveTransactional(@Param("productId") String productId, @Param("transactionalId") String transactionalId);
}
