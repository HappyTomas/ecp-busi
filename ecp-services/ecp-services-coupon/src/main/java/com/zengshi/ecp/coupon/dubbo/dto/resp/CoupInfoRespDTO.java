package com.zengshi.ecp.coupon.dubbo.dto.resp;

import java.util.Date;

import com.zengshi.ecp.server.front.dto.BaseResponseDTO;


public class CoupInfoRespDTO extends BaseResponseDTO {

    private static final long serialVersionUID = 1L;
    
    //0:优惠券结算校验,如果校验不通过则返回具体的错误信息以及错误位置(优惠券后场内部使用)
    private String checkLimit;
    
    private Long id;
    
    private String coupCode;

    private Long siteId;
    
    private String siteName;//站点名称

    //展示优惠券使用条件
  	private String conditionsShow;
  	
  	//优惠券面额展示 例如满100 券面额50 展示100-50   
    private String coupValueShow;
  	
    private Long coupTypeId;
    
    private String coupTypeName;//优惠券类型名称

    private String coupName;

    //0:平台级别,1:店铺级别
    //平台级别的只要平台管理员可以使用,店铺级别的平台管理员和店铺都能使用
    private String typeLimit;
    
    private Long coupValue;
    
    private String coupValueName;//带单位

    private String useRuleCode;

    private String gainRuleCode;

    private Long shopId;
    
    private String shopName;

    private String ifCode;
    
    private String ifCodeName;

    private String ifBack;
    
    private String ifBackName;

    private Long coupNum;

    private Long getNum;

    private String effType;
    
    private String effTypeName;

    private Integer fixedTime;

    private Date activeTime;

    private Date inactiveTime;

    private String status;
    
    private String statusName;

    private String remark;

    private Date createTime;

    private Long createStaff;

    private Date updateTime;

    private Long updateStaff;

    
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

	public String getTypeLimit() {
		return typeLimit;
	}

	public void setTypeLimit(String typeLimit) {
		this.typeLimit = typeLimit;
	}

	public String getCheckLimit() {
		return checkLimit;
	}

	public void setCheckLimit(String checkLimit) {
		this.checkLimit = checkLimit;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCoupCode() {
		return coupCode;
	}

	public void setCoupCode(String coupCode) {
		this.coupCode = coupCode;
	}

	public Long getSiteId() {
		return siteId;
	}

	public void setSiteId(Long siteId) {
		this.siteId = siteId;
	}

	public String getSiteName() {
		return siteName;
	}

	public void setSiteName(String siteName) {
		this.siteName = siteName;
	}

	public Long getCoupTypeId() {
		return coupTypeId;
	}

	public void setCoupTypeId(Long coupTypeId) {
		this.coupTypeId = coupTypeId;
	}

	public String getCoupTypeName() {
		return coupTypeName;
	}

	public void setCoupTypeName(String coupTypeName) {
		this.coupTypeName = coupTypeName;
	}

	public String getCoupName() {
		return coupName;
	}

	public void setCoupName(String coupName) {
		this.coupName = coupName;
	}

	public Long getCoupValue() {
		return coupValue;
	}

	public void setCoupValue(Long coupValue) {
		this.coupValue = coupValue;
	}

	public String getCoupValueName() {
		return coupValueName;
	}

	public void setCoupValueName(String coupValueName) {
		this.coupValueName = coupValueName;
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

	public Long getShopId() {
		return shopId;
	}

	public void setShopId(Long shopId) {
		this.shopId = shopId;
	}

	public String getShopName() {
		return shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
	}

	public String getIfCode() {
		return ifCode;
	}

	public void setIfCode(String ifCode) {
		this.ifCode = ifCode;
	}

	public String getIfCodeName() {
		return ifCodeName;
	}

	public void setIfCodeName(String ifCodeName) {
		this.ifCodeName = ifCodeName;
	}

	public String getIfBack() {
		return ifBack;
	}

	public void setIfBack(String ifBack) {
		this.ifBack = ifBack;
	}

	public String getIfBackName() {
		return ifBackName;
	}

	public void setIfBackName(String ifBackName) {
		this.ifBackName = ifBackName;
	}

	public Long getCoupNum() {
		return coupNum;
	}

	public void setCoupNum(Long coupNum) {
		this.coupNum = coupNum;
	}

	public Long getGetNum() {
		return getNum;
	}

	public void setGetNum(Long getNum) {
		this.getNum = getNum;
	}

	public String getEffType() {
		return effType;
	}

	public void setEffType(String effType) {
		this.effType = effType;
	}

	public String getEffTypeName() {
		return effTypeName;
	}

	public void setEffTypeName(String effTypeName) {
		this.effTypeName = effTypeName;
	}

	public Integer getFixedTime() {
		return fixedTime;
	}

	public void setFixedTime(Integer fixedTime) {
		this.fixedTime = fixedTime;
	}

	public Date getActiveTime() {
		return activeTime;
	}

	public void setActiveTime(Date activeTime) {
		this.activeTime = activeTime;
	}

	public Date getInactiveTime() {
		return inactiveTime;
	}

	public void setInactiveTime(Date inactiveTime) {
		this.inactiveTime = inactiveTime;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getStatusName() {
		return statusName;
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Long getCreateStaff() {
		return createStaff;
	}

	public void setCreateStaff(Long createStaff) {
		this.createStaff = createStaff;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Long getUpdateStaff() {
		return updateStaff;
	}

	public void setUpdateStaff(Long updateStaff) {
		this.updateStaff = updateStaff;
	}
    
}
