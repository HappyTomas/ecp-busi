package com.zengshi.ecp.goods.dubbo.dto.category.dataimport;

import com.zengshi.ecp.server.front.dto.BaseInfo;

public class GdsInterfaceCatgReqDTO extends BaseInfo {
	
	
	
    /** 
	 * serialVersionUID:TODO(用一句话描述这个变量表示什么). 
	 * @since JDK 1.6 
	 */ 
	private static final long serialVersionUID = -9046599409283514166L;

	private Long id;

    private String catgCode;
    
    private String originCatgCode;

    private String originCatgName;
    
    private String catgParent;

    private String origin;

    private String status;
    
    private String updateRule;
    
    private Long createStaff;

    private Long updateStaff;
    
    /**
     * 信息排序。
     */
    private Integer sortNo;
    /**
     * 操作类型. 0-增加 1－删除 2－修改
     */
    private Integer actionType;
    /**
     * 分类类型.1-数字教材  2-电子书.
     */
    private Integer catgType;
    /**
     * 排除的原始编码前缀.
     */
    private String originCatgCodeExcludePrefix;
    /**
     * 原始编码开始前缀.
     */
    private String originCatgCodePrefixLike;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCatgCode() {
        return catgCode;
    }

    public void setCatgCode(String catgCode) {
        this.catgCode = catgCode == null ? null : catgCode.trim();
    }

    public String getOriginCatgCode() {
        return originCatgCode;
    }

    public void setOriginCatgCode(String originCatgCode) {
        this.originCatgCode = originCatgCode == null ? null : originCatgCode.trim();
    }

    public String getOriginCatgName() {
        return originCatgName;
    }

    public void setOriginCatgName(String originCatgName) {
        this.originCatgName = originCatgName == null ? null : originCatgName.trim();
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin == null ? null : origin.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

	public String getCatgParent() {
		return catgParent;
	}

	public void setCatgParent(String catgParent) {
		this.catgParent = catgParent == null ? null : catgParent.trim();
	}

	public Integer getActionType() {
		return actionType;
	}

	public void setActionType(Integer actionType) {
		this.actionType = actionType;
	}

	public Integer getSortNo() {
		return sortNo;
	}

	public void setSortNo(Integer sortNo) {
		this.sortNo = sortNo;
	}

	public Integer getCatgType() {
		return catgType;
	}

	public void setCatgType(Integer catgType) {
		this.catgType = catgType;
	}

	public String getUpdateRule() {
		return updateRule;
	}

	public void setUpdateRule(String updateRule) {
		this.updateRule = updateRule;
	}

	public Long getCreateStaff() {
		return createStaff;
	}

	public void setCreateStaff(Long createStaff) {
		this.createStaff = createStaff;
	}

	public Long getUpdateStaff() {
		return updateStaff;
	}

	public void setUpdateStaff(Long updateStaff) {
		this.updateStaff = updateStaff;
	}

	public String getOriginCatgCodeExcludePrefix() {
		return originCatgCodeExcludePrefix;
	}

	public void setOriginCatgCodeExcludePrefix(String originCatgCodeExcludePrefix) {
		this.originCatgCodeExcludePrefix = originCatgCodeExcludePrefix != null ? originCatgCodeExcludePrefix.trim() : null;
	}

	public String getOriginCatgCodePrefixLike() {
		return originCatgCodePrefixLike;
	}

	public void setOriginCatgCodePrefixLike(String originCatgCodePrefixLike) {
		this.originCatgCodePrefixLike = originCatgCodePrefixLike != null ? originCatgCodePrefixLike.trim() : null;
	}
    
	

}
