package com.zengshi.ecp.app.resp.cms;

import com.zengshi.ecp.app.resp.gds.GdsBaseInfo;
import com.zengshi.butterfly.app.model.IBody;

public class AppFloorDataRespVO extends IBody {
    /**
     * 楼层数据id
     */
    private Long id;
    
    /**
     * 楼层数据名称  
     */
    private String name;
    
    /**
     * 楼层数据图片地址
     */
    private String vfsUrl;
    
    /**
     * 楼层数据类型
     */
    private String linkType;
    
    /**
     * 楼层数据链接地址
     */
    private String linkUrl;
    
    /**
     * 楼层数据对应模板内容位置的排序
     */
    private Integer sortNo;

    /**
     * 商品基本数据
     */
    GdsBaseInfo gdsInfo ;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVfsUrl() {
        return vfsUrl;
    }

    public void setVfsUrl(String vfsUrl) {
        this.vfsUrl = vfsUrl;
    }

    public String getLinkType() {
        return linkType;
    }

    public void setLinkType(String linkType) {
        this.linkType = linkType;
    }

    public String getLinkUrl() {
        return linkUrl;
    }

    public void setLinkUrl(String linkUrl) {
        this.linkUrl = linkUrl;
    }

    public Integer getSortNo() {
        return sortNo;
    }

    public void setSortNo(Integer sortNo) {
        this.sortNo = sortNo;
    }

    public GdsBaseInfo getGdsInfo() {
        return gdsInfo;
    }

    public void setGdsInfo(GdsBaseInfo gdsInfo) {
        this.gdsInfo = gdsInfo;
    }
    
    

}

