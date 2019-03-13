package com.zengshi.ecp.sys.dubbo.dto;


import com.zengshi.ecp.server.front.dto.BaseResponseDTO;

public class MsgSiteResDTO extends BaseResponseDTO {
    private String msgCode;

    private String msgSiteTemplate;

    private String remark;

    private static final long serialVersionUID = 1L;

    public String getMsgCode() {
        return msgCode;
    }

    public void setMsgCode(String msgCode) {
        this.msgCode = msgCode == null ? null : msgCode.trim();
    }

    public String getMsgSiteTemplate() {
        return msgSiteTemplate;
    }

    public void setMsgSiteTemplate(String msgSiteTemplate) {
        this.msgSiteTemplate = msgSiteTemplate == null ? null : msgSiteTemplate.trim();
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
        sb.append(", msgSiteTemplate=").append(msgSiteTemplate);
        sb.append(", remark=").append(remark);
        sb.append("]");
        return sb.toString();
    }
}
