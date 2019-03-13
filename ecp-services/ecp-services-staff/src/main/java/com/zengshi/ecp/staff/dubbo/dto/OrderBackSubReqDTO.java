package com.zengshi.ecp.staff.dubbo.dto;

import com.zengshi.ecp.server.front.dto.BaseInfo;

public class OrderBackSubReqDTO extends BaseInfo {
    private Long staffId;
    
    private String orderId;

    private String subOrderId;

    private String type;//是否可以扣为负值（积分、资金账户）

    private Long scale;

    private Long frozenScore;
    
    private Long siteId;//站点
    
    private boolean lastFlag;//是否为退货的最后一笔
    
    private String refundOrBack;//退款退货标识：0：退款；1：退货；

    private static final long serialVersionUID = 1L;
    
    private Long backId;//订单退款退货申请id

    public Long getStaffId() {
		return staffId;
	}

	public void setStaffId(Long staffId) {
		this.staffId = staffId;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getSubOrderId() {
		return subOrderId;
	}

	public void setSubOrderId(String subOrderId) {
		this.subOrderId = subOrderId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Long getScale() {
		return scale;
	}

	public void setScale(Long scale) {
		this.scale = scale;
	}

	public Long getFrozenScore() {
		return frozenScore;
	}

	public void setFrozenScore(Long frozenScore) {
		this.frozenScore = frozenScore;
	}

	public boolean isLastFlag() {
        return lastFlag;
    }

    public void setLastFlag(boolean lastFlag) {
        this.lastFlag = lastFlag;
    }

    public Long getSiteId() {
        return siteId;
    }

    public void setSiteId(Long siteId) {
        this.siteId = siteId;
    }

    public String getRefundOrBack() {
		return refundOrBack;
	}

	public void setRefundOrBack(String refundOrBack) {
		this.refundOrBack = refundOrBack;
	}

	public Long getBackId() {
        return backId;
    }

    public void setBackId(Long backId) {
        this.backId = backId;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", staffId=").append(staffId);
        sb.append(", orderId=").append(orderId);
        sb.append(", subOrderId=").append(subOrderId);
        sb.append(", type=").append(type);
        sb.append(", scale=").append(scale);
        sb.append(", frozenScore=").append(frozenScore);
        sb.append(", lastFlag=").append(lastFlag);
        sb.append(", siteId=").append(siteId);
        sb.append(", refundOrBack=").append(refundOrBack);
        sb.append(", backId=").append(backId);
        sb.append("]");
        return sb.toString();
    }
}