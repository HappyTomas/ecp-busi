package com.zengshi.ecp.goods.dubbo.dto;

import java.math.BigDecimal;
import java.sql.Timestamp;

import com.zengshi.ecp.server.front.dto.BaseResponseDTO;

public class GdsCatgCustDiscRespDTO extends BaseResponseDTO  {

    /** 
     * serialVersionUID:TODO(用一句话描述这个变量表示什么). 
     * @since JDK 1.6 
     */ 
    private static final long serialVersionUID = -1L;
    
    private Long id;

    private String custLevelCode;

    private String catgCode;
    
    private String catgName;
    
    private String custLevelName;

    private Long shopId;

    private BigDecimal discount;

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

    public String getCatgName() {
        return catgName;
    }

    public void setCatgName(String catgName) {
        this.catgName = catgName;
    }

    public String getCustLevelName() {
        return custLevelName;
    }

    public void setCustLevelName(String custLevelName) {
        this.custLevelName = custLevelName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    
}

