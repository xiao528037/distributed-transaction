package com.xiao.cloud.cloudcommon.common;


import lombok.Data;

import java.io.Serializable;

/**
 * @author aloneMan
 * @projectName spring-cloud-learn-02
 * @createTime 2022-10-13 21:38:50
 * @description
 */
@Data
public class CommonResult<T> implements Serializable {

    private Long code;

    private String message;

    private T data;

    public CommonResult() {
    }

    public CommonResult(Long code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public CommonResult(ResultCode resultCode) {
        this.code = resultCode.getCode();
        this.message = resultCode.getMsg();
    }

    @Override
    public String toString() {
        return "CommonResult{" +
                "code='" + code + '\'' +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }
}
