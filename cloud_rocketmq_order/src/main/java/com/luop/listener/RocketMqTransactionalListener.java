package com.luop.listener;

import com.alibaba.fastjson.JSONObject;
import com.luop.entity.RocketmqTransactionLog;
import com.luop.entity.TOrder;
import com.luop.service.TransactionLogService;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionState;
import org.apache.rocketmq.spring.support.RocketMQHeaders;
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

    @Override
    public RocketMQLocalTransactionState executeLocalTransaction(Message msg, Object arg) {
        log.info("监听消息是否到达消息队列=====================");
        String uuid = Objects.requireNonNull(msg.getHeaders().get(RocketMQHeaders.TRANSACTION_ID)).toString();    //获取事务id
        String orderJson = new String((byte[]) msg.getPayload());
        TOrder order = JSONObject.parseObject(orderJson, TOrder.class);
        try {
            logService.createTransactionLog(RocketmqTransactionLog.builder().id(order.getId()).transactionId(uuid).description("订单创建完成").build());
            return RocketMQLocalTransactionState.COMMIT;     //提交
        } catch (Exception e) {
            log.error("e={}", e);
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
