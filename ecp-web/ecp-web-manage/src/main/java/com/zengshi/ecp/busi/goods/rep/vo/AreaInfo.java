package com.zengshi.ecp.busi.goods.rep.vo;

import java.io.Serializable;

public class AreaInfo implements Serializable{

    /** 
     * serialVersionUID:TODO(用一句话描述这个变量表示什么). 
     * @since JDK 1.6 
     */ 
    private static final long serialVersionUID = -7716388629004747202L;

    private String countryCode;

    private String cityCode;

    private String provinceCode;
    //判断当前店铺是否覆盖该省份
    private Boolean ifHasOver;
    
    private Boolean ifCityLevel;
    //判断当前仓库是否覆盖该省份
    private Boolean ifCRepHasOver;
    //当前地区的名称
    private String areaName;
    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public String getProvinceCode() {
        return provinceCode;
    }

    public void setProvinceCode(String provinceCode) {
        this.provinceCode = provinceCode;
    }

    public Boolean getIfHasOver() {
        return ifHasOver;
    }

    public void setIfHasOver(Boolean ifHasOver) {
        this.ifHasOver = ifHasOver;
    }

    public Boolean getIfCityLevel() {
        return ifCityLevel;
    }

    public void setIfCityLevel(Boolean ifCityLevel) {
        this.ifCityLevel = ifCityLevel;
    }

    public Boolean getIfCRepHasOver() {
        return ifCRepHasOver;
    }

    public void setIfCRepHasOver(Boolean ifCRepHasOver) {
        this.ifCRepHasOver = ifCRepHasOver;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }
}

