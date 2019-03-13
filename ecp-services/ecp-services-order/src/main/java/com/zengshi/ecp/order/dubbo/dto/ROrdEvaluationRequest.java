package com.zengshi.ecp.order.dubbo.dto;

import com.zengshi.ecp.server.front.dto.BaseInfo;

public class ROrdEvaluationRequest extends BaseInfo {

    /** 
     * serialVersionUID:. 
     * @since JDK 1.6 
     */ 
    private static final long serialVersionUID = -8892155997859769938L;
    
    /** 
     * staffId:买家ID. 
     * @since JDK 1.6 
     */ 
    private Long staffId;
    /** 
     * gdsId:商品编码. 
     * @since JDK 1.6 
     */ 
    private Long gdsId;
    /** 
     * skuId:单品编码. 
     * @since JDK 1.6 
     */ 
    private Long skuId;
    public Long getStaffId() {
        return staffId;
    }
    public void setStaffId(Long staffId) {
        this.staffId = staffId;
    }
    public Long getGdsId() {
        return gdsId;
    }
    public void setGdsId(Long gdsId) {
        this.gdsId = gdsId;
    }
    public Long getSkuId() {
        return skuId;
    }
    public void setSkuId(Long skuId) {
        this.skuId = skuId;
    }

}

