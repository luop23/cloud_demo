package com.luop;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;

/**
 * @Author: luoping
 * @Date: 2020/6/8 17:03
 * @Description:
 */
@SpringBootApplication
@EnableFeignClients
@MapperScan("com.luop.mapper")
@EnableBinding(Source.class)
public class CloudRocketMqOrderMain {

    public static void main(String[] args) {
        SpringApplication.run(CloudRocketMqOrderMain.class, args);
    }
}
