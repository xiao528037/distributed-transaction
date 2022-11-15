package com.xiao.cloud.cloudcommon.common;

import lombok.Data;


/**
 * @author aloneMan
 * @projectName spring-cloud-learn-02
 * @createTime 2022-10-13 21:48:33
 * @description
 */


public enum ResultCode {

    //成功
    SUCCESS(0x10000L, "处理成功"),
    FAIL(0x10001L, "处理失败");

    private Long code;
    private String msg;

    ResultCode(Long code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Long getCode() {
        return code;
    }

    public void setCode(Long code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
