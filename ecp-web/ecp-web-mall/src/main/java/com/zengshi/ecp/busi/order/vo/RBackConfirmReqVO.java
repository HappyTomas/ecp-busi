package com.zengshi.ecp.busi.order.vo;

import java.util.List;

import com.zengshi.ecp.base.vo.EcpBasePageReqVO;

public class RBackConfirmReqVO extends EcpBasePageReqVO {

    /** 
     * serialVersionUID:. 
     * @since JDK 1.6 
     */ 
    private static final long serialVersionUID = -2160350537330606063L;
    
    /** 
     * backId:退换货申请ID. 
     * @since JDK 1.6 
     */ 
    private Long backId;

    /** 
     * orderId:订单号. 
     * @since JDK 1.6 
     */ 
    private String orderId;
    
    /** 
     * status:状态. 
     * @since JDK 1.6 
     */ 
    private String status;
    
    /** 
     * expressId:物流公司编号. 
     * @since JDK 1.6 
     */ 
    private Long expressId;

    /** 
     * express:物流公司. 
     * @since JDK 1.6 
     */ 
    private String express;

    /** 
     * expressNo:物流单号. 
     * @since JDK 1.6 
     */ 
    private String expressNo;
    
    /** 
     * backPicList:上传图片id串. 
     * @since JDK 1.6 
     */ 
    private List<String> backPicList;

    
    public Long getExpressId() {
        return expressId;
    }

    public void setExpressId(Long expressId) {
        this.expressId = expressId;
    }

    public String getExpress() {
        return express;
    }

    public void setExpress(String express) {
        this.express = express;
    }

    public String getExpressNo() {
        return expressNo;
    }

    public void setExpressNo(String expressNo) {
        this.expressNo = expressNo;
    }

    public Long getBackId() {
        return backId;
    }

    public void setBackId(Long backId) {
        this.backId = backId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<String> getBackPicList() {
        return backPicList;
    }

    public void setBackPicList(List<String> backPicList) {
        this.backPicList = backPicList;
    }
    
    

}

