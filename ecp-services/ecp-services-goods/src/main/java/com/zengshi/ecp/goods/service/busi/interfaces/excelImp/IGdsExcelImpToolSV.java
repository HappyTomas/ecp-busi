package com.zengshi.ecp.goods.service.busi.interfaces.excelImp;

import com.zengshi.ecp.goods.dubbo.dto.excelImp.ExcelImportGdsModelDTO;
import com.zengshi.ecp.goods.dubbo.dto.excelImp.GdsExcelImpLogReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.excelImp.GdsExcelImpReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.excelImp.GdsExcelImportResultDTO;

public interface IGdsExcelImpToolSV {

	public GdsExcelImportResultDTO importTempGdsExcelData(GdsExcelImpLogReqDTO excelImpTempReqDTO)throws Exception;
	

	public GdsExcelImpReqDTO validateGdsExcelImpData(ExcelImportGdsModelDTO excelImportGdsModelDTO,String optType) throws Exception;
}

