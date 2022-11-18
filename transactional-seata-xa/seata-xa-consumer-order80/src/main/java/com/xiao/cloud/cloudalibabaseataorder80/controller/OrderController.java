package com.xiao.cloud.cloudalibabaseataorder80.controller;

import com.xiao.cloud.cloudalibabaseataorder80.api.CommodityApi;
import com.xiao.cloud.cloudalibabaseataorder80.service.OrderService;
import com.xiao.cloud.cloudalibabaseataorder80.api.StockApi;
import com.xiao.cloud.cloudcommon.common.CommonResult;
import com.xiao.cloud.cloudcommon.seata_at.commodity.entity.CommodityDetails;
import com.xiao.cloud.cloudcommon.seata_at.order.entity.OrderList;
import com.xiao.cloud.cloudcommon.seata_at.stock.entity.PhoneStock;
import io.seata.core.context.RootContext;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.math.BigDecimal;

/**
 * @author aloneMan
 * @projectName spring-cloud-learn-02
 * @createTime 2022-10-30 13:48:40
 * @description
 */
@RestController
@RequestMapping("/order")
@Slf4j
public class OrderController {

    @Resource
    private OrderService orderService;

    @Resource
    private StockApi stockApi;

    @Resource
    private CommodityApi commodityApi;

    @PostMapping("/addOrder")
    @GlobalTransactional(rollbackFor = Exception.class)
    public CommonResult<OrderList> add(OrderList orderList) {
        //首先获得商品信息
        CommodityDetails commodity = commodityApi.get(orderList.getCommodityId()).getData();
        //获得商品库存
        PhoneStock phoneStock = stockApi.get(commodity.getId()).getData();

        //购买的数量
        Integer commodityCount = orderList.getCommodityCount();

        //总价
        BigDecimal totalPrice = new BigDecimal(commodityCount).multiply(commodity.getPrice());

        //设置值
        orderList.setTotalPricel(totalPrice);
        //减少库存
        phoneStock.setCount(phoneStock.getCount() - orderList.getCommodityCount());
        //保存订单信息
        orderService.save(orderList);
        log.info("全局事务ID >>>>  {}", RootContext.getXID());
        //扣除商品库存
        stockApi.update(phoneStock);
        //模拟业务发生异常
        int i = 1 / 0;
        return new CommonResult<>(0x000001L, "添加成功", orderList);
    }

    @PostMapping("/addOrderTwo")
    @GlobalTransactional(rollbackFor = Exception.class)
    public CommonResult<OrderList> addTwo(OrderList orderList) {

        //添加订单记录
        orderList.setTotalPricel(new BigDecimal(10000.00));
        orderService.save(orderList);
        //减少库存
        PhoneStock phoneStock = new PhoneStock();
        phoneStock.setId(1L);
        phoneStock.setCount((int) (100 - orderList.getId()));
        stockApi.update(phoneStock);
        return new CommonResult<>(0x000001L, "添加成功", orderList);
    }
}
