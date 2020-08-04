package com.bqx.community.service.impl;

import com.bqx.community.mapper.UserMapper;
import com.bqx.community.pojo.User;
import com.bqx.community.service.UserService;
import com.bqx.community.utils.FileUtils;
import com.bqx.community.utils.ResultModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userDao;

    @Override
    public ResultModel<User> getUser(String userId) {
        ResultModel<User> userResultModel=  userDao.getUser(userId);
        return userResultModel;
    }

    @Override
    public void getImageFromHttp( String urlStr) throws IOException {
        ByteArrayOutputStream output = null;
        InputStream inputStream = null;
        InputStream result = null;
        try {
            URL url = new URL(urlStr);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(20 * 1000);
            inputStream = connection.getInputStream();
            output = FileUtils.inputStreamCache(inputStream);
            result = new ByteArrayInputStream(output.toByteArray());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (output != null) {
                output.close();
                output = null;
            }
            if (inputStream != null) {
                inputStream.close();
                inputStream = null;
            }
        }
    }
}
