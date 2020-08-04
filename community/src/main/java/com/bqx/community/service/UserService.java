package com.bqx.community.service;

import com.bqx.community.pojo.User;
import com.bqx.community.utils.ResultModel;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public interface UserService {
    ResultModel<User> getUser(String userId);

    void getImageFromHttp(String urlStr) throws IOException;
}
