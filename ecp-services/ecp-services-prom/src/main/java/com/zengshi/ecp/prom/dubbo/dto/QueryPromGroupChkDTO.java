package com.zengshi.ecp.prom.dubbo.dto;


import com.zengshi.ecp.server.front.dto.BaseInfo;

public class QueryPromGroupChkDTO extends BaseInfo {

    private static final long serialVersionUID = 1L;

    private Long groupId;//主题促销编码
    
    private String promTheme;//主题名称
    
    private Long shopId;//店铺id 如果通过店铺名称查询 需要转为id
    
    private String statusGroup;//主题促销状态

    private String chkStatus;//审核状态
    
    private Long siteId;//站点

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    public String getPromTheme() {
        return promTheme;
    }

    public void setPromTheme(String promTheme) {
        this.promTheme = promTheme;
    }

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public String getChkStatus() {
        return chkStatus;
    }

    public void setChkStatus(String chkStatus) {
        this.chkStatus = chkStatus;
    }

    public String getStatusGroup() {
        return statusGroup;
    }

    public void setStatusGroup(String statusGroup) {
        this.statusGroup = statusGroup;
    }

    public Long getSiteId() {
        return siteId;
    }

    public void setSiteId(Long siteId) {
        this.siteId = siteId;
    }

 

}