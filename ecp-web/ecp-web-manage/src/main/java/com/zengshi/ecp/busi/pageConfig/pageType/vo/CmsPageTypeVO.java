package com.zengshi.ecp.busi.pageConfig.pageType.vo;

import java.io.Serializable;
import java.sql.Timestamp;

import org.springframework.format.annotation.DateTimeFormat;

import com.zengshi.ecp.base.vo.EcpBasePageReqVO;
import com.alibaba.fastjson.annotation.JSONField;

public class CmsPageTypeVO extends EcpBasePageReqVO implements Serializable{

    private Long id;

    private String pageTypeName;
    
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
    
    private String fieldStr;
    
    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPageTypeName() {
        return pageTypeName;
    }

    public void setPageTypeName(String pageTypeName) {
        this.pageTypeName = pageTypeName == null ? null : pageTypeName.trim();
    }

    public String getPlatformType() {
		return platformType;
	}

	public void setPlatformType(String platformType) {
        this.platformType = platformType == null ? null : platformType.trim();
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

	public String getFieldStr() {
		return fieldStr;
	}

	public void setFieldStr(String fieldStr) {
		this.fieldStr = fieldStr;
	}
    
    
}