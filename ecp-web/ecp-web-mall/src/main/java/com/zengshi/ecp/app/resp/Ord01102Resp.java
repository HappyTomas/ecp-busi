package com.zengshi.ecp.app.resp;

import java.sql.Timestamp;

public class Ord01102Resp {
   
    /** 
     * createTime:处理时间. 
     * @since JDK 1.6 
     */ 
    private Timestamp createTime;
    /** 
     * nodeDesc:处理信息. 
     * @since JDK 1.6 
     */ 
    private String nodeDesc;
    /** 
     * createStaffName:操作人昵称. 
     * @since JDK 1.6 
     */ 
    private String createStaffName;
    public Timestamp getCreateTime() {
        return createTime;
    }
    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }
    public String getNodeDesc() {
        return nodeDesc;
    }
    public void setNodeDesc(String nodeDesc) {
        this.nodeDesc = nodeDesc;
    }
    public String getCreateStaffName() {
        return createStaffName;
    }
    public void setCreateStaffName(String createStaffName) {
        this.createStaffName = createStaffName;
    }
    
}

