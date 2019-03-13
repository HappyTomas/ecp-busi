package com.zengshi.ecp.prom.dubbo.dto;

import com.zengshi.ecp.server.front.dto.BaseInfo;

/**
 * Title: AI-ECP <br>
 * Description: <br>
 * Date: 2015-12-05 <br>
 * Copyright (c) 2015 AI <br>
 * 
 * @author huangjx
 */
public class CommonFileDTO extends BaseInfo {

    private static final long serialVersionUID = 1L;

    //文件id
    private String fileId;
    //文件名称
    private String fileName;
    //文件大小
    private String size;
    
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
    public String getSize() {
        return size;
    }
    public void setSize(String size) {
        this.size = size;
    }
     
}
