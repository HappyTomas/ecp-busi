package com.zengshi.ecp.unpf.dubbo.interfaces.order;


import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.unpf.dubbo.dto.RUnpfExportFileResp;
import com.zengshi.ecp.unpf.dubbo.dto.order.RUnpfQueryOrderReq;
/**
 * 异步下载
 * @author jiangmr
 *
 */
public interface IUnpfOrdExportRSV {
	
    public RUnpfExportFileResp creatQueryOrderFileKey(RUnpfQueryOrderReq rUnpfQueryOrderReq) throws BusinessException;
    
}
