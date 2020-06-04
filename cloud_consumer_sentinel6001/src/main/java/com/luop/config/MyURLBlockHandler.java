package com.luop.config;

import com.alibaba.csp.sentinel.adapter.servlet.callback.UrlBlockHandler;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.authority.AuthorityException;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeException;
import com.alibaba.csp.sentinel.slots.block.flow.FlowException;
import com.alibaba.csp.sentinel.slots.block.flow.param.ParamFlowException;
import com.alibaba.csp.sentinel.slots.system.SystemBlockException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.luop.entity.CommonResult;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author: luoping
 * @Date: 2020/6/2 21:05
 * @Description:  sentinel降级处理
 */
@Component
public class MyURLBlockHandler implements UrlBlockHandler {

    @Override
    public void blocked(HttpServletRequest request, HttpServletResponse response, BlockException ex) throws IOException {
        CommonResult commonResult = null;
        //细化异常
        if (ex instanceof FlowException) {   //流控
            commonResult = new CommonResult(111, "限流了", ex);
        } else if (ex instanceof DegradeException) {
            commonResult = new CommonResult(222, "降级了", ex);
        } else if (ex instanceof ParamFlowException) {
            commonResult = new CommonResult(333, "热点参数", ex);
        } else if (ex instanceof SystemBlockException) {
            commonResult = new CommonResult(444, "系统规则", ex);
        } else if (ex instanceof AuthorityException) {
            commonResult = new CommonResult(555, "授权规则", ex);
        }
        //设置字符集编码
        response.setCharacterEncoding("utf-8");
        response.setHeader("Content-Type", "application/json;charset=utf-8");
        response.setContentType("application/json;charset=utf-8");
        //spring mvc自带的json操作工具 叫jackson
        new ObjectMapper().writeValue(response.getWriter(), commonResult);
    }
}
