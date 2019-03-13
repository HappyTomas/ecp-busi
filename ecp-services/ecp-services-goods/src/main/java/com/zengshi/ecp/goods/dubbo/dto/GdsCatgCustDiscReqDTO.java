package com.zengshi.ecp.goods.dubbo.dto;

import java.math.BigDecimal;
import java.sql.Timestamp;

import com.zengshi.ecp.server.front.dto.BaseInfo;

@SuppressWarnings("rawtypes")
public class GdsCatgCustDiscReqDTO extends BaseInfo {

    /** 
     * @since JDK 1.6 
     */ 
    private static final long serialVersionUID = -1L;

    private Long id;
    
    private String custLevelCode;

    private String catgCode;

    private Long shopId;

    private BigDecimal discount;
    
    private BigDecimal discount01;
    
    private BigDecimal discount02;

    private BigDecimal discount03;

    private BigDecimal discount04;
    

    private String status;

    private String mark;

    private Timestamp createTime;

    private Long createStaff;

    private Timestamp updateTime;

    private Long updateStaff;


    public String getCustLevelCode() {
        return custLevelCode;
    }

    public void setCustLevelCode(String custLevelCode) {
        this.custLevelCode = custLevelCode;
    }

    public String getCatgCode() {
        return catgCode;
    }

    public void setCatgCode(String catgCode) {
        this.catgCode = catgCode == null ? null : catgCode.trim();
    }

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark == null ? null : mark.trim();
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
        sb.append(", custLevelCode=").append(custLevelCode);
        sb.append(", catgCode=").append(catgCode);
        sb.append(", shopId=").append(shopId);
        sb.append(", discount=").append(discount);
        sb.append(", status=").append(status);
        sb.append(", mark=").append(mark);
        sb.append(", createTime=").append(createTime);
        sb.append(", createStaff=").append(createStaff);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", updateStaff=").append(updateStaff);
        sb.append("]");
        return sb.toString();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getDiscount01() {
        return discount01;
    }

    public void setDiscount01(BigDecimal discount01) {
        this.discount01 = discount01;
    }

    public BigDecimal getDiscount02() {
        return discount02;
    }

    public void setDiscount02(BigDecimal discount02) {
        this.discount02 = discount02;
    }

    public BigDecimal getDiscount03() {
        return discount03;
    }

    public void setDiscount03(BigDecimal discount03) {
        this.discount03 = discount03;
    }

    public BigDecimal getDiscount04() {
        return discount04;
    }

    public void setDiscount04(BigDecimal discount04) {
        this.discount04 = discount04;
    }

    
}

