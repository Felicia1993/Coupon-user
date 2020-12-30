package com.xdclass.couponapp;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.xdclass.couponapp.mapper")//扫描当前package下的所有包
public class CouponAppApplication {
    public static void main(String[] args) {
        SpringApplication.run(CouponAppApplication.class, args);
    }
}
