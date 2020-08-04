package com.bqx.community.utils;

public enum ResultStatusEnum {

    /**
     * 广义通用
     */
    SUCCESS(1, "请求成功"),
    ERROR(-1, "错误"),
    NOT_FOUND(-404, "没有找到"),
    NOT_EMPTY(-200, "字段不能为空"),
    DUPLICATE_EXCEPTION(-3, "重复异常"),

    /**
     * 规约：时间异常类 value定义区间为 -100 ~ -199
     */
    TIME_EMPTY(-100, "时间不能为空"),
    TIME_MORE_THAN_14_DAYS(-101, "时间跨度不能超过两个星期"),

    /**
     * 规约：字段为空 value区间 -200 ~ -299
     */
    OPENID_EMPTY(-201, "openid不能为空");
    private int value;

    private String msg;

    ResultStatusEnum(int value, String msg) {
        this.value = value;
        this.msg = msg;
    }

    public int getValue() {
        return value;
    }

    public String getMsg() {
        return msg;
    }
}

