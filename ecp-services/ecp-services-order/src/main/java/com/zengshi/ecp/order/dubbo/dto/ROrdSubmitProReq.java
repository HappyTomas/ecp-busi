package com.zengshi.ecp.order.dubbo.dto;

import com.zengshi.ecp.general.order.dto.ROrdCartsCommRequest;
import com.zengshi.ecp.server.front.dto.BaseInfo;
import com.zengshi.ecp.staff.dubbo.dto.ListReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.ScoreExchangeReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.TransactionContentReqDTO;

public class ROrdSubmitProReq extends BaseInfo {
    
    /** 
     * rOrdCartsCommRequest:公共入参. 
     * @since JDK 1.6 
     */ 
    private ROrdCartsCommRequest rOrdCartsCommRequest;
    
    /** 
     * scoreExchangeReqDTOs:客户域积分. 
     * @since JDK 1.6 
     */ 
    private ListReqDTO<ScoreExchangeReqDTO> scoreExchangeReqDTOs;
    
    /** 
     * transactionContentReqDTOs:客户域资金账户. 
     * @since JDK 1.6 
     */ 
    private ListReqDTO<TransactionContentReqDTO> transactionContentReqDTOs;
    
    /** 
     * isProm:是否有促销 . 
     * @since JDK 1.6 
     */ 
    private boolean isProm;
    
    /** 
     * isCoup:是否使用优惠券. 
     * @since JDK 1.6 
     */ 
    private boolean isCoup;

    public ROrdCartsCommRequest getrOrdCartsCommRequest() {
        return rOrdCartsCommRequest;
    }

    public void setrOrdCartsCommRequest(ROrdCartsCommRequest rOrdCartsCommRequest) {
        this.rOrdCartsCommRequest = rOrdCartsCommRequest;
    }

    public ListReqDTO<ScoreExchangeReqDTO> getScoreExchangeReqDTOs() {
        return scoreExchangeReqDTOs;
    }

    public void setScoreExchangeReqDTOs(ListReqDTO<ScoreExchangeReqDTO> scoreExchangeReqDTOs) {
        this.scoreExchangeReqDTOs = scoreExchangeReqDTOs;
    }

    public ListReqDTO<TransactionContentReqDTO> getTransactionContentReqDTOs() {
        return transactionContentReqDTOs;
    }

    public void setTransactionContentReqDTOs(
            ListReqDTO<TransactionContentReqDTO> transactionContentReqDTOs) {
        this.transactionContentReqDTOs = transactionContentReqDTOs;
    }

    public boolean isProm() {
        return isProm;
    }

    public void setProm(boolean isProm) {
        this.isProm = isProm;
    }

    public boolean isCoup() {
        return isCoup;
    }

    public void setCoup(boolean isCoup) {
        this.isCoup = isCoup;
    }
    
    
}

