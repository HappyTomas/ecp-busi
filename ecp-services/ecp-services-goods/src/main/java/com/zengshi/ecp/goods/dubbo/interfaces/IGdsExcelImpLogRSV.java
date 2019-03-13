package com.zengshi.ecp.goods.dubbo.interfaces;

import com.zengshi.ecp.goods.dubbo.dto.excelImp.GdsExcelImpLogReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.excelImp.GdsExcelImpLogRespDTO;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;

public interface IGdsExcelImpLogRSV {

	
	public Long addGdsExcelImpLog(GdsExcelImpLogReqDTO excelImpLogReqDTO) throws BusinessException ;

	public PageResponseDTO<GdsExcelImpLogRespDTO> queryImpLogByPage(GdsExcelImpLogReqDTO dto) throws BusinessException; 
	
    public void delImportLogById(GdsExcelImpLogReqDTO excelImpLogReqDTO)throws BusinessException;

}

