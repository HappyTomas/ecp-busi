package com.zengshi.ecp.busi.sys.controller;

import java.util.ArrayList;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.HtmlUtils;

import com.zengshi.ecp.base.controller.EcpBaseController;
import com.zengshi.ecp.base.vo.EcpBasePageRespVO;
import com.zengshi.ecp.base.vo.EcpBaseResponseVO;
import com.zengshi.ecp.busi.sys.vo.AppVersionVO;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.server.front.util.BaseParamUtil;
import com.zengshi.ecp.sys.dubbo.dto.AppVersionReqDTO;
import com.zengshi.ecp.sys.dubbo.dto.AppVersionRespDTO;
import com.zengshi.ecp.sys.dubbo.interfaces.IAppVersionRSV;
import com.zengshi.ecp.sys.dubbo.util.AppVersionConstants;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.ObjectCopyUtil;

@Controller
@RequestMapping(value = "/appversion")
public class AppVersionController extends EcpBaseController {

	private static final String MODULE = AppVersionController.class.getName();
	@Resource
	private IAppVersionRSV appVersionRSV;

	@RequestMapping(value = "/pageInit")
	public String pageInit(AppVersionVO appVersionVO) {
		return "/sys/appversion/version-grid";
	}

	@RequestMapping(value = "/listVersion")
	@ResponseBody
	public Model listVersion(Model model, AppVersionVO appVersionVO)
			throws Exception {
		LogUtil.debug(MODULE, "请求参数为：" + appVersionVO.toString());

		// /后场服务所需要的DTO；
		AppVersionReqDTO appVersionDTO = appVersionVO
				.toBaseInfo(AppVersionReqDTO.class);

		if (appVersionVO.getVerProgram() != null) {
			appVersionDTO.setVerProgram(appVersionVO.getVerProgram());
		}
		if (appVersionVO.getVerOs() != null
				&& !"".equals(appVersionVO.getVerOs())) {

			appVersionDTO.setVerOs(appVersionVO.getVerOs());
		}

		if (appVersionVO.getVerNo() != null && appVersionVO.getVerNo() != 0L) {

			appVersionDTO.setVerNo(appVersionVO.getVerNo());
		}

		if (appVersionVO.getVerPublishNo() != null
				&& !"".equals(appVersionVO.getVerPublishNo())) {

			appVersionDTO.setVerPublishNo(appVersionVO.getVerPublishNo());
		}

		PageResponseDTO<AppVersionRespDTO> t = appVersionRSV
				.queryAppVersionPageInfo(appVersionDTO);

		// 调用，并结果返回；从后场返回的分页对象，封装为前店所需要的分页对象；
		if (t.getResult() == null) {
			t.setResult(new ArrayList<AppVersionRespDTO>());
		} else {

			for (AppVersionRespDTO appVersionRespDTO : t.getResult()) {
				String verPro = BaseParamUtil.fetchParamValue(
						"APP_VER_PROGRAM", appVersionRespDTO.getVerProgram());
				appVersionRespDTO.setVerProgram(verPro);

				String verOs = BaseParamUtil.fetchParamValue("APP_VER_OS",
						appVersionRespDTO.getVerOs());
				appVersionRespDTO.setVerOs(verOs);

				if ("00".equals(appVersionRespDTO.getStatus())) {
					appVersionRespDTO.setStatus("未发布");
				} else if ("01".equals(appVersionRespDTO.getStatus())) {
					appVersionRespDTO.setStatus("发布中");
				} else if ("02".equals(appVersionRespDTO.getStatus())) {
					appVersionRespDTO.setStatus("已过时");
				}
			}

		}
		EcpBasePageRespVO<Map> respVO = EcpBasePageRespVO
				.buildByPageResponseDTO(t);
		return super.addPageToModel(model, respVO);
	}

	@RequestMapping(value = "/addVersion")
	public String addAppVersion(AppVersionVO appVersionVO) throws Exception {

		return "/sys/appversion/version-add";
	}

	@RequestMapping(value = "/editVersion")
	public String editAppVersion(Model model, AppVersionVO appVersionVO)
			throws Exception {
		AppVersionReqDTO appVersionReqDTO = new AppVersionReqDTO();
		appVersionReqDTO.setId(appVersionVO.getId());
		AppVersionRespDTO appVersionRespDTO = appVersionRSV
				.queryAppVersionByPK(appVersionReqDTO);
		ObjectCopyUtil.copyObjValue(appVersionRespDTO, appVersionVO, null,
				false);

		model.addAttribute("appVersion", appVersionVO);
		return "/sys/appversion/version-edit";
	}

	@RequestMapping(value = "/saveAddVersion")
	@ResponseBody
	public EcpBaseResponseVO saveAddAppVersion(AppVersionVO appVersionVO) throws Exception {
		AppVersionReqDTO appVersionReqDTO = new AppVersionReqDTO();
		ObjectCopyUtil
				.copyObjValue(appVersionVO, appVersionReqDTO, null, false);
		EcpBaseResponseVO respVO = new EcpBaseResponseVO();
		try {
			appVersionRSV.addAppVersion(appVersionReqDTO);
			respVO.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_SUCCESS);
		} catch (BusinessException e) {

			respVO.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_FAILURE);
			respVO.setResultMsg(e.getMessage());
		}
		 return respVO;
	}

	@RequestMapping(value = "/publishVer")
	public void publishVer(AppVersionVO appVersionVO) throws Exception {
		AppVersionReqDTO appVersionReqDTO = new AppVersionReqDTO();
		appVersionReqDTO.setId(appVersionVO.getId());
		appVersionReqDTO.setStatus(AppVersionConstants.VERSION_STATUS_DEAL);
		appVersionRSV.updateAppVersion(appVersionReqDTO);
	}

	@RequestMapping(value = "/saveEditVersion")
	@ResponseBody
	public EcpBaseResponseVO saveEditAppVersion(AppVersionVO appVersionVO)
			throws Exception {
		AppVersionReqDTO appVersionReqDTO = new AppVersionReqDTO();
		ObjectCopyUtil
				.copyObjValue(appVersionVO, appVersionReqDTO, null, false);
		EcpBaseResponseVO respVO = new EcpBaseResponseVO();
		try {
			appVersionRSV.updateAppVersion(appVersionReqDTO);
			respVO.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_SUCCESS);
		} catch (BusinessException e) {
			respVO.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_FAILURE);
			respVO.setResultMsg(e.getMessage());
		}
		return respVO;
	}

	@RequestMapping(value = "/verDetail")
	public String verDetail(Model model, AppVersionVO appVersionVO) {
		AppVersionReqDTO appVersionReqDTO = new AppVersionReqDTO();
		appVersionReqDTO.setId(appVersionVO.getId());
		AppVersionRespDTO appVersionRespDTO = appVersionRSV
				.queryAppVersionByPK(appVersionReqDTO);
		ObjectCopyUtil.copyObjValue(appVersionRespDTO, appVersionVO, null,
				false);
		String str = appVersionVO.getVerDetail();
		if (str != null) {
			str = str.replaceAll("\n", "<br />");
			str = str.replaceAll("\r", "<br />");
			appVersionVO.setVerDetail(str);
		}
		model.addAttribute("appVersion", appVersionVO);
		return "/sys/appversion/detail/version-detail";

	}
}
