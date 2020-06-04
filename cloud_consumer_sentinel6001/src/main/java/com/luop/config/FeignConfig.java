package com.luop.config;

import com.luop.interceptor.HeaderRequestInterceptor;
import feign.Logger;
import feign.RequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: luoping
 * @Date: 2020/5/27 16:28
 * @Description: 配置feign的日志级别
 */
@Configuration
public class FeignConfig {

    @Bean
    Logger.Level feignLogLevel(){
        return Logger.Level.FULL;
    }

    //注入请求头拦截器实例
    @Bean
    public RequestInterceptor requestInterceptor(){
        return new HeaderRequestInterceptor();
    }
}
