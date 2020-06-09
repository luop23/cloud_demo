package com.luop.service.impl;

import com.alibaba.nacos.common.util.UuidUtils;
import com.luop.entity.TOrder;
import com.luop.mapper.TOrderMapper;
import com.luop.service.OrderService;
import org.apache.rocketmq.spring.support.RocketMQHeaders;
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
public class OrderServiceImpl implements OrderService {

    @Resource
    private TOrderMapper orderMapper;
    @Resource
    private Source source;

    @Override
    public void pushMessage(TOrder order) {
        //发送半消息
        String uuid = UuidUtils.generateUuid();    //生成唯一id作为事务主键
        source.output().send(
                MessageBuilder.withPayload(order)
                        .setHeader(RocketMQHeaders.TRANSACTION_ID, uuid)
                        .build()
        );
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveOrder(TOrder order) {
        orderMapper.insert(order);
        pushMessage(order);
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
