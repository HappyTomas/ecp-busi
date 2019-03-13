package com.zengshi.ecp.sys.dubbo.dto;


import com.zengshi.ecp.server.front.dto.BaseResponseDTO;

public class MsgMailResDTO extends BaseResponseDTO {
    private String msgCode;

    private String mailTitleTemplate;

    private String mailBodyTemplate;

    private String remark;

    private static final long serialVersionUID = 1L;

    public String getMsgCode() {
        return msgCode;
    }

    public void setMsgCode(String msgCode) {
        this.msgCode = msgCode == null ? null : msgCode.trim();
    }

    public String getMailTitleTemplate() {
        return mailTitleTemplate;
    }

    public void setMailTitleTemplate(String mailTitleTemplate) {
        this.mailTitleTemplate = mailTitleTemplate == null ? null : mailTitleTemplate.trim();
    }

    public String getMailBodyTemplate() {
        return mailBodyTemplate;
    }

    public void setMailBodyTemplate(String mailBodyTemplate) {
        this.mailBodyTemplate = mailBodyTemplate == null ? null : mailBodyTemplate.trim();
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
        sb.append(", mailTitleTemplate=").append(mailTitleTemplate);
        sb.append(", mailBodyTemplate=").append(mailBodyTemplate);
        sb.append(", remark=").append(remark);
        sb.append("]");
        return sb.toString();
    }
}
