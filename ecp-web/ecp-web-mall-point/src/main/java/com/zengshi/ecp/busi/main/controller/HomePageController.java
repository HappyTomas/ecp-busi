package com.zengshi.ecp.busi.main.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.ArrayUtils;
import org.jsoup.Jsoup;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zengshi.ecp.base.controller.EcpBaseController;
import com.zengshi.ecp.busi.main.vo.ComponentReqVO;
import com.zengshi.ecp.cms.dubbo.dto.CmsFloorGdsReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsFloorGdsRespDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsFloorReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsFloorRespDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsFloorTabReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsFloorTabRespDTO;
import com.zengshi.ecp.cms.dubbo.interfaces.ICmsAdvertiseRSV;
import com.zengshi.ecp.cms.dubbo.interfaces.ICmsFloorGdsRSV;
import com.zengshi.ecp.cms.dubbo.interfaces.ICmsFloorRSV;
import com.zengshi.ecp.cms.dubbo.interfaces.ICmsFloorTabRSV;
import com.zengshi.ecp.cms.dubbo.interfaces.ICmsGdsCategoryRSV;
import com.zengshi.ecp.cms.dubbo.interfaces.ICmsInfoRSV;
import com.zengshi.ecp.cms.dubbo.interfaces.ICmsPlaceCategoryRSV;
import com.zengshi.ecp.cms.dubbo.interfaces.ICmsPlaceRSV;
import com.zengshi.ecp.cms.dubbo.util.CmsConstants;
import com.zengshi.ecp.goods.dubbo.constants.GdsOption.GdsQueryOption;
import com.zengshi.ecp.goods.dubbo.constants.GdsOption.SkuQueryOption;
import com.zengshi.ecp.goods.dubbo.dto.GdsMediaRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfo.GdsInfoDetailRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfo.GdsInfoReqDTO;
import com.zengshi.ecp.goods.dubbo.interfaces.IGdsCategoryRSV;
import com.zengshi.ecp.goods.dubbo.interfaces.IGdsInfoQueryRSV;
import com.zengshi.ecp.goods.dubbo.interfaces.IGdsSkuInfoQueryRSV;
import com.zengshi.ecp.order.dubbo.interfaces.IOrdMainRSV;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.system.util.ParamsTool;
import com.zengshi.paas.utils.FileUtil;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.StringUtil;

/**
 * Title: 积分商城-首页 <br>
 * Project Name:ecp-web-mall-point <br>
 * Description: <br>
 * Date:2015年11月2日下午10:04:58  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author jiangzh
 * @version  
 * @since JDK 1.6 
 */  
@Controller
@RequestMapping(value = "/homepage")
public class HomePageController extends EcpBaseController {

    private static String MODULE = HomePageController.class.getName();
    
    private final int GDSDESCCONTENT_LENGTH=115;
    
    //商品详情地址模板
    private final String DETAILlURl = "/gdspointdetail/gdsId-skuId";
    
    @Resource(name = "cmsAdvertiseRSV")
    private ICmsAdvertiseRSV cmsAdvertiseRSV;
    @Resource(name = "cmsInfoRSV")
    private ICmsInfoRSV cmsInfoRSV;
    @Resource(name = "cmsFloorRSV")
    private ICmsFloorRSV cmsFloorRSV;
    @Resource(name = "cmsFloorGdsRSV")
    private ICmsFloorGdsRSV cmsFloorGdsRSV;
    @Resource(name = "gdsInfoQueryRSV")
    private IGdsInfoQueryRSV gdsInfoQueryRSV;
    @Resource(name = "cmsFloorTabRSV")
    private ICmsFloorTabRSV cmsFloorTabRSV;
    @Resource(name = "cmsPlaceRSV")
    private ICmsPlaceRSV cmsPlaceRSV;
    //根据属性获取商品服务
    @Resource(name ="gdsSkuInfoQueryRSV" )
    private IGdsSkuInfoQueryRSV gdsSkuInfoQueryRSV;
    @Resource
    private IOrdMainRSV orderMainRSV;
    //分类树查询服务
    @Resource(name ="cmsPlaceCategoryRSV" )
    private ICmsPlaceCategoryRSV cmsPlaceCategoryRSV;
    //分类树查询服务
    @Resource(name ="cmsGdsCategoryRSV" )
    private ICmsGdsCategoryRSV cmsGdsCategoryRSV;
    //商品组分类树查询服务
    @Resource(name ="gdsCategoryRSV" )
    private IGdsCategoryRSV gdsCategoryRSV;
    
    /**
     * init:页面初始化，requestMapping如果不写的话，访问地址同：Controller类的 requestmapping的URL TODO(这里描述这个方法适用条件 –
     * 可选).<br/>
     * TODO(考虑用户体验，请求先显示页面的主体框架).<br/>
     * TODO(这里描述这个方法的使用方法 – 可选).<br/>
     * TODO(这里描述这个方法的注意事项 – 可选).<br/>
     * 
     * @author jiangzh
     * @return
     * @since JDK 1.6
     */
    @RequestMapping()
    public String init(Model model) {
        model.addAttribute("nav_type", "0");
        model.addAttribute("HTML_TITLE", "积分商城首页");
        return "/main/homepage/homepage-content";
    }
    
    
    
    /** 
     * queryGdsByTabId:(根据页签ID获取页签下的商品). <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author jiangzh 
     * @param model
     * @param reqVO
     * @return
     * @throws Exception 
     * @since JDK 1.6 
     */ 
    @RequestMapping(value = "/queryGdsByTabId")
    @ResponseBody
    public Map<String,Object> queryGdsByTabId(Model model,ComponentReqVO reqVO) throws Exception {
        LogUtil.info(MODULE, "==========reqVO:" + reqVO.toString() + ";");
        
        Map<String,Object> resultMap = new HashMap<String,Object>();
        List<GdsInfoDetailRespDTO> gdsInfoDetailRespList = null;
        
        // 1. 判断入参
        CmsFloorGdsReqDTO cmsFloorGdsReqDTO = new CmsFloorGdsReqDTO();
        if (StringUtil.isNotEmpty(reqVO.getTabId())) {//页签
            cmsFloorGdsReqDTO.setTabId(reqVO.getTabId());
        }
        if (StringUtil.isNotEmpty(reqVO.getStatus())) {//状态
            cmsFloorGdsReqDTO.setStatus(reqVO.getStatus());
        }
        if (StringUtil.isNotEmpty(reqVO.getGdsSize())) {//楼层商品数量
            cmsFloorGdsReqDTO.setPageNo(1);
            cmsFloorGdsReqDTO.setPageSize(reqVO.getGdsSize());
        }
        String standard = "";//规格
        if(StringUtil.isNotEmpty(reqVO.getPlaceWidth()) && StringUtil.isNotEmpty(reqVO.getPlaceHeight())){
            standard = reqVO.getPlaceWidth() + "x" + reqVO.getPlaceHeight() + "!";
        }
        try{
            // 2. 根据楼层页签ID获取商品
            List<CmsFloorGdsRespDTO> respList = new ArrayList<CmsFloorGdsRespDTO>();
            if(cmsFloorGdsReqDTO.getPageSize() != null && cmsFloorGdsReqDTO.getPageSize() != 0){
                /*PageResponseDTO<CmsFloorGdsRespDTO> pageInfo = cmsFloorGdsRSV.queryCmsFloorGdsPage(cmsFloorGdsReqDTO);
                if(pageInfo != null){
                    respList = pageInfo.getResult();
                } */
              //商品下架或删除商城页面自动不再显示
                PageResponseDTO<CmsFloorGdsRespDTO> pageInfo = null;
                for(int size = cmsFloorGdsReqDTO.getPageSize(),respSize = respList.size();respSize < size ;respSize = respList.size()){
                    pageInfo = cmsFloorGdsRSV.queryCmsFloorGdsPage(cmsFloorGdsReqDTO);
                    if(pageInfo != null && !CollectionUtils.isEmpty(pageInfo.getResult())){
                        for(CmsFloorGdsRespDTO dto : pageInfo.getResult()){
                            GdsInfoReqDTO gdsInfoReqDTO = new GdsInfoReqDTO();
                            GdsQueryOption[] gdsOptions = new GdsQueryOption[] {GdsQueryOption.BASIC};
                            gdsInfoReqDTO.setGdsQueryOptions(gdsOptions);
                            gdsInfoReqDTO.setId(dto.getGdsId());
                            GdsInfoDetailRespDTO gdsInfoDetailRespDTO = gdsInfoQueryRSV.queryGdsInfoDetail(gdsInfoReqDTO);
                            if(gdsInfoDetailRespDTO !=null && "11".equalsIgnoreCase(gdsInfoDetailRespDTO.getGdsStatus())){
                                respList.add(dto);
                            }
                            if(respList.size() >= size){
                                break;
                            }
                        }
                    }else{
                        break;
                    }
                    
                  //取下一页
                  cmsFloorGdsReqDTO.setPageNo(cmsFloorGdsReqDTO.getPageNo()+1);
                }
            }
            // 3 商品域  查询商品信息（单个）并根据页面要求处理商品
            gdsInfoDetailRespList = this.qryGdsDetailList(respList,standard);
        }catch(Exception err){
            LogUtil.error(MODULE, "查询查询商品出现异常:",err);
        }
        
        resultMap.put("gdsList", gdsInfoDetailRespList);
        return resultMap;
    }

    /** 
     * qryFloorVM:(根据内容位置获取楼层信息). <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author jiangzh 
     * @param model
     * @param reqVO
     * @return
     * @throws Exception 
     * @since JDK 1.6 
     */ 
    @RequestMapping(value = "/qryFloorVM")
    public String qryFloorVM(Model model,ComponentReqVO reqVO) throws Exception {

        LogUtil.info(MODULE, "==========reqVO:" + reqVO.toString() + ";");
        
        // 返回URL
        String url  = "/main/homepage/child/salefloor";
        if("salefloor".equals(reqVO.getMenuType())){
            url = "/main/homepage/child/salefloor";
        }else if("giftfloor".equals(reqVO.getMenuType())){
            url = "/main/homepage/child/giftfloor";
        }
        
        // 图片规格
        String standard = "";
        if (StringUtil.isNotEmpty(reqVO.getPlaceWidth())&& StringUtil.isNotEmpty(reqVO.getPlaceHeight())) {
            standard = reqVO.getPlaceWidth() + "x" + reqVO.getPlaceHeight() + "!";
        } 

        CmsFloorRespDTO floorRespDTO = null;
        List<CmsFloorTabRespDTO> floorTabRespList = null;
        List<GdsInfoDetailRespDTO> gdsInfoDetailRespList = null;
        
        try{
            // 1 根据内容位置ID查询有效楼层
            floorRespDTO = this.qryFloorByPlaceId(reqVO);
        
            // 2 当有效楼层不为空时，根据楼层ID查询页签
            if(floorRespDTO!=null && floorRespDTO.getId()!=null &&  StringUtil.isNotBlank(floorRespDTO.getId().toString())){
                CmsFloorTabReqDTO cmsFloorTabReqDTO = new CmsFloorTabReqDTO();
                cmsFloorTabReqDTO.setFloorId(floorRespDTO.getId());// 楼层ID
                floorTabRespList = this.qryFloorTabByFloorId(reqVO,cmsFloorTabReqDTO);
                
                // 3 当页签不为空时，获取第一个页签的商品，当页签为空时，获取楼层下的商品
                CmsFloorGdsReqDTO cmsFloorGdsReqDTO = new CmsFloorGdsReqDTO();
                cmsFloorGdsReqDTO.setFloorId(floorRespDTO.getId());// 楼层ID
                if(!CollectionUtils.isEmpty(floorTabRespList)){
                    CmsFloorTabRespDTO cmsFloorTabRespDTO = floorTabRespList.get(0);
                    if(cmsFloorTabRespDTO != null && StringUtil.isNotEmpty(cmsFloorTabRespDTO.getId())){
                        cmsFloorGdsReqDTO.setTabId(cmsFloorTabRespDTO.getId());
                    }
                }
                gdsInfoDetailRespList = this.qryFloorGdsList(reqVO,cmsFloorGdsReqDTO,standard);
            }
            
        }catch(Exception err){
            LogUtil.error(MODULE, "查询楼层信息出现异常:",err);
        }
        model.addAttribute("floorRespDTO", floorRespDTO);
        model.addAttribute("floorTabList", floorTabRespList);
        model.addAttribute("gdsList", gdsInfoDetailRespList);
        return url;
    }
    
    /** 
     * queryFloorInfoVM:(根据页签ID获取页签下的商品). <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author jiangzh 
     * @param model
     * @param reqVO
     * @return
     * @throws Exception 
     * @since JDK 1.6 
     */ 
    @RequestMapping(value = "/queryFloorInfoVM")
    public String queryFloorInfoVM(Model model,ComponentReqVO reqVO) throws Exception {
        LogUtil.info(MODULE, "==========reqVO:" + reqVO.toString() + ";");
        
        String url = "/main/homepage/child/floorinfo";
        List<GdsInfoDetailRespDTO> gdsInfoDetailRespList = null;

        String standard = "";//规格
        if(StringUtil.isNotEmpty(reqVO.getPlaceWidth()) && StringUtil.isNotEmpty(reqVO.getPlaceHeight())){
            standard = reqVO.getPlaceWidth() + "x" + reqVO.getPlaceHeight() + "!";
        }
        CmsFloorGdsReqDTO cmsFloorGdsReqDTO = new CmsFloorGdsReqDTO();
        if (StringUtil.isNotEmpty(reqVO.getTabId())) {//页签
            cmsFloorGdsReqDTO.setTabId(reqVO.getTabId());
        }
        if (StringUtil.isNotEmpty(reqVO.getStatus())) {//状态
            cmsFloorGdsReqDTO.setStatus(reqVO.getStatus());
        }
        if (StringUtil.isNotEmpty(reqVO.getGdsSize())) {//楼层商品数量
            cmsFloorGdsReqDTO.setPageNo(1);
            cmsFloorGdsReqDTO.setPageSize(reqVO.getGdsSize());
        }
        
        // 2. 根据楼层页签ID获取商品
        try{
            List<CmsFloorGdsRespDTO> respList = new ArrayList<CmsFloorGdsRespDTO>();
            if(cmsFloorGdsReqDTO.getPageSize() != null && cmsFloorGdsReqDTO.getPageSize() != 0){
                /*PageResponseDTO<CmsFloorGdsRespDTO> pageInfo = cmsFloorGdsRSV.queryCmsFloorGdsPage(cmsFloorGdsReqDTO);
                if(pageInfo != null){
                    respList = pageInfo.getResult();
                } */
              //商品下架或删除商城页面自动不再显示
                PageResponseDTO<CmsFloorGdsRespDTO> pageInfo = null;
                for(int size = cmsFloorGdsReqDTO.getPageSize(),respSize = respList.size();respSize < size ;respSize = respList.size()){
                    pageInfo = cmsFloorGdsRSV.queryCmsFloorGdsPage(cmsFloorGdsReqDTO);
                    if(pageInfo != null && !CollectionUtils.isEmpty(pageInfo.getResult())){
                        for(CmsFloorGdsRespDTO dto : pageInfo.getResult()){
                            GdsInfoReqDTO gdsInfoReqDTO = new GdsInfoReqDTO();
                            GdsQueryOption[] gdsOptions = new GdsQueryOption[] {GdsQueryOption.BASIC};
                            gdsInfoReqDTO.setGdsQueryOptions(gdsOptions);
                            gdsInfoReqDTO.setId(dto.getGdsId());
                            GdsInfoDetailRespDTO gdsInfoDetailRespDTO = gdsInfoQueryRSV.queryGdsInfoDetail(gdsInfoReqDTO);
                            if(gdsInfoDetailRespDTO !=null && "11".equalsIgnoreCase(gdsInfoDetailRespDTO.getGdsStatus())){
                                respList.add(dto);
                            }
                            if(respList.size() >= size){
                                break;
                            }
                        }
                    }else{
                        break;
                    }
                    
                  //取下一页
                  cmsFloorGdsReqDTO.setPageNo(cmsFloorGdsReqDTO.getPageNo()+1);
                }
                
            }
            // 3.1 商品域  查询商品信息（单个）并根据页面要求处理商品
            gdsInfoDetailRespList = this.qryGdsDetailList(respList,standard);
        }catch(Exception err){
            LogUtil.error(MODULE, "查询商品出现异常:",err);
        }
        
        model.addAttribute("gdsList", gdsInfoDetailRespList);
        return url;
    }
    
    /** 
     * qryGdsDetailList:(将商品ID列表转成商品详情列表). <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author jiangzh 
     * @param respList  商品ID列表
     * @param standard  商品图片规格
     * @return
     * @throws Exception 
     * @since JDK 1.6 
     */ 
    public List<GdsInfoDetailRespDTO> qryGdsDetailList(List<CmsFloorGdsRespDTO> respList,String standard) throws Exception{
        List<GdsInfoDetailRespDTO> gdsInfoDetailRespList = null;
        if (!CollectionUtils.isEmpty(respList)) {
            gdsInfoDetailRespList = new ArrayList<GdsInfoDetailRespDTO>();
            for (CmsFloorGdsRespDTO dto : respList) {
                if (dto != null && StringUtil.isNotEmpty(dto.getGdsId())) {
                    GdsInfoReqDTO gdsInfoReqDTO = new GdsInfoReqDTO();
                    GdsQueryOption[] gdsOptions = new GdsQueryOption[] { 
                            GdsQueryOption.BASIC,GdsQueryOption.PRICE, GdsQueryOption.MAINPIC,GdsQueryOption.SCORE
                            };
                    SkuQueryOption[] skuOptions = new SkuQueryOption[] { 
                            SkuQueryOption.BASIC,SkuQueryOption.PRICE, SkuQueryOption.PROP 
                            };
                    gdsInfoReqDTO.setGdsQueryOptions(gdsOptions);
                    gdsInfoReqDTO.setSkuQuerys(skuOptions);
                    gdsInfoReqDTO.setId(dto.getGdsId());
                    GdsInfoDetailRespDTO gdsInfoDetailRespDTO = gdsInfoQueryRSV.queryGdsInfoDetail(gdsInfoReqDTO);
                    if (gdsInfoDetailRespDTO != null) {
                        // 3.1.1 商品图片
                        GdsMediaRespDTO gdsMediaRespDTO = gdsInfoDetailRespDTO.getMainPic();
                        if(gdsMediaRespDTO == null){
                            gdsMediaRespDTO = new GdsMediaRespDTO();
                            gdsInfoDetailRespDTO.setMainPic(gdsMediaRespDTO);
                        }
                        gdsMediaRespDTO.setURL(ParamsTool.getImageUrl(gdsMediaRespDTO.getMediaUuid(), standard));

                        // 3.1.2 根据ID读取商品描述内容
                        byte[] content = FileUtil.readFile(gdsInfoDetailRespDTO.getGdsDesc());
                        if (ArrayUtils.isNotEmpty(content)) {
                            String gdsDescContent = Jsoup.parse(new String(content)).text();
                            if (gdsDescContent.length() > GDSDESCCONTENT_LENGTH) {
                                gdsDescContent = gdsDescContent.substring(0, GDSDESCCONTENT_LENGTH) + "...";
                            }
                            gdsInfoDetailRespDTO.setGdsDesc(gdsDescContent);
                        }
                        
                        // 3.1.3 拼接商品详情地址
                        /*String url = this.DETAILlURl;
                        if (dto.getGdsId()!= null) {
                            url = url.replace("gdsId", gdsInfoDetailRespDTO.getSkuInfo().getGdsId() + "");
                        }
                        if (gdsInfoDetailRespDTO.getSkuInfo()!=null && gdsInfoDetailRespDTO.getSkuInfo().getId()!= null) {
                            url = url.replace("skuId", gdsInfoDetailRespDTO.getSkuInfo().getId() + "");
                        } else {
                            url = url.replace("skuId", "");
                        }
                        gdsInfoDetailRespDTO.setUrl(url);*/

                        gdsInfoDetailRespList.add(gdsInfoDetailRespDTO);
                    }
                }
            }
        }
        return gdsInfoDetailRespList;
    }
    
    /** 
     * qryFloorByPlaceId:(根据内容位置获取楼层id). <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author jiangzh 
     * @param reqVO
     * @return
     * @throws Exception 
     * @since JDK 1.6 
     */ 
    public CmsFloorRespDTO qryFloorByPlaceId(ComponentReqVO reqVO) throws Exception{
        CmsFloorReqDTO cmsFloorReqDTO = new CmsFloorReqDTO();
        /*1.验证前店入参*/
        if(StringUtil.isEmpty(reqVO.getPlaceId())){//内容位置
            LogUtil.error(MODULE, "入参placeId为空！");
            return null;
        }
        cmsFloorReqDTO.setPlaceId(reqVO.getPlaceId());
        if (StringUtil.isNotBlank(reqVO.getStatus())) {//状态
            cmsFloorReqDTO.setStatus(reqVO.getStatus());
        } else {//默认有效
            cmsFloorReqDTO.setStatus(CmsConstants.ParamStatus.CMS_PARAMSTATUS_1);
        }
        
        CmsFloorRespDTO cmsFloorRespDTO = new CmsFloorRespDTO();
        List<CmsFloorRespDTO> floorRespList = cmsFloorRSV.queryCmsFloorList(cmsFloorReqDTO);
        if(!CollectionUtils.isEmpty(floorRespList)){
            cmsFloorRespDTO = floorRespList.get(0);
        }
        return cmsFloorRespDTO;
    }
    
    /** 
     * qryFloorTabByFloorId:(根据楼层ID获取楼层页签). <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author jiangzh 
     * @param reqVO
     * @param cmsFloorTabReqDTO
     * @return 
     * @since JDK 1.6 
     */ 
    public List<CmsFloorTabRespDTO> qryFloorTabByFloorId(ComponentReqVO reqVO,CmsFloorTabReqDTO cmsFloorTabReqDTO) throws Exception{
        
        if (StringUtil.isNotEmpty(reqVO.getStatus())) {//状态
            cmsFloorTabReqDTO.setStatus(reqVO.getStatus());
        } else {//默认有效
            cmsFloorTabReqDTO.setStatus(CmsConstants.ParamStatus.CMS_PARAMSTATUS_1);
        }
        if (StringUtil.isNotEmpty(reqVO.getTabSize())) {// 楼层页签数量
            cmsFloorTabReqDTO.setPageNo(1);
            cmsFloorTabReqDTO.setPageSize(reqVO.getTabSize());
        }
        List<CmsFloorTabRespDTO> floorTabRespList = new ArrayList<CmsFloorTabRespDTO>();
        if(cmsFloorTabReqDTO.getPageSize() != null && cmsFloorTabReqDTO.getPageSize() != 0){
            PageResponseDTO<CmsFloorTabRespDTO> floorTabPageInfo = cmsFloorTabRSV.queryCmsFloorTabPage(cmsFloorTabReqDTO);
            if(floorTabPageInfo != null){
                floorTabRespList = floorTabPageInfo.getResult();
            }
        }
        return floorTabRespList;
    }
    
    /** 
     * qryFloorGdsList:(根据楼层ID或者页签ID获取商品). <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author jiangzh 
     * @param reqVO
     * @param cmsFloorGdsReqDTO
     * @param standard
     * @return 
     * @since JDK 1.6 
     */ 
    public List<GdsInfoDetailRespDTO> qryFloorGdsList(ComponentReqVO reqVO,CmsFloorGdsReqDTO cmsFloorGdsReqDTO,String standard) throws Exception{
        
        // 状态
        if (StringUtil.isNotEmpty(reqVO.getStatus())) {
            cmsFloorGdsReqDTO.setStatus(reqVO.getStatus());
        }else {// 默认有效
            cmsFloorGdsReqDTO.setStatus(CmsConstants.ParamStatus.CMS_PARAMSTATUS_1);
        }
        // 楼层商品分页
        if (StringUtil.isNotEmpty(reqVO.getGdsSize())) {
            cmsFloorGdsReqDTO.setPageNo(1);
            cmsFloorGdsReqDTO.setPageSize(reqVO.getGdsSize());
        }
        // 3. 根据楼层或者页签ID获取商品
        List<CmsFloorGdsRespDTO> respList = new ArrayList<CmsFloorGdsRespDTO>();
        if(cmsFloorGdsReqDTO.getPageSize() != null && cmsFloorGdsReqDTO.getPageSize() != 0){
            /*PageResponseDTO<CmsFloorGdsRespDTO> pageInfo = cmsFloorGdsRSV.queryCmsFloorGdsPage(cmsFloorGdsReqDTO);
            if(pageInfo != null){
                respList = pageInfo.getResult();
            } */
          //商品下架或删除商城页面自动不再显示
            PageResponseDTO<CmsFloorGdsRespDTO> pageInfo = null;
            for(int size = cmsFloorGdsReqDTO.getPageSize(),respSize = respList.size();respSize < size ;respSize = respList.size()){
                pageInfo = cmsFloorGdsRSV.queryCmsFloorGdsPage(cmsFloorGdsReqDTO);
                if(pageInfo != null && !CollectionUtils.isEmpty(pageInfo.getResult())){
                    for(CmsFloorGdsRespDTO dto : pageInfo.getResult()){
                        GdsInfoReqDTO gdsInfoReqDTO = new GdsInfoReqDTO();
                        GdsQueryOption[] gdsOptions = new GdsQueryOption[] {GdsQueryOption.BASIC};
                        gdsInfoReqDTO.setGdsQueryOptions(gdsOptions);
                        gdsInfoReqDTO.setId(dto.getGdsId());
                        GdsInfoDetailRespDTO gdsInfoDetailRespDTO = gdsInfoQueryRSV.queryGdsInfoDetail(gdsInfoReqDTO);
                        if(gdsInfoDetailRespDTO !=null && "11".equalsIgnoreCase(gdsInfoDetailRespDTO.getGdsStatus())){
                            respList.add(dto);
                        }
                        if(respList.size() >= size){
                            break;
                        }
                    }
                }else{
                    break;
                }
                
              //取下一页
              cmsFloorGdsReqDTO.setPageNo(cmsFloorGdsReqDTO.getPageNo()+1);
            }
        }
        // 3.1 商品域  查询商品信息（单个）并根据页面要求处理商品
        List<GdsInfoDetailRespDTO> gdsInfoDetailRespList = this.qryGdsDetailList(respList,standard);
        
        return gdsInfoDetailRespList;
    }
    
}
