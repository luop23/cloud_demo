package com.luop.config;

import com.alibaba.csp.sentinel.adapter.servlet.callback.UrlCleaner;
import org.apache.commons.lang.math.NumberUtils;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * @Author: luoping
 * @Date: 2020/6/2 21:16
 * @Description:   处理http请求中携带的参数，使其规则化
 * 例如一个api请求：/testSentinel/{}
 * 如果只对/testSentinel/2 添加流控处理 只会使/testSentinel/2 接口生效 而不会使/testSentinel/3 接口生效
 */
@Component
public class MyURLCleaner implements UrlCleaner {
    @Override
    public String clean(String originUrl) {
        String[] split = originUrl.split("/");
        return Arrays.stream(split)
                .map(s -> {
                    if (NumberUtils.isNumber(s)) {
                        return "{number}";
                    }
                    return s;
                })
                .reduce((a, b) -> a + "/" + b)
                .orElse("");
    }
}
