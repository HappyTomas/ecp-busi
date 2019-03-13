package com.zengshi.ecp.app.resp;

import com.zengshi.butterfly.app.model.IBody;

public class Pay105Resp extends IBody {
    /** 
     * status:状态. 
     * @since JDK 1.6 
     */ 
    private boolean status;
    /** 
     * msg:状态说明. 
     * @since JDK 1.6 
     */ 
    private String msg;
    public boolean isStatus() {
        return status;
    }
    public void setStatus(boolean status) {
        this.status = status;
    }
    public String getMsg() {
        return msg;
    }
    public void setMsg(String msg) {
        this.msg = msg;
    }
    
}

