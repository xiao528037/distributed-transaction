package com.xiao.cloud.transactional.seatatccaccount;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

@Slf4j
class SeataTccTccAccountApplicationTests {

    @Test
    void compare() {
        BigDecimal old = new BigDecimal(0);
        BigDecimal newOver = new BigDecimal(-2);
        int i = newOver.compareTo(old);
        log.info(" >> {} ", i);

        BigDecimal count = new BigDecimal(10);
        BigDecimal min = count.subtract(newOver);
        log.info("{} ",min);

    }

}
