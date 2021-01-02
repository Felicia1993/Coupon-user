package com.xdclass.userapp;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.xdclass.userapp.mapper")//扫描当前package下的所有包
public class UserAppApplication {
    public static void main(String[] args) {
        SpringApplication.run(UserAppApplication.class, args);
    }
}
