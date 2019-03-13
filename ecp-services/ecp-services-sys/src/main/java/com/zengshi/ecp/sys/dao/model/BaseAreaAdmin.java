package com.zengshi.ecp.sys.dao.model;

import java.io.Serializable;
import java.math.BigDecimal;

public class BaseAreaAdmin implements Serializable {
    private String areaCode;

    private String areaName;

    private String parentAreaCode;

    private String areaCodeShort;

    private String areaLevel;

    private BigDecimal areaOrder;

    private String status;

    private String centerFlag;

    private static final long serialVersionUID = 1L;

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode == null ? null : areaCode.trim();
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName == null ? null : areaName.trim();
    }

    public String getParentAreaCode() {
        return parentAreaCode;
    }

    public void setParentAreaCode(String parentAreaCode) {
        this.parentAreaCode = parentAreaCode == null ? null : parentAreaCode.trim();
    }

    public String getAreaCodeShort() {
        return areaCodeShort;
    }

    public void setAreaCodeShort(String areaCodeShort) {
        this.areaCodeShort = areaCodeShort == null ? null : areaCodeShort.trim();
    }

    public String getAreaLevel() {
        return areaLevel;
    }

    public void setAreaLevel(String areaLevel) {
        this.areaLevel = areaLevel == null ? null : areaLevel.trim();
    }

    public BigDecimal getAreaOrder() {
        return areaOrder;
    }

    public void setAreaOrder(BigDecimal areaOrder) {
        this.areaOrder = areaOrder;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getCenterFlag() {
        return centerFlag;
    }

    public void setCenterFlag(String centerFlag) {
        this.centerFlag = centerFlag == null ? null : centerFlag.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", areaCode=").append(areaCode);
        sb.append(", areaName=").append(areaName);
        sb.append(", parentAreaCode=").append(parentAreaCode);
        sb.append(", areaCodeShort=").append(areaCodeShort);
        sb.append(", areaLevel=").append(areaLevel);
        sb.append(", areaOrder=").append(areaOrder);
        sb.append(", status=").append(status);
        sb.append(", centerFlag=").append(centerFlag);
        sb.append("]");
        return sb.toString();
    }
}
