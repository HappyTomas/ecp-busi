package com.zengshi.ecp.busi.buyer.vo;

import java.io.Serializable;

public class CoupDetailVO
  implements Serializable
{
  private static final long serialVersionUID = 1L;
  private String coupNo;
  private String coupName;
  private Integer coupValue;
  private String conditions;
  private String conditionsShow;
  private String coupStatus;
  private Long siteId;
  private String activeTime;
  private String inactiveTime;
  private Long shopId;
  private String shopName;
  private String discountCoup;

  public String getCoupNo()
  {
    return this.coupNo;
  }
  public void setCoupNo(String coupNo) {
    this.coupNo = coupNo;
  }
  public String getCoupName() {
    return this.coupName;
  }
  public void setCoupName(String coupName) {
    this.coupName = coupName;
  }
  public Integer getCoupValue() {
    return this.coupValue;
  }
  public void setCoupValue(Integer coupValue) {
    this.coupValue = coupValue;
  }
  public String getConditions() {
    return this.conditions;
  }
  public void setConditions(String conditions) {
    this.conditions = conditions;
  }
  public String getConditionsShow() {
    return this.conditionsShow;
  }
  public void setConditionsShow(String conditionsShow) {
    this.conditionsShow = conditionsShow;
  }
  public String getCoupStatus() {
    return this.coupStatus;
  }
  public void setCoupStatus(String coupStatus) {
    this.coupStatus = coupStatus;
  }
  public Long getSiteId() {
    return this.siteId;
  }
  public void setSiteId(Long siteId) {
    this.siteId = siteId;
  }
  public String getActiveTime() {
    return this.activeTime;
  }
  public void setActiveTime(String activeTime) {
    this.activeTime = activeTime;
  }
  public String getInactiveTime() {
    return this.inactiveTime;
  }
  public void setInactiveTime(String inactiveTime) {
    this.inactiveTime = inactiveTime;
  }
  public Long getShopId() {
    return this.shopId;
  }
  public void setShopId(Long shopId) {
    this.shopId = shopId;
  }
  public String getShopName() {
    return this.shopName;
  }
  public void setShopName(String shopName) {
    this.shopName = shopName;
  }
  public String getDiscountCoup() {
    return this.discountCoup;
  }
  public void setDiscountCoup(String discountCoup) {
    this.discountCoup = discountCoup;
  }
}