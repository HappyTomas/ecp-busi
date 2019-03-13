package com.zengshi.ecp.unpf.dubbo.dto;

import com.zengshi.ecp.server.front.dto.BaseResponseDTO;

public class RExportExcleResponse extends BaseResponseDTO {

    /** 
     * serialVersionUID:. 
     * @since JDK 1.6 
     */ 
    private static final long serialVersionUID = -8079966370431391488L;
    
    /** 
     * fileId:保存的文件ID. 
     * @since JDK 1.6 
     */ 
    private String fileId;

    public String getFileId() {
        return fileId;
    }

    public void setFileId(String fileId) {
        this.fileId = fileId;
    }
    
    

}

