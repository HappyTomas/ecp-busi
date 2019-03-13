package com.zengshi.ecp.goods.dubbo.interfaces;

import com.zengshi.ecp.goods.dubbo.dto.excelImp.GdsExcelImpReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.excelImp.GdsExcelImpRespDTO;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;

public interface IGdsExcelImpRSV {

	
	public PageResponseDTO<GdsExcelImpRespDTO> queryGdsExcelImpByImportId(GdsExcelImpReqDTO excelImpReqDTO)throws Exception;

}

