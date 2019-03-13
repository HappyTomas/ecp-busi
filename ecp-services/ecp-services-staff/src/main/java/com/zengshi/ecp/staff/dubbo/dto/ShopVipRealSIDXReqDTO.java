package com.zengshi.ecp.staff.dubbo.dto;

import com.zengshi.ecp.server.front.dto.BaseInfo;
import com.zengshi.paas.common.ICriteria;

public class ShopVipRealSIDXReqDTO extends BaseInfo<ICriteria> {
    private Long id;

    private Long shopId;

    private Long staffId;
    
    //店铺名称（模糊查询）
    private String shopName;

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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", shopId=").append(shopId);
        sb.append(", staffId=").append(staffId);
        sb.append("]");
        return sb.toString();
    }

	public String getShopName() {
		return shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
	}
}