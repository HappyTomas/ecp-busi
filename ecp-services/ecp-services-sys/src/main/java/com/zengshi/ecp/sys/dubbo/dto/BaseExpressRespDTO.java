/** 
 * Project Name:ecp-services-sys 
 * File Name:BaseExpressRespDTO.java 
 * Package Name:com.zengshi.ecp.sys.dubbo.dto 
 * Date:2015-9-2上午10:16:06 
 * Copyright (c) 2015, ZengShi All Rights Reserved. 
 * 
 */ 
package com.zengshi.ecp.sys.dubbo.dto;

import java.math.BigDecimal;

import com.zengshi.ecp.server.front.dto.BaseResponseDTO;

/**
 * Title: ECP <br>
 * Project Name:ecp-services-sys <br>
 * Description: <br>
 * Date:2015-9-2上午10:16:06  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author yugn
 * @version  
 * @since JDK 1.6 
 */
public class BaseExpressRespDTO extends BaseResponseDTO {

    /** 
     * @since JDK 1.6 
     */ 
    private static final long serialVersionUID = 478711946770018867L;
    
    private long id;
    
    private String expressFullName;

    private String expressName;

    private String expressWebsite;

    private String status;
    
    private String statusCn;

    private BigDecimal sortNo;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public BigDecimal getSortNo() {
        return sortNo;
    }

    public void setSortNo(BigDecimal sortNo) {
        this.sortNo = sortNo;
    }

    public String getStatusCn() {
        return statusCn;
    }

    public void setStatusCn(String statusCn) {
        this.statusCn = statusCn;
    }
    
}

