package com.zengshi.ecp.goods.dubbo.impl;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;

import com.zengshi.ecp.goods.dubbo.dto.excelImp.GdsExcelImpReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.excelImp.GdsExcelImpRespDTO;
import com.zengshi.ecp.goods.dubbo.interfaces.IGdsExcelImpRSV;
import com.zengshi.ecp.goods.service.busi.interfaces.excelImp.IGdsExcelImpSV;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.paas.utils.LogUtil;

public class GdsExcelImpRSVImpl implements IGdsExcelImpRSV {
	@Autowired(required = false)
	private IGdsExcelImpSV gdsExcelImpSV;
	private static final String MODULE = GdsExcelImpRSVImpl.class.getName();

	@Override
	public PageResponseDTO<GdsExcelImpRespDTO> queryGdsExcelImpByImportId(GdsExcelImpReqDTO excelImpReqDTO)
			throws Exception {

		try {
			LogUtil.debug(MODULE, "请求参数为" + excelImpReqDTO.toString());
			return gdsExcelImpSV.queryGdsExcelImpByImportId(excelImpReqDTO);
		} catch (BusinessException e) {
			throw e;
		} catch (Exception e) {
			// TODO: handle exception
			throw new BusinessException();
		}
	}

}
