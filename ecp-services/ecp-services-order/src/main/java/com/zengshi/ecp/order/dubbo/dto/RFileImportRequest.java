package com.zengshi.ecp.order.dubbo.dto;

import com.zengshi.ecp.server.front.dto.BaseInfo;

/**
 * Title: ECP <br>
 * Project Name:ecp-services-order <br>
 * Description: <br>
 * Date:2015年8月21日上午11:21:39  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author cbl
 * @version  
 * @since JDK 1.6 
 */  
public class RFileImportRequest extends BaseInfo {

    /** 
     * serialVersionUID:TODO(用一句话描述这个变量表示什么). 
     * @since JDK 1.6 
     */ 
    private static final long serialVersionUID = 7497219942317934106L;
    
    /** 
     * shopId:卖家ID. 
     * @since JDK 1.6 
     */ 
    private Long shopId;
    
    /** 
     * orderId:订单号. 
     * @since JDK 1.6 
     */ 
    private String orderId;
    
    /** 
     * moduleName:模板名称. 
     * @since JDK 1.6 
     */ 
    private String moduleName;
    
    /** 
     * fileId:批导文件Id. 
     * @since JDK 1.6 
     */ 
    private String fileId;
    
    /** 
     * fileName:批导文件名. 
     * @since JDK 1.6 
     */ 
    private String fileName;
    
    /** 
     * importNo:批次号. 
     * @since JDK 1.6 
     */ 
    private Long importNo;
    
    /** 
     * staffId: 买家id
     * @since JDK 1.6 
     */     
    private Long staffId;
    
    public Long getImportNo() {
        return importNo;
    }

    public void setImportNo(Long importNo) {
        this.importNo = importNo;
    }

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

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

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public Long getStaffId() {
        return staffId;
    }

    public void setStaffId(Long staffId) {
        this.staffId = staffId;
    }

}

