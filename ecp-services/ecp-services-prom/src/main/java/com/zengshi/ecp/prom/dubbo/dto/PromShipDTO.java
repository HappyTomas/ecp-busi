package com.zengshi.ecp.prom.dubbo.dto;

import com.zengshi.ecp.server.front.dto.BaseInfo;

/**
 * Title: AI-ECP <br>
 * Description: <br>
 * Date: 2015-10-31 <br>
 * Copyright (c) 2015 AI <br>
 * 
 * @author huangjx
 */
public class PromShipDTO extends BaseInfo {

    private static final long serialVersionUID = 1L;
    
    private Long money;//金额 必填
    
    private String countryCode;//国家编码（有值就传，否则为空）
    
    private String provinceCode;//省份编码（有值就传，否则为空）
    
    private String cityCode;//地市编码（有值就传，否则为空）
    
    private Long shopId;//必填

   // 分类 商品 单品  列表  暂时不用配置。 按照最简单的机制实现。
   // 站点编码 不能为空 baseInfo.currensiteid
    
    public Long getMoney() {
        return money;
    }

    public void setMoney(Long money) {
        this.money = money;
    }

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getProvinceCode() {
        return provinceCode;
    }

    public void setProvinceCode(String provinceCode) {
        this.provinceCode = provinceCode;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }
}
