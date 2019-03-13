package com.zengshi.ecp.goods.dubbo.dto;

import com.zengshi.ecp.server.front.dto.BaseInfo;

@SuppressWarnings("rawtypes")
public class AreaCodeDTO extends BaseInfo{
    /** 
     * serialVersionUID:TODO(用一句话描述这个变量表示什么). 
     * @since JDK 1.6 
     */ 
    private static final long serialVersionUID = 1L;

    private String provinceCode;

    private String cityCode;

    private String countryCode;
    
    /**
     * add by gongxq 用于运费模板地区
     */
    private String areaCode; //地区编码
    private Long areaLevel;//地区级别
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

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    
    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public Long getAreaLevel() {
        return areaLevel;
    }

    public void setAreaLevel(Long areaLevel) {
        this.areaLevel = areaLevel;
    }

    
    
}
