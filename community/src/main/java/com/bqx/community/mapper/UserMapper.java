package com.bqx.community.mapper;

import com.bqx.community.pojo.User;
import com.bqx.community.utils.ResultModel;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {

    ResultModel<User> getUser(String userId);
}
