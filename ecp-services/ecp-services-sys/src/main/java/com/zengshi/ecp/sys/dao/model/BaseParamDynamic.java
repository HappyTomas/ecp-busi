package com.zengshi.ecp.sys.dao.model;

import java.io.Serializable;

public class BaseParamDynamic implements Serializable {
    private String paramLinkKey;

    private String paramName;

    private String sqlLoad;

    private String dbName;

    private String tableName;

    private String loadType;

    private String status;

    private String spDesc;

    private static final long serialVersionUID = 1L;

    public String getParamLinkKey() {
        return paramLinkKey;
    }

    public void setParamLinkKey(String paramLinkKey) {
        this.paramLinkKey = paramLinkKey == null ? null : paramLinkKey.trim();
    }

    public String getParamName() {
        return paramName;
    }

    public void setParamName(String paramName) {
        this.paramName = paramName == null ? null : paramName.trim();
    }

    public String getSqlLoad() {
        return sqlLoad;
    }

    public void setSqlLoad(String sqlLoad) {
        this.sqlLoad = sqlLoad == null ? null : sqlLoad.trim();
    }

    public String getDbName() {
        return dbName;
    }

    public void setDbName(String dbName) {
        this.dbName = dbName == null ? null : dbName.trim();
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName == null ? null : tableName.trim();
    }

    public String getLoadType() {
        return loadType;
    }

    public void setLoadType(String loadType) {
        this.loadType = loadType == null ? null : loadType.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getSpDesc() {
        return spDesc;
    }

    public void setSpDesc(String spDesc) {
        this.spDesc = spDesc == null ? null : spDesc.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", paramLinkKey=").append(paramLinkKey);
        sb.append(", paramName=").append(paramName);
        sb.append(", sqlLoad=").append(sqlLoad);
        sb.append(", dbName=").append(dbName);
        sb.append(", tableName=").append(tableName);
        sb.append(", loadType=").append(loadType);
        sb.append(", status=").append(status);
        sb.append(", spDesc=").append(spDesc);
        sb.append("]");
        return sb.toString();
    }
}
