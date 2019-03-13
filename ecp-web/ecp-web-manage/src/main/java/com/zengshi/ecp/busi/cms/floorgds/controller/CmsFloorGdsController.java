package com.zengshi.ecp.busi.cms.floorgds.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zengshi.ecp.base.controller.EcpBaseController;
import com.zengshi.ecp.base.vo.EcpBasePageRespVO;
import com.zengshi.ecp.base.vo.EcpBaseResponseVO;
import com.zengshi.ecp.busi.cms.floorgds.vo.CmsFloorGdsBatchVO;
import com.zengshi.ecp.busi.cms.floorgds.vo.CmsFloorGdsVO;
import com.zengshi.ecp.busi.goods.vo.GdsManageVO;
import com.zengshi.ecp.busi.order.util.ParamsTool;
import com.zengshi.ecp.cms.dubbo.dto.CmsFloorGdsReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsFloorGdsRespDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsFloorReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsFloorRespDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsFloorTabReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsFloorTabRespDTO;
import com.zengshi.ecp.cms.dubbo.interfaces.ICmsFloorGdsRSV;
import com.zengshi.ecp.cms.dubbo.interfaces.ICmsFloorRSV;
import com.zengshi.ecp.cms.dubbo.interfaces.ICmsFloorTabRSV;
import com.zengshi.ecp.cms.dubbo.interfaces.ICmsPlaceRSV;
import com.zengshi.ecp.cms.dubbo.util.CmsConstants;
import com.zengshi.ecp.cms.dubbo.util.CmsConstants.ParamStatus;
import com.zengshi.ecp.goods.dubbo.constants.GdsConstants;
import com.zengshi.ecp.goods.dubbo.constants.GdsOption.GdsQueryOption;
import com.zengshi.ecp.goods.dubbo.dto.GdsMediaRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfo.GdsInfoReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfo.GdsInfoRespDTO;
import com.zengshi.ecp.goods.dubbo.interfaces.IGdsInfoQueryRSV;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.staff.dubbo.dto.ShopInfoResDTO;
import com.zengshi.ecp.staff.dubbo.interfaces.IShopInfoRSV;
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
 * @author jiangzh
 * @version  
 * @since JDK 1.6
 */
@Controller
@RequestMapping(value="/floorgds")
public class CmsFloorGdsController extends EcpBaseController {
    
    private static String MODULE = CmsFloorGdsController.class.getName();
    
    private String URL = "/cms/floor/gds/";//返回页面的基本路径 
    private String URL_OPEN = "/cms/floor/gds/open/";//返回页面的弹出窗路径
    
    @Resource(name="cmsFloorGdsRSV")
    private ICmsFloorGdsRSV cmsFloorGdsRSV;
    @Resource(name="cmsPlaceRSV")
    private ICmsPlaceRSV cmsPlaceRSV;
    @Resource(name="cmsFloorRSV")
    private ICmsFloorRSV cmsFloorRSV;
    @Resource(name="cmsFloorTabRSV")
    private ICmsFloorTabRSV cmsFloorTabRSV;
    @Resource(name="gdsInfoQueryRSV")
    private IGdsInfoQueryRSV gdsInfoQueryRSV;
    @Resource(name="shopInfoRSV")
    private IShopInfoRSV shopInfoRSV;

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
    	if(StringUtil.isNotEmpty(floorId)){
            model.addAttribute("floorTabList",this.queryFloorTabList(Long.valueOf(floorId),CmsConstants.ParamStatus.CMS_PARAMSTATUS_1));
        }
    	model.addAttribute("floorId", floorId);
        return URL+"gds-grid";
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
            @ModelAttribute("floorSearchParams") String floorSearchParams)throws Exception {
   	    /*1.验证入参*/
        if(StringUtil.isBlank(id)){
            String[] keyInfos = new String[1];
            keyInfos[0]="id";
           throw new BusinessException("cms.common.param.null.error",keyInfos); 
        }
        
        /*2.根据楼层ID获取楼层信息*/
        CmsFloorGdsReqDTO reqDTO = new CmsFloorGdsReqDTO();
        reqDTO.setId(Long.valueOf(id));
        try {
            CmsFloorGdsRespDTO respDTO = cmsFloorGdsRSV.queryCmsFloorGds(reqDTO);
            
            //3. 调用商品，返回商品信息
            if(respDTO != null && respDTO.getGdsId() != null){
                GdsInfoReqDTO gdsInfoReqDTO = new GdsInfoReqDTO();
                gdsInfoReqDTO.setId(respDTO.getGdsId());
                GdsInfoRespDTO gdsInfoRespDTO = gdsInfoQueryRSV.queryGdsInfoByOption(gdsInfoReqDTO);
                if(gdsInfoRespDTO != null){
                    respDTO.setGdsName(gdsInfoRespDTO.getGdsName());
                } 
            }
            if(respDTO != null && respDTO.getFloorId() != null){
                model.addAttribute("floorTabList",this.queryFloorTabList(respDTO.getFloorId(),CmsConstants.ParamStatus.CMS_PARAMSTATUS_1));
            }
            model.addAttribute("respDTO",respDTO);
        } catch (BusinessException e) {
            // TODO: handle exception
            throw new BusinessException(e.getErrorCode());
        }
        
        /*3.返回信息*/
        return URL+"gds-view";
   	}
   	
    /** 
     * add:(这里用一句话描述这个方法的作用). <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author jiangzh 
     * @param model
     * @param floorId
     * @return 
     * @since JDK 1.6 
     */ 
    @RequestMapping(value="/{addType}add")
    public String add(Model model,
            @PathVariable("addType")String addType,
            @RequestParam("floorId") String floorId,
            @RequestParam(value="tabId",required=false) String tabId,
            @ModelAttribute("searchParams") String searchParams,
            @ModelAttribute("floorSearchParams") String floorSearchParams) throws Exception{
        /*1.通过楼层ID查询楼层*/
        String url = "gds-edit";
        if(StringUtil.isNotBlank(addType) && "batch".equals(addType)){//批量新增
            url = "gds-batch-add";
        }
        
        CmsFloorReqDTO reqDTO = new CmsFloorReqDTO();
        if(StringUtil.isNotEmpty(floorId)){
            reqDTO.setId(Long.valueOf(floorId));
        }
        CmsFloorGdsRespDTO respDTO = null;
        if(StringUtil.isNotEmpty(tabId)){
            respDTO = new CmsFloorGdsRespDTO();
            respDTO.setTabId(Long.valueOf(tabId));
        }
        /*2.copy对象*/
        CmsFloorRespDTO floorRespDTO = cmsFloorRSV.queryCmsFloor(reqDTO);
        if(floorRespDTO!=null){
            model.addAttribute("floorTabList",this.queryFloorTabList(floorRespDTO.getId(),CmsConstants.ParamStatus.CMS_PARAMSTATUS_1));
        }
        model.addAttribute("floorRespDTO",floorRespDTO);
        model.addAttribute("respDTO",respDTO);
        
        /*3.返回给页面*/
        return URL+url;
    }
    /**
     * 
     * getSelectedGds:(这里用一句话描述这个方法的作用). <br/> 
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
    @RequestMapping(value="/getselectedgds")
    @ResponseBody
    public  EcpBaseResponseVO getSelectedGds(CmsFloorGdsVO VO){
      //查找已经选择的商品
        List<CmsFloorGdsRespDTO> selGdsList = null;
        try {
            selGdsList = this.qrySelectedGds(VO.getFloorId(),VO.getTabId());
        } catch (Exception e) {
            LogUtil.error(MODULE, "查询已选择的商品方法异常",e);
        }
        String selGdsStrs = ",";
        if(CollectionUtils.isNotEmpty(selGdsList)){
            for(CmsFloorGdsRespDTO floorGds : selGdsList){
                if(StringUtil.isNotEmpty(floorGds.getGdsId())){
                    selGdsStrs += String.valueOf(floorGds.getGdsId())+",";
                }
            }
        }
        
        EcpBaseResponseVO resultVo = new EcpBaseResponseVO();
        resultVo.setResultMsg(selGdsStrs);
        return resultVo;
    }
    /**
     * 
     * qrySelectedGds:(获取楼层已选择的商品). <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author zhanbh 
     * @param floorId
     * @param tabId
     * @return 
     * @since JDK 1.6
     */
    private List<CmsFloorGdsRespDTO> qrySelectedGds(Long floorId , Long tabId){
        LogUtil.info(MODULE, "qrySelectedGds获取楼层已选择的商品开始：");
        if(StringUtil.isEmpty(floorId) && StringUtil.isEmpty(tabId)){//参数为空  则返回空
            return new ArrayList<CmsFloorGdsRespDTO>();
        }
        CmsFloorGdsReqDTO reqDto = new CmsFloorGdsReqDTO();
        reqDto.setFloorId(floorId);
        reqDto.setTabId(tabId);
        
        //状态为发布以及未发布 都为已选择的
        reqDto.setStatusSet(new HashSet<String>(Arrays.asList(ParamStatus.CMS_PARAMSTATUS_0,ParamStatus.CMS_PARAMSTATUS_1)));
        return this.cmsFloorGdsRSV.queryCmsFloorGdsList(reqDto);
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
            @RequestParam("floorId")String floorId,
            @ModelAttribute("searchParams") String searchParams,
            @ModelAttribute("floorSearchParams") String floorSearchParams) throws Exception{
        /*1.验证入参*/
        if(StringUtil.isBlank(id)){
            String[] keyInfos = new String[1];
            keyInfos[0]="id";
           throw new BusinessException("cms.common.param.null.error",keyInfos); 
        }
        /*2.通过楼层ID查询楼层*/
        CmsFloorReqDTO reqDTO = new CmsFloorReqDTO();
        if(StringUtil.isNotBlank(floorId)){
            reqDTO.setId(Long.valueOf(floorId));
        }
        /*3.查询楼层*/
        CmsFloorRespDTO floorRespDTO = cmsFloorRSV.queryCmsFloor(reqDTO);
        if(floorRespDTO!=null){
            model.addAttribute("floorTabList",this.queryFloorTabList(floorRespDTO.getId(),CmsConstants.ParamStatus.CMS_PARAMSTATUS_1));
        }
        model.addAttribute("floorRespDTO",floorRespDTO);
        /*4.根据楼层ID获取楼层信息*/
        CmsFloorGdsReqDTO floorGdsReqDTO = new CmsFloorGdsReqDTO();
        floorGdsReqDTO.setId(Long.valueOf(id));
        CmsFloorGdsRespDTO respDTO = cmsFloorGdsRSV.queryCmsFloorGds(floorGdsReqDTO);
        //5. 调用商品，返回商品信息
        if(respDTO != null && respDTO.getGdsId() != null){
            GdsInfoReqDTO gdsInfoReqDTO = new GdsInfoReqDTO();
            gdsInfoReqDTO.setId(respDTO.getGdsId());
            GdsInfoRespDTO gdsInfoRespDTO = gdsInfoQueryRSV.queryGdsInfoByOption(gdsInfoReqDTO);
            if(gdsInfoRespDTO != null){
                respDTO.setGdsName(gdsInfoRespDTO.getGdsName());
            } 
        }
        model.addAttribute("respDTO",respDTO);
        
        /*3.返回信息*/
        return URL+"gds-edit";
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
    public EcpBaseResponseVO changestatus(Model model,
            @RequestParam("ids")String ids,
            @RequestParam("status")String status) throws Exception{
        LogUtil.info(MODULE,"==========ids:"+ids+";");
        if(StringUtil.isBlank(ids)){
            String[] keyInfos = new String[1];
            keyInfos[0]="ids";
            throw new BusinessException("cms.common.param.null.error",keyInfos); 
         }
         if(StringUtil.isBlank(status)){
             String[] keyInfos = new String[1];
             keyInfos[0]="status";
             throw new BusinessException("cms.common.param.null.error",keyInfos); 
         }
        String[] idsArray = ids.split(",");
        List<String> list = Arrays.asList(idsArray);
        if(CollectionUtils.isEmpty(list)){
            throw new BusinessException("入参ids为空！");
        }
        cmsFloorGdsRSV.changeStatusCmsFloorGdsBatch(list, status);
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
        if(StringUtil.isBlank(ids)){
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
        cmsFloorGdsRSV.deleteCmsFloorGdsBatch(list);
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
     * @param cmsFloorGdsVO
     * @return 
     * @since JDK 1.6 
     */ 
    @RequestMapping(value="/pubsave")
    @ResponseBody
    public CmsFloorGdsRespDTO pubsave(@Valid CmsFloorGdsVO VO) throws Exception{
        LogUtil.info(MODULE,"==========" + JSONObject.toJSONString(VO));
        CmsFloorGdsReqDTO reqDTO = new CmsFloorGdsReqDTO();
        ObjectCopyUtil.copyObjValue(VO, reqDTO, null, true);
        reqDTO.setStatus(CmsConstants.ParamStatus.CMS_PARAMSTATUS_1);//发布
        //如果排序为空，则默认赋值为1.
        if(reqDTO.getSortNo() == null){
            reqDTO.setSortNo(1);
        }
        CmsFloorGdsRespDTO respDTO = new CmsFloorGdsRespDTO();
        if(reqDTO.getId() != null){
        	respDTO= cmsFloorGdsRSV.updateCmsFloorGds(reqDTO);
        }else{
        	respDTO= cmsFloorGdsRSV.addCmsFloorGds(reqDTO);
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
    public CmsFloorGdsRespDTO save(@Valid CmsFloorGdsVO VO) throws Exception{
        LogUtil.info(MODULE,"==========" + JSONObject.toJSONString(VO));
        CmsFloorGdsReqDTO reqDTO = new CmsFloorGdsReqDTO();
        ObjectCopyUtil.copyObjValue(VO, reqDTO, null, true);
        reqDTO.setStatus(CmsConstants.ParamStatus.CMS_PARAMSTATUS_0);//未发布
        //如果排序为空，则默认赋值为1.
        if(reqDTO.getSortNo() == null){
            reqDTO.setSortNo(1);
        }
        CmsFloorGdsRespDTO respDTO = new CmsFloorGdsRespDTO();
        if(VO.getId() != null){
            respDTO = cmsFloorGdsRSV.updateCmsFloorGds(reqDTO);
        }else{
            respDTO = cmsFloorGdsRSV.addCmsFloorGds(reqDTO);
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
    @RequestMapping(value="/batchsave")
    @ResponseBody
    public EcpBaseResponseVO batchSave(@Valid CmsFloorGdsBatchVO VO) throws Exception{
        LogUtil.info(MODULE,"==========" + JSONObject.toJSONString(VO));
        
        List<CmsFloorGdsReqDTO> reqList = VO.getFloorGdsList();
        
        //验证数据
        if(CollectionUtils.isNotEmpty(reqList)){
            for(int i = 0 ; i < reqList.size();i++){
                CmsFloorGdsReqDTO reqDto= reqList.get(i);
                if(StringUtil.isEmpty(reqDto.getFloorId()) || StringUtil.isEmpty(reqDto.getGdsId())){
                    reqList.remove(i);
                    i--;
                    continue;
                }
                
                if(StringUtil.isEmpty(reqDto.getSortNo())){
                    reqDto.setSortNo(1);
                }
                
                if(StringUtil.isBlank(reqDto.getStatus())){
                    reqDto.setStatus(CmsConstants.ParamStatus.CMS_PARAMSTATUS_0);
                }
                
                if(StringUtil.isBlank(reqDto.getIsProm())){
                    reqDto.setIsProm(CmsConstants.IsNot.CMS_ISNOT_0);
                }
            }
        }
        
        //保存入库
        EcpBaseResponseVO resultVo = new EcpBaseResponseVO();
        try {
            cmsFloorGdsRSV.addCmsFloorGdsBatch(reqList);
            resultVo.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_SUCCESS);
        } catch (Exception e) {
            LogUtil.error(MODULE, "批量新增楼层商品失败", e);
            resultVo.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_EXCEPTION);
        }
        return resultVo;
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
    public Model gridList(Model model, @ModelAttribute("searchVO") CmsFloorGdsVO searchVO) throws Exception{
        LogUtil.info(MODULE,"==========" + JSONObject.toJSONString(searchVO));
        //1. 调用后场服务所需要的DTO；
        CmsFloorGdsReqDTO reqDTO = searchVO.toBaseInfo(CmsFloorGdsReqDTO.class);
        ObjectCopyUtil.copyObjValue(searchVO, reqDTO, "MODULE", true);
        
        //2. 调用后场服务，将后场返回的分页对象封装为前店所需要的分页对象；
        PageResponseDTO<CmsFloorGdsRespDTO> pageInfo = cmsFloorGdsRSV.queryCmsFloorGdsPage(reqDTO);
        //3. 调用商品，返回商品信息
        List<CmsFloorGdsRespDTO> respList = pageInfo.getResult();
        if(CollectionUtils.isNotEmpty(respList)){
            for(CmsFloorGdsRespDTO dto:respList){
                if(StringUtil.isNotEmpty(dto.getGdsId())){
                    GdsInfoReqDTO gdsInfoReqDTO = new GdsInfoReqDTO();
                    gdsInfoReqDTO.setId(dto.getGdsId());
                    GdsQueryOption[] gdsOptions = new GdsQueryOption[] { 
                            GdsQueryOption.BASIC, GdsQueryOption.MAINPIC 
                            };
                    gdsInfoReqDTO.setGdsQueryOptions(gdsOptions);
                    GdsInfoRespDTO gdsInfoRespDTO = gdsInfoQueryRSV.queryGdsInfoByOption(gdsInfoReqDTO);
                    if(gdsInfoRespDTO != null){
                        dto.setGdsName(gdsInfoRespDTO.getGdsName());
                        //  商品图片
                        GdsMediaRespDTO gdsMediaRespDTO = gdsInfoRespDTO.getMainPic();
                        if(gdsMediaRespDTO == null){
                            gdsMediaRespDTO = new GdsMediaRespDTO();
                        }
                        dto.setGdsImgUrl(ParamsTool.getImageUrl(gdsMediaRespDTO.getMediaUuid(), "120x50!"));
                    } 
                }
            }
        }
       
        EcpBasePageRespVO<Map> respVO = EcpBasePageRespVO.buildByPageResponseDTO(pageInfo);
        return super.addPageToModel(model, respVO);
    }
    
    /** 选择商品内容弹出框
     * opengds:(这里用一句话描述这个方法的作用). <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author jiangzh 
     * @return 
     * @since JDK 1.6 
     */ 
    @RequestMapping("/opengds")
    public String opengds(Model model,@RequestParam("siteId")String siteId) throws Exception{
    	model.addAttribute("siteId",siteId);
        return URL_OPEN + "gds-grid";
    } 
    /** 选择商品内容弹出框
     * opengds:(这里用一句话描述这个方法的作用). <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author jiangzh 
     * @return 
     * @since JDK 1.6 
     */ 
    @RequestMapping("/openbatchgds")
    public String openBatchGds(Model model,@RequestParam("siteId")String siteId) throws Exception{
        model.addAttribute("siteId",siteId);
        return URL_OPEN + "batch-gds-grid";
    }
    /** 批量选择促销商品内容弹出框
     * opengds:(这里用一句话描述这个方法的作用). <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author jiangzh 
     * @return 
     * @since JDK 1.6 
     */ 
    @RequestMapping("/openpromgds")
    public String openpromgds(Model model,@RequestParam("siteId")String siteId) throws Exception{
        //CmsFloorReqDTO cmsFloorReqDTO = new CmsFloorReqDTO();
        //cmsFloorReqDTO.setId(Long.valueOf(floorId));
        //CmsFloorRespDTO floorRespDTO = cmsFloorRSV.queryCmsFloor(cmsFloorReqDTO);
        model.addAttribute("siteId",siteId);
        return URL_OPEN + "prom-gds-grid";
    }
    /** 批量选择促销商品内容弹出框
     * opengds:(这里用一句话描述这个方法的作用). <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author jiangzh 
     * @return 
     * @since JDK 1.6 
     */ 
    @RequestMapping("/openbatchpromgds")
    public String openbatchpromgds(Model model,@RequestParam("siteId")String siteId) throws Exception{
        model.addAttribute("siteId",siteId);
        return URL_OPEN + "batch-prom-gds-grid";
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
    @RequestMapping("/querygds")
    public Model querygds(Model model, @ModelAttribute("searchVO") GdsManageVO searchVO,@RequestParam("floorId")String floorId) throws Exception{
        LogUtil.info(MODULE,"==========" + JSONObject.toJSONString(searchVO));
        //1. 调用后场服务所需要的DTO；
        GdsInfoReqDTO reqDTO = searchVO.toBaseInfo(GdsInfoReqDTO.class);
        ObjectCopyUtil.copyObjValue(searchVO, reqDTO, "MODULE", true);
        reqDTO.setGdsStatus(GdsConstants.GdsInfo.GDS_STATUS_ONSHELVES);//已上架
        
        Long siteLong = 0L;
        if(StringUtil.isNotEmpty(floorId)){
            CmsFloorReqDTO cmsFloorReqDTO = new CmsFloorReqDTO();
            cmsFloorReqDTO.setId(Long.valueOf(floorId));
            CmsFloorRespDTO cmsFloorRespDTO = cmsFloorRSV.queryCmsFloor(cmsFloorReqDTO);
            siteLong = cmsFloorRespDTO.getSiteId();
            if(siteLong == 1L){
                reqDTO.setIfScoreGds("0");
            }else{
                reqDTO.setIfScoreGds("1");
            }
        }
        
        PageResponseDTO<GdsInfoRespDTO> pageInfo = gdsInfoQueryRSV.queryGdsInfoListPage(reqDTO);
        List<GdsInfoRespDTO> respList = null;
        if(pageInfo != null){
            respList = pageInfo.getResult();
        } 
        if(!CollectionUtils.isEmpty(respList)){
            for(GdsInfoRespDTO gdsInfoRespDTO:respList){
                if(gdsInfoRespDTO != null && StringUtil.isNotEmpty(gdsInfoRespDTO.getShopId())){
                    //3.2调用店铺，返回店铺信息
                    ShopInfoResDTO shopInfoRespDTO = shopInfoRSV.findShopInfoByShopID(gdsInfoRespDTO.getShopId());
                    if(shopInfoRespDTO != null){
                        gdsInfoRespDTO.setGdsSubHead(shopInfoRespDTO.getShopName());//将商品副标题，当成店铺名称用。
                    } 
                }
            }
        }
        EcpBasePageRespVO<Map> respVO = EcpBasePageRespVO.buildByPageResponseDTO(pageInfo);
        
        //2. 调用后场服务，将后场返回的分页对象封装为前店所需要的分页对象；
        return super.addPageToModel(model, respVO);
    }
    
    /** 
     * queryFloorTabList:(这里用一句话描述这个方法的作用). <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author jiangzh 
     * @param floorId
     * @return
     * @throws Exception 
     * @since JDK 1.6 
     */ 
    private List<CmsFloorTabRespDTO> queryFloorTabList(Long floorId,String status) throws Exception{
        CmsFloorTabReqDTO cmsFloorTabReqDTO = new CmsFloorTabReqDTO();
        if(StringUtil.isNotEmpty(floorId)){
            cmsFloorTabReqDTO.setFloorId(Long.valueOf(floorId));
        }
        if(StringUtil.isNotBlank(status)){ 
            cmsFloorTabReqDTO.setStatus(status);//有效
        }
        List<CmsFloorTabRespDTO> cmsFloorTabRespDTOList = cmsFloorTabRSV.queryCmsFloorTabList(cmsFloorTabReqDTO);
        return cmsFloorTabRespDTOList;
    }

}
