package com.zengshi.ecp.order.facade.interfaces.eventual;

import com.zengshi.ecp.order.dubbo.dto.REntityChgImportRequest;
import com.zengshi.ecp.order.dubbo.dto.REntityCodeChgRequest;

public interface IOrdEntityMainSV{

    /** 
     * updateEntityCodeFromWeb:通过页面单个变更实体编号. <br/> 
     * @author cbl 
     * @param rEntityCodeChgRequest 
     * @since JDK 1.6 
     */ 
    public void updateEntityCodeFromWeb(REntityCodeChgRequest rEntityCodeChgRequest);
    
    /** 
     * updateEntityCodeFromFile:通过文件批量修改实体编号. <br/> 
     * @author cbl 
     * @param rEntityChgImportRequest 
     * @since JDK 1.6 
     */ 
    public void updateEntityCodeFromFile(REntityChgImportRequest rEntityChgImportRequest);
}

