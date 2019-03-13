package com.zengshi.ecp.sys.dao.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class MsgSendDetail implements Serializable {
    private Long msgDetailId;

    private Long msgInfoId;

    private String msgCode;

    private Long recUserId;

    private String sendType;

    private Timestamp sendTime;

    private String recCode;

    private String msgMemo;

    private String sendStatus;

    private String sendTag;

    private Timestamp createTime;

    private Long createStaff;

    private Timestamp updateTime;

    private Long updateStaff;

    private static final long serialVersionUID = 1L;

    public Long getMsgDetailId() {
        return msgDetailId;
    }

    public void setMsgDetailId(Long msgDetailId) {
        this.msgDetailId = msgDetailId;
    }

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

    public Long getRecUserId() {
        return recUserId;
    }

    public void setRecUserId(Long recUserId) {
        this.recUserId = recUserId;
    }

    public String getSendType() {
        return sendType;
    }

    public void setSendType(String sendType) {
        this.sendType = sendType == null ? null : sendType.trim();
    }

    public Timestamp getSendTime() {
        return sendTime;
    }

    public void setSendTime(Timestamp sendTime) {
        this.sendTime = sendTime;
    }

    public String getRecCode() {
        return recCode;
    }

    public void setRecCode(String recCode) {
        this.recCode = recCode == null ? null : recCode.trim();
    }

    public String getMsgMemo() {
        return msgMemo;
    }

    public void setMsgMemo(String msgMemo) {
        this.msgMemo = msgMemo == null ? null : msgMemo.trim();
    }

    public String getSendStatus() {
        return sendStatus;
    }

    public void setSendStatus(String sendStatus) {
        this.sendStatus = sendStatus == null ? null : sendStatus.trim();
    }

    public String getSendTag() {
        return sendTag;
    }

    public void setSendTag(String sendTag) {
        this.sendTag = sendTag == null ? null : sendTag.trim();
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
        sb.append(", msgDetailId=").append(msgDetailId);
        sb.append(", msgInfoId=").append(msgInfoId);
        sb.append(", msgCode=").append(msgCode);
        sb.append(", recUserId=").append(recUserId);
        sb.append(", sendType=").append(sendType);
        sb.append(", sendTime=").append(sendTime);
        sb.append(", recCode=").append(recCode);
        sb.append(", msgMemo=").append(msgMemo);
        sb.append(", sendStatus=").append(sendStatus);
        sb.append(", sendTag=").append(sendTag);
        sb.append(", createTime=").append(createTime);
        sb.append(", createStaff=").append(createStaff);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", updateStaff=").append(updateStaff);
        sb.append("]");
        return sb.toString();
    }
}
