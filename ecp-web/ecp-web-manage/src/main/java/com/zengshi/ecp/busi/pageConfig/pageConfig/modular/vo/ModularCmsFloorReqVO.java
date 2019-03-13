package com.zengshi.ecp.busi.pageConfig.pageConfig.modular.vo;

import java.io.Serializable;

import com.zengshi.ecp.base.vo.EcpBasePageReqVO;

/**
 * Title: ECP <br>
 * Project Name:ecp-web-manage <br>
 * Description: <br>
 * Date:2015年8月21日下午5:49:47  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author jiangzh
 * @version  
 * @since JDK 1.6 
 */  
public class ModularCmsFloorReqVO extends EcpBasePageReqVO implements Serializable{
    
    /** 
     * serialVersionUID:
     * @since JDK 1.6 
     */ 
    private Long id;
    
    private Long tabId;

    private String status;
    
    private Long placeId;

    private Long siteId;
    
    private String dataSource;

    private String countType;

    private String catgCode;
    
    private Long startRowIndex;//下次查询的下标
    
    private Long imgWidth;//商品主图宽度
    
    private Long imgHeight;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTabId() {
        return tabId;
    }

    public void setTabId(Long tabId) {
        this.tabId = tabId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getPlaceId() {
        return placeId;
    }

    public void setPlaceId(Long placeId) {
        this.placeId = placeId;
    }

    public Long getSiteId() {
        return siteId;
    }

    public void setSiteId(Long siteId) {
        this.siteId = siteId;
    }

    public String getDataSource() {
        return dataSource;
    }

    public void setDataSource(String dataSource) {
        this.dataSource = dataSource;
    }

    public String getCountType() {
        return countType;
    }

    public void setCountType(String countType) {
        this.countType = countType;
    }

    public String getCatgCode() {
        return catgCode;
    }

    public void setCatgCode(String catgCode) {
        this.catgCode = catgCode;
    }

    public Long getStartRowIndex() {
        return startRowIndex;
    }

    public void setStartRowIndex(Long startRowIndex) {
        this.startRowIndex = startRowIndex;
    }

    public Long getImgWidth() {
        return imgWidth;
    }

    public void setImgWidth(Long imgWidth) {
        this.imgWidth = imgWidth;
    }

    public Long getImgHeight() {
        return imgHeight;
    }

    public void setImgHeight(Long imgHeight) {
        this.imgHeight = imgHeight;
    }
}

