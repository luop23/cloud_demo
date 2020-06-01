package com.luop.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.luop.entity.TOrder;

/**
 * @Author: luoping
 * @Date: 2020/6/1 09:51
 * @Description:
 */
public interface OrderService extends IService<TOrder> {

    //新增订单
    void createOrder(TOrder order);

    //修改订单状态
    void editOrder(TOrder order);
}
