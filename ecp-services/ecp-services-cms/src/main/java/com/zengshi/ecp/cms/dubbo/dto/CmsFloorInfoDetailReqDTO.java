package com.zengshi.ecp.cms.dubbo.dto;

import java.util.Arrays;

import com.zengshi.ecp.cms.dubbo.util.CmsOption.FloorQueryOption;
import com.zengshi.ecp.server.front.dto.BaseInfo;

/**
 * 楼层详情信息请求对象
 * Title: ECP <br>
 * Project Name:ecp-services-cms-server-core <br>
 * Description: <br>
 * Date:2016年7月12日上午10:09:37  <br>
 * Copyright (c) 2016 ZengShi All Rights Reserved <br>
 * 
 * @author zhanbh
 * @version  
 * @since JDK 1.6
 */
public class CmsFloorInfoDetailReqDTO extends BaseInfo  {
    
    /** 
     * serialVersionUID:TODO(用一句话描述这个变量表示什么). 
     * @since JDK 1.6 
     */ 
    private static final long serialVersionUID = -6411700161600351028L;

    /**
     * 楼层id  必传
     */
    private Long floorId;
    
    /**
     * 楼层id  必传
     */
    private Long tabId;
    
    /**
     * 楼层优惠券个数
     */
    private Integer couponSize;
    /**
     * 楼层商品个数
     */
    private Integer gdsSize;
    /**
     * 楼层标签个数
     */
    private Integer labelSize;
    /**
     * 楼层页签个数
     */
    private Integer tabSize;
    /**
     * 楼层广告个数
     */
    private Integer adSize;
    
    public Long getFloorId() {
        return floorId;
    }
    public void setFloorId(Long floorId) {
        this.floorId = floorId;
    }
    public Long getTabId() {
        return tabId;
    }
    public void setTabId(Long tabId) {
        this.tabId = tabId;
    }
    public Integer getCouponSize() {
        return couponSize;
    }
    public void setCouponSize(Integer couponSize) {
        this.couponSize = couponSize;
    }
    public Integer getGdsSize() {
        return gdsSize;
    }
    public void setGdsSize(Integer gdsSize) {
        this.gdsSize = gdsSize;
    }
    public Integer getLabelSize() {
        return labelSize;
    }
    public void setLabelSize(Integer labelSize) {
        this.labelSize = labelSize;
    }
    public Integer getTabSize() {
        return tabSize;
    }
    public void setTabSize(Integer tabSize) {
        this.tabSize = tabSize;
    }
    public Integer getAdSize() {
        return adSize;
    }
    public void setAdSize(Integer adSize) {
        this.adSize = adSize;
    }
    @Override
    public String toString() {
        return "CmsFloorInfoDetailReqDTO [floorId=" + floorId + ", tabId=" + tabId + ", couponSize=" + couponSize + ", gdsSize="
                + gdsSize + ", labelSize=" + labelSize + ", tabSize=" + tabSize + ", adSize=" + adSize +"]";
    }
    
    
}

