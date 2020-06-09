package com.luop.controller;

import com.luop.entity.CommonResult;
import com.luop.entity.TOrder;
import com.luop.service.OrderService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Author: luoping
 * @Date: 2020/6/8 17:30
 * @Description:
 */
@RestController
@RequestMapping("/order")
public class OrderController {

    @Resource
    private OrderService orderService;

    @PostMapping
    public CommonResult createOrder(@RequestBody TOrder order) {
        orderService.saveOrder(order);
        return new CommonResult(200, "订单创建成功！");
    }

}
