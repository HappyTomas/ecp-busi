package com.zengshi.ecp.order.dubbo.dto;

import com.zengshi.ecp.server.front.dto.BaseResponseDTO;

public class RBackPicResp extends BaseResponseDTO {

    /** 
     * serialVersionUID:. 
     * @since JDK 1.6 
     */ 
    private static final long serialVersionUID = -4762072367102635118L;
    /** 
     * backId:退换货申请ID. 
     * @since JDK 1.6 
     */ 
    private Long backId;

    /** 
     * picType:图片类型. 
     * @since JDK 1.6 
     */ 
    private String picType;

    /** 
     * orderId:订单号. 
     * @since JDK 1.6 
     */ 
    private Long orderId;

    /** 
     * orderSubId:子订单号. 
     * @since JDK 1.6 
     */ 
    private Long orderSubId;

    /** 
     * vfsId:文件附件系统ID. 
     * @since JDK 1.6 
     */ 
    private String vfsId;

    /** 
     * picName:附件名称. 
     * @since JDK 1.6 
     */ 
    private String picName;

    public Long getBackId() {
        return backId;
    }

    public void setBackId(Long backId) {
        this.backId = backId;
    }

    public String getPicType() {
        return picType;
    }

    public void setPicType(String picType) {
        this.picType = picType == null ? null : picType.trim();
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getOrderSubId() {
        return orderSubId;
    }

    public void setOrderSubId(Long orderSubId) {
        this.orderSubId = orderSubId;
    }

    public String getVfsId() {
        return vfsId;
    }

    public void setVfsId(String vfsId) {
        this.vfsId = vfsId == null ? null : vfsId.trim();
    }

    public String getPicName() {
        return picName;
    }

    public void setPicName(String picName) {
        this.picName = picName == null ? null : picName.trim();
    }

}

