package com.bqx.community.mapper;

import com.bqx.community.pojo.ExcleImportCityRequest;
import com.bqx.community.pojo.ExcleImportInfo;
import com.bqx.community.pojo.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface UserMapper {
/*    void insertUserExcle(List<ExcleImportInfo> list);

    void impltreadCityExcel(List<ExcleImportCityRequest> list);*/

    List<Map> getRpoCityList();

    //ResultModel<User> getUser(String userId);
}
