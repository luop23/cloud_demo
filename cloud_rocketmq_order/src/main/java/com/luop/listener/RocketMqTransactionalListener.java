package com.luop.listener;

import com.alibaba.fastjson.JSONObject;
import com.luop.entity.RocketmqTransactionLog;
import com.luop.entity.TOrder;
import com.luop.service.OrderService;
import com.luop.service.TransactionLogService;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionState;
import org.springframework.messaging.Message;

import javax.annotation.Resource;
import java.util.Objects;

/**
 * @Author: luoping
 * @Date: 2020/6/8 17:32
 * @Description:
 */
@Slf4j
@RocketMQTransactionListener(txProducerGroup = "tx-order-group", corePoolSize = 5, maximumPoolSize = 10)
public class RocketMqTransactionalListener implements RocketMQLocalTransactionListener {

    @Resource
    private TransactionLogService logService;

    @Resource
    private OrderService orderService;

    @Override
    public RocketMQLocalTransactionState executeLocalTransaction(Message msg, Object arg) {
        log.info("监听消息是否到达消息队列=====================");
        try {
            String orderJson = new String((byte[]) msg.getPayload());
            TOrder order = JSONObject.parseObject(orderJson, TOrder.class);
            orderService.saveOrder(order);
            log.info("本地事务提交成功。");
            return RocketMQLocalTransactionState.COMMIT;     //提交
        } catch (Exception e) {
            log.error("本地事务提交失败，e={}", e);
            return RocketMQLocalTransactionState.ROLLBACK;    //回滚
        }
    }

    @Override
    public RocketMQLocalTransactionState checkLocalTransaction(Message msg) {
        long orderId = Long.parseLong(Objects.requireNonNull(msg.getHeaders().get("orderId")).toString());
        RocketmqTransactionLog transactionLog = logService.findById(orderId);
        if (Objects.nonNull(transactionLog)) {
            return RocketMQLocalTransactionState.COMMIT;
        }
        return RocketMQLocalTransactionState.ROLLBACK;
    }
}
