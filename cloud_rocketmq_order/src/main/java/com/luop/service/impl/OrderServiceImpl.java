package com.luop.service.impl;

import com.alibaba.nacos.common.util.UuidUtils;
import com.luop.entity.RocketmqTransactionLog;
import com.luop.entity.TOrder;
import com.luop.mapper.RocketmqTransactionLogMapper;
import com.luop.mapper.TOrderMapper;
import com.luop.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @Author: luoping
 * @Date: 2020/6/8 17:31
 * @Description:
 */
@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

    @Resource
    private TOrderMapper orderMapper;

    @Resource
    private RocketmqTransactionLogMapper logMapper;

    @Resource
    private Source source;

    @Override
    public void pushMessage(TOrder order) {
        //发送半消息
        source.output().send(MessageBuilder.withPayload(order).build());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveOrder(TOrder order) {
        orderMapper.insert(order);   //创建订单
        String uuid = UuidUtils.generateUuid();    //生成唯一id作为事务主键
        logMapper.insert(RocketmqTransactionLog.builder().id(order.getId()).transactionId(uuid).description("订单创建完成").build());   //创建事务日志记录
        log.info("订单创建完成！！！！");
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void editOrder(TOrder order) {
        orderMapper.updateById(order);
    }

    @Override
    public TOrder getById(long orderId) {
        return orderMapper.selectById(orderId);
    }
}
