package com.zengshi.ecp.busi.sellerModular.controller;

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
import com.zengshi.ecp.busi.pageConfig.pageInfo.vo.SkinPageConfigVO;
import com.zengshi.ecp.cms.dubbo.dto.CmsPageInfoReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsPageInfoRespDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsPageTypeReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsPageTypeRespDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsSiteReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsSiteRespDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsTemplateLibReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsTemplateLibRespDTO;
import com.zengshi.ecp.cms.dubbo.interfaces.ICmsPageConfigRSV;
import com.zengshi.ecp.cms.dubbo.interfaces.ICmsPageInfoRSV;
import com.zengshi.ecp.cms.dubbo.interfaces.ICmsPageTypeRSV;
import com.zengshi.ecp.cms.dubbo.interfaces.ICmsSiteRSV;
import com.zengshi.ecp.cms.dubbo.interfaces.ICmsTemplateLibRSV;
import com.zengshi.ecp.cms.dubbo.util.CmsConstants;
import com.zengshi.ecp.cms.dubbo.util.CmsConstants.ParamStatus;
import com.zengshi.ecp.server.front.dto.BaseParamDTO;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.server.front.util.BaseParamUtil;
import com.zengshi.ecp.staff.dubbo.dto.ShopInfoResDTO;
import com.zengshi.ecp.staff.dubbo.interfaces.IShopInfoRSV;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.ObjectCopyUtil;
import com.zengshi.paas.utils.StringUtil;
import com.alibaba.dubbo.common.utils.CollectionUtils;
import com.alibaba.fastjson.JSONObject;
/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-busi-ecp-web-manage <br>
 * Description: 卖家中心跳转到管理平台的页面配置<br>
 * Date:2016年6月16日上午10:33:09  <br>
 * Copyright (c) 2016 ZengShi All Rights Reserved <br>
 * 
 * @author gxq
 * @version  
 * @since JDK 1.6
 */
@RequestMapping(value="/cmssellerpageinfo")
@Controller
public class CmsSellerPageinfoController extends EcpBaseController{
    private static String SHOPFISHING = "shopfishing_";
    private static String MODULE = CmsSellerPageinfoController.class.getName();
    private String URL = "/pageConfig/seller/sellerpageinfo";//返回页面的基本路径 
    private String URL_OPEN = "/pageConfig/seller/models/seller-manage-pageInfo";//返回页面的弹出窗路径
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
     * 
     * @author gxq
     * @return 
     * @throws Exception 
     * @since JDK 1.6
     */
    @RequestMapping("/init")
    public String init(Model model,SkinPageConfigVO skinPageConfigVO,HttpServletRequest request) throws Exception{
        model.addAttribute("mallskintomanage", true);
        CmsPageTypeReqDTO pageType = new CmsPageTypeReqDTO();
        pageType.setStatus(CmsConstants.ParamStatus.CMS_PARAMSTATUS_1);
        model.addAttribute("pageTypeList", this.queryPageType(pageType));
        //获取从卖家中心跳转过来的shopId
        model.addAttribute("shopId", request.getSession().getAttribute(SHOPFISHING));
        model.addAttribute("pageName", "page");
        return URL + "/seller-manage-pageinfo";
    }
    
    /**
     * 
     * queryPageType:(查询页面类型). <br/> 
     * 
     * @author gxq
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
    public String grid(Model model,@ModelAttribute("searchParams") String searchParams,
            @ModelAttribute("mallskintomanage") String mallskintomanage,
            HttpServletRequest request) throws Exception {
        try {
            model.addAttribute("siteList", this.querySiteList(null));
            
            CmsPageTypeReqDTO pageType = new CmsPageTypeReqDTO();
            pageType.setStatus(CmsConstants.ParamStatus.CMS_PARAMSTATUS_1);
            model.addAttribute("pageTypeList", this.queryPageType(pageType));
            model.addAttribute("mallskintomanage", mallskintomanage);
            model.addAttribute("shopId", request.getSession().getAttribute(SHOPFISHING));
        } catch (Exception e) {
            LogUtil.error(MODULE, "查询页面信息：grid 异常", e);
        }
        model.addAttribute("pageName", "page");
        return URL + "/seller-manage-pageinfo";
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
    public String add(Model model,@ModelAttribute("searchParams") String searchParams,
            @ModelAttribute("mallskintomanage") String mallskintomanage,
            HttpServletRequest request) throws Exception{
        try {
             model.addAttribute("siteList",this.querySiteList(CmsConstants.ParamStatus.CMS_PARAMSTATUS_1));
             
             CmsPageTypeReqDTO pageType = new CmsPageTypeReqDTO();
             pageType.setStatus(CmsConstants.ParamStatus.CMS_PARAMSTATUS_1);
             model.addAttribute("pageTypeList", this.queryPageType(pageType));
             model.addAttribute("mallskintomanage", mallskintomanage);
             model.addAttribute("shopId", request.getSession().getAttribute(SHOPFISHING));
             model.addAttribute("pageName", "page");
        } catch (Exception e) {
            LogUtil.error(MODULE, "添加页面信息：add 异常", e);
        }
        return URL+"/seller-manage-pageinfo-edit";
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
    public String edit(Model model,@RequestParam("id")String id,@ModelAttribute("searchParams") String searchParams,
            @ModelAttribute("mallskintomanage") String mallskintomanage,
            HttpServletRequest request) throws Exception{
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
            
            if(pageInfo!=null && CmsConstants.IsNot.CMS_ISNOT_1.equalsIgnoreCase(pageInfo.getIsUseTemplate())&&StringUtil.isNotEmpty(pageInfo.getTemplateId())){
                CmsTemplateLibRespDTO tempLib = this.queryTemplateLib(pageInfo.getTemplateId());
                if(tempLib!=null && StringUtil.isNotEmpty(tempLib.getId())){
                    model.addAttribute("tempResp",tempLib);
                }
            }
        } catch (BusinessException e) {
            LogUtil.error(MODULE, "跳转到编辑页面信息：edit 异常", e);
            throw new BusinessException(e.getErrorCode());
        }
        model.addAttribute("mallskintomanage", mallskintomanage);
        model.addAttribute("shopId", request.getSession().getAttribute(SHOPFISHING));
        model.addAttribute("pageName", "page");
        return URL+"/seller-manage-pageinfo-edit";
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
    public EcpBaseResponseVO save(@Valid CmsPageInfoVO VO) throws Exception{
        LogUtil.info(MODULE,"==========" + JSONObject.toJSONString(VO));
        CmsPageInfoReqDTO reqDTO = new CmsPageInfoReqDTO();
        ObjectCopyUtil.copyObjValue(VO, reqDTO, null, true);
        
        if(reqDTO.getId() == null){//新增则状态为预览   修改则不动状态
            reqDTO.setStatus(CmsConstants.ParamStatus.CMS_PARAMSTATUS_0);//有效
        }
        
        EcpBaseResponseVO respVO = new EcpBaseResponseVO();
        CmsPageInfoRespDTO pageInfo = null;
        try {
            if(reqDTO.getId() != null){//修改
                pageInfo= this.cmsPageInfoRSV.updateCmsPageInfo(reqDTO);
            }else{//新增
                //新增时如果制定了模板  则调用从模板继承的方法
                if(StringUtil.isNotEmpty(reqDTO.getTemplateId())){
                    pageInfo = cmsPageConfigRSV.saveTemplateToPageConfig(reqDTO);
                }else{
                    pageInfo = this.cmsPageInfoRSV.addCmsPageInfo(reqDTO);
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
            LogUtil.error(MODULE, "改变页面的模板页面id或者模板 id参数错误：changePageTemp 错误");
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
    public EcpBaseResponseVO changestatus(CmsPageInfoVO VO) throws Exception{
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
                if(cmsPageConfigRSV.savePageConfigPub(reqDTO)){
                    respVO.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_SUCCESS);
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
            @RequestParam("siteId")String siteId,
            HttpServletRequest request){
        model.addAttribute("siteId", siteId);
        CmsPageTypeReqDTO pageType = new CmsPageTypeReqDTO();
        pageType.setStatus(CmsConstants.ParamStatus.CMS_PARAMSTATUS_1);
        model.addAttribute("pageTypeList", this.queryPageType(pageType));
        model.addAttribute("shopId", request.getSession().getAttribute(SHOPFISHING));
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
        //根据权限过滤掉对应的模板类型  
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
     * hasOtherRelease:(是否存在已发布的其他店铺首页). <br/> 
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
            reqDTO.setShopId(pageInfoVO.getShopId());
            reqDTO.setPageTypeId(1L);
            reqDTO.setStatus(ParamStatus.CMS_PARAMSTATUS_1);
            List<CmsPageInfoRespDTO> list= this.cmsPageInfoRSV.queryCmsPageInfoList(reqDTO);
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
}

