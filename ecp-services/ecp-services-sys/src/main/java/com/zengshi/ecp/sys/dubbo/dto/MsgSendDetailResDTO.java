package com.zengshi.ecp.sys.dubbo.dto;

import java.sql.Timestamp;

import com.zengshi.ecp.server.front.dto.BaseResponseDTO;

public class MsgSendDetailResDTO extends BaseResponseDTO {
    private Long msgDetailId;

    private Long msgInfoId;

    private String msgCode;

    private String sendType;

    private Timestamp sendTime;

    private String msgMemo;

    private String sendStatus;

    private Timestamp createTime;

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

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
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
        sb.append(", sendType=").append(sendType);
        sb.append(", sendTime=").append(sendTime);
        sb.append(", msgMemo=").append(msgMemo);
        sb.append(", sendStatus=").append(sendStatus);
        sb.append(", createTime=").append(createTime);
        sb.append("]");
        return sb.toString();
    }
}
