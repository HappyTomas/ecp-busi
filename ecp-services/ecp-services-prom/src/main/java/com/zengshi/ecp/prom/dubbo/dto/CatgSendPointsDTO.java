package com.zengshi.ecp.prom.dubbo.dto;

import java.math.BigDecimal;
import java.util.Map;

/**
 * Title: AI-ECP <br>
 * Description: <br>
 * Date: 2015-9-7 <br>
 * Copyright (c) 2015 AI <br>
 * 
 * @author huangjx
 */
public class CatgSendPointsDTO extends CommPromTypeDTO {
    private static final long serialVersionUID = 1L;
    private BigDecimal discountRate;//折扣比例
    private String sendType;//赠送类型 0按照固定数值赠送  1按照倍数赠送
    private Map<String,String> custLevelMap;//key 客户等级  value 赠送倍数或者赠送值
    public String getSendType() {
        return sendType;
    }
    public void setSendType(String sendType) {
        this.sendType = sendType;
    }
    public Map<String,String> getCustLevelMap() {
        return custLevelMap;
    }
    public void setCustLevelMap(Map<String,String> custLevelMap) {
        this.custLevelMap = custLevelMap;
    }
    public BigDecimal getDiscountRate() {
        return discountRate;
    }
    public void setDiscountRate(BigDecimal discountRate) {
        this.discountRate = discountRate;
    }
}
