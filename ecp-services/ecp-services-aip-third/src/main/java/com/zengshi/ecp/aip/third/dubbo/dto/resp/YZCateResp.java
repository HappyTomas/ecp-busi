package com.zengshi.ecp.aip.third.dubbo.dto.resp;

import java.util.List;

/**
 * Created by cbl on 2017/2/15.
 */
public class YZCateResp {
    private ErrorResponse error_response;
    private SuccResponse response;

    public ErrorResponse getError_response() {
        return error_response;
    }

    public void setError_response(ErrorResponse error_response) {
        this.error_response = error_response;
    }

    public SuccResponse getResponse() {
        return response;
    }

    public void setResponse(SuccResponse response) {
        this.response = response;
    }
}
