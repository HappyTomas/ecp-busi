package com.zengshi.ecp.order.dubbo.dto;

import com.zengshi.ecp.server.front.dto.BaseInfo;

public class ROrdStaffTradeInfoReq extends BaseInfo {

    /** 
     * serialVersionUID: 
     * @since JDK 1.6 
     */ 
    private static final long serialVersionUID = 3233039470070861231L;
    
    /** 
     * shopId:买家ID. 
     * @since JDK 1.6 
     */ 
    private Long shopId;
    
    /** 
     * dealNum:交易次数. 
     * @since JDK 1.6 
     */ 
    private Long dealNum;
    
    /** 
     * dealSumStart:交易金额起始. 
     * @since JDK 1.6 
     */ 
    private Long dealSumStart;
    
    /** 
     * dealSumEnd:交易金额结束. 
     * @since JDK 1.6 
     */ 
    private Long dealSumEnd;

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public Long getDealNum() {
        return dealNum;
    }

    public void setDealNum(Long dealNum) {
        this.dealNum = dealNum;
    }

    public Long getDealSumStart() {
        return dealSumStart;
    }

    public void setDealSumStart(Long dealSumStart) {
        this.dealSumStart = dealSumStart;
    }

    public Long getDealSumEnd() {
        return dealSumEnd;
    }

    public void setDealSumEnd(Long dealSumEnd) {
        this.dealSumEnd = dealSumEnd;
    }

}

