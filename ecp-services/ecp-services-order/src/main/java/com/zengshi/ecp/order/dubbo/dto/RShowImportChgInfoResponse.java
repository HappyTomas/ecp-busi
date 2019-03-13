package com.zengshi.ecp.order.dubbo.dto;

import com.zengshi.ecp.server.front.dto.BaseResponseDTO;

public class RShowImportChgInfoResponse extends BaseResponseDTO {

    /** 
     * serialVersionUID:. 
     * @since JDK 1.6 
     */ 
    private static final long serialVersionUID = -269471529469647320L;
    /** 
     * orderId:订单号. 
     * @since JDK 1.6 
     */ 
    private String orderId;
    /** 
     * entityCodeBf:变更前实体编号. 
     * @since JDK 1.6 
     */ 
    private String entityCodeBf;

    /** 
     * entityCodeAf:变更后实体编号. 
     * @since JDK 1.6 
     */ 
    private String entityCodeAf;
    /** 
     * remark:状态信息. 
     * @since JDK 1.6 
     */ 
    private String remark;
    public String getOrderId() {
        return orderId;
    }
    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }
    public String getEntityCodeBf() {
        return entityCodeBf;
    }
    public void setEntityCodeBf(String entityCodeBf) {
        this.entityCodeBf = entityCodeBf;
    }
    public String getEntityCodeAf() {
        return entityCodeAf;
    }
    public void setEntityCodeAf(String entityCodeAf) {
        this.entityCodeAf = entityCodeAf;
    }
    public String getRemark() {
        return remark;
    }
    public void setRemark(String remark) {
        this.remark = remark;
    }

}

