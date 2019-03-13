package com.zengshi.ecp.order.dubbo.dto;

import com.zengshi.ecp.general.order.dto.ROrdCartChangeRequest;
import com.zengshi.ecp.server.front.dto.BaseInfo;

public class ROrdCartChgRequest extends BaseInfo {

    /** 
     * serialVersionUID:. 
     * @since JDK 1.6 
     */ 
    private static final long serialVersionUID = -158786092821153255L;
    
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
     * rOrdCartItemRequest:修改相关信息保存的对象. 
     * @since JDK 1.6 
     */ 
    private ROrdCartItemRequest  rOrdCartItemRequest;
    
    /** 
     * rOrdCartChangeRequest:购物车信息修改,组装请求促销域的入参对象. 
     * @since JDK 1.6 
     */ 
    private ROrdCartChangeRequest rOrdCartChangeRequest;
    
    public ROrdCartItemRequest getrOrdCartItemRequest() {
        return rOrdCartItemRequest;
    }

    public void setrOrdCartItemRequest(ROrdCartItemRequest rOrdCartItemRequest) {
        this.rOrdCartItemRequest = rOrdCartItemRequest;
    }

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

    public ROrdCartChangeRequest getrOrdCartChangeRequest() {
        return rOrdCartChangeRequest;
    }

    public void setrOrdCartChangeRequest(ROrdCartChangeRequest rOrdCartChangeRequest) {
        this.rOrdCartChangeRequest = rOrdCartChangeRequest;
    }
    
    

}

