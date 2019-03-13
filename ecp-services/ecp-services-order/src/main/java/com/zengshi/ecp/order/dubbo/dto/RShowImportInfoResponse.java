package com.zengshi.ecp.order.dubbo.dto;

import com.zengshi.ecp.server.front.dto.BaseResponseDTO;

public class RShowImportInfoResponse extends BaseResponseDTO {

    /** 
     * serialVersionUID: 
     * @since JDK 1.6 
     */ 
    private static final long serialVersionUID = 6116225314361107918L;
    /** 
     * entityCode:实体编号. 
     * @since JDK 1.6 
     */ 
    private String entityCode;
    /** 
     * remark:状态信息. 
     * @since JDK 1.6 
     */ 
    private String remark;
    public String getEntityCode() {
        return entityCode;
    }
    public void setEntityCode(String entityCode) {
        this.entityCode = entityCode;
    }
    public String getRemark() {
        return remark;
    }
    public void setRemark(String remark) {
        this.remark = remark;
    }
}

