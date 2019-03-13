package com.zengshi.ecp.sys.dubbo.dto;

import java.sql.Timestamp;

import com.zengshi.ecp.server.front.dto.BaseResponseDTO;

public class MsgDefineResDTO extends BaseResponseDTO {
    private String msgCode;

    private String msgName;

    private String msgType;

    private Timestamp createTime;

    private Long createStaff;

    private Timestamp updateTime;

    private Long updateStaff;
    
    private String insiteOnOff;
    
    private String smsOnOff;
    
    private String emailOnOff;

    private static final long serialVersionUID = 1L;

    public String getMsgCode() {
        return msgCode;
    }

    public void setMsgCode(String msgCode) {
        this.msgCode = msgCode == null ? null : msgCode.trim();
    }

    public String getMsgName() {
        return msgName;
    }

    public void setMsgName(String msgName) {
        this.msgName = msgName == null ? null : msgName.trim();
    }

    public String getMsgType() {
        return msgType;
    }

    public void setMsgType(String msgType) {
        this.msgType = msgType == null ? null : msgType.trim();
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

    public String getInsiteOnOff() {
        return insiteOnOff;
    }

    public void setInsiteOnOff(String insiteOnOff) {
        this.insiteOnOff = insiteOnOff;
    }

    public String getSmsOnOff() {
        return smsOnOff;
    }

    public void setSmsOnOff(String smsOnOff) {
        this.smsOnOff = smsOnOff;
    }

    public String getEmailOnOff() {
        return emailOnOff;
    }

    public void setEmailOnOff(String emailOnOff) {
        this.emailOnOff = emailOnOff;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", msgCode=").append(msgCode);
        sb.append(", msgName=").append(msgName);
        sb.append(", msgType=").append(msgType);
        sb.append(", createTime=").append(createTime);
        sb.append(", createStaff=").append(createStaff);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", updateStaff=").append(updateStaff);
        sb.append(", insiteOnOff=").append(insiteOnOff);
        sb.append(", smsOnOff=").append(smsOnOff);
        sb.append(", emailOnOff=").append(emailOnOff);
        sb.append("]");
        return sb.toString();
    }
}
