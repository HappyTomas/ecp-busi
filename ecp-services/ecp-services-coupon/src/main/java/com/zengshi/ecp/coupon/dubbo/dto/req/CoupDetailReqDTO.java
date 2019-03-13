package com.zengshi.ecp.coupon.dubbo.dto.req;

import java.sql.Timestamp;

import com.zengshi.ecp.server.front.dto.BaseInfo;

public class CoupDetailReqDTO extends BaseInfo {
    private Long id;

    private String coupNo;
    
    private String coupName;

    private Long siteId;

    private Long staffId;

    private Long coupId;

    private Long coupTypeId;

    private Long coupValue;
    //0:平台级别,1:店铺级别
    //平台级别的只要平台管理员可以使用,店铺级别的平台管理员和店铺都能使用
    private String typeLimit;
    
    private String coupSource;
    
    //优惠券面额展示 例如满100 券面额50 展示100-50   
    private String coupValueShow;
    //展示优惠券使用条件
  	private String conditionsShow;

    private Timestamp useTime;
    //使用时间开始
    private Timestamp useTimeStart;
    //使用时间结束
    private Timestamp useTimeEnd;

    private Long shopId;

    private Long batchId;

    private String remark;

    private String ifUse;
    //1:可使用 2:已使用 0:已过期
  	private String opeType;

    private String status;
    //生效时间
    private Timestamp activeTime;
    //失效时间
    private Timestamp inactiveTime;
    
    private Timestamp createTime;
    //领取时间开始
    private Timestamp createTimeStart;
    //领取时间结束
    private Timestamp createTimeEnd;
    
    private Long createStaff;

    private Timestamp updateTime;

    private Long updateStaff;
    
    private String useRuleCode;

    private String gainRuleCode;
    //0:降序 1:升序
    private String sortValue;
    //排序字段
    private String key;
    
    private static final long serialVersionUID = 1L;

    
	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getSortValue() {
		return sortValue;
	}

	public void setSortValue(String sortValue) {
		this.sortValue = sortValue;
	}

	public String getTypeLimit() {
		return typeLimit;
	}

	public void setTypeLimit(String typeLimit) {
		this.typeLimit = typeLimit;
	}

	public String getCoupName() {
		return coupName;
	}

	public void setCoupName(String coupName) {
		this.coupName = coupName;
	}

	public String getCoupValueShow() {
		return coupValueShow;
	}

	public void setCoupValueShow(String coupValueShow) {
		this.coupValueShow = coupValueShow;
	}

	public String getConditionsShow() {
		return conditionsShow;
	}

	public void setConditionsShow(String conditionsShow) {
		this.conditionsShow = conditionsShow;
	}

	public String getUseRuleCode() {
		return useRuleCode;
	}

	public void setUseRuleCode(String useRuleCode) {
		this.useRuleCode = useRuleCode;
	}

	public String getGainRuleCode() {
		return gainRuleCode;
	}

	public void setGainRuleCode(String gainRuleCode) {
		this.gainRuleCode = gainRuleCode;
	}

	public Timestamp getUseTime() {
		return useTime;
	}

	public void setUseTime(Timestamp useTime) {
		this.useTime = useTime;
	}

	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public String getOpeType() {
		return opeType;
	}

	public void setOpeType(String opeType) {
		this.opeType = opeType;
	}

	public Timestamp getUseTimeStart() {
		return useTimeStart;
	}

	public void setUseTimeStart(Timestamp useTimeStart) {
		this.useTimeStart = useTimeStart;
	}

	public Timestamp getUseTimeEnd() {
		return useTimeEnd;
	}

	public void setUseTimeEnd(Timestamp useTimeEnd) {
		this.useTimeEnd = useTimeEnd;
	}

	public Timestamp getCreateTimeStart() {
		return createTimeStart;
	}

	public void setCreateTimeStart(Timestamp createTimeStart) {
		this.createTimeStart = createTimeStart;
	}

	public Timestamp getCreateTimeEnd() {
		return createTimeEnd;
	}

	public void setCreateTimeEnd(Timestamp createTimeEnd) {
		this.createTimeEnd = createTimeEnd;
	}

	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    

    public String getCoupNo() {
		return coupNo;
	}

	public void setCoupNo(String coupNo) {
		this.coupNo = coupNo;
	}

	public Long getSiteId() {
        return siteId;
    }

    public void setSiteId(Long siteId) {
        this.siteId = siteId;
    }

    public Long getStaffId() {
        return staffId;
    }

    public void setStaffId(Long staffId) {
        this.staffId = staffId;
    }

    public Long getCoupId() {
        return coupId;
    }

    public void setCoupId(Long coupId) {
        this.coupId = coupId;
    }

    public Long getCoupTypeId() {
        return coupTypeId;
    }

    public void setCoupTypeId(Long coupTypeId) {
        this.coupTypeId = coupTypeId;
    }

    public Long getCoupValue() {
		return coupValue;
	}

	public void setCoupValue(Long coupValue) {
		this.coupValue = coupValue;
	}

	public String getCoupSource() {
        return coupSource;
    }

    public void setCoupSource(String coupSource) {
        this.coupSource = coupSource == null ? null : coupSource.trim();
    }

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public Long getBatchId() {
        return batchId;
    }

    public void setBatchId(Long batchId) {
        this.batchId = batchId;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public String getIfUse() {
        return ifUse;
    }

    public void setIfUse(String ifUse) {
        this.ifUse = ifUse == null ? null : ifUse.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public Timestamp getActiveTime() {
        return activeTime;
    }

    public void setActiveTime(Timestamp activeTime) {
        this.activeTime = activeTime;
    }

    public Timestamp getInactiveTime() {
        return inactiveTime;
    }

    public void setInactiveTime(Timestamp inactiveTime) {
        this.inactiveTime = inactiveTime;
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

}
