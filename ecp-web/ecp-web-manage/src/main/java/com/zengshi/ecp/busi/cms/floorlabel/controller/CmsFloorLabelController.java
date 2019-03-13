package com.zengshi.ecp.busi.cms.floorlabel.controller;

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
import com.zengshi.ecp.busi.cms.floorlabel.vo.CmsFloorLabelVO;
import com.zengshi.ecp.cms.dubbo.dto.CmsFloorLabelReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsFloorLabelRespDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsFloorReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsFloorRespDTO;
import com.zengshi.ecp.cms.dubbo.interfaces.ICmsFloorLabelRSV;
import com.zengshi.ecp.cms.dubbo.interfaces.ICmsFloorRSV;
import com.zengshi.ecp.cms.dubbo.interfaces.ICmsPlaceRSV;
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
 * @author jiangzh
 * @version  
 * @since JDK 1.6
 */
@Controller
@RequestMapping(value="/floorlabel")
public class CmsFloorLabelController extends EcpBaseController {
    
    private static String MODULE = CmsFloorLabelController.class.getName();
    
    private String URL = "/cms/floor/label/";//返回页面的基本路径 
    
    @Resource(name="cmsFloorLabelRSV")
    private ICmsFloorLabelRSV cmsFloorLabelRSV;
    @Resource(name="cmsPlaceRSV")
    private ICmsPlaceRSV cmsPlaceRSV;
    @Resource(name="cmsFloorRSV")
    private ICmsFloorRSV cmsFloorRSV;

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
            @RequestParam("floorId")String floorId,
            @ModelAttribute("searchParams") String searchParams,
            @ModelAttribute("floorSearchParams") String floorSearchParams) throws Exception{
    	model.addAttribute("floorId", floorId);
        return URL+"label-grid";
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
            @RequestParam("floorId")String floorId,
            @ModelAttribute("searchParams") String searchParams,
            @ModelAttribute("floorSearchParams") String floorSearchParams) throws Exception{
        /*1.通过楼层ID查询楼层*/
        CmsFloorReqDTO reqDTO = new CmsFloorReqDTO();
        if(StringUtils.isNotBlank(floorId)){
            reqDTO.setId(Long.valueOf(floorId));
        }
        /*2.copy对象*/
        CmsFloorLabelRespDTO respDTO = new CmsFloorLabelRespDTO();
        CmsFloorRespDTO cmsFloorRespDTO = cmsFloorRSV.queryCmsFloor(reqDTO);
        if(cmsFloorRespDTO!=null){
            respDTO.setFloorId(cmsFloorRespDTO.getId());
            respDTO.setPlaceId(cmsFloorRespDTO.getPlaceId());
            respDTO.setFloorName(cmsFloorRespDTO.getFloorName());
        }
        /*3.返回给页面*/
        model.addAttribute("respDTO",respDTO);
        return URL+"label-edit";
    }


    /**
   	 * 查看页面初始化方法 view:(这里用一句话描述这个方法的作用). <br/>
   	 * TODO(这里描述这个方法适用条件 – 可选).<br/>
   	 * TODO(这里描述这个方法的执行流程 – 可选).<br/>
   	 * TODO(这里描述这个方法的使用方法 – 可选).<br/>
   	 * TODO(这里描述这个方法的注意事项 – 可选).<br/>
   	 * 
   	 * @author huangxm9
   	 * @param model
   	 * @param id
   	 * @return
   	 * @since JDK 1.7
   	 */
   	@RequestMapping(value = "/view")
   	public String view(Model model, 
   	        @RequestParam("id") String id,
   	        @ModelAttribute("searchParams") String searchParams,
            @ModelAttribute("floorSearchParams") String floorSearchParams) throws Exception{
   	    /*1.验证入参*/
        if(StringUtils.isBlank(id)){
            String[] keyInfos = new String[1];
            keyInfos[0]="floorId";
           throw new BusinessException("cms.common.param.null.error",keyInfos); 
        }
        
        /*2.根据楼层ID获取楼层信息*/
        CmsFloorLabelReqDTO reqDTO = new CmsFloorLabelReqDTO();
        reqDTO.setId(Long.valueOf(id));
        try {
            CmsFloorLabelRespDTO respDTO = cmsFloorLabelRSV.queryCmsFloorLabel(reqDTO);
            model.addAttribute("respDTO",respDTO);
        } catch (BusinessException e) {
            // TODO: handle exception
            throw new BusinessException(e.getErrorCode());
        }
        
        /*3.返回信息*/
        return URL+"label-view";
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
            @RequestParam("id")String floorId,
            @ModelAttribute("searchParams") String searchParams,
            @ModelAttribute("floorSearchParams") String floorSearchParams) throws Exception{
        /*1.验证入参*/
        if(StringUtils.isBlank(floorId)){
            String[] keyInfos = new String[1];
            keyInfos[0]="floorId";
           throw new BusinessException("cms.common.param.null.error",keyInfos); 
        }
        
        /*2.根据楼层ID获取楼层信息*/
        CmsFloorLabelReqDTO reqDTO = new CmsFloorLabelReqDTO();
        reqDTO.setId(Long.valueOf(floorId));
        try {
            CmsFloorLabelRespDTO respDTO = cmsFloorLabelRSV.queryCmsFloorLabel(reqDTO);
            model.addAttribute("respDTO",respDTO);
        } catch (BusinessException e) {
            // TODO: handle exception
            throw new BusinessException(e.getErrorCode());
        }
        
        /*3.返回信息*/
        return URL+"label-edit";
    }
    
    /** 
     * changestatus:(生效、失效). <br/> ;
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
        cmsFloorLabelRSV.changeStatusCmsFloorLabelBatch(list, status);
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
            throw new BusinessException("cms.common.param.null.error",keyInfos); 
        }
        String[] idsArray = ids.split(",");
        List<String> list = Arrays.asList(idsArray);
        if(CollectionUtils.isEmpty(list)){
            String[] keyInfos = new String[1];
            keyInfos[0]="ids";
            throw new BusinessException("cms.common.param.null.error",keyInfos); 
        }
        cmsFloorLabelRSV.deleteCmsFloorLabelBatch(list);
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
     * @param cmsFloorLabelVO
     * @return 
     * @since JDK 1.6 
     */ 
    @RequestMapping(value="/pubsave")
    @ResponseBody
    public CmsFloorLabelRespDTO pubsave(@Valid CmsFloorLabelVO VO) throws Exception{
        LogUtil.info(MODULE,"==========" + JSONObject.toJSONString(VO));
        CmsFloorLabelReqDTO reqDTO = new CmsFloorLabelReqDTO();
        ObjectCopyUtil.copyObjValue(VO, reqDTO, null, true);
        reqDTO.setStatus(CmsConstants.ParamStatus.CMS_PARAMSTATUS_1);//发布
        //如果排序为空，则默认赋值为1.
        if(reqDTO.getSortNo() == null){
            reqDTO.setSortNo(1);
        }
        CmsFloorLabelRespDTO respDTO = new CmsFloorLabelRespDTO();
        if(reqDTO.getId() != null){
        	respDTO= cmsFloorLabelRSV.updateCmsFloorLabel(reqDTO);
        }else{
        	respDTO= cmsFloorLabelRSV.addCmsFloorLabel(reqDTO);
        }
        return respDTO;
    }
    /** 
     * save:(新增/编辑 保存). <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author jiangzh 
     * @param cmsAdvertiseVO
     * @return 
     * @since JDK 1.6 
     */ 
    @RequestMapping(value="/save")
    @ResponseBody
    public CmsFloorLabelRespDTO save(@Valid CmsFloorLabelVO VO) throws Exception{
        LogUtil.info(MODULE,"==========" + JSONObject.toJSONString(VO));
        CmsFloorLabelReqDTO reqDTO = new CmsFloorLabelReqDTO();
        ObjectCopyUtil.copyObjValue(VO, reqDTO, null, true);
        reqDTO.setStatus(CmsConstants.ParamStatus.CMS_PARAMSTATUS_0);//未发布
        //如果排序为空，则默认赋值为1.
        if(reqDTO.getSortNo() == null){
            reqDTO.setSortNo(1);
        }
        CmsFloorLabelRespDTO respDTO = new CmsFloorLabelRespDTO();
        if(VO.getId() != null){
            respDTO = cmsFloorLabelRSV.updateCmsFloorLabel(reqDTO);
        }else{
           
            respDTO = cmsFloorLabelRSV.addCmsFloorLabel(reqDTO);
        }
        return respDTO;
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
     * @param searchVO
     * @return
     * @throws Exception 
     * @since JDK 1.6 
     */ 
    @SuppressWarnings("rawtypes")
    @RequestMapping("/gridlist")
    @ResponseBody
    public Model gridList(Model model, @ModelAttribute("searchVO") CmsFloorLabelVO searchVO) throws Exception{
        LogUtil.info(MODULE,"==========" + JSONObject.toJSONString(searchVO));
        //1. 调用后场服务所需要的DTO；
        CmsFloorLabelReqDTO reqDTO = searchVO.toBaseInfo(CmsFloorLabelReqDTO.class);
        ObjectCopyUtil.copyObjValue(searchVO, reqDTO, "MODULE", true);
        
        //2. 调用后场服务，将后场返回的分页对象封装为前店所需要的分页对象；
        PageResponseDTO<CmsFloorLabelRespDTO> pageInfo = cmsFloorLabelRSV.queryCmsFloorLabelPage(reqDTO);
        EcpBasePageRespVO<Map> respVO = EcpBasePageRespVO.buildByPageResponseDTO(pageInfo);
        return super.addPageToModel(model, respVO);
    }

}
