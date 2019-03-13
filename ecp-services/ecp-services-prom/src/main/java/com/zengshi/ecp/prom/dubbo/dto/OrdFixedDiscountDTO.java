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
public class OrdFixedDiscountDTO extends CommPromTypeDTO {

    private static final long serialVersionUID = 1L;

    private BigDecimal orderMoney;// 满足金额
    
    private BigDecimal fixedMoney;//固定优惠金额

    public BigDecimal getOrderMoney() {
        return orderMoney;
    }

    public void setOrderMoney(BigDecimal orderMoney) {
        this.orderMoney = orderMoney;
    }

    public BigDecimal getFixedMoney() {
        return fixedMoney;
    }

    public void setFixedMoney(BigDecimal fixedMoney) {
        this.fixedMoney = fixedMoney;
    }
 
}
