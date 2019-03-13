package com.zengshi.ecp.busi.seller.staff.vo;

import java.io.Serializable;
import java.sql.Timestamp;

import com.zengshi.ecp.base.vo.EcpBasePageReqVO;

public class ShopVipRealVO extends EcpBasePageReqVO implements Serializable{
    private Long id;

    private Long shopId;

    private Long staffId;

    private String staffCode;
    
    private String custLevelCode;

    private Long payMoneyFrom;
    
    private Long payMoneyEnd;

    private Long tradesNumFrom;
    
    private Long tradesNumEnd;

    private Timestamp payDate;

    private Timestamp createTime;

    private Long createStaff;

    private Timestamp updateTime;

    private Long updateStaff;
    
    private String status;
    
    public String getVipLevelName() {
        return vipLevelName;
    }

    public void setVipLevelName(String vipLevelName) {
        this.vipLevelName = vipLevelName;
    }

    public String getVipLevelCode() {
        return vipLevelCode;
    }

    public void setVipLevelCode(String vipLevelCode) {
        this.vipLevelCode = vipLevelCode;
    }

    private String vipLevelName;
    
    private String vipLevelCode;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public Long getStaffId() {
        return staffId;
    }

    public void setStaffId(Long staffId) {
        this.staffId = staffId;
    }

    

    public Long getPayMoneyFrom() {
        return payMoneyFrom;
    }

    public void setPayMoneyFrom(Long payMoneyFrom) {
        this.payMoneyFrom = payMoneyFrom;
    }

    public Long getPayMoneyEnd() {
        return payMoneyEnd;
    }

    public void setPayMoneyEnd(Long payMoneyEnd) {
        this.payMoneyEnd = payMoneyEnd;
    }

    public Long getTradesNumFrom() {
        return tradesNumFrom;
    }

    public void setTradesNumFrom(Long tradesNumFrom) {
        this.tradesNumFrom = tradesNumFrom;
    }

    public Long getTradesNumEnd() {
        return tradesNumEnd;
    }

    public void setTradesNumEnd(Long tradesNumEnd) {
        this.tradesNumEnd = tradesNumEnd;
    }

    public Timestamp getPayDate() {
        return payDate;
    }

    public void setPayDate(Timestamp payDate) {
        this.payDate = payDate;
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

    public String getStaffCode() {
        return staffCode;
    }

    public void setStaffCode(String staffCode) {
        this.staffCode = staffCode;
    }

    public String getCustLevelCode() {
        return custLevelCode;
    }

    public void setCustLevelCode(String custLevelCode) {
        this.custLevelCode = custLevelCode;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

