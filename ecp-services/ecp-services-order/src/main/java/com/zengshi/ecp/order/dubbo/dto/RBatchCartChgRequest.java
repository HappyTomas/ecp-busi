package com.zengshi.ecp.order.dubbo.dto;

import java.util.List;

import com.zengshi.ecp.server.front.dto.BaseInfo;

public class RBatchCartChgRequest extends BaseInfo {

    /** 
     * serialVersionUID:. 
     * @since JDK 1.6 
     */ 
    private static final long serialVersionUID = 6376629997712688684L;
    
    /** 
     * source:终端来源. 
     * @since JDK 1.6 
     */ 
    private String source;
    
    /** 
     * countryCode:国家代码. 
     * @since JDK 1.6 
     */ 
    private String countryCode;
    
    /** 
     * provinceCode:省份代码. 
     * @since JDK 1.6 
     */ 
    private String provinceCode;
    
    /** 
     * cityCode:地市代码. 
     * @since JDK 1.6 
     */ 
    private String cityCode;
    
    
    /** 
     * rOrdCartItemRequests:删除的明细ID列表. 
     * @since JDK 1.6 
     */ 
    List<ROrdCartItemRequest> rOrdCartItemRequests;
    
    /** 
     * rBatchCartsRequests:店铺ID列表. 
     * @since JDK 1.6 
     */ 
    List<RBatchCartsRequest> rBatchCartsRequests;
    
    /** 
     * rBatchCartItemsRequests:勾选的明细ID列表. 
     * @since JDK 1.6 
     */ 
    List<RBatchCartItemsRequest> rBatchCartItemsRequests;

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
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

    public List<ROrdCartItemRequest> getrOrdCartItemRequests() {
        return rOrdCartItemRequests;
    }

    public void setrOrdCartItemRequests(List<ROrdCartItemRequest> rOrdCartItemRequests) {
        this.rOrdCartItemRequests = rOrdCartItemRequests;
    }

    public List<RBatchCartsRequest> getrBatchCartsRequests() {
        return rBatchCartsRequests;
    }

    public void setrBatchCartsRequests(List<RBatchCartsRequest> rBatchCartsRequests) {
        this.rBatchCartsRequests = rBatchCartsRequests;
    }

    public List<RBatchCartItemsRequest> getrBatchCartItemsRequests() {
        return rBatchCartItemsRequests;
    }

    public void setrBatchCartItemsRequests(List<RBatchCartItemsRequest> rBatchCartItemsRequests) {
        this.rBatchCartItemsRequests = rBatchCartItemsRequests;
    }
    
    

}

