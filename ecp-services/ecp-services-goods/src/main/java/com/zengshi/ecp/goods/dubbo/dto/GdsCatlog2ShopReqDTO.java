package com.zengshi.ecp.goods.dubbo.dto;

import java.sql.Timestamp;
import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.zengshi.ecp.server.front.dto.BaseInfo;

@SuppressWarnings("rawtypes")
public class GdsCatlog2ShopReqDTO extends BaseInfo {
	
	
	/** 
	 * serialVersionUID:TODO(用一句话描述这个变量表示什么). 
	 * @since JDK 1.6 
	 */ 
	private static final long serialVersionUID = 5163473807233093751L;

    private Long shopId;

	private String status;

    private Timestamp createTime;

    private Long createStaff;

    private Timestamp updateTime;

    private Long updateStaff;
    
    private Long catlogId;
    
    private List<Long> catlogIds;


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
    
	public List<Long> getCatlogIds() {
		return catlogIds;
	}

	public void setCatlogIds(List<Long> catlogIds) {
		this.catlogIds = catlogIds;
	}

	public Long getShopId() {
		return shopId;
	}

	public void setShopId(Long shopId) {
		this.shopId = shopId;
	}
	
	

	public Long getCatlogId() {
		return catlogId;
	}

	public void setCatlogId(Long catlogId) {
		this.catlogId = catlogId;
	}

	@Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
    
    
}
