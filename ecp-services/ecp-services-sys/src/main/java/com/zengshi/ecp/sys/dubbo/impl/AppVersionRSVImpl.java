package com.zengshi.ecp.sys.dubbo.impl;

import javax.annotation.Resource;

import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.sys.dubbo.dto.AppVersionReqDTO;
import com.zengshi.ecp.sys.dubbo.dto.AppVersionRespDTO;
import com.zengshi.ecp.sys.dubbo.interfaces.IAppVersionRSV;
import com.zengshi.ecp.sys.dubbo.util.AppVersionConstants;
import com.zengshi.ecp.sys.service.common.interfaces.IAppVersionSV;
import com.zengshi.paas.utils.LogUtil;

public class AppVersionRSVImpl implements IAppVersionRSV{
	private static final String MODULE = AppVersionRSVImpl.class.getName();
	@Resource
	private IAppVersionSV appVersionSV;
	
	@Override
	public void addAppVersion(AppVersionReqDTO appVersionReqDTO)
			throws BusinessException {
		// TODO Auto-generated method stub
		try{
			appVersionSV.addAppVersion(appVersionReqDTO);
			
		}catch(BusinessException e){
			throw e;
			
		}catch (Exception e) {
			// TODO: handle exception
			throw new BusinessException(AppVersionConstants.SYS_APP_VERSION_ADD_ERROR);
		}
	}

	@Override
	public void updateAppVersion(AppVersionReqDTO appVersionReqDTO)
			throws BusinessException {
		// TODO Auto-generated method stub
		try{
			appVersionSV.updateAppVersion(appVersionReqDTO);
			
		}catch(BusinessException e){
			throw e;
			
		}catch (Exception e) {
			// TODO: handle exception
			throw new BusinessException(AppVersionConstants.SYS_APP_VERSION_UPDATE_ERROR);
		}
	}

	@Override
	public PageResponseDTO<AppVersionRespDTO> queryAppVersionPageInfo(
			AppVersionReqDTO appVersionReqDTO) throws BusinessException {
		// TODO Auto-generated method stub
		try{
			return appVersionSV.queryAppVersionPageInfo(appVersionReqDTO);
			
		}catch(BusinessException e){
			throw e;
			
		}catch (Exception e) {
			// TODO: handle exception
			LogUtil.error(MODULE, "表或者视图不存在");
			throw new BusinessException(AppVersionConstants.SYS_APP_VERSION_FETCHLIST_ERROR);
		}
	}

	@Override
	public AppVersionRespDTO checkUpdateStatus(AppVersionReqDTO appVersionReqDTO)
			throws BusinessException {
		// TODO Auto-generated method stub
		try{
			return appVersionSV.checkUpdateStatus(appVersionReqDTO);
			
		}catch(BusinessException e){
			throw e;
			
		}catch (Exception e) {
			// TODO: handle exception
			throw new BusinessException(AppVersionConstants.SYS_APP_VERSION_CHECKUPDATE_ERROR);
		}
	}

	@Override
	public AppVersionRespDTO queryAppVersionByPK(
			AppVersionReqDTO appVersionReqDTO) throws BusinessException {
		// TODO Auto-generated method stub
		try{
			return appVersionSV.queryAppVersionByPK(appVersionReqDTO);
			
		}catch(BusinessException e){
			throw e;
			
		}catch (Exception e) {
			// TODO: handle exception
			throw new BusinessException(AppVersionConstants.SYS_APP_VERSION_FETCH_ERROR);
		}	}

}
