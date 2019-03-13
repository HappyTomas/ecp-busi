package com.zengshi.ecp.order.dubbo.dto;

import java.util.List;

import com.zengshi.ecp.server.front.dto.BaseInfo;

public class RBackApplyOrdReq  extends BaseInfo{

    /**
	 * 
	 */
	private static final long serialVersionUID = 7249537115367339649L;
	/**
     * 申请类型
     */
    private String applyType;
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

    public String getApplyType() {
        return applyType;
    }

    public void setApplyType(String applyType) {
        this.applyType = applyType;
    }

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
