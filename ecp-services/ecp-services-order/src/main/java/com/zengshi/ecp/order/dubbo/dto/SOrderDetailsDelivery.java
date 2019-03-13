package com.zengshi.ecp.order.dubbo.dto;

import java.io.Serializable;
import java.sql.Timestamp;

public class SOrderDetailsDelivery implements Serializable{

    /** 
     * serialVersionUID:. 
     * @since JDK 1.6 
     */ 
    private static final long serialVersionUID = -6059567285145671773L;
    
    /** 
     * expressId:物流公司编号. 
     * @since JDK 1.6 
     */ 
    private Long expressId;
    
    /** 
     * expressName:物流公司名称. 
     * @since JDK 1.6 
     */ 
    private String expressName;

    /** 
     * expressNo:物流单号. 
     * @since JDK 1.6 
     */ 
    private String expressNo;
    
    /** 
     * deliveryType:送货方式. 
     * @since JDK 1.6 
     */ 
    private String deliveryType;

    /** 
     * sendDate:发货时间. 
     * @since JDK 1.6 
     */ 
    private Timestamp sendDate;
    
    /** 
     * remark:备注. 
     * @since JDK 1.6 
     */ 
    private String remark;

    public String getExpressName() {
        return expressName;
    }

    public void setExpressName(String expressName) {
        this.expressName = expressName;
    }

    public String getDeliveryType() {
        return deliveryType;
    }

    public void setDeliveryType(String deliveryType) {
        this.deliveryType = deliveryType;
    }

    public Timestamp getSendDate() {
        return sendDate;
    }

    public void setSendDate(Timestamp sendDate) {
        this.sendDate = sendDate;
    }

    public Long getExpressId() {
        return expressId;
    }

    public void setExpressId(Long expressId) {
        this.expressId = expressId;
    }

    public String getExpressNo() {
        return expressNo;
    }

    public void setExpressNo(String expressNo) {
        this.expressNo = expressNo;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
    
}

