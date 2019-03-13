package com.zengshi.ecp.goods.dubbo.dto;

import java.sql.Timestamp;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.zengshi.ecp.server.front.dto.BaseResponseDTO;

public class GdsCatlog2ShopPageRespDTO extends BaseResponseDTO {
	
	private Long id;

    private Long companyId;

    private String shopName;

    private String shopFullName;

    private String shopGrade;

    private String shopDesc;

    private String shopStatus;

    private String shopDealType;

    private Long cautionMoney;

    private String microMessageExt;

    private String hotShowSupported;

    private Short hotShowSort;

    private String logoPath;
    
    private String logoPathURL;

    private String linkPerson;

    private String linkMobilephone;

    private String linkEmail;

    private String isUseVip;

    private String offlineSupported;

    private String onlineSupported;

    private String isWhiteList;

    private String isBlackList;

    private Timestamp invalidDate;

    private String invalidStaff;

    private Long checkStaff;

    private Timestamp checkDate;

    private String checkRemark;

    private Timestamp createTime;

    private Long createStaff;

    private Timestamp updateTime;

    private Long updateStaff;
    
    private String companyName;
    
    /**
     * 已经关联目录名称(逗号分隔)
     */
    private String catlogNames;
    /**
     * 已经关联目录ID(逗号分隔)
     */
    private String catlogIds;
    
    public String getDistribution() {
        return distribution;
    }

    public void setDistribution(String distribution) {
        this.distribution = distribution;
    }

    private String distribution;

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName == null ? null : shopName.trim();
    }

    public String getShopFullName() {
        return shopFullName;
    }

    public void setShopFullName(String shopFullName) {
        this.shopFullName = shopFullName == null ? null : shopFullName.trim();
    }

    public String getShopGrade() {
        return shopGrade;
    }

    public void setShopGrade(String shopGrade) {
        this.shopGrade = shopGrade == null ? null : shopGrade.trim();
    }

    public String getShopDesc() {
        return shopDesc;
    }

    public void setShopDesc(String shopDesc) {
        this.shopDesc = shopDesc == null ? null : shopDesc.trim();
    }

    public String getShopStatus() {
        return shopStatus;
    }

    public void setShopStatus(String shopStatus) {
        this.shopStatus = shopStatus == null ? null : shopStatus.trim();
    }

    public String getShopDealType() {
        return shopDealType;
    }

    public void setShopDealType(String shopDealType) {
        this.shopDealType = shopDealType == null ? null : shopDealType.trim();
    }

    public Long getCautionMoney() {
        return cautionMoney;
    }

    public void setCautionMoney(Long cautionMoney) {
        this.cautionMoney = cautionMoney;
    }

    public String getMicroMessageExt() {
        return microMessageExt;
    }

    public void setMicroMessageExt(String microMessageExt) {
        this.microMessageExt = microMessageExt == null ? null : microMessageExt.trim();
    }

    public String getHotShowSupported() {
        return hotShowSupported;
    }

    public void setHotShowSupported(String hotShowSupported) {
        this.hotShowSupported = hotShowSupported == null ? null : hotShowSupported.trim();
    }

    public Short getHotShowSort() {
        return hotShowSort;
    }

    public void setHotShowSort(Short hotShowSort) {
        this.hotShowSort = hotShowSort;
    }

    public String getLogoPath() {
        return logoPath;
    }

    public void setLogoPath(String logoPath) {
        this.logoPath = logoPath == null ? null : logoPath.trim();
    }

    /** 
     * logoPathURL. 
     * 
     * @return  the logoPathURL 
     * @since   JDK 1.6 
     */
    public String getLogoPathURL() {
        return logoPathURL;
    }

    /** 
     * logoPathURL. 
     * 
     * @param   logoPathURL    the logoPathURL to set 
     * @since   JDK 1.6 
     */
    public void setLogoPathURL(String logoPathURL) {
        this.logoPathURL = logoPathURL;
    }

    public String getLinkPerson() {
        return linkPerson;
    }

    public void setLinkPerson(String linkPerson) {
        this.linkPerson = linkPerson == null ? null : linkPerson.trim();
    }

    public String getLinkMobilephone() {
        return linkMobilephone;
    }

    public void setLinkMobilephone(String linkMobilephone) {
        this.linkMobilephone = linkMobilephone == null ? null : linkMobilephone.trim();
    }

    public String getLinkEmail() {
        return linkEmail;
    }

    public void setLinkEmail(String linkEmail) {
        this.linkEmail = linkEmail == null ? null : linkEmail.trim();
    }

    public String getIsUseVip() {
        return isUseVip;
    }

    public void setIsUseVip(String isUseVip) {
        this.isUseVip = isUseVip == null ? null : isUseVip.trim();
    }

    public String getOfflineSupported() {
        return offlineSupported;
    }

    public void setOfflineSupported(String offlineSupported) {
        this.offlineSupported = offlineSupported == null ? null : offlineSupported.trim();
    }

    public String getOnlineSupported() {
        return onlineSupported;
    }

    public void setOnlineSupported(String onlineSupported) {
        this.onlineSupported = onlineSupported == null ? null : onlineSupported.trim();
    }

    public String getIsWhiteList() {
        return isWhiteList;
    }

    public void setIsWhiteList(String isWhiteList) {
        this.isWhiteList = isWhiteList == null ? null : isWhiteList.trim();
    }

    public String getIsBlackList() {
        return isBlackList;
    }

    public void setIsBlackList(String isBlackList) {
        this.isBlackList = isBlackList == null ? null : isBlackList.trim();
    }

    public Timestamp getInvalidDate() {
        return invalidDate;
    }

    public void setInvalidDate(Timestamp invalidDate) {
        this.invalidDate = invalidDate;
    }

    public String getInvalidStaff() {
        return invalidStaff;
    }

    public void setInvalidStaff(String invalidStaff) {
        this.invalidStaff = invalidStaff == null ? null : invalidStaff.trim();
    }

    public Long getCheckStaff() {
        return checkStaff;
    }

    public void setCheckStaff(Long checkStaff) {
        this.checkStaff = checkStaff;
    }

    public Timestamp getCheckDate() {
        return checkDate;
    }

    public void setCheckDate(Timestamp checkDate) {
        this.checkDate = checkDate;
    }

    public String getCheckRemark() {
        return checkRemark;
    }

    public void setCheckRemark(String checkRemark) {
        this.checkRemark = checkRemark == null ? null : checkRemark.trim();
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
    

    public String getCatlogNames() {
		return catlogNames;
	}

	public void setCatlogNames(String catlogNames) {
		this.catlogNames = catlogNames;
	}

	public String getCatlogIds() {
		return catlogIds;
	}

	public void setCatlogIds(String catlogIds) {
		this.catlogIds = catlogIds;
	}

	@Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
