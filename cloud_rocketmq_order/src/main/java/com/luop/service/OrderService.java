package com.luop.service;

import com.luop.entity.TOrder;

/**
 * @Author: luoping
 * @Date: 2020/6/8 17:30
 * @Description:
 */
public interface OrderService {

    //发送消息
    void pushMessage(TOrder order);

    //
    void saveOrder(TOrder order);

    //修改订单
    void editOrder(TOrder order);

    //根据id查询数据
    TOrder getById(long orderId);

}
