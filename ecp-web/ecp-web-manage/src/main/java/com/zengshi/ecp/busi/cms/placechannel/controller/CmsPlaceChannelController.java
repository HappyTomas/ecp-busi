package com.zengshi.ecp.busi.cms.placechannel.controller;

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
import com.zengshi.ecp.busi.cms.placechannel.vo.CmsPlaceChannelVO;
import com.zengshi.ecp.cms.dubbo.dto.CmsPlaceChannelReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsPlaceChannelRespDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsPlaceReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsPlaceRespDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsSiteReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsSiteRespDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsTemplateReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsTemplateRespDTO;
import com.zengshi.ecp.cms.dubbo.interfaces.ICmsPlaceChannelRSV;
import com.zengshi.ecp.cms.dubbo.interfaces.ICmsPlaceRSV;
import com.zengshi.ecp.cms.dubbo.interfaces.ICmsSiteInfoRSV;
import com.zengshi.ecp.cms.dubbo.interfaces.ICmsSiteRSV;
import com.zengshi.ecp.cms.dubbo.interfaces.ICmsTemplateRSV;
import com.zengshi.ecp.cms.dubbo.util.CmsConstants;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.ObjectCopyUtil;
import com.zengshi.paas.utils.StringUtil;
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
@RequestMapping(value="/placechannel")
public class CmsPlaceChannelController extends EcpBaseController {
    
    private static String MODULE = CmsPlaceChannelController.class.getName();
    
    private String URL = "/cms/placechannel/placechannel";//返回页面的基本路径 
    
    @Resource(name="cmsPlaceChannelRSV")
    private ICmsPlaceChannelRSV cmsPlaceChannelRSV;
    @Resource(name="cmsPlaceRSV")
    private ICmsPlaceRSV cmsPlaceRSV;
    @Resource(name="cmsSiteRSV")
    private ICmsSiteRSV cmsSiteRSV;
    @Resource(name="cmsTemplateRSV")
    private ICmsTemplateRSV cmsTemplateRSV;
    @Resource(name = "cmsSiteInfoRSV")
    private ICmsSiteInfoRSV cmsSiteInfoRSV;

    /** 
     * init:页面初始化，requestMapping如果不写的话，访问地址同：Controller类的 requestmapping的URL
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author huangxm9 
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
     * @author huangxm9 
     * @return 
     * @throws Exception 
     * @since JDK 1.6 
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
     * @since JDK 1.6 
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
     * @since JDK 1.6 
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
        
        CmsPlaceChannelReqDTO reqDTO = new CmsPlaceChannelReqDTO();
        reqDTO.setId(Long.valueOf(id));
        try {
            CmsPlaceChannelRespDTO cmsPlaceChannelRespDTO = cmsPlaceChannelRSV.queryCmsPlaceChannel(reqDTO);
            model.addAttribute("siteList",this.querySiteList(null));
            Long siteId = cmsPlaceChannelRespDTO.getSiteId();
            model.addAttribute("templateList",this.queryTemplateList(siteId,null,CmsConstants.ParamStatus.CMS_PARAMSTATUS_1));
            Long templateId = cmsPlaceChannelRespDTO.getTemplateId();
            model.addAttribute("placeList",this.queryPlaceList(templateId,null,CmsConstants.ParamStatus.CMS_PARAMSTATUS_1));
            model.addAttribute("respVO",cmsPlaceChannelRespDTO);
        } catch (BusinessException e) {
            // TODO: handle exception
            throw new BusinessException(e.getErrorCode());
        }
       
        return URL+"-edit";
    }
    
    /** 
     * view:(这里用一句话描述这个方法的作用). <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author zhanbh
     * 2015.10.16 
     * @param model
     * @param id
     * @return
     * @throws Exception 
     * @since JDK 1.6 
     */ 
    @RequestMapping(value="/view")
    public String view(Model model,
            @RequestParam("id")String id,
            @ModelAttribute("searchParams") String searchParams) throws Exception{
        if(StringUtils.isBlank(id)){
            String[] keyInfos = new String[1];
            keyInfos[0]="id";
           throw new BusinessException("cms.common.param.null.error",keyInfos); 
        }
        
        CmsPlaceChannelReqDTO reqDTO = new CmsPlaceChannelReqDTO();
        reqDTO.setId(Long.valueOf(id));
        try {
            CmsPlaceChannelRespDTO cmsPlaceChannelRespDTO = cmsPlaceChannelRSV.queryCmsPlaceChannel(reqDTO);
            model.addAttribute("respVO",cmsPlaceChannelRespDTO);
        } catch (BusinessException e) {
            // TODO: handle exception
            throw new BusinessException(e.getErrorCode());
        }
       
        return URL+"-view";
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
            throw new BusinessException("cms.common.param.null.error",new String[] {"ids"}); 
         }
         if(StringUtils.isBlank(status)){
             throw new BusinessException("cms.common.param.null.error",new String[] {"status"}); 
         }
        String[] idsArray = ids.split(",");
        List<String> list = Arrays.asList(idsArray);
        if(CollectionUtils.isEmpty(list)){
            throw new BusinessException("入参ids为空！");
        }
        EcpBaseResponseVO vo = new EcpBaseResponseVO();
        
        // 如果状态为1，则为发布，需进行判断该内容位置下是否已经存在楼层，
        if (status.equalsIgnoreCase(CmsConstants.ParamStatus.CMS_PARAMSTATUS_1)) {
            CmsPlaceChannelRespDTO cmsPlaceChannelRespDTO = new CmsPlaceChannelRespDTO();
            // 遍历循环判断
            for (int i = 0; i < list.size(); i++) {
                String id = list.get(i);
                if(StringUtil.isNotEmpty(id)){
                    //根据楼层ID查询楼层
                    CmsPlaceChannelReqDTO cmsPlaceChannelReqDTO = new CmsPlaceChannelReqDTO();
                    cmsPlaceChannelReqDTO.setId(Long.valueOf(id));
                    cmsPlaceChannelRespDTO = cmsPlaceChannelRSV.queryCmsPlaceChannel(cmsPlaceChannelReqDTO);
                    if(cmsPlaceChannelRespDTO != null && StringUtil.isNotEmpty(cmsPlaceChannelRespDTO.getPlaceId())){
                        //根据内容位置查询有效楼层
                        CmsPlaceChannelReqDTO cmsPlaceChannelReqDTO2 = new CmsPlaceChannelReqDTO();
                        cmsPlaceChannelReqDTO2.setPlaceId(cmsPlaceChannelRespDTO.getPlaceId());
                        cmsPlaceChannelReqDTO2.setStatus(CmsConstants.ParamStatus.CMS_PARAMSTATUS_1);
                        List<CmsPlaceChannelRespDTO> list2 = cmsPlaceChannelRSV.queryCmsPlaceChannelList(cmsPlaceChannelReqDTO2);
                        if (!CollectionUtils.isEmpty(list2)) {
                            vo.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_FAILURE);
                            return vo;
                        } 
                    }
                }
            }
        }
        
        cmsPlaceChannelRSV.changeStatusCmsPlaceChannelBatch(list, status);
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
     * @since JDK 1.6 
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
        cmsPlaceChannelRSV.deleteCmsPlaceChannelBatch(list);
        EcpBaseResponseVO respVO = new EcpBaseResponseVO();
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
     * @param cmsplaceChannelVO
     * @return 
     * @since JDK 1.6 
     */ 
    @RequestMapping(value="/save")
    @ResponseBody
    public EcpBaseResponseVO save(@Valid CmsPlaceChannelVO VO) throws Exception{
        LogUtil.info(MODULE,"==========" + JSONObject.toJSONString(VO));
        CmsPlaceChannelReqDTO reqDTO = new CmsPlaceChannelReqDTO();
        ObjectCopyUtil.copyObjValue(VO, reqDTO, null, true);
        reqDTO.setStatus(CmsConstants.ParamStatus.CMS_PARAMSTATUS_0);//未发布
        EcpBaseResponseVO respVO = new EcpBaseResponseVO();
        if(VO.getId() != null){
            cmsPlaceChannelRSV.updateCmsPlaceChannel(reqDTO);
        }else{
            cmsPlaceChannelRSV.addCmsPlaceChannel(reqDTO);
        }
        respVO.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_SUCCESS);
        return respVO;
    }
    
    /** 
     * pubsave:(新增/编辑 保存). <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author huangxm9 
     * @param cmsplaceChannelVO
     * @return 
     * @since JDK 1.6 
     */ 
    @RequestMapping(value="/pubsave")
    @ResponseBody
    public CmsPlaceChannelRespDTO pubSave(@Valid CmsPlaceChannelVO VO) throws Exception{
        LogUtil.info(MODULE,"==========" + JSONObject.toJSONString(VO));
        CmsPlaceChannelReqDTO reqDTO = new CmsPlaceChannelReqDTO();
        ObjectCopyUtil.copyObjValue(VO, reqDTO, null, true);
        reqDTO.setStatus(CmsConstants.ParamStatus.CMS_PARAMSTATUS_1);//发布
        
        CmsPlaceChannelReqDTO reqDTO2 = new CmsPlaceChannelReqDTO();
		reqDTO2.setPlaceId(reqDTO.getPlaceId());
		reqDTO2.setStatus(CmsConstants.ParamStatus.CMS_PARAMSTATUS_1);
		List<CmsPlaceChannelRespDTO> list = cmsPlaceChannelRSV.queryCmsPlaceChannelList(reqDTO2);
		if (list.size() > 0) {
			CmsPlaceChannelRespDTO cmsPlaceChannelRespDTO = new CmsPlaceChannelRespDTO();
			return cmsPlaceChannelRespDTO;
		}
		
//        EcpBaseResponseVO respVO = new EcpBaseResponseVO();
		CmsPlaceChannelRespDTO respDTO = new CmsPlaceChannelRespDTO();
        if(VO.getId() != null){
        	respDTO = cmsPlaceChannelRSV.updateCmsPlaceChannel(reqDTO);
        }else{
        	respDTO = cmsPlaceChannelRSV.addCmsPlaceChannel(reqDTO);
        }
//        respVO.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_SUCCESS);
        return respDTO;
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
     * @param cmsplaceChannelSearchVO
     * @return
     * @throws Exception 
     * @since JDK 1.6 
     */ 
    @SuppressWarnings("rawtypes")
    @RequestMapping("/gridlist")
    @ResponseBody
    public Model gridList(Model model, @ModelAttribute("searchVO") CmsPlaceChannelVO searchVO) throws Exception{
        LogUtil.info(MODULE,"==========" + JSONObject.toJSONString(searchVO));
        //1. 调用后场服务所需要的DTO；
        CmsPlaceChannelReqDTO reqDTO = searchVO.toBaseInfo(CmsPlaceChannelReqDTO.class);
        ObjectCopyUtil.copyObjValue(searchVO, reqDTO, "MODULE", true);
        
        //2. 调用后场服务，将后场返回的分页对象封装为前店所需要的分页对象；
        PageResponseDTO<CmsPlaceChannelRespDTO> pageInfo = cmsPlaceChannelRSV.queryCmsPlaceChannelPage(reqDTO);
        
        
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
     * @since JDK 1.6 
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
     * @since JDK 1.6 
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
     * @since JDK 1.6 
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
     * @author huangxm9 
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


