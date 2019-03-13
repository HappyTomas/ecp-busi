package com.zengshi.ecp.sys.dao.model;

import java.io.Serializable;
import java.math.BigDecimal;

public class BaseExpress implements Serializable {
    private Long id;

    private String expressFullName;

    private String expressName;

    private String expressWebsite;

    private String status;

    private BigDecimal sortNo;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getExpressFullName() {
        return expressFullName;
    }

    public void setExpressFullName(String expressFullName) {
        this.expressFullName = expressFullName == null ? null : expressFullName.trim();
    }

    public String getExpressName() {
        return expressName;
    }

    public void setExpressName(String expressName) {
        this.expressName = expressName == null ? null : expressName.trim();
    }

    public String getExpressWebsite() {
        return expressWebsite;
    }

    public void setExpressWebsite(String expressWebsite) {
        this.expressWebsite = expressWebsite == null ? null : expressWebsite.trim();
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
        sb.append(", expressFullName=").append(expressFullName);
        sb.append(", expressName=").append(expressName);
        sb.append(", expressWebsite=").append(expressWebsite);
        sb.append(", status=").append(status);
        sb.append(", sortNo=").append(sortNo);
        sb.append("]");
        return sb.toString();
    }
}
