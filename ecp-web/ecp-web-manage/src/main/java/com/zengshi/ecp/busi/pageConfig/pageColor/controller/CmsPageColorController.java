package com.zengshi.ecp.busi.pageConfig.pageColor.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zengshi.ecp.base.controller.EcpBaseController;
import com.zengshi.ecp.base.vo.EcpBasePageRespVO;
import com.zengshi.ecp.base.vo.EcpBaseResponseVO;
import com.zengshi.ecp.busi.pageConfig.pageColor.vo.CmsPageColorVO;
import com.zengshi.ecp.cms.dubbo.dto.CmsPageColorReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsPageColorRespDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsPageTypeReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsPageTypeRespDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsSiteReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsSiteRespDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsTemplateLibReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsTemplateLibRespDTO;
import com.zengshi.ecp.cms.dubbo.interfaces.ICmsPageColorRSV;
import com.zengshi.ecp.cms.dubbo.interfaces.ICmsPageConfigRSV;
import com.zengshi.ecp.cms.dubbo.interfaces.ICmsPageTypeRSV;
import com.zengshi.ecp.cms.dubbo.interfaces.ICmsSiteRSV;
import com.zengshi.ecp.cms.dubbo.interfaces.ICmsTemplateLibRSV;
import com.zengshi.ecp.cms.dubbo.util.CmsConstants;
import com.zengshi.ecp.server.front.dto.BaseParamDTO;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.server.front.util.BaseParamUtil;
import com.zengshi.paas.utils.ImageUtil;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.ObjectCopyUtil;
import com.zengshi.paas.utils.StringUtil;
import com.alibaba.dubbo.common.utils.CollectionUtils;
import com.alibaba.fastjson.JSONObject;

/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-web-manage <br>
 * Description: 配色管理<br>
 * Date:2016年3月28日下午3:59:40  <br>
 * Copyright (c) 2016 ZengShi All Rights Reserved <br>
 * 
 * @author zhanbh
 * @version  
 * @since JDK 1.6
 */
@Controller
@RequestMapping(value="/pageColor")
public class CmsPageColorController extends EcpBaseController {
    
    private static String MODULE = CmsPageColorController.class.getName();
    
    private static String STAFF_TYPE = "01";//管理员  ：01   卖家 02
    
    private String URL = "/pageConfig/pageColor/pageColor";//返回页面的基本路径 
    
    private String URL_OPEN = "/pageConfig/pageColor/models/pageColor";//返回页面的弹出窗路径
    
    @Resource(name="cmsSiteRSV")
    private ICmsSiteRSV cmsSiteRSV;
    
    @Resource(name = "cmsPageColorRSV")
    private ICmsPageColorRSV cmsPageColorRSV;
    
    @Resource(name = "cmsPageTypeRSV")
    private ICmsPageTypeRSV cmsPageTypeRSV;
    
    @Resource(name = "cmsPageConfigRSV")
    private ICmsPageConfigRSV cmsPageConfigRSV;
    
    @Resource(name = "cmsTemplateLibRSV")
    private ICmsTemplateLibRSV cmsTemplateLibRSV;
    
    /**
     * 
     * init:(页面初始化). <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author zhanbh 
     * @return 
     * @throws Exception 
     * @since JDK 1.6
     */
    @RequestMapping()
    public String init(Model model,@ModelAttribute("searchParams") String searchParams) throws Exception{
        return this.grid(model, searchParams);
    }
    
    /** 
     * grid:(页面管理列表页). <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author zhanbh 
     * @return 
     * @throws Exception 
     * @since JDK 1.6 
     */ 
    @RequestMapping(value="/grid")
    public String grid(Model model,@ModelAttribute("searchParams") String searchParams) throws Exception {
        try {
            model.addAttribute("siteList", this.querySiteList(null));
            
            CmsPageTypeReqDTO pageType = new CmsPageTypeReqDTO();
            pageType.setStatus(CmsConstants.ParamStatus.CMS_PARAMSTATUS_1);
            model.addAttribute("pageTypeList", this.queryPageType(pageType));
        } catch (Exception e) {
            LogUtil.error(MODULE, "查询页面信息：grid 异常", e);
        }
        return URL + "-grid";
    }

    /** 
     * gridList:(查询页面管理列表数据). <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author zhanbh 
     * @param model
     * @param cmsPlaceSearchVO
     * @return
     * @throws Exception 
     * @since JDK 1.6 
     */ 
    @SuppressWarnings("rawtypes")
    @RequestMapping("/gridlist")
    @ResponseBody
    public Model gridList(Model model, @ModelAttribute("searchVO") CmsPageColorVO searchVO) throws Exception{
        LogUtil.info(MODULE,"==========" + JSONObject.toJSONString(searchVO));
        //1. 调用后场服务所需要的DTO；
        CmsPageColorReqDTO reqDTO = searchVO.toBaseInfo(CmsPageColorReqDTO.class);
        ObjectCopyUtil.copyObjValue(searchVO, reqDTO, "MODULE", true);
        
        //2. 调用后场服务，将后场返回的分页对象封装为前店所需要的分页对象；
        PageResponseDTO<CmsPageColorRespDTO> pageColor = this.cmsPageColorRSV.queryCmsPageColorPage(reqDTO);

        if(CollectionUtils.isNotEmpty(pageColor.getResult())){
    		for(CmsPageColorRespDTO resp:pageColor.getResult()){
    			//调文件服务器，返回图片
                if(StringUtils.isNotBlank(resp.getShowPic())){
                    resp.setShowPic(ImageUtil.getImageUrl(resp.getShowPic()+"_"+"50x50!"));
                }else{
                	resp.setShowPic(ImageUtil.getImageUrl(ImageUtil.getDefaultImageId()+"_"+"50x50!"));
                }
    		}
    	}
        if(pageColor!=null){
            this.extendPageColorList(pageColor.getResult());
        }
        EcpBasePageRespVO<Map> respVO = EcpBasePageRespVO.buildByPageResponseDTO(pageColor);
        return super.addPageToModel(model, respVO);
    }
    /** 
     * add:(跳转到新增页面). <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author jiangzh 
     * @param model
     * @return 
     * @since JDK 1.6 
     */ 
    @RequestMapping(value="/add")
    public String add(Model model,@ModelAttribute("searchParams") String searchParams) throws Exception{
        try {
             model.addAttribute("siteList",this.querySiteList(CmsConstants.ParamStatus.CMS_PARAMSTATUS_1));
             
             CmsPageTypeReqDTO pageType = new CmsPageTypeReqDTO();
             pageType.setStatus(CmsConstants.ParamStatus.CMS_PARAMSTATUS_1);
             model.addAttribute("pageTypeList", this.queryPageType(pageType));
        } catch (Exception e) {
            LogUtil.error(MODULE, "添加页面信息：add 异常", e);
        }
       
        return URL+"-edit";
    }
    
    /** 
     * edit:(跳转到编辑页面). <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author jiangzh 
     * @param model
     * @param id
     * @return
     * @throws Exception 
     * @since JDK 1.6 
     */ 
    @RequestMapping(value="/edit")
    public String edit(Model model,@RequestParam("id")String id,@ModelAttribute("searchParams") String searchParams) throws Exception{
        if(StringUtils.isBlank(id)){
            String[] keyColors = new String[1];
            keyColors[0]="id";
           throw new BusinessException("cms.place.param.null.error",keyColors); 
        }
        
        CmsPageColorReqDTO reqDTO = new CmsPageColorReqDTO();
        reqDTO.setId(Long.valueOf(id));
        try {
            CmsPageColorRespDTO pageColor = this.cmsPageColorRSV.queryCmsPageColor(reqDTO);
            model.addAttribute("siteList",this.querySiteList(CmsConstants.ParamStatus.CMS_PARAMSTATUS_1));
            
            CmsPageTypeReqDTO pageType = new CmsPageTypeReqDTO();
            pageType.setStatus(CmsConstants.ParamStatus.CMS_PARAMSTATUS_1);
            model.addAttribute("pageTypeList", this.queryPageType(pageType));
            
            model.addAttribute("respVO",pageColor);
            
        } catch (BusinessException e) {
            LogUtil.error(MODULE, "跳转到编辑页面信息：edit 异常", e);
            throw new BusinessException(e.getErrorCode());
        }
        return URL+"-edit";
    }
    /**
	 * 页面信息查看页面初始化方法 view:(这里用一句话描述这个方法的作用). <br/>
	 * TODO(这里描述这个方法适用条件 – 可选).<br/>
	 * TODO(这里描述这个方法的执行流程 – 可选).<br/>
	 * TODO(这里描述这个方法的使用方法 – 可选).<br/>
	 * TODO(这里描述这个方法的注意事项 – 可选).<br/>
	 * 
	 * @author wenyf
	 * @param model
	 * @param id
	 * @return
	 * @since JDK 1.6
	 */
	@RequestMapping(value = "/view")
	public String view(Model model, 
	        @RequestParam("id") String id,
	        @ModelAttribute("searchParams") String searchParams) {
		LogUtil.info(MODULE, "进入站点查看初始化,入参：{id=" + id + "}");
		
		if (StringUtils.isBlank(id)) {
			String[] keyInfos = new String[1];
			keyInfos[0] = "id";
			throw new BusinessException("cms.common.param.null.error", keyInfos);
		}

		/* 1.根据入参调用后场页面信息查询服务 */
		CmsPageColorReqDTO cmsPageColorReqDTO = new CmsPageColorReqDTO();
		cmsPageColorReqDTO.setId(Long.parseLong(id));
		CmsPageColorRespDTO cmsPageColorRespDTO = this.cmsPageColorRSV.queryCmsPageColor(cmsPageColorReqDTO);
		
		/* 2.设置页面对象 */
		model.addAttribute("respVO", cmsPageColorRespDTO);
		
		/* 4.返回页面路径 */
		return URL + "-view";
	}
	
    /**
     * 
     * queryTemplateLib:(根据id查询模板). <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author IME 
     * @param templateId
     * @return 
     * @since JDK 1.6
     */
    private CmsTemplateLibRespDTO queryTemplateLib(Long templateId) {
        if(StringUtil.isEmpty(templateId)){
            return null;
        }
        CmsTemplateLibRespDTO respDto = null;
        CmsTemplateLibReqDTO reqDto = new CmsTemplateLibReqDTO();
        reqDto.setId(templateId);
        respDto = cmsTemplateLibRSV.queryCmsTemplateLib(reqDto);
        return respDto;
    }

    /** 
     * save:(新增/编辑 保存). <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author zhanbh 
     * @param cmsPlaceVO
     * @return 
     * @since JDK 1.6 
     */ 
    @RequestMapping(value="/save")
    @ResponseBody
    public EcpBaseResponseVO save(@Valid CmsPageColorVO VO) throws Exception{
        LogUtil.info(MODULE,"==========" + JSONObject.toJSONString(VO));
        CmsPageColorReqDTO reqDTO = new CmsPageColorReqDTO();
        ObjectCopyUtil.copyObjValue(VO, reqDTO, null, true);
        
        if(reqDTO.getId() == null&& StringUtil.isBlank(reqDTO.getStatus())){//新增则状态为预览   修改则不动状态
            reqDTO.setStatus(CmsConstants.ParamStatus.CMS_PARAMSTATUS_0);//有效
        }
        
        EcpBaseResponseVO respVO = new EcpBaseResponseVO();
        CmsPageColorRespDTO pageColor = null;
        try {
            if(reqDTO.getId() != null){
                pageColor= this.cmsPageColorRSV.updateCmsPageColor(reqDTO);
            }else{
                pageColor = this.cmsPageColorRSV.addCmsPageColor(reqDTO);
            }
            respVO.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_SUCCESS);
            if(pageColor!=null && StringUtil.isNotEmpty(pageColor.getId())){
                respVO.setResultMsg(pageColor.getId().toString());
            }
        } catch (Exception e) {
            LogUtil.error(MODULE, "保存页面信息：save 异常", e);
            respVO.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_EXCEPTION);
        }
        return respVO;
    }
    /** 
     * changestatus:(失效，撤销，发布). <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author zhanbh 
     * @param VO
     * @return 
     * @since JDK 1.6 
     */ 
    @RequestMapping(value="/changestatus")
    @ResponseBody
    public EcpBaseResponseVO changestatus(CmsPageColorVO VO) throws Exception{
        if(VO == null){
            VO = new CmsPageColorVO();
        }
        LogUtil.info(MODULE,"==========" + JSONObject.toJSONString(VO));
        
        EcpBaseResponseVO respVO = new EcpBaseResponseVO();
        if(StringUtil.isBlank(VO.getStatus()) || StringUtil.isEmpty(VO.getId())){
            respVO.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_FAILURE);
            respVO.setResultMsg("更新失败，入参id为空，或者状态为空！");
            return respVO;
        }
        
        CmsPageColorReqDTO reqDTO = new CmsPageColorReqDTO();
        reqDTO.setId(VO.getId());
        if("1".equalsIgnoreCase(VO.getStatus())){//发布
            try {
            	cmsPageColorRSV.changeStatusCmsPageColor(reqDTO.getId().toString(), CmsConstants.ParamStatus.CMS_PARAMSTATUS_1);
                respVO.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_SUCCESS);
            } catch (Exception e) {
                LogUtil.error(MODULE, "页面发布异常", e);
                respVO.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_FAILURE);
                respVO.setResultMsg("页面发布发生异常，请重试！");
            }
        }else if("0".equalsIgnoreCase(VO.getStatus())){//撤销
            try {
                cmsPageColorRSV.changeStatusCmsPageColor(reqDTO.getId().toString(), CmsConstants.ParamStatus.CMS_PARAMSTATUS_0);
                respVO.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_SUCCESS);
            } catch (Exception e) {
                LogUtil.error(MODULE, "页面撤销异常", e);
                respVO.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_FAILURE);
                respVO.setResultMsg("页面撤销发生异常，请重试！");
            }
        }else if("2".equalsIgnoreCase(VO.getStatus())){//失效
            try {
            	cmsPageColorRSV.changeStatusCmsPageColor(reqDTO.getId().toString(), CmsConstants.ParamStatus.CMS_PARAMSTATUS_2);
                respVO.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_SUCCESS);
            } catch (Exception e) {
                LogUtil.error(MODULE, "页面发布异常", e);
                respVO.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_FAILURE);
                respVO.setResultMsg("页面失效发生异常，请重试！");
            }
        }else{//错误代码
            respVO.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_FAILURE);
            respVO.setResultMsg("更新失败，状态编码错误！");
        }
        
        return respVO;
    }
    /**
     * 
     * extendPageColorList:(扩展页面信息数据列表). <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author zhanbh 
     * @param pageColors 
     * @since JDK 1.6
     */
    private void extendPageColorList (List<CmsPageColorRespDTO> pageColors){
        if(CollectionUtils.isNotEmpty(pageColors)){
            for(CmsPageColorRespDTO pageColor : pageColors){
                this.extendPageColor(pageColor);
            }
        }
    }
    /**
     * 
     * extendPageColor:(扩展页面信息数据). <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author zhanbh 
     * @param pageColor 
     * @since JDK 1.6
     */
    private void extendPageColor (CmsPageColorRespDTO pageColor){
        if(pageColor!=null &&StringUtil.isNotEmpty(pageColor.getId())){
            
        }
    }
    
    /** 
     * querySiteList:(获取站点列表). <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author zhanbh 
     * @param status  //状态，当参数为空时，查询所有
     * @return
     * @throws Exception 
     * @since JDK 1.6 
     */ 
    private List<CmsSiteRespDTO> querySiteList(String status) throws Exception{
        CmsSiteReqDTO cmsSiteReqDTO = new CmsSiteReqDTO();
        if(StringUtils.isNotBlank(status)){
            cmsSiteReqDTO.setStatus(status);
        }
         List<CmsSiteRespDTO> cmsSiteRespDTOList =null;
        try {
            cmsSiteRespDTOList = cmsSiteRSV.queryCmsSiteList(cmsSiteReqDTO);
        } catch (Exception e) {
            LogUtil.error(MODULE, "查询站点异常", e);
            cmsSiteRespDTOList = new ArrayList<CmsSiteRespDTO>();
        }
        
        if(cmsSiteRespDTOList == null){
            cmsSiteRespDTOList = new ArrayList<CmsSiteRespDTO>();
        }
        return cmsSiteRespDTOList;
    }
    
    /**
     * 
     * queryPageType:(查询页面类型). <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author zhanbh 
     * @return 
     * @since JDK 1.6
     */
    private List<CmsPageTypeRespDTO> queryPageType(CmsPageTypeReqDTO reqDto) {
        List<CmsPageTypeRespDTO> pageTypeList = null;
        try {
            pageTypeList = this.cmsPageTypeRSV.queryCmsPageTypeList(reqDto);
        } catch (Exception e) {
            LogUtil.error(MODULE, "查询页面类型异常", e);
            pageTypeList = new ArrayList<CmsPageTypeRespDTO>();
        }
        if(pageTypeList == null){
            pageTypeList = new ArrayList<CmsPageTypeRespDTO>();
        }
        return pageTypeList;
    }
    
    /** 选择页面信息弹出框
     * openpageColor:(这里用一句话描述这个方法的作用). <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author jiangzh 
     * @return 
     * @since JDK 1.6 
     */ 
    @RequestMapping("/openpageColor")
    public String openpageColor(Model model, 
            @ModelAttribute("searchVO") CmsPageTypeReqDTO searchVO,
            @RequestParam("siteId")String siteId){
        model.addAttribute("siteId", siteId);
        CmsPageTypeReqDTO pageType = new CmsPageTypeReqDTO();
        pageType.setStatus(CmsConstants.ParamStatus.CMS_PARAMSTATUS_1);
        model.addAttribute("pageTypeList", this.queryPageType(pageType));
        return URL_OPEN + "-list";
    }
    
    /** 
     * querygds:(这里用一句话描述这个方法的作用). <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author jiangzh 
     * @param model
     * @param ids
     * @return
     * @throws Exception 
     * @since JDK 1.6 
     */ 
    @SuppressWarnings("rawtypes")
    @RequestMapping("/querypageColor")
    public Model querypageColor(Model model, 
            @ModelAttribute("searchVO") CmsPageColorVO searchVO,
            @RequestParam("siteId")String siteId) throws Exception{
        LogUtil.info(MODULE,"==========" + JSONObject.toJSONString(searchVO));
        //1. 调用后场服务所需要的DTO；
        CmsPageColorReqDTO reqDTO = searchVO.toBaseInfo(CmsPageColorReqDTO.class);
        ObjectCopyUtil.copyObjValue(searchVO, reqDTO, "MODULE", true);
        PageResponseDTO<CmsPageColorRespDTO> pageColor = cmsPageColorRSV.queryCmsPageColorPage(reqDTO);
        if(pageColor != null){
            this.extendPageColorList(pageColor.getResult());
        } 
        
        EcpBasePageRespVO<Map> respVO = EcpBasePageRespVO.buildByPageResponseDTO(pageColor);
        
        //2. 调用后场服务，将后场返回的分页对象封装为前店所需要的分页对象；
        return super.addPageToModel(model, respVO);
    }
    
    /**
     * 
     * getTempType:(根据角色返回不同的模板类型). <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author zhanbh 
     * @param VO
     * @return
     * @throws Exception 
     * @since JDK 1.6
     */
    @RequestMapping(value="/getTempType")
    @ResponseBody
    public Map<String,Object> getTempType(Model model) throws Exception{
        LogUtil.info(MODULE,"==========");
        Map<String,Object> returnMap = new HashMap<String, Object>();
        
        try{
            
            List<BaseParamDTO> tempTypeList =  BaseParamUtil.fetchParamList(CmsConstants.ParamConfig.CMS_TEMPLATE_TYPE);
        
            //根据权限过滤掉对应的模板类型  
            if(CollectionUtils.isNotEmpty(tempTypeList)){
                if("01".equalsIgnoreCase(STAFF_TYPE)){//管理员
                    
                }
                else if("02".equalsIgnoreCase(STAFF_TYPE)){//卖家
                    
                }
                
            }
            
            returnMap.put("resultFlag", "ok");
            returnMap.put("tempTypes", tempTypeList);
            
        } catch (Exception e) {
            returnMap.put("resultFlag", "exception");
            LogUtil.error(MODULE, "查询模板类型失败");
        }
        
        return returnMap;
    }
    
}

