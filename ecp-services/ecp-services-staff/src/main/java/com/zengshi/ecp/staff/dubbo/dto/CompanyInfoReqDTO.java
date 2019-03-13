package com.zengshi.ecp.staff.dubbo.dto;

import java.sql.Timestamp;

import com.zengshi.ecp.server.front.dto.BaseInfo;

public class CompanyInfoReqDTO extends BaseInfo  {
    private Long id;

    private String companyName;

    private String companyAdress;

    private String trade;

    private String companyType;

    private String employeeNum;

    private String countryCode;

    private String provinceCode;

    private String cityCode;

    private String countyCode;

    private String companyEmail;

    private String status;

    private String linkPersonMsg;

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

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", companyName=").append(companyName);
        sb.append(", companyAdress=").append(companyAdress);
        sb.append(", trade=").append(trade);
        sb.append(", companyType=").append(companyType);
        sb.append(", employeeNum=").append(employeeNum);
        sb.append(", countryCode=").append(countryCode);
        sb.append(", provinceCode=").append(provinceCode);
        sb.append(", cityCode=").append(cityCode);
        sb.append(", countyCode=").append(countyCode);
        sb.append(", companyEmail=").append(companyEmail);
        sb.append(", status=").append(status);
        sb.append(", linkPersonMsg=").append(linkPersonMsg);
        sb.append(", linkPhoneMsg=").append(linkPhoneMsg);
        sb.append(", linkPsnCard=").append(linkPsnCard);
        sb.append(", linkTelephone=").append(linkTelephone);
        sb.append(", companyWeixin=").append(companyWeixin);
        sb.append(", companyQq=").append(companyQq);
        sb.append(", licencePath=").append(licencePath);
        sb.append(", taxPath=").append(taxPath);
        sb.append(", legalPersonImage=").append(legalPersonImage);
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
        sb.append(", source=").append(source);
        sb.append(", createTime=").append(createTime);
        sb.append(", createStaff=").append(createStaff);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", updateStaff=").append(updateStaff);
        sb.append("]");
        return sb.toString();
    }
}