package com.luop.service;

import com.luop.entity.CommonResult;
import org.springframework.stereotype.Component;

/**
 * @Author: luoping
 * @Date: 2020/5/29 15:24
 * @Description:
 */
@Component
public class FeignFallback implements PaymentFeignService{

    @Override
    public CommonResult getPort() {
        return new CommonResult(777,"服务宕机，请稍后再试！");
    }

    @Override
    public CommonResult getFeignTimeout() {
        return new CommonResult(777,"服务宕机，请稍后再试！");
    }

    @Override
    public CommonResult getAll() {
        return new CommonResult(777,"服务宕机，请稍后再试！");
    }
}
