package com.bqx.community.service.impl;

import com.bqx.community.mapper.UserMapper;
import com.bqx.community.pojo.User;
import com.bqx.community.service.UserService;
import com.bqx.community.utils.ResultModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userDao;

    @Override
    public ResultModel<User> getUser(String userId) {
        ResultModel<User> userResultModel=  userDao.getUser(userId);
        return userResultModel;
    }
}
