package com.zengshi.ecp.order.dubbo.dto;

import java.io.Serializable;
import java.sql.Timestamp;

public class SOrderDetailsTrack implements Serializable{
    /** 
     * serialVersionUID:. 
     * @since JDK 1.6 
     */ 
    private static final long serialVersionUID = -1631662420275146203L;
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
     * createStaff:操作人. 
     * @since JDK 1.6 
     */ 
    private Long createStaff;
    
    
    /** 
     * createStaffName:操作人昵称. 
     * @since JDK 1.6 
     */ 
    private String createStaffName;
    
    public String getCreateStaffName() {
        return createStaffName;
    }
    public void setCreateStaffName(String createStaffName) {
        this.createStaffName = createStaffName;
    }
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
    public Long getCreateStaff() {
        return createStaff;
    }
    public void setCreateStaff(Long createStaff) {
        this.createStaff = createStaff;
    }
}

