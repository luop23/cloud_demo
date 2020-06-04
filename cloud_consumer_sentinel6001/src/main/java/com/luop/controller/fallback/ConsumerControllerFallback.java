package com.luop.controller.fallback;

import com.luop.entity.CommonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @Author: luoping
 * @Date: 2020/5/29 11:46
 * @Description:
 */
@Slf4j
public class ConsumerControllerFallback {

    //业务逻辑异常降级处理
    public static CommonResult handlerFallback(@PathVariable Long id, Throwable throwable) {
        log.error(throwable.toString());
        return new CommonResult(333, "I'm Sorry! 网页丢失了。。。。",id);
    }
}
