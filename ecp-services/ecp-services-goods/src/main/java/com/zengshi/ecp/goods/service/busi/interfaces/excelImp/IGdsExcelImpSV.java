package com.zengshi.ecp.goods.service.busi.interfaces.excelImp;

import com.zengshi.ecp.goods.dubbo.dto.excelImp.GdsExcelImpReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.excelImp.GdsExcelImpRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfo.GdsInfoAddReqDTO;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;

public interface IGdsExcelImpSV {

	public void addGdsExcelImp(GdsExcelImpReqDTO excelImpReqDTO) throws Exception;

	public PageResponseDTO<GdsExcelImpRespDTO> queryGdsExcelImpByImportId(GdsExcelImpReqDTO excelImpReqDTO)
			throws Exception;

	public GdsInfoAddReqDTO transformAddGdsExcelImp(GdsExcelImpReqDTO excelImpReqDTO) throws Exception;

	public GdsInfoAddReqDTO transformEditGdsExcelImp(GdsExcelImpReqDTO excelImpReqDTO)throws Exception ;

}
