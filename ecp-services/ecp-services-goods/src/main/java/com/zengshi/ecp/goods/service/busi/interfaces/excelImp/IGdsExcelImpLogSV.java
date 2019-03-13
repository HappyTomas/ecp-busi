package com.zengshi.ecp.goods.service.busi.interfaces.excelImp;

import com.zengshi.ecp.goods.dubbo.dto.excelImp.GdsExcelImpLogReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.excelImp.GdsExcelImpLogRespDTO;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;

public interface IGdsExcelImpLogSV {

	public Long addGdsExcelImpLog(GdsExcelImpLogReqDTO excelImpLogReqDTO)throws Exception;
	
	public GdsExcelImpLogRespDTO queryGdsExcelImpLogRespByImportInfo(GdsExcelImpLogReqDTO dto)throws Exception;
	
	public void updateGdsExcelImpLogWhenTempDone(GdsExcelImpLogReqDTO dto)throws Exception;

    public PageResponseDTO<GdsExcelImpLogRespDTO> queryImpLogByPage(GdsExcelImpLogReqDTO dto)throws Exception;
    
    public void delImportLogById(GdsExcelImpLogReqDTO excelImpLogReqDTO)throws Exception;
}

