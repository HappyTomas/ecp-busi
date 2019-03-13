package com.zengshi.ecp.prom.dubbo.dto;

import java.math.BigDecimal;

/**
 * Title: AI-ECP <br>
 * Description: <br>
 * Date: 2015-10-29 <br>
 * Copyright (c) 2015 AI <br>
 * 
 * @author huangjx
 */
public class OrdPostDTO extends CommPromTypeDTO {

    private static final long serialVersionUID = 1L;

    private BigDecimal orderMoney;// 满足金额
    
    private String postAreaCode;//区域列表数据

    private String postAreaExclude;//1表示 除选中之外
    
    private String ifSelectAll;//1 全部区域 否则部分区域
    
    public BigDecimal getOrderMoney() {
        return orderMoney;
    }

    public void setOrderMoney(BigDecimal orderMoney) {
        this.orderMoney = orderMoney;
    }

    public String getPostAreaCode() {
        return postAreaCode;
    }

    public void setPostAreaCode(String postAreaCode) {
        this.postAreaCode = postAreaCode;
    }

    public String getPostAreaExclude() {
        return postAreaExclude;
    }

    public void setPostAreaExclude(String postAreaExclude) {
        this.postAreaExclude = postAreaExclude;
    }

    public String getIfSelectAll() {
        return ifSelectAll;
    }

    public void setIfSelectAll(String ifSelectAll) {
        this.ifSelectAll = ifSelectAll;
    }

     
}
