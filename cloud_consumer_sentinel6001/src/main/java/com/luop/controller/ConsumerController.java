package com.luop.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.luop.controller.fallback.ConsumerBlockHandler;
import com.luop.controller.fallback.ConsumerControllerFallback;
import com.luop.entity.CommonResult;
import com.luop.service.PaymentFeignService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
@Slf4j
public class ConsumerController {

    @Resource
    private PaymentFeignService paymentFeignService;

    @GetMapping
    public CommonResult getPort() {
        return paymentFeignService.getPort();
    }

    @GetMapping("/feignTimeout")
    public CommonResult getFeignTimeout() {
        return paymentFeignService.getFeignTimeout();
    }

    @GetMapping("/all/{id}")
    //fallbackClass:业务逻辑异常降级类.class   fallback:  具体异常降级的方法   注：如果是单独异常降级类，则降级方法必须由static修饰！！
//    @SentinelResource(value = "getAll",fallbackClass = {ConsumerControllerFallback.class},fallback = "handlerFallback")    //异常降级方法在外部类
//    @SentinelResource(value = "getAll", fallback = "handlerFallback")    //异常降级方法在内部
    //注意：此处的value值要唯一，并且与sentinel界面设置的规则资源路径一致！即限流规则里的资源名必须和@SentinelResource注解声明的资源一致才能进入自定义的blockHandler方法
//    @SentinelResource(value = "getAllInfo", blockHandler = "blockHandler")    //sentinel配置的规则降级
    @SentinelResource(value = "fallback", fallbackClass = ConsumerControllerFallback.class, fallback = "handlerFallback"
            , blockHandlerClass = ConsumerBlockHandler.class, blockHandler = "blockHandler")    //sentinel配置的规则降级与业务逻辑异常降级
    public CommonResult getAllInfo(@PathVariable Long id) {
        if (id == 2L) {
            int i = 1 / 0;
        }
        return paymentFeignService.getAll();
    }

    /**
     * 生产上sentinel配置
     * ①配置MyURLBlockHandler类即可实现流控降级等处理，不用添加@sentinelResource注解
     * ②
     * @param id
     * @return
     */
    @GetMapping("/sentinel/{id}")
    public CommonResult get(@PathVariable Long id){
        return paymentFeignService.getAll();
    }

    //业务逻辑异常降级处理
    public CommonResult handlerFallback() {
        return new CommonResult(333, "I'm Sorry! 网页丢失了。。。。");
    }

    //sentinel流控等其他规则降级
    public CommonResult blockHandler(BlockException blockException) {    //必须要有BlockException参数，因为sentinel降级是拦截该异常进行处理的
        log.error(blockException.toString());
        return new CommonResult(555, "服务器繁忙，请稍后再试！");
    }

}
