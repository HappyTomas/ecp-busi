/** 
 * Project Name:ecp-web-demo 
 * File Name:DemoInfoVO.java 
 * Package Name:com.zengshi.ecp.busi.demo.vo 
 * Date:2015-8-5下午3:02:29 
 * Copyright (c) 2015, ZengShi All Rights Reserved. 
 * 
 */ 
package com.zengshi.ecp.busi.staff.buyer.vo;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

/**
 * Title: ECP <br>
 * Project Name:ecp-web-manage <br>
 * Description: <br>
 * Date:2015-8-5下午3:02:29  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author wangbh
 * @version  
 * @since JDK 1.7 
 */
public class CompanyInfoVO implements Serializable{
    
    /** 
     * serialVersionUID:
     * @since JDK 1.7 
     */ 
    private static final long serialVersionUID = -1808106181284676714L;
    
    @NotNull(message="{staff.companyName.null.error}")
    private String companyName;
    
    @NotNull
    @Length(min=1, max=128, message="{staff.companyAdress.length.error}")
    private String companyAdress;

    private Long companyId;
   
    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    private String trade;

    private String companyType;

    private String employeeNum;

    private String countryCode;

    @NotNull(message="{staff.provinceCode.null.error}")
    private String provinceCode;

    @NotNull(message="{staff.cityCode.null.error}")
    private String cityCode;
    
    @NotNull(message="{staff.countyCode.null.error}")
    private String countyCode;

    private String companyEmail;

    private String status;

    @NotNull(message="{staff.linkPersonMsg.null.error}")
    private String linkPersonMsg;

    @NotNull(message="{staff.linkPhoneMsg.null.error}")
    private String linkPhoneMsg;

    private String linkPsnCard;

    private String linkTelephone;

    private String companyWeixin;

    private String companyQq;

    private String licencePath;

    private String taxPath;

    private String legalPersonImage;

    private Long companyCreateStaff;

    private Timestamp companyCreateTime;

  
    private String isEnter;

    @Length(min=0, max=128, message="{staff.remark.length.error}")
    private String remark;

   
    private String licenceCode;
    
    private String taxCode;
   
    private String organizationForm;
   
    private Long licenceCapital;
   
    private String legalPerson;
    
    private String legalCard;

    private Long parentId;

    private String source;

    private Timestamp createTime;

    private Long createStaff;

    private Timestamp updateTime;

    private Long updateStaff;
    
    public Long getShopNum() {
        return shopNum;
    }

    public void setShopNum(Long shopNum) {
        this.shopNum = shopNum;
    }

    public Long getCustNum() {
        return custNum;
    }

    public void setCustNum(Long custNum) {
        this.custNum = custNum;
    }

    private Long shopNum;
    
    private Long custNum;

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

    public String getCompanyType() {
        return companyType;
    }

    public void setCompanyType(String companyType) {
        this.companyType = companyType == null ? null : companyType.trim();
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
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

    public String getCompanyWeixin() {
        return companyWeixin;
    }

    public void setCompanyWeixin(String companyWeixin) {
        this.companyWeixin = companyWeixin == null ? null : companyWeixin.trim();
    }

    public String getCompanyQq() {
        return companyQq;
    }

    public void setCompanyQq(String companyQq) {
        this.companyQq = companyQq == null ? null : companyQq.trim();
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


}

