package com.luop;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @Author: luoping
 * @Date: 2020/6/1 09:29
 * @Description:
 */
@MapperScan("com.luop.mapper")
@SpringBootApplication(/*exclude = DataSourceAutoConfiguration.class*/)
@EnableFeignClients
public class AccountSeataMain {

    public static void main(String[] args) {
        SpringApplication.run(AccountSeataMain.class, args);
    }
}
