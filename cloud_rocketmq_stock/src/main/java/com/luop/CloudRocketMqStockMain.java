package com.luop;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Sink;

/**
 * @Author: luoping
 * @Date: 2020/6/8 17:13
 * @Description:
 */
@SpringBootApplication
@EnableFeignClients
@MapperScan("com.luop.mapper")
@EnableBinding(Sink.class)
public class CloudRocketMqStockMain {
    public static void main(String[] args) {
        SpringApplication.run(CloudRocketMqStockMain.class, args);
    }
}
