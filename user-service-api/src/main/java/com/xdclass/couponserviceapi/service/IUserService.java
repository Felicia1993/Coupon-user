package com.xdclass.couponserviceapi.service;

import com.xdclass.couponserviceapi.DTO.UserVO;

public interface IUserService {
    /**
     * 通过用户id获取用户信息接口
     * @param id
     * @return
     */
    public UserVO getUserById(Long id);
}
