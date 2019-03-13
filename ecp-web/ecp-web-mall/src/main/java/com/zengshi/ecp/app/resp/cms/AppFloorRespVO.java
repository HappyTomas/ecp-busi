package com.zengshi.ecp.app.resp.cms;

import java.util.List;

import com.zengshi.butterfly.app.model.IBody;

public class AppFloorRespVO extends IBody {
    private Long id;
    
    private String floorName;
    
    private String linkUrl;
    
    private Long floorTemplateId;
    
    private List<AppFloorDataRespVO> floorDataList;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFloorName() {
        return floorName;
    }

    public void setFloorName(String floorName) {
        this.floorName = floorName;
    }

    public String getLinkUrl() {
        return linkUrl;
    }

    public void setLinkUrl(String linkUrl) {
        this.linkUrl = linkUrl;
    }

    public Long getFloorTemplateId() {
        return floorTemplateId;
    }

    public void setFloorTemplateId(Long floorTemplateId) {
        this.floorTemplateId = floorTemplateId;
    }

    public List<AppFloorDataRespVO> getFloorDataList() {
        return floorDataList;
    }

    public void setFloorDataList(List<AppFloorDataRespVO> floorDataList) {
        this.floorDataList = floorDataList;
    }
    
    
}

