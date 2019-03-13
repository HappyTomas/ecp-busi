package com.zengshi.ecp.goods.dao.model;

import java.io.Serializable;

public class StockCompanyRepIdx implements Serializable {
    private Long companyId;

    private String repName;

    private String repType;

    private String status;

    private Long repCode;

    private static final long serialVersionUID = 1L;

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public String getRepName() {
        return repName;
    }

    public void setRepName(String repName) {
        this.repName = repName == null ? null : repName.trim();
    }

    public String getRepType() {
        return repType;
    }

    public void setRepType(String repType) {
        this.repType = repType == null ? null : repType.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public Long getRepCode() {
        return repCode;
    }

    public void setRepCode(Long repCode) {
        this.repCode = repCode;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", companyId=").append(companyId);
        sb.append(", repName=").append(repName);
        sb.append(", repType=").append(repType);
        sb.append(", status=").append(status);
        sb.append(", repCode=").append(repCode);
        sb.append("]");
        return sb.toString();
    }
}
