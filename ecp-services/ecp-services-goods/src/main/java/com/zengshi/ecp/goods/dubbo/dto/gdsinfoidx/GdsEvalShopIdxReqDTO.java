package com.zengshi.ecp.goods.dubbo.dto.gdsinfoidx;

import java.sql.Timestamp;

import com.zengshi.ecp.server.front.dto.BaseInfo;
public class GdsEvalShopIdxReqDTO extends BaseInfo    {
	private static final long serialVersionUID = -6867347023104178674L;

	 private Long shopId;

	    private Long gdsId;

	    private Long evalId;

	    private Long staffId;

	    private String staffName;

	    private Timestamp evaluationTime;

	    private Timestamp buyTime;

	    private String gdsName;

	    private Long skuId;

	    private String orderId;

	    private String orderSubId;

	    private Short score;

	    private Short correspondencyScore;

	    private Short serviceattitudeScore;

	    private Short deliveryspeedScore;

	    private String content;

	    private String isAnonymous;

	    private Short isReply;

	    private Long labelNames;

	    private String status;

	    private Timestamp createTime;

	    private Long createStaff;

	    private Timestamp updateTime;

	    private Long updateStaff;


    public Long  getShopId() {
        return shopId;
    }

    public void setShopId(Long  shopId) {
        this.shopId = shopId;
    }

    public Timestamp getEvaluationTime() {
        return evaluationTime;
    }

    public void setEvaluationTime(Timestamp evaluationTime) {
        this.evaluationTime = evaluationTime;
    }

    public Long getGdsId() {
        return gdsId;
    }

    public void setGdsId(Long gdsId) {
        this.gdsId = gdsId;
    }

    public Long getSkuId() {
        return skuId;
    }

    public void setSkuId(Long skuId) {
        this.skuId = skuId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

	public Long getEvalId() {
		return evalId;
	}

	public void setEvalId(Long evalId) {
		this.evalId = evalId;
	}

	public Long getStaffId() {
		return staffId;
	}

	public void setStaffId(Long staffId) {
		this.staffId = staffId;
	}

	public String getStaffName() {
		return staffName;
	}

	public void setStaffName(String staffName) {
		this.staffName = staffName;
	}

	public Timestamp getBuyTime() {
		return buyTime;
	}

	public void setBuyTime(Timestamp buyTime) {
		this.buyTime = buyTime;
	}

	public String getGdsName() {
		return gdsName;
	}

	public void setGdsName(String gdsName) {
		this.gdsName = gdsName;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getOrderSubId() {
		return orderSubId;
	}

	public void setOrderSubId(String orderSubId) {
		this.orderSubId = orderSubId;
	}

	public Short getScore() {
		return score;
	}

	public void setScore(Short score) {
		this.score = score;
	}

	public Short getCorrespondencyScore() {
		return correspondencyScore;
	}

	public void setCorrespondencyScore(Short correspondencyScore) {
		this.correspondencyScore = correspondencyScore;
	}

	public Short getServiceattitudeScore() {
		return serviceattitudeScore;
	}

	public void setServiceattitudeScore(Short serviceattitudeScore) {
		this.serviceattitudeScore = serviceattitudeScore;
	}

	public Short getDeliveryspeedScore() {
		return deliveryspeedScore;
	}

	public void setDeliveryspeedScore(Short deliveryspeedScore) {
		this.deliveryspeedScore = deliveryspeedScore;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getIsAnonymous() {
		return isAnonymous;
	}

	public void setIsAnonymous(String isAnonymous) {
		this.isAnonymous = isAnonymous;
	}

	public Short getIsReply() {
		return isReply;
	}

	public void setIsReply(Short isReply) {
		this.isReply = isReply;
	}

	public Long getLabelNames() {
		return labelNames;
	}

	public void setLabelNames(Long labelNames) {
		this.labelNames = labelNames;
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
