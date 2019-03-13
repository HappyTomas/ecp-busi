package com.zengshi.ecp.prom.dubbo.dto;

/**
 * Title: AI-ECP <br>
 * Description: <br>
 * Date: 2015-10-1 <br>
 * Copyright (c) 2015 AI <br>
 * 
 * @author huangjx
 */
public class CartPromItemDTO extends CartPromDTO {

    private static final long serialVersionUID = 1L;
   
    private Long ordPromId;//关联订单促销id

    public Long getOrdPromId() {
        return ordPromId;
    }

    public void setOrdPromId(Long ordPromId) {
        this.ordPromId = ordPromId;
    }
}
