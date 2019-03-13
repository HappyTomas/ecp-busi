package com.zengshi.ecp.goods.dubbo.dto;

import java.sql.Timestamp;
import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.zengshi.ecp.server.front.dto.BaseInfo;
@SuppressWarnings("rawtypes")
public class GdsEvalReqDTO extends BaseInfo    {
    /** 
     * serialVersionUID:TODO(用一句话描述这个变量表示什么). 
     * @since JDK 1.6 
     */ 
    private static final long serialVersionUID = -1L;

    private Long id;

    private Long staffId;

    private String staffName;

    private Timestamp evaluationTime;

    private Timestamp buyTime;

    private Long gdsId;

    private String gdsName;

    private Long skuId;

    private Long shopId;

    private String orderId;

    private String orderSubId;

    private Short score;

    private Short correspondencyScore;

    private Short serviceattitudeScore;

    private Short deliveryspeedScore;

    private String content;

    private String isAnonymous;

    private Short isReply;

    private String labelNames;

    private String status;

    private Timestamp createTime;

    private Long createStaff;

    private Timestamp updateTime;

    private Long updateStaff;
    
    private Timestamp beginTime;
    
    private Timestamp endTime;
    // 是否删除回复,true-表示删除该评价时,则时也删除与之前联的回复.
    private Boolean delReply;
    // 评价评情.
    private String detail;
    // 执行批量操作时的回复ID.
    private List<Long> ids;
    // 批量审核时需要提交的审核状态.
    private String auditStatus;
    
    // 分数区间下限.
    private Short scoreFrom;
    // 分类区间上限.
    private Short scoreTo;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getStaffId() {
        return staffId;
    }

    public void setStaffId(Long staffId) {
        this.staffId = staffId;
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

    public Long  getShopId() {
        return shopId;
    }

    public void setShopId(Long  shopId) {
        this.shopId = shopId;
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
        this.content = content == null ? null : content.trim();
    }

    public String getIsAnonymous() {
        return isAnonymous;
    }

    public void setIsAnonymous(String isAnonymous) {
        this.isAnonymous = isAnonymous == null ? null : isAnonymous.trim();
    }

    public Short getIsReply() {
        return isReply;
    }

    public void setIsReply(Short isReply) {
        this.isReply = isReply;
    }

    public String getLabelNames() {
        return labelNames;
    }

    public void setLabelNames(String labelNames) {
        this.labelNames = labelNames;
    }

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
    
    

    public Timestamp getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(Timestamp beginTime) {
        this.beginTime = beginTime;
    }

    public Timestamp getEndTime() {
        return endTime;
    }

    public void setEndTime(Timestamp endTime) {
        this.endTime = endTime;
    }

    public Boolean isDelReply() {
        return delReply;
    }

    public void setDelReply(Boolean delReply) {
        this.delReply = delReply;
    }

    public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}
	
	

	public Short getScore() {
		return score;
	}

	public void setScore(Short score) {
		this.score = score;
	}

	public List<Long> getIds() {
		return ids;
	}

	public void setIds(List<Long> ids) {
		this.ids = ids;
	}

	public String getAuditStatus() {
		return auditStatus;
	}

	public void setAuditStatus(String auditStatus) {
		this.auditStatus = auditStatus;
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
	
	public Short getScoreFrom() {
		return scoreFrom;
	}

	public void setScoreFrom(Short scoreFrom) {
		this.scoreFrom = scoreFrom;
	}

	public Short getScoreTo() {
		return scoreTo;
	}

	public void setScoreTo(Short scoreTo) {
		this.scoreTo = scoreTo;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

}
