package com.bqx.order.mapper;

import com.bqx.order.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface OrderMapper {
    /**
     *  @Select("select * from t_user where id=#{value}")
     * @param id
     * @return
     */
    User testOrder(String id);
}
