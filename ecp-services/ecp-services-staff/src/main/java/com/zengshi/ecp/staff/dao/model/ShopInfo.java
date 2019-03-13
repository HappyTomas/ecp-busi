package com.zengshi.ecp.staff.dao.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class ShopInfo implements Serializable {
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

    private String linkPerson;

    private String linkMobilephone;

    private String linkEmail;

    private String distribution;

    private String isUseVip;

    private String offlineSupported;

    private String onlineSupported;

    private String isWhiteList;

    private String isBlackList;

    private Timestamp invalidDate;

    private String invalidStaff;

    private Timestamp createTime;

    private Long createStaff;

    private Timestamp updateTime;

    private Long updateStaff;

    private Long siteId;

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

    public String getDistribution() {
        return distribution;
    }

    public void setDistribution(String distribution) {
        this.distribution = distribution == null ? null : distribution.trim();
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

    public Long getSiteId() {
        return siteId;
    }

    public void setSiteId(Long siteId) {
        this.siteId = siteId;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", companyId=").append(companyId);
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
        sb.append(", distribution=").append(distribution);
        sb.append(", isUseVip=").append(isUseVip);
        sb.append(", offlineSupported=").append(offlineSupported);
        sb.append(", onlineSupported=").append(onlineSupported);
        sb.append(", isWhiteList=").append(isWhiteList);
        sb.append(", isBlackList=").append(isBlackList);
        sb.append(", invalidDate=").append(invalidDate);
        sb.append(", invalidStaff=").append(invalidStaff);
        sb.append(", createTime=").append(createTime);
        sb.append(", createStaff=").append(createStaff);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", updateStaff=").append(updateStaff);
        sb.append(", siteId=").append(siteId);
        sb.append("]");
        return sb.toString();
    }
}