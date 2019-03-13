package com.zengshi.ecp.goods.dubbo.dto;

import java.sql.Timestamp;

import com.zengshi.ecp.server.front.dto.BaseResponseDTO;

public class GdsCatg2PropRespDTO extends BaseResponseDTO {
    

    /** 
     * @since JDK 1.6 
     */ 
    private static final long serialVersionUID = 1L;

    private String catgCode;

    private Long propId;

    private String ifBasic;

    private String ifHaveto;

    private String status;

    private Timestamp createTime;

    private Long createStaff;

    private Timestamp updateTime;

    private Long updateStaff;
    
    private String ifSearch;
    
    // 关联属性名称.
    private String propName;
    // 关联属性类型.
    private String propType;
    // 属性类型名称.
    private String propTypeName;
    
    // 属性值类型.
    private String propValueType;
    // 属性值类型名称.
    private String propValueTypeName;    
    
    private String ifGdsInput;
    
    private String ifAbleEdit;
    
    public String getCatgCode() {
        return catgCode;
    }

    public void setCatgCode(String catgCode) {
        this.catgCode = catgCode == null ? null : catgCode.trim();
    }

    public Long getPropId() {
        return propId;
    }

    public void setPropId(Long propId) {
        this.propId = propId;
    }

    public String getIfBasic() {
        return ifBasic;
    }

    public void setIfBasic(String ifBasic) {
        this.ifBasic = ifBasic == null ? null : ifBasic.trim();
    }

    public String getIfHaveto() {
        return ifHaveto;
    }

    public void setIfHaveto(String ifHaveto) {
        this.ifHaveto = ifHaveto == null ? null : ifHaveto.trim();
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


	public String getIfSearch() {
		return ifSearch;
	}

	public void setIfSearch(String ifSearch) {
		this.ifSearch = ifSearch;
	}
	
	

	public String getPropName() {
		return propName;
	}

	public void setPropName(String propName) {
		this.propName = propName;
	}

	public String getPropType() {
		return propType;
	}

	public void setPropType(String propType) {
		this.propType = propType;
	}

	public String getPropValueType() {
		return propValueType;
	}

	public void setPropValueType(String propValueType) {
		this.propValueType = propValueType;
	}
	

	public String getIfGdsInput() {
		return ifGdsInput;
	}

	public void setIfGdsInput(String ifGdsInput) {
		this.ifGdsInput = ifGdsInput;
	}
	

	public String getPropTypeName() {
		return propTypeName;
	}

	public void setPropTypeName(String propTypeName) {
		this.propTypeName = propTypeName;
	}

	public String getPropValueTypeName() {
		return propValueTypeName;
	}

	public void setPropValueTypeName(String propValueTypeName) {
		this.propValueTypeName = propValueTypeName;
	}

	public String getIfAbleEdit() {
		return ifAbleEdit;
	}

	public void setIfAbleEdit(String ifAbleEdit) {
		this.ifAbleEdit = ifAbleEdit == null ? null : ifAbleEdit.trim();
	}
	
	

}
