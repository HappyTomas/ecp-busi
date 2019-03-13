package com.zengshi.ecp.busi.cms.flooradvertise.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
import com.zengshi.ecp.busi.cms.flooradvertise.vo.CmsFloorAdvertiseVO;
import com.zengshi.ecp.cms.dubbo.dto.CmsFloorAdvertiseReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsFloorAdvertiseRespDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsFloorReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsFloorRespDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsInfoReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsInfoRespDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsPageInfoReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsPageInfoRespDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsPlaceReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsPlaceRespDTO;
import com.zengshi.ecp.cms.dubbo.interfaces.ICmsFloorAdvertiseRSV;
import com.zengshi.ecp.cms.dubbo.interfaces.ICmsFloorRSV;
import com.zengshi.ecp.cms.dubbo.interfaces.ICmsInfoRSV;
import com.zengshi.ecp.cms.dubbo.interfaces.ICmsPageInfoRSV;
import com.zengshi.ecp.cms.dubbo.interfaces.ICmsPlaceRSV;
import com.zengshi.ecp.cms.dubbo.util.CmsConstants;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfo.GdsInfoReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfo.GdsInfoRespDTO;
import com.zengshi.ecp.goods.dubbo.interfaces.IGdsInfoQueryRSV;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.staff.dubbo.dto.ShopInfoResDTO;
import com.zengshi.ecp.staff.dubbo.interfaces.IShopInfoRSV;
import com.zengshi.paas.utils.ImageUtil;
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
@RequestMapping(value="/flooradvertise")
public class CmsFloorAdvertiseController extends EcpBaseController {
    
    private static String MODULE = CmsFloorAdvertiseController.class.getName();
    
    private String URL = "/cms/floor/advertise/advertise";//返回页面的基本路径 
    private String URL_OPEN = "/cms/floor/advertise/open/advertise";//返回页面的弹出窗路径
    
    @Resource(name="cmsFloorAdvertiseRSV")
    private ICmsFloorAdvertiseRSV cmsFloorAdvertiseRSV;
    @Resource(name="cmsPlaceRSV")
    private ICmsPlaceRSV cmsPlaceRSV;
    @Resource(name="cmsFloorRSV")
    private ICmsFloorRSV cmsFloorRSV;
    @Resource(name="shopInfoRSV")
    private IShopInfoRSV shopInfoRSV;
    @Resource(name="cmsInfoRSV")
    private ICmsInfoRSV cmsInfoRSV;
    @Resource(name="gdsInfoQueryRSV")
    private IGdsInfoQueryRSV gdsInfoQueryRSV;
    @Resource(name = "cmsPageInfoRSV")
    private ICmsPageInfoRSV cmsPageInfoRSV;

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
        return URL+"-grid";
    }
   
    /**
   	 * 查看页面初始化方法 flooradvertiseView:(这里用一句话描述这个方法的作用). <br/>
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
   	        @ModelAttribute("searchParams") String searchParams) {
   	    /*1.验证入参*/
        if(StringUtils.isBlank(id)){
            String[] keyInfos = new String[1];
            keyInfos[0]="id";
           throw new BusinessException("cms.common.param.null.error",keyInfos); 
        }
        
        /*2.根据楼层ID获取楼层信息*/
        CmsFloorAdvertiseReqDTO reqDTO = new CmsFloorAdvertiseReqDTO();
        reqDTO.setId(Long.valueOf(id));
        try {
            CmsFloorAdvertiseRespDTO cmsAdvertiseRespDTO = cmsFloorAdvertiseRSV.queryCmsFloorAdvertise(reqDTO);
            this.extendAd(cmsAdvertiseRespDTO, "link,image,shop");
            model.addAttribute("respDTO",cmsAdvertiseRespDTO);
        } catch (BusinessException e) {
            // TODO: handle exception
            throw new BusinessException(e.getErrorCode());
        }
        /*3.返回信息*/
        return URL+"-view";
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
    @RequestMapping(value="/add")
    public String add(Model model,
            @RequestParam("floorId")String floorId,
            @ModelAttribute("searchParams") String searchParams) throws Exception{
        /*1.通过楼层ID查询楼层*/
        CmsFloorReqDTO reqDTO = new CmsFloorReqDTO();
        if(StringUtils.isNotBlank(floorId)){
            reqDTO.setId(Long.valueOf(floorId));
        }
        /*2.copy对象*/
        CmsFloorAdvertiseRespDTO respDTO = new CmsFloorAdvertiseRespDTO();
        CmsFloorRespDTO cmsFloorRespDTO = cmsFloorRSV.queryCmsFloor(reqDTO);
        if(cmsFloorRespDTO!=null){
            respDTO.setFloorId(cmsFloorRespDTO.getId());
            respDTO.setPlaceId(cmsFloorRespDTO.getPlaceId());
            respDTO.setFloorName(cmsFloorRespDTO.getFloorName());
        }
        /*3.返回给页面*/
        model.addAttribute("respDTO",respDTO);
        model.addAttribute("floorInfo",cmsFloorRespDTO);
        return URL+"-edit";
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
        /*1.验证入参*/
        if(StringUtils.isBlank(id)){
            String[] keyInfos = new String[1];
            keyInfos[0]="id";
           throw new BusinessException("cms.common.param.null.error",keyInfos); 
        }
        
        /*2.根据楼层ID获取楼层信息*/
        CmsFloorAdvertiseReqDTO reqDTO = new CmsFloorAdvertiseReqDTO();
        reqDTO.setId(Long.valueOf(id));
        try {
            CmsFloorAdvertiseRespDTO cmsAdvertiseRespDTO = cmsFloorAdvertiseRSV.queryCmsFloorAdvertise(reqDTO);
            
            this.extendAd(cmsAdvertiseRespDTO,"link");
            
            model.addAttribute("respDTO",cmsAdvertiseRespDTO);
            /*通过楼层ID查询楼层*/
            if(cmsAdvertiseRespDTO!=null && StringUtil.isNotEmpty(cmsAdvertiseRespDTO.getFloorId())){
                CmsFloorReqDTO floorReqDTO = new CmsFloorReqDTO();
                floorReqDTO.setId(cmsAdvertiseRespDTO.getFloorId());
                CmsFloorRespDTO cmsFloorRespDTO = cmsFloorRSV.queryCmsFloor(floorReqDTO);
                model.addAttribute("floorInfo",cmsFloorRespDTO);
            }
            
        } catch (BusinessException e) {
            // TODO: handle exception
            throw new BusinessException(e.getErrorCode());
        }
        
        /*3.返回信息*/
        return URL+"-edit";
    }
    
    /**
     * 
     * extendAd:(根据需求  扩展字段). <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author zhanbh 
     * @param cmsAdvertiseRespDTO
     * @param string  完整格式为"link,image,shop"，如有需要，自行新增
     * @throws Exception 
     * @since JDK 1.6
     */
    private void extendAd(CmsFloorAdvertiseRespDTO dto, String options) {
        if(dto!=null && StringUtil.isNotBlank(options)){
            String[] optionStrs = options.split(",");
            if(optionStrs !=null && optionStrs.length > 0){
                for(String option : optionStrs){
                    switch(option){
                        case "link"://扩展链接名称
                            if(StringUtil.isNotBlank(dto.getLinkType())){
                                switch (dto.getLinkType()){
                                    case CmsConstants.LinkType.CMS_LINKTYPE_01://商品
                                        if(this.isNumeric(dto.getLinkUrl())){
                                            GdsInfoReqDTO gdsReqDTO = new GdsInfoReqDTO();
                                            gdsReqDTO.setId(Long.valueOf(dto.getLinkUrl()));
                                            GdsInfoRespDTO gds = gdsInfoQueryRSV.queryGdsInfo(gdsReqDTO);
                                            if(gds != null && StringUtil.isNotBlank(gds.getGdsName())){
                                                dto.setLinkName(gds.getGdsName());
                                            }
                                        }
                                        break;
                                    case CmsConstants.LinkType.CMS_LINKTYPE_02://公告
                                        if(this.isNumeric(dto.getLinkUrl())){
                                            CmsInfoReqDTO infoReqDTO = new CmsInfoReqDTO();
                                            infoReqDTO.setId(Long.valueOf(dto.getLinkUrl()));
                                            CmsInfoRespDTO info = cmsInfoRSV.queryCmsInfo(infoReqDTO);
                                            if(info != null && StringUtil.isNotBlank(info.getInfoTitle())){
                                                dto.setLinkName(info.getInfoTitle());
                                            }
                                        }
                                        break;
                                    case CmsConstants.LinkType.CMS_LINKTYPE_03://促销
                                        if(this.isNumeric(dto.getLinkUrl())){
                                            CmsPageInfoReqDTO pageReqDTO = new CmsPageInfoReqDTO();
                                            pageReqDTO.setId(Long.valueOf(dto.getLinkUrl()));
                                            CmsPageInfoRespDTO page = cmsPageInfoRSV.queryCmsPageInfo(pageReqDTO);
                                            if(page != null && StringUtil.isNotBlank(page.getPageName())){
                                                dto.setLinkName(page.getPageName());
                                            }
                                        }
                                        break;
                                    case CmsConstants.LinkType.CMS_LINKTYPE_04://其他
                                        dto.setLinkName(dto.getLinkUrl());
                                        break;
                                        
                                }
                            }
                            break;
                        case "image"://扩展图片地址
                            dto.setVfsUrl(ImageUtil.getImageUrl(dto.getVfsId()+"_"+"120x50!"));
                            break;
                        case "shop"://扩展店铺名称
                            if(dto.getShopId() != null){
                                ShopInfoResDTO shopInfoRespDTO = shopInfoRSV.findShopInfoByShopID(dto.getShopId());
                                if(shopInfoRespDTO != null){
                                    dto.setShopName(shopInfoRespDTO.getShopName());
                                } 
                            }
                            break;
                    }
                }
            }
         }
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
        cmsFloorAdvertiseRSV.changeStatusCmsFloorAdvertiseBatch(list, status);
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
        cmsFloorAdvertiseRSV.deleteCmsFloorAdvertiseBatch(list);
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
     * @param cmsFloorAdvertiseVO
     * @return 
     * @since JDK 1.6 
     */ 
    @RequestMapping(value="/pubsave")
    @ResponseBody
    public CmsFloorAdvertiseRespDTO pubsave(@Valid CmsFloorAdvertiseVO VO) throws Exception{
        LogUtil.info(MODULE,"==========" + JSONObject.toJSONString(VO));
        CmsFloorAdvertiseReqDTO reqDTO = new CmsFloorAdvertiseReqDTO();
        ObjectCopyUtil.copyObjValue(VO, reqDTO, null, true);
        reqDTO.setStatus(CmsConstants.ParamStatus.CMS_PARAMSTATUS_1);//发布
        //如果排序为空，则默认赋值为1.
        if(reqDTO.getSortNo() == null){
            reqDTO.setSortNo(1);
        }
        if(CmsConstants.LinkType.CMS_LINKTYPE_04.equalsIgnoreCase(reqDTO.getLinkType()) && StringUtil.isBlank(reqDTO.getLinkUrl())){//其他
            reqDTO.setLinkUrl(VO.getLinkName());
        }
        CmsFloorAdvertiseRespDTO respDTO = new CmsFloorAdvertiseRespDTO();
        if(reqDTO.getId() != null){
        	respDTO= cmsFloorAdvertiseRSV.updateCmsFloorAdvertise(reqDTO);
        }else{
        	respDTO= cmsFloorAdvertiseRSV.addCmsFloorAdvertise(reqDTO);
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
    public CmsFloorAdvertiseRespDTO save(@Valid CmsFloorAdvertiseVO VO) throws Exception{
        LogUtil.info(MODULE,"==========" + JSONObject.toJSONString(VO));
        CmsFloorAdvertiseReqDTO reqDTO = new CmsFloorAdvertiseReqDTO();
        ObjectCopyUtil.copyObjValue(VO, reqDTO, null, true);
        reqDTO.setStatus(CmsConstants.ParamStatus.CMS_PARAMSTATUS_0);//未发布
        //如果排序为空，则默认赋值为1.
        if(reqDTO.getSortNo() == null){
            reqDTO.setSortNo(1);
        }
        if(CmsConstants.LinkType.CMS_LINKTYPE_04.equalsIgnoreCase(reqDTO.getLinkType()) && StringUtil.isBlank(reqDTO.getLinkUrl())){//其他
            reqDTO.setLinkUrl(VO.getLinkName());
        }
        CmsFloorAdvertiseRespDTO respDTO = new CmsFloorAdvertiseRespDTO();
        if(VO.getId() != null){
            respDTO = cmsFloorAdvertiseRSV.updateCmsFloorAdvertise(reqDTO);
        }else{
            respDTO = cmsFloorAdvertiseRSV.addCmsFloorAdvertise(reqDTO);
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
    public Model gridList(Model model, @ModelAttribute("searchVO") CmsFloorAdvertiseVO searchVO) throws Exception{
        LogUtil.info(MODULE,"==========" + JSONObject.toJSONString(searchVO));
        //1. 调用后场服务所需要的DTO；
        CmsFloorAdvertiseReqDTO reqDTO = searchVO.toBaseInfo(CmsFloorAdvertiseReqDTO.class);
        ObjectCopyUtil.copyObjValue(searchVO, reqDTO, "MODULE", true);
        
        //2. 调用后场服务，将后场返回的分页对象封装为前店所需要的分页对象；
        PageResponseDTO<CmsFloorAdvertiseRespDTO> pageInfo = cmsFloorAdvertiseRSV.queryCmsFloorAdvertisePage(reqDTO);
        
        //3. 调文件服务器，返回图片，调用店铺，返回店铺信息
        List<CmsFloorAdvertiseRespDTO> respList = pageInfo.getResult();
        if(CollectionUtils.isNotEmpty(respList)){
            for(CmsFloorAdvertiseRespDTO dto:respList){
                this.extendAd(dto, "image,shop");
            }
        }
        
        EcpBasePageRespVO<Map> respVO = EcpBasePageRespVO.buildByPageResponseDTO(pageInfo);
        return super.addPageToModel(model, respVO);
    }
    
    /** 选择商品内容弹出框
     * openinfo:(这里用一句话描述这个方法的作用). <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author jiangzh 
     * @return 
     * @since JDK 1.6 
     */ 
    @RequestMapping("/openadvertise")
    public String openadvertise(Model model) throws Exception{
        model.addAttribute("placeList",this.queryPlaceList(null,CmsConstants.PlaceType.CMS_PlACE_TYPE_04,CmsConstants.ParamStatus.CMS_PARAMSTATUS_1));
        return URL_OPEN + "-grid";
    } 

    /** 
     * queryPlaceList:(获取内容位置列表). <br/> 
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
     * 
     * isNumeric:(判断字符串是否为纯数字). <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author zhanbh
     * @param str
     * @return 
     * @since JDK 1.6
     */
    private boolean isNumeric(String str){
        if(StringUtil.isNotEmpty(str)){
            Pattern pattern = Pattern.compile("[0-9]*"); 
            Matcher isNum = pattern.matcher(str);
            if( isNum.matches() ){
                return true; 
            }
        }
         
        return false; 
     }
}




