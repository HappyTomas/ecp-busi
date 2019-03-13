package com.zengshi.ecp.sys.dubbo.interfaces;

import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.sys.dubbo.dto.AppVersionReqDTO;
import com.zengshi.ecp.sys.dubbo.dto.AppVersionRespDTO;

public interface IAppVersionRSV {

	// 新增app版本
	public void addAppVersion(AppVersionReqDTO appVersionReqDTO)
			throws BusinessException;

	// 更新app版本记录
	public void updateAppVersion(AppVersionReqDTO appVersionReqDTO)
			throws BusinessException;

	public PageResponseDTO<AppVersionRespDTO> queryAppVersionPageInfo(
			AppVersionReqDTO appVersionReqDTO) throws BusinessException;

	public AppVersionRespDTO checkUpdateStatus(AppVersionReqDTO appVersionReqDTO)throws BusinessException;
	
	public AppVersionRespDTO queryAppVersionByPK(
			AppVersionReqDTO appVersionReqDTO) throws BusinessException; 
}
