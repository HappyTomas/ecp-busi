package com.zengshi.ecp.order.dubbo.dto;

import com.zengshi.ecp.server.front.dto.BaseInfo;

public class REntityChgImportRequest extends BaseInfo {

    /** 
     * serialVersionUID:. 
     * @since JDK 1.6 
     */ 
    private static final long serialVersionUID = 8805591855628248691L;
    
    /** 
     * shopId:卖家ID. 
     * @since JDK 1.6 
     */ 
    private Long shopId;
    
    /** 
     * moduleName:模板名称. 
     * @since JDK 1.6 
     */ 
    private String moduleName;
    
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

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public Long getImportNo() {
        return importNo;
    }

    public void setImportNo(Long importNo) {
        this.importNo = importNo;
    }
    
    

}

