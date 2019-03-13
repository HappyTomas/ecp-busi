package com.zengshi.ecp.prom.dubbo.dto;

import java.math.BigDecimal;

/**
 * Title: AI-ECP <br>
 * Description: <br>
 * Date: 2015-8-5 <br>
 * Copyright (c) 2015 AI <br>
 * 
 * @author huangjx
 */
public class BindDTO extends CommPromTypeDTO {

    private static final long serialVersionUID = 1L;

    private BigDecimal bindPrice;

    public BigDecimal getBindPrice() {
        return bindPrice;
    }

    public void setBindPrice(BigDecimal bindPrice) {
        this.bindPrice = bindPrice;
    }

}
