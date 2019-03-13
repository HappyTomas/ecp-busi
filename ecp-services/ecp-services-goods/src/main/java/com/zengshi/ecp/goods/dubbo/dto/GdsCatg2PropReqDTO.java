package com.zengshi.ecp.goods.dubbo.dto;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.zengshi.ecp.server.front.dto.BaseInfo;

@SuppressWarnings("rawtypes")
public class GdsCatg2PropReqDTO extends BaseInfo {
    /** 
     * serialVersionUID:TODO(用一句话描述这个变量表示什么). 
     * @since JDK 1.6 
     */ 
    private static final long serialVersionUID = -1L;

    private String catgCode;

    private Long propId;

    private String ifBasic;

    private String ifHaveto;

    private String status;

    private Timestamp createTime;

    private Long createStaff;

    private Timestamp updateTime;

    private Long updateStaff;
    
    private String propName;
    
    private String ifSearch;
    
    private List<Long> propIds;
    
    private String ifAbleEdit;
    
    /**
     * 是否在商品录入中
     */
    private String ifGdsInput;
    
    /**
     * 是否包含顶级分类的属性
     */
    private boolean ifContainTopProp;
    
    /**
     * 商品录入，编辑查询需要传
     */
    private boolean ifGdsInputQuery;


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

    public String getPropName() {
		return propName;
	}

	public void setPropName(String propName) {
		this.propName = propName;
	}
	
	

	public String getIfSearch() {
		return ifSearch;
	}

	public void setIfSearch(String ifSearch) {
		this.ifSearch = ifSearch;
	}
	
	public List<Long> getPropIds() {
		return propIds;
	}

	public void setPropIds(List<Long> propIds) {
		this.propIds = propIds;
	}
	
	public void addPropId(Long propId){
		if(null ==  propId) 
			return ;
		if(null == propIds){
			propIds = new ArrayList<>();
		}
		propIds.add(propId);
		
	}
	
	

	public String getIfGdsInput() {
		return ifGdsInput;
	}

	public void setIfGdsInput(String ifGdsInput) {
		this.ifGdsInput = ifGdsInput;
	}

	public boolean getIfContainTopProp() {
		return ifContainTopProp;
	}

	public void setIfContainTopProp(boolean ifContainTopProp) {
		this.ifContainTopProp = ifContainTopProp;
	}

	public boolean isIfGdsInputQuery() {
		return ifGdsInputQuery;
	}

	public void setIfGdsInputQuery(boolean ifGdsInputQuery) {
		this.ifGdsInputQuery = ifGdsInputQuery;
	}

	@Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", catgCode=").append(catgCode);
        sb.append(", propId=").append(propId);
        sb.append(", ifBasic=").append(ifBasic);
        sb.append(", ifHaveto=").append(ifHaveto);
        sb.append(", status=").append(status);
        sb.append(", createTime=").append(createTime);
        sb.append(", createStaff=").append(createStaff);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", updateStaff=").append(updateStaff);
        sb.append(", propName=").append(propName);
        sb.append(", ifSearch=").append(ifSearch);
        sb.append(", ifGdsInput=").append(ifGdsInput);
        sb.append("]");
        return sb.toString();
    }

	public String getIfAbleEdit() {
		return ifAbleEdit;
	}

	public void setIfAbleEdit(String ifAbleEdit) {
		this.ifAbleEdit = ifAbleEdit == null ? null : ifAbleEdit.trim();
	}
	
	
	
}
