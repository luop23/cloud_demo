package com.luop.service;

import com.luop.entity.TOrder;

/**
 * @Author: luoping
 * @Date: 2020/6/8 17:30
 * @Description:
 */
public interface OrderService {

    //创建订单
    void createOrder(TOrder order);

    //修改订单
    void editOrder(TOrder order);

}
