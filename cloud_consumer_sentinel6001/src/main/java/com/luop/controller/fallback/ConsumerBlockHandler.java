package com.luop.controller.fallback;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.authority.AuthorityException;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeException;
import com.alibaba.csp.sentinel.slots.block.flow.FlowException;
import com.alibaba.csp.sentinel.slots.block.flow.param.ParamFlowException;
import com.alibaba.csp.sentinel.slots.system.SystemBlockException;
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
        /*CommonResult commonResult = null;
        //细化异常
        if (blockException instanceof FlowException) {   //流控
            commonResult = new CommonResult(111, "限流了", blockException);
        } else if (blockException instanceof DegradeException) {
            commonResult = new CommonResult(222, "降级了", blockException);
        } else if (blockException instanceof ParamFlowException) {
            commonResult = new CommonResult(333, "热点参数", blockException);
        } else if (blockException instanceof SystemBlockException) {
            commonResult = new CommonResult(444, "系统规则", blockException);
        } else if (blockException instanceof AuthorityException) {
            commonResult = new CommonResult(555, "授权规则", blockException);
        }
        return commonResult;*/
    }
}
