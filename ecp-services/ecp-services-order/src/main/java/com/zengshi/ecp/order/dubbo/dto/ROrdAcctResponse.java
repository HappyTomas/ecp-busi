package com.zengshi.ecp.order.dubbo.dto;



import com.zengshi.ecp.server.front.dto.BaseInfo;

public class ROrdAcctResponse extends BaseInfo {

    private Long acctId;
    
    private String acctName;
    
    private Long balance;

    private static final long serialVersionUID = 1L;

    public Long getAcctId() {
        return acctId;
    }

    public void setAcctId(Long acctId) {
        this.acctId = acctId;
    }

    public String getAcctName() {
        return acctName;
    }

    public void setAcctName(String acctName) {
        this.acctName = acctName;
    }

    public Long getBalance() {
        return balance;
    }

    public void setBalance(Long balance) {
        this.balance = balance;
    }

 

    
    
}

