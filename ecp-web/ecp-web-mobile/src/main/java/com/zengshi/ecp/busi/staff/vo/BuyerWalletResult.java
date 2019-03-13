package com.zengshi.ecp.busi.staff.vo;

import java.io.Serializable;
import java.util.List;

public class BuyerWalletResult implements Serializable{
    /** 
     * serialVersionUID:TODO(用一句话描述这个变量表示什么). 
     * @since JDK 1.6 
     */ 
    private static final long serialVersionUID = -6288039663550712635L;

    private long total;

    private List<AcctInfoResVO> datas;

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public List<AcctInfoResVO> getDatas() {
        return datas;
    }

    public void setDatas(List<AcctInfoResVO> datas) {
        this.datas = datas;
    }
    
}

