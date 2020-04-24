package com.bqx.order.controller;

import com.bqx.order.pojo.User;
import com.bqx.order.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/order")
public class OrderController {
    private final static Logger logger = LoggerFactory.getLogger(OrderController.class);


    @Autowired
    private OrderService orderService;

    @RequestMapping("/testOrder/{id}")
    @ResponseBody
    public User testOrder(@PathVariable(value = "id") String id){
        System.out.println("========================"+"启动");
       User user= orderService.testOrder(id);
        logger.info("user:{}"+user);
        return  user;
    }

    @RequestMapping("/testOrder")
    @ResponseBody
    public String testOrder(){
        System.out.println("============================"+"启动更改git默认账号");

        return  "============================"+"启动";
    }


}
