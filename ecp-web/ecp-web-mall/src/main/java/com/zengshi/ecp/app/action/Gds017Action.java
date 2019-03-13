package com.zengshi.ecp.app.action;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.zengshi.ecp.app.req.Gds017Req;
import com.zengshi.ecp.app.resp.gds.AppVersionInfo;
import com.zengshi.ecp.app.resp.Gds017Resp;
import com.zengshi.ecp.cms.dubbo.dto.CmsSiteRespDTO;
import com.zengshi.ecp.cms.dubbo.util.CmsCacheUtil;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.sys.dubbo.dto.AppVersionReqDTO;
import com.zengshi.ecp.sys.dubbo.dto.AppVersionRespDTO;
import com.zengshi.ecp.sys.dubbo.interfaces.IAppVersionRSV;
import com.zengshi.paas.utils.ObjectCopyUtil;
import com.zengshi.paas.utils.StringUtil;
import com.zengshi.butterfly.app.annotation.Action;
import com.zengshi.butterfly.core.exception.SystemException;

/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-web-mall <br>
 * Description: <br>
 * Date:2016年3月12日下午5:16:50 <br>
 * Copyright (c) 2016 ZengShi All Rights Reserved <br>
 * 
 * @author zjh
 * @version
 * @since JDK 1.6
 */
@Service("gds017")
@Action(bizcode = "gds017", userCheck = false)
@Scope("prototype")
public class Gds017Action extends AppBaseAction<Gds017Req, Gds017Resp> {

	private static final String MODULE = Gds017Action.class.getName();
	@Resource
	private IAppVersionRSV appVersionRSV;


	@Override
	protected void getResponse() throws BusinessException, SystemException,
			Exception {
		Gds017Req gds017Req = this.request;
		Gds017Resp gds017Resp = this.response;

		AppVersionReqDTO appVersionReqDTO = new AppVersionReqDTO();
		appVersionReqDTO.setVerOs(gds017Req.getVerOs());
		appVersionReqDTO.setVerProgram(gds017Req.getVerProgram());
		appVersionReqDTO.setVerNo(gds017Req.getVerNo());
		AppVersionRespDTO appVersionRespDTO = appVersionRSV.checkUpdateStatus(appVersionReqDTO);
		AppVersionInfo appVersionInfo = new AppVersionInfo();
		ObjectCopyUtil.copyObjValue(appVersionRespDTO, appVersionInfo, null, false);
		appVersionInfo.setVerDetailUrl(getHtmlUrl(appVersionRespDTO.getVerDetailUrl()));
		appVersionInfo.setVerUrl(getDownHtmlUrl());
		gds017Resp.setAppVersionInfo(appVersionInfo);
	}

	
	  private String getHtmlUrl(String vfsId) {
	        if (StringUtil.isBlank(vfsId)) {
	            return "";
	        }
	        CmsSiteRespDTO cms = CmsCacheUtil.getCmsSiteCache(1L);
	        if (cms != null && StringUtil.isNotBlank(cms.getSiteUrl())) {
	            return cms.getSiteUrl() + "/gdsdetail/html/" + vfsId;
	        }
	        return "";
	    }
	  private String getDownHtmlUrl() {
	        
	        CmsSiteRespDTO cms = CmsCacheUtil.getCmsSiteCache(1L);
	        if (cms != null && StringUtil.isNotBlank(cms.getSiteUrl())) {
	            return cms.getSiteUrl() + "/app/download";
	        }
	        return "";
	    }
}