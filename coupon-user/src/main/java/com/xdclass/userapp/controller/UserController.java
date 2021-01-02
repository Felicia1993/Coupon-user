package com.xdclass.userapp.controller;

import com.xdclass.couponserviceapi.DTO.UserVO;
import com.xdclass.userapp.service.dubbo.UserService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class UserController {
    @Resource
    private UserService userService;

    @RequestMapping("/getUserById")
    public UserVO getUserById(Long id) {
        if (id== null){
            return new UserVO();
        }
        return userService.getUserById(id);
    }
}
