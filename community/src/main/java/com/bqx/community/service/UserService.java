package com.bqx.community.service;

import com.bqx.community.pojo.ExcleImportInfo;
import com.bqx.community.pojo.vo.ServiceResponse;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public interface UserService {
   // ResultModel<User> getUser(String userId);

    void getImageFromHttp(String urlStr) throws IOException;

    ServiceResponse insertUserExcle(List<ExcleImportInfo> list);
}
