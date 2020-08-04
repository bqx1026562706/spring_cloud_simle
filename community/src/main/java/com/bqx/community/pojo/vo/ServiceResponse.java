package com.bqx.community.pojo.vo;

/**
 * 服务输出响应
 */
public class ServiceResponse {

    /**
     * 操作是否成功
     */
    private boolean isSuccess;

    /**
     * 返回信息
     */
    private Object result;

    /**
     * 成功实例
     */
    private static final ServiceResponse SUCCESS_INSTANCE = new ServiceResponse(true);

    /**
     * 失败实例
     */
    private static final ServiceResponse FAIL_INSTANCE = new ServiceResponse(false);

    private ServiceResponse(boolean isSuccess) {
        this.isSuccess = isSuccess;
    }

    public boolean isSuccess() {
        return isSuccess;
    }

    public ServiceResponse setSuccess(boolean success) {
        isSuccess = success;
        return this;
    }

    public Object getResult() {
        return result;
    }

    public ServiceResponse setResult(Object result) {
        this.result = result;
        return this;
    }

    /**
     * 成功
     *
     * @return 返回结果
     */
    public static ServiceResponse ok() {
        return SUCCESS_INSTANCE.setResult(null);
    }

    /**
     * 成功 - 带信息
     *
     * @param result 返回信息
     * @return 返回结果
     */
    public static ServiceResponse ok(Object result) {
        return SUCCESS_INSTANCE.setResult(result);
    }

    /**
     * 失败
     *
     * @return 返回结果
     */
    public static ServiceResponse fail() {
        return FAIL_INSTANCE.setResult(null);
    }

    /**
     * 失败 - 带信息
     *
     * @param result 返回信息
     * @return 返回结果
     */
    public static ServiceResponse fail(Object result) {
        return FAIL_INSTANCE.setResult(result);
    }
}
