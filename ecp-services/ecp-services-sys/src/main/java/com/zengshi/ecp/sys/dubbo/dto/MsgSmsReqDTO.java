package com.zengshi.ecp.sys.dubbo.dto;


import com.zengshi.ecp.server.front.dto.BaseInfo;

public class MsgSmsReqDTO extends BaseInfo {
    private String msgCode;

    private String smsTemplate;

    private String smsTime;

    private String remark;

    private static final long serialVersionUID = 1L;

    public String getMsgCode() {
        return msgCode;
    }

    public void setMsgCode(String msgCode) {
        this.msgCode = msgCode == null ? null : msgCode.trim();
    }

    public String getSmsTemplate() {
        return smsTemplate;
    }

    public void setSmsTemplate(String smsTemplate) {
        this.smsTemplate = smsTemplate == null ? null : smsTemplate.trim();
    }

    public String getSmsTime() {
        return smsTime;
    }

    public void setSmsTime(String smsTime) {
        this.smsTime = smsTime == null ? null : smsTime.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", msgCode=").append(msgCode);
        sb.append(", smsTemplate=").append(smsTemplate);
        sb.append(", smsTime=").append(smsTime);
        sb.append(", remark=").append(remark);
        sb.append("]");
        return sb.toString();
    }
}
