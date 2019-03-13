package com.zengshi.ecp.unpf.service.busi.order.interfaces;


import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.unpf.dubbo.dto.RExportExcleResponse;
import com.zengshi.ecp.unpf.dubbo.dto.order.RUnpfQueryOrderReq;

public interface IUnpfOrdExportFileSV {
  
    
    /** 
     * queryOrder2Excle:导出单头. <br/> 
     */ 
    public RExportExcleResponse queryOrder2Excle(RUnpfQueryOrderReq rUnpfQueryOrderReq) throws BusinessException;

   
    /** 
     * queryOrderBarCode:导出明细. <br/> 
     */ 
    public RExportExcleResponse queryOrderBarCode(RUnpfQueryOrderReq rUnpfQueryOrderReq) throws BusinessException;

   
    
}

