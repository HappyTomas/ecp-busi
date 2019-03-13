package com.zengshi.ecp.order.dubbo.dto.pay;

import com.zengshi.ecp.server.front.dto.BaseInfo;
/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-services-order-server <br>
 * Description:退款处理对象 <br>
 * Date:2015年12月23日下午3:00:28  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author weijq
 * @version  
 * @since JDK 1.6
 */
public class PayRefundSuccInfo  extends BaseInfo {

	private static final long serialVersionUID = -7895930024883341702L;
	private String orderId;//必须
    private String payWay;//必须
    private Long staffId;   //必须
    private String refundTransNo;//流水号
    private long payment;//退款金额（单位分）
    private String payeeName;//退款人姓名
    private String payeeAcct;//退款人账户
    private String flag;//退款成功或失败状态
    private Long backId;//退货申请ID
    
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
	public String getRefundTransNo() {
		return refundTransNo;
	}
	public void setRefundTransNo(String payTransNo) {
		this.refundTransNo = payTransNo;
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
    public String getFlag() {
        return flag;
    }
    public void setFlag(String flag) {
        this.flag = flag;
    }
    public void setStaffId(Long staffId) {
        this.staffId = staffId;
    }
    public Long getStaffId() {
        return staffId;
    }
    public Long getBackId() {
        return backId;
    }
    public void setBackId(Long backId) {
        this.backId = backId;
    }
}
