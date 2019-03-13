package com.zengshi.ecp.app.req;

import java.sql.Date;

import com.zengshi.butterfly.app.model.IBody;

public class Ord012Req extends IBody {
    
    /** 
     * pageNo:请求查询的页码. 
     * @since JDK 1.6 
     */ 
    private int pageNo = 1;

    /** 
     * pageSize:每页显示条数. 
     * @since JDK 1.6 
     */ 
    private int pageSize;

    /** 
     * count:总条数. 
     * @since JDK 1.6 
     */ 
    private long count = 0;
    /** 
     * begDate:查询开始时间. 
     * @since JDK 1.6 
     */ 
    private Date begDate;

    /** 
     * endDate:查询结束时间. 
     * @since JDK 1.6 
     */ 
    private Date endDate;

    /** 
     * status:订单状态.  // 00-全部01-待付款02-待发货03-待收货04-已收货05-已取消
     * @since JDK 1.6 
     */ 
    private String status;

    /** 
     * siteId:站点ID. 
     * @since JDK 1.6 
     */ 
    private Long siteId;

    private Long shopId;


    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getSiteId() {
        return siteId;
    }

    public void setSiteId(Long siteId) {
        this.siteId = siteId;
    }

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }
}

