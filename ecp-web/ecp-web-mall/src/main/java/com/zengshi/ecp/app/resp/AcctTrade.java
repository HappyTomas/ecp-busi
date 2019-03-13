/** 
 * Project Name:ecp-web-mall 
 * File Name:DemoResp.java 
 * Package Name:com.zengshi.ecp.app.resp 
 * Date:2016-2-22下午6:53:17 
 * Copyright (c) 2016, ZengShi All Rights Reserved. 
 * 
 */ 
package com.zengshi.ecp.app.resp;

import java.sql.Timestamp;


/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-web-mall Maven Webapp <br>
 * Description: 资金账户交易明细javabean，用于Staff104Resp的返回对象List的元素<br>
 * Date:2016-3-4下午4:49:08  <br>
 * Copyright (c) 2016 ZengShi All Rights Reserved <br>
 * 
 * @author huangxl5
 * @version  
 * @since JDK 1.6
 */
public class AcctTrade {
    
    private Long tradeMoney;//交易金额（分）
   
    private String tradeTypeName;//交易类型名称
   
    private String debitCredit;//交易方向：1：收入、2：支出 
   
    private String orderId;//订单编码
    
    private Timestamp createTime;//创建时间

    public Long getTradeMoney() {
        return tradeMoney;
    }
    
    public void setTradeMoney(Long tradeMoney) {
        this.tradeMoney = tradeMoney;
    }
    
    public String getTradeTypeName() {
        return tradeTypeName;
    }
    
    public void setTradeTypeName(String tradeTypeName) {
        this.tradeTypeName = tradeTypeName;
    }
    
    public String getDebitCredit() {
        return debitCredit;
    }
    
    public void setDebitCredit(String debitCredit) {
        this.debitCredit = debitCredit;
    }
    
    public String getOrderId() {
        return orderId;
    }
    
    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }
       
}

