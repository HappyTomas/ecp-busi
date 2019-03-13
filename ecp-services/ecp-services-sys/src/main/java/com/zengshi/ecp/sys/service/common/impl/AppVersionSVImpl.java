package com.zengshi.ecp.sys.service.common.impl;

import com.zengshi.ecp.frame.vo.BaseCriteria;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.server.service.impl.GeneralSQLSVImpl;
import com.zengshi.ecp.server.service.pagination.PaginationCallback;
import com.zengshi.ecp.sys.dao.mapper.common.AppVersionMapper;
import com.zengshi.ecp.sys.dao.model.AppVersion;
import com.zengshi.ecp.sys.dao.model.AppVersionCriteria;
import com.zengshi.ecp.sys.dubbo.dto.AppVersionReqDTO;
import com.zengshi.ecp.sys.dubbo.dto.AppVersionRespDTO;
import com.zengshi.ecp.sys.dubbo.util.AppVersionConstants;
import com.zengshi.ecp.sys.service.common.interfaces.IAppVersionSV;
import com.zengshi.paas.utils.ObjectCopyUtil;
import com.zengshi.paas.utils.StringUtil;
import com.db.sequence.Sequence;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.Collections;
import java.util.List;

public class AppVersionSVImpl extends GeneralSQLSVImpl implements IAppVersionSV {

	@Resource(name = "seq_app_version")
	private Sequence seqAppVersion;
	@Resource
	private AppVersionMapper appVersionMapper;

	@Override
	public void addAppVersion(AppVersionReqDTO appVersionReqDTO)
			throws Exception {
		if (!checkVerNo(appVersionReqDTO)) {
			throw new BusinessException(
					AppVersionConstants.SYS_APP_VERSION_VERNO_EXISTS);
		}
		// TODO Auto-generated method stub
		AppVersion appVersion = new AppVersion();
		ObjectCopyUtil.copyObjValue(appVersionReqDTO, appVersion, null, false);
		Long versionId = seqAppVersion.nextValue();
		appVersion.setId(versionId);
		appVersion.setStatus(AppVersionConstants.VERSION_STATUS_UNDEAL);
		appVersion.setCreateStaff(appVersionReqDTO.getStaff().getId());
		appVersion.setUpdateStaff(appVersionReqDTO.getStaff().getId());
		appVersion.setCreateTime(new Timestamp(System.currentTimeMillis()));
		appVersion.setUpdateTime(new Timestamp(System.currentTimeMillis()));
		appVersionMapper.insertSelective(appVersion);
	}

	private Boolean checkVerNo(AppVersionReqDTO appVersionReqDTO) {
		Boolean flag = true;

		AppVersionCriteria example = new AppVersionCriteria();
		AppVersionCriteria.Criteria criteria = example.createCriteria();
		criteria.andVerOsEqualTo(appVersionReqDTO.getVerOs());
		criteria.andVerNoGreaterThanOrEqualTo(appVersionReqDTO.getVerNo());
		Long count = appVersionMapper.countByExample(example);
		if (count > 0) {
			flag = false;
		}
		return flag;
	}
	
	private Boolean checkEditVerNo(AppVersionReqDTO appVersionReqDTO) {
		Boolean flag = true;
		AppVersionCriteria example = new AppVersionCriteria();
		List<AppVersion> dlist= appVersionMapper.selectByExample(example);
		long max = dlist.get(0).getVerNo();     
		for (int i = 0; i < dlist.size(); i++) {          
             if (max < dlist.get(i).getVerNo()) max = dlist.get(i).getVerNo();        
		}
		long noMax = max;
		AppVersionCriteria.Criteria criteria = example.createCriteria();
//		criteria.andVerOsEqualTo(appVersionReqDTO.getVerOs());
		criteria.andVerNoEqualTo(appVersionReqDTO.getVerNo());
		Long count = appVersionMapper.countByExample(example);
		if(appVersionReqDTO.getVerNo()>noMax || count==1){
			return flag;
		}else{
			flag = false;
		}
		return flag;
	}

	@Override
	public void updateAppVersion(AppVersionReqDTO appVersionReqDTO)
			throws Exception {
		// TODO Auto-generated method stub
        if(StringUtil.isNotBlank(appVersionReqDTO.getVerOs())){
		if (!checkEditVerNo(appVersionReqDTO)) {
			throw new BusinessException(
					AppVersionConstants.SYS_APP_VERSION_VERNO_EXISTS);
		}
        }
		if (appVersionReqDTO.getId() == null || appVersionReqDTO.getId() == 0L) {

			throw new BusinessException(
					AppVersionConstants.SYS_APP_VERSION_PARAM_NULL_ID);
		}
		AppVersion appVersionTemp = appVersionMapper
				.selectByPrimaryKey(appVersionReqDTO.getId());

		AppVersion appVersion = new AppVersion();
		ObjectCopyUtil.copyObjValue(appVersionReqDTO, appVersion, null, false);
		appVersion.setUpdateTime(new Timestamp(System.currentTimeMillis()));
		if (AppVersionConstants.VERSION_STATUS_DEAL.equals(appVersion
				.getStatus())) {
			// 先将之前版本置为已过时
			AppVersionCriteria appVersionCriteria = new AppVersionCriteria();
			AppVersionCriteria.Criteria criteria = appVersionCriteria
					.createCriteria();
			criteria.andVerOsEqualTo(appVersionTemp.getVerOs());
			criteria.andVerProgramEqualTo(appVersionTemp.getVerProgram());
			criteria.andStatusEqualTo(AppVersionConstants.VERSION_STATUS_DEAL);
			List<AppVersion> appVersions = appVersionMapper
					.selectByExample(appVersionCriteria);

			if (appVersions != null && appVersions.size() > 0) {
				AppVersion appVersionPre = appVersions.get(0);
				appVersionPre
						.setStatus(AppVersionConstants.VERSION_STATUS_OUTTIME);
				appVersionPre.setUpdateTime(new Timestamp(System
						.currentTimeMillis()));
				appVersionPre.setUpdateStaff(appVersionReqDTO.getStaff()
						.getId());
				appVersionMapper.updateByPrimaryKeySelective(appVersionPre);

			}

			appVersion
					.setPublishTime(new Timestamp(System.currentTimeMillis()));
			appVersion.setPublishStaff(appVersionReqDTO.getStaff().getId());
		}
		appVersionMapper.updateByPrimaryKeySelective(appVersion);

	}

	@Override
	public PageResponseDTO<AppVersionRespDTO> queryAppVersionPageInfo(
			AppVersionReqDTO appVersionReqDTO) throws Exception {
		AppVersionCriteria appVersionCriteria = new AppVersionCriteria();
		AppVersionCriteria.Criteria criteria = appVersionCriteria
				.createCriteria();
		if (StringUtil.isNotBlank(appVersionReqDTO.getVerProgram())) {

			criteria.andVerProgramEqualTo(appVersionReqDTO.getVerProgram());

		}
		if (StringUtil.isNotBlank(appVersionReqDTO.getVerOs())) {

			criteria.andVerOsEqualTo(appVersionReqDTO.getVerOs());

		}
		if (StringUtil.isNotBlank(appVersionReqDTO.getStatus())) {

			criteria.andVerOsEqualTo(appVersionReqDTO.getStatus());

		}
		if (StringUtil.isNotBlank(appVersionReqDTO.getVerPublishNo())) {

			criteria.andVerPublishNoEqualTo(appVersionReqDTO.getVerPublishNo());

		}
		if (appVersionReqDTO.getVerNo() != null
				&& appVersionReqDTO.getVerNo() != 0l) {

			criteria.andVerNoEqualTo(appVersionReqDTO.getVerNo());

		}
		appVersionCriteria.setOrderByClause("update_time desc");
		appVersionCriteria.setLimitClauseCount(appVersionReqDTO.getPageSize());
		appVersionCriteria.setLimitClauseStart(appVersionReqDTO
				.getStartRowIndex());
		// TODO Auto-generated method stub
		return super.queryByPagination(appVersionReqDTO, appVersionCriteria,
				true, new PaginationCallback<AppVersion, AppVersionRespDTO>() {

					@Override
					public List<AppVersion> queryDB(BaseCriteria arg0) {
						// TODO Auto-generated method stub
						return appVersionMapper
								.selectByExample((AppVersionCriteria) arg0);
					}

					@Override
					public long queryTotal(BaseCriteria arg0) {
						// TODO Auto-generated method stub
						return appVersionMapper
								.countByExample((AppVersionCriteria) arg0);
					}

					@Override
					public AppVersionRespDTO warpReturnObject(AppVersion arg0) {
						// TODO Auto-generated method stub
						AppVersionRespDTO appVersionRespDTO = new AppVersionRespDTO();
						ObjectCopyUtil.copyObjValue(arg0, appVersionRespDTO,
								null, false);
						// appVersionRespDTO.setVerDetailUrl(getHtmlUrl(arg0.getVerDetail()));
						return appVersionRespDTO;
					}
				});
	}

	@Override
	public AppVersionRespDTO checkUpdateStatus(AppVersionReqDTO appVersionReqDTO)
			throws Exception {
		// TODO Auto-generated method stub
		AppVersionRespDTO appVersionRespDTO = new AppVersionRespDTO();
		if (appVersionReqDTO.getVerNo() == null
				|| appVersionReqDTO.getVerNo() == 0L) {

			throw new BusinessException(
					AppVersionConstants.SYS_APP_VERSION_PARAM_NULL_VERNO);
		}

		AppVersionCriteria exampleIfUpdate = new AppVersionCriteria();
		AppVersionCriteria.Criteria criteriaIfUpdate = exampleIfUpdate
				.createCriteria();
		criteriaIfUpdate.andVerNoGreaterThan(appVersionReqDTO.getVerNo());
		criteriaIfUpdate
				.andStatusNotEqualTo(AppVersionConstants.VERSION_STATUS_UNDEAL);
		criteriaIfUpdate.andVerOsEqualTo(appVersionReqDTO.getVerOs());
		Long count = appVersionMapper.countByExample(exampleIfUpdate);
		Boolean ifUpdate = false;
		if (count > 0) {
			ifUpdate = true;
			// appVersionRespDTO.setIfUpdate(true);

		}

		AppVersionCriteria exampleIfForce = new AppVersionCriteria();
		AppVersionCriteria.Criteria criteriaIfForce = exampleIfForce
				.createCriteria();
		criteriaIfForce.andIfForceEqualTo("1");
		criteriaIfForce.andVerNoGreaterThan(appVersionReqDTO.getVerNo());
		criteriaIfForce
				.andStatusNotEqualTo(AppVersionConstants.VERSION_STATUS_UNDEAL);
		criteriaIfForce.andVerOsEqualTo(appVersionReqDTO.getVerOs());
		Long countForce = appVersionMapper.countByExample(exampleIfForce);
		Boolean ifForce = false;
		if (countForce > 0) {

			ifForce = true;

		}
		AppVersionCriteria example = new AppVersionCriteria();
		AppVersionCriteria.Criteria criteria = example.createCriteria();
		criteria.andStatusEqualTo(AppVersionConstants.VERSION_STATUS_DEAL);
		criteria.andVerOsEqualTo(appVersionReqDTO.getVerOs());
		List<AppVersion> appVersions = appVersionMapper
				.selectByExample(example);

		if (appVersions != null && appVersions.size() > 0) {
			ObjectCopyUtil.copyObjValue(appVersions.get(0), appVersionRespDTO,
					null, false);

		}
		appVersionRespDTO.setIfUpdate(ifUpdate);
		appVersionRespDTO.setIfFore(ifForce);
		return appVersionRespDTO;
	}

	public AppVersionRespDTO queryAppVersionByPK(
			AppVersionReqDTO appVersionReqDTO) throws Exception {
		AppVersionRespDTO appVersionRespDTO = new AppVersionRespDTO();
		AppVersion appVersion = appVersionMapper
				.selectByPrimaryKey(appVersionReqDTO.getId());
		if (appVersion != null) {
			ObjectCopyUtil.copyObjValue(appVersion, appVersionRespDTO, null,
					false);
			return appVersionRespDTO;
		} else {

			return null;
		}

	}
	// private String getHtmlUrl(String vfsId){
	// if(StringUtil.isBlank(vfsId)){
	// return "";
	// }
	// return ImageUtil.getStaticDocUrl(vfsId, "html");
	// }
}
