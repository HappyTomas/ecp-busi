package com.zengshi.ecp.coupon.dao.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class CoupDetail implements Serializable {
    private Long id;

    private String coupNo;

    private String coupName;

    private Long siteId;

    private Long staffId;

    private Long coupId;

    private Long coupTypeId;

    private Long coupValue;
    
    //1:可使用 2:已使用 0:已过期
  	private String opeType;
    
    private String typeLimit;

    private String useRuleCode;

    private String gainRuleCode;

    private String coupValueShow;

    private String conditionsShow;

    private String coupSource;

    private Timestamp useTime;

    private Long shopId;

    private Long batchId;

    private String remark;

    private String ifUse;

    private String status;

    private Timestamp activeTime;

    private Timestamp inactiveTime;

    private Timestamp createTime;

    private Long createStaff;

    private Timestamp updateTime;

    private Long updateStaff;

    private static final long serialVersionUID = 1L;

    
    public String getOpeType() {
		return opeType;
	}

	public void setOpeType(String opeType) {
		this.opeType = opeType;
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
        this.coupNo = coupNo == null ? null : coupNo.trim();
    }

    public String getCoupName() {
        return coupName;
    }

    public void setCoupName(String coupName) {
        this.coupName = coupName == null ? null : coupName.trim();
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

    public String getTypeLimit() {
        return typeLimit;
    }

    public void setTypeLimit(String typeLimit) {
        this.typeLimit = typeLimit == null ? null : typeLimit.trim();
    }

    public String getUseRuleCode() {
        return useRuleCode;
    }

    public void setUseRuleCode(String useRuleCode) {
        this.useRuleCode = useRuleCode == null ? null : useRuleCode.trim();
    }

    public String getGainRuleCode() {
        return gainRuleCode;
    }

    public void setGainRuleCode(String gainRuleCode) {
        this.gainRuleCode = gainRuleCode == null ? null : gainRuleCode.trim();
    }

    public String getCoupValueShow() {
        return coupValueShow;
    }

    public void setCoupValueShow(String coupValueShow) {
        this.coupValueShow = coupValueShow == null ? null : coupValueShow.trim();
    }

    public String getConditionsShow() {
        return conditionsShow;
    }

    public void setConditionsShow(String conditionsShow) {
        this.conditionsShow = conditionsShow == null ? null : conditionsShow.trim();
    }

    public String getCoupSource() {
        return coupSource;
    }

    public void setCoupSource(String coupSource) {
        this.coupSource = coupSource == null ? null : coupSource.trim();
    }

    public Timestamp getUseTime() {
        return useTime;
    }

    public void setUseTime(Timestamp useTime) {
        this.useTime = useTime;
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
        sb.append(", coupNo=").append(coupNo);
        sb.append(", coupName=").append(coupName);
        sb.append(", siteId=").append(siteId);
        sb.append(", staffId=").append(staffId);
        sb.append(", coupId=").append(coupId);
        sb.append(", coupTypeId=").append(coupTypeId);
        sb.append(", coupValue=").append(coupValue);
        sb.append(", typeLimit=").append(typeLimit);
        sb.append(", useRuleCode=").append(useRuleCode);
        sb.append(", gainRuleCode=").append(gainRuleCode);
        sb.append(", coupValueShow=").append(coupValueShow);
        sb.append(", conditionsShow=").append(conditionsShow);
        sb.append(", coupSource=").append(coupSource);
        sb.append(", useTime=").append(useTime);
        sb.append(", shopId=").append(shopId);
        sb.append(", batchId=").append(batchId);
        sb.append(", remark=").append(remark);
        sb.append(", ifUse=").append(ifUse);
        sb.append(", status=").append(status);
        sb.append(", activeTime=").append(activeTime);
        sb.append(", inactiveTime=").append(inactiveTime);
        sb.append(", createTime=").append(createTime);
        sb.append(", createStaff=").append(createStaff);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", updateStaff=").append(updateStaff);
        sb.append("]");
        return sb.toString();
    }
}
