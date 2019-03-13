package com.zengshi.ecp.prom.dubbo.dto;

import com.zengshi.ecp.server.front.dto.BaseInfo;

/**
 * Title: AI-ECP <br>
 * Description: <br>
 * Date: 2016-2-17 <br>
 * Copyright (c) 2015 AI <br>
 * 
 * @author huangjx
 */
public class PromListTaskBeanDTO extends BaseInfo {

    private static final long serialVersionUID = 1L;
    
    private OrdPromDetailDTO ordPromDetailDTO;//明细信息
    
    private boolean ifSuccess=true;//是否成功
    
    private Exception exception;//错误信息

    public boolean isIfSuccess() {
        return ifSuccess;
    }

    public void setIfSuccess(boolean ifSuccess) {
        this.ifSuccess = ifSuccess;
    }

    public Exception getException() {
        return exception;
    }

    public void setException(Exception exception) {
        this.exception = exception;
    }

    public OrdPromDetailDTO getOrdPromDetailDTO() {
        return ordPromDetailDTO;
    }

    public void setOrdPromDetailDTO(OrdPromDetailDTO ordPromDetailDTO) {
        this.ordPromDetailDTO = ordPromDetailDTO;
    }
 
}
