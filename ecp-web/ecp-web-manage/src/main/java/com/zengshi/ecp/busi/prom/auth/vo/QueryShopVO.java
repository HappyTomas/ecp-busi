package com.zengshi.ecp.busi.prom.auth.vo;

import com.zengshi.ecp.base.vo.EcpBasePageReqVO;

/**
 * Title: AI-ECP <br>
 * Description: <br>
 * Date: 2015-8-19 <br>
 * Copyright (c) 2015 AI <br>
 * 
 * @author huangjx
 */
public class QueryShopVO extends EcpBasePageReqVO{

    private static final long serialVersionUID = 1L;

    private Long shopId;

    private String shopName;

    private String status;

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    } 
 
}
