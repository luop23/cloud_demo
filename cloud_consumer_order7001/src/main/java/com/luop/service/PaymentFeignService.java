package com.luop.service;

import com.luop.entity.CommonResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @Author: luoping
 * @Date: 2020/5/27 15:08
 * @Description:
 */
@FeignClient(value = "cloud-payment-service")
public interface PaymentFeignService {

    @GetMapping(value = "/payment/port")
    CommonResult getPort();

    /**
     * openFeign 默认超时时间为1s
     * @return
     */
    @GetMapping("/payment/timeout")
    CommonResult getFeignTimeout();
}
