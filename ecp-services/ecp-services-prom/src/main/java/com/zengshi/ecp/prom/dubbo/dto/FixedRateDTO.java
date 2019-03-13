package com.zengshi.ecp.prom.dubbo.dto;

import java.math.BigDecimal;

/**
 * Title: AI-ECP <br>
 * Description: <br>
 * Date: 2015-9-1 <br>
 * Copyright (c) 2015 AI <br>
 * 
 * @author huangjx
 */
public class FixedRateDTO extends CommPromTypeDTO {

    private static final long serialVersionUID = 1L;

    private BigDecimal orderMoney;// 满足金额
    
    private BigDecimal discountRate;

    public BigDecimal getDiscountRate() {
        return discountRate;
    }

    public void setDiscountRate(BigDecimal discountRate) {
        this.discountRate = discountRate;
    }

    public BigDecimal getOrderMoney() {
        return orderMoney;
    }

    public void setOrderMoney(BigDecimal orderMoney) {
        this.orderMoney = orderMoney;
    }
 
}
