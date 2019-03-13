package com.zengshi.ecp.order.dubbo.impl;

import javax.annotation.Resource;

import com.zengshi.ecp.order.dubbo.dto.OrdFileImportDTO;
import com.zengshi.ecp.order.dubbo.dto.RExportFileReq;
import com.zengshi.ecp.order.dubbo.dto.RExportFileResp;
import com.zengshi.ecp.order.dubbo.interfaces.IOrdFileImportRSV;
import com.zengshi.ecp.order.service.busi.interfaces.IOrdFileImportSV;
import com.zengshi.ecp.server.front.exception.BusinessException;

public class OrdFileImportRSVImpl implements IOrdFileImportRSV{

	@Resource
	private IOrdFileImportSV ordFileImportSV;
	
	@Override
	public OrdFileImportDTO saveOrdFileImport(OrdFileImportDTO ordFileImportDTO) {
		return ordFileImportSV.insertOrdFileImport(ordFileImportDTO);
	}

	@Override
	public void updateById(OrdFileImportDTO ordFileImportDTO) throws BusinessException {
		ordFileImportSV.updateOrdFileImportById(ordFileImportDTO);
		
	}

	@Override
	public RExportFileResp queryById(RExportFileReq rExportFileReq) throws BusinessException {
		return ordFileImportSV.queryById(rExportFileReq);
	}


}
