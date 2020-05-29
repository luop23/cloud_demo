package com.luop.controller.fallback;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.luop.entity.CommonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @Author: luoping
 * @Date: 2020/5/29 14:31
 * @Description:
 */
@Slf4j
public class ConsumerBlockHandler {

    //sentinel流控等其他规则降级
    public static CommonResult blockHandler(@PathVariable Long id, BlockException blockException) {    //必须要有BlockException参数，因为sentinel降级是拦截该异常进行处理的
        log.error(blockException.toString());
        return new CommonResult(555, "服务器繁忙，请稍后再试！", id);
    }
}
