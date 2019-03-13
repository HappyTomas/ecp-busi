package com.zengshi.ecp.app.resp;

import com.zengshi.butterfly.app.model.IBody;

public class Ord007Resp extends IBody {

    /** 
     * cartPromBeanRespDTO:促销返回信息. 
     * @since JDK 1.6 
     */ 
    private boolean success;

    private String message;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

