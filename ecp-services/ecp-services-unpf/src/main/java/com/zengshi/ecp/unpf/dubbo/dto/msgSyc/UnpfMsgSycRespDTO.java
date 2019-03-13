package com.zengshi.ecp.unpf.dubbo.dto.msgSyc;

import java.sql.Timestamp;

import com.zengshi.ecp.server.front.dto.BaseResponseDTO;

/**
* @author  lisp: 
* @date 创建时间：2016年11月15日 上午10:30:12 
* @version 1.0 
* @parameter  
* @since  
* @return  */
public class UnpfMsgSycRespDTO extends BaseResponseDTO{

    private Long id;

    private String platType;

    private Long shopId;

    private String tradeType;

    private String msg;

    private String errors;
    
    private String status;

    private Timestamp createTime;

    private Long createStaff;

    private Long updateStaff;

    private Timestamp updateTime;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPlatType() {
        return platType;
    }

    public void setPlatType(String platType) {
        this.platType = platType == null ? null : platType.trim();
    }

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public String getTradeType() {
        return tradeType;
    }

    public void setTradeType(String tradeType) {
        this.tradeType = tradeType == null ? null : tradeType.trim();
    }

    public String getMsg() {
		return msg;
	}

	public String getErrors() {
		return errors;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public void setErrors(String errors) {
		this.errors = errors;
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

    public Long getUpdateStaff() {
        return updateStaff;
    }

    public void setUpdateStaff(Long updateStaff) {
        this.updateStaff = updateStaff;
    }

    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", platType=").append(platType);
        sb.append(", shopId=").append(shopId);
        sb.append(", tradeType=").append(tradeType);
        sb.append(", msg=").append(msg);
        sb.append(", errors=").append(errors);
        sb.append(", status=").append(status);
        sb.append(", createTime=").append(createTime);
        sb.append(", createStaff=").append(createStaff);
        sb.append(", updateStaff=").append(updateStaff);
        sb.append(", updateTime=").append(updateTime);
        sb.append("]");
        return sb.toString();
    }


}


