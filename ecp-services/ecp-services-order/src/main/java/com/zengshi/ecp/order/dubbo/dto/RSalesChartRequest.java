package com.zengshi.ecp.order.dubbo.dto;

import java.util.List;

import com.zengshi.ecp.server.front.dto.BaseInfo;

public class RSalesChartRequest extends BaseInfo {

    /** 
     * serialVersionUID:. 
     * @since JDK 1.6 
     */ 
    private static final long serialVersionUID = -1770765256925425319L;
    
    /** 
     * siteId:站点. 
     * @since JDK 1.6 
     */ 
    private Long siteId;
    
    /** 
     * topNum:前几位. 
     * @since JDK 1.6 
     */ 
    private int topNum;

    public Long getSiteId() {
        return siteId;
    }

    public void setSiteId(Long siteId) {
        this.siteId = siteId;
    }

    public int getTopNum() {
        return topNum;
    }

    public void setTopNum(int topNum) {
        this.topNum = topNum;
    }



}

