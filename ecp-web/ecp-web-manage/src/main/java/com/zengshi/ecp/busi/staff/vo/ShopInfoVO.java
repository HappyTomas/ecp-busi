package com.zengshi.ecp.busi.staff.vo;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;

public class ShopInfoVO implements Serializable{

    /** 
     * serialVersionUID:TODO(用一句话描述这个变量表示什么). 
     * @since JDK 1.6 
     */ 
    private static final long serialVersionUID = 1L;
    
    private String id;

    private String companyId;
    @Length(min=1, max=32, message="{staff.shopName.length.error}")
    private String shopName;
    @Length(min=1, max=64, message="{staff.shopFullName.length.error}")
    private String shopFullName;

    private String shopGrade;
    @Length(min=0, max=512, message="{staff.shopDesc.length.error}")
    private String shopDesc;

    private String shopStatus;

    private String shopDealType;
    @Length(min=0, max=10, message="{staff.cautionMoney.length.error}")
    private String cautionMoney;

    @Length(min=0, max=10, message="{staff.microMessageExt.length.error}")
    private String microMessageExt;

    private String hotShowSupported;

    private Short hotShowSort;

    private String logoPath;
    private String logoMongodbKey;

    @Length(min=1, max=32, message="{staff.linkPerson.length.error}")
    private String linkPerson;
    
    @Pattern(regexp = "^((13[0-9])|(14[5|7])|(15([0-3]|[5-9]))|(17[0-9])|(18[0,1-9]))\\d{8}$",message="{staff.linkMobilephone.error}")
    private String linkMobilephone;
    
    @Email(message="{staff.email.style.error}")
    private String linkEmail;
    
    @Length(min=1, max=8, message="{staff.distribution.length.error}")
    private String distribution;

    private String isUseVip;

    private String offlineSupported;

    private String onlineSupported;

    private String isWhiteList;

    private String isBlackList;

    private Timestamp invalidDate;

    private String invalidStaff;

    private Timestamp checkDate;

    private String checkRemark;

    private String createStaff;  
    
    //物流运费模版信息
    private String shipTemplateName;

    private String shipTemplateType;//计价方式

    private String ifFree;
    
    private String seniorFreeParam;//高级运费的配置参数
    
    private String defaultFreeParam;//默认运费的配置参数
    
    
    public String getStaffCode() {
        return staffCode;
    }

    public void setStaffCode(String staffCode) {
        this.staffCode = staffCode;
    }

    public String getStaffPasswd() {
        return staffPasswd;
    }

    public void setStaffPasswd(String staffPasswd) {
        this.staffPasswd = staffPasswd;
    }

    @Length(min=3, max=20, message="{staff.staffCode.length.error}")
    private String staffCode;
    
    @Length(min=6, max=20, message="{staff.staffPasswd.length.error}")
    private String staffPasswd;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
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

    public String getCautionMoney() {
        return cautionMoney;
    }

    public void setCautionMoney(String cautionMoney) {
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
     * logoMongodbKey. 
     * 
     * @return  the logoMongodbKey 
     * @since   JDK 1.6 
     */
    public String getLogoMongodbKey() {
        return logoMongodbKey;
    }

    /** 
     * logoMongodbKey. 
     * 
     * @param   logoMongodbKey    the logoMongodbKey to set 
     * @since   JDK 1.6 
     */
    public void setLogoMongodbKey(String logoMongodbKey) {
        this.logoMongodbKey = logoMongodbKey;
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

    /** 
     * distribution. 
     * 
     * @return  the distribution 
     * @since   JDK 1.6 
     */
    public String getDistribution() {
        return distribution;
    }

    /** 
     * distribution. 
     * 
     * @param   distribution    the distribution to set 
     * @since   JDK 1.6 
     */
    public void setDistribution(String distribution) {
        this.distribution = distribution;
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

   
    public String getCreateStaff() {
        return createStaff;
    }

    public void setCreateStaff(String createStaff) {
        this.createStaff = createStaff;
    }

    
    
    public String getShipTemplateName() {
        return shipTemplateName;
    }

    public void setShipTemplateName(String shipTemplateName) {
        this.shipTemplateName = shipTemplateName;
    }

    public String getShipTemplateType() {
        return shipTemplateType;
    }

    public void setShipTemplateType(String shipTemplateType) {
        this.shipTemplateType = shipTemplateType;
    }

    public String getIfFree() {
        return ifFree;
    }

    public void setIfFree(String ifFree) {
        this.ifFree = ifFree;
    }

    public String getSeniorFreeParam() {
        return seniorFreeParam;
    }

    public void setSeniorFreeParam(String seniorFreeParam) {
        this.seniorFreeParam = seniorFreeParam;
    }

    public String getDefaultFreeParam() {
        return defaultFreeParam;
    }

    public void setDefaultFreeParam(String defaultFreeParam) {
        this.defaultFreeParam = defaultFreeParam;
    }

    /** 
     * TODO 简单描述该方法的实现功能（可选）. 
     * @see java.lang.Object#toString() 
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("ShopInfoVO [id=");
        builder.append(id);
        builder.append(", companyId=");
        builder.append(companyId);
        builder.append(", shopName=");
        builder.append(shopName);
        builder.append(", shopFullName=");
        builder.append(shopFullName);
        builder.append(", shopGrade=");
        builder.append(shopGrade);
        builder.append(", shopDesc=");
        builder.append(shopDesc);
        builder.append(", shopStatus=");
        builder.append(shopStatus);
        builder.append(", shopDealType=");
        builder.append(shopDealType);
        builder.append(", cautionMoney=");
        builder.append(cautionMoney);
        builder.append(", microMessageExt=");
        builder.append(microMessageExt);
        builder.append(", hotShowSupported=");
        builder.append(hotShowSupported);
        builder.append(", hotShowSort=");
        builder.append(hotShowSort);
        builder.append(", logoPath=");
        builder.append(logoPath);
        builder.append(", linkPerson=");
        builder.append(linkPerson);
        builder.append(", linkMobilephone=");
        builder.append(linkMobilephone);
        builder.append(", linkEmail=");
        builder.append(linkEmail);
        builder.append(", isUseVip=");
        builder.append(isUseVip);
        builder.append(", offlineSupported=");
        builder.append(offlineSupported);
        builder.append(", onlineSupported=");
        builder.append(onlineSupported);
        builder.append(", isWhiteList=");
        builder.append(isWhiteList);
        builder.append(", isBlackList=");
        builder.append(isBlackList);
        builder.append(", invalidDate=");
        builder.append(invalidDate);
        builder.append(", invalidStaff=");
        builder.append(invalidStaff);
        builder.append(", checkDate=");
        builder.append(checkDate);
        builder.append(", checkRemark=");
        builder.append(checkRemark);
        builder.append(", createStaff=");
        builder.append(createStaff);
        builder.append(", staffCode=");
        builder.append(staffCode);
        builder.append(", staffPasswd=");
        builder.append(staffPasswd);
        builder.append("]");
        return builder.toString();
    }
    public ShopInfoVO()
    {}
 

}

