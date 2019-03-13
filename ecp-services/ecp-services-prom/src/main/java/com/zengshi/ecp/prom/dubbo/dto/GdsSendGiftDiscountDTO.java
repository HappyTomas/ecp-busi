package com.zengshi.ecp.prom.dubbo.dto;

import java.math.BigDecimal;

public class GdsSendGiftDiscountDTO extends CommPromTypeDTO {

    private static final long serialVersionUID = -855538023584709871L;

    private BigDecimal buyAmount;

    public BigDecimal getBuyAmount() {
        return buyAmount;
    }

    public void setBuyAmount(BigDecimal buyAmount) {
        this.buyAmount = buyAmount;
    }
    
    
}

