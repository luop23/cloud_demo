package com.luop.listener;

import com.luop.entity.dto.OrderDTO;
import com.luop.service.OrderService;
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
    private OrderService orderService;

    @Resource
    private TransactionLogService logService;

    @Override
    public RocketMQLocalTransactionState executeLocalTransaction(Message msg, Object arg) {
        String uuid = Objects.requireNonNull(msg.getHeaders().get(RocketMQHeaders.TRANSACTION_ID)).toString();    //获取事务id
        OrderDTO orderDTO = (OrderDTO) msg.getPayload();
        log.info(orderDTO.toString());
        return null;
    }

    @Override
    public RocketMQLocalTransactionState checkLocalTransaction(Message msg) {
        return null;
    }
}
