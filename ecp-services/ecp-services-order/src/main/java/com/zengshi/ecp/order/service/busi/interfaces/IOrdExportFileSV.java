package com.zengshi.ecp.order.service.busi.interfaces;

import com.zengshi.ecp.order.dubbo.dto.RExportExcleResponse;
import com.zengshi.ecp.order.dubbo.dto.RGoodSaleRequest;
import com.zengshi.ecp.order.dubbo.dto.ROrderDetailsResponse;
import com.zengshi.ecp.order.dubbo.dto.RQueryOrderRequest;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;

public interface IOrdExportFileSV {
    
    /** 
     * exportOrder2Print:批量打印. <br/> 
     * @author cbl 
     * @param rQueryOrderRequest
     * @return
     * @throws BusinessException 
     * @since JDK 1.6 
     */ 
    public PageResponseDTO<ROrderDetailsResponse> exportOrder2Print(RQueryOrderRequest rQueryOrderRequest) throws BusinessException;
    
    
    /** 
     * queryOrder2Excle:导出单头. <br/> 
     * @author cbl 
     * @param rQueryOrderRequest
     * @return
     * @throws BusinessException 
     * @since JDK 1.6 
     */ 
    public RExportExcleResponse queryOrder2Excle(RQueryOrderRequest rQueryOrderRequest) throws BusinessException;
    public RExportExcleResponse queryOrder2ExcleNew(RQueryOrderRequest rQueryOrderRequest) throws BusinessException;

   
    /** 
     * queryOrderBarCode:导出明细. <br/> 
     * @author cbl 
     * @param queryOrderRequest
     * @return
     * @throws BusinessException 
     * @since JDK 1.6 
     */ 
    public RExportExcleResponse queryOrderBarCode(RQueryOrderRequest queryOrderRequest) throws BusinessException;
    public RExportExcleResponse queryOrderBarCodeNew(RQueryOrderRequest queryOrderRequest) throws BusinessException;

    
    /**
    *
    * queryGoodSaleInfo:“商品销售明细”列表. <br/>
    * TODO(这里描述这个方法适用条件 – 可选).<br/>
    * TODO(这里描述这个方法的执行流程 – 可选).<br/>
    * TODO(这里描述这个方法的使用方法 – 可选).<br/>
    * TODO(这里描述这个方法的注意事项 – 可选).<br/>
    *
    * @author wangxq
    * @param rGoodSaleRequest
    * @since JDK 1.6
    */
   public RExportExcleResponse exportGoodSaleExcel(RGoodSaleRequest rGoodSaleRequest);
    
}

