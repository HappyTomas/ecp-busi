package com.zengshi.ecp.busi.cms.place.controller;

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
import com.zengshi.ecp.busi.cms.place.vo.CmsPlaceVO;
import com.zengshi.ecp.cms.dubbo.dto.CmsPlaceReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsPlaceRespDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsSiteReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsSiteRespDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsTemplateReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsTemplateRespDTO;
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
 * Description:内容位置 <br>
 * Date:2015.09.14
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author huangxm9
 * @version  
 * @since JDK 1.7
 */
@Controller
@RequestMapping(value="/place")
public class CmsPlaceController extends EcpBaseController {
    
    private static String MODULE = CmsPlaceController.class.getName();
    
    private String URL = "/cms/place/place";//返回页面的基本路径 
    
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
     * @author jiangzh 
     * @return 
     * @since JDK 1.6 
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
     * @author jiangzh 
     * @return 
     * @throws Exception 
     * @since JDK 1.6 
     */ 
    @RequestMapping(value="/grid")
    public String grid(Model model,
            @ModelAttribute("searchParams") String searchParams) throws Exception {
        try {
            model.addAttribute("siteList", this.querySiteList(null));
            model.addAttribute("templateList", this.queryTemplateList(null,null,null));
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return URL + "-grid";
    }
   
    /** 
     * edit:(这里用一句话描述这个方法的作用). <br/> 
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
    public String add(Model model,
            @ModelAttribute("searchParams") String searchParams) throws Exception{
    	try {
    	     model.addAttribute("siteList",this.querySiteList(CmsConstants.ParamStatus.CMS_PARAMSTATUS_1));
    	} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
       
        return URL+"-edit";
    }

    /**
   	 * 页面信息查看页面初始化方法 placeView:(这里用一句话描述这个方法的作用). <br/>
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
   	@RequestMapping(value = "/view")
   	public String view(Model model, 
   	        @RequestParam("id") String id,
   	        @ModelAttribute("searchParams") String searchParams) {
   		LogUtil.info(MODULE, "进入内容位置查看初始化,入参：{id=" + id + "}");
   		if (StringUtils.isBlank(id)) {
   			String[] keyInfos = new String[1];
   			keyInfos[0] = "id";
   			throw new BusinessException("cms.common.param.null.error", keyInfos);
   		}

   		/* 1.根据入参调用后场place查询服务 */
   		CmsPlaceReqDTO cmsPlaceDTO = new CmsPlaceReqDTO();
   		cmsPlaceDTO.setId(Long.parseLong(id));
   		CmsPlaceRespDTO cmsPlaceRespDTO = cmsPlaceRSV.queryCmsPlace(cmsPlaceDTO);
   		
   		/* 2.设置页面对象 */
   		model.addAttribute("respVO", cmsPlaceRespDTO);

   		/* 4.返回页面路径 */	
   		return URL + "-view";
   	}
    /** 
     * edit:(这里用一句话描述这个方法的作用). <br/> 
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
            @ModelAttribute("searchParams") String searchParams) throws Exception{
        if(StringUtils.isBlank(id)){
            String[] keyInfos = new String[1];
            keyInfos[0]="id";
           throw new BusinessException("cms.place.param.null.error",keyInfos); 
        }
        
        CmsPlaceReqDTO reqDTO = new CmsPlaceReqDTO();
        reqDTO.setId(Long.valueOf(id));
        try {
            CmsPlaceRespDTO cmsPlaceRespDTO = cmsPlaceRSV.queryCmsPlace(reqDTO);
            model.addAttribute("siteList",this.querySiteList(CmsConstants.ParamStatus.CMS_PARAMSTATUS_1));
            model.addAttribute("templateList",this.queryTemplateList(cmsPlaceRespDTO.getSiteId(),null,CmsConstants.ParamStatus.CMS_PARAMSTATUS_1));
            model.addAttribute("respVO",cmsPlaceRespDTO);
        } catch (BusinessException e) {
            // TODO: handle exception
            throw new BusinessException(e.getErrorCode());
        }
        return URL+"-edit";
    }
    
    /** 
     * changeSite:(这里用一句话描述这个方法的作用). <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author jiangzh 
     * @param model
     * @param siteId
     * @param templateClass
     * @param status
     * @return
     * @throws Exception 
     * @since JDK 1.6 
     */ 
    @RequestMapping(value="/changeSite")
    @ResponseBody
    public List<CmsTemplateRespDTO> changeSite(Model model,@RequestParam("siteId")String siteId,@RequestParam(value="templateClass",required=false)String templateClass,@RequestParam(value="status",required=false)String status) throws Exception{
        LogUtil.info(MODULE,"==========siteId:"+siteId+";");
        return this.queryTemplateList((StringUtils.isNotBlank(siteId))?Long.valueOf(siteId):null,templateClass,status);
    }
    
    /** 
     * changestatus:(生效、失效). <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author jiangzh 
     * @param model
     * @param ids
     * @param status
     * @return
     * @throws Exception 
     * @since JDK 1.6 
     */ 
    @RequestMapping(value="/changestatus")
    @ResponseBody
    public EcpBaseResponseVO changestatus(Model model,@RequestParam("ids")String ids,@RequestParam("status")String status) throws Exception{
        LogUtil.info(MODULE,"==========ids:"+ids+";");
        if(StringUtils.isBlank(ids)){
            String[] keyInfos = new String[1];
            keyInfos[0]="ids";
            throw new BusinessException("cms.place.param.null.error",keyInfos); 
         }
         if(StringUtils.isBlank(status)){
             String[] keyInfos = new String[1];
             keyInfos[0]="status";
             throw new BusinessException("cms.place.param.null.error",keyInfos); 
         }
        String[] idsArray = ids.split(",");
        List<String> list = Arrays.asList(idsArray);
        if(CollectionUtils.isEmpty(list)){
            throw new BusinessException("入参ids为空！");
        }
        cmsPlaceRSV.changeStatusCmsPlaceBatch(list, status);
        
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
     * @author jiangzh 
     * @param ids
     * @return
     * @throws Exception 
     * @since JDK 1.6 
     */ 
    @RequestMapping(value="/delete")
    @ResponseBody
    public EcpBaseResponseVO delete(@RequestParam("ids")String ids) throws Exception{
        
        LogUtil.info(MODULE,"==========ids:"+ids+";");
        if(StringUtils.isBlank(ids)){
            String[] keyInfos = new String[1];
            keyInfos[0]="ids";
            throw new BusinessException("cms.place.param.null.error",keyInfos); 
        }
        String[] idsArray = ids.split(",");
        List<String> list = Arrays.asList(idsArray);
        if(CollectionUtils.isEmpty(list)){
            String[] keyInfos = new String[1];
            keyInfos[0]="ids";
            throw new BusinessException("cms.place.param.null.error",keyInfos); 
        }
        cmsPlaceRSV.deleteCmsPlaceBatch(list);
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
     * @param cmsPlaceVO
     * @return 
     * @since JDK 1.6 
     */ 
    @RequestMapping(value="/pubsave")
    @ResponseBody
    public EcpBaseResponseVO pubsave(@Valid CmsPlaceVO VO) throws Exception{
        LogUtil.info(MODULE,"==========" + JSONObject.toJSONString(VO));
        CmsPlaceReqDTO reqDTO = new CmsPlaceReqDTO();
        ObjectCopyUtil.copyObjValue(VO, reqDTO, null, true);
        reqDTO.setStatus(CmsConstants.ParamStatus.CMS_PARAMSTATUS_1);//发布
        //如果排序为空，则默认赋值为1.
        if(reqDTO.getSortNo() == null){
            reqDTO.setSortNo(1);
        }
        EcpBaseResponseVO respVO = new EcpBaseResponseVO();
        if(reqDTO.getId() != null){
            cmsPlaceRSV.updateCmsPlace(reqDTO);
        }else{
            cmsPlaceRSV.addCmsPlace(reqDTO);
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
     * @author jiangzh 
     * @param cmsPlaceVO
     * @return 
     * @since JDK 1.6 
     */ 
    @RequestMapping(value="/save")
    @ResponseBody
    public EcpBaseResponseVO save(@Valid CmsPlaceVO VO) throws Exception{
        LogUtil.info(MODULE,"==========" + JSONObject.toJSONString(VO));
        CmsPlaceReqDTO reqDTO = new CmsPlaceReqDTO();
        ObjectCopyUtil.copyObjValue(VO, reqDTO, null, true);
        reqDTO.setStatus(CmsConstants.ParamStatus.CMS_PARAMSTATUS_0);//有效
        //如果排序为空，则默认赋值为1.
        if(reqDTO.getSortNo() == null){
            reqDTO.setSortNo(1);
        }
        EcpBaseResponseVO respVO = new EcpBaseResponseVO();
        if(VO.getId() != null){
            cmsPlaceRSV.updateCmsPlace(reqDTO);
        }else{
            cmsPlaceRSV.addCmsPlace(reqDTO);
        }
        respVO.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_SUCCESS);
        return respVO;
    }
    
    /** 
     * gridList:(这里用一句话描述这个方法的作用). <br/> 
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
    public Model gridList(Model model, @ModelAttribute("searchVO") CmsPlaceVO searchVO) throws Exception{
        LogUtil.info(MODULE,"==========" + JSONObject.toJSONString(searchVO));
        //1. 调用后场服务所需要的DTO；
        CmsPlaceReqDTO reqDTO = searchVO.toBaseInfo(CmsPlaceReqDTO.class);
        ObjectCopyUtil.copyObjValue(searchVO, reqDTO, "MODULE", true);
        
        //2. 调用后场服务，将后场返回的分页对象封装为前店所需要的分页对象；
        PageResponseDTO<CmsPlaceRespDTO> pageInfo = cmsPlaceRSV.queryCmsPlacePage(reqDTO);
        	
        EcpBasePageRespVO<Map> respVO = EcpBasePageRespVO.buildByPageResponseDTO(pageInfo);
        return super.addPageToModel(model, respVO);
    }
    
    /** 
     * querySiteList:(获取内容位置列表). <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author jiangzh 
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
     * @author jiangzh 
     * @param siteId  站点
     * @param templateClass  模板分类
     * @param status  状态
     * @return
     * @throws Exception 
     * @since JDK 1.6 
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
