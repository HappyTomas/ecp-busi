package com.zengshi.ecp.staff.dubbo.dto;

import java.sql.Timestamp;

import com.zengshi.ecp.server.front.dto.BaseResponseDTO;

public class ShopVipRealResDTO extends BaseResponseDTO {
    private Long id;

    private Long shopId;

    private Long staffId;

    private String staffCode;

    private String custLevelCode;
    
    private Long payMoney;

    private Long tradesNum;

    private Timestamp payDate;

    private Timestamp createTime;

    private Long createStaff;

    private Timestamp updateTime;

    private Long updateStaff;
    
    private String status;
    
    private String custLevelName;
    
    //店铺会员等级
    private String vipLevelCode;
    
    private String vipLevelName;
    
    //店铺VIP规则
    private Long discount;
    
    //店铺基础信息
    private String shopName;
    
    private String logoPath;
    

    public String getVipLevelCode() {
		return vipLevelCode;
	}

	public void setVipLevelCode(String vipLevelCode) {
		this.vipLevelCode = vipLevelCode;
	}

	public String getVipLevelName() {
		return vipLevelName;
	}

	public void setVipLevelName(String vipLevelName) {
		this.vipLevelName = vipLevelName;
	}

	public Long getDiscount() {
		return discount;
	}

	public void setDiscount(Long discount) {
		this.discount = discount;
	}

	private static final long serialVersionUID = 1L;
    
    private CustInfoResDTO custInfo;
    
    public CustInfoResDTO getCustInfo() {
        return custInfo;
    }

    public void setCustInfo(CustInfoResDTO custInfo) {
        this.custInfo = custInfo;
    }

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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCustLevelName() {
		return custLevelName;
	}

	public void setCustLevelName(String custLevelName) {
		this.custLevelName = custLevelName;
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
        sb.append(", payMoney=").append(payMoney);
        sb.append(", tradesNum=").append(tradesNum);
        sb.append(", payDate=").append(payDate);
        sb.append(", createTime=").append(createTime);
        sb.append(", createStaff=").append(createStaff);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", updateStaff=").append(updateStaff);
        sb.append(", custInfo=").append(custInfo);
        sb.append(", status=").append(status);
        sb.append("]");
        return sb.toString();
    }

	public String getShopName() {
		return shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
	}

	public String getLogoPath() {
		return logoPath;
	}

	public void setLogoPath(String logoPath) {
		this.logoPath = logoPath;
	}
}