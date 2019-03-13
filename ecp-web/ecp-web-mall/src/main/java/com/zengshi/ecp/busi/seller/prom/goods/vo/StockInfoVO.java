package com.zengshi.ecp.busi.seller.prom.goods.vo;

import java.io.Serializable;

/**
 * Title: AI-ECP <br>
 * Description: <br>
 * Date: 2015-10-09 <br>
 * Copyright (c) 2015 AI <br>
 * 
 * @author huangjx
 */
public class StockInfoVO implements Serializable{

    private static final long serialVersionUID = 9197036868497822424L;

    private String adaptProvince;
    
    private String adaptCity;
    
    private String adaptCountry;
    
    private Long availCount;
    
    private String repCode;

    private String repType;
    
    private long skuId;
    
    private Long stockId;

    public String getAdaptProvince() {
        return adaptProvince;
    }

    public void setAdaptProvince(String adaptProvince) {
        this.adaptProvince = adaptProvince;
    }

    public String getAdaptCity() {
        return adaptCity;
    }

    public void setAdaptCity(String adaptCity) {
        this.adaptCity = adaptCity;
    }

    public String getAdaptCountry() {
        return adaptCountry;
    }

    public void setAdaptCountry(String adaptCountry) {
        this.adaptCountry = adaptCountry;
    }

    public Long getAvailCount() {
        return availCount;
    }

    public void setAvailCount(Long availCount) {
        this.availCount = availCount;
    }

    public String getRepCode() {
        return repCode;
    }

    public void setRepCode(String repCode) {
        this.repCode = repCode;
    }


    public String getRepType() {
        return repType;
    }

    public void setRepType(String repType) {
        this.repType = repType;
    }

    public long getSkuId() {
        return skuId;
    }

    public void setSkuId(long skuId) {
        this.skuId = skuId;
    }

    public Long getStockId() {
        return stockId;
    }

    public void setStockId(Long stockId) {
        this.stockId = stockId;
    }
  
}
