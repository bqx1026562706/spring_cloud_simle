package com.bqx.community.pojo;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;

public class ExcleImportInfo extends BaseRowModel {

    @ExcelProperty(index = 0)
    private String name;

    @ExcelProperty(index = 1)
    private String age;

    @ExcelProperty(index = 2)
    private String email;

    @ExcelProperty(index = 3)
    private String phone;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    /*
            作为 excel 的模型映射，需要 setter 方法
         */
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}