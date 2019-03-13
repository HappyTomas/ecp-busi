package com.zengshi.ecp.im.dubbo.dto;

import java.sql.Timestamp;
import java.util.Objects;

import com.zengshi.ecp.server.front.dto.BaseResponseDTO;
import com.alibaba.fastjson.JSON;

public class ImStaffHotlineResDTO extends BaseResponseDTO{
    private Long id;

    private Long staffId;

    private String staffCode;

    private String ofStaffCode;

    private String status;
    
    private String moduleType;
    
    private String hotlinePerson;

    private Long hotlinePhone;
    
    private String staffClass;

    private Timestamp createTime;

    private Long createStaff;

    private Timestamp updateTime;

    private Long updateStaff;

    private Integer lineStatus;
    //等待人数
    private Integer waitCount = 0;

    private Long custId;

    private String custCode;

    private Long hotlineId;

    private Long shopId;

    private String csaCode;
    
    private Short receptionCount;
    
    private String orderEdit;
    
    private String platSource;
    
    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public String getCsaCode() {
        return csaCode;
    }

    public void setCsaCode(String csaCode) {
        this.csaCode = csaCode;
    }

    public Long getHotlineId() {
        return hotlineId;
    }

    public void setHotlineId(Long hotlineId) {
        this.hotlineId = hotlineId;
    }


    public ImStaffHotlineResDTO() {
    }

//    public ImStaffHotlineResDTO(ImStaffHotline vo) {
//        this.setStaffId(vo.getStaffId());
//        this.setLineStatus(vo.getLineStatus());
//    }

    private static final long serialVersionUID = 1L;

    public Integer getLineStatus() {
        return lineStatus;
    }

    public void setLineStatus(Integer lineStatus) {
        this.lineStatus = lineStatus;
    }

    public Integer getWaitCount() {
        return waitCount;
    }

    public void setWaitCount(Integer waitCount) {
        this.waitCount = waitCount;
    }

    public Long getCustId() {
        return custId;
    }

    public void setCustId(Long custId) {
        this.custId = custId;
    }

    public String getCustCode() {
        return custCode;
    }

    public void setCustCode(String custCode) {
        this.custCode = custCode;
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

	public String getStaffClass() {
		return staffClass;
	}

	public void setStaffClass(String staffClass) {
		this.staffClass = staffClass;
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

	public String getOrderEdit() {
		return orderEdit;
	}

	public void setOrderEdit(String orderEdit) {
		this.orderEdit = orderEdit;
	}

	public String getPlatSource() {
		return platSource;
	}

	public void setPlatSource(String platSource) {
		this.platSource = platSource;
	}

}