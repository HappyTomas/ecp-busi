package com.zengshi.ecp.order.service.busi.interfaces;

import com.zengshi.ecp.order.dubbo.dto.RFileImportRequest;
import com.zengshi.ecp.order.dubbo.dto.REntityChgImportRequest;

public interface IOrdDealFileSV {
    /** 
     * importExcel:读取excel文件内容插入数据库中,通过文件新增实体编号. <br/> 
     * @author cbl 
     * @param sDealFileInfo 
     * @since JDK 1.6 
     */ 
    public void importEntityAddExcel(RFileImportRequest rEntityAddImport);
    
    /** 
     * importEntityChgExcel:读取excel文件内容插入数据库中，通过文件修改实体编号. <br/> 
     * @author cbl 
     * @param sDealFileInfo 
     * @since JDK 1.6 
     */ 
    public void importEntityChgExcel(REntityChgImportRequest rEntityChgImportRequest);
}

