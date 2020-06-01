package com.luop.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: luoping
 * @Date: 2020/6/1 16:49
 * @Description:
 */
@Configuration
@MapperScan("com.luop.mapper")
public class MyBatisConfig {
}
