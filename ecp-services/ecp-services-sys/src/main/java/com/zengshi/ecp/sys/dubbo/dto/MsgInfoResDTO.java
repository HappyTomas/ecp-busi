package com.zengshi.ecp.sys.dubbo.dto;

import java.sql.Timestamp;

import com.zengshi.ecp.server.front.dto.BaseResponseDTO;

public class MsgInfoResDTO extends BaseResponseDTO {
    private Long msgInfoId;

    private String msgCode;

    private String msgParams;

    private Long sendUserId;

    private Long recUserId;

    private Timestamp recTime;

    private String msgInfoStatus;

    private String msgCommen;

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

    public String getMsgParams() {
        return msgParams;
    }

    public void setMsgParams(String msgParams) {
        this.msgParams = msgParams == null ? null : msgParams.trim();
    }

    public Long getSendUserId() {
        return sendUserId;
    }

    public void setSendUserId(Long sendUserId) {
        this.sendUserId = sendUserId;
    }

    public Long getRecUserId() {
        return recUserId;
    }

    public void setRecUserId(Long recUserId) {
        this.recUserId = recUserId;
    }

    public Timestamp getRecTime() {
        return recTime;
    }

    public void setRecTime(Timestamp recTime) {
        this.recTime = recTime;
    }

    public String getMsgInfoStatus() {
        return msgInfoStatus;
    }

    public void setMsgInfoStatus(String msgInfoStatus) {
        this.msgInfoStatus = msgInfoStatus == null ? null : msgInfoStatus.trim();
    }

    public String getMsgCommen() {
        return msgCommen;
    }

    public void setMsgCommen(String msgCommen) {
        this.msgCommen = msgCommen == null ? null : msgCommen.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", msgInfoId=").append(msgInfoId);
        sb.append(", msgCode=").append(msgCode);
        sb.append(", msgParams=").append(msgParams);
        sb.append(", sendUserId=").append(sendUserId);
        sb.append(", recUserId=").append(recUserId);
        sb.append(", recTime=").append(recTime);
        sb.append(", msgInfoStatus=").append(msgInfoStatus);
        sb.append(", msgCommen=").append(msgCommen);
        sb.append("]");
        return sb.toString();
    }
}
