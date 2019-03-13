package com.zengshi.ecp.busi.seller.order.vo;

import java.util.Date;

import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import com.zengshi.ecp.base.vo.EcpBasePageReqVO;
import com.alibaba.fastjson.annotation.JSONField;

public class ROrdBackReqVO extends EcpBasePageReqVO {

	/** 
	 * serialVersionUID:. 
	 * @since JDK 1.6 
	 */ 
	private static final long serialVersionUID = -1025452233121412401L;
	
    /** 
     * begDate:开始时间. 
     * @since JDK 1.6 
     */ 
    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @NotNull(message="{order.orderdate.null.error}")
    private Date begDate;

    /** 
     * endDate:截止时间. 
     * @since JDK 1.6 
     */ 
    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @NotNull(message="{order.orderdate.null.error}")
    private Date endDate;
    
    /** 
     * orderId:订单编号. 
     * @since JDK 1.6 
     */ 
    @NotNull(message="{order.orderid.null.error}")
    private String orderId;
    
    /** 
     * siteId:站点信息 . 
     * @since JDK 1.6 
     */ 
    private Long siteId;
    
    /** 
     * shopId:卖家ID. 
     * @since JDK 1.6 
     */ 
    private Long shopId;
    
    /** 
     * tabFlag:状态标志. 
     * @since JDK 1.6 
     */ 
    private String tabFlag;
    
    /** 
     * status:申请单状态. 
     * @since JDK 1.6 
     */ 
    private String status;
    

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public String getTabFlag() {
        return tabFlag;
    }

    public void setTabFlag(String tabFlag) {
        this.tabFlag = tabFlag;
    }

    public Date getBegDate() {
        return begDate;
    }

    public void setBegDate(Date begDate) {
        this.begDate = begDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Long getSiteId() {
        return siteId;
    }

    public void setSiteId(Long siteId) {
        this.siteId = siteId;
    }

}
