package com.zengshi.ecp.staff.dubbo.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

import com.zengshi.ecp.server.front.dto.BaseResponseDTO;

public class AcctTypeResDTO extends BaseResponseDTO implements Serializable{
    /** 
     * serialVersionUID:TODO(用一句话描述这个变量表示什么). 
     * @since JDK 1.6 
     */ 
    private static final long serialVersionUID = -4307450931432829266L;

    private String acctType;
    
    private String acctTypeValue;

    private String adaptType;
    
    private String adaptTypeValue;

    private Long shopId;
    
    private String shopName;
    
    private BigDecimal deductOrderRatio;

    private Timestamp createTime;

    private Long createStaff;

    private Timestamp updateTime;

    private Long updateStaff;

    public BigDecimal getDeductOrderRatio() {
        return deductOrderRatio;
    }

    public void setDeductOrderRatio(BigDecimal deductOrderRatio) {
        this.deductOrderRatio = deductOrderRatio;
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

    public String getAcctType() {
        return acctType;
    }

    public void setAcctType(String acctType) {
        this.acctType = acctType;
    }

    public String getAdaptType() {
        return adaptType;
    }

    public void setAdaptType(String adaptType) {
        this.adaptType = adaptType;
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
        this.shopName = shopName;
    }

    public String getAcctTypeValue() {
        return acctTypeValue;
    }

    public void setAcctTypeValue(String acctTypeValue) {
        this.acctTypeValue = acctTypeValue;
    }

    public String getAdaptTypeValue() {
        return adaptTypeValue;
    }

    public void setAdaptTypeValue(String adaptTypeValue) {
        this.adaptTypeValue = adaptTypeValue;
    }
}

