package com.zengshi.ecp.prom.dubbo.dto;

import java.math.BigDecimal;

/**
 * Title: AI-ECP <br>
 * Description: <br>
 * Date: 2016-01-25 <br>
 * Copyright (c) 2016 AI <br>
 * 
 * @author huangjx
 */
public class OrdSendDiscountDTO extends CommPromTypeDTO {

    private static final long serialVersionUID = 1L;

    private BigDecimal orderMoney;// 满足金额
    
    private BigDecimal sendAmount;// 赠送数量
    
    private String sendType;//赠送类型 0按照固定数值赠送  1按照倍数赠送

    public BigDecimal getOrderMoney() {
        return orderMoney;
    }

    public void setOrderMoney(BigDecimal orderMoney) {
        this.orderMoney = orderMoney;
    }

    public BigDecimal getSendAmount() {
        return sendAmount;
    }

    public void setSendAmount(BigDecimal sendAmount) {
        this.sendAmount = sendAmount;
    }

    public String getSendType() {
        return sendType;
    }

    public void setSendType(String sendType) {
        this.sendType = sendType;
    }

}
