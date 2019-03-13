package com.zengshi.ecp.order.dubbo.dto.pay;

import com.zengshi.ecp.server.front.dto.BaseInfo;
/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-services-order-server <br>
 * Description: 支付成功返回对象<br>
 * Date:2015年10月12日上午10:05:50  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author weijq
 * @version  
 * @since JDK 1.6
 */
public class PaySuccInfo  extends BaseInfo {

	private static final long serialVersionUID = -7895930024883341702L;
	private String orderId;//必须
	private String payWay;//必须
    private long staffId;   //必须
    private String payTransNo;//支付流水号
    private long payment;//支付金额（单位分）
    private String payeeName;//付款人姓名
    private String payeeAcct;//付款人账户
    private String payWayName;//支付通道名称
    private String merchAcct;//收款账户编码
    private long shopId;//收款账户编码
    private String payType;//支付类型，01 代表像线上支付，02 非线上支付
    private String subOrder;//子订单
    private String joinOrderid;//合并支付订单号
    
    private Boolean shareFlag;
    
    public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public String getPayWay() {
		return payWay;
	}
	public void setPayWay(String payWay) {
		this.payWay = payWay;
	}
    public long getStaffId() {
        return staffId;
    }
    public void setStaffId(long staffId) {
        this.staffId = staffId;
    }
	public String getPayTransNo() {
		return payTransNo;
	}
	public void setPayTransNo(String payTransNo) {
		this.payTransNo = payTransNo;
	}
	public long getPayment() {
		return payment;
	}
	public void setPayment(long payment) {
		this.payment = payment;
	}
	public String getPayeeName() {
		return payeeName;
	}
	public void setPayeeName(String payeeName) {
		this.payeeName = payeeName;
	}
	public String getPayeeAcct() {
		return payeeAcct;
	}
	public void setPayeeAcct(String payeeAcct) {
		this.payeeAcct = payeeAcct;
	}
	public String getPayWayName() {
		return payWayName;
	}
	public void setPayWayName(String payWayName) {
		this.payWayName = payWayName;
	}
	public String getMerchAcct() {
		return merchAcct;
	}
	public void setMerchAcct(String merchAcct) {
		this.merchAcct = merchAcct;
	}
	public long getShopId() {
        return shopId;
    }
    public void setShopId(long shopId) {
        this.shopId = shopId;
    }
    public String getPayType() {
        return payType;
    }
    public void setPayType(String payType) {
        this.payType = payType;
    }
    public String getSubOrder() {
        return subOrder;
    }
    public void setSubOrder(String subOrder) {
        this.subOrder = subOrder;
    }
    public String getJoinOrderid() {
        return joinOrderid;
    }
    public void setJoinOrderid(String joinOrderid) {
        this.joinOrderid = joinOrderid;
    }
	public Boolean getShareFlag() {
		return shareFlag;
	}
	public void setShareFlag(Boolean shareFlag) {
		this.shareFlag = shareFlag;
	}
}
