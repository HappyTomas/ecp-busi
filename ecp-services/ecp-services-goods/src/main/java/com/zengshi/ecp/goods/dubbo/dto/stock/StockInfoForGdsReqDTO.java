package com.zengshi.ecp.goods.dubbo.dto.stock;

import com.zengshi.ecp.server.front.dto.BaseInfo;

public class StockInfoForGdsReqDTO extends BaseInfo {

    /**
     * serialVersionUID:TODO(用一句话描述这个变量表示什么).
     * 
     * @since JDK 1.6
     */
    private static final long serialVersionUID = 6095918987572992182L;

    // private String repType;

    private Long gdsId;

    private Long shopId;

    private Long skuId;

    private String adaptCountry;

    private String adaptProvince;

    private String adaptCity;
    
    private Long typeId;

 

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public Long getSkuId() {
        return skuId;
    }

    public void setSkuId(Long skuId) {
        this.skuId = skuId;
    }

    public String getAdaptCountry() {
        return adaptCountry;
    }

    public void setAdaptCountry(String adaptCountry) {
        this.adaptCountry = adaptCountry;
    }

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

    public Long getGdsId() {
        return gdsId;
    }

    public void setGdsId(Long gdsId) {
        this.gdsId = gdsId;
    }

    public Long getTypeId() {
        return typeId;
    }

    public void setTypeId(Long typeId) {
        this.typeId = typeId;
    }

  
}
