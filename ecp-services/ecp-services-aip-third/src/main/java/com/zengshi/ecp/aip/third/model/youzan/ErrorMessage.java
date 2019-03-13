package com.zengshi.ecp.aip.third.model.youzan;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * Created by guojingman on 2017/3/23.
 * 调用有赞发货接口返回的错误消息
 */
public class ErrorMessage {
    @JSONField(name = "error_response")
    private ErrorResponse errorResponse;

    public void setErrorResponse(ErrorResponse errorResponse) {
        this.errorResponse = errorResponse;
    }

    public ErrorResponse getErrorResponse() {
        return errorResponse;
    }

    public static class ErrorResponse {
        private int code;
        private String msg;

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }
    }
}
