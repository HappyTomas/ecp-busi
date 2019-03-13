package com.zengshi.ecp.busi.prom.createprom.vo;

import com.zengshi.ecp.server.front.dto.BaseResponseDTO;

/**
 * Title: AI-ECP <br>
 * Description: <br>
 * Date: 2015-8-20 <br>
 * Copyright (c) 2015 AI <br>
 * 
 * @author huangjx
 */
public class CatgVO extends BaseResponseDTO{


    private static final long serialVersionUID = 1L;
   
    private String catgCode;
    private String catgName;
    private String status;
    public String getCatgCode() {
        return catgCode;
    }
    public void setCatgCode(String catgCode) {
        this.catgCode = catgCode;
    }
    public String getCatgName() {
        return catgName;
    }
    public void setCatgName(String catgName) {
        this.catgName = catgName;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
     
    
}
