package com.zengshi.ecp.goods.dubbo.dto.stock;

import com.zengshi.ecp.server.front.dto.BaseInfo;


public class StockRepAdaptReqDTO extends BaseInfo{

    /** 
     * serialVersionUID:TODO(用一句话描述这个变量表示什么). 
     * @since JDK 1.6 
     */ 
    private static final long serialVersionUID = -3241997990454149402L;

    private Long shopId;

    private Long repCode;

    private String adaptCountry;

    private String adaptProvince;

    private String adaptCity;

    private String status;

    private Long staffId;
    
    private Boolean ifCityLevel;
    
    private Boolean ifCurrentHas;
    //商品录入时，录入的库存量
    private Long count;



    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public Long getRepCode() {
        return repCode;
    }

    public void setRepCode(Long repCode) {
        this.repCode = repCode;
    }

    public String getAdaptCountry() {
        return adaptCountry;
    }

    public void setAdaptCountry(String adaptCountry) {
        this.adaptCountry = adaptCountry == null ? null : adaptCountry.trim();
    }

    public String getAdaptProvince() {
        return adaptProvince;
    }

    public void setAdaptProvince(String adaptProvince) {
        this.adaptProvince = adaptProvince == null ? null : adaptProvince.trim();
    }

    public String getAdaptCity() {
        return adaptCity;
    }

    public void setAdaptCity(String adaptCity) {
        this.adaptCity = adaptCity == null ? null : adaptCity.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

   

    public Long getStaffId() {
        return staffId;
    }

    public void setStaffId(Long staffId) {
        this.staffId = staffId;
    }

    public Boolean getIfCityLevel() {
        return ifCityLevel;
    }

    public void setIfCityLevel(Boolean ifCityLevel) {
        this.ifCityLevel = ifCityLevel;
    }

    public Boolean getIfCurrentHas() {
        return ifCurrentHas;
    }

    public void setIfCurrentHas(Boolean ifCurrentHas) {
        this.ifCurrentHas = ifCurrentHas;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }



   
}

