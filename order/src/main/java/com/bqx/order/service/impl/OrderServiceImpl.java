package com.bqx.order.service.impl;

import com.bqx.order.mapper.OrderMapper;
import com.bqx.order.pojo.User;
import com.bqx.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMapper orderMapper;

    @Override
    public User testOrder(String id) {
        User user= orderMapper.testOrder(id);
        return user;
    }
}
