package com.luop.controller;

import com.luop.entity.CommonResult;
import com.luop.entity.TOrder;
import com.luop.service.OrderService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @Author: luoping
 * @Date: 2020/6/1 10:34
 * @Description:
 */
@RestController
@RequestMapping("/order")
public class OrderController {

    @Resource
    private OrderService orderService;

    @PostMapping
    public CommonResult createOrder(@RequestBody TOrder order) {
        orderService.createOrder(order);
        return new CommonResult(200, "订单创建成功！");
    }

    @GetMapping
    public CommonResult getAllOrder() {
        return new CommonResult(200, "数据查询成功", orderService.list());
    }
}
