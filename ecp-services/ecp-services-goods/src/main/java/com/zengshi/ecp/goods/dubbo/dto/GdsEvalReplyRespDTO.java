package com.zengshi.ecp.goods.dubbo.dto;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.zengshi.ecp.server.front.dto.BaseResponseDTO;
public class GdsEvalReplyRespDTO extends BaseResponseDTO    {
    /** 
     * serialVersionUID:TODO(用一句话描述这个变量表示什么). 
     * @since JDK 1.6 
     */ 
    private static final long serialVersionUID = -1L;

    private Long id;

    private Long evalId;

    private Long replyId;

    private String content;

    private Long gdsId;

    private Long shopId;

    private String orderId;

    private String orderSubId;

    private Long staffId;

    private Timestamp replayTime;

    private String status;

    private Timestamp createTime;

    private Long createStaff;

    private Timestamp updateTime;

    private Long updateStaff;
    
    private String replyType;
    
    private String staffName;
    /**
     * 会员等级.
     */
    private String staffLevelCode;
    
    private String detail;
    
    private List<GdsEvalReplyRespDTO> subReplys;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getEvalId() {
        return evalId;
    }

    public void setEvalId(Long evalId) {
        this.evalId = evalId;
    }

    public Long getReplyId() {
        return replyId;
    }

    public void setReplyId(Long replyId) {
        this.replyId = replyId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public Long getGdsId() {
        return gdsId;
    }

    public void setGdsId(Long gdsId) {
        this.gdsId = gdsId;
    }

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
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

    public Long getStaffId() {
        return staffId;
    }

    public void setStaffId(Long staffId) {
        this.staffId = staffId;
    }

    public Timestamp getReplayTime() {
        return replayTime;
    }

    public void setReplayTime(Timestamp replayTime) {
        this.replayTime = replayTime;
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

    public List<GdsEvalReplyRespDTO> getSubReplys() {
        return subReplys;
    }

    public void setSubReplys(List<GdsEvalReplyRespDTO> subReplys) {
        this.subReplys = subReplys;
    }
    
    public void setSubReply(GdsEvalReplyRespDTO subReply) {
        if(null != subReply){
           if(null == subReplys){
               subReplys = new ArrayList<>();
           }
           subReplys.add(subReply);
        }
    }
    

	public String getReplyType() {
		return replyType;
	}

	public void setReplyType(String replyType) {
		this.replyType = replyType;
	}
	
	public String getStaffName() {
		return staffName;
	}

	public void setStaffName(String staffName) {
		this.staffName = staffName;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}
	

	public String getStaffLevelCode() {
		return staffLevelCode;
	}

	public void setStaffLevelCode(String staffLevelCode) {
		this.staffLevelCode = staffLevelCode;
	}

	@Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", evalId=").append(evalId);
        sb.append(", replyId=").append(replyId);
        sb.append(", content=").append(content);
        sb.append(", gdsId=").append(gdsId);
        sb.append(", shopId=").append(shopId);
        sb.append(", orderId=").append(orderId);
        sb.append(", orderSubId=").append(orderSubId);
        sb.append(", staffId=").append(staffId);
        sb.append(", replayTime=").append(replayTime);
        sb.append(", status=").append(status);
        sb.append(", createTime=").append(createTime);
        sb.append(", createStaff=").append(createStaff);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", updateStaff=").append(updateStaff);
        sb.append(", replyType=").append(replyType);
        sb.append(", staffLevelCode=").append(staffLevelCode);
        sb.append("]");
        return sb.toString();
    }
}
