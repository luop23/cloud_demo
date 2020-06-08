package com.luop;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @Author: luoping
 * @Date: 2020/6/8 17:13
 * @Description:
 */
@SpringBootApplication
@EnableFeignClients
@MapperScan("com.luop.mapper")
public class CloudRocketMqStockMain {
    public static void main(String[] args) {
        SpringApplication.run(CloudRocketMqStockMain.class, args);
    }
}
