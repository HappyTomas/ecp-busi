package com.zengshi.ecp.order.dubbo.dto;

import java.sql.Timestamp;

import com.zengshi.ecp.server.front.dto.BaseResponseDTO;

public class RShowImportResponse extends BaseResponseDTO {

    /** 
     * serialVersionUID: 
     * @since JDK 1.6 
     */ 
    private static final long serialVersionUID = -6173039288211484492L;
    
    /** 
     * importNo:批次号. 
     * @since JDK 1.6 
     */ 
    private Long importNo;
    
    /** 
     * fileName:文件名. 
     * @since JDK 1.6 
     */ 
    private String fileName;
    /** 
     * importTime:导入时间. 
     * @since JDK 1.6 
     */ 
    private Timestamp importTime;
    /** 
     * status:文件状态. 
     * @since JDK 1.6 
     */ 
    private String status;
    public Long getImportNo() {
        return importNo;
    }
    public void setImportNo(Long importNo) {
        this.importNo = importNo;
    }
    public String getFileName() {
        return fileName;
    }
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
    public Timestamp getImportTime() {
        return importTime;
    }
    public void setImportTime(Timestamp importTime) {
        this.importTime = importTime;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    
}

