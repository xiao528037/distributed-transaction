package com.xiao.cloud.rocketmqmessageorder;

import com.netflix.client.ClientException;
import com.xiao.cloud.cloudcommon.message_tx.order.entity.MessageTxOrder;
import com.xiao.cloud.rocketmqmessageorder.service.OrderService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.Date;

import static com.xiao.cloud.rocketmqmessageorder.controller.OrderController.buildOrder;

@SpringBootTest
class RocketmqMessageOrderApplicationTests {

    @Autowired
    private OrderService orderService;

    @Test
    public void t1() {
        MessageTxOrder messageTxOrder = buildOrder(1, BigDecimal.valueOf(1));

    }
}
