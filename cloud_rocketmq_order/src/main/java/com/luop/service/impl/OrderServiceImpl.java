package com.luop.service.impl;

import com.alibaba.nacos.common.util.UuidUtils;
import com.luop.entity.TOrder;
import com.luop.entity.dto.OrderDTO;
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
    @Transactional(rollbackFor = Exception.class)
    public void createOrder(TOrder order) {
        order.setStatus(0);
        orderMapper.insert(order);    //新增订单
        //发送半消息
        String uuid = UuidUtils.generateUuid();    //生成唯一id作为事务主键
        source.output().send(
                MessageBuilder.withPayload(
                        OrderDTO.builder()
                                .userId(order.getUserId())
                                .productId(order.getProductId())
                                .count(order.getCount())
                                .money(order.getMoney())
                                .build())
                        .setHeader(RocketMQHeaders.TRANSACTION_ID, uuid)
                        .build()
        );
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void editOrder(TOrder order) {
        orderMapper.updateById(order);
    }
}
