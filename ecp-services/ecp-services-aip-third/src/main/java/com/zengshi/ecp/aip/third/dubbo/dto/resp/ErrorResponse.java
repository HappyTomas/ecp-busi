package com.zengshi.ecp.aip.third.dubbo.dto.resp;

/**
 * Created by cbl on 2017/2/15.
 */
public class ErrorResponse {
    private Long code;
    private String msg;

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
