package com.xdclass.couponapp;

import com.xdclass.couponapp.model.TCoupon;
import com.xdclass.couponapp.mapper.TCouponMapper;
import com.xdclass.couponapp.model.TCouponExample;
import com.xdclass.couponapp.service.CouponService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = CouponAppApplication.class)
public class CouponAppApplicationTests {

    @Resource
    private CouponService couponService;
    @Resource
    private TCouponMapper tCouponMapper;
    @Test
    public void contextLoads() {
        couponService.print();
        System.err.println("hello world");
    }
    @Test
    public void insertSelective() {
        TCoupon tCoupon = new TCoupon();
        tCoupon.setCode(UUID.randomUUID().toString());
        tCoupon.setPicUrl("1.jpg");
        tCoupon.setAchieveAmount(500);
        tCoupon.setReduceAmount(20);
        tCoupon.setCreateTime(new Date());
        tCoupon.setStock(100);
        tCoupon.setTitle("测试coupon");
        tCoupon.setStatus(0);
        tCouponMapper.insert(tCoupon);
    }

    @Test
    public void delete(){
        tCouponMapper.deleteByPrimaryKey(7);
    }


    @Test
    public void select() {
        TCouponExample example = new TCouponExample();
        example.createCriteria().andCodeEqualTo("004515d96-49bd-4cce-83e3-08302b9aa084").andStatusEqualTo(0)
                .andAchieveAmountBetween(100,1000);
        List<TCoupon> tCou = tCouponMapper.selectByExample(example);
        System.err.println(tCouponMapper);
    }
}
