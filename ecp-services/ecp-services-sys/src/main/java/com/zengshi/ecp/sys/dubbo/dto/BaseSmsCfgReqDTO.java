/** 
 * Project Name:ecp-services-sys 
 * File Name:BaseSmsCfgReqDTO.java 
 * Package Name:com.zengshi.ecp.sys.dubbo.dto 
 * Date:2016年3月19日下午5:56:28 
 * Copyright (c) 2016, ZengShi All Rights Reserved. 
 * 
 */ 
package com.zengshi.ecp.sys.dubbo.dto;

import java.util.Map;

import com.zengshi.ecp.server.front.dto.BaseInfo;

/**
 * Title: ECP <br>
 * Project Name:ecp-services-sys <br>
 * Description: <br>
 * Date:2016年3月19日下午5:56:28  <br>
 * Copyright (c) 2016 ZengShi All Rights Reserved <br>
 * 
 * @author yugn
 * @version  
 * @since JDK 1.6 
 */
public class BaseSmsCfgReqDTO extends BaseInfo{
    
    /** 
     * serialVersionUID:TODO(用一句话描述这个变量表示什么). 
     * @since JDK 1.6 
     */ 
    private static final long serialVersionUID = 1659389770754782424L;

    private String gateway;
    
    private String url;
    
    private String account;
    
    private String authKey;
    
    private Map<String, String> othParams;

    public String getGateway() {
        return gateway;
    }

    public void setGateway(String gateway) {
        this.gateway = gateway;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getAuthKey() {
        return authKey;
    }

    public void setAuthKey(String authKey) {
        this.authKey = authKey;
    }

    public Map<String, String> getOthParams() {
        return othParams;
    }

    public void setOthParams(Map<String, String> othParams) {
        this.othParams = othParams;
    }
    
    
}

