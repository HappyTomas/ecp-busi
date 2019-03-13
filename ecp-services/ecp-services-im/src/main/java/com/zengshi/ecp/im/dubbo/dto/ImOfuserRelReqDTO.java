package com.zengshi.ecp.im.dubbo.dto;

import java.sql.Timestamp;

import com.zengshi.ecp.server.front.dto.BaseInfo;

public class ImOfuserRelReqDTO extends BaseInfo  {
    /** 
	 * serialVersionUID:TODO(用一句话描述这个变量表示什么). 
	 * @since JDK 1.6 
	 */ 

	private String staffCode;

    private String ofStaffCode;

    private Long createStaff;

    private Timestamp createTime;
    
    private Long staffId;

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
        this.ofStaffCode = ofStaffCode == null ? null : ofStaffCode.trim();
    }

    public Long getCreateStaff() {
        return createStaff;
    }

    public void setCreateStaff(Long createStaff) {
        this.createStaff = createStaff;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
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
        sb.append(", staffCode=").append(staffCode);
        sb.append(", ofStaffCode=").append(ofStaffCode);
        sb.append(", createStaff=").append(createStaff);
        sb.append(", createTime=").append(createTime);
        sb.append(", staffId=").append(staffId);
        sb.append("]");
        return sb.toString();
    }
}