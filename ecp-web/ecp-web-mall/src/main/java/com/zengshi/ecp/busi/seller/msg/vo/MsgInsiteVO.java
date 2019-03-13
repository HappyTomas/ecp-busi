package com.zengshi.ecp.busi.seller.msg.vo;

import java.sql.Timestamp;

import com.zengshi.ecp.base.vo.EcpBasePageReqVO;

public class MsgInsiteVO extends EcpBasePageReqVO{
    private String msgCode;

    private String msgContext;
    
    private Long msgInfoId;

    private String readFlag;

    private Timestamp recTime;

    private Long fromStaffId;

    private Timestamp createTime;

    private Long createStaff;

    private Timestamp updateTime;

    private Long updateStaff;

    private static final long serialVersionUID = 1L;
    
    public Long getMsgInfoId() {
        return msgInfoId;
    }

    public void setMsgInfoId(Long msgInfoId) {
        this.msgInfoId = msgInfoId;
    }

    public String getMsgCode() {
        return msgCode;
    }

    public void setMsgCode(String msgCode) {
        this.msgCode = msgCode == null ? null : msgCode.trim();
    }

    public String getMsgContext() {
        return msgContext;
    }

    public void setMsgContext(String msgContext) {
        this.msgContext = msgContext == null ? null : msgContext.trim();
    }

    public String getReadFlag() {
        return readFlag;
    }

    public void setReadFlag(String readFlag) {
        this.readFlag = readFlag == null ? null : readFlag.trim();
    }

    public Timestamp getRecTime() {
        return recTime;
    }

    public void setRecTime(Timestamp recTime) {
        this.recTime = recTime;
    }

    public Long getFromStaffId() {
        return fromStaffId;
    }

    public void setFromStaffId(Long fromStaffId) {
        this.fromStaffId = fromStaffId;
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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", msgCode=").append(msgCode);
        sb.append(", msgContext=").append(msgContext);
        sb.append(", readFlag=").append(readFlag);
        sb.append(", recTime=").append(recTime);
        sb.append(", fromStaffId=").append(fromStaffId);
        sb.append(", createTime=").append(createTime);
        sb.append(", createStaff=").append(createStaff);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", updateStaff=").append(updateStaff);
        sb.append("]");
        return sb.toString();
    }
}