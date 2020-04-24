package com.bqx.community.controller;

import com.bqx.community.pojo.User;
import com.bqx.community.service.UserService;
import com.bqx.community.utils.ResultModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/user")
public class UserController {

    private final static Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @RequestMapping("/log")
    public String testLog() {
        logger.debug("=====测试日志debug级别打印====");
        logger.info("======测试日志info级别打印=====");
        logger.error("=====测试日志error级别打印====");
        logger.warn("======测试日志warn级别打印=====");
        // 可以使用占位符打印出一些参数信息
        String str1 = "blog.itcodai.com";
        String str2 = "blog.csdn.net/eson_15";
        logger.info("======倪升武的个人博客：{}；倪升武的CSDN博客：{}", str1, str2);
        return "success";
    }

    @RequestMapping("/login")
    public String testLogin() {
        System.out.println("=========================" + "启动成功");
        return "=========================" + "启动成功";
    }

    /**
     * 根据id 找对应使用用户
     * @param userId
     * @return
     */
    @RequestMapping("/findByUserId/{userId}")
    public ResultModel<User> getUser(@PathVariable(value = "userId") String userId) {
        ResultModel<User> user=   userService.getUser(userId);
        return user;
    }



    @RequestMapping("/map")
    public Map<String, Object> getMap() {
        Map<String, Object> map = new HashMap<>(3);
        User user = new User(2, "倪升武", "123456");
        map.put("作者信息", user);
        map.put("博客地址", "55");
        map.put("CSDN地址", "56");
        map.put("粉丝数量", 4153);
        return map;
    }



}
