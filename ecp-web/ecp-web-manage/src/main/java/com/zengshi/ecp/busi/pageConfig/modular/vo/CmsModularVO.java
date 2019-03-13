package com.zengshi.ecp.busi.pageConfig.modular.vo;

import java.io.Serializable;
import java.sql.Timestamp;

import org.springframework.format.annotation.DateTimeFormat;

import com.zengshi.ecp.base.vo.EcpBasePageReqVO;
import com.alibaba.fastjson.annotation.JSONField;

public class CmsModularVO extends EcpBasePageReqVO implements Serializable{
    private Long id;

    private String modularName;
    
    private String modularType;
    
    private String platformType;

    private String status;

    private Long createStaff;
    
    @JSONField(format="yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Timestamp createTime;

    private Long updateStaff;
    
    @JSONField(format="yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Timestamp updateTime;
    
    //模块与组件关系字段冗余 --start 
    private String applyPageType;
    
    private String applyItemSize;

    private String showPic;
    
    private Long componentId;
    
    private String modularClass;
    //模块与组件关系字段冗余 --end 

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getModularName() {
        return modularName;
    }

    public void setModularName(String modularName) {
        this.modularName = modularName == null ? null : modularName.trim();
    }

    public String getApplyPageType() {
        return applyPageType;
    }

    public void setApplyPageType(String applyPageType) {
        this.applyPageType = applyPageType == null ? null : applyPageType.trim();
    }

    public String getApplyItemSize() {
        return applyItemSize;
    }

    public void setApplyItemSize(String applyItemSize) {
        this.applyItemSize = applyItemSize == null ? null : applyItemSize.trim();
    }

    public String getShowPic() {
        return showPic;
    }

    public void setShowPic(String showPic) {
        this.showPic = showPic == null ? null : showPic.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
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

	public String getModularType() {
		return modularType;
	}

	public void setModularType(String modularType) {
		this.modularType = modularType;
	}
	
	public Long getComponentId() {
        return componentId;
    }

    public void setComponentId(Long componentId) {
        this.componentId = componentId;
    }
    
    public String getModularClass() {
        return modularClass;
    }

    public void setModularClass(String modularClass) {
        this.modularClass = modularClass;
    }
    
    public String getPlatformType() {
        return platformType;
    }

    public void setPlatformType(String platformType) {
        this.platformType = platformType;
    }
    
}