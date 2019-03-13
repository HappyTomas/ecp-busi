package com.zengshi.ecp.prom.dubbo.dto;

import java.io.Serializable;

/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-services-prom-server <br>
 * Description: <br>
 * Date:2015-12-31下午4:11:36  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author PJieWin
 * @version  
 * @since JDK 1.6
 * 
 * 单品批量导入错误信息类
 */
public class ImportPromErrorInfoDTO implements Serializable{
    /** 
     * serialVersionUID:TODO(用一句话描述这个变量表示什么). 
     * @since JDK 1.6 
     */ 
    private static final long serialVersionUID = -1959323107542682167L;
    
    private String promCnt = null;
    private String promSkuId = null;
    private String errorCode = null;
    private String errorContext = null;
    
    public ImportPromErrorInfoDTO(String promCnt, String promSkuId, String errorCode, String errorContext)
    {
        this.promCnt = promCnt;
        this.promSkuId = promSkuId;
        this.errorCode = errorCode;
        this.errorContext = errorContext;
    }
    public ImportPromErrorInfoDTO(String promCnt, String promSkuId, String errorCode)
    {
        this.promCnt = promCnt;
        this.promSkuId = promSkuId;
        this.errorCode = errorCode;
    }
    public ImportPromErrorInfoDTO(String errorCode, String errorContext)
    {
        this.errorCode = errorCode;
        this.errorContext = errorContext;
    }
    public ImportPromErrorInfoDTO()
    {
        
    }
    public String getPromCnt() {
        return promCnt;
    }
    public void setPromCnt(String promCnt) {
        this.promCnt = promCnt;
    }
    public String getPromSkuId() {
        return promSkuId;
    }
    public void setPromSkuId(String promSkuId) {
        this.promSkuId = promSkuId;
    }
    public String getErrorContext() {
        return errorContext;
    }
    public void setErrorContext(String errorContext) {
        this.errorContext = errorContext;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("ImportPromErrorInfoDTO [promCnt=");
        builder.append(promCnt);
        builder.append(", promSkuId=");
        builder.append(promSkuId);
        builder.append(", errorCode=");
        builder.append(errorCode);
        builder.append(", errorContext=");
        builder.append(errorContext);
        builder.append("]");
        return builder.toString();
    }
    
    
}

