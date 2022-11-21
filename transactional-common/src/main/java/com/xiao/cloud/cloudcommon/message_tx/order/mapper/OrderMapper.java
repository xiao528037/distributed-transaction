package com.xiao.cloud.cloudcommon.message_tx.order.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xiao.cloud.cloudcommon.hmily_tcc.order.entity.HmilyTccOrder;
import com.xiao.cloud.cloudcommon.message_tx.order.entity.MessageTxOrder;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

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
     * @param hmilyTccOrder
     *         订单信息
     * @return 数量
     */
    @Insert("INSERT INTO hmily_tcc_order.hmily_tcc_order(create_time,number,status,product_id,total_amount,count,user_id)" +
            "VALUES (#{createTime,jdbcType=TIMESTAMP}," +
            "#{number,jdbcType=VARCHAR}," +
            "#{status,jdbcType=INTEGER}," +
            "#{productId,jdbcType=VARCHAR}," +
            "#{totalAmount,jdbcType=DECIMAL}," +
            "#{count,jdbcType=INTEGER}," +
            "#{userId,jdbcType=VARCHAR})")
    int save(HmilyTccOrder hmilyTccOrder);

    /**
     * 更新订单状态
     *
     * @param hmilyTccOrder
     *         订单对象
     * @return 数量
     */
    @Update("UPDATE hmily_tcc_order.hmily_tcc_order SET status = #{status,jdbcType=INTEGER} WHERE number = #{number,jdbcType=VARCHAR}")
    int update(HmilyTccOrder hmilyTccOrder);
}
