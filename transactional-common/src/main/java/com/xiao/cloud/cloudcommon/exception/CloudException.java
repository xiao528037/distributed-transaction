package com.xiao.cloud.cloudcommon.exception;


import com.xiao.cloud.cloudcommon.common.ResultCode;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author aloneMan
 * @projectName spring-cloud-learn-02
 * @createTime 2022-10-13 21:47:37
 * @description
 */
@NoArgsConstructor
@ToString
@Data
public class CloudException extends Exception {

    private Long code;
    private String msg;

    private ResultCode resultCode;

    public CloudException(ResultCode resultCode) {
        super(resultCode.getMsg());
        this.code = resultCode.getCode();
        this.msg = resultCode.getMsg();
        this.resultCode = resultCode;
    }

    public CloudException(Long code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}

