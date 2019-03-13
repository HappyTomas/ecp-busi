package com.zengshi.ecp.busi.pageConfig.pageInfo.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
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
import com.zengshi.ecp.busi.pageConfig.pageInfo.vo.CmsPageInfoVO;
import com.zengshi.ecp.cms.dubbo.dto.CmsPageInfoReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsPageInfoRespDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsPageTypeReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsPageTypeRespDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsSiteReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsSiteRespDTO;
import com.zengshi.ecp.cms.dubbo.interfaces.ICmsPageConfigRSV;
import com.zengshi.ecp.cms.dubbo.interfaces.ICmsPageInfoRSV;
import com.zengshi.ecp.cms.dubbo.interfaces.ICmsPageTypeRSV;
import com.zengshi.ecp.cms.dubbo.interfaces.ICmsSiteRSV;
import com.zengshi.ecp.cms.dubbo.interfaces.ICmsTemplateLibRSV;
import com.zengshi.ecp.cms.dubbo.util.CmsCacheUtil;
import com.zengshi.ecp.cms.dubbo.util.CmsConstants;
import com.zengshi.ecp.cms.dubbo.util.CmsConstants.ParamStatus;
import com.zengshi.ecp.server.front.dto.BaseParamDTO;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.server.front.util.BaseParamUtil;
import com.zengshi.ecp.staff.dubbo.dto.ShopInfoResDTO;
import com.zengshi.ecp.staff.dubbo.interfaces.IShopInfoRSV;
import com.zengshi.ecp.system.util.ConstantTool;
import com.zengshi.ecp.system.util.MatrixToImageWriter;
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
 * Description: 模板化 页面管理<br>
 * Date:2016年3月28日下午3:59:40  <br>
 * Copyright (c) 2016 ZengShi All Rights Reserved <br>
 * 
 * @author zhanbh
 * @version  
 * @since JDK 1.6
 */
@Controller
@RequestMapping(value="/pageInfo")
public class CmsPageInfoController extends EcpBaseController {
    
    private static String MODULE = CmsPageInfoController.class.getName();
    
    private String RELEASE_URL_INDEX="/modularcommon/prom/";
    
    private String URL = "/pageConfig/pageInfo/pageInfo";//返回页面的基本路径 
    
    private String URL_OPEN = "/pageConfig/pageInfo/models/pageInfo";//返回页面的弹出窗路径
    
    @Resource(name="cmsSiteRSV")
    private ICmsSiteRSV cmsSiteRSV;
    @Resource(name = "cmsPageInfoRSV")
    private ICmsPageInfoRSV cmsPageInfoRSV;
    @Resource(name = "cmsPageTypeRSV")
    private ICmsPageTypeRSV cmsPageTypeRSV;
    @Resource(name = "cmsPageConfigRSV")
    private ICmsPageConfigRSV cmsPageConfigRSV;
    @Resource(name = "cmsTemplateLibRSV")
    private ICmsTemplateLibRSV cmsTemplateLibRSV;
    @Resource(name="shopInfoRSV")
    private IShopInfoRSV shopInfoRSV;
    
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
    public Model gridList(Model model, @ModelAttribute("searchVO") CmsPageInfoVO searchVO) throws Exception{
        LogUtil.info(MODULE,"==========" + JSONObject.toJSONString(searchVO));
        //1. 调用后场服务所需要的DTO；
        CmsPageInfoReqDTO reqDTO = searchVO.toBaseInfo(CmsPageInfoReqDTO.class);
        ObjectCopyUtil.copyObjValue(searchVO, reqDTO, "MODULE", true);
        
        //2. 调用后场服务，将后场返回的分页对象封装为前店所需要的分页对象；
        PageResponseDTO<CmsPageInfoRespDTO> pageInfo = this.cmsPageInfoRSV.queryCmsPageInfoPage(reqDTO);
            
        if(pageInfo!=null){
            this.extendPageInfoList(pageInfo.getResult());
        }
        EcpBasePageRespVO<Map> respVO = EcpBasePageRespVO.buildByPageResponseDTO(pageInfo);
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
            String[] keyInfos = new String[1];
            keyInfos[0]="id";
           throw new BusinessException("cms.place.param.null.error",keyInfos); 
        }
        
        CmsPageInfoReqDTO reqDTO = new CmsPageInfoReqDTO();
        reqDTO.setId(Long.valueOf(id));
        try {
            CmsPageInfoRespDTO pageInfo = this.cmsPageInfoRSV.queryCmsPageInfo(reqDTO);
            model.addAttribute("siteList",this.querySiteList(CmsConstants.ParamStatus.CMS_PARAMSTATUS_1));
            
            CmsPageTypeReqDTO pageType = new CmsPageTypeReqDTO();
            pageType.setStatus(CmsConstants.ParamStatus.CMS_PARAMSTATUS_1);
            model.addAttribute("pageTypeList", this.queryPageType(pageType));
            
            model.addAttribute("respVO",pageInfo);
        } catch (BusinessException e) {
            LogUtil.error(MODULE, "跳转到编辑页面信息：edit 异常", e);
            throw new BusinessException(e.getErrorCode());
        }
        //如果是卖家操作的
        return URL+"-edit";
        
    }
    /** 
     * toUseTemp:(跳转到模板应用页面). <br/> 
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
    @RequestMapping(value="/toUseTemp")
    public String toUseTemp(Model model,@RequestParam("pageId")String pageId,@ModelAttribute("searchParams") String searchParams,@ModelAttribute("mallskintomanage") String mallskintomanage) throws Exception{
        if(StringUtils.isBlank(pageId)){
            String[] keyInfos = new String[1];
            keyInfos[0]="pageId";
           throw new BusinessException("cms.place.param.null.error",keyInfos); 
        }
        
        CmsPageInfoReqDTO reqDTO = new CmsPageInfoReqDTO();
        reqDTO.setId(Long.valueOf(pageId));
        try {
            CmsPageInfoRespDTO pageInfo = this.cmsPageInfoRSV.queryCmsPageInfo(reqDTO);
            model.addAttribute("respVO",pageInfo);
        } catch (BusinessException e) {
            LogUtil.error(MODULE, "跳转到模板应用页面：toUseTemp 异常", e);
            throw new BusinessException(e.getErrorCode());
        }
        
        return URL+"-temp-grid";
        
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
    public EcpBaseResponseVO save(@Valid CmsPageInfoVO VO, HttpServletRequest request) throws Exception{
        LogUtil.info(MODULE,"==========" + JSONObject.toJSONString(VO));
        CmsPageInfoReqDTO reqDTO = new CmsPageInfoReqDTO();
        ObjectCopyUtil.copyObjValue(VO, reqDTO, null, true);
        
        EcpBaseResponseVO respVO = new EcpBaseResponseVO();
        CmsPageInfoRespDTO pageInfo = null;
        try {
            if(reqDTO.getId() != null){//修改
                pageInfo= this.cmsPageInfoRSV.updateCmsPageInfo(reqDTO);
            }else{//新增  如果制定了模板  则调用从模板继承的方法
                reqDTO.setStatus(CmsConstants.ParamStatus.CMS_PARAMSTATUS_0);//新增则状态为预览   修改则不动状态
                
                if(StringUtil.isNotEmpty(reqDTO.getTemplateId())){
                    pageInfo = cmsPageConfigRSV.saveTemplateToPageConfig(reqDTO);
                }else{
                    pageInfo = this.cmsPageInfoRSV.addCmsPageInfo(reqDTO);
                }
                if(CmsConstants.PlatformType.CMS_PLATFORMTYPE_03.equals(reqDTO.getPlatformType())){
	                String qrcodeUrl=	CmsCacheUtil.getCmsSiteCache(8L).getSiteUrl()+"/page-pre/withoutLogin?pageId="+pageInfo.getId();
	    			String qrcodePic=this.getQRCode(qrcodeUrl, request,false);
	    			CmsPageInfoReqDTO reqDTOWap = new CmsPageInfoReqDTO();
	    		    ObjectCopyUtil.copyObjValue(pageInfo, reqDTOWap, null, true);
	    		    reqDTOWap.setViewQrcodePic(qrcodePic);
	    		    reqDTOWap.setId(pageInfo.getId());
	    			cmsPageInfoRSV.updateCmsPageInfo(reqDTOWap);
                }
            }
            respVO.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_SUCCESS);
            if(pageInfo!=null && StringUtil.isNotEmpty(pageInfo.getId())){
                respVO.setResultMsg(pageInfo.getId().toString());
            }
        } catch (Exception e) {
            LogUtil.error(MODULE, "保存页面信息：save 异常", e);
            respVO.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_EXCEPTION);
        }
        return respVO;
    }
    
    /** 
     * changePageTemp:(改变页面的模板). <br/> 
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
    @RequestMapping(value="/changePageTemp")
    @ResponseBody
    public EcpBaseResponseVO changePageTemp(CmsPageInfoVO VO) throws Exception{
        LogUtil.info(MODULE,"==========" + JSONObject.toJSONString(VO));
        CmsPageInfoReqDTO reqDTO = new CmsPageInfoReqDTO();
        ObjectCopyUtil.copyObjValue(VO, reqDTO, null, true);
        
        EcpBaseResponseVO respVO = new EcpBaseResponseVO();
        CmsPageInfoRespDTO pageInfo = null;
        
        //参数不对处理  必须页面id与模板id都具备
        if(StringUtil.isEmpty(reqDTO.getId()) || StringUtil.isEmpty(reqDTO.getTemplateId())){
            LogUtil.error(MODULE, "改变页面的模板参数错误：changePageTemp 错误");
            respVO.setResultMsg("使用模板进行覆盖参数错误！");
            respVO.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_FAILURE);
            return respVO;
        }
            
        try {
            pageInfo = cmsPageConfigRSV.saveTemplateToPageConfig(reqDTO);
            respVO.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_SUCCESS);
            if(pageInfo!=null && StringUtil.isNotEmpty(pageInfo.getId())){
                respVO.setResultMsg(pageInfo.getId().toString());
            }
        } catch (Exception e) {
            LogUtil.error(MODULE, "改变页面的模板：changePageTemp 异常", e);
            respVO.setResultMsg("使用模板进行覆盖发生异常！");
            respVO.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_EXCEPTION);
        }
        return respVO;
    }
    
    /** 
     * changePlatformType:(根据平台类型取页面类型集合 ajax). <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author jiangzh 
     * @param CmsPageTypeReqDTO
     * @return
     * @throws Exception 
     * @since JDK 1.6 
     */ 
    @RequestMapping(value="/changePlatformType")
    @ResponseBody
    public List<CmsPageTypeRespDTO> changePlatformType(Model model,CmsPageTypeReqDTO reqDTO) throws Exception{
        LogUtil.info(MODULE,"==========reqDTO.platformType:"+reqDTO.getPlatformType()+";");
        return this.cmsPageTypeRSV.queryCmsPageTypeList(reqDTO);
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
    public EcpBaseResponseVO changestatus(CmsPageInfoVO VO,HttpServletRequest request) throws Exception{
        if(VO == null){
            VO = new CmsPageInfoVO();
        }
        LogUtil.info(MODULE,"==========" + JSONObject.toJSONString(VO));
        
        EcpBaseResponseVO respVO = new EcpBaseResponseVO();
        if(StringUtil.isBlank(VO.getStatus()) || StringUtil.isEmpty(VO.getId())){
            respVO.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_FAILURE);
            respVO.setResultMsg("更新失败，入参id为空，或者状态为空！");
            return respVO;
        }
        CmsPageInfoReqDTO reqDTO = new CmsPageInfoReqDTO();
        reqDTO.setId(VO.getId());
        if("1".equalsIgnoreCase(VO.getStatus())){//发布
            reqDTO.setStatus(CmsConstants.ParamStatus.CMS_PARAMSTATUS_1);
            try {
            	CmsPageInfoReqDTO req = new CmsPageInfoReqDTO();
        		String qrcodeUrl=null;
        		String qrcodePic=null;
        		req.setId(VO.getId());
        		CmsPageInfoRespDTO pageInfoRespDto=this.cmsPageInfoRSV.queryCmsPageInfo(req);
            	//wap 生成二维码图片
        		if(CmsConstants.PlatformType.CMS_PLATFORMTYPE_03.equals(pageInfoRespDto.getPlatformType())){
        			String lastIndexUrl="/homepage";
        			if(50L==pageInfoRespDto.getPageTypeId()){
        				lastIndexUrl="/homepage";
        			}else if(51L==pageInfoRespDto.getPageTypeId()){
        				lastIndexUrl=RELEASE_URL_INDEX+pageInfoRespDto.getId();
        			}
        			qrcodeUrl= BaseParamUtil.fetchParamValue(CmsConstants.ParamConfig.CMS_SITE_MAPPING, String.valueOf(pageInfoRespDto.getSiteId()))+lastIndexUrl;  
        			qrcodePic=this.getQRCode(qrcodeUrl, request,true);
        			req.setQrcodePic(qrcodePic);
        			req.setPageTypeId(pageInfoRespDto.getPageTypeId());
        			qrcodePic=ImageUtil.getImageUrl(qrcodePic+"_"+"250x250!");
        		}else if(CmsConstants.PlatformType.CMS_PLATFORMTYPE_01.equals(pageInfoRespDto.getPlatformType())){
        			req.setSiteUrl(RELEASE_URL_INDEX+req.getId());
        		}
        		boolean result = cmsPageConfigRSV.savePageConfigPub(req);
        		if(result){
        			respVO.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_SUCCESS);
        			if(null!=qrcodePic){
        				List<String> list=new ArrayList<>();
        				list.add(qrcodeUrl);
        				list.add(qrcodePic);
        				respVO.setResultMsg(JSONObject.toJSONString(list));
        			}
        		}else{
        			respVO.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_FAILURE);
    			    respVO.setResultMsg("页面发布失败，请重试！");
        		}
            } catch (Exception e) {
                LogUtil.error(MODULE, "页面发布异常", e);
                respVO.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_FAILURE);
                respVO.setResultMsg("页面发布发生异常，请重试！");
            }
        }else if("0".equalsIgnoreCase(VO.getStatus())){//撤销
            try {
                cmsPageInfoRSV.changeStatusCmsPageInfo(reqDTO.getId().toString(), CmsConstants.ParamStatus.CMS_PARAMSTATUS_0);
                respVO.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_SUCCESS);
            } catch (Exception e) {
                LogUtil.error(MODULE, "页面撤销异常", e);
                respVO.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_FAILURE);
                respVO.setResultMsg("页面撤销发生异常，请重试！");
            }
        }else if("2".equalsIgnoreCase(VO.getStatus())){//失效
            reqDTO.setStatus(CmsConstants.ParamStatus.CMS_PARAMSTATUS_2);
            try {
                if(cmsPageConfigRSV.deletePageInfo(reqDTO)){
                    respVO.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_SUCCESS);
                }else{
                    respVO.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_FAILURE);
                    respVO.setResultMsg("页面失效失败，请重试！");
                }
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
     * extendPageInfoList:(扩展页面信息数据列表). <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author zhanbh 
     * @param pageInfos 
     * @since JDK 1.6
     */
    private void extendPageInfoList (List<CmsPageInfoRespDTO> pageInfos){
        if(CollectionUtils.isNotEmpty(pageInfos)){
            for(CmsPageInfoRespDTO pageInfo : pageInfos){
                this.extendPageInfo(pageInfo);
            }
        }
    }

    /**
     * 
     * extendPageInfo:(扩展页面信息数据). <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author zhanbh 
     * @param pageInfo 
     * @since JDK 1.6
     */
    private void extendPageInfo (CmsPageInfoRespDTO pageInfo){
        if(pageInfo!=null &&StringUtil.isNotEmpty(pageInfo.getId())){
            
            //扩展页面类型
            if(StringUtil.isNotEmpty(pageInfo.getPageTypeId())){
                List<CmsPageTypeRespDTO> pageTypes = null;
                CmsPageTypeReqDTO reqDto = new CmsPageTypeReqDTO();
                reqDto.setId(pageInfo.getPageTypeId());
                pageTypes = this.queryPageType(reqDto);
                if(CollectionUtils.isNotEmpty(pageTypes)){
                    pageInfo.setPageTypeZH(pageTypes.get(0).getPageTypeName());
                }
            }
            //调用店铺，返回店铺信息
            if(pageInfo.getShopId() != null){
                ShopInfoResDTO shopInfoRespDTO = shopInfoRSV.findShopInfoByShopID(pageInfo.getShopId());
                if(shopInfoRespDTO != null){
                    pageInfo.setShopName(shopInfoRespDTO.getShopName());
                } 
            }
            //已发布二维码图片
            if(StringUtil.isNotEmpty(pageInfo.getQrcodePic())){
            	String qrcodePic=ImageUtil.getImageUrl(pageInfo.getQrcodePic()+"_"+"250x250!");
            	String lastIndexUrl="/homepage";
    			if(50L==pageInfo.getPageTypeId()){
    				lastIndexUrl="/homepage";
    			}else if(51L==pageInfo.getPageTypeId()){
    				lastIndexUrl=RELEASE_URL_INDEX+pageInfo.getId();
    			}
            	String qrcodeUrl= BaseParamUtil.fetchParamValue(CmsConstants.ParamConfig.CMS_SITE_MAPPING, String.valueOf(pageInfo.getSiteId()))+lastIndexUrl;  
            	pageInfo.setQrcodePic(qrcodePic);
            	pageInfo.setQrcodePicUrl(qrcodeUrl);
            }
            //预览二维码图片
            if(StringUtil.isNotEmpty(pageInfo.getViewQrcodePic())){
            	String qrcodePic=ImageUtil.getImageUrl(pageInfo.getViewQrcodePic()+"_"+"250x250!");
            	String qrcodeUrl=	CmsCacheUtil.getCmsSiteCache(pageInfo.getSiteId()).getSiteUrl()+"/page-pre/init?pageId="+pageInfo.getId();
            	pageInfo.setViewQrcodePic(qrcodePic);
            	pageInfo.setViewQrcodePicUrl(qrcodeUrl);
            }
//            if(CmsConstants.ParamStatus.CMS_PARAMSTATUS_1.equals(pageInfo.getStatus())&&CmsConstants.PlatformType.CMS_PLATFORMTYPE_01.equals(pageInfo.getPlatformType())){
//            	String siteUrl=CmsCacheUtil.getCmsSiteCache(pageInfo.getSiteId()).getSiteUrl()+pageInfo.getSiteUrl();
//            	pageInfo.setSiteUrl(siteUrl);
//            }
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
     * openpageinfo:(这里用一句话描述这个方法的作用). <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author jiangzh 
     * @return 
     * @since JDK 1.6 
     */ 
    @RequestMapping("/openpageinfo")
    public String openpageinfo(Model model, 
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
    @RequestMapping("/querypageinfo")
    public Model querypageinfo(Model model, 
            @ModelAttribute("searchVO") CmsPageInfoVO searchVO,
            @RequestParam("siteId")String siteId) throws Exception{
        LogUtil.info(MODULE,"==========" + JSONObject.toJSONString(searchVO));
        //1. 调用后场服务所需要的DTO；
        CmsPageInfoReqDTO reqDTO = searchVO.toBaseInfo(CmsPageInfoReqDTO.class);
        ObjectCopyUtil.copyObjValue(searchVO, reqDTO, "MODULE", true);
        PageResponseDTO<CmsPageInfoRespDTO> pageInfo = cmsPageInfoRSV.queryCmsPageInfoPage(reqDTO);
        if(pageInfo != null){
            this.extendPageInfoList(pageInfo.getResult());
        } 
        
        EcpBasePageRespVO<Map> respVO = EcpBasePageRespVO.buildByPageResponseDTO(pageInfo);
        
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
    public Map<String,Object> getTempType(Model model, HttpServletRequest request) throws Exception{
        LogUtil.info(MODULE,"==========");
        
        Long shopId=(Long) request.getSession().getAttribute("shopfishing_");//把店铺id放到session里面
        
        Map<String,Object> returnMap = new HashMap<String, Object>();
        
        List<BaseParamDTO> tempTypeList = null;
        try{
            tempTypeList =  BaseParamUtil.fetchParamList(CmsConstants.ParamConfig.CMS_TEMPLATE_TYPE);
        } catch (Exception e) {
            returnMap.put("resultFlag", "exception");
            LogUtil.error(MODULE, "查询模板类型失败");
        }
        //根据权限过滤掉对应的模板类型   管理员只返回系统模板，卖家返回系统模板与我的模板
        if(CollectionUtils.isNotEmpty(tempTypeList)){
            if(StringUtil.isEmpty(shopId)){//管理员
                for(int i = 0 ;i < tempTypeList.size();i++){
                    BaseParamDTO dto = tempTypeList.get(i);
                    if(CmsConstants.TemplateType.CMS_TEMPLATE_TYPE_SHOP.equalsIgnoreCase(dto.getSpCode())){
                        tempTypeList.remove(i);
                        i--;
                    }
                }
            }
        }
        
        returnMap.put("resultFlag", "ok");
        returnMap.put("tempTypes", tempTypeList);
            
        return returnMap;
    }
    /**
     * 
     * hasOtherRelease:(是否存在已发布的其他页面). <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author yedw 
     * @return 
     * @throws Exception 
     * @since JDK 1.6
     */
    @RequestMapping("/hasOtherRelease")
    @ResponseBody
    public EcpBaseResponseVO hasOtherRelease(Model model,CmsPageInfoVO pageInfoVO) throws Exception{
    	EcpBaseResponseVO ecpBaseResponseVO = new EcpBaseResponseVO();
        try {
            CmsPageInfoReqDTO reqDTO=new CmsPageInfoReqDTO();
            List<CmsPageInfoRespDTO> list = null;
            if(1L == pageInfoVO.getPageTypeId()){//店铺首页
                reqDTO.setShopId(pageInfoVO.getShopId());
                reqDTO.setPageTypeId(pageInfoVO.getPageTypeId());
                reqDTO.setStatus(ParamStatus.CMS_PARAMSTATUS_1);
                list= this.cmsPageInfoRSV.queryCmsPageInfoList(reqDTO);
            }else if(50L == pageInfoVO.getPageTypeId()){//微信首页
                reqDTO.setPageTypeId(pageInfoVO.getPageTypeId());
                reqDTO.setPlatformType(pageInfoVO.getPlatformType());
                reqDTO.setSiteId(pageInfoVO.getSiteId());
                reqDTO.setStatus(ParamStatus.CMS_PARAMSTATUS_1);
                list= this.cmsPageInfoRSV.queryCmsPageInfoList(reqDTO);
            }
        	if(list==null||list.size()==0){
        		ecpBaseResponseVO.setResultFlag("no ok");
        	}else{
        		boolean tag=false;
        		for (CmsPageInfoRespDTO respDto: list) {
					if(!respDto.getId().equals(pageInfoVO.getId())){
						tag=true;
						break;
					}
				}
        		if(tag){
        			ecpBaseResponseVO.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_SUCCESS);  
        		}else{
        			ecpBaseResponseVO.setResultFlag("no ok");
        		}
        	}
          
        } catch (BusinessException e) {
            LogUtil.error(MODULE, "判断是否存在已发布的其他店铺首页操作失败！", e);
            ecpBaseResponseVO.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_FAILURE);
            ecpBaseResponseVO.setResultMsg("操作失败！");
        } catch (Exception e) {
        	LogUtil.error(MODULE, "判断是否存在已发布的其他店铺首页操作失败！", e);
            ecpBaseResponseVO.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_FAILURE);
            ecpBaseResponseVO.setResultMsg("操作失败！");
        }
        return ecpBaseResponseVO;
    }
    /**
     * 二维码图片
     * @param url
     * @param request
     * @return
     */
    @SuppressWarnings({ "deprecation" })
	private String getQRCode(String url, HttpServletRequest request,boolean wechat) {
    	if(wechat){
    		String appId=BaseParamUtil.fetchParamValue(CmsConstants.ParamConfig.WECHAT_QRCODE_CONFIG,CmsConstants.ParamConfig.WECHAT_QRCODE_CONFIG_APP_ID);
    		String response_type=BaseParamUtil.fetchParamValue(CmsConstants.ParamConfig.WECHAT_QRCODE_CONFIG,CmsConstants.ParamConfig.WECHAT_QRCODE_CONFIG_RESPONSE_TYPE);
    		String scope=BaseParamUtil.fetchParamValue(CmsConstants.ParamConfig.WECHAT_QRCODE_CONFIG,CmsConstants.ParamConfig.WECHAT_QRCODE_CONFIG_SCOPE);
    		String state=BaseParamUtil.fetchParamValue(CmsConstants.ParamConfig.WECHAT_QRCODE_CONFIG,CmsConstants.ParamConfig.WECHAT_QRCODE_CONFIG_STATE);
    		String connect_redirect=BaseParamUtil.fetchParamValue(CmsConstants.ParamConfig.WECHAT_QRCODE_CONFIG,CmsConstants.ParamConfig.WECHAT_QRCODE_CONFIG_CONNECT_REDIRECT);
    		url="https://open.weixin.qq.com/connect/oauth2/authorize?appid="+appId+"&redirect_uri="+url
    				+"&response_type="+response_type+"&scope="+scope+"&state="+state+"&connect_redirect="+connect_redirect;
    	}
		try {
			File imageTempFile = new File(request.getRealPath("/")
					+ File.separator + "pageconfig_qrcode.jpg");
			MatrixToImageWriter.encode(url, 300, 300, imageTempFile);
			InputStream in = new FileInputStream(imageTempFile);
			byte[] datas = ConstantTool.inputStream2Bytes(in);
			String vfsId = ImageUtil.upLoadImage(datas, "image");
			imageTempFile.delete();
			return vfsId;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}

