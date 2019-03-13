package com.zengshi.ecp.prom.dubbo.dto;

import java.math.BigDecimal;

/**
 * Title: AI-ECP <br>
 * Description: <br>
 * Date: 2015-8-5 <br>
 * Copyright (c) 2015 AI <br>
 * 
 * @author huangjx
 * @param <T>
 */
public class BuyXSendYDTO extends CommPromTypeDTO {

    private static final long serialVersionUID = 1L;

    private BigDecimal buyX;

    private BigDecimal sendY;

    public BigDecimal getBuyX() {
        return buyX;
    }

    public void setBuyX(BigDecimal buyX) {
        this.buyX = buyX;
    }

    public BigDecimal getSendY() {
        return sendY;
    }

    public void setSendY(BigDecimal sendY) {
        this.sendY = sendY;
    }

}
