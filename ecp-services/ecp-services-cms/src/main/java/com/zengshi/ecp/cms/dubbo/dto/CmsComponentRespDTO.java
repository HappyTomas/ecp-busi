package com.zengshi.ecp.cms.dubbo.dto;

import java.sql.Timestamp;

import com.zengshi.ecp.server.front.dto.BaseResponseDTO;

/**
 * Title: ECP <br>
 * Project Name:ecp-services-cms <br>
 * Description: 作为响应参数类<br>
 * Date:2015年8月7日下午5:07:27  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author jiangzh
 * @version  
 * @param <T>
 * @since JDK 1.6 
 */  
public class CmsComponentRespDTO extends BaseResponseDTO {
    
    /*--------------------------以下为model后添加的字段 start--------------------------*/
    /**
     * 状态翻译
     */
    private String statusZH;
    /**
     * 组件分类翻译
     */
    private String componentClassZH;

    /*--------------------------以下为model后添加的字段 end------------------------*/
    private Long id;

    private String componentClass;

    private String componentName;

    private String componentMethod;

    private String showPic;

    private String componentUrl;

    private String status;

    private String remark;

    private Long createStaff;

    private Timestamp createTime;

    private Long updateStaff;

    private Timestamp updateTime;

    private String componentVmUrl;

    private String componentEditUrl;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getComponentClass() {
        return componentClass;
    }

    public void setComponentClass(String componentClass) {
        this.componentClass = componentClass == null ? null : componentClass.trim();
    }

    public String getComponentName() {
        return componentName;
    }

    public void setComponentName(String componentName) {
        this.componentName = componentName == null ? null : componentName.trim();
    }

    public String getComponentMethod() {
        return componentMethod;
    }

    public void setComponentMethod(String componentMethod) {
        this.componentMethod = componentMethod == null ? null : componentMethod.trim();
    }

    public String getShowPic() {
        return showPic;
    }

    public void setShowPic(String showPic) {
        this.showPic = showPic == null ? null : showPic.trim();
    }

    public String getComponentUrl() {
        return componentUrl;
    }

    public void setComponentUrl(String componentUrl) {
        this.componentUrl = componentUrl == null ? null : componentUrl.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public Long getCreateStaff() {
        return createStaff;
    }

    public void setCreateStaff(Long createStaff) {
        this.createStaff = createStaff;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public Long getUpdateStaff() {
        return updateStaff;
    }

    public void setUpdateStaff(Long updateStaff) {
        this.updateStaff = updateStaff;
    }

    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }

    public String getComponentVmUrl() {
        return componentVmUrl;
    }

    public void setComponentVmUrl(String componentVmUrl) {
        this.componentVmUrl = componentVmUrl == null ? null : componentVmUrl.trim();
    }

    public String getComponentEditUrl() {
        return componentEditUrl;
    }

    public void setComponentEditUrl(String componentEditUrl) {
        this.componentEditUrl = componentEditUrl == null ? null : componentEditUrl.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", componentClass=").append(componentClass);
        sb.append(", componentName=").append(componentName);
        sb.append(", componentMethod=").append(componentMethod);
        sb.append(", showPic=").append(showPic);
        sb.append(", componentUrl=").append(componentUrl);
        sb.append(", status=").append(status);
        sb.append(", remark=").append(remark);
        sb.append(", createStaff=").append(createStaff);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateStaff=").append(updateStaff);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", componentVmUrl=").append(componentVmUrl);
        sb.append(", componentEditUrl=").append(componentEditUrl);
        sb.append("]");
        return sb.toString();
    }

    public String getStatusZH() {
        return statusZH;
    }

    public void setStatusZH(String statusZH) {
        this.statusZH = statusZH;
    }

    public String getComponentClassZH() {
        return componentClassZH;
    }

    public void setComponentClassZH(String componentClassZH) {
        this.componentClassZH = componentClassZH;
    }
}

