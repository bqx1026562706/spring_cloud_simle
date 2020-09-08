package com.bqx.community.pojo;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;

public class ExcleImportCityRequest extends BaseRowModel {

    @ExcelProperty(index = 0)
    private Integer id;

    @ExcelProperty(index = 1)
    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
