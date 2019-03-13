package com.zengshi.ecp.busi.pageConfig.prop.vo;

import java.io.Serializable;
import java.sql.Timestamp;

import org.springframework.format.annotation.DateTimeFormat;

import com.zengshi.ecp.base.vo.EcpBasePageReqVO;
import com.alibaba.fastjson.annotation.JSONField;

public class CmsModularPropVO extends EcpBasePageReqVO implements Serializable{
	
    private Long id;

    private Long modularId;
    
    private Long propId;

    private Integer sortNo;

    private String status;

    private Long createStaff;
    
    private String paraPropValStr;

    @JSONField(format="yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Timestamp createTime;

    private Long updateStaff;

    @JSONField(format="yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Timestamp updateTime;
    
    private String propStr;
    
    private String isAutobuild;
    
    private String selectPropIds;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getModularId() {
        return modularId;
    }

    public void setModularId(Long modularId) {
        this.modularId = modularId;
    }

    public Long getPropId() {
		return propId;
	}

	public void setPropId(Long propId) {
		this.propId = propId;
	}

	public Integer getSortNo() {
        return sortNo;
    }

    public void setSortNo(Integer sortNo) {
        this.sortNo = sortNo;
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

	public String getParaPropValStr() {
		return paraPropValStr;
	}

	public void setParaPropValStr(String paraPropValStr) {
		this.paraPropValStr = paraPropValStr;
	}

	public String getPropStr() {
		return propStr;
	}

	public void setPropStr(String propStr) {
		this.propStr = propStr;
	}
    
	public String getIsAutobuild() {
        return isAutobuild;
    }

    public void setIsAutobuild(String isAutobuild) {
        this.isAutobuild = isAutobuild;
    }

	public String getSelectPropIds() {
		return selectPropIds;
	}

	public void setSelectPropIds(String selectPropIds) {
		this.selectPropIds = selectPropIds;
	}
    
    
}