package com.luop.controller;

import com.luop.entity.CommonResult;
import com.luop.service.PaymentFeignService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Author: luoping
 * @Date: 2020/5/27 14:52
 * @Description:
 */
@RestController
@RequestMapping("/consumer")
public class ConsumerController {

    @Resource
    private PaymentFeignService paymentFeignService;

    @Value("${config.info}")
    private String info;

    @GetMapping
    public CommonResult getPort() {
        return paymentFeignService.getPort();
    }

    @GetMapping("/feignTimeout")
    public CommonResult getFeignTimeout() {
        return paymentFeignService.getFeignTimeout();
    }

    @GetMapping("/config/info")
    public CommonResult getConfigPort() {
        return new CommonResult(200, "读取配置信息成功", info);
    }
}
