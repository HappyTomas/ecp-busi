package com.zengshi.ecp.prom.dubbo.dto;

import java.util.Date;


public class PromImportFileReqDTO extends CommonFileDTO {

    private static final long serialVersionUID = 1L;
    
    private String fileId;

    private String fileName;

    private String importType;

    private String importDesc;

    private Date createTime;

    private Long createStaff;

    public String getFileId() {
        return fileId;
    }

    public void setFileId(String fileId) {
        this.fileId = fileId;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getImportType() {
        return importType;
    }

    public void setImportType(String importType) {
        this.importType = importType;
    }

    public String getImportDesc() {
        return importDesc;
    }

    public void setImportDesc(String importDesc) {
        this.importDesc = importDesc;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Long getCreateStaff() {
        return createStaff;
    }

    public void setCreateStaff(Long createStaff) {
        this.createStaff = createStaff;
    }


}