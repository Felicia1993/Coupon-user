package com.xdclass.couponapp.mapper;

import com.xdclass.couponapp.model.TUserCoupon;
import com.xdclass.couponapp.model.TUserCouponExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TUserCouponMapper {
    long countByExample(TUserCouponExample example);

    int deleteByExample(TUserCouponExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TUserCoupon record);

    int insertSelective(TUserCoupon record);

    List<TUserCoupon> selectByExample(TUserCouponExample example);

    TUserCoupon selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TUserCoupon record, @Param("example") TUserCouponExample example);

    int updateByExample(@Param("record") TUserCoupon record, @Param("example") TUserCouponExample example);

    int updateByPrimaryKeySelective(TUserCoupon record);

    int updateByPrimaryKey(TUserCoupon record);
}