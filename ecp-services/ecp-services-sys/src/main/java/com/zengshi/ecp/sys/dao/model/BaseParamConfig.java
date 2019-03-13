package com.zengshi.ecp.sys.dao.model;

import java.io.Serializable;

public class BaseParamConfig implements Serializable {
    private String paramKey;

    private String paramName;
    
    private String paramLinkTable;

    private String paramLinkKey;
   
    private String status;

    private String paramDesc;

    private String paramType;

    private String usedTable;

    private String usedColumn;

    private static final long serialVersionUID = 1L;

    public String getParamKey() {
        return paramKey;
    }

    public void setParamKey(String paramKey) {
        this.paramKey = paramKey == null ? null : paramKey.trim();
    }

    public String getParamName() {
        return paramName;
    }

    public void setParamName(String paramName) {
        this.paramName = paramName == null ? null : paramName.trim();
    }

    public String getParamLinkTable() {
        return paramLinkTable;
    }

    public void setParamLinkTable(String paramLinkTable) {
        this.paramLinkTable = paramLinkTable == null ? null : paramLinkTable.trim();
    }

    public String getParamLinkKey() {
        return paramLinkKey;
    }

    public void setParamLinkKey(String paramLinkKey) {
        this.paramLinkKey = paramLinkKey == null ? null : paramLinkKey.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getParamDesc() {
        return paramDesc;
    }

    public void setParamDesc(String paramDesc) {
        this.paramDesc = paramDesc == null ? null : paramDesc.trim();
    }

    public String getParamType() {
        return paramType;
    }

    public void setParamType(String paramType) {
        this.paramType = paramType == null ? null : paramType.trim();
    }

    public String getUsedTable() {
        return usedTable;
    }

    public void setUsedTable(String usedTable) {
        this.usedTable = usedTable == null ? null : usedTable.trim();
    }

    public String getUsedColumn() {
        return usedColumn;
    }

    public void setUsedColumn(String usedColumn) {
        this.usedColumn = usedColumn == null ? null : usedColumn.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", paramKey=").append(paramKey);
        sb.append(", paramName=").append(paramName);
        sb.append(", paramLinkTable=").append(paramLinkTable);
        sb.append(", paramLinkKey=").append(paramLinkKey);
        sb.append(", status=").append(status);
        sb.append(", paramDesc=").append(paramDesc);
        sb.append(", paramType=").append(paramType);
        sb.append(", usedTable=").append(usedTable);
        sb.append(", usedColumn=").append(usedColumn);
        sb.append("]");
        return sb.toString();
    }
}
