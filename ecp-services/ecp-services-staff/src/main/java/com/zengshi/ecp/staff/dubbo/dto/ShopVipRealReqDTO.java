package com.zengshi.ecp.staff.dubbo.dto;

import java.sql.Timestamp;

import com.zengshi.ecp.server.front.dto.BaseInfo;

public class ShopVipRealReqDTO extends BaseInfo  {
    private Long id;

    private Long shopId;

    private Long staffId;

    private String staffCode;
    
    private String custLevelCode;
    
    private Long payMoney;

    private Long payMoneyFrom;
    
    private Long payMoneyEnd;
    
    private Long tradesNum;

    private Long tradesNumFrom;
    
    private Long tradesNumEnd;

    private Timestamp payDate;

    private Timestamp createTime;

    private Long createStaff;

    private Timestamp updateTime;

    private Long updateStaff;
    
    private String status;

    public Long getPayMoney() {
		return payMoney;
	}

	public void setPayMoney(Long payMoney) {
		this.payMoney = payMoney;
	}

	public Long getTradesNum() {
		return tradesNum;
	}

	public void setTradesNum(Long tradesNum) {
		this.tradesNum = tradesNum;
	}

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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", shopId=").append(shopId);
        sb.append(", staffId=").append(staffId);
        sb.append(", staffCode=").append(staffCode);
        sb.append(", custLevelCode=").append(custLevelCode);
        sb.append(", payMoneyFrom=").append(payMoneyFrom);
        sb.append(", payMoneyEnd=").append(payMoneyEnd);
        sb.append(", tradesNumFrom=").append(tradesNumFrom);
        sb.append(", tradesNumEnd=").append(tradesNumEnd);
        sb.append(", payDate=").append(payDate);
        sb.append(", createTime=").append(createTime);
        sb.append(", createStaff=").append(createStaff);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", updateStaff=").append(updateStaff);
        sb.append(", status=").append(status);
        sb.append("]");
        return sb.toString();
    }
}