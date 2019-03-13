package com.zengshi.ecp.staff.dubbo.dto;

import java.io.Serializable;

import com.zengshi.ecp.server.front.dto.BaseResponseDTO;

public class AcctSumResDTO extends BaseResponseDTO implements Serializable {
    
    /** 
     * serialVersionUID:TODO(用一句话描述这个变量表示什么). 
     * @since JDK 1.6 
     */ 
    private static final long serialVersionUID = 1L;

    private Long totalMoney;
    
    private Long balance;
    
    private Long freezeMoney;

    public AcctSumResDTO(){
        this.totalMoney = 0L;
        this.balance = 0L;
        this.freezeMoney = 0L;
    }
    
    public Long getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(Long totalMoney) {
        this.totalMoney = totalMoney;
    }

    public Long getBalance() {
        return balance;
    }

    public void setBalance(Long balance) {
        this.balance = balance;
    }

    public Long getFreezeMoney() {
        return freezeMoney;
    }

    public void setFreezeMoney(Long freezeMoney) {
        this.freezeMoney = freezeMoney;
    }
    
    

}

