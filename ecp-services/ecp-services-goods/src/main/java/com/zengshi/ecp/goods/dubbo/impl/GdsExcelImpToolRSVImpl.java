package com.zengshi.ecp.goods.dubbo.impl;

import javax.annotation.Resource;

import com.zengshi.ecp.goods.dubbo.dto.excelImp.GdsExcelImpLogReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.excelImp.GdsExcelImportResultDTO;
import com.zengshi.ecp.goods.dubbo.interfaces.IGdsExcelImpToolRSV;
import com.zengshi.ecp.goods.service.busi.interfaces.excelImp.IGdsExcelImpToolSV;
import com.zengshi.ecp.server.front.exception.BusinessException;

public class GdsExcelImpToolRSVImpl implements IGdsExcelImpToolRSV{
@Resource
	private IGdsExcelImpToolSV gdsExcelImpToolSV;
	@Override
	public GdsExcelImportResultDTO importTempGdsExcelData(GdsExcelImpLogReqDTO excelImpLogReqDTO) throws BusinessException {
	
		try {
			return gdsExcelImpToolSV.importTempGdsExcelData(excelImpLogReqDTO);
		} catch (BusinessException e) {
			throw e;
		}catch (Exception e) {
			// TODO: handle exception
			throw new BusinessException();
		}
	}

}

