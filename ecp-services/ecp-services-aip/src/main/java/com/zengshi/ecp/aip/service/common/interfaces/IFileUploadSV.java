package com.zengshi.ecp.aip.service.common.interfaces;

import java.util.List;

import com.zengshi.ecp.aip.dao.model.FileUploadLog;

public interface IFileUploadSV {
    
    public String insertUploadLog(String remoteUri, String localUri);

    public List<FileUploadLog> selectUploadingLog(String remoteUri);

    public void deleteUploadedLogById(String logid);
    
    public void updateUploadLog(String logId, String flag, String remark);
}
