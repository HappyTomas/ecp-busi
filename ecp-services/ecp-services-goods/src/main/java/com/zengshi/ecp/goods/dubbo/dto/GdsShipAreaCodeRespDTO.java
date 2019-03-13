package com.zengshi.ecp.goods.dubbo.dto;

import java.util.List;

import com.zengshi.ecp.server.front.dto.BaseInfo;

@SuppressWarnings("rawtypes")
public class GdsShipAreaCodeRespDTO extends BaseInfo{

    /** 
     * serialVersionUID:TODO(用一句话描述这个变量表示什么). 
     * @since JDK 1.6 
     */ 
    private static final long serialVersionUID = 5263152110256886810L;
    
    private String countryCode;//国家
    
    private String provinceCode;//省份
    
    private String cityCode;//城市
    
    private String countyCode;//区县
    
    private String countryName;//国家名称
    
    private String provinceName;//省份名称
    
    private String cityName;//地市名称
    
    private String countyName;//区县名称
    
    private Long areaLevel;//地区等级

    private List<GdsShipAreaCodeRespDTO> cityCodeList;
    
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

    public String getCountyCode() {
        return countyCode;
    }

    public void setCountyCode(String countyCode) {
        this.countyCode = countyCode;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public List<GdsShipAreaCodeRespDTO> getCityCodeList() {
        return cityCodeList;
    }

    public void setCityCodeList(List<GdsShipAreaCodeRespDTO> cityCodeList) {
        this.cityCodeList = cityCodeList;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getCountyName() {
        return countyName;
    }

    public void setCountyName(String countyName) {
        this.countyName = countyName;
    }

    public Long getAreaLevel() {
        return areaLevel;
    }

    public void setAreaLevel(Long areaLevel) {
        this.areaLevel = areaLevel;
    }
    
    
    
}

