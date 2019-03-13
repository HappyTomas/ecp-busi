package com.zengshi.ecp.busi.seller.prom.createprom.vo;

import java.io.Serializable;
/**
 * Title: AI-ECP <br>
 * Description: <br>
 * Date: 2015-8-20 <br>
 * Copyright (c) 2015 AI <br>
 * 
 * @author huangjx
 */
public class PromTypeVO implements Serializable{


    private static final long serialVersionUID = 1L;

    private String promTypeCode;

    private String promTypeName;

    private String nameShort;

    private String promTypeDesc;

    private String promImg;

    private String promClass;

    private String ifShow;

    private String status;

    private String jsonBeanId;

    private String serviceId;
    
    private Long shopId;

    public String getPromTypeCode() {
        return promTypeCode;
    }

    public void setPromTypeCode(String promTypeCode) {
        this.promTypeCode = promTypeCode;
    }

    public String getPromTypeName() {
        return promTypeName;
    }

    public void setPromTypeName(String promTypeName) {
        this.promTypeName = promTypeName;
    }

    public String getNameShort() {
        return nameShort;
    }

    public void setNameShort(String nameShort) {
        this.nameShort = nameShort;
    }

    public String getPromTypeDesc() {
        return promTypeDesc;
    }

    public void setPromTypeDesc(String promTypeDesc) {
        this.promTypeDesc = promTypeDesc;
    }

    public String getPromImg() {
        return promImg;
    }

    public void setPromImg(String promImg) {
        this.promImg = promImg;
    }

    public String getPromClass() {
        return promClass;
    }

    public void setPromClass(String promClass) {
        this.promClass = promClass;
    }

    public String getIfShow() {
        return ifShow;
    }

    public void setIfShow(String ifShow) {
        this.ifShow = ifShow;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getJsonBeanId() {
        return jsonBeanId;
    }

    public void setJsonBeanId(String jsonBeanId) {
        this.jsonBeanId = jsonBeanId;
    }

    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

}
