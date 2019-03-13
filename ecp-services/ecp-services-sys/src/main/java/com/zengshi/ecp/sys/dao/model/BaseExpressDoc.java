package com.zengshi.ecp.sys.dao.model;

import java.io.Serializable;
import java.math.BigDecimal;

public class BaseExpressDoc implements Serializable {
    private Long id;

    private Long expressId;

    private String templateName;

    private String docPic;

    private String printCfg;

    private String status;

    private BigDecimal sortNo;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getExpressId() {
        return expressId;
    }

    public void setExpressId(Long expressId) {
        this.expressId = expressId;
    }

    public String getTemplateName() {
        return templateName;
    }

    public void setTemplateName(String templateName) {
        this.templateName = templateName == null ? null : templateName.trim();
    }

    public String getDocPic() {
        return docPic;
    }

    public void setDocPic(String docPic) {
        this.docPic = docPic == null ? null : docPic.trim();
    }

    public String getPrintCfg() {
        return printCfg;
    }

    public void setPrintCfg(String printCfg) {
        this.printCfg = printCfg == null ? null : printCfg.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public BigDecimal getSortNo() {
        return sortNo;
    }

    public void setSortNo(BigDecimal sortNo) {
        this.sortNo = sortNo;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", expressId=").append(expressId);
        sb.append(", templateName=").append(templateName);
        sb.append(", docPic=").append(docPic);
        sb.append(", printCfg=").append(printCfg);
        sb.append(", status=").append(status);
        sb.append(", sortNo=").append(sortNo);
        sb.append("]");
        return sb.toString();
    }
}
