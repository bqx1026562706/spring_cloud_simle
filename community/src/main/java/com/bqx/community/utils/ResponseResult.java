package com.bqx.community.utils;

public class ResponseResult<T> {

    private Integer status;
    private String msg;
    /**
     * data的总条数，在没有确定data类型时初始化为0显然不太合理。
     */
    private Integer total;
    private T data;

    public static <T> ResponseResult<T> build(Integer status, String msg, Integer total, T data) {
        return new ResponseResult<>(status, msg, total, data);
    }


    public static <T> ResponseResult<T> build(Integer status, String msg) {
        return new ResponseResult<>(status, msg);
    }


    public static <T> ResponseResult<T> build(ResultStatusEnum resultStatusEnum, String msg, Integer total, T data) {
        return new ResponseResult<>(resultStatusEnum, msg, total, data);
    }
    public static <T> ResponseResult<T> build(ResultStatusEnum resultStatusEnum) {
        return new ResponseResult<>(resultStatusEnum);
    }



    public static <T> ResponseResult<T> buildError(ResultStatusEnum resultStatusEnum) {
        return new ResponseResult<>(resultStatusEnum);
    }

    public static <T> ResponseResult<T> buildClient(T data) {
        return new ResponseResult<>(data);
    }

    public static <T> ResponseResult<T> buildServer(Integer total, T data) {
        return new ResponseResult<>(total, data);
    }


    /**
     * 有错误的情况下需要传递错误码 + 错误提示
     *
     * @param status
     * @param msg
     */
    private ResponseResult(Integer status, String msg) {
        this.status = status;
        this.msg = msg;
    }

    private ResponseResult(ResultStatusEnum resultStatusEnum, String msg) {
        this.status = resultStatusEnum.getValue();
        this.msg = msg;
    }

    private ResponseResult(ResultStatusEnum resultStatusEnum) {
        this.status = resultStatusEnum.getValue();
        this.msg = resultStatusEnum.getMsg();
    }

    /**
     * 客户端分页，只需要数据 data
     *
     * @param data
     */
    private ResponseResult(T data) {
        this.status = ResultStatusEnum.SUCCESS.getValue();
        this.msg = msg;
        this.data = data;
    }

    /**
     * 服务端分页，data + total
     *
     * @param total
     * @param data
     */
    private ResponseResult(Integer total, T data) {
        this.status = ResultStatusEnum.SUCCESS.getValue();
        this.msg = msg;
        this.total = total;
        this.data = data;
    }

    private ResponseResult(Integer status, String msg, Integer total, T data) {
        this.status = status;
        this.msg = msg;
        this.total = total;
        this.data = data;
    }

    private ResponseResult(ResultStatusEnum resultStatusEnum, String msg, Integer total, T data) {
        this.status = resultStatusEnum.getValue();
        this.msg = msg;
        this.total = total;
        this.data = data;
    }

    private ResponseResult() {}

}
