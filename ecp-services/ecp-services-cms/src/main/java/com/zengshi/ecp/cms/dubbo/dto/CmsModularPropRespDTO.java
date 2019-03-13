package com.zengshi.ecp.cms.dubbo.dto;

import java.sql.Timestamp;

import com.zengshi.ecp.server.front.dto.BaseResponseDTO;

public class CmsModularPropRespDTO extends BaseResponseDTO {
	private Long id;

    private Long modularId;
    
    private String modularName;

    private Long propId;
    
    private String propName;
    
    private String propValueType; 
    
    /**
     * 属性值类型翻译
     */
    private String propValueTypeZH;

    private Integer sortNo;

    private String status;
    
    private String statusZH;

    private Long createStaff;

    private Timestamp createTime;

    private Long updateStaff;

    private Timestamp updateTime;

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

	public String getModularName() {
		return modularName;
	}

	public void setModularName(String modularName) {
		this.modularName = modularName == null ? null : modularName.trim();
	}

	public String getPropName() {
		return propName;
	}

	public void setPropName(String propName) {
		this.propName = propName == null ? null : propName.trim();
	}
	
	public String getPropValueType() {
		return propValueType;
	}

	public void setPropValueType(String propValueType) {
		this.propValueType = propValueType == null ? null : propValueType.trim();
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

    public String getStatusZH() {
		return statusZH;
	}

	public void setStatusZH(String statusZH) {
		this.statusZH = statusZH;
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

    public String getPropValueTypeZH() {
		return propValueTypeZH;
	}

	public void setPropValueTypeZH(String propValueTypeZH) {
		this.propValueTypeZH = propValueTypeZH == null ? null : propValueTypeZH.trim();
	}

	@Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", modularId=").append(modularId);
        sb.append(", modularName=").append(modularName);
        sb.append(", propId=").append(propId);
        sb.append(", propName=").append(propName);
        sb.append(", propValueType=").append(propValueType);
        sb.append(", propValueTypeZH=").append(propValueTypeZH);
        sb.append(", sortNo=").append(sortNo);
        sb.append(", status=").append(status);
        sb.append(", statusZH=").append(statusZH);
        sb.append(", createStaff=").append(createStaff);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateStaff=").append(updateStaff);
        sb.append(", updateTime=").append(updateTime);
        sb.append("]");
        return sb.toString();
    }
}
