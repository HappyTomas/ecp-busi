package com.zengshi.ecp.goods.dubbo.dto;

import java.sql.Timestamp;
import java.util.List;

import com.zengshi.ecp.server.front.dto.BaseInfo;

@SuppressWarnings("rawtypes")
public class GdsPropReqDTO extends BaseInfo {
    /** 
     * serialVersionUID:TODO(用一句话描述这个变量表示什么). 
     * @since JDK 1.6 
     */ 
    private static final long serialVersionUID = 8824307999109246614L;
    

    private Long id;

    private String propName;

    private String propSname;
    
    /**
     * 属性值列表
     */
    private List<GdsPropValueReqDTO> propValues;
    
    private String propValueType;

    private String propType;

    private String propDesc;

    private Integer sortNo;

    private String status;

    private Timestamp createTime;

    private Long createStaff;

    private Timestamp updateTime;

    private Long updateStaff;
    
    private String propInputType;

    private String propInputRule;
    
    private String ifAbleEdit;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPropName() {
        return propName;
    }

    public void setPropName(String propName) {
        this.propName = propName == null ? null : propName.trim();
    }

    public String getPropSname() {
        return propSname;
    }

    public void setPropSname(String propSname) {
        this.propSname = propSname == null ? null : propSname.trim();
    }

    public List<GdsPropValueReqDTO> getPropValues() {
		return propValues;
	}

	public void setPropValues(List<GdsPropValueReqDTO> propValues) {
		this.propValues = propValues;
	}

	public String getPropValueType() {
        return propValueType;
    }

    public void setPropValueType(String propValueType) {
        this.propValueType = propValueType == null ? null : propValueType.trim();
    }

    public String getPropType() {
        return propType;
    }

    public void setPropType(String propType) {
        this.propType = propType == null ? null : propType.trim();
    }

    public String getPropDesc() {
        return propDesc;
    }

    public void setPropDesc(String propDesc) {
        this.propDesc = propDesc == null ? null : propDesc.trim();
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

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public Long getCreateStaff() {
        return createStaff;
    }

    public void setCreateStaff(Long createStaff) {
        this.createStaff = createStaff;
    }

    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }

    public Long getUpdateStaff() {
        return updateStaff;
    }

    public void setUpdateStaff(Long updateStaff) {
        this.updateStaff = updateStaff;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", propName=").append(propName);
        sb.append(", propSname=").append(propSname);
        sb.append(", propValueType=").append(propValueType);
        sb.append(", propType=").append(propType);
        sb.append(", propDesc=").append(propDesc);
        sb.append(", sortNo=").append(sortNo);
        sb.append(", status=").append(status);
        sb.append(", createTime=").append(createTime);
        sb.append(", createStaff=").append(createStaff);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", updateStaff=").append(updateStaff);
        sb.append("]");
        return sb.toString();
    }

    public String getPropInputType() {
        return propInputType;
    }

    public void setPropInputType(String propInputType) {
        this.propInputType = propInputType;
    }

    public String getPropInputRule() {
        return propInputRule;
    }

    public void setPropInputRule(String propInputRule) {
        this.propInputRule = propInputRule;
    }

	public String getIfAbleEdit() {
		return ifAbleEdit;
	}

	public void setIfAbleEdit(String ifAbleEdit) {
		this.ifAbleEdit = ifAbleEdit == null ? null : ifAbleEdit.trim();
	}
    
    
}
