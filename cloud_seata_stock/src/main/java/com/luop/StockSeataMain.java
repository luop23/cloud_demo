package com.luop;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @Author: luoping
 * @Date: 2020/6/1 09:39
 * @Description:
 */
@SpringBootApplication(/*exclude = DataSourceAutoConfiguration.class*/)
@MapperScan("com.luop.mapper")
@EnableFeignClients
public class StockSeataMain {

    public static void main(String[] args) {
        SpringApplication.run(StockSeataMain.class, args);
    }
}
