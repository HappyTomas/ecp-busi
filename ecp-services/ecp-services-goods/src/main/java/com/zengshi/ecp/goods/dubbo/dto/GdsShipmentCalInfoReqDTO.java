package com.zengshi.ecp.goods.dubbo.dto;

import java.util.ArrayList;
import java.util.List;

import com.zengshi.ecp.server.front.dto.BaseInfo;

@SuppressWarnings("rawtypes")
public class GdsShipmentCalInfoReqDTO extends BaseInfo{

    /** 
     * serialVersionUID:TODO(用一句话描述这个变量表示什么). 
     * @since JDK 1.6 
     */ 
    private static final long serialVersionUID = -3704296745458528659L;

    private List<GdsInfoShipmentReqDTO> gdsInfos = new ArrayList<GdsInfoShipmentReqDTO>();
    
    private Long shopId;
    
    private String countryCode;
    
    private String provinceCode;
    
    private String cityCode;
    //购买店铺商品折算后的总金额
    private Long amount;

    private List<Long> promIds;

    public List<GdsInfoShipmentReqDTO> getGdsInfos() {
        return gdsInfos;
    }

    public void setGdsInfos(List<GdsInfoShipmentReqDTO> gdsInfos) {
        this.gdsInfos = gdsInfos;
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

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public List<Long> getPromIds() {
        return promIds;
    }

    public void setPromIds(List<Long> promIds) {
        this.promIds = promIds;
    }
}

