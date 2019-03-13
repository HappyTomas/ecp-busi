package com.zengshi.ecp.goods.dubbo.impl;

import javax.annotation.Resource;

import com.zengshi.ecp.goods.dubbo.dto.excelImp.GdsExcelImpLogReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.excelImp.GdsExcelImpLogRespDTO;
import com.zengshi.ecp.goods.dubbo.interfaces.IGdsExcelImpLogRSV;
import com.zengshi.ecp.goods.service.busi.interfaces.excelImp.IGdsExcelImpLogSV;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.paas.utils.LogUtil;

public class GdsExcelImpLogRSVImpl implements IGdsExcelImpLogRSV {
	@Resource
	private IGdsExcelImpLogSV gdsExcelImpLogSV;
private static final String MODULE = GdsExcelImpLogRSVImpl.class.getName();
	@Override
	public Long addGdsExcelImpLog(GdsExcelImpLogReqDTO excelImpLogReqDTO) throws BusinessException {

		try {
			LogUtil.debug(MODULE, excelImpLogReqDTO.toString());
		return	gdsExcelImpLogSV.addGdsExcelImpLog(excelImpLogReqDTO);
		} catch (BusinessException e) {
			throw  e;
		}catch (Exception e) {
			// TODO: handle exception
			throw new BusinessException();
		}

	}
	@Override
	public PageResponseDTO<GdsExcelImpLogRespDTO> queryImpLogByPage(GdsExcelImpLogReqDTO dto) throws BusinessException {

		try {
			LogUtil.debug(MODULE, "查询入参：" + dto.toString());
		return	gdsExcelImpLogSV.queryImpLogByPage(dto);
		} catch (BusinessException e) {
			throw  e;
		}catch (Exception e) {
			// TODO: handle exception
			throw new BusinessException();
		}
	}
	@Override
	public void delImportLogById(GdsExcelImpLogReqDTO excelImpLogReqDTO) throws BusinessException {
		try {
			LogUtil.debug(MODULE, excelImpLogReqDTO.toString());
			gdsExcelImpLogSV.delImportLogById(excelImpLogReqDTO);
		} catch (BusinessException e) {
			throw  e;
		}catch (Exception e) {
			// TODO: handle exception
			throw new BusinessException();
		}
	}

}
