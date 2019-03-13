package com.zengshi.ecp.coupon.dubbo.dto.req;

import java.sql.Timestamp;

import com.zengshi.ecp.server.front.dto.BaseInfo;

public class CoupConsumeReqDTO extends BaseInfo {
	
    private Long id;

    private Long siteId;

    private String coupNo;

    private String orderId;

    private String orderSubId;
    
    private Long coupMoney;

    private Long staffId;

    private Long coupDetailId;

    private Long coupId;

    private String operType;

    private Long shopId;

    private Timestamp useTimeStart;

    private Timestamp useTimeEnd;
    
    private Timestamp useTime;
    
    private String status;
    
    private String remark;

    private Timestamp createTime;

    private Long createStaff;

    private Timestamp updateTime;

    private Long updateStaff;

    private static final long serialVersionUID = 1L;

    
    public Long getCoupMoney() {
		return coupMoney;
	}

	public void setCoupMoney(Long coupMoney) {
		this.coupMoney = coupMoney;
	}

	public Timestamp getUseTime() {
		return useTime;
	}

	public void setUseTime(Timestamp useTime) {
		this.useTime = useTime;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSiteId() {
        return siteId;
    }

    public void setSiteId(Long siteId) {
        this.siteId = siteId;
    }

    public String getCoupNo() {
        return coupNo;
    }

    public void setCoupNo(String coupNo) {
        this.coupNo = coupNo == null ? null : coupNo.trim();
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId == null ? null : orderId.trim();
    }

    public String getOrderSubId() {
        return orderSubId;
    }

    public void setOrderSubId(String orderSubId) {
        this.orderSubId = orderSubId == null ? null : orderSubId.trim();
    }

    public Long getStaffId() {
        return staffId;
    }

    public void setStaffId(Long staffId) {
        this.staffId = staffId;
    }

    public Long getCoupDetailId() {
        return coupDetailId;
    }

    public void setCoupDetailId(Long coupDetailId) {
        this.coupDetailId = coupDetailId;
    }

    public Long getCoupId() {
        return coupId;
    }

    public void setCoupId(Long coupId) {
        this.coupId = coupId;
    }

    public String getOperType() {
        return operType;
    }

    public void setOperType(String operType) {
        this.operType = operType == null ? null : operType.trim();
    }

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
    
    public Timestamp getUseTimeStart() {
		return useTimeStart;
	}

	public void setUseTimeStart(Timestamp useTimeStart) {
		this.useTimeStart = useTimeStart;
	}

	public Timestamp getUseTimeEnd() {
		return useTimeEnd;
	}

	public void setUseTimeEnd(Timestamp useTimeEnd) {
		this.useTimeEnd = useTimeEnd;
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
