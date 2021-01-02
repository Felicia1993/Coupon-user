package com.xdclass.userapp.service.dubbo;

import com.fasterxml.jackson.databind.util.BeanUtil;
import com.xdclass.couponserviceapi.DTO.UserVO;
import com.xdclass.couponserviceapi.service.IUserService;
import com.xdclass.userapp.mapper.TUserMapper;
import com.xdclass.userapp.model.TUser;
import com.xdclass.userapp.model.TUserExample;
import org.apache.catalina.User;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserService implements IUserService {
    @Resource
    private TUserMapper tUserMapper;

    /**
     * 通过用户id获取用户信息接口
     * @param id
     * @return
     */
    @Override
    public UserVO getUserById(Long id) {
        TUser tUser = tUserMapper.selectByPrimaryKey(id);
        UserVO userVO = new UserVO();
        BeanUtils.copyProperties(tUser, userVO);
        return userVO;
    }
}
