package com.zengshi.ecp.busi.order.pay.vo;

import java.io.Serializable;

public class SOfflinePicVO implements Serializable {


    
    /** 
     * serialVersionUID:TODO(用一句话描述这个变量表示什么). 
     * @since JDK 1.6 
     */ 
    private static final long serialVersionUID = 7003522325936512237L;
    String  vfsId	;
    String  picName	;
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

