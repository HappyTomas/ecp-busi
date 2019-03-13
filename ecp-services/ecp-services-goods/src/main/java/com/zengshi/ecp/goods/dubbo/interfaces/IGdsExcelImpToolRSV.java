package com.zengshi.ecp.goods.dubbo.interfaces;

import com.zengshi.ecp.goods.dubbo.dto.excelImp.GdsExcelImpLogReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.excelImp.GdsExcelImportResultDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;

public interface IGdsExcelImpToolRSV {

	
	public GdsExcelImportResultDTO importTempGdsExcelData(GdsExcelImpLogReqDTO excelImpLogReqDTO) throws BusinessException; 
}

