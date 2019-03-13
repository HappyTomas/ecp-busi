package com.zengshi.ecp.aip.dubbo.dto;

import com.zengshi.ecp.server.front.dto.BaseInfo;

/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-services-aip-server <br>
 * Description: 农行对账响应类<br>
 * Date:2015年12月4日下午8:23:23 <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author weijq
 * @version
 * @since JDK 1.6
 */
public class ABCPayQueryOrderRequest extends BaseInfo {

    /**
     * serialVersionUID:TODO(用一句话描述这个变量表示什么).
     * 
     * @since JDK 1.6
     */
    private static final long serialVersionUID = -7005885221321742637L;

    String payTypeID;

    String queryTpye;
    
    String OrderNo;
    
    private boolean connectionFlag;

    private String merchantID;
    
    public String getPayTypeID() {
        return payTypeID;
    }

    public void setPayTypeID(String payTypeID) {
        this.payTypeID = payTypeID;
    }

    public String getQueryTpye() {
        return queryTpye;
    }

    public void setQueryTpye(String queryTpye) {
        this.queryTpye = queryTpye;
    }

    public String getOrderNo() {
        return OrderNo;
    }

    public void setOrderNo(String orderNo) {
        OrderNo = orderNo;
    }

    public boolean getConnectionFlag() {
        return connectionFlag;
    }

    public void setConnectionFlag(boolean connectionFlag) {
        this.connectionFlag = connectionFlag;
    }

    public String getMerchantID() {
        return merchantID;
    }

    public void setMerchantID(String merchantID) {
        this.merchantID = merchantID;
    }

}
