package com.zengshi.ecp.aip.third.dubbo.dto.resp;

/**
 * Created by cbl on 2017/2/21.
 */
public class YZGdsResp {
    private ErrorResponse error_response;
    private GdsResponse response;

    public ErrorResponse getError_response() {
        return error_response;
    }

    public void setError_response(ErrorResponse error_response) {
        this.error_response = error_response;
    }

    public GdsResponse getResponse() {
        return response;
    }

    public void setResponse(GdsResponse response) {
        this.response = response;
    }
}
