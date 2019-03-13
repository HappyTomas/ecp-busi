package com.zengshi.ecp.busi.order.pay.vo;

import java.sql.Timestamp;
import java.util.Date;

import org.apache.commons.lang.time.DateUtils;
import org.springframework.format.annotation.DateTimeFormat;

import com.zengshi.ecp.base.vo.EcpBasePageReqVO;
import com.zengshi.ecp.server.front.dto.BaseInfo;
import com.alibaba.fastjson.annotation.JSONField;

public class ROrderBackReqVO extends EcpBasePageReqVO {

    /** 
     * serialVersionUID:. 
     * @since JDK 1.6 
     */ 
    private static final long serialVersionUID = -7224146266120095042L;
    /**
	 * 开始时间
	 */
    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date begDate; 

	/**
     * 截止时间
     */
    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date endDate;
    /** 
     * siteId:所属站点. 
     * @since JDK 1.6 
     */ 
    private Long siteId;
    /** 
     * orderId:订单号. 
     * @since JDK 1.6 
     */ 
    private String orderId;
    
    /** 
     * tabFlag:查询状态. 
     * @since JDK 1.6 
     */ 
    private String tabFlag;
    
    /** 
     * backId:退货申请单. 
     * @since JDK 1.6 
     */ 
    private Long backId;
    
    /** 
     * applyType:申请类型. 
     * @since JDK 1.6 
     */ 
    private String applyType;
    
    
    /** 
     * shopId:卖家ID. 
     * @since JDK 1.6 
     */ 
    private Long shopId;
    
    
    public Date getBegDate() {
		return begDate;
	}

	public void setBegDate(Date begDate) {
		this.begDate = begDate;
	}

	public Date getEndDate() {
		 return DateUtils.addDays(endDate, 1);
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public String getApplyType() {
        return applyType;
    }

    public void setApplyType(String applyType) {
        this.applyType = applyType;
    }

    public Long getBackId() {
        return backId;
    }

    public void setBackId(Long backId) {
        this.backId = backId;
    }

     
    public void setEndDate(Timestamp endDate) {
        this.endDate = endDate;
    }

    public Long getSiteId() {
        return siteId;
    }

    public void setSiteId(Long siteId) {
        this.siteId = siteId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getTabFlag() {
        return tabFlag;
    }

    public void setTabFlag(String tabFlag) {
        this.tabFlag = tabFlag;
    }
    
    
}

