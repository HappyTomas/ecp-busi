package com.zengshi.ecp.general.order.dto;

import com.zengshi.ecp.server.front.dto.BaseResponseDTO;

public class ROrdCartsChkResponse extends BaseResponseDTO {

    /** 
     * serialVersionUID:. 
     * @since JDK 1.6 
     */ 
    private static final long serialVersionUID = 824746612549803086L;

    /** 
     * orderType:订单种类. 
     * @since JDK 1.6 
     */ 
    private boolean status;
    
    private String  msg;

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

