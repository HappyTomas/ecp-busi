package com.zengshi.ecp.busi.pageConfig.pageConfig.modular.vo;

import java.util.List;

import com.zengshi.ecp.base.vo.EcpBasePageReqVO;
import com.zengshi.ecp.cms.dubbo.dto.CmsFloorAdvertiseRespDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsFloorGdsRespDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsFloorLabelRespDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsFloorRespDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsFloorTabRespDTO;
import com.zengshi.ecp.coupon.dubbo.dto.resp.CoupInfoRespDTO;

/**
 * 楼层详情信息返回对象
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
public class CmsFloorInfoDetailRespVO extends EcpBasePageReqVO  {

    /** 
     * serialVersionUID:TODO(用一句话描述这个变量表示什么). 
     * @since JDK 1.6 
     */ 
    private static final long serialVersionUID = -6817547030652562757L;

    /**
     * 楼层基本信息
     */
    private CmsFloorRespDTO floorBaseInfo ;
    /**
     * 楼层页签列表
     */
    private List<CmsFloorTabRespDTO> floorTabList;
    /**
     * 楼层广告
     */
    private List<CmsFloorAdvertiseRespDTO> floorAdList;
    /**
     * 楼层标签列表
     */
    private List<CmsFloorLabelRespDTO> floorLabelList;
    /**
     * 楼层优惠券列表
     */
    private List<CoupInfoRespDTO> couponList;
    /**
     * 楼层广告列表
     */
    private List<CmsFloorGdsRespDTO> gdsList;
    
    
    public CmsFloorRespDTO getFloorBaseInfo() {
        return floorBaseInfo;
    }
    public void setFloorBaseInfo(CmsFloorRespDTO floorBaseInfo) {
        this.floorBaseInfo = floorBaseInfo;
    }
    public List<CmsFloorTabRespDTO> getFloorTabList() {
        return floorTabList;
    }
    public void setFloorTabList(List<CmsFloorTabRespDTO> floorTabList) {
        this.floorTabList = floorTabList;
    }
    public List<CmsFloorAdvertiseRespDTO> getFloorAdList() {
        return floorAdList;
    }
    public void setFloorAdList(List<CmsFloorAdvertiseRespDTO> floorAdList) {
        this.floorAdList = floorAdList;
    }
    public List<CmsFloorLabelRespDTO> getFloorLabelList() {
        return floorLabelList;
    }
    public void setFloorLabelList(List<CmsFloorLabelRespDTO> floorLabelList) {
        this.floorLabelList = floorLabelList;
    }
    public List<CoupInfoRespDTO> getCouponList() {
        return couponList;
    }
    public void setCouponList(List<CoupInfoRespDTO> couponList) {
        this.couponList = couponList;
    }
    public List<CmsFloorGdsRespDTO> getGdsList() {
        return gdsList;
    }
    public void setGdsList(List<CmsFloorGdsRespDTO> gdsList) {
        this.gdsList = gdsList;
    }
    
    
    
}

