/** 
 * Project Name:ecp-web-manage Maven Webapp 
 * File Name:CmsInfoController.java 
 * Package Name:com.zengshi.ecp.busi.cms.controller 
 * Date:2015-8-14下午2:58:36 
 * Copyright (c) 2015, ZengShi All Rights Reserved. 
 * 
 */
package com.zengshi.ecp.busi.cms.info.controller;

import java.io.IOException;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.zengshi.ecp.base.controller.EcpBaseController;
import com.zengshi.ecp.base.vo.EcpBasePageRespVO;
import com.zengshi.ecp.base.vo.EcpBaseResponseVO;
import com.zengshi.ecp.busi.cms.info.vo.CmsInfoVO;
import com.zengshi.ecp.cms.dubbo.dto.CmsInfoReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsInfoRespDTO;
import com.zengshi.ecp.cms.dubbo.interfaces.ICmsInfoRSV;
import com.zengshi.ecp.cms.dubbo.interfaces.ICmsPlaceRSV;
import com.zengshi.ecp.cms.dubbo.interfaces.ICmsSiteRSV;
import com.zengshi.ecp.cms.dubbo.interfaces.ICmsTemplateRSV;
import com.zengshi.ecp.cms.dubbo.util.CmsConstants;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.staff.dubbo.dto.FileImportReqDTO;
import com.zengshi.ecp.staff.dubbo.util.StaffConstants;
import com.zengshi.paas.utils.DateUtil;
import com.zengshi.paas.utils.FileUtil;
import com.zengshi.paas.utils.ImageUtil;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.ObjectCopyUtil;
import com.zengshi.paas.utils.StringUtil;
import com.alibaba.fastjson.JSONObject;

/**
 * Title: ECP <br>
 * Project Name:ecp-web-manage Maven Webapp <br>
 * Description: 页面信息管理平台前店controller类<br>
 * Date:2015-8-14下午2:58:36 <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author wenyf
 * @version
 * @since JDK 1.7
 */
@Controller
@RequestMapping(value = "/info")
public class CmsInfoController extends EcpBaseController {

	private static String MODULE = CmsInfoController.class.getName();
	private String URL = "/cms/info/";// 返回页面的基本路径

	@Resource(name = "cmsInfoRSV")
	private ICmsInfoRSV cmsInfoRSV;
	@Resource(name = "cmsPlaceRSV")
	private ICmsPlaceRSV cmsPlaceRSV;
	@Resource(name = "cmsSiteRSV")
	private ICmsSiteRSV cmsSiteRSV;
	@Resource(name = "cmsTemplateRSV")
	private ICmsTemplateRSV cmsTemplateRSV;

	/**
	 * 页面信息新增页面初始化 infoAdd:(这里用一句话描述这个方法的作用). <br/>
	 * TODO(这里描述这个方法适用条件 – 可选).<br/>
	 * TODO(这里描述这个方法的执行流程 – 可选).<br/>
	 * TODO(这里描述这个方法的使用方法 – 可选).<br/>
	 * TODO(这里描述这个方法的注意事项 – 可选).<br/>
	 * 
	 * @author wenyf
	 * @param model
	 * @return
	 * @since JDK 1.7
	 */
	@RequestMapping(value = "/infoadd")
	public String infoAdd(Model model,@ModelAttribute("searchParams") String searchParams) {
		LogUtil.info(MODULE, "开始新增页面初始化");
		
		/* 1.根据入参调用后场页面信息查询服务 */
		CmsInfoVO vo = new CmsInfoVO();
		vo.setPubTime(DateUtil.getSysDate());
		
		/* 2.设置页面对象 */
		model.addAttribute("respVO", vo);
		
		/* 3.跳转到页面的路径 */
		return URL + "info-edit";
	}

	/**
	 * 编辑页面初始化 infoEdit:(这里用一句话描述这个方法的作用). <br/>
	 * TODO(这里描述这个方法适用条件 – 可选).<br/>
	 * TODO(这里描述这个方法的执行流程 – 可选).<br/>
	 * TODO(这里描述这个方法的使用方法 – 可选).<br/>
	 * TODO(这里描述这个方法的注意事项 – 可选).<br/>
	 * 
	 * @author wenyf
	 * @return
	 * @since JDK 1.7
	 */
	@RequestMapping(value = "/infoedit")
	public String infoEdit(Model model, 
	        @RequestParam("id") String id,
	        @ModelAttribute("searchParams") String searchParams) {
		LogUtil.info(MODULE, "开始页面编辑初始化,入参：{id=" + id + "}");
		if (StringUtils.isBlank(id)) {
			throw new BusinessException("cms.common.param.null.error", new String[]{"id"});
		}

		/* 1.根据入参调用后场页面信息查询服务 */
		CmsInfoReqDTO cmsInfoDTO = new CmsInfoReqDTO();
		cmsInfoDTO.setId(Long.parseLong(id));
		CmsInfoRespDTO respVO = cmsInfoRSV.queryCmsInfo(cmsInfoDTO);
		if (respVO != null && StringUtils.isNotBlank(respVO.getStaticId())) {
		    respVO.setStaticUrl(ImageUtil.getStaticDocUrl(respVO.getStaticId(), "html"));
		}
		
		/* 2.设置页面对象 */
		model.addAttribute("respVO", respVO);

		/* 3.跳转到页面的路径 */
		return URL + "info-edit";
	}

	/**
	 * 保存页面信息方法 infoSave:(这里用一句话描述这个方法的作用). <br/>
	 * TODO(这里描述这个方法适用条件 – 可选).<br/>
	 * TODO(这里描述这个方法的执行流程 – 可选).<br/>
	 * TODO(这里描述这个方法的使用方法 – 可选).<br/>
	 * TODO(这里描述这个方法的注意事项 – 可选).<br/>
	 * 
	 * @author wenyf
	 * @param cmsInfo
	 * @return
	 * @throws Exception
	 * @since JDK 1.7
	 */
	@RequestMapping(value = "/infosave")
	public EcpBaseResponseVO infoSave(@Valid CmsInfoVO cmsInfoVO)throws Exception {
		LogUtil.info(MODULE, "进入修改页面信息方法,入参：{vo=" + cmsInfoVO.toString() + "}");

		/* 1.保存静态文件到静态文件服务器 */
		String staticId = FileUtil.saveFile(cmsInfoVO.getEditorText().getBytes("utf-8"),"cmsinfo", ".html");
		cmsInfoVO.setStaticId(staticId);// 静态文件ID

		/* 2.封装后场入参 */
		CmsInfoReqDTO dto = new CmsInfoReqDTO();
		ObjectCopyUtil.copyObjValue(cmsInfoVO, dto, "", true);
		dto.setSource(CmsConstants.InfoSource.CMS_INFOSOURCE_WEB);// 来源 01：web, 02：手机 ,03: IPAD
		dto.setStatus(CmsConstants.ParamStatus.CMS_PARAMSTATUS_0);// 新增默认为无效状态

		/* 3.调用后场页面信息修改服务 */
		EcpBaseResponseVO respVO = new EcpBaseResponseVO();
		if (StringUtil.isNotEmpty(dto.getId())) {// 修改
			cmsInfoRSV.updateCmsInfo(dto);
		} else {// 新增
			cmsInfoRSV.saveCmsInfo(dto);
		}
		respVO.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_SUCCESS);
		return respVO;
	}

	/**
	 * 发布页面信息方法 pubSave:(这里用一句话描述这个方法的作用). <br/>
	 * TODO(这里描述这个方法适用条件 – 可选).<br/>
	 * TODO(这里描述这个方法的执行流程 – 可选).<br/>
	 * TODO(这里描述这个方法的使用方法 – 可选).<br/>
	 * TODO(这里描述这个方法的注意事项 – 可选).<br/>
	 * 
	 * @author wenyf
	 * @param cmsInfo
	 * @return
	 * @throws Exception
	 * @since JDK 1.7
	 */
	@RequestMapping(value = "/pubsave")
	public EcpBaseResponseVO pubSave(@Valid CmsInfoVO cmsInfoVO) throws Exception {
		LogUtil.info(MODULE, "进入修改页面信息方法,入参：{vo=" + cmsInfoVO.toString() + "}");

		/* 1.保存静态文件到静态文件服务器 */
		String staticId = FileUtil .saveFile(cmsInfoVO.getEditorText().getBytes("utf-8"), "cmsinfo", ".html");
		cmsInfoVO.setStaticId(staticId);// 静态文件ID

		/* 2.封装后场入参 */
		CmsInfoReqDTO dto = new CmsInfoReqDTO();
		ObjectCopyUtil.copyObjValue(cmsInfoVO, dto, "", true);
		dto.setSource(CmsConstants.InfoSource.CMS_INFOSOURCE_WEB);// 来源 01：web,02：手机,03:IPAD
		dto.setStatus(CmsConstants.ParamStatus.CMS_PARAMSTATUS_1);// 发布默认为有效状态

		/* 3.调用后场页面信息修改服务 */
		EcpBaseResponseVO respVO = new EcpBaseResponseVO();
		if (StringUtil.isNotEmpty(dto.getId())) {// 修改
			cmsInfoRSV.updateCmsInfo(dto);
		} else {// 新增
			cmsInfoRSV.saveCmsInfo(dto);
		}
		respVO.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_SUCCESS);
		return respVO;
	}

	/**
	 * 使页面信息生效 infoActive:(这里用一句话描述这个方法的作用). <br/>
	 * TODO(这里描述这个方法适用条件 – 可选).<br/>
	 * TODO(这里描述这个方法的执行流程 – 可选).<br/>
	 * TODO(这里描述这个方法的使用方法 – 可选).<br/>
	 * TODO(这里描述这个方法的注意事项 – 可选).<br/>
	 * 
	 * @author wenyf
	 * @param cmsInfo
	 * @return
	 * @throws Exception
	 * @since JDK 1.7
	 */
	@RequestMapping(value = "/infoactive")
	public EcpBaseResponseVO infoActive(@RequestParam("id") String id) throws Exception {
		LogUtil.info(MODULE, "进入使页面信息生效方法,入参：{id=" + id + "}");
		if (StringUtils.isBlank(id)) {
			throw new BusinessException("cms.common.param.null.error", new String[]{"id"});
		}

		/* 1.封装后场入参 */
		CmsInfoReqDTO dto = new CmsInfoReqDTO();
		dto.setId(Long.parseLong(id));

		/* 2.调用后场服务 */
		EcpBaseResponseVO respVO = new EcpBaseResponseVO();
		try {
			cmsInfoRSV.activeCmsInfo(dto);
		} catch (BusinessException err) {
		    LogUtil.error(MODULE, "使页面信息生效出现异常:",err);
			throw new BusinessException(err.getErrorMessage());
		}
		respVO.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_SUCCESS);
		return respVO;
	}

	/**
	 * 使页面信息失效 infoInvalid:(这里用一句话描述这个方法的作用). <br/>
	 * TODO(这里描述这个方法适用条件 – 可选).<br/>
	 * TODO(这里描述这个方法的执行流程 – 可选).<br/>
	 * TODO(这里描述这个方法的使用方法 – 可选).<br/>
	 * TODO(这里描述这个方法的注意事项 – 可选).<br/>
	 * 
	 * @author wenyf
	 * @param cmsInfo
	 * @return
	 * @throws Exception
	 * @since JDK 1.7
	 */
	@RequestMapping(value = "/infoinvalid")
	public EcpBaseResponseVO infoInvalid(@RequestParam("id") String id)throws Exception {
		LogUtil.info(MODULE, "进入使页面信息失效方法,入参：{id=" + id + "}");
		if (StringUtils.isBlank(id)) {
			throw new BusinessException("cms.common.param.null.error", new String[]{"id"});
		}

		/* 1.封装后场入参 */
		CmsInfoReqDTO dto = new CmsInfoReqDTO();
		dto.setId(Long.parseLong(id));

		/* 2.调用后场服务 */
		EcpBaseResponseVO respVO = new EcpBaseResponseVO();
		try {
			cmsInfoRSV.invalidCmsInfo(dto);
		} catch (BusinessException err) {
		    LogUtil.error(MODULE, "使页面信息失效出现异常:",err);
			throw new BusinessException(err.getErrorMessage());
		}
		respVO.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_SUCCESS);
		return respVO;
	}

	/**
	 * 页面信息删除 infoDelete:(这里用一句话描述这个方法的作用). <br/>
	 * TODO(这里描述这个方法适用条件 – 可选).<br/>
	 * TODO(这里描述这个方法的执行流程 – 可选).<br/>
	 * TODO(这里描述这个方法的使用方法 – 可选).<br/>
	 * TODO(这里描述这个方法的注意事项 – 可选).<br/>
	 * 
	 * @author wenyf
	 * @param cmsInfo
	 * @return
	 * @throws Exception
	 * @since JDK 1.7
	 */
	@RequestMapping(value = "/infodelete")
	public EcpBaseResponseVO infoDelete(@RequestParam("id") String id)throws Exception {
		LogUtil.info(MODULE, "进入删除信息方法,入参：{id=" + id + "}");
		if (StringUtils.isBlank(id)) {
			throw new BusinessException("cms.common.param.null.error", new String[]{"id"});
		}

		/* 1.封装后场入参 */
		CmsInfoReqDTO dto = new CmsInfoReqDTO();
		dto.setId(Long.parseLong(id));

		/* 2.调用后场服务 */
		EcpBaseResponseVO respVO = new EcpBaseResponseVO();
		try {
			cmsInfoRSV.deleteCmsInfo(dto);
		} catch (BusinessException err) {
		    LogUtil.error(MODULE, "删除信息出现异常:",err);
			throw new BusinessException(err.getErrorMessage());
		}
		respVO.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_SUCCESS);
		return respVO;
	}

	/**
	 * 分页查询页面信息页面初始化 infoGrid:(这里用一句话描述这个方法的作用). <br/>
	 * TODO(这里描述这个方法适用条件 – 可选).<br/>
	 * TODO(这里描述这个方法的执行流程 – 可选).<br/>
	 * TODO(这里描述这个方法的使用方法 – 可选).<br/>
	 * TODO(这里描述这个方法的注意事项 – 可选).<br/>
	 * 
	 * @author wenyf
	 * @return
	 * @since JDK 1.7
	 */
	@RequestMapping(value = "/infogrid")
	public String infoGrid(Model model,@ModelAttribute("searchParams") String searchParams) {
		return URL + "info-grid";
	}

	/**
	 * 分页查询页面信息列表方法 gridList:(这里用一句话描述这个方法的作用). <br/>
	 * TODO(这里描述这个方法适用条件 – 可选).<br/>
	 * TODO(这里描述这个方法的执行流程 – 可选).<br/>
	 * TODO(这里描述这个方法的使用方法 – 可选).<br/>
	 * TODO(这里描述这个方法的注意事项 – 可选).<br/>
	 * 
	 * @author wenyf
	 * @param model
	 * @param vo
	 * @return
	 * @throws Exception
	 * @since JDK 1.7
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/gridlist")
	@ResponseBody
	public Model gridList(Model model,@ModelAttribute("searchVO") CmsInfoVO searchVO) throws Exception {
		LogUtil.info(MODULE, "进入页面信息分页查询方法,入参：{vo=" + searchVO.toString() + "}");

		/* 1.封装后场入参对象 */
		CmsInfoReqDTO cmsInfoDTO = searchVO.toBaseInfo(CmsInfoReqDTO.class);
		ObjectCopyUtil.copyObjValue(searchVO, cmsInfoDTO, "MODULE", false);

		/* 2.调用后场服务 */
		PageResponseDTO<CmsInfoRespDTO> pageResponseDTO = cmsInfoRSV.queryCmsInfoPage(cmsInfoDTO);
		EcpBasePageRespVO<Map> respVO = EcpBasePageRespVO.buildByPageResponseDTO(pageResponseDTO);

		/* 3.返回 */
		return super.addPageToModel(model, respVO);
	}

	/**
	 * 页面信息查看页面初始化方法 infoCheck:(这里用一句话描述这个方法的作用). <br/>
	 * TODO(这里描述这个方法适用条件 – 可选).<br/>
	 * TODO(这里描述这个方法的执行流程 – 可选).<br/>
	 * TODO(这里描述这个方法的使用方法 – 可选).<br/>
	 * TODO(这里描述这个方法的注意事项 – 可选).<br/>
	 * 
	 * @author wenyf
	 * @param model
	 * @param id
	 * @return
	 * @since JDK 1.7
	 */
	@RequestMapping(value = "/infoview")
	public String infoview(Model model, 
	        @RequestParam("id") String id,
	        @ModelAttribute("searchParams") String searchParams) {
		LogUtil.info(MODULE, "进入页面信息查看初始化,入参：{id=" + id + "}");
		if (StringUtils.isBlank(id)) {
			throw new BusinessException("cms.common.param.null.error", new String[]{"id"});
		}

		/* 1.根据入参调用后场页面信息查询服务 */
		CmsInfoReqDTO cmsInfoDTO = new CmsInfoReqDTO();
		cmsInfoDTO.setId(Long.parseLong(id));
		CmsInfoRespDTO cmsInfoRespDTO = cmsInfoRSV.queryCmsInfo(cmsInfoDTO);
		// 静态文件
		if (cmsInfoRespDTO != null && StringUtils.isNotBlank(cmsInfoRespDTO.getStaticId())) {
			cmsInfoRespDTO.setStaticUrl(ImageUtil.getStaticDocUrl(cmsInfoRespDTO.getStaticId(), "html"));
		}
		// 附件
		if (cmsInfoRespDTO != null && StringUtils.isNotBlank(cmsInfoRespDTO.getVfsId())) {
			cmsInfoRespDTO.setVfsUrl(ImageUtil.getStaticDocUrl(cmsInfoRespDTO.getVfsId(), "doc"));
		}

		/* 2.设置页面对象 */
		model.addAttribute("respVO", cmsInfoRespDTO);

		/* 4.返回页面路径 */
		return URL + "info-view";
	}

	
	 /**
     * 
     * importData:(上传附件). <br/> 
     * 
     * @author huangxm9 
     * @param excel
     * @param model
     * @param request
     * @param response
     * @return 
     * @since JDK 1.6
     */
    @RequestMapping(value="/importdata")
    @ResponseBody
    public JSONObject importData(
            @RequestParam(value = "excelFile", required = true) MultipartFile excel,
            Model model,
            HttpServletRequest request, 
            HttpServletResponse response){
    	if(excel==null){
            LogUtil.info(MODULE, "附件不存在");
            throw new BusinessException(StaffConstants.STAFF_NULL_ERROR, new String[]{ "文件不存在" });
        }
        Long maxSize = 5*1024*1024L;
        if(excel.getSize()>maxSize){
        	LogUtil.info(MODULE, "附件大小不能超过5M");
            throw new BusinessException("附件大小不能超过5M", new String[]{ "附件大小不能超过5M" });
        }
        FileImportReqDTO aiReqDto = new FileImportReqDTO();
        String fileId = "";
        String oriFileName = excel.getOriginalFilename();
        String[] fileNamea = oriFileName.split("\\.");
        String fileName =    fileNamea[0]+"_"+UUID.randomUUID();
        String fileExtName = fileNamea[1];
        try {
            fileId = FileUtil.saveFile(excel.getBytes(), fileName, fileExtName);
        } catch (IOException e) {
            LogUtil.error(MODULE, "文件保存失败",e);
            throw new BusinessException("cms.common.param.null.erro", new String[]{ "文件保存失败" });
        }
        aiReqDto.setFileId(fileId);
        aiReqDto.setFileName(fileName);
        
        JSONObject obj = new JSONObject();
        obj.put("fileId", fileId);
        obj.put("fileName", oriFileName);
        return obj;
    }

}
