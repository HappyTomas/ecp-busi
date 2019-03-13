package com.zengshi.ecp.order.dubbo.dto;

import java.util.List;

import com.zengshi.ecp.server.front.dto.BaseResponseDTO;

public class RBackApplyOrdResp extends BaseResponseDTO {

    /** 
     * serialVersionUID:TODO(用一句话描述这个变量表示什么). 
     * @since JDK 1.6 
     */ 
    private static final long serialVersionUID = -7792951136163313775L;
    
    /** 
     * orderId:订单号. 
     * @since JDK 1.6 
     */ 
    private SOrderDetailsMain SOrderDetailsMain;
    
    /** 
     * rBackApplyOrdSubResps:订单明细列表. 
     * @since JDK 1.6 
     */ 
    private List<RBackApplyOrdSubResp> rBackApplyOrdSubResps;

    

    public SOrderDetailsMain getSOrderDetailsMain() {
        return SOrderDetailsMain;
    }

    public void setSOrderDetailsMain(SOrderDetailsMain sOrderDetailsMain) {
        SOrderDetailsMain = sOrderDetailsMain;
    }

    public List<RBackApplyOrdSubResp> getrBackApplyOrdSubResps() {
        return rBackApplyOrdSubResps;
    }

    public void setrBackApplyOrdSubResps(List<RBackApplyOrdSubResp> rBackApplyOrdSubResps) {
        this.rBackApplyOrdSubResps = rBackApplyOrdSubResps;
    }
    
    
}

