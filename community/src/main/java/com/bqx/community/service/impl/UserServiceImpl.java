package com.bqx.community.service.impl;

import com.bqx.community.mapper.UserMapper;
import com.bqx.community.pojo.ExcleImportCityRequest;
import com.bqx.community.pojo.ExcleImportInfo;
import com.bqx.community.pojo.ResumeHistoryClean;
import com.bqx.community.pojo.vo.ServiceResponse;
import com.bqx.community.service.UserService;
import com.bqx.community.utils.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.function.ServerResponse;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

  /*  @Override
    public ResultModel<User> getUser(String userId) {
        ResultModel<User> userResultModel=  userDao.getUser(userId);
        return userResultModel;
    }*/

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

   /* @Override
    public ServiceResponse insertUserExcle(List<ExcleImportInfo> list) {
           userMapper.insertUserExcle(list);

        return  ServiceResponse.ok();
    }

    @Override
    public ServerResponse impltreadCityExcel(List<ExcleImportCityRequest> list) {
        userMapper.impltreadCityExcel(list);
        return null;
    }*/

    @Override
    public ResumeHistoryClean resumeHistoryClean(String urlStr) {




        return null;
    }

    @Override
    public List<Map> getRpoCityList() {
        List<Map> m=   userMapper.getRpoCityList();

        return m;
    }
}
