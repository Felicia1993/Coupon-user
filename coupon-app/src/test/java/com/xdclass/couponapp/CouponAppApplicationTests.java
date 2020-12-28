package com.xdclass.couponapp;

import com.xdclass.couponapp.service.CouponService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = CouponAppApplication.class)
public class CouponAppApplicationTests {

    @Resource
    private CouponService couponService;
    @Test
    public void contextLoads() {
        couponService.print();
        System.err.println("hello world");
    }

    public static void main(String[] args) {
        System.err.println("main hello world");
    }
}
