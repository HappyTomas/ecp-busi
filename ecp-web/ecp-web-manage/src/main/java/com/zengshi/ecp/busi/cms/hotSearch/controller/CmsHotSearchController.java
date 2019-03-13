package com.zengshi.ecp.busi.cms.hotSearch.controller;

import java.util.Arrays;
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
import com.zengshi.ecp.busi.cms.hotSearch.vo.CmsHotSearchVO;
import com.zengshi.ecp.cms.dubbo.dto.CmsHotSearchReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsHotSearchRespDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsPlaceReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsPlaceRespDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsSiteReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsSiteRespDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsTemplateReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsTemplateRespDTO;
import com.zengshi.ecp.cms.dubbo.interfaces.ICmsHotSearchRSV;
import com.zengshi.ecp.cms.dubbo.interfaces.ICmsPlaceRSV;
import com.zengshi.ecp.cms.dubbo.interfaces.ICmsSiteRSV;
import com.zengshi.ecp.cms.dubbo.interfaces.ICmsTemplateRSV;
import com.zengshi.ecp.cms.dubbo.util.CmsConstants;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.ObjectCopyUtil;
import com.alibaba.dubbo.common.utils.CollectionUtils;
import com.alibaba.fastjson.JSONObject;


/**
 * Title: ECP <br>
 * Project Name:ecp-web-manage <br>
 * Description: <br>
 * Date:2015年8月17日下午6:54:49  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author huangxm9
 * @version  
 * @since JDK 1.6
 */
@Controller
@RequestMapping(value="/hotSearch")
public class CmsHotSearchController extends EcpBaseController {
    
    private static String MODULE = CmsHotSearchController.class.getName();
    
    private String URL = "/cms/hotSearch/hotSearch";//返回页面的基本路径 
    
    @Resource(name="cmsHotSearchRSV")
    private ICmsHotSearchRSV cmsHotSearchRSV;
    @Resource(name="cmsPlaceRSV")
    private ICmsPlaceRSV cmsPlaceRSV;
    @Resource(name="cmsSiteRSV")
    private ICmsSiteRSV cmsSiteRSV;
    @Resource(name="cmsTemplateRSV")
    private ICmsTemplateRSV cmsTemplateRSV;
    

    /** 
     * init:页面初始化，requestMapping如果不写的话，访问地址同：Controller类的 requestmapping的URL
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author huangxm9 
     * @return 
     * @since JDK 1.7
     */ 
    @RequestMapping()
    public String init(){
        return URL+"-init";
    }
    
    /** 
     * grid:(这里用一句话描述这个方法的作用). <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author huangxm9 
     * @return 
     * @throws Exception 
     * @since JDK 1.7
     */ 
    @RequestMapping(value="/grid")
    public String grid(Model model,
            @ModelAttribute("searchParams") String searchParams) throws Exception{
        model.addAttribute("siteList",this.querySiteList(null));
        model.addAttribute("templateList", this.queryTemplateList(null,null,null));
        model.addAttribute("placeList", this.queryPlaceList(null,null,null));
        return URL+"-grid";
    }
   
    /** 
     * edit:(这里用一句话描述这个方法的作用). <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author huangxm9 
     * @param model
     * @return 
     * @since JDK 1.7
     */ 
    @RequestMapping(value="/add")
    public String add(Model model,
            @ModelAttribute("searchParams") String searchParams) throws Exception{
        model.addAttribute("siteList",this.querySiteList(CmsConstants.ParamStatus.CMS_PARAMSTATUS_1));
        return URL+"-edit";
    }
    
    /** 
     * edit:(这里用一句话描述这个方法的作用). <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author huangxm9 
     * @param model
     * @param id
     * @return
     * @throws Exception 
     * @since JDK 1.7
     */ 
    @RequestMapping(value="/edit")
    public String edit(Model model,
            @RequestParam("id")String id,
            @ModelAttribute("searchParams") String searchParams) throws Exception{
        if(StringUtils.isBlank(id)){
            String[] keyInfos = new String[1];
            keyInfos[0]="id";
           throw new BusinessException("cms.common.param.null.error",keyInfos); 
        }
        
        CmsHotSearchReqDTO reqDTO = new CmsHotSearchReqDTO();
        reqDTO.setId(Long.valueOf(id));
        try {
            CmsHotSearchRespDTO cmsHotSearchRespDTO = cmsHotSearchRSV.queryCmsHotSearch(reqDTO);
            model.addAttribute("siteList",this.querySiteList(null));
//            Long siteId = cmsHotSearchRespDTO.getSiteId();
//            model.addAttribute("templateList",this.queryTemplateList(siteId,null,CmsConstants.ParamStatus.CMS_PARAMSTATUS_1));
//            Long templateId = cmsHotSearchRespDTO.getTemplateId();
//            model.addAttribute("placeList",this.queryPlaceList(templateId,null,CmsConstants.ParamStatus.CMS_PARAMSTATUS_1));
            model.addAttribute("respVO",cmsHotSearchRespDTO);
        } catch (BusinessException e) {
            // TODO: handle exception
            throw new BusinessException(e.getErrorCode());
        }
       
        return URL+"-edit";
    }
    
    /** 
     * changestatus:(生效、失效). <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author huangxm9 
     * @param model
     * @param ids
     * @param status
     * @return
     * @throws Exception 
     * @since JDK 1.7
     */ 
    @RequestMapping(value="/changestatus")
    @ResponseBody
    public EcpBaseResponseVO changestatus(Model model,@RequestParam("ids")String ids,@RequestParam("status")String status) throws Exception{
        LogUtil.info(MODULE,"==========ids:"+ids+";");
        if(StringUtils.isBlank(ids)){
            String[] keyInfos = new String[1];
            keyInfos[0]="ids";
            throw new BusinessException("cms.common.param.null.error",keyInfos); 
         }
         if(StringUtils.isBlank(status)){
             String[] keyInfos = new String[1];
             keyInfos[0]="status";
             throw new BusinessException("cms.common.param.null.error",keyInfos); 
         }
        String[] idsArray = ids.split(",");
        List<String> list = Arrays.asList(idsArray);
        if(CollectionUtils.isEmpty(list)){
            throw new BusinessException("入参ids为空！");
        }
        cmsHotSearchRSV.changeStatusCmsHotSearchBatch(list, status);
        EcpBaseResponseVO vo = new EcpBaseResponseVO();
        vo.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_SUCCESS);
        return vo;
    }
    
    /** 
     * delete:(这里用一句话描述这个方法的作用). <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author huangxm9 
     * @param ids
     * @return
     * @throws Exception 
     * @since JDK 1.7
     */ 
    @RequestMapping(value="/delete")
    @ResponseBody
    public EcpBaseResponseVO delete(@RequestParam("ids")String ids) throws Exception{
        
        LogUtil.info(MODULE,"==========ids:"+ids+";");
        if(StringUtils.isBlank(ids)){
            String[] keyInfos = new String[1];
            keyInfos[0]="ids";
            throw new BusinessException("cms.common.param.null.error",keyInfos); 
        }
        String[] idsArray = ids.split(",");
        List<String> list = Arrays.asList(idsArray);
        if(CollectionUtils.isEmpty(list)){
            String[] keyInfos = new String[1];
            keyInfos[0]="ids";
            throw new BusinessException("cms.common.param.null.error",keyInfos); 
        }
        cmsHotSearchRSV.deleteCmsHotSearchBatch(list);
        EcpBaseResponseVO respVO = new EcpBaseResponseVO();
        respVO.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_SUCCESS);
        return respVO;
    }

    /** 
     * save:(新增/编辑 发布保存). <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author huangxm9 
     * @param cmsHotSearchVO
     * @return 
     * @since JDK 1.7
     */ 
    @RequestMapping(value="/pubsave")
    @ResponseBody
    public EcpBaseResponseVO pubsave(@Valid CmsHotSearchVO VO) throws Exception{
        LogUtil.info(MODULE,"==========" + JSONObject.toJSONString(VO));
        CmsHotSearchReqDTO reqDTO = new CmsHotSearchReqDTO();
        ObjectCopyUtil.copyObjValue(VO, reqDTO, null, true);
        reqDTO.setStatus(CmsConstants.ParamStatus.CMS_PARAMSTATUS_1);//发布
        //如果排序为空，则默认赋值为1.
        if(reqDTO.getSortNo() == null){
            reqDTO.setSortNo(1);
        }
        EcpBaseResponseVO respVO = new EcpBaseResponseVO();
        if(reqDTO.getId() != null){
            cmsHotSearchRSV.updateCmsHotSearch(reqDTO);
        }else{
            cmsHotSearchRSV.addCmsHotSearch(reqDTO);
        }
        respVO.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_SUCCESS);
        return respVO;
    }
    
    /** 
     * save:(新增/编辑 保存). <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author huangxm9 
     * @param cmsHotSearchVO
     * @return 
     * @since JDK 1.7
     */ 
    @RequestMapping(value="/save")
    @ResponseBody
    public EcpBaseResponseVO save(@Valid CmsHotSearchVO VO) throws Exception{
        LogUtil.info(MODULE,"==========" + JSONObject.toJSONString(VO));
        CmsHotSearchReqDTO reqDTO = new CmsHotSearchReqDTO();
        ObjectCopyUtil.copyObjValue(VO, reqDTO, null, true);
        reqDTO.setStatus(CmsConstants.ParamStatus.CMS_PARAMSTATUS_0);//未发布
        //如果排序为空，则默认赋值为1.
        if(reqDTO.getSortNo() == null){
            reqDTO.setSortNo(1);
        }
        EcpBaseResponseVO respVO = new EcpBaseResponseVO();
        if(VO.getId() != null){
            cmsHotSearchRSV.updateCmsHotSearch(reqDTO);
        }else{
            cmsHotSearchRSV.addCmsHotSearch(reqDTO);
        }
        respVO.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_SUCCESS);
        return respVO;
    }
   
    /** 
     * view:(查看详情). <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author zhanbh
     * 2015.10.16 
     * @param cmsHotSearchVO
     * @return 
     * @since JDK 1.7
     */ 
    @RequestMapping(value="/view")
    public String view(Model model,
            @RequestParam("id")String id,
            @ModelAttribute("searchParams") String searchParams
            ) throws Exception{
        
        if(StringUtils.isBlank(id)){
            String[] keyInfos = new String[1];
            keyInfos[0]="id";
           throw new BusinessException("cms.common.param.null.error",keyInfos); 
        }
        CmsHotSearchReqDTO reqDTO = new CmsHotSearchReqDTO();
        reqDTO.setId(Long.valueOf(id));
        try {
            CmsHotSearchRespDTO cmsHotSearchRespDTO = cmsHotSearchRSV.queryCmsHotSearch(reqDTO);
            model.addAttribute("respVO",cmsHotSearchRespDTO);
        } catch (BusinessException e) {
            // TODO: handle exception
            throw new BusinessException(e.getErrorCode());
        }
       
        return URL+"-view";
    }
   
    /** 
     * gridList:(这里用一句话描述这个方法的作用). <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author huangxm9 
     * @param model
     * @param cmsHotSearchSearchVO
     * @return
     * @throws Exception 
     * @since JDK 1.7
     */ 
    @SuppressWarnings("rawtypes")
    @RequestMapping("/gridlist")
    @ResponseBody
    public Model gridList(Model model, 
            @ModelAttribute("searchVO") CmsHotSearchVO searchVO) throws Exception{
        LogUtil.info(MODULE,"==========" + JSONObject.toJSONString(searchVO));
        //1. 调用后场服务所需要的DTO；
        CmsHotSearchReqDTO reqDTO = searchVO.toBaseInfo(CmsHotSearchReqDTO.class);
        ObjectCopyUtil.copyObjValue(searchVO, reqDTO, "MODULE", true);
        
        //2. 调用后场服务，将后场返回的分页对象封装为前店所需要的分页对象；
        PageResponseDTO<CmsHotSearchRespDTO> pageInfo = cmsHotSearchRSV.queryCmsHotSearchPage(reqDTO);
        
        EcpBasePageRespVO<Map> respVO = EcpBasePageRespVO.buildByPageResponseDTO(pageInfo);
        return super.addPageToModel(model, respVO);
    }

    /** 
     * changeSite:(这里用一句话描述这个方法的作用). <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author huangxm9 
     * @param model
     * @param siteId
     * @param templateClass
     * @param status
     * @return
     * @throws Exception 
     * @since JDK 1.7
     */ 
    @RequestMapping(value="/changeSite")
    @ResponseBody
    public List<CmsTemplateRespDTO> changeSite(Model model,@RequestParam("siteId")String siteId,@RequestParam(value="templateClass",required=false)String templateClass,@RequestParam(value="status",required=false)String status) throws Exception{
        LogUtil.info(MODULE,"==========siteId:"+siteId+";");
        return this.queryTemplateList((StringUtils.isNotBlank(siteId))?Long.valueOf(siteId):null,templateClass,status);
    }
    
    /** 
     * changeTemplate:(这里用一句话描述这个方法的作用). <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author huangxm9 
     * @param model
     * @param templateId
     * @param placeType
     * @param status
     * @return
     * @throws Exception 
     * @since JDK 1.7
     */ 
    @RequestMapping(value="/changeTemplate")
    @ResponseBody
    public List<CmsPlaceRespDTO> changeTemplate(Model model,@RequestParam("templateId")String templateId,@RequestParam(value="placeType",required=false)String placeType,@RequestParam(value="status",required=false)String status) throws Exception{
        LogUtil.info(MODULE,"==========templateId:"+templateId+";");
        return this.queryPlaceList((StringUtils.isNotBlank(templateId))?Long.valueOf(templateId):null,placeType,status);
    }
    
    /** 
     * queryPlaceList:(获取内容位置列表). <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author huangxm9 
     * @return
     * @throws Exception 
     * @since JDK 1.7
     */ 
    private List<CmsPlaceRespDTO> queryPlaceList(Long templateId,String placeType,String status) throws Exception{
        CmsPlaceReqDTO cmsPlaceReqDTO = new CmsPlaceReqDTO();
        if(templateId != null){
            cmsPlaceReqDTO.setTemplateId(Long.valueOf(templateId));
        }
        if(StringUtils.isNotBlank(placeType)){
            cmsPlaceReqDTO.setPlaceType(placeType);
        }
        if(StringUtils.isNotBlank(status)){
            cmsPlaceReqDTO.setStatus(status);
        }
        List<CmsPlaceRespDTO> cmsPlaceRespDTOList = cmsPlaceRSV.queryCmsPlaceList(cmsPlaceReqDTO);
        return cmsPlaceRespDTOList;
    }
    
    /** 
     * querySiteList:(获取内容位置列表). <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author huangxm9 
     * @return
     * @throws Exception 
     * @since JDK 1.7
     */ 
    private List<CmsSiteRespDTO> querySiteList(String status) throws Exception{
        CmsSiteReqDTO cmsSiteReqDTO = new CmsSiteReqDTO();
        if(StringUtils.isNotBlank(status)){
            cmsSiteReqDTO.setStatus(status);
        }
        List<CmsSiteRespDTO> cmsSiteRespDTOList = cmsSiteRSV.queryCmsSiteList(cmsSiteReqDTO);
        return cmsSiteRespDTOList;
    }
    
    /** 
     * queryTemplateList:(这里用一句话描述这个方法的作用). <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author huangxm9 
     * @param siteId  站点
     * @param templateClass  模板分类
     * @param status  状态
     * @return
     * @throws Exception 
     * @since JDK 1.7
     */ 
    private List<CmsTemplateRespDTO> queryTemplateList(Long siteId,String templateClass,String status) throws Exception{
        CmsTemplateReqDTO cmsTemplateReqDTO = new CmsTemplateReqDTO();
        if(siteId != null){
            cmsTemplateReqDTO.setSiteId(Long.valueOf(siteId));
        }
        if(StringUtils.isNotBlank(templateClass)){
            cmsTemplateReqDTO.setTemplateClass(templateClass);
        }
        if(StringUtils.isNotBlank(status)){
            cmsTemplateReqDTO.setStatus(status);
        }
        return cmsTemplateRSV.queryCmsTemplateList(cmsTemplateReqDTO);
    }
  

}


