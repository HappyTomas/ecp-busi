package com.zengshi.ecp.order.dubbo.dto;

import java.util.List;

import com.zengshi.ecp.server.front.dto.BaseResponseDTO;

public class RDeliveryPrintResponse extends BaseResponseDTO {

    /** 
     * serialVersionUID:. 
     * @since JDK 1.6 
     */ 
    private static final long serialVersionUID = -6739535953726108832L;
    
    //主订单相关字段
    private SOrderDetailsMain sOrderDetailsMain;
    //子订单相关字段
    private List<SOrderDetailsSub>  sOrderDetailsSubs;
    //订单普通发票相关字段
    private SOrderDetailsComm sOrderDetailsComm;
    //订单增值税发票相关字段 
    private SOrderDetailsTax sOrderDetailsTax;
    //订单收货地址相关字段
    private SOrderDetailsAddr sOrderDetailsAddr;
    public SOrderDetailsMain getsOrderDetailsMain() {
        return sOrderDetailsMain;
    }
    public void setsOrderDetailsMain(SOrderDetailsMain sOrderDetailsMain) {
        this.sOrderDetailsMain = sOrderDetailsMain;
    }
    public List<SOrderDetailsSub> getsOrderDetailsSubs() {
        return sOrderDetailsSubs;
    }
    public void setsOrderDetailsSubs(List<SOrderDetailsSub> sOrderDetailsSubs) {
        this.sOrderDetailsSubs = sOrderDetailsSubs;
    }
    public SOrderDetailsComm getsOrderDetailsComm() {
        return sOrderDetailsComm;
    }
    public void setsOrderDetailsComm(SOrderDetailsComm sOrderDetailsComm) {
        this.sOrderDetailsComm = sOrderDetailsComm;
    }
    public SOrderDetailsTax getsOrderDetailsTax() {
        return sOrderDetailsTax;
    }
    public void setsOrderDetailsTax(SOrderDetailsTax sOrderDetailsTax) {
        this.sOrderDetailsTax = sOrderDetailsTax;
    }
    public SOrderDetailsAddr getsOrderDetailsAddr() {
        return sOrderDetailsAddr;
    }
    public void setsOrderDetailsAddr(SOrderDetailsAddr sOrderDetailsAddr) {
        this.sOrderDetailsAddr = sOrderDetailsAddr;
    }

}

