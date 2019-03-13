package com.zengshi.ecp.order.dubbo.dto;


import java.util.List;

import com.zengshi.ecp.server.front.dto.BaseInfo;

public class ROrdCartsRequest extends BaseInfo {
    //买家id
    private Long staffId;
    
    //来源
    private String source;
    
    //购物车实例列表
    private List<ROrdCartRequest> ordCartList;
    
    //订单收货地址信息----提交订单时候用
    private ROrdDeliveAddrRequest rOrdDeliveAddrRequest;
    
    //支付方式---提交订单时候使用、
    private String payType;


    private static final long serialVersionUID = 1L;


    public Long getStaffId() {
        return staffId;
    }


    public void setStaffId(Long staffId) {
        this.staffId = staffId;
    }


    public List<ROrdCartRequest> getOrdCartList() {
        return ordCartList;
    }


    public void setOrdCartList(List<ROrdCartRequest> ordCartList) {
        this.ordCartList = ordCartList;
    }


    public ROrdDeliveAddrRequest getrOrdDeliveAddrRequest() {
        return rOrdDeliveAddrRequest;
    }


    public void setrOrdDeliveAddrRequest(ROrdDeliveAddrRequest rOrdDeliveAddrRequest) {
        this.rOrdDeliveAddrRequest = rOrdDeliveAddrRequest;
    }


    public String getPayType() {
        return payType;
    }


    public void setPayType(String payType) {
        this.payType = payType;
    }


    public String getSource() {
        return source;
    }


    public void setSource(String source) {
        this.source = source;
    }

}

