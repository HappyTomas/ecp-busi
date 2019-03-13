/** 
 * Project Name:ecp-services-sys 
 * File Name:BaseExpressReqDTO.java 
 * Package Name:com.zengshi.ecp.sys.dubbo.dto 
 * Date:2015-9-2上午10:19:29 
 * Copyright (c) 2015, ZengShi All Rights Reserved. 
 * 
 */ 
package com.zengshi.ecp.sys.dubbo.dto;

import com.zengshi.ecp.server.front.dto.BaseInfo;

/**
 * Title: ECP <br>
 * Project Name:ecp-services-sys <br>
 * Description: <br>
 * Date:2015-9-2上午10:19:29  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author yugn
 * @version  
 * @since JDK 1.6 
 */
public class BaseExpressReqDTO extends BaseInfo {

    /** 
     * @since JDK 1.6 
     */ 
    private static final long serialVersionUID = 4159831844565193442L;
    
    private long id;
    
    private String status;
    
    private String expressFullName;
    
    private String expressName;
    
    private String expressWebsite;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getExpressFullName() {
        return expressFullName;
    }

    public void setExpressFullName(String expressFullName) {
        this.expressFullName = expressFullName;
    }

    public String getExpressName() {
        return expressName;
    }

    public void setExpressName(String expressName) {
        this.expressName = expressName;
    }

    public String getExpressWebsite() {
        return expressWebsite;
    }

    public void setExpressWebsite(String expressWebsite) {
        this.expressWebsite = expressWebsite;
    }
    
}

