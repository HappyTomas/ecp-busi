package com.zengshi.ecp.staff.dao.model;

import java.io.Serializable;

public class AcctTradeShopIDX implements Serializable {
    private Long id;

    private Long shopId;

    private Long staffId;

    private String acctType;

    private String tradeType;

    private String debitCredit;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public Long getStaffId() {
        return staffId;
    }

    public void setStaffId(Long staffId) {
        this.staffId = staffId;
    }

    public String getAcctType() {
        return acctType;
    }

    public void setAcctType(String acctType) {
        this.acctType = acctType == null ? null : acctType.trim();
    }

    public String getTradeType() {
        return tradeType;
    }

    public void setTradeType(String tradeType) {
        this.tradeType = tradeType == null ? null : tradeType.trim();
    }

    public String getDebitCredit() {
        return debitCredit;
    }

    public void setDebitCredit(String debitCredit) {
        this.debitCredit = debitCredit == null ? null : debitCredit.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", shopId=").append(shopId);
        sb.append(", staffId=").append(staffId);
        sb.append(", acctType=").append(acctType);
        sb.append(", tradeType=").append(tradeType);
        sb.append(", debitCredit=").append(debitCredit);
        sb.append("]");
        return sb.toString();
    }
}