package com.zengshi.ecp.busi.seller.staff.vo;

import java.io.Serializable;

import com.zengshi.ecp.base.vo.EcpBaseResponseVO;

@SuppressWarnings("serial")
public class ShopVipRuleVO extends EcpBaseResponseVO implements Serializable{
    
    private Long shopId;
    
    private String vipLevelCode;
    
    private String vipLevelName;
    
    private String discount;
    
    private String orderPay;
    
    private String tradesNum;

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public String getVipLevelCode() {
        return vipLevelCode;
    }

    public void setVipLevelCode(String vipLevelCode) {
        this.vipLevelCode = vipLevelCode;
    }

    public String getVipLevelName() {
        return vipLevelName;
    }

    public void setVipLevelName(String vipLevelName) {
        this.vipLevelName = vipLevelName;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public String getOrderPay() {
        return orderPay;
    }

    public void setOrderPay(String orderPay) {
        this.orderPay = orderPay;
    }

    public String getTradesNum() {
        return tradesNum;
    }

    public void setTradesNum(String tradesNum) {
        this.tradesNum = tradesNum;
    }

    
   
}

