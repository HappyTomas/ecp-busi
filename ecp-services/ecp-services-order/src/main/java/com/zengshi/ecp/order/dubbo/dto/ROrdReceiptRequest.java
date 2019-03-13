package com.zengshi.ecp.order.dubbo.dto;

import com.zengshi.ecp.server.front.dto.BaseInfo;

public class ROrdReceiptRequest extends BaseInfo {

    /** 
     * serialVersionUID:. 
     * @since JDK 1.6 
     */ 
    private static final long serialVersionUID = -8770968139138557654L;
    
    /** 
     * shopId:卖家ID
     * @since JDK 1.6 
     */ 
    private Long shopId;
    /** 
     * staffId:买家ID. 
     * @since JDK 1.6 
     */ 
    private Long staffId;
    /** 
     * orderId:订单号. 
     * @since JDK 1.6 
     */ 
    private String orderId;
    
    /** 
     * companyId:企业ID. 
     * @since JDK 1.6 
     */ 
    private Long companyId;
    
    /**
     * count:要获取的订单数量
     * @since JDK 1.6 
     */
    private int count;
    
    /**
     * tableIndex:指定分库
     * @since JDK 1.6 
     */
    private int tableIndex;
    
    public Long getCompanyId() {
        return companyId;
    }
    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
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
    public String getOrderId() {
        return orderId;
    }
    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }
    public int getCount() {
        return count;
    }
    public void setCount(int count) {
        this.count = count;
    }
    public int getTableIndex() {
        return tableIndex;
    }
    public void setTableIndex(int tableIndex) {
        this.tableIndex = tableIndex;
    }
}

