package com.zengshi.ecp.busi.cms.component.vo;

import java.sql.Timestamp;
import java.io.Serializable;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import com.zengshi.ecp.base.vo.EcpBasePageReqVO;
import com.alibaba.fastjson.annotation.JSONField;

/**
 * Title: ECP <br>
 * Project Name:ecp-web-manage <br>
 * Description: <br>
 * Date:2015年8月21日下午5:49:47  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author huangxm9
 * @version  
 * @since JDK 1.7
 */  

public class CmsComponentVO  extends EcpBasePageReqVO implements Serializable{
    
    private Long id;

    @NotNull(message="{cms.component.componentClass.null.error}")
    private String componentClass;

    @NotNull(message="{cms.component.componentName.null.error}")
    @Length(max=20, message="{cms.component.componentName.length.error}")
    private String componentName;

    @Length(max=256, message="{cms.component.componentMethod.length.error}")
    private String componentMethod;

    private String showPic;

    @Length(max=256, message="{cms.component.componentUrl.length.error}")
    private String componentUrl;

    private String status;

    private String remark;

    private Long createStaff;

    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Timestamp createTime;

    private Long updateStaff;

    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd")
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

}
