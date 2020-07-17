package com.luop.tx_mq;

import com.luop.entity.TAccount;
import com.luop.entity.TOrder;
import com.luop.service.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @Author: luoping
 * @Date: 2020/6/9 10:33
 * @Description:
 */
@Component
@Slf4j
public class AccountConsumer {

    @Resource
    private AccountService accountService;

    //扣减金额
    @StreamListener(Sink.INPUT)
    public void decreaseAccount(TOrder order) {
        log.info("扣减金额开始=================");
        int i = 1/0;
        TAccount account = accountService.getByUserId(order.getUserId());
        account.setUsed(account.getUsed().add(order.getMoney())).setResidue(account.getResidue().subtract(order.getMoney()));
        accountService.update(account);
        log.info("扣减金额成功==================");
    }
}
