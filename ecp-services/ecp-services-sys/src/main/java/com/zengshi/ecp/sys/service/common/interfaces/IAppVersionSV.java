package com.zengshi.ecp.sys.service.common.interfaces;

import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.sys.dubbo.dto.AppVersionReqDTO;
import com.zengshi.ecp.sys.dubbo.dto.AppVersionRespDTO;

public interface IAppVersionSV {

	// 新增app版本
	public void addAppVersion(AppVersionReqDTO appVersionReqDTO)
			throws Exception;

	// 更新app版本记录
	public void updateAppVersion(AppVersionReqDTO appVersionReqDTO)
			throws Exception;

	public PageResponseDTO<AppVersionRespDTO> queryAppVersionPageInfo(
			AppVersionReqDTO appVersionReqDTO) throws Exception;

	public AppVersionRespDTO checkUpdateStatus(AppVersionReqDTO appVersionReqDTO)throws Exception;
	
	public AppVersionRespDTO queryAppVersionByPK(
			AppVersionReqDTO appVersionReqDTO) throws Exception ;
}
