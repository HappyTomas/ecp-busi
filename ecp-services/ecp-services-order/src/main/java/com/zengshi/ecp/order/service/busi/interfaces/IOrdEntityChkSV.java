package com.zengshi.ecp.order.service.busi.interfaces;

import com.zengshi.ecp.order.dubbo.dto.SBaseAndImportInfo;

/**
 * Title: ECP <br>
 * Project Name:ecp-services-order <br>
 * Description: <br>
 * Date:2015年8月21日下午2:26:59  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author cbl
 * @version  
 * @since JDK 1.6 
 */  
public interface IOrdEntityChkSV {
    
    /** 
     * entiytCodeChk:实体编号新增验证. <br/> 
     * @author cbl 
     * @return 
     * @since JDK 1.6 
     */ 
    public Boolean saveEntiytCodeAddChk(SBaseAndImportInfo sBaseAndImportInfo);
    
    
    /** 
     * entiytCodeChgChk:实体编号变更验证. <br/> 
     * @author cbl 
     * @param sBaseAndImportInfo
     * @return 
     * @since JDK 1.6 
     */ 
    public Boolean saveEntiytCodeChgChk(SBaseAndImportInfo sBaseAndImportInfo);
}

