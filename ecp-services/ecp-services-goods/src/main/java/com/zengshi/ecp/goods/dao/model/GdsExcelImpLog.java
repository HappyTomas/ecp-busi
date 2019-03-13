package com.zengshi.ecp.goods.dao.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class GdsExcelImpLog implements Serializable {
    private Long importId;

    private String importFile;

    private String importUuid;

    private String importStatus;

    private String importSrc;

    private Timestamp createTime;

    private Long createStaff;

    private Timestamp updateTime;

    private Long updateStaff;

    private static final long serialVersionUID = 1L;

    public Long getImportId() {
        return importId;
    }

    public void setImportId(Long importId) {
        this.importId = importId;
    }

    public String getImportFile() {
        return importFile;
    }

    public void setImportFile(String importFile) {
        this.importFile = importFile == null ? null : importFile.trim();
    }

    public String getImportUuid() {
        return importUuid;
    }

    public void setImportUuid(String importUuid) {
        this.importUuid = importUuid == null ? null : importUuid.trim();
    }

    public String getImportStatus() {
        return importStatus;
    }

    public void setImportStatus(String importStatus) {
        this.importStatus = importStatus == null ? null : importStatus.trim();
    }

    public String getImportSrc() {
        return importSrc;
    }

    public void setImportSrc(String importSrc) {
        this.importSrc = importSrc == null ? null : importSrc.trim();
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public Long getCreateStaff() {
        return createStaff;
    }

    public void setCreateStaff(Long createStaff) {
        this.createStaff = createStaff;
    }

    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }

    public Long getUpdateStaff() {
        return updateStaff;
    }

    public void setUpdateStaff(Long updateStaff) {
        this.updateStaff = updateStaff;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", importId=").append(importId);
        sb.append(", importFile=").append(importFile);
        sb.append(", importUuid=").append(importUuid);
        sb.append(", importStatus=").append(importStatus);
        sb.append(", importSrc=").append(importSrc);
        sb.append(", createTime=").append(createTime);
        sb.append(", createStaff=").append(createStaff);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", updateStaff=").append(updateStaff);
        sb.append("]");
        return sb.toString();
    }
}
