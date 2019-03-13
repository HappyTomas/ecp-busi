package com.zengshi.ecp.order.dubbo.dto;

import java.util.List;

import com.zengshi.ecp.server.front.dto.BaseInfo;

public class RBackApplyReq extends BaseInfo {

    /** 
     * serialVersionUID:. 
     * @since JDK 1.6 
     */ 
    private static final long serialVersionUID = -9041455012123279740L;
    
    
    /** 
     * applyType:申请类型. 
     * @since JDK 1.6 
     */ 
    private String applyType;
    /** 
     * orderId:订单号. 
     * @since JDK 1.6 
     */ 
    private String orderId;
    /** 
     * orderSubId:子订单列表. 
     * @since JDK 1.6 
     */ 
    private List<RBackOrdSubReq> rBackOrdSubReqs;
    /** 
     * backType:退货原因id. 
     * @since JDK 1.6 
     */ 
    private String backType;
    /** 
     * backTypeName:退货原因中文描述. 
     * @since JDK 1.6 
     */ 
    private String backTypeName;
    /** 
     * backDesc:问题描述. 
     * @since JDK 1.6 
     */ 
    private String backDesc;
    /** 
     * backPicList:上传图片id串. 
     * @since JDK 1.6 
     */ 
    private List<String> backPicList;
    
    /**
     * 是否补偿性退款
     */
    private String isCompenstate;
    
    /**
     * 全部退货1：是， 0:否
     */
    private String checkedAll;
    
    public String getApplyType() {
        return applyType;
    }
    public void setApplyType(String applyType) {
        this.applyType = applyType;
    }
    public String getOrderId() {
        return orderId;
    }
    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }
    public List<RBackOrdSubReq> getrBackOrdSubReqs() {
        return rBackOrdSubReqs;
    }
    public void setrBackOrdSubReqs(List<RBackOrdSubReq> rBackOrdSubReqs) {
        this.rBackOrdSubReqs = rBackOrdSubReqs;
    }
    public String getBackType() {
        return backType;
    }
    public void setBackType(String backType) {
        this.backType = backType;
    }
    public String getBackTypeName() {
        return backTypeName;
    }
    public void setBackTypeName(String backTypeName) {
        this.backTypeName = backTypeName;
    }
    public String getBackDesc() {
        return backDesc;
    }
    public void setBackDesc(String backDesc) {
        this.backDesc = backDesc;
    }
    public List<String> getBackPicList() {
        return backPicList;
    }
    public void setBackPicList(List<String> backPicList) {
        this.backPicList = backPicList;
    }
    public String getCheckedAll() {
        return checkedAll;
    }
    public void setCheckedAll(String checkedAll) {
        this.checkedAll = checkedAll;
    }
	public String getIsCompenstate() {
		return isCompenstate;
	}
	public void setIsCompenstate(String isCompenstate) {
		this.isCompenstate = isCompenstate;
	}
    
}

