package com.luop;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @Author: luoping
 * @Date: 2020/6/1 09:35
 * @Description:
 */
@MapperScan("com.luop.mapper")
@SpringBootApplication(/*exclude = DataSourceAutoConfiguration.class*/)   //取消数据源的自动创建
@EnableFeignClients
public class OrderSeataMain {
    public static void main(String[] args) {
        SpringApplication.run(OrderSeataMain.class, args);
    }
}
