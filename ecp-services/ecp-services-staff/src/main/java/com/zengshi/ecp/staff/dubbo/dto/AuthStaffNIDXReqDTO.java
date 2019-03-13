package com.zengshi.ecp.staff.dubbo.dto;

import com.zengshi.ecp.server.front.dto.BaseInfo;

public class AuthStaffNIDXReqDTO extends BaseInfo {
    private String nickname;

    private Long staffId;

    private Long serialNumber;

    private static final long serialVersionUID = 1L;

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname == null ? null : nickname.trim();
    }

    public Long getStaffId() {
        return staffId;
    }

    public void setStaffId(Long staffId) {
        this.staffId = staffId;
    }

    public Long getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(Long serialNumber) {
        this.serialNumber = serialNumber;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", nickname=").append(nickname);
        sb.append(", staffId=").append(staffId);
        sb.append(", serialNumber=").append(serialNumber);
        sb.append("]");
        return sb.toString();
    }
}