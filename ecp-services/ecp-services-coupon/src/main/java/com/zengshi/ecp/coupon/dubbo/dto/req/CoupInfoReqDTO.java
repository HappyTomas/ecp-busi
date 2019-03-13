package com.zengshi.ecp.coupon.dubbo.dto.req;

import java.util.Date;
import java.util.Map;

import com.zengshi.ecp.server.front.dto.BaseInfo;


public class CoupInfoReqDTO extends BaseInfo {

    private static final long serialVersionUID = 1L;
    
    private Long id;
    //优惠券编码
    private String coupCode;
    
    //0:平台级别,1:店铺级别
    //平台级别的只要平台管理员可以使用,店铺级别的平台管理员和店铺都能使用
    private String typeLimit;
    
    private Long siteId;

    private Long coupTypeId;

    private String coupName;

    private Long coupValue;

    private String useRuleCode;

    private String gainRuleCode;

    private Long shopId;

    private String ifCode;

    private String ifBack;

    private Long coupNum;

    private Long getNum;

    private String effType;

    private Integer fixedTime;

    private Date activeTime;

    private Date inactiveTime;
    //状态（0:失效,1:有效 2:草稿,3.删除,4.系统删除）删除为用户自己删除,系统删除比如是生效改失效先系统删除,再添加
    private String status;

    private String remark;

    private Date createTime;
    
    private Date createTimeStart;
    
    private Date createTimeEnd;

    private Long createStaff;

    private Date updateTime;
    
    private Date updateTimeStart;
    
    private Date updateTimeEnd;

    private Long updateStaff;

    //使用规则
    private Map<String, Object> useMap;

    
	public String getTypeLimit() {
		return typeLimit;
	}

	public void setTypeLimit(String typeLimit) {
		this.typeLimit = typeLimit;
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

	public Long getCoupTypeId() {
		return coupTypeId;
	}

	public void setCoupTypeId(Long coupTypeId) {
		this.coupTypeId = coupTypeId;
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

	public String getIfCode() {
		return ifCode;
	}

	public void setIfCode(String ifCode) {
		this.ifCode = ifCode;
	}

	public String getIfBack() {
		return ifBack;
	}

	public void setIfBack(String ifBack) {
		this.ifBack = ifBack;
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

	public Date getCreateTimeStart() {
		return createTimeStart;
	}

	public void setCreateTimeStart(Date createTimeStart) {
		this.createTimeStart = createTimeStart;
	}

	public Date getCreateTimeEnd() {
		return createTimeEnd;
	}

	public void setCreateTimeEnd(Date createTimeEnd) {
		this.createTimeEnd = createTimeEnd;
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

	public Date getUpdateTimeStart() {
		return updateTimeStart;
	}

	public void setUpdateTimeStart(Date updateTimeStart) {
		this.updateTimeStart = updateTimeStart;
	}

	public Date getUpdateTimeEnd() {
		return updateTimeEnd;
	}

	public void setUpdateTimeEnd(Date updateTimeEnd) {
		this.updateTimeEnd = updateTimeEnd;
	}

	public Long getUpdateStaff() {
		return updateStaff;
	}

	public void setUpdateStaff(Long updateStaff) {
		this.updateStaff = updateStaff;
	}

	public Map<String, Object> getUseMap() {
		return useMap;
	}

	public void setUseMap(Map<String, Object> useMap) {
		this.useMap = useMap;
	}
    
    
}
