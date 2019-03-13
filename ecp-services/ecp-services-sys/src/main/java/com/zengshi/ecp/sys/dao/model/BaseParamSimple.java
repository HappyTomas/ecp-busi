package com.zengshi.ecp.sys.dao.model;

import java.io.Serializable;
import java.math.BigDecimal;

public class BaseParamSimple extends BaseParamSimpleKey implements Serializable {
    private String paramName;
    
    private String spValue;

    private BigDecimal spOrder;
    
    private String spDesc;

    private String tableName;
    
    private String fieldName;

    private static final long serialVersionUID = 1L;

    public String getParamName() {
        return paramName;
    }

    public void setParamName(String paramName) {
        this.paramName = paramName == null ? null : paramName.trim();
    }

    public String getSpValue() {
        return spValue;
    }

    public void setSpValue(String spValue) {
        this.spValue = spValue == null ? null : spValue.trim();
    }

    public BigDecimal getSpOrder() {
        return spOrder;
    }

    public void setSpOrder(BigDecimal spOrder) {
        this.spOrder = spOrder;
    }

    public String getSpDesc() {
        return spDesc;
    }

    public void setSpDesc(String spDesc) {
        this.spDesc = spDesc == null ? null : spDesc.trim();
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName == null ? null : tableName.trim();
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName == null ? null : fieldName.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", paramName=").append(paramName);
        sb.append(", spValue=").append(spValue);
        sb.append(", spOrder=").append(spOrder);
        sb.append(", spDesc=").append(spDesc);
        sb.append(", tableName=").append(tableName);
        sb.append(", fieldName=").append(fieldName);
        sb.append("]");
        return sb.toString();
    }
}
