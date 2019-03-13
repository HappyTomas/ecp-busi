package com.zengshi.ecp.busi.order.pay.vo;

import javax.validation.constraints.NotNull;

import com.zengshi.ecp.base.vo.EcpBasePageReqVO;

public class ROfflineReviewReqVO extends EcpBasePageReqVO {

    /** 
     * serialVersionUID:TODO(用一句话描述这个变量表示什么). 
     * @since JDK 1.6 
     */ 
    private static final long serialVersionUID = 8270337630584754017L;
    @NotNull
    private String   orderId  ;
    private Long   shopId  ;
    @NotNull
    private Long     offlineNo;
    @NotNull
    private String   checkCont;
    private Long     staffId  ;
    @NotNull
    private String   status   ;
    public String getOrderId() {
        return orderId;
    }
    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }
    public Long getOfflineNo() {
        return offlineNo;
    }
    public void setOfflineNo(Long offlineNo) {
        this.offlineNo = offlineNo;
    }
    public String getCheckCont() {
        return checkCont;
    }
    public void setCheckCont(String checkCont) {
        this.checkCont = checkCont;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public Long getStaffId() {
        return staffId;
    }
    public void setStaffId(Long staffId) {
        this.staffId = staffId;
    }
	public Long getShopId() {
		return shopId;
	}
	public void setShopId(Long shopId) {
		this.shopId = shopId;
	}

}   

