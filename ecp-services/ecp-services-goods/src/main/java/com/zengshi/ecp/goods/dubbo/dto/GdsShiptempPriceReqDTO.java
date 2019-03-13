package com.zengshi.ecp.goods.dubbo.dto;

import java.sql.Timestamp;
import java.util.List;

import com.zengshi.ecp.server.front.dto.BaseInfo;
@SuppressWarnings("rawtypes")
public class GdsShiptempPriceReqDTO extends BaseInfo{
    /** 
     * serialVersionUID:TODO(用一句话描述这个变量表示什么). 
     * @since JDK 1.6 
     */ 
    private static final long serialVersionUID = -7356908556176707408L;

    private Long pricingListId;

    private Long shipTemplateId;

    private String pricingMode;
    
    private String countryCode;

    private String provinceCode;

    private String cityCode;

    private String countyCode;

    private Long areaLevel;

    private Long firstAmount;

    private Long firstPrice;

    private Long continueAmount;

    private Long continuePrice;

    private Long freeAmount;

    private String status;

    private Timestamp createTime;

    private Long createStaff;

    private Timestamp updateTime;

    private Long updateStaff;
    
    private List<AreaCodeDTO> areaCodeList;

    public Long getPricingListId() {
        return pricingListId;
    }

    public void setPricingListId(Long pricingListId) {
        this.pricingListId = pricingListId;
    }

    public Long getShipTemplateId() {
        return shipTemplateId;
    }

    public void setShipTemplateId(Long shipTemplateId) {
        this.shipTemplateId = shipTemplateId;
    }

    public String getPricingMode() {
        return pricingMode;
    }

    public void setPricingMode(String pricingMode) {
        this.pricingMode = pricingMode == null ? null : pricingMode.trim();
    }


    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getProvinceCode() {
        return provinceCode;
    }

    public void setProvinceCode(String provinceCode) {
        this.provinceCode = provinceCode;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public String getCountyCode() {
        return countyCode;
    }

    public void setCountyCode(String countyCode) {
        this.countyCode = countyCode;
    }

    public Long getAreaLevel() {
        return areaLevel;
    }

    public void setAreaLevel(Long areaLevel) {
        this.areaLevel = areaLevel;
    }

    public Long getFirstAmount() {
        return firstAmount;
    }

    public void setFirstAmount(Long firstAmount) {
        this.firstAmount = firstAmount;
    }

    public Long getFirstPrice() {
        return firstPrice;
    }

    public void setFirstPrice(Long firstPrice) {
        this.firstPrice = firstPrice;
    }

    public Long getContinueAmount() {
        return continueAmount;
    }

    public void setContinueAmount(Long continueAmount) {
        this.continueAmount = continueAmount;
    }

    public Long getContinuePrice() {
        return continuePrice;
    }

    public void setContinuePrice(Long continuePrice) {
        this.continuePrice = continuePrice;
    }

    public Long getFreeAmount() {
        return freeAmount;
    }

    public void setFreeAmount(Long freeAmount) {
        this.freeAmount = freeAmount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
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
    
    
    public List<AreaCodeDTO> getAreaCodeList() {
        return areaCodeList;
    }

    public void setAreaCodeList(List<AreaCodeDTO> areaCodeList) {
        this.areaCodeList = areaCodeList;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", pricingListId=").append(pricingListId);
        sb.append(", shipTemplateId=").append(shipTemplateId);
        sb.append(", pricingMode=").append(pricingMode);
        sb.append(", areaLevel=").append(areaLevel);
        sb.append(", firstAmount=").append(firstAmount);
        sb.append(", firstPrice=").append(firstPrice);
        sb.append(", continueAmount=").append(continueAmount);
        sb.append(", continuePrice=").append(continuePrice);
        sb.append(", freeAmount=").append(freeAmount);
        sb.append(", status=").append(status);
        sb.append(", createTime=").append(createTime);
        sb.append(", createStaff=").append(createStaff);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", updateStaff=").append(updateStaff);
        sb.append("]");
        return sb.toString();
    }
}
