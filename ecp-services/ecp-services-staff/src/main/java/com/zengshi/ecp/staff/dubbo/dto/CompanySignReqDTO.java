package com.zengshi.ecp.staff.dubbo.dto;

import java.sql.Timestamp;

import com.zengshi.ecp.server.front.dto.BaseInfo;

public class CompanySignReqDTO extends BaseInfo{
    private Long id;

    private Long companyId;

    private String companyName;

    private String companyAdress;

    private String trade;

    private String employeeNum;
    
    public String getCompanyType() {
        return companyType;
    }

    public void setCompanyType(String companyType) {
        this.companyType = companyType;
    }

    private String companyType;

    private String countryCode;

    private String provinceCode;

    private String cityCode;

    private String countyCode;

    private String companyEmail;

    private String linkPersonMsg;

    private String linkPhoneMsg;

    private String linkPsnCard;

    private String linkTelephone;
    
    private String distribution;

    public String getDistribution() {
		return distribution;
	}

	public void setDistribution(String distribution) {
		this.distribution = distribution;
	}

	private String companyWeixin;

    private String companyQq;

    private String licencePath;

    private String taxPath;

    private String legalPersonImage;

    private String staffCode;

    private Long companyCreateStaff;

    private Timestamp companyCreateTime;

    private String isEnter;

    private String remark;

    private String licenceCode;

    private String taxCode;

    private String organizationForm;

    private Long licenceCapital;

    private String legalPerson;

    private String legalCard;

    private Long parentId;

    private Long shopId;

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

    private String checkStatus;

    private Long checkStaff;

    private Timestamp checkDate;

    private String checkRemark;

    private String source;

    private Timestamp createTime;

    private Long createStaff;

    private Timestamp updateTime;

    private Long updateStaff;
    
    private String serialNumber;

    private static final long serialVersionUID = 1L;
    
    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

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

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName == null ? null : companyName.trim();
    }

    public String getCompanyAdress() {
        return companyAdress;
    }

    public void setCompanyAdress(String companyAdress) {
        this.companyAdress = companyAdress == null ? null : companyAdress.trim();
    }

    public String getTrade() {
        return trade;
    }

    public void setTrade(String trade) {
        this.trade = trade == null ? null : trade.trim();
    }

    public String getEmployeeNum() {
        return employeeNum;
    }

    public void setEmployeeNum(String employeeNum) {
        this.employeeNum = employeeNum == null ? null : employeeNum.trim();
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode == null ? null : countryCode.trim();
    }

    public String getProvinceCode() {
        return provinceCode;
    }

    public void setProvinceCode(String provinceCode) {
        this.provinceCode = provinceCode == null ? null : provinceCode.trim();
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode == null ? null : cityCode.trim();
    }

    public String getCountyCode() {
        return countyCode;
    }

    public void setCountyCode(String countyCode) {
        this.countyCode = countyCode == null ? null : countyCode.trim();
    }

    public String getCompanyEmail() {
        return companyEmail;
    }

    public void setCompanyEmail(String companyEmail) {
        this.companyEmail = companyEmail == null ? null : companyEmail.trim();
    }

    public String getLinkPersonMsg() {
        return linkPersonMsg;
    }

    public void setLinkPersonMsg(String linkPersonMsg) {
        this.linkPersonMsg = linkPersonMsg == null ? null : linkPersonMsg.trim();
    }

    public String getLinkPhoneMsg() {
        return linkPhoneMsg;
    }

    public void setLinkPhoneMsg(String linkPhoneMsg) {
        this.linkPhoneMsg = linkPhoneMsg == null ? null : linkPhoneMsg.trim();
    }

    public String getLinkPsnCard() {
        return linkPsnCard;
    }

    public void setLinkPsnCard(String linkPsnCard) {
        this.linkPsnCard = linkPsnCard == null ? null : linkPsnCard.trim();
    }

    public String getLinkTelephone() {
        return linkTelephone;
    }

    public void setLinkTelephone(String linkTelephone) {
        this.linkTelephone = linkTelephone == null ? null : linkTelephone.trim();
    }

    public String getLicencePath() {
        return licencePath;
    }

    public void setLicencePath(String licencePath) {
        this.licencePath = licencePath == null ? null : licencePath.trim();
    }

    public String getTaxPath() {
        return taxPath;
    }

    public void setTaxPath(String taxPath) {
        this.taxPath = taxPath == null ? null : taxPath.trim();
    }

    public String getLegalPersonImage() {
        return legalPersonImage;
    }

    public void setLegalPersonImage(String legalPersonImage) {
        this.legalPersonImage = legalPersonImage == null ? null : legalPersonImage.trim();
    }

    public String getStaffCode() {
        return staffCode;
    }

    public void setStaffCode(String staffCode) {
        this.staffCode = staffCode == null ? null : staffCode.trim();
    }

    public Long getCompanyCreateStaff() {
        return companyCreateStaff;
    }

    public void setCompanyCreateStaff(Long companyCreateStaff) {
        this.companyCreateStaff = companyCreateStaff;
    }

    public Timestamp getCompanyCreateTime() {
        return companyCreateTime;
    }

    public void setCompanyCreateTime(Timestamp companyCreateTime) {
        this.companyCreateTime = companyCreateTime;
    }

    public String getIsEnter() {
        return isEnter;
    }

    public void setIsEnter(String isEnter) {
        this.isEnter = isEnter == null ? null : isEnter.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public String getLicenceCode() {
        return licenceCode;
    }

    public void setLicenceCode(String licenceCode) {
        this.licenceCode = licenceCode == null ? null : licenceCode.trim();
    }

    public String getTaxCode() {
        return taxCode;
    }

    public void setTaxCode(String taxCode) {
        this.taxCode = taxCode == null ? null : taxCode.trim();
    }

    public String getOrganizationForm() {
        return organizationForm;
    }

    public void setOrganizationForm(String organizationForm) {
        this.organizationForm = organizationForm == null ? null : organizationForm.trim();
    }

    public Long getLicenceCapital() {
        return licenceCapital;
    }

    public void setLicenceCapital(Long licenceCapital) {
        this.licenceCapital = licenceCapital;
    }

    public String getLegalPerson() {
        return legalPerson;
    }

    public void setLegalPerson(String legalPerson) {
        this.legalPerson = legalPerson == null ? null : legalPerson.trim();
    }

    public String getLegalCard() {
        return legalCard;
    }

    public void setLegalCard(String legalCard) {
        this.legalCard = legalCard == null ? null : legalCard.trim();
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
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

    public String getCheckStatus() {
        return checkStatus;
    }

    public void setCheckStatus(String checkStatus) {
        this.checkStatus = checkStatus == null ? null : checkStatus.trim();
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

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source == null ? null : source.trim();
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
    
    public String getCompanyWeixin() {
        return companyWeixin;
    }

    public void setCompanyWeixin(String companyWeixin) {
        this.companyWeixin = companyWeixin;
    }

    public String getCompanyQq() {
        return companyQq;
    }

    public void setCompanyQq(String companyQq) {
        this.companyQq = companyQq;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", companyId=").append(companyId);
        sb.append(", companyName=").append(companyName);
        sb.append(", companyAdress=").append(companyAdress);
        sb.append(", trade=").append(trade);
        sb.append(", employeeNum=").append(employeeNum);
        sb.append(", countryCode=").append(countryCode);
        sb.append(", provinceCode=").append(provinceCode);
        sb.append(", cityCode=").append(cityCode);
        sb.append(", countyCode=").append(countyCode);
        sb.append(", companyEmail=").append(companyEmail);
        sb.append(", linkPersonMsg=").append(linkPersonMsg);
        sb.append(", linkPhoneMsg=").append(linkPhoneMsg);
        sb.append(", linkPsnCard=").append(linkPsnCard);
        sb.append(", linkTelephone=").append(linkTelephone);
        sb.append(", chnlWeixin=").append(companyWeixin);
        sb.append(", chnlQq=").append(companyQq);
        sb.append(", licencePath=").append(licencePath);
        sb.append(", taxPath=").append(taxPath);
        sb.append(", legalPersonImage=").append(legalPersonImage);
        sb.append(", staffCode=").append(staffCode);
        sb.append(", companyCreateStaff=").append(companyCreateStaff);
        sb.append(", companyCreateTime=").append(companyCreateTime);
        sb.append(", isEnter=").append(isEnter);
        sb.append(", remark=").append(remark);
        sb.append(", licenceCode=").append(licenceCode);
        sb.append(", taxCode=").append(taxCode);
        sb.append(", organizationForm=").append(organizationForm);
        sb.append(", licenceCapital=").append(licenceCapital);
        sb.append(", legalPerson=").append(legalPerson);
        sb.append(", legalCard=").append(legalCard);
        sb.append(", parentId=").append(parentId);
        sb.append(", shopId=").append(shopId);
        sb.append(", shopName=").append(shopName);
        sb.append(", shopFullName=").append(shopFullName);
        sb.append(", shopGrade=").append(shopGrade);
        sb.append(", shopDesc=").append(shopDesc);
        sb.append(", shopStatus=").append(shopStatus);
        sb.append(", shopDealType=").append(shopDealType);
        sb.append(", cautionMoney=").append(cautionMoney);
        sb.append(", microMessageExt=").append(microMessageExt);
        sb.append(", hotShowSupported=").append(hotShowSupported);
        sb.append(", hotShowSort=").append(hotShowSort);
        sb.append(", logoPath=").append(logoPath);
        sb.append(", linkPerson=").append(linkPerson);
        sb.append(", linkMobilephone=").append(linkMobilephone);
        sb.append(", linkEmail=").append(linkEmail);
        sb.append(", isUseVip=").append(isUseVip);
        sb.append(", offlineSupported=").append(offlineSupported);
        sb.append(", onlineSupported=").append(onlineSupported);
        sb.append(", isWhiteList=").append(isWhiteList);
        sb.append(", isBlackList=").append(isBlackList);
        sb.append(", invalidDate=").append(invalidDate);
        sb.append(", invalidStaff=").append(invalidStaff);
        sb.append(", checkStatus=").append(checkStatus);
        sb.append(", checkStaff=").append(checkStaff);
        sb.append(", checkDate=").append(checkDate);
        sb.append(", checkRemark=").append(checkRemark);
        sb.append(", source=").append(source);
        sb.append(", createTime=").append(createTime);
        sb.append(", createStaff=").append(createStaff);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", updateStaff=").append(updateStaff);
        sb.append(", companyType=").append(companyType);
        sb.append("]");
        return sb.toString();
    }
}