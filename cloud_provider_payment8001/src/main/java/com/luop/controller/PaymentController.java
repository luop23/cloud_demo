package com.luop.controller;

import com.luop.entity.CommonResult;
import com.luop.entity.Payment;
import com.luop.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * @Author: luoping
 * @Date: 2020/5/25 17:50
 * @Description:
 */
@RestController
@Slf4j
@RequestMapping("/payment")
public class PaymentController {

    @Resource
    private PaymentService paymentService;

    @Value("${server.port}")
    private String port;

    @PostMapping
    public CommonResult create(@RequestBody Payment payment) {
        boolean save = paymentService.save(payment);
        log.info("插入结果：{}", payment);
        if (save) {
            return new CommonResult(200, "插入数据成功！");
        } else {
            return new CommonResult(444, "插入数据失败！");
        }
    }

    @GetMapping("/{id}")
    public CommonResult getById(@PathVariable Long id) {
        Payment payment = paymentService.getById(id);
        log.info("查询结果：{}", payment);
        if (Objects.nonNull(payment)) {
            return new CommonResult(200, "查询成功！", payment);
        } else {
            return new CommonResult(444, "查询失败！");
        }
    }

    @GetMapping
    public CommonResult getAll() {
        List<Payment> payments = paymentService.list();
        return new CommonResult(200, "查询数据成功", payments);
    }

    @GetMapping("/port")
    public CommonResult getPort() {
        return new CommonResult(200, "数据查询成功", port);
    }

    @GetMapping("/timeout")
    public CommonResult getFeignTimeout() {
        try {
            TimeUnit.SECONDS.sleep(3);   //模拟feign超时
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return new CommonResult(200, "数据查询成功", port);
    }
}
