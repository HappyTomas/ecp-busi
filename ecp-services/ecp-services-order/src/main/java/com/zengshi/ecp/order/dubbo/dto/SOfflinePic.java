package com.zengshi.ecp.order.dubbo.dto;

import com.zengshi.ecp.server.front.dto.BaseInfo;

public class SOfflinePic extends BaseInfo {
    /** 
     * serialVersionUID:. 
     * @since JDK 1.6 
     */ 
    private static final long serialVersionUID = -688070738212977637L;
    /** 
     * vfsId:文件附件系统ID. 
     * @since JDK 1.6 
     */ 
    private String vfsId;
    /** 
     * picName:附件名称. 
     * @since JDK 1.6 
     */ 
    private String picName;
    public String getVfsId() {
        return vfsId;
    }
    public void setVfsId(String vfsId) {
        this.vfsId = vfsId;
    }
    public String getPicName() {
        return picName;
    }
    public void setPicName(String picName) {
        this.picName = picName;
    }
}

