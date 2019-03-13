package com.zengshi.ecp.order.dubbo.dto.pay;

import java.util.Map;

import com.abc.pay.client.ebus.PaymentResult;

public class ABCPayWayResponse extends BasePayResponse{
	
	/** 
     * serialVersionUID:TODO(用一句话描述这个变量表示什么). 
     * @since JDK 1.6 
     */ 
    private static final long serialVersionUID = 1L;

    public static final String module = ABCPayWayResponse.class.getName();
	
	private PaymentResult tResult;
	private String merchantID;
	private String trxTyp;
	private String orderNo;
	private String amount;
	private String batchNo;
	private String voucherNo;
	private String hostDate;
	private String hostTime;
	private String merchantRemarks;
	private String payType;
	private String notifyType;
	private String iRspRef;
	public ABCPayWayResponse(PaymentResult tResult) {
	    this.tResult=tResult;
    }
	@Override
	public void buildSelf(Map<String, String> responseMap) throws Exception {
		this.setMerchantID(tResult.getValue("MerchantID"));
		this.setTrxTyp(tResult.getValue("TrxTyp"));
		this.setOrderNo(tResult.getValue("OrderNo"));
		this.setAmount(tResult.getValue("Amount"));
		this.setBatchNo(tResult.getValue("BatchNo"));
		this.setVoucherNo(tResult.getValue("VoucherNo"));
		this.setHostDate(tResult.getValue("HostDate"));
		this.setHostTime(tResult.getValue("HostTime"));
		this.setMerchantRemarks(tResult.getValue("MerchantRemarks"));
		this.setPayType(tResult.getValue("PayType"));
		this.setNotifyType(tResult.getValue("NotifyType"));
		this.setIRspRef(tResult.getValue("iRspRef"));
	}
    public String getMerchantID() {
        return merchantID;
    }
    public void setMerchantID(String merchantID) {
        this.merchantID = merchantID;
    }
    public String getTrxTyp() {
        return trxTyp;
    }
    public void setTrxTyp(String trxTyp) {
        this.trxTyp = trxTyp;
    }
    public String getOrderNo() {
        return orderNo;
    }
    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }
    public String getAmount() {
        return amount;
    }
    public void setAmount(String amount) {
        this.amount = amount;
    }
    public String getBatchNo() {
        return batchNo;
    }
    public void setBatchNo(String batchNo) {
        this.batchNo = batchNo;
    }
    public String getVoucherNo() {
        return voucherNo;
    }
    public void setVoucherNo(String voucherNo) {
        this.voucherNo = voucherNo;
    }
    public String getHostDate() {
        return hostDate;
    }
    public void setHostDate(String hostDate) {
        this.hostDate = hostDate;
    }
    public String getHostTime() {
        return hostTime;
    }
    public void setHostTime(String hostTime) {
        this.hostTime = hostTime;
    }
    public String getMerchantRemarks() {
        return merchantRemarks;
    }
    public void setMerchantRemarks(String merchantRemarks) {
        this.merchantRemarks = merchantRemarks;
    }
    public String getPayType() {
        return payType;
    }
    public void setPayType(String payType) {
        this.payType = payType;
    }
    public String getNotifyType() {
        return notifyType;
    }
    public void setNotifyType(String notifyType) {
        this.notifyType = notifyType;
    }
    public String getIRspRef() {
        return iRspRef;
    }
    public void setIRspRef(String iRspRef) {
        this.iRspRef = iRspRef;
    }
	
//	@Override
//	public boolean verifySign(String[] signParams, String checkValue)
//			throws Exception {
//		String signStr=orderId+ payFlowNo+ amount+ flag+ payDate+ chnlId+ shopId+bankNo+custName+subShopId;
////		RSASign rsaSign = RSASign.getInstance();
//		return rsaSign.validateSign(checkValue, signStr);
//	}
}
