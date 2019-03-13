package com.zengshi.ecp.order.dao.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class AuditAlipayDetailLog implements Serializable {
    private Long id;

    private Long originalId;

    private Timestamp checkDate;

    private String transOutOrderNo;

    private String tradeNo;

    private String partnerId;

    private String balance;

    private String bankAccountName;

    private String bankAccountNo;

    private String bankName;

    private String buyerAccount;

    private String currency;

    private String depositBankNo;

    private String goodsTitle;

    private String income;

    private String iwAccountLogId;

    private String memo;

    private String merchantOutOrderNo;

    private String otherAccountEmail;

    private String otherAccountFullname;

    private String otherUserId;

    private String outcome;

    private String sellerAccount;

    private String sellerFullname;

    private String serviceFee;

    private String serviceFeeRatio;

    private String totalFee;

    private String tradeRefundAmount;

    private String transAccount;

    private String transCodeMsg;

    private Timestamp transDate;

    private String subTransCodeMsg;

    private String signProductName;

    private String rate;

    private Timestamp createTime;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getOriginalId() {
        return originalId;
    }

    public void setOriginalId(Long originalId) {
        this.originalId = originalId;
    }

    public Timestamp getCheckDate() {
        return checkDate;
    }

    public void setCheckDate(Timestamp checkDate) {
        this.checkDate = checkDate;
    }

    public String getTransOutOrderNo() {
        return transOutOrderNo;
    }

    public void setTransOutOrderNo(String transOutOrderNo) {
        this.transOutOrderNo = transOutOrderNo == null ? null : transOutOrderNo.trim();
    }

    public String getTradeNo() {
        return tradeNo;
    }

    public void setTradeNo(String tradeNo) {
        this.tradeNo = tradeNo == null ? null : tradeNo.trim();
    }

    public String getPartnerId() {
        return partnerId;
    }

    public void setPartnerId(String partnerId) {
        this.partnerId = partnerId == null ? null : partnerId.trim();
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance == null ? null : balance.trim();
    }

    public String getBankAccountName() {
        return bankAccountName;
    }

    public void setBankAccountName(String bankAccountName) {
        this.bankAccountName = bankAccountName == null ? null : bankAccountName.trim();
    }

    public String getBankAccountNo() {
        return bankAccountNo;
    }

    public void setBankAccountNo(String bankAccountNo) {
        this.bankAccountNo = bankAccountNo == null ? null : bankAccountNo.trim();
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName == null ? null : bankName.trim();
    }

    public String getBuyerAccount() {
        return buyerAccount;
    }

    public void setBuyerAccount(String buyerAccount) {
        this.buyerAccount = buyerAccount == null ? null : buyerAccount.trim();
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency == null ? null : currency.trim();
    }

    public String getDepositBankNo() {
        return depositBankNo;
    }

    public void setDepositBankNo(String depositBankNo) {
        this.depositBankNo = depositBankNo == null ? null : depositBankNo.trim();
    }

    public String getGoodsTitle() {
        return goodsTitle;
    }

    public void setGoodsTitle(String goodsTitle) {
        this.goodsTitle = goodsTitle == null ? null : goodsTitle.trim();
    }

    public String getIncome() {
        return income;
    }

    public void setIncome(String income) {
        this.income = income == null ? null : income.trim();
    }

    public String getIwAccountLogId() {
        return iwAccountLogId;
    }

    public void setIwAccountLogId(String iwAccountLogId) {
        this.iwAccountLogId = iwAccountLogId == null ? null : iwAccountLogId.trim();
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo == null ? null : memo.trim();
    }

    public String getMerchantOutOrderNo() {
        return merchantOutOrderNo;
    }

    public void setMerchantOutOrderNo(String merchantOutOrderNo) {
        this.merchantOutOrderNo = merchantOutOrderNo == null ? null : merchantOutOrderNo.trim();
    }

    public String getOtherAccountEmail() {
        return otherAccountEmail;
    }

    public void setOtherAccountEmail(String otherAccountEmail) {
        this.otherAccountEmail = otherAccountEmail == null ? null : otherAccountEmail.trim();
    }

    public String getOtherAccountFullname() {
        return otherAccountFullname;
    }

    public void setOtherAccountFullname(String otherAccountFullname) {
        this.otherAccountFullname = otherAccountFullname == null ? null : otherAccountFullname.trim();
    }

    public String getOtherUserId() {
        return otherUserId;
    }

    public void setOtherUserId(String otherUserId) {
        this.otherUserId = otherUserId == null ? null : otherUserId.trim();
    }

    public String getOutcome() {
        return outcome;
    }

    public void setOutcome(String outcome) {
        this.outcome = outcome == null ? null : outcome.trim();
    }

    public String getSellerAccount() {
        return sellerAccount;
    }

    public void setSellerAccount(String sellerAccount) {
        this.sellerAccount = sellerAccount == null ? null : sellerAccount.trim();
    }

    public String getSellerFullname() {
        return sellerFullname;
    }

    public void setSellerFullname(String sellerFullname) {
        this.sellerFullname = sellerFullname == null ? null : sellerFullname.trim();
    }

    public String getServiceFee() {
        return serviceFee;
    }

    public void setServiceFee(String serviceFee) {
        this.serviceFee = serviceFee == null ? null : serviceFee.trim();
    }

    public String getServiceFeeRatio() {
        return serviceFeeRatio;
    }

    public void setServiceFeeRatio(String serviceFeeRatio) {
        this.serviceFeeRatio = serviceFeeRatio == null ? null : serviceFeeRatio.trim();
    }

    public String getTotalFee() {
        return totalFee;
    }

    public void setTotalFee(String totalFee) {
        this.totalFee = totalFee == null ? null : totalFee.trim();
    }

    public String getTradeRefundAmount() {
        return tradeRefundAmount;
    }

    public void setTradeRefundAmount(String tradeRefundAmount) {
        this.tradeRefundAmount = tradeRefundAmount == null ? null : tradeRefundAmount.trim();
    }

    public String getTransAccount() {
        return transAccount;
    }

    public void setTransAccount(String transAccount) {
        this.transAccount = transAccount == null ? null : transAccount.trim();
    }

    public String getTransCodeMsg() {
        return transCodeMsg;
    }

    public void setTransCodeMsg(String transCodeMsg) {
        this.transCodeMsg = transCodeMsg == null ? null : transCodeMsg.trim();
    }

    public Timestamp getTransDate() {
        return transDate;
    }

    public void setTransDate(Timestamp transDate) {
        this.transDate = transDate;
    }

    public String getSubTransCodeMsg() {
        return subTransCodeMsg;
    }

    public void setSubTransCodeMsg(String subTransCodeMsg) {
        this.subTransCodeMsg = subTransCodeMsg == null ? null : subTransCodeMsg.trim();
    }

    public String getSignProductName() {
        return signProductName;
    }

    public void setSignProductName(String signProductName) {
        this.signProductName = signProductName == null ? null : signProductName.trim();
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate == null ? null : rate.trim();
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", originalId=").append(originalId);
        sb.append(", checkDate=").append(checkDate);
        sb.append(", transOutOrderNo=").append(transOutOrderNo);
        sb.append(", tradeNo=").append(tradeNo);
        sb.append(", partnerId=").append(partnerId);
        sb.append(", balance=").append(balance);
        sb.append(", bankAccountName=").append(bankAccountName);
        sb.append(", bankAccountNo=").append(bankAccountNo);
        sb.append(", bankName=").append(bankName);
        sb.append(", buyerAccount=").append(buyerAccount);
        sb.append(", currency=").append(currency);
        sb.append(", depositBankNo=").append(depositBankNo);
        sb.append(", goodsTitle=").append(goodsTitle);
        sb.append(", income=").append(income);
        sb.append(", iwAccountLogId=").append(iwAccountLogId);
        sb.append(", memo=").append(memo);
        sb.append(", merchantOutOrderNo=").append(merchantOutOrderNo);
        sb.append(", otherAccountEmail=").append(otherAccountEmail);
        sb.append(", otherAccountFullname=").append(otherAccountFullname);
        sb.append(", otherUserId=").append(otherUserId);
        sb.append(", outcome=").append(outcome);
        sb.append(", sellerAccount=").append(sellerAccount);
        sb.append(", sellerFullname=").append(sellerFullname);
        sb.append(", serviceFee=").append(serviceFee);
        sb.append(", serviceFeeRatio=").append(serviceFeeRatio);
        sb.append(", totalFee=").append(totalFee);
        sb.append(", tradeRefundAmount=").append(tradeRefundAmount);
        sb.append(", transAccount=").append(transAccount);
        sb.append(", transCodeMsg=").append(transCodeMsg);
        sb.append(", transDate=").append(transDate);
        sb.append(", subTransCodeMsg=").append(subTransCodeMsg);
        sb.append(", signProductName=").append(signProductName);
        sb.append(", rate=").append(rate);
        sb.append(", createTime=").append(createTime);
        sb.append("]");
        return sb.toString();
    }
}