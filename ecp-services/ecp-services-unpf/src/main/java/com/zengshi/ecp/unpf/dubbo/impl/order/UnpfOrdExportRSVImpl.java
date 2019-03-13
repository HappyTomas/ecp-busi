package com.zengshi.ecp.unpf.dubbo.impl.order;

import javax.annotation.Resource;

import com.zengshi.ecp.order.dubbo.interfaces.IOrdFileImportRSV;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.unpf.dubbo.dto.RUnpfExportFileResp;
import com.zengshi.ecp.unpf.dubbo.dto.order.RUnpfQueryOrderReq;
import com.zengshi.ecp.unpf.dubbo.interfaces.order.IUnpfOrdExportRSV;
import com.zengshi.ecp.unpf.service.busi.order.interfaces.IUnpfOrdExportSV;

public class UnpfOrdExportRSVImpl implements IUnpfOrdExportRSV{
	
    @Resource
	private IUnpfOrdExportSV unpfOrdExportSV;
    
    @Resource
    private IOrdFileImportRSV ordFileImportRSV;

	@Override
	public RUnpfExportFileResp creatQueryOrderFileKey(RUnpfQueryOrderReq rUnpfQueryOrderReq) throws BusinessException {
		 return unpfOrdExportSV.creatFileKey(rUnpfQueryOrderReq);
	}

}
