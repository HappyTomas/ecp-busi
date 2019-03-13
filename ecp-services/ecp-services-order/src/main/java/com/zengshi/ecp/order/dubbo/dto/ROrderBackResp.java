package com.zengshi.ecp.order.dubbo.dto;

import java.util.List;

import com.zengshi.ecp.server.front.dto.BaseResponseDTO;

public class ROrderBackResp extends BaseResponseDTO {

    /** 
     * serialVersionUID:. 
     * @since JDK 1.6 
     */ 
    private static final long serialVersionUID = -5199021838709716179L;
    
    /** 
     * rBackApplyResp:退货申请表相关字段. 
     * @since JDK 1.6 
     */ 
    private RBackApplyResp rBackApplyResp;
    
    /**
     * 支付方式
     */
    private String payType;
    
    /**
     * 支付通道
     */
    private String payWay;
    /** 
     * rBackGdsResp:退货明细表相关字段. 
     * @since JDK 1.6 
     */ 
    private List<RBackGdsResp> rBackGdsResps;
    
    
    public RBackApplyResp getrBackApplyResp() {
        return rBackApplyResp;
    }
    public void setrBackApplyResp(RBackApplyResp rBackApplyResp) {
        this.rBackApplyResp = rBackApplyResp;
    }
    public List<RBackGdsResp> getrBackGdsResps() {
        return rBackGdsResps;
    }
    public void setrBackGdsResps(List<RBackGdsResp> rBackGdsResps) {
        this.rBackGdsResps = rBackGdsResps;
    }
	public String getPayType() {
		return payType;
	}
	public void setPayType(String payType) {
		this.payType = payType;
	}
	public String getPayWay() {
		return payWay;
	}
	public void setPayWay(String payWay) {
		this.payWay = payWay;
	}
    
}

