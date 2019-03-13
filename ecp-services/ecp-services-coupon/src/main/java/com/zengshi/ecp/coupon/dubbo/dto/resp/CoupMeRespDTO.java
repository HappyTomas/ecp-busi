package com.zengshi.ecp.coupon.dubbo.dto.resp;

import java.sql.Timestamp;

import com.zengshi.ecp.server.front.dto.BaseResponseDTO;

public class CoupMeRespDTO extends BaseResponseDTO{
	
	/** 
	 * serialVersionUID:TODO(用一句话描述这个变量表示什么). 
	 * @since JDK 1.7
	 
	 */ 
	private static final long serialVersionUID = 1L;
	
	private Long id;
	 //优惠券编码(对外展示,使用)
    private String coupNo;
    //优惠券名称
    private String coupName;
	//优惠券编码
	private String coupNum;
	//优惠券面额
	private Integer coupValue;
	//展示优惠券使用条件 100-50 满100元可使用
	private String conditions;
	//优惠券面额展示 例如满100 券面额50 展示100-50
	private String coupValueShow;
	//展示优惠券使用条件
	private String conditionsShow;
	//0:不展示  1:新(2天内领取) 2:快过期
	private String coupStatus;
	//站点
	private Long siteId;
	//站点名称
	private String siteName;
	//来源
    private String coupSource;
    //生效时间
    private Timestamp activeTime;
    //失效时间
    private Timestamp inactiveTime;
    //领取时间
    private Timestamp createTime;
	
    private Long coupId;

    private Long coupTypeId;
    
    private String useRuleCode;

    private String gainRuleCode;
    
    private Long shopId;
    
    private String shopName;
    
    private String status;
    
    private String remark;
    //记录优惠券是否使用 0:未使用 , 1:使用
    private String ifUse;
    
    private Long batchId;
    
    private Timestamp useTime;
    
    private Long staffId;
    
    private Long createStaff;

    
	public String getCoupStatus() {
		return coupStatus;
	}

	public void setCoupStatus(String coupStatus) {
		this.coupStatus = coupStatus;
	}

	public String getConditionsShow() {
		return conditionsShow;
	}

	public void setConditionsShow(String conditionsShow) {
		this.conditionsShow = conditionsShow;
	}

	public String getCoupValueShow() {
		return coupValueShow;
	}

	public void setCoupValueShow(String coupValueShow) {
		this.coupValueShow = coupValueShow;
	}

	public String getCoupNo() {
		return coupNo;
	}

	public void setCoupNo(String coupNo) {
		this.coupNo = coupNo;
	}

	public String getCoupName() {
		return coupName;
	}

	public void setCoupName(String coupName) {
		this.coupName = coupName;
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

	public String getConditions() {
		return conditions;
	}

	public void setConditions(String conditions) {
		this.conditions = conditions;
	}

	public String getSiteName() {
		return siteName;
	}

	public void setSiteName(String siteName) {
		this.siteName = siteName;
	}

	public Integer getCoupValue() {
		return coupValue;
	}

	public void setCoupValue(Integer coupValue) {
		this.coupValue = coupValue;
	}



	public String getShopName() {
		return shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCoupNum() {
		return coupNum;
	}

	public void setCoupNum(String coupNum) {
		this.coupNum = coupNum;
	}
	public Long getSiteId() {
		return siteId;
	}

	public void setSiteId(Long siteId) {
		this.siteId = siteId;
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

	public Long getShopId() {
		return shopId;
	}

	public void setShopId(Long shopId) {
		this.shopId = shopId;
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

	public String getIfUse() {
		return ifUse;
	}

	public void setIfUse(String ifUse) {
		this.ifUse = ifUse;
	}

	public Long getBatchId() {
		return batchId;
	}

	public void setBatchId(Long batchId) {
		this.batchId = batchId;
	}

	public String getCoupSource() {
		return coupSource;
	}

	public void setCoupSource(String coupSource) {
		this.coupSource = coupSource;
	}

	public Timestamp getUseTime() {
		return useTime;
	}

	public void setUseTime(Timestamp useTime) {
		this.useTime = useTime;
	}

	public Long getStaffId() {
		return staffId;
	}

	public void setStaffId(Long staffId) {
		this.staffId = staffId;
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
    
    
}

