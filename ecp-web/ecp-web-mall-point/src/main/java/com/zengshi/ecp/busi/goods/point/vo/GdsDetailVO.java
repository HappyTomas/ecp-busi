package com.zengshi.ecp.busi.goods.point.vo;

import java.io.Serializable;

public class GdsDetailVO implements Serializable{
    /** 
     * serialVersionUID:TODO(用一句话描述这个变量表示什么). 
     * @since JDK 1.6 
     */ 
    private static final long serialVersionUID = -4451238182453155158L;
    /**
     * 商品编码
     */
    private Long gdsId;
    /**
     * 单品编码
     */
    private Long skuId;
    /**
     * 企业编码
     */
    private Long companyId;
    /**
     * 国家编码
     */
    private String countryCode;
    
    /**
     * 省份编码
     */
    private String provinceCode;

    /**
     * 地市编码
     */
    private String cityCode;
    
    /**
     * 根据单品规格属性id（不是规格属性值id） 和属性值获取对应单品信息
     */
    private String skuPropParam;
    
    /**
     *店铺id 
     */
    private Long shopId;
    public Long getGdsId() {
        return gdsId;
    }
    public void setGdsId(Long gdsId) {
        this.gdsId = gdsId;
    }
    public Long getSkuId() {
        return skuId;
    }
    public void setSkuId(Long skuId) {
        this.skuId = skuId;
    }
    public Long getCompanyId() {
        return companyId;
    }
    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
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
    public String getSkuPropParam() {
        return skuPropParam;
    }
    public void setSkuPropParam(String skuPropParam) {
        this.skuPropParam = skuPropParam;
    }
    public Long getShopId() {
        return shopId;
    }
    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }
    
}

