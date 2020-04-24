package com.bqx.demolambda.controller;

import java.time.LocalDateTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;

public class BqxJDKController {



    interface  Ilambda{
        void test(String name ,int  age);
       default String testReturn(){
           return "默认返回";
       };
    }


}
