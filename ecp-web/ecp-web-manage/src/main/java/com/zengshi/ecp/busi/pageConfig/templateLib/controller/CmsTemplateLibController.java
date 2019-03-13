package com.zengshi.ecp.busi.pageConfig.templateLib.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
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
import com.zengshi.ecp.busi.pageConfig.templateLib.vo.CmsTemplateLibVO;
import com.zengshi.ecp.cms.dubbo.dto.CmsPageInfoRespDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsPageTypeReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsPageTypeRespDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsSiteReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsSiteRespDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsTemplateLibReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsTemplateLibRespDTO;
import com.zengshi.ecp.cms.dubbo.interfaces.ICmsPageTypeRSV;
import com.zengshi.ecp.cms.dubbo.interfaces.ICmsSiteRSV;
import com.zengshi.ecp.cms.dubbo.interfaces.ICmsTemplateLibRSV;
import com.zengshi.ecp.cms.dubbo.util.CmsConstants;
import com.zengshi.ecp.cms.dubbo.util.CmsConstants.ParamStatus;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.staff.dubbo.util.StaffConstants;
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
 * Description: 模板化 模板库管理<br>
 * Date:2016年3月28日下午3:59:40  <br>
 * Copyright (c) 2016 ZengShi All Rights Reserved <br>
 * 
 * @author jiangzh
 * @version  
 * @since JDK 1.6
 */
@Controller
@RequestMapping(value="/templateLib")
public class CmsTemplateLibController extends EcpBaseController {
    
    private static String MODULE = CmsTemplateLibController.class.getName();
    
    private String URL = "/pageConfig/templateLib/templateLib";//返回页面的基本路径 
    
    @Resource(name="cmsSiteRSV")
    private ICmsSiteRSV cmsSiteRSV;
    
    @Resource(name = "cmsTemplateLibRSV")
    private ICmsTemplateLibRSV cmsTemplateLibRSV;
    
    @Resource(name = "cmsPageTypeRSV")
    private ICmsPageTypeRSV cmsPageTypeRSV;
    
    /**
     * 
     * init:(模板初始化). <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author jiangzh 
     * @return 
     * @throws Exception 
     * @since JDK 1.6
     */
    @RequestMapping()
    public String init(Model model,@ModelAttribute("searchParams") String searchParams) throws Exception{
        return this.grid(model, searchParams);
    }
    
    /** 
     * grid:(模板库管理列表页). <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author jiangzh 
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
            LogUtil.error(MODULE, "查询模板库：grid 异常", e);
        }
        return URL + "-grid";
    }
    
    /**
     * sameTemplateName:(可以撤销判断). <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author zhuqr 
     * @return 
     * @throws Exception 
     * @since JDK 1.6
     */
    @RequestMapping("/sameTemplateName")
    @ResponseBody
    public EcpBaseResponseVO sameTemplateName(Model model,CmsTemplateLibVO cmsTemplateLibVO) throws Exception{
        EcpBaseResponseVO ecpBaseResponseVO = new EcpBaseResponseVO();
        try {
            CmsTemplateLibReqDTO req=new CmsTemplateLibReqDTO();
            req.setId(cmsTemplateLibVO.getId());
            req.setTemplateName(cmsTemplateLibVO.getTemplateName());
            req.setStatusSet(new HashSet<String>(Arrays.asList(ParamStatus.CMS_PARAMSTATUS_0,ParamStatus.CMS_PARAMSTATUS_1)));
            boolean isExit=cmsTemplateLibRSV.isExistTemplateLib(req);
            if(isExit){
                ecpBaseResponseVO.setResultFlag("no ok");
            }else{
                ecpBaseResponseVO.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_SUCCESS);  
            }
          
        } catch (BusinessException e) {
            LogUtil.error(MODULE, "操作失败！", e);
            ecpBaseResponseVO.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_FAILURE);
            ecpBaseResponseVO.setResultMsg("操作失败！");
        } catch (Exception e) {
            LogUtil.error(MODULE, "操作失败！", e);
            ecpBaseResponseVO.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_FAILURE);
            ecpBaseResponseVO.setResultMsg("操作失败！");
        }
        return ecpBaseResponseVO;
    }

    /** 
     * gridList:(查询模板库列表数据). <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author jiangzh 
     * @param model
     * @param cmsPlaceSearchVO
     * @return
     * @throws Exception 
     * @since JDK 1.6 
     */ 
    @SuppressWarnings("rawtypes")
    @RequestMapping("/gridlist")
    @ResponseBody
    public Model gridList(Model model, @ModelAttribute("searchVO") CmsTemplateLibVO searchVO) throws Exception{
        LogUtil.info(MODULE,"==========" + JSONObject.toJSONString(searchVO));
        //1. 调用后场服务所需要的DTO；
        CmsTemplateLibReqDTO reqDTO = searchVO.toBaseInfo(CmsTemplateLibReqDTO.class);
        ObjectCopyUtil.copyObjValue(searchVO, reqDTO, "MODULE", true);
        
        //2. 调用后场服务，将后场返回的分页对象封装为前店所需要的分页对象；
        PageResponseDTO<CmsTemplateLibRespDTO> pageInfo = this.cmsTemplateLibRSV.queryCmsTemplateLibPage(reqDTO);
            
        if(pageInfo!=null){
            this.extendTemplateLibList(pageInfo.getResult(),null);
        }
        EcpBasePageRespVO<Map> respVO = EcpBasePageRespVO.buildByPageResponseDTO(pageInfo);
        return super.addPageToModel(model, respVO);
    }
    
    /**
     * 
     * extendTemplateLibList:(扩展模板库数据列表). <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author jiangzh 
     * @param pageInfos 
     * @since JDK 1.6
     */
    private void extendTemplateLibList (List<CmsTemplateLibRespDTO> pageInfos,String picLeng){
        if(CollectionUtils.isNotEmpty(pageInfos)){
            for(CmsTemplateLibRespDTO pageInfo : pageInfos){
                this.extendTemplateLib(pageInfo,picLeng);
            }
        }
    }
    
    /**
     * 
     * extendPageInfo:(扩展模板库数据). <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author jiangzh 
     * @param pageInfo 
     * @param picLeng 图片规格，为空则为120x50!
     * @since JDK 1.6
     */
    private void extendTemplateLib (CmsTemplateLibRespDTO pageInfo,String picLeng){
        if(StringUtil.isBlank(picLeng)){
            picLeng="120x50!";
        }
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
            //调文件服务器，返回图片
            if(StringUtils.isNotBlank(pageInfo.getShowPic())){
                pageInfo.setShowPic(ImageUtil.getImageUrl(pageInfo.getShowPic()+"_"+picLeng));
            }else{
                pageInfo.setShowPic(ImageUtil.getImageUrl(ImageUtil.getDefaultImageId()+"_"+picLeng));
            }
        }
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
             CmsPageTypeReqDTO pageType = new CmsPageTypeReqDTO();
			if (pageType.getStaff().getStaffClass().equals(StaffConstants.authStaff.STAFF_CLASS_M)) {
				CmsTemplateLibRespDTO pageInfo = new CmsTemplateLibRespDTO();
				model.addAttribute("isManage", true);
				pageInfo.setTemplateType(CmsConstants.TemplateType.CMS_TEMPLATE_TYPE_SYS);
				model.addAttribute("respVO", pageInfo);
			}
             pageType.setStatus(CmsConstants.ParamStatus.CMS_PARAMSTATUS_1);
             model.addAttribute("pageTypeList", this.queryPageType(pageType));
        } catch (Exception e) {
            LogUtil.error(MODULE, "添加模板信息：add 异常", e);
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
    public String edit(Model model,
            @RequestParam("id")String id,
            @RequestParam("reqType")String reqType,
            @ModelAttribute("searchParams") String searchParams) throws Exception{
        
        model.addAttribute("reqType",reqType);
        if(StringUtils.isBlank(id)){
            String[] keyInfos = new String[1];
            keyInfos[0]="id";
           throw new BusinessException("cms.place.param.null.error",keyInfos); 
        }
        
        CmsTemplateLibReqDTO reqDTO = new CmsTemplateLibReqDTO();
        reqDTO.setId(Long.valueOf(id));
        try {
            CmsTemplateLibRespDTO pageInfo = this.cmsTemplateLibRSV.queryCmsTemplateLib(reqDTO);
            if(reqDTO.getStaff().getStaffClass().equals(StaffConstants.authStaff.STAFF_CLASS_M)){
            	model.addAttribute("isManage",true);
            	if(StringUtil.isBlank(pageInfo.getTemplateType())){
            		pageInfo.setTemplateType(CmsConstants.TemplateType.CMS_TEMPLATE_TYPE_SYS);
            	}
            }
            CmsPageTypeReqDTO pageType = new CmsPageTypeReqDTO();
            pageType.setStatus(CmsConstants.ParamStatus.CMS_PARAMSTATUS_1);
            model.addAttribute("pageTypeList", this.queryPageType(pageType));
            model.addAttribute("respVO",pageInfo);
        } catch (BusinessException e) {
            LogUtil.error(MODULE, "跳转到编辑模板信息：edit 异常", e);
            throw new BusinessException(e.getErrorCode());
        }
        return URL+"-edit";
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
    public EcpBaseResponseVO save(@Valid CmsTemplateLibVO vo) throws Exception{
        LogUtil.info(MODULE,"==========" + JSONObject.toJSONString(vo));
        CmsTemplateLibReqDTO reqDTO = new CmsTemplateLibReqDTO();
        ObjectCopyUtil.copyObjValue(vo, reqDTO, null, true);
        reqDTO.setStatus(CmsConstants.ParamStatus.CMS_PARAMSTATUS_0);//有效
        
        EcpBaseResponseVO respVO = new EcpBaseResponseVO();
        CmsTemplateLibRespDTO pageInfo = null;
        try {
            if(vo.getId() != null){
                pageInfo= this.cmsTemplateLibRSV.updateCmsTemplateLib(reqDTO);
            }else{
                pageInfo = this.cmsTemplateLibRSV.addCmsTemplateLib(reqDTO);
            }
            respVO.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_SUCCESS);
            if(pageInfo!=null && StringUtil.isNotEmpty(pageInfo.getId())){
                respVO.setResultMsg(pageInfo.getId().toString());
            }
        } catch (Exception e) {
            LogUtil.error(MODULE, "保存模板信息：save 异常", e);
            respVO.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_EXCEPTION);
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
                reqDto.setStatus(CmsConstants.ParamStatus.CMS_PARAMSTATUS_1);
                pageTypes = this.queryPageType(reqDto);
                if(CollectionUtils.isNotEmpty(pageTypes)){
                    pageInfo.setPageTypeZH(pageTypes.get(0).getPageTypeName());
                }
            }
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
    
    /** 
     * changestatus:(失效，撤销，发布). <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author jiangzh 
     * @param VO
     * @return 
     * @since JDK 1.6 
     */ 
    @RequestMapping(value="/changestatus")
    @ResponseBody
    public EcpBaseResponseVO changestatus(CmsTemplateLibVO VO) throws Exception{
        if(VO == null){
            VO = new CmsTemplateLibVO();
        }
        LogUtil.info(MODULE,"==========" + JSONObject.toJSONString(VO));
        
        EcpBaseResponseVO respVO = new EcpBaseResponseVO();
        if(StringUtil.isBlank(VO.getStatus()) || StringUtil.isEmpty(VO.getId())){
            respVO.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_FAILURE);
            respVO.setResultMsg("更新失败，入参id为空，或者状态为空！");
            return respVO;
        }
        
        //条件DTO
        CmsTemplateLibReqDTO conditionDTO = new CmsTemplateLibReqDTO();
        conditionDTO.setId(VO.getId());
        //结果DTO
        CmsTemplateLibReqDTO resultDTO = new CmsTemplateLibReqDTO();
        resultDTO.setId(VO.getId());
        if("1".equalsIgnoreCase(VO.getStatus())){//发布
            try {
                conditionDTO.setStatus(CmsConstants.ParamStatus.CMS_PARAMSTATUS_0);
                resultDTO.setStatus(CmsConstants.ParamStatus.CMS_PARAMSTATUS_1);
                cmsTemplateLibRSV.updateTemplateLibStatusByExample(conditionDTO,resultDTO);
                respVO.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_SUCCESS);
            } catch (Exception e) {
                LogUtil.error(MODULE, "模板发布异常", e);
                respVO.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_FAILURE);
                respVO.setResultMsg("模板发布发生异常，请重试！");
            }
        }else if("0".equalsIgnoreCase(VO.getStatus())){//撤销
            try {
                conditionDTO.setStatus(CmsConstants.ParamStatus.CMS_PARAMSTATUS_1);
                resultDTO.setStatus(CmsConstants.ParamStatus.CMS_PARAMSTATUS_0);
                cmsTemplateLibRSV.updateTemplateLibStatusByExample(conditionDTO,resultDTO);
                respVO.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_SUCCESS);
            } catch (Exception e) {
                LogUtil.error(MODULE, "模板撤销异常", e);
                respVO.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_FAILURE);
                respVO.setResultMsg("模板撤销发生异常，请重试！");
            }
        }else if("2".equalsIgnoreCase(VO.getStatus())){//失效
            try {
                conditionDTO.setStatus(CmsConstants.ParamStatus.CMS_PARAMSTATUS_0);
                resultDTO.setStatus(CmsConstants.ParamStatus.CMS_PARAMSTATUS_2);
                cmsTemplateLibRSV.updateTemplateLibStatusByExample(conditionDTO,resultDTO);
                respVO.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_SUCCESS);
            } catch (Exception e) {
                LogUtil.error(MODULE, "模板发布异常", e);
                respVO.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_FAILURE);
                respVO.setResultMsg("模板失效发生异常，请重试！");
            }
        }else{//错误代码
            respVO.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_FAILURE);
            respVO.setResultMsg("更新失败，状态编码错误！");
        }
        return respVO;
    }
    /**
     * 
     * getTempList:(这里用一句话描述这个方法的作用). <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author zhanbh 
     * @param model
     * @param vo
     * @return
     * @throws Exception 
     * @since JDK 1.6
     */
    @RequestMapping("/getTempList")
    public String getTempList(Model model,@ModelAttribute("searchVO")CmsTemplateLibVO searchVO) throws Exception{
        LogUtil.info(MODULE,"==========" + JSONObject.toJSONString(searchVO));
        //1. 调用后场服务所需要的DTO；
        CmsTemplateLibReqDTO reqDTO = searchVO.toBaseInfo(CmsTemplateLibReqDTO.class);
        ObjectCopyUtil.copyObjValue(searchVO, reqDTO, "MODULE", true);
        
        if(StringUtil.isEmpty(reqDTO.getPageTypeId()) && StringUtil.isEmpty(reqDTO.getTemplateType())){
            throw new Exception("字段页面类型为空，或者模板类型为空");
        }
        if(StringUtil.isEmpty(reqDTO.getPlatformType())){
            throw new Exception("字段平台类型为空");
        }
        //2. 调用后场服务，将后场返回的分页对象封装为前店所需要的分页对象；
        reqDTO.setStatus(CmsConstants.ParamStatus.CMS_PARAMSTATUS_1);
        PageResponseDTO<CmsTemplateLibRespDTO> pageInfo = this.cmsTemplateLibRSV.queryCmsTemplateLibPage(reqDTO);
            
        if(pageInfo!=null){
            this.extendTemplateLibList(pageInfo.getResult(),"150x150!");
        }
        EcpBasePageRespVO<Map> respVO = EcpBasePageRespVO.buildByPageResponseDTO(pageInfo);
        //model.addAttribute("checkTemplateId",templateId);
        super.addPageToModel(model, respVO);
        
        return "pageConfig/pageInfo/open/temp-list";
    }
}

