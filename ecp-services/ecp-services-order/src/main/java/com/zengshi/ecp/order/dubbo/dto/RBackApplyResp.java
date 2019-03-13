package com.zengshi.ecp.order.dubbo.dto;

import java.sql.Timestamp;

import com.zengshi.ecp.server.front.dto.BaseResponseDTO;

public class RBackApplyResp extends BaseResponseDTO {

    /** 
     * serialVersionUID:. 
     * @since JDK 1.6 
     */ 
    private static final long serialVersionUID = 1824978584656161972L;
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
     * backType:退换货类型. 
     * @since JDK 1.6 
     */ 
    private String backType;

    /** 
     * backTypeName:退换货类型名称. 
     * @since JDK 1.6 
     */ 
    private String backTypeName;

    /** 
     * backDesc:问题描述. 
     * @since JDK 1.6 
     */ 
    private String backDesc;

    /** 
     * applyType:申请类型. 
     * @since JDK 1.6 
     */ 
    private String applyType;

    /** 
     * applyTime:申请时间. 
     * @since JDK 1.6 
     */ 
    private Timestamp applyTime;
    
    /**
     * payTime:支付时间. 
     * @since JDK 1.6 
     */
    private Timestamp payTime;
    
    /**
     * payTime:退款时间. 
     * @since JDK 1.6 
     */
    private Timestamp refundTime;

    /** 
     * isEntire:是否整单退换货. 
     * @since JDK 1.6 
     */ 
    private String isEntire;

    /** 
     * num:退货总数量. 
     * @since JDK 1.6 
     */ 
    private Long num;

    /** 
     * staffId:买家ID. 
     * @since JDK 1.6 
     */ 
    private Long staffId;

    /** 
     * shopId:卖家ID. 
     * @since JDK 1.6 
     */ 
    private Long shopId;

    /** 
     * checkDesc:审核意见. 
     * @since JDK 1.6 
     */ 
    private String checkDesc;

    /** 
     * status:退换货状态. 
     * @since JDK 1.6 
     */ 
    private String status;

    /** 
     * backMoney:退款金额. 
     * @since JDK 1.6 
     */ 
    private Long backMoney;

    /** 
     * backScore:退货积分. 
     * @since JDK 1.6 
     */ 
    private Long backScore;

    /** 
     * backAccount:退货资金账户. 
     * @since JDK 1.6 
     */ 
    private Long backAccount;

    /** 
     * expressId:物流公司编号. 
     * @since JDK 1.6 
     */ 
    private Long expressId;
    
    /** 
     * express:物流公司名称. 
     * @since JDK 1.6 
     */ 
    private String express;

    /** 
     * expressNo:物流单号. 
     * @since JDK 1.6 
     */ 
    private String expressNo;

    /** 
     * siteId:所属站点. 
     * @since JDK 1.6 
     */ 
    private Long siteId;
    
    /** 
     * payType:退款方式. 
     * @since JDK 1.6 
     */ 
    private String payType;
    
    /** 
     * realMoney:订单实付金额. 
     * @since JDK 1.6 
     */ 
    private Long realMoney;
    
    /** 
     * orderScore:下单使用的积分. 
     * @since JDK 1.6 
     */ 
    private Long orderScore;
    
    /** 
     * staffName:会员名. 
     * @since JDK 1.6 
     */ 
    private String staffName;
    
    /**
     * 是否补偿性退款
     */
    private String isCompenstate;
    
    /** 
     * payTranNo:商户订单号. 
     * @since JDK 1.6 
     */
    private String payTranNo;
     
    public String getPayTranNo() {
        return payTranNo;
    }

    public void setPayTranNo(String payTranNo) {
        this.payTranNo = payTranNo;
    }

    public Long getOrderScore() {
        return orderScore;
    }

    public void setOrderScore(Long orderScore) {
        this.orderScore = orderScore;
    }

    public String getExpress() {
        return express;
    }

    public void setExpress(String express) {
        this.express = express;
    }

    public Long getRealMoney() {
        return realMoney;
    }

    public void setRealMoney(Long realMoney) {
        this.realMoney = realMoney;
    }

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType;
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
        this.orderId = orderId == null ? null : orderId.trim();
    }

    public String getBackType() {
        return backType;
    }

    public void setBackType(String backType) {
        this.backType = backType == null ? null : backType.trim();
    }

    public String getBackTypeName() {
        return backTypeName;
    }

    public void setBackTypeName(String backTypeName) {
        this.backTypeName = backTypeName == null ? null : backTypeName.trim();
    }

    public String getBackDesc() {
        return backDesc;
    }

    public void setBackDesc(String backDesc) {
        this.backDesc = backDesc == null ? null : backDesc.trim();
    }

    public String getApplyType() {
        return applyType;
    }

    public void setApplyType(String applyType) {
        this.applyType = applyType == null ? null : applyType.trim();
    }

    public Timestamp getApplyTime() {
        return applyTime;
    }

    public void setApplyTime(Timestamp applyTime) {
        this.applyTime = applyTime;
    }
    
    public Timestamp getPayTime() {
        return payTime;
    }

    public void setPayTime(Timestamp payTime) {
        this.payTime = payTime;
    }

    public Timestamp getRefundTime() {
        return refundTime;
    }

    public void setRefundTime(Timestamp refundTime) {
        this.refundTime = refundTime;
    }

    public String getIsEntire() {
        return isEntire;
    }

    public void setIsEntire(String isEntire) {
        this.isEntire = isEntire == null ? null : isEntire.trim();
    }

    public Long getNum() {
        return num;
    }

    public void setNum(Long num) {
        this.num = num;
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

    public String getCheckDesc() {
        return checkDesc;
    }

    public void setCheckDesc(String checkDesc) {
        this.checkDesc = checkDesc == null ? null : checkDesc.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public Long getBackMoney() {
        return backMoney;
    }

    public void setBackMoney(Long backMoney) {
        this.backMoney = backMoney;
    }

    public Long getBackScore() {
        return backScore;
    }

    public void setBackScore(Long backScore) {
        this.backScore = backScore;
    }

    public Long getBackAccount() {
        return backAccount;
    }

    public void setBackAccount(Long backAccount) {
        this.backAccount = backAccount;
    }

    public Long getExpressId() {
        return expressId;
    }

    public void setExpressId(Long expressId) {
        this.expressId = expressId;
    }

    public String getExpressNo() {
        return expressNo;
    }

    public void setExpressNo(String expressNo) {
        this.expressNo = expressNo == null ? null : expressNo.trim();
    }

    public Long getSiteId() {
        return siteId;
    }

    public void setSiteId(Long siteId) {
        this.siteId = siteId;
    }

    public String getStaffName() {
		return staffName;
	}

	public void setStaffName(String staffName) {
		this.staffName = staffName;
	}

	@Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(backId);
        sb.append(", orderId=").append(orderId);
        sb.append(", backType=").append(backType);
        sb.append(", backTypeName=").append(backTypeName);
        sb.append(", backDesc=").append(backDesc);
        sb.append(", applyType=").append(applyType);
        sb.append(", applyTime=").append(applyTime);
        sb.append(", isEntire=").append(isEntire);
        sb.append(", num=").append(num);
        sb.append(", staffId=").append(staffId);
        sb.append(", shopId=").append(shopId);
        sb.append(", checkDesc=").append(checkDesc);
        sb.append(", status=").append(status);
        sb.append(", backMoney=").append(backMoney);
        sb.append(", backScore=").append(backScore);
        sb.append(", backAccount=").append(backAccount);
        sb.append(", expressId=").append(expressId);
        sb.append(", expressNo=").append(expressNo);
        sb.append(", siteId=").append(siteId);
        sb.append(", isCompenstate=").append(isCompenstate);
        sb.append("]");
        return sb.toString();
    }

	public String getIsCompenstate() {
		return isCompenstate;
	}

	public void setIsCompenstate(String isCompenstate) {
		this.isCompenstate = isCompenstate;
	}
}

