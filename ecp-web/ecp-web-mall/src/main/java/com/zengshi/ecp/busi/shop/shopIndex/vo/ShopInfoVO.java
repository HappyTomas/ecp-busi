package com.zengshi.ecp.busi.shop.shopIndex.vo;

import java.sql.Timestamp;
import com.zengshi.ecp.server.front.dto.BaseResponseDTO;

/**
 * Title: ECP <br>
 * Project Name:ecp-web-mall <br>
 * Description: <br>
 * Date:2015年9月14日下午5:05:04 <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author huangdf
 * @version @param <T>
 * @since JDK 1.6
 */
public class ShopInfoVO extends BaseResponseDTO {
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
    
    private String smallLogoPathURL;

    private String linkPerson;

    private String linkMobilephone;

    private String linkEmail;

    private String isUseVip;

    private String offlineSupported;
    
    private double evalRate;
    
    private Long favNum;
    
    private Long saleNum;

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
		this.shopName = shopName;
	}

	public String getShopFullName() {
		return shopFullName;
	}

	public void setShopFullName(String shopFullName) {
		this.shopFullName = shopFullName;
	}

	public String getShopGrade() {
		return shopGrade;
	}

	public void setShopGrade(String shopGrade) {
		this.shopGrade = shopGrade;
	}

	public String getShopDesc() {
		return shopDesc;
	}

	public void setShopDesc(String shopDesc) {
		this.shopDesc = shopDesc;
	}

	public String getShopStatus() {
		return shopStatus;
	}

	public void setShopStatus(String shopStatus) {
		this.shopStatus = shopStatus;
	}

	public String getShopDealType() {
		return shopDealType;
	}

	public void setShopDealType(String shopDealType) {
		this.shopDealType = shopDealType;
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
		this.microMessageExt = microMessageExt;
	}

	public String getHotShowSupported() {
		return hotShowSupported;
	}

	public void setHotShowSupported(String hotShowSupported) {
		this.hotShowSupported = hotShowSupported;
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
		this.logoPath = logoPath;
	}

	public String getLogoPathURL() {
		return logoPathURL;
	}

	public void setLogoPathURL(String logoPathURL) {
		this.logoPathURL = logoPathURL;
	}

	public String getLinkPerson() {
		return linkPerson;
	}

	public void setLinkPerson(String linkPerson) {
		this.linkPerson = linkPerson;
	}

	public String getLinkMobilephone() {
		return linkMobilephone;
	}

	public void setLinkMobilephone(String linkMobilephone) {
		this.linkMobilephone = linkMobilephone;
	}

	public String getLinkEmail() {
		return linkEmail;
	}

	public void setLinkEmail(String linkEmail) {
		this.linkEmail = linkEmail;
	}

	public String getIsUseVip() {
		return isUseVip;
	}

	public void setIsUseVip(String isUseVip) {
		this.isUseVip = isUseVip;
	}

	public String getOfflineSupported() {
		return offlineSupported;
	}

	public void setOfflineSupported(String offlineSupported) {
		this.offlineSupported = offlineSupported;
	}

	public double getEvalRate() {
		return evalRate;
	}

	public void setEvalRate(double evalRate) {
		this.evalRate = evalRate;
	}

	public Long getFavNum() {
		return favNum;
	}

	public void setFavNum(Long favNum) {
		this.favNum = favNum;
	}

	public Long getSaleNum() {
		return saleNum;
	}

	public void setSaleNum(Long saleNum) {
		this.saleNum = saleNum;
	}

	public String getSmallLogoPathURL() {
		return smallLogoPathURL;
	}

	public void setSmallLogoPathURL(String smallLogoPathURL) {
		this.smallLogoPathURL = smallLogoPathURL;
	}
	
	
}
