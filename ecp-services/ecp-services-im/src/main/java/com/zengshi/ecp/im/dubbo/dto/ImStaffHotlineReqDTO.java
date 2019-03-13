package com.zengshi.ecp.im.dubbo.dto;

import java.sql.Timestamp;

import com.zengshi.ecp.server.front.dto.BaseInfo;
import com.alibaba.fastjson.JSON;

public class ImStaffHotlineReqDTO extends BaseInfo{
    private Long id;

    private Long staffId;

    private String staffCode;

    private String ofStaffCode;

    private String status;
    
    private String staffClass;

    private Timestamp createTime;

    private Long createStaff;

    private Timestamp updateTime;

    private Long updateStaff;
    
    private Long shopId;

    private String moduleType;

    private String hotlinePerson;

    private Long hotlinePhone;

    private Integer lineStatus;

    private Long custId;

    private static final long serialVersionUID = 1L;

    private Long hotlineId;

    private String custLevel;

    private String csaCode;

    private String sessionId;
    
    private Short receptionCount;

    private Long reqTime;
    
    private String orderEdit;
    
    private String platSource;

    public Long getReqTime() {
        return reqTime;
    }

    public void setReqTime(Long reqTime) {
        this.reqTime = reqTime;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getCsaCode() {
        return csaCode;
    }

    public void setCsaCode(String csaCode) {
        this.csaCode = csaCode;
    }

    public String getCustLevel() {
        return custLevel;
    }

    public void setCustLevel(String custLevel) {
        this.custLevel = custLevel;
    }

    public Long getHotlineId() {
        return hotlineId;
    }

    public void setHotlineId(Long hotlineId) {
        this.hotlineId = hotlineId;
    }

    public Long getCustId() {
        return custId;
    }

    public void setCustId(Long custId) {
        this.custId = custId;
    }

    public Integer getLineStatus() {
        return lineStatus;
    }

    public void setLineStatus(Integer lineStatus) {
        this.lineStatus = lineStatus;
    }

    public String getStaffClass() {
		return staffClass;
	}

	public void setStaffClass(String staffClass) {
		this.staffClass = staffClass;
	}

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

    public String getStaffCode() {
        return staffCode;
    }

    public void setStaffCode(String staffCode) {
        this.staffCode = staffCode == null ? null : staffCode.trim();
    }

    public String getOfStaffCode() {
		return ofStaffCode;
	}

	public void setOfStaffCode(String ofStaffCode) {
		this.ofStaffCode = ofStaffCode;
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
    

    public Long getShopId() {
		return shopId;
	}

	public void setShopId(Long shopId) {
		this.shopId = shopId;
	}

	public String getModuleType() {
		return moduleType;
	}

	public void setModuleType(String moduleType) {
		this.moduleType = moduleType;
	}

	public String getHotlinePerson() {
		return hotlinePerson;
	}

	public void setHotlinePerson(String hotlinePerson) {
		this.hotlinePerson = hotlinePerson;
	}

	public Long getHotlinePhone() {
		return hotlinePhone;
	}

	public void setHotlinePhone(Long hotlinePhone) {
		this.hotlinePhone = hotlinePhone;
	}
	

	public Short getReceptionCount() {
		return receptionCount;
	}

	public void setReceptionCount(Short receptionCount) {
		this.receptionCount = receptionCount;
	}

	@Override
    public String toString() {
        String jsonStr = JSON.toJSONString(this);
        return jsonStr;
//        StringBuilder sb = new StringBuilder();
//        sb.append(getClass().getSimpleName());
//        sb.append(" [");
//        sb.append("Hash = ").append(hashCode());
//        sb.append(", id=").append(id);
//        sb.append(", staffId=").append(staffId);
//        sb.append(", staffCode=").append(staffCode);
//        sb.append(", ofStaffCode=").append(ofStaffCode);
//        sb.append(", status=").append(status);
//        sb.append(", createTime=").append(createTime);
//        sb.append(", createStaff=").append(createStaff);
//        sb.append(", updateTime=").append(updateTime);
//        sb.append(", updateStaff=").append(updateStaff);
//        sb.append("]");
//        return sb.toString();
    }

	public String getPlatSource() {
		return platSource;
	}

	public void setPlatSource(String platSource) {
		this.platSource = platSource;
	}

	public String getOrderEdit() {
		return orderEdit;
	}

	public void setOrderEdit(String orderEdit) {
		this.orderEdit = orderEdit;
	}
}