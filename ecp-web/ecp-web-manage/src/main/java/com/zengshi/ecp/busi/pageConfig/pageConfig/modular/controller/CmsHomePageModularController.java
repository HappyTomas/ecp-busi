package com.zengshi.ecp.busi.pageConfig.pageConfig.modular.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zengshi.ecp.base.controller.EcpBaseController;
import com.zengshi.ecp.base.vo.EcpBasePageRespVO;
import com.zengshi.ecp.busi.order.util.ParamsTool;
import com.zengshi.ecp.busi.pageConfig.pageConfig.modular.utils.CmsAnalUtil;
import com.zengshi.ecp.busi.pageConfig.pageConfig.modular.utils.CmsGoodsDetailUtil;
import com.zengshi.ecp.busi.pageConfig.pageConfig.modular.vo.CmsFloorInfoDetailRespVO;
import com.zengshi.ecp.busi.pageConfig.pageConfig.modular.vo.ComponentReqVO;
import com.zengshi.ecp.busi.pageConfig.pageConfig.modular.vo.ModularCmsFloorReqVO;
import com.zengshi.ecp.cms.dubbo.dto.CmsFloorAdvertiseRespDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsFloorCouponReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsFloorCouponRespDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsFloorGdsReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsFloorGdsRespDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsFloorInfoDetailReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsFloorInfoDetailRespDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsFloorReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsFloorRespDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsFloorTabReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsFloorTabRespDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsGdsCategoryReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsGdsCategoryRespDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsInfoReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsInfoRespDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsPageInfoReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsPageInfoRespDTO;
import com.zengshi.ecp.cms.dubbo.interfaces.ICmsAdvertiseRSV;
import com.zengshi.ecp.cms.dubbo.interfaces.ICmsFloorCouponRSV;
import com.zengshi.ecp.cms.dubbo.interfaces.ICmsFloorGdsRSV;
import com.zengshi.ecp.cms.dubbo.interfaces.ICmsFloorLabelRSV;
import com.zengshi.ecp.cms.dubbo.interfaces.ICmsFloorRSV;
import com.zengshi.ecp.cms.dubbo.interfaces.ICmsFloorTabRSV;
import com.zengshi.ecp.cms.dubbo.interfaces.ICmsGdsCategoryRSV;
import com.zengshi.ecp.cms.dubbo.interfaces.ICmsInfoRSV;
import com.zengshi.ecp.cms.dubbo.interfaces.ICmsPageInfoRSV;
import com.zengshi.ecp.cms.dubbo.interfaces.ICmsPlaceCategoryRSV;
import com.zengshi.ecp.cms.dubbo.interfaces.ICmsPlaceRSV;
import com.zengshi.ecp.cms.dubbo.util.CmsCacheUtil;
import com.zengshi.ecp.cms.dubbo.util.CmsConstants;
import com.zengshi.ecp.coupon.dubbo.dto.resp.CoupInfoRespDTO;
import com.zengshi.ecp.coupon.dubbo.interfaces.ICoupDetailRSV;
import com.zengshi.ecp.coupon.dubbo.interfaces.ICoupRSV;
import com.zengshi.ecp.goods.dubbo.constants.GdsConstants;
import com.zengshi.ecp.goods.dubbo.constants.GdsOption;
import com.zengshi.ecp.goods.dubbo.constants.GdsOption.GdsQueryOption;
import com.zengshi.ecp.goods.dubbo.constants.GdsOption.SkuQueryOption;
import com.zengshi.ecp.goods.dubbo.dto.GdsCategoryRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsCollectRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsEvalReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsEvalRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsGuessHomePageRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsGuessYLReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfo.GdsInfoDetailRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfo.GdsInfoReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfo.GdsSkuInfoReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfo.GdsSkuInfoRespDTO;
import com.zengshi.ecp.goods.dubbo.interfaces.IGdsEvalRSV;
import com.zengshi.ecp.goods.dubbo.interfaces.IGdsGuessYLRSV;
import com.zengshi.ecp.goods.dubbo.interfaces.IGdsSkuInfoQueryRSV;
import com.zengshi.ecp.goods.dubbo.util.GdsUtils;
import com.zengshi.ecp.order.dubbo.util.LongUtils;
import com.zengshi.ecp.prom.dubbo.dto.PromInfoDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromListRespDTO;
import com.zengshi.ecp.prom.dubbo.interfaces.IPromQueryRSV;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.staff.dubbo.dto.CustInfoReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.CustInfoResDTO;
import com.zengshi.ecp.staff.dubbo.interfaces.ICustInfoRSV;
import com.zengshi.paas.utils.DateUtil;
import com.zengshi.paas.utils.FileUtil;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.ObjectCopyUtil;
import com.zengshi.paas.utils.StringUtil;
import com.alibaba.dubbo.common.utils.CollectionUtils;
import com.alibaba.fastjson.JSON;

/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-busi-ecp-web-manage <br>
 * Description: 商城首页取数<br>
 * Date:2016年6月6日下午3:39:31  <br>
 * Copyright (c) 2016 ZengShi All Rights Reserved <br>
 * 
 * @author gxq
 * @version  
 * @since JDK 1.6
 */
@RequestMapping("/cmshomepagegetdata")
@Controller
public class CmsHomePageModularController extends EcpBaseController{
    private static String URL = "/pageConfig/pageConfig";
    private static String WAPURL = "/pageConfig/pageConfigWap";
    private static String TRUE = "true";
    private String MODULE = CmsHomePageModularController.class.getName();
    @Resource
    private IGdsEvalRSV iGdsEvalRSV;
    @Resource
    private ICmsGdsCategoryRSV cmsGdsCategoryRSV;
    @Resource
    private ICmsPlaceCategoryRSV cmsPlaceCategoryRSV;
    @Resource
    private ICmsAdvertiseRSV cmsAdvertiseRSV;
    @Resource
    private ICmsPlaceRSV cmsPlaceRSV;
    @Resource
    private ICmsPageInfoRSV cmsPageInfoRSV;
    @Resource
    private ICustInfoRSV iCustInfoRSV;
    @Resource
    private ICmsFloorLabelRSV cmsFloorLabelRSV;
    @Resource
    private ICmsFloorTabRSV cmsFloorTabRSV;
    @Resource
    private ICmsFloorRSV cmsFloorRSV;
    @Resource
    private ICmsFloorGdsRSV cmsFloorGdsRSV;
    @Resource
    private ICmsInfoRSV cmsInfoRSV;
    @Resource
    private IPromQueryRSV promQueryRSV;
    @Resource
    private ICmsFloorCouponRSV cmsFloorCouponRSV;
    @Resource
    private ICoupRSV coupRSV;
    @Resource
    private ICoupDetailRSV coupDetailRSV;
    //查询新书预售服务
    @Resource(name="gdsSkuInfoQueryRSV")
    private IGdsSkuInfoQueryRSV gdsSkuInfoQueryRSV;
    @Resource (name = "gdsGuessYLRSV")
    private IGdsGuessYLRSV gdsGuessYLRSV;
    /**
     * 
     * qryCatgList:(获取商城首页的商品分类树信息). <br/> 
     * 
     * @author gxq 
     * @param model
     * @param reqVO
     * @return
     * @throws Exception 
     * @since JDK 1.6
     */
    @RequestMapping(value = "/qryMallCatgList")
    public String qryMallCatgList(Model model,ComponentReqVO reqVO,String returnUrl) throws Exception {
        LogUtil.info(MODULE, "==========reqVO :" + reqVO.toString() + ";");
        String url = "/modular/loading/list/"+returnUrl;
        
        CmsGdsCategoryRespDTO catgParent = null;// 父节点
        List<CmsGdsCategoryRespDTO> cmsGdsCatgRespDtoList = null;// 二级节点
        List<List<CmsGdsCategoryRespDTO>> cmsShowGdsCatgRespDtoList = new ArrayList<List<CmsGdsCategoryRespDTO>>();// 右侧显示节点
        
        try{
            if(!StringUtil.isBlank(reqVO.getGdsCategory())){//商品分类
                // 1.查找分类根节点 且查找其两级有效子分类
                CmsGdsCategoryReqDTO cmsGdsCatgReqDto = new CmsGdsCategoryReqDTO();
                cmsGdsCatgReqDto.setId(reqVO.getGdsCategory());
                cmsGdsCatgReqDto.setStatus(CmsConstants.ParamStatus.CMS_PARAMSTATUS_1);//只查询有效的
                catgParent = cmsGdsCategoryRSV.queryCmsGdsCategory(cmsGdsCatgReqDto,(short) 2, CmsConstants.ParamStatus.CMS_PARAMSTATUS_1);
                
                cmsGdsCatgRespDtoList = catgParent.getCmsChildCatg();
                
                //查询二级节点右侧分类 且查找其两级有效子分类
                if(CollectionUtils.isNotEmpty(cmsGdsCatgRespDtoList)){
                    for(CmsGdsCategoryRespDTO catg : cmsGdsCatgRespDtoList){
                        CmsGdsCategoryReqDTO showReqDto = new CmsGdsCategoryReqDTO();
                        List<CmsGdsCategoryRespDTO> showChilCatgList = null;
                        if(StringUtil.isNotBlank(catg.getShowCatgId())){
                            showReqDto.setId(catg.getShowCatgId());
                            showReqDto.setStatus(CmsConstants.ParamStatus.CMS_PARAMSTATUS_1);//只查询有效的
                            CmsGdsCategoryRespDTO showCatg = cmsGdsCategoryRSV.queryCmsGdsCategory(showReqDto, (short) 2, CmsConstants.ParamStatus.CMS_PARAMSTATUS_1);
                            
                            if(showCatg !=null){
                                showChilCatgList = showCatg.getCmsChildCatg();
                                catg.setCmsShowChildCatg(showChilCatgList);
                            } 
                        }
                        //核心版本需要一一对应
                        if(showChilCatgList == null){
                            showChilCatgList = new ArrayList<CmsGdsCategoryRespDTO>();
                        }
                        cmsShowGdsCatgRespDtoList.add(showChilCatgList);
                    }
                }
                
            }else{
                model.addAttribute("errMsg", "未选择分类！");
            }
            
        }catch(Exception err){
            LogUtil.error(MODULE, "查询商品分类出现异常:",err);
            model.addAttribute("errMsg", "查询分类出现异常！");
        }
        
        model.addAttribute("catgParent", catgParent);
        model.addAttribute("catgChild", cmsGdsCatgRespDtoList);
        model.addAttribute("placeHeight", reqVO.getPlaceHeight());
        model.addAttribute("showCatg", cmsShowGdsCatgRespDtoList);
        return URL+url;
    }
    
    /**
     * 
     * qryFloorVM:(根据内容位置获取楼层信息). <br/> 
     * 
     * @author gxq 
     * @param model
     * @param reqVO
     * @param request
     * @return
     * @throws Exception 
     * @since JDK 1.6
     */
    @RequestMapping(value = "/qryFloorVM")
    public String qryFloorVM(Model model,ComponentReqVO reqVO,HttpServletRequest request) throws Exception {
        LogUtil.info(MODULE, "==========reqVO:" + reqVO.toString() + ";");
        // 图片规格
        String standard = "";
        if (StringUtil.isNotEmpty(reqVO.getPlaceWidth())&& StringUtil.isNotEmpty(reqVO.getPlaceHeight())) {
            standard = reqVO.getPlaceWidth() + "x" + reqVO.getPlaceHeight() + "!";
        } 
        CmsFloorRespDTO floorRespDTO = null;
        try{
            // 1 根据内容位置ID查询有效楼层
            floorRespDTO = this.getOneFloor(this.qryFloorByPlaceId(reqVO));
            if(floorRespDTO != null && StringUtil.isNotEmpty(floorRespDTO.getId())){//具有有效楼层
                CmsFloorInfoDetailReqDTO floorInfoReqDto = new CmsFloorInfoDetailReqDTO();
                floorInfoReqDto.setFloorId(floorRespDTO.getId());
                floorInfoReqDto.setAdSize(1);//广告数量
                floorInfoReqDto.setTabSize(reqVO.getTabSize());
                floorInfoReqDto.setLabelSize(8);
                floorInfoReqDto.setCouponSize(reqVO.getCoupSize());
                
                CmsFloorInfoDetailRespVO floorInfoRespVO = this.getFloorInfo(floorInfoReqDto, null);
                if(floorInfoRespVO != null){
                    if(CollectionUtils.isNotEmpty(floorInfoRespVO.getFloorAdList())){
                        model.addAttribute("floorAdvertise", this.getAdDetail(floorInfoRespVO.getFloorAdList().get(0), standard));
                    }
                    model.addAttribute("floorLabelList", floorInfoRespVO.getFloorLabelList());
                    model.addAttribute("coupList", floorInfoRespVO.getCouponList());
                    model.addAttribute("floorTabList", floorInfoRespVO.getFloorTabList());
                }
            }else{
                model.addAttribute("errMsg","无效的内容位置！");
            }
        }catch(Exception err){
            LogUtil.error(MODULE, "查询楼层信息出现异常:",err);
            model.addAttribute("errMsg","查询楼层出现异常！");
        }
        
        model.addAttribute("componentReqVO", reqVO);
        model.addAttribute("floorRespDto", floorRespDTO);
        if(StringUtil.isNotBlank(reqVO.getReturnUrl())){
        	return  URL + "/modular/loading/list/"+reqVO.getReturnUrl();
        }
        return URL+"/modular/loading/list/mallhomepage/gds-show-template";
    }
    /**
     * 
     * queryFloorGdsVM:(根据页签ID获取页签下的商品). <br/> 
     * 
     * @author gxq 
     * @param model
     * @param reqVO
     * @return
     * @throws Exception 
     * @since JDK 1.6
     */
    @RequestMapping(value = "/queryFloorInfoVM")
    public String queryFloorGdsVM(Model model,ComponentReqVO reqVO) throws Exception {
        LogUtil.info(MODULE, "==========reqVO:" + reqVO.toString() + ";");
        
        List<Long> propIds = new ArrayList<Long>();
        propIds.add(1001l);//作者
        //propIds.add(1005l);//出版日期
        //propIds.add(1006l);//出版社
        //propIds.add(1020l);//内容简介 
        try {
            model.addAttribute("gdsList", getGdsDetailOfFlr(reqVO, propIds,CmsConstants.PlatformType.CMS_PLATFORMTYPE_01));
        } catch (Exception e) {
            LogUtil.error(MODULE, "查询楼层商品异常", e);
            model.addAttribute("errorMsg", "查询楼层商品异常");
        }
        
        model.addAttribute("componentReqVO", reqVO);
        if(!StringUtil.isBlank(reqVO.getReturnUrl())){
            return URL + reqVO.getReturnUrl();
        }
        return URL+"/modular/loading/list/mallhomepage/gds-show-floorinfo-template";
    }
    /**
     * 
     * qryFloorList:(根据内容位置获取楼层信息). <br/> 
     * 
     * @author gxq 
     * @param model
     * @param reqVO
     * @return
     * @throws Exception 
     * @since JDK 1.6
     */
    @RequestMapping(value = "/qryFloorList")
    @ResponseBody
    public Map<String,Object> qryFloorList(Model model,ComponentReqVO reqVO) throws Exception {
        if(reqVO == null ){
            reqVO = new ComponentReqVO();
        }
        LogUtil.info(MODULE, "==========reqVO:" + reqVO.toString() + ";" );
        
        Map<String,Object> resultMap = new HashMap<String,Object>();
        // 图片规格
        String standard = "";
        if (StringUtil.isNotEmpty(reqVO.getPlaceWidth())&& StringUtil.isNotEmpty(reqVO.getPlaceHeight())) {
            standard = reqVO.getPlaceWidth() + "x" + reqVO.getPlaceHeight() + "!";
        } 
        CmsFloorRespDTO cmsFloorRespDTO = null;
        
        // 1 根据内容位置ID查询有效楼层
        try{
            cmsFloorRespDTO = this.getOneFloor(this.qryFloorByPlaceId(reqVO));
            LogUtil.info(MODULE, "查询楼层返回信息:"+(StringUtil.isEmpty(cmsFloorRespDTO)?cmsFloorRespDTO:cmsFloorRespDTO.toString()));
        }catch (Exception e){
            LogUtil.error(MODULE, "查询楼层出现异常:",e);
            resultMap.put("errMsg","查询楼层出现异常!");
        }
        
        //2 查询楼层数据
        CmsFloorInfoDetailRespVO floorInfoRespVO = null;
        if(cmsFloorRespDTO != null && StringUtil.isNotEmpty(cmsFloorRespDTO.getId())){
            try {
                CmsFloorInfoDetailReqDTO floorInfoReqDto = new CmsFloorInfoDetailReqDTO();
                floorInfoReqDto.setFloorId(cmsFloorRespDTO.getId());
                floorInfoReqDto.setTabSize(reqVO.getTabSize());
                floorInfoReqDto.setGdsSize(reqVO.getGdsSize());
                
                floorInfoRespVO = this.getFloorInfo(floorInfoReqDto, null);
            
                if(floorInfoRespVO != null){
                    resultMap.put("floorTabList", floorInfoRespVO.getFloorTabList());
                    
                    if(CmsConstants.DataSource.CMS_DATA_SOURCE_02.equalsIgnoreCase(cmsFloorRespDTO.getDataSource())){
                        List<CmsFloorTabRespDTO> tabList = floorInfoRespVO.getFloorTabList();
                        CmsFloorTabRespDTO tabInfo = null;
                        if( CollectionUtils.isNotEmpty(tabList)){
                            tabInfo = tabList.get(0);
                        }
                        EcpBasePageRespVO<GdsInfoDetailRespDTO> analyGds = this.getAnalysGdsRank(reqVO, cmsFloorRespDTO, tabInfo,CmsConstants.PlatformType.CMS_PLATFORMTYPE_01);
                        if(analyGds!=null){
                            resultMap.put("gdsList",analyGds.getList());
                        }
                    }else{
                        List<CmsFloorGdsRespDTO> floorGdsList = floorInfoRespVO.getGdsList();
                        if(CollectionUtils.isNotEmpty(floorGdsList)){
                            List<Long> propIds = new ArrayList<Long>();
                            propIds.add(1001l);//作者
                            propIds.add(1005l);//出版日期
                            propIds.add(1006l);//出版社
                            propIds.add(1020l);//内容简介
                            //propIds.add(1023l);//编辑推荐
                            //propIds.add(1024l);//专家推荐                    
                            //propIds.add(1026l);//在线试读pdf
                            //propIds.add(1031l);//是否提供试读
                            resultMap.put("gdsList", this.floorGdsToGdsDetailList(floorGdsList, propIds, standard,CmsConstants.PlatformType.CMS_PLATFORMTYPE_01,null));
                        } 
                    }
                }
            } catch (Exception e) {
                LogUtil.error(MODULE, "查询楼层数据异常", e);
                resultMap.put("errMsg","查询楼层数据异常!");
            }
        }else{
            resultMap.put("errMsg","无效的内容位置!");
        }
        // 4.返回结果
        resultMap.put("floorRespDTO", cmsFloorRespDTO);
        return resultMap;
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
        if(reqVO == null){
            return null;
        }
        LogUtil.info(MODULE, "===========reqVO:" + reqVO.toString() + ";");
        Map<String,Object> resultMap = new HashMap<String,Object>();
        List<Long> propIds = new ArrayList<Long>();
        propIds.add(1001l);//作者
        propIds.add(1005l);//出版日期
        propIds.add(1006l);//出版社
        propIds.add(1020l);//内容简介 
        try {
            resultMap.put("gdsList", getGdsDetailOfFlr(reqVO, propIds,CmsConstants.PlatformType.CMS_PLATFORMTYPE_01));
        } catch (Exception e) {
            LogUtil.error(MODULE, "查询楼层商品异常", e);
            resultMap.put("errorMsg", "查询楼层商品异常");
        }
        
        return resultMap;
    }
    /**
     * 
     * getGdsDetailOfFlr:(查询楼层下商品，有tabid查tabId下商品，无则查楼层下商品). <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author zhanbh 
     * @param reqVo   tabId 与 floorId 必须有一个
    * @param propIds   需要的商品属性
    * @return 
     * @throws Exception 
    * @since JDK 1.6
    */
    private List<GdsInfoDetailRespDTO> getGdsDetailOfFlr(ComponentReqVO reqVO,List<Long> propIds,String platformType) throws Exception{
        //验证入参
        if(reqVO == null || (StringUtil.isEmpty(reqVO.getFloorId()) && StringUtil.isEmpty(reqVO.getTabId()))){
            return null;
        }
        
        List<GdsInfoDetailRespDTO> resultInfo = null;
        
        String standard = "";//规格
        if(StringUtil.isNotEmpty(reqVO.getPlaceWidth()) && StringUtil.isNotEmpty(reqVO.getPlaceHeight())){
            standard = reqVO.getPlaceWidth() + "x" + reqVO.getPlaceHeight() + "!";
        }
        
        CmsFloorTabRespDTO tabInfo = null; 
        if (StringUtil.isNotEmpty(reqVO.getTabId())) {//查询页签信息
            CmsFloorTabReqDTO tabReqDto = new CmsFloorTabReqDTO();
            tabReqDto.setId(reqVO.getTabId());
            tabInfo = cmsFloorTabRSV.queryCmsFloorTab(tabReqDto);
            if(tabInfo != null){
                reqVO.setFloorId(tabInfo.getFloorId());
            }
        }
        // 2. 根据楼层获取商品  楼层id必传  并且页签id为空 如果不为空 则查询出来的页签信息必须有值
        if(StringUtil.isNotEmpty(reqVO.getFloorId()) 
                && (StringUtil.isEmpty(reqVO.getTabId()) || (tabInfo !=null && StringUtil.isNotEmpty(tabInfo.getId())))){
                CmsFloorInfoDetailReqDTO floorInfoReqDto = new CmsFloorInfoDetailReqDTO();
                floorInfoReqDto.setFloorId(reqVO.getFloorId());
                floorInfoReqDto.setGdsSize(reqVO.getGdsSize());
                
                CmsFloorInfoDetailRespVO floorInfoRespVO = this.getFloorInfo(floorInfoReqDto, tabInfo);
                if(floorInfoRespVO != null){
                    CmsFloorRespDTO floorRestDto = floorInfoRespVO.getFloorBaseInfo();
                    if(floorRestDto !=null && CmsConstants.DataSource.CMS_DATA_SOURCE_02.equalsIgnoreCase(floorRestDto.getDataSource())){
                        EcpBasePageRespVO<GdsInfoDetailRespDTO> analyGds = this.getAnalysGdsRank(reqVO, floorRestDto, tabInfo,platformType);
                        if(analyGds!=null){
                            resultInfo = analyGds.getList();
                        }
                    }else{
                        List<CmsFloorGdsRespDTO> floorGdsList = floorInfoRespVO.getGdsList();
                        if(CollectionUtils.isNotEmpty(floorGdsList)){
                            if(CollectionUtils.isEmpty(propIds)){
                                propIds = new ArrayList<Long>();
                            }
                            resultInfo = this.floorGdsToGdsDetailList(floorGdsList, propIds, standard,platformType,null);
                        }
                    }
                }
        }
        
        return resultInfo;
    }
    /**
     * 
     * getFloorInfo:(根据楼层id及数据选项获取楼层基本信息)楼层，页签，标签，优惠券. <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author zhanbh
     * @param reqDto 查询选项  通过其中各信息数量的大小确定
     * @param tabInfo 如果指定了页签信息   则查商品时  只查该页签下的商品
     * @return 
     * @since JDK 1.6
     */
    private CmsFloorInfoDetailRespVO getFloorInfo (CmsFloorInfoDetailReqDTO reqDto,CmsFloorTabRespDTO tabInfo){
        LogUtil.info(MODULE, "开始查询楼层基本信息:"+JSON.toJSONString(reqDto));
        //1 验证入参
        if(reqDto == null || StringUtil.isEmpty(reqDto.getFloorId())){
            return null;
        }
        
        Integer coupSize = reqDto.getCouponSize();
        Integer gdsSize = reqDto.getGdsSize();
        reqDto.setCouponSize(null);
        reqDto.setGdsSize(null);
        
        //2 查询数据  根据reqDto中各个选项的数量来判断选项
        CmsFloorInfoDetailRespVO respVO = new CmsFloorInfoDetailRespVO();
        CmsFloorInfoDetailRespDTO floorInfoRespDto = null;
        try {
            floorInfoRespDto = cmsFloorRSV.queryFloorInfoDetail(reqDto);
        } catch (Exception e) {
            LogUtil.error(MODULE, "cmsFloorRSV.queryFloorInfoDetail查询楼层基本信息出现异常：", e);
        }
        
        if(floorInfoRespDto !=null){
            ObjectCopyUtil.copyObjValue(floorInfoRespDto, respVO, null, false);
        }
        
        //优惠券与商品特殊处理  由于要去掉无效的数据
        if(coupSize != null && coupSize > 0){
            try {
                respVO.setCouponList(this.qryCoupByFloorID(coupSize,reqDto.getFloorId()));
            } catch (Exception e) {
                LogUtil.error(MODULE, "查询优惠券异常", e);
            }
            
        }
        if(gdsSize !=null && gdsSize >0){
            //没有指定页签  则取第一个页签
            List<CmsFloorTabRespDTO> tabList = respVO.getFloorTabList();
            if((tabInfo == null || StringUtil.isNotEmpty(tabInfo.getId())) && CollectionUtils.isNotEmpty(tabList)){
                tabInfo = tabList.get(0);
            }
            
            try {
                CmsFloorRespDTO floorInfo = respVO.getFloorBaseInfo();
                if(floorInfo!=null && StringUtil.isNotEmpty(floorInfo.getId())
                   && !CmsConstants.DataSource.CMS_DATA_SOURCE_02.equalsIgnoreCase(floorInfo.getDataSource())){//非行为分析
                    CmsFloorGdsReqDTO cmsFloorGdsReqDTO = new CmsFloorGdsReqDTO();
                    cmsFloorGdsReqDTO.setPageNo(1);
                    cmsFloorGdsReqDTO.setPageSize(gdsSize);
                    cmsFloorGdsReqDTO.setFloorId(floorInfo.getId());
                    cmsFloorGdsReqDTO.setStatus(CmsConstants.ParamStatus.CMS_PARAMSTATUS_1);
                    //如果指定tab信息 则查询该tab下的商品  否则查找第一个页签的商品
                    if(tabInfo!=null && StringUtil.isNotEmpty(tabInfo.getId())){
                        cmsFloorGdsReqDTO.setTabId(tabInfo.getId());
                    }
                    PageResponseDTO<CmsFloorGdsRespDTO> resultPage = this.getFloorGdsPage(cmsFloorGdsReqDTO,true,0l);
                    if(resultPage!=null){
                        respVO.setGdsList(resultPage.getResult());
                    }
                }
            } catch (Exception e) {
                LogUtil.error(MODULE, "查询楼层商品异常", e);
            }
        }
        return respVO;
    }
    /**
     * 
     * qryCoupByFloorID:(根据楼层id 查询楼层有效的优惠券). <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author zhanbh 
     * @param FloorId
     * @return
     * @throws Exception 
     * @since JDK 1.6
     */
    private List<CoupInfoRespDTO> qryCoupByFloorID(Integer coupSize,Long FloorId) throws Exception{
        List<CoupInfoRespDTO> coupInfoRespDTOList= new ArrayList<CoupInfoRespDTO>();//返回优惠券数据
        List<CoupInfoRespDTO> coupInfoDTOList= null;//一次查询出的优惠券数据
        List<Long> coupIds = new ArrayList<Long>();
        List<CmsFloorCouponRespDTO> cmsFloorCouponRespDtoList = null;
        if(StringUtil.isNotEmpty(coupSize) && coupSize > 0&& StringUtil.isNotEmpty(FloorId)){
            CmsFloorCouponReqDTO cmsCoupReqDto = new CmsFloorCouponReqDTO();
            cmsCoupReqDto.setFloorId(FloorId);
            cmsCoupReqDto.setStatus(CmsConstants.ParamStatus.CMS_PARAMSTATUS_1);
            cmsCoupReqDto.setPageSize(coupSize);
            
            /**
             * 无效优惠券不会被查找出来
             */
            for(int pageNo = 1;coupInfoRespDTOList.size() < coupSize ;pageNo ++){
                coupInfoDTOList =null;
                coupIds.clear();
                cmsCoupReqDto.setPageNo(pageNo);
                cmsFloorCouponRespDtoList = cmsFloorCouponRSV.queryCmsFloorCouponPage(cmsCoupReqDto).getResult();
                if(CollectionUtils.isNotEmpty(cmsFloorCouponRespDtoList)){
                    for (CmsFloorCouponRespDTO dto : cmsFloorCouponRespDtoList){
                        coupIds.add(dto.getCouponId());
                    }
                }else{
                    break; //查询为空，跳出循环
                }
                
                if(CollectionUtils.isNotEmpty(coupIds)){
                    coupInfoDTOList = coupRSV.queryCoupInfoList(coupIds);
                }
                
                if(CollectionUtils.isNotEmpty(coupInfoDTOList)){
                    for(CoupInfoRespDTO dto : coupInfoDTOList){
                        if(coupInfoRespDTOList.size() < coupSize){
                            coupInfoRespDTOList.add(coupInfoRespDTOList.size(), dto);
                        }else{
                            break;
                        }
                    }
                }
            }
            
        }
        return coupInfoRespDTOList;
    }
    
    
    /** 
     * getAdDetail:(扩展广告数据). <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author jiangzh 
     * @param reqVO
     * @param request
     * @return
     * @throws Exception 
     * @since JDK 1.6 
     */ 
    private CmsFloorAdvertiseRespDTO getAdDetail(CmsFloorAdvertiseRespDTO dto,String standard) throws Exception {
        if(dto != null){
             LogUtil.info(MODULE, "==========adDto:" + dto.toString() + ";");
             // 1. 判断入参
             if(standard == null){
                 standard = "";
             }
            // 3.1调文件服务器，返回图片
            if (StringUtil.isNotBlank(dto.getVfsId())) {
                dto.setVfsUrl(ParamsTool.getImageUrl(dto.getVfsId(),standard));
            }
            if (StringUtil.isNotBlank(dto.getLinkUrl())) {
                if(CmsConstants.LinkType.CMS_LINKTYPE_01.equalsIgnoreCase(dto.getLinkType())){//商品
                    if(CmsGoodsDetailUtil.isNumeric(dto.getLinkUrl())){
                        dto.setLinkUrl("/gdsdetail/"+dto.getLinkUrl()+"-");
                    }
                    dto.setLinkUrl(dto.getLinkUrl());
                }else if(CmsConstants.LinkType.CMS_LINKTYPE_02.equalsIgnoreCase(dto.getLinkType())){//公告
                    if(CmsGoodsDetailUtil.isNumeric(dto.getLinkUrl())){
                        dto.setLinkUrl("/info/infodetail?id="+dto.getLinkUrl());
                    }
                    dto.setLinkUrl(dto.getLinkUrl());
                }else if(CmsConstants.LinkType.CMS_LINKTYPE_03.equalsIgnoreCase(dto.getLinkType())){
                    if(CmsGoodsDetailUtil.isNumeric(dto.getLinkUrl())){
                        CmsPageInfoReqDTO pageInfoReqDTO = new CmsPageInfoReqDTO();
                        pageInfoReqDTO.setId(Long.valueOf(dto.getLinkUrl().trim()));
                        CmsPageInfoRespDTO cmsPageInfoRespDTO = cmsPageInfoRSV.queryCmsPageInfo(pageInfoReqDTO);
                        if (cmsPageInfoRespDTO != null && StringUtil
                                .isNotEmpty(cmsPageInfoRespDTO.getSiteUrl())) {
                            dto.setLinkUrl(cmsPageInfoRespDTO.getSiteUrl());
                        } else {
                            dto.setLinkUrl("/modularcommon?pageId=" + dto.getLinkUrl());
                        }
                    }
                }
            }
        }
        
        return dto;
    }
    
    /**
     * 
     * qryFloorByPlaceId:(根据内容位置获取楼层id). <br/> 
     * 
     * @author gxq 
     * @param reqVO
     * @return
     * @throws Exception 
     * @since JDK 1.6
     */
    private List<CmsFloorRespDTO> qryFloorByPlaceId(ComponentReqVO reqVO) throws Exception{
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
        
        List<CmsFloorRespDTO> floorRespList = cmsFloorRSV.queryCmsFloorList(cmsFloorReqDTO);
        return floorRespList;
    }
    /**
     * 
     * getOneFloor:(获取楼层数组中第一个楼层). <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author zhanbh 
     * @param floors
     * @return 
     * @since JDK 1.6
     */
    private CmsFloorRespDTO getOneFloor(List<CmsFloorRespDTO> floors){
        CmsFloorRespDTO cmsFloorRespDTO = null;
        if(!CollectionUtils.isEmpty(floors)){
            cmsFloorRespDTO = floors.get(0);
        }
        return cmsFloorRespDTO;
    }
    /**
     * 
     * qryTabsByFloorId:(获取楼层下的页签数据). <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author zhanbh 
     * @param floorId
     * @return 
     * @since JDK 1.6
     */
    private List<CmsFloorTabRespDTO> qryTabsByFloorId(Long floorId){
        List<CmsFloorTabRespDTO> tabs = null;
        if(null != floorId){
            CmsFloorTabReqDTO  reqDto = new CmsFloorTabReqDTO();
            reqDto.setFloorId(floorId);
            reqDto.setStatus(CmsConstants.ParamStatus.CMS_PARAMSTATUS_1);
            try {
                tabs = cmsFloorTabRSV.queryCmsFloorTabList(reqDto);
            } catch (Exception e) {
                LogUtil.error(MODULE, "查询楼层id为："+floorId+" 的页签数据出现异常", e);
            }
        }
        return tabs;
    }
    /**
     * 
     * getFloorGdsPage:(根据楼层ID或者页签ID获取商品,过滤掉非上架商品). <br/> 
     * 
     * @author zhanbh 
     * @param reqVO
     * @param cmsFloorGdsReqDTO 楼层商品条件
     * @param isOnShelves  是否根据是否上架过滤数据
     * @return
     * @throws Exception 
     * @since JDK 1.6
     */
    private PageResponseDTO<CmsFloorGdsRespDTO> getFloorGdsPage(CmsFloorGdsReqDTO cmsFloorGdsReqDTO,boolean isOnShelves,Long startRowIndex) throws Exception{
        //查询楼层商品
        List<CmsFloorGdsRespDTO> respList = new ArrayList<CmsFloorGdsRespDTO>();
        PageResponseDTO<CmsFloorGdsRespDTO> respPage = null;
        PageResponseDTO<CmsFloorGdsRespDTO> pageInfo = null; 
        Integer size = cmsFloorGdsReqDTO.getPageSize();
        
        if(size != null && size > 0){
            if(null == startRowIndex || 0 >  startRowIndex){
                startRowIndex = 0l;
            }
            int pageNo = (int) ((startRowIndex / size) + 1);
            int startNo = (int) (startRowIndex % size);
            cmsFloorGdsReqDTO.setPageNo(pageNo);
            respPage =  pageInfo = cmsFloorGdsRSV.queryCmsFloorGdsPage(cmsFloorGdsReqDTO);
            //商品下架或删除商城页面自动不再显示
            if(isOnShelves==true){
                int respSize = respList.size();
                do {
                    List<CmsFloorGdsRespDTO> pageInfoList = pageInfo.getResult(); 
                    if(pageInfo != null && !CollectionUtils.isEmpty(pageInfoList)){
                        for( ; startNo < pageInfoList.size() ; startNo++){
                            CmsFloorGdsRespDTO dto = pageInfoList.get(startNo);
                            if(null != dto && StringUtil.isNotEmpty(dto.getGdsId()) && CmsGoodsDetailUtil.isGdsOnShelves(dto.getGdsId())){
                                respList.add(dto);
                                respPage.setCount((pageNo-1)*size + startNo);//用来存储当前有效数据的rowIndex
                                respPage.setPageNo(pageNo);
                            }
                            if(respList.size() >= size){
                                break;
                            }
                        }
                    }else{
                        break;
                    }
                    
                  //取下一页
                  pageNo ++;
                  cmsFloorGdsReqDTO.setPageNo(pageNo);
                  startNo = 0;
                  respSize = respList.size();
                  if(respSize < size){
                      pageInfo = cmsFloorGdsRSV.queryCmsFloorGdsPage(cmsFloorGdsReqDTO);
                  }
                } while (respSize < size);
            }
            
            respPage.setResult(respList);
            
        }
        
        return respPage;
    }
    /**
     * 
     * qryGdsDetailList:(将商品ID列表转成商品详情列表). <br/> 
     * 
     * @author gxq 
     * @param respList
     * @param standard
     * @return
     * @throws Exception 
     * @since JDK 1.6
     */
    private List<GdsInfoDetailRespDTO> floorGdsToGdsDetailList(List<CmsFloorGdsRespDTO> respList,List<Long> propIds,String standard,String platformType,Long staffId) throws Exception{
        List<GdsInfoDetailRespDTO> gdsInfoDetailRespList = null;
        if (!CollectionUtils.isEmpty(respList)) {
            gdsInfoDetailRespList = new ArrayList<GdsInfoDetailRespDTO>();
            for (CmsFloorGdsRespDTO dto : respList) {
                if (dto != null && StringUtil.isNotEmpty(dto.getGdsId())) {
                    GdsInfoReqDTO gdsInfoReqDTO = new GdsInfoReqDTO();
                    GdsQueryOption[] gdsOptions = new GdsQueryOption[] { 
                            GdsQueryOption.BASIC, GdsQueryOption.MAINPIC 
                            };
                    SkuQueryOption[] skuOptions = new SkuQueryOption[] { 
                            SkuQueryOption.BASIC, SkuQueryOption.PROP,SkuQueryOption.CAlDISCOUNT
                            };
                    gdsInfoReqDTO.setPropIds(propIds);
                    gdsInfoReqDTO.setGdsQueryOptions(gdsOptions);
                    gdsInfoReqDTO.setSkuQuerys(skuOptions);
                    gdsInfoReqDTO.setId(dto.getGdsId());
                    if(StringUtil.isNotEmpty(staffId)){
                    	gdsInfoReqDTO.setStaffId(staffId);
                    }
                    
                    GdsInfoDetailRespDTO gdsInfoDetailRespDTO = null;
                    gdsInfoDetailRespDTO = CmsGoodsDetailUtil.getGdsDetailByGdsId(gdsInfoReqDTO);
                    gdsInfoDetailRespDTO = CmsGoodsDetailUtil.extendGdsInfo(gdsInfoDetailRespDTO, propIds, true, true, standard);
                    Long promId = null;
                    if(CmsConstants.IsNot.CMS_ISNOT_1.equalsIgnoreCase(dto.getIsProm()) && StringUtil.isNotEmpty(dto.getPromId())){
                        promId = dto.getPromId();
                    }
                    gdsInfoDetailRespDTO = CmsGoodsDetailUtil.getGdsPromPrice(gdsInfoDetailRespDTO, platformType, promId);
                    if (gdsInfoDetailRespDTO != null) {
                        gdsInfoDetailRespList.add(gdsInfoDetailRespDTO);
                    }
                }
            }
        }
        return gdsInfoDetailRespList;
    }
    /**
     * 
     * hotComment:(用户热评). <br/> 
     * @author gxq 
     * @param status
     * @param size
     * @return
     * @throws Exception 
     * @since JDK 1.6
     */
    @RequestMapping(value = "/hotComment")
    @ResponseBody
    public List<GdsEvalRespDTO> hotComment(String status,int size) throws Exception{
        GdsEvalReqDTO gdsEvalReqDTO=new GdsEvalReqDTO();
        gdsEvalReqDTO.setStatus(status);
        gdsEvalReqDTO.setPageSize(size);
        PageResponseDTO<GdsEvalRespDTO> page = iGdsEvalRSV.queryPaging(gdsEvalReqDTO);
        List<GdsEvalRespDTO> gdsEval = new ArrayList<GdsEvalRespDTO>();
        if(page != null && !CollectionUtils.isEmpty(page.getResult())){
            gdsEval = page.getResult();
            for(GdsEvalRespDTO evalResp:gdsEval){
                evalResp.setDetail(FileUtil.readFile2Text(evalResp.getContent(), "UTF-8"));
                evalResp.setGdsDetailUrl(GdsUtils.getGdsUrl(evalResp.getGdsId(), evalResp.getSkuId(), null));
                if(LongUtils.isNotEmpty(evalResp.getStaffId())){
                    CustInfoReqDTO cusReqDto=new CustInfoReqDTO();
                    cusReqDto.setId(evalResp.getStaffId());
                    CustInfoResDTO cusResDto = iCustInfoRSV.getCustInfoById(cusReqDto);
                    evalResp.setStaffName(cusResDto.getStaffCode());
                }
            }  
        }
        return gdsEval;
    }
    /**
     * 
     * infolist:(获取商城公告). <br/> 
     * 
     * @author gxq 
     * @param model
     * @param reqVO
     * @return
     * @throws Exception 
     * @since JDK 1.6
     */
    @RequestMapping(value = "/infolistplug")
    @ResponseBody
    public Map<String,Object> infolistplug(Model model,ComponentReqVO reqVO) throws Exception {
        LogUtil.info(MODULE, "==========reqVO:" + reqVO.toString() + ";");
        
        Map<String,Object> resultMap = new HashMap<String,Object>();
        List<CmsInfoRespDTO> respList = null;
        
        // 1. 判断入参
        if (StringUtil.isNotEmpty(reqVO.getPlaceId()) || StringUtil.isNotEmpty(reqVO.getSiteId())) {
            CmsInfoReqDTO reqDTO = new CmsInfoReqDTO();
            reqDTO.setPlaceId(reqVO.getPlaceId());
            reqDTO.setSiteId(reqVO.getSiteId());
            reqDTO.setThisTime(DateUtil.getSysDate());
            //reqDTO.setThisTime(DateUtil.getTheDayFirstSecond(DateUtil.getSysDate()));
            if (StringUtil.isNotEmpty(reqVO.getStatus())) {
                reqDTO.setStatus(reqVO.getStatus());
            } else {// 默认查有效
                reqDTO.setStatus(CmsConstants.ParamStatus.CMS_PARAMSTATUS_1);
            }
            if (StringUtil.isNotEmpty(reqVO.getPlaceSize())) {
                reqDTO.setPageNo(1);
                reqDTO.setPageSize(reqVO.getPlaceSize());
            } else {// 默认3条
                reqDTO.setPageNo(1);
                reqDTO.setPageSize(3);
            }
            // 2. 调用信息服务，无分页
            respList = new ArrayList<CmsInfoRespDTO>();
            PageResponseDTO<CmsInfoRespDTO> pageInfo = null;
            try{
                pageInfo = cmsInfoRSV.queryCmsInfoPage(reqDTO);
            }catch(Exception err){
                LogUtil.error(MODULE, "查询信息出现异常:",err);
                resultMap.put("errMsg", "查询出现异常！");
            }
            if (pageInfo != null) {
                respList = pageInfo.getResult();
            }
        }else{
            resultMap.put("errMsg", "未配置内容位置！");
        }
  
        // 4. 返回结果
        resultMap.put("respList", respList);
        return resultMap;
    }
    /**
     * 
     * getAnalysGdsRank:(调用用户行为分析返回商品数据). <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author zhanbh
     * @param cmsFloorRespDTO
     * @param cmsFloorTabRespDTO
     * @return 
     * @throws Exception 
     * @since JDK 1.6
     */
    private EcpBasePageRespVO<GdsInfoDetailRespDTO> getAnalysGdsRank(ComponentReqVO reqVO,CmsFloorRespDTO cmsFloorRespDTO,
            CmsFloorTabRespDTO cmsFloorTabRespDTO,String platformType,Long staffId) throws Exception {
        LogUtil.info(MODULE, "调用用户行为分析服务开始:");
        //1 验证入参
        if(cmsFloorRespDTO == null || StringUtil.isEmpty(cmsFloorRespDTO.getId())){
            return null;
        }
        if(reqVO == null ){
            reqVO = new ComponentReqVO();
        }
        if(StringUtil.isNotEmpty(reqVO.getGdsSize())){
            reqVO.setPageSize(reqVO.getGdsSize());
        }
        // 图片规格
        String standard = "";
        if (StringUtil.isNotEmpty(reqVO.getPlaceWidth())&& StringUtil.isNotEmpty(reqVO.getPlaceHeight())) {
            standard = reqVO.getPlaceWidth() + "x" + reqVO.getPlaceHeight() + "!";
        }
        
        EcpBasePageRespVO<GdsInfoDetailRespDTO> gdsPageInfo= new EcpBasePageRespVO<GdsInfoDetailRespDTO>(); 
        //转化商品必须的属性id
        List<Long> propIds = new ArrayList<Long>();
        propIds.add(1001l);//作者
        propIds.add(1020l);//内容简介
        String catgCode = "";
        if(cmsFloorTabRespDTO!=null && StringUtil.isNotBlank(cmsFloorTabRespDTO.getCatgCode())){//tab页的优先级更高
            catgCode = cmsFloorTabRespDTO.getCatgCode();
        }else{
            catgCode = cmsFloorRespDTO.getCatgCode();
        }
        gdsPageInfo = CmsAnalUtil.getAnalRankDataPage(reqVO.getPageNumber(), reqVO.getPageSize(), cmsFloorRespDTO.getCountType(), catgCode, null, propIds,staffId);
        List<GdsInfoDetailRespDTO> resultGds = null;
        if(null != gdsPageInfo){
            resultGds = gdsPageInfo.getList();
        }
        CmsGoodsDetailUtil.extendGdsInfoList(resultGds, propIds, true, true, standard);
        if(CollectionUtils.isNotEmpty(resultGds)){
            for(GdsInfoDetailRespDTO gdsInfo : resultGds){
                CmsGoodsDetailUtil.getGdsPromPrice(gdsInfo, platformType, null);
            }
        }

        return gdsPageInfo;
    }
    /**
     * 
     * getAnalysGdsRank:(调用用户行为分析返回商品数据,没有指定staffid). <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author zhanbh 
     * @param reqVO
     * @param cmsFloorRespDTO
     * @param cmsFloorTabRespDTO
     * @param platformType
     * @return
     * @throws Exception 
     * @since JDK 1.6
     */
    private EcpBasePageRespVO<GdsInfoDetailRespDTO> getAnalysGdsRank(ComponentReqVO reqVO,CmsFloorRespDTO cmsFloorRespDTO,
            CmsFloorTabRespDTO cmsFloorTabRespDTO,String platformType) throws Exception {
        return getAnalysGdsRank(reqVO, cmsFloorRespDTO, cmsFloorTabRespDTO, platformType,null);
    }
    /**
     * 
     * getAnalysGuessForWeb:(Web端获取猜你喜欢行为分析数据). <br/>
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author zhanbh 
     * @param reqVO
     * @param standard
     * @return
     * @throws Exception
     * @since JDK 1.6
     */
    @SuppressWarnings("unchecked")
    private Map<String, Object> getAnalysGuessForWeb(ComponentReqVO reqVO, String standard,HttpServletRequest request) throws Exception {
        LogUtil.info(MODULE, "调用猜你喜欢行为分析开始：");
        Map<String,Object> resultMap = new HashMap<String, Object>();
        Map<String,Object> guessResultMap = new HashMap<String, Object>();
        
        List<Long> propIds = new ArrayList<Long>();
        propIds.add(1001l);//作者
        try {
            guessResultMap = CmsAnalUtil.getAnalysGuessData(reqVO.getPageSize(), propIds, request);
        } catch (Exception e) {
            LogUtil.error(MODULE, "获取数据异常！", e);
            throw e;
        }

        List<GdsInfoDetailRespDTO> gdsInfos = null;
        EcpBasePageRespVO<GdsInfoDetailRespDTO> gdsPageInfo = null;
        if(null != guessResultMap && guessResultMap.containsKey("pageInfo") && null != guessResultMap.get("pageInfo")){
            try {
                gdsPageInfo = (EcpBasePageRespVO<GdsInfoDetailRespDTO>) guessResultMap.get("pageInfo");
            } catch (Exception e) {
                LogUtil.error(MODULE, "从行为分析数据map转化为分页数据异常！", e);
            }
            if(null != gdsPageInfo){
                gdsInfos =  gdsPageInfo.getList();
            }
        }

        //转化数据
        if(CollectionUtils.isNotEmpty(gdsInfos)){
            for(GdsInfoDetailRespDTO gds : gdsInfos){
                CmsGoodsDetailUtil.extendGdsInfo(gds, propIds, false, true, standard);
                CmsGoodsDetailUtil.getGdsPromPrice(gds, CmsConstants.PlatformType.CMS_PLATFORMTYPE_01, null);
            }
            resultMap.put("detailRespDTOs",gdsInfos);
        }

        if(null != guessResultMap && guessResultMap.containsKey("resultCatgs")){
            resultMap.put("categoryRespDTOs",guessResultMap.get("resultCatgs"));
        }
        return resultMap;
    }
    /** 
     * rankinglist:(请求排行榜页面). <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author zhanbh 
     * @param model
     * @param reqVO
     * @return
     * @throws Exception 
     * @since JDK 1.6 
     */ 
    @RequestMapping(value = "/rankinglist")
    public String rankinglist(Model model, ComponentReqVO reqVO) throws Exception {
        String url = "/main/ranking/ranking-list";
        if(StringUtil.isEmpty(reqVO.getPlaceId())&& StringUtil.isEmpty(reqVO.getTabId())){
            throw new Exception("参数placeId与tabId都为空");
        }
        // 分页初始化为 第一页 8个记录
        reqVO.setPageNumber(1);;
        reqVO.setPageSize(8);
        CmsFloorGdsReqDTO reqDTO = reqVO.toBaseInfo(CmsFloorGdsReqDTO.class);
        
        if (StringUtil.isBlank(reqVO.getStatus())) {//默认有效
            reqDTO.setStatus(CmsConstants.ParamStatus.CMS_PARAMSTATUS_1);
        }

        try{
            // 查询楼层与页签
            CmsFloorTabRespDTO cmsFloorTabRespDTO = null;
            if (LongUtils.isNotEmpty(reqVO.getTabId())) {
                reqDTO.setTabId(reqVO.getTabId());
                CmsFloorTabReqDTO cmsTabReqDto = new CmsFloorTabReqDTO();
                cmsTabReqDto.setId(reqVO.getTabId());
                cmsFloorTabRespDTO = cmsFloorTabRSV.queryCmsFloorTab(cmsTabReqDto);
                model.addAttribute("floorTab", cmsFloorTabRespDTO);
            } 
            
            CmsFloorRespDTO cmsFloorRespDTO =null;
            if (StringUtil.isNotEmpty(reqVO.getPlaceId())) {
                reqDTO.setPlaceId(reqVO.getPlaceId());
                
                CmsFloorReqDTO cmsFloorReqDto = new CmsFloorReqDTO();
                cmsFloorReqDto.setPlaceId(reqVO.getPlaceId());
                cmsFloorRespDTO = this.getOneFloor(this.qryFloorByPlaceId(reqVO));
                model.addAttribute("floorInfo", cmsFloorRespDTO);
            }
            // 图片规格
            String standard = "150x150!";
            
            // 调用，并结果返回；从后场返回的分页对象，封装为前店所需要的分页对象；
            PageResponseDTO<CmsFloorGdsRespDTO> respVO = null;
            EcpBasePageRespVO<GdsInfoDetailRespDTO> resultVO = null;
            if(cmsFloorRespDTO != null && StringUtil.isNotEmpty(cmsFloorRespDTO.getId())){
                if(CmsConstants.DataSource.CMS_DATA_SOURCE_02.equalsIgnoreCase(cmsFloorRespDTO.getDataSource())){//去行为分析
                    resultVO = this.getAnalysGdsRank(reqVO,cmsFloorRespDTO,cmsFloorTabRespDTO,CmsConstants.PlatformType.CMS_PLATFORMTYPE_01);
                }else{
                    respVO = this.getFloorGdsPage(reqDTO, false,0l);
                    if(respVO != null){
                        resultVO = new EcpBasePageRespVO<GdsInfoDetailRespDTO>();
                        ObjectCopyUtil.copyObjValue(respVO, resultVO, null, false);
                        List<Long> propIds = new ArrayList<Long>();
                        propIds.add(1001l);//作者
                        propIds.add(1005l);//出版日期
                        propIds.add(1006l);//出版社
                        propIds.add(1020l);//内容简介
                        resultVO.setList(this.floorGdsToGdsDetailList(respVO.getResult(), propIds, standard,CmsConstants.PlatformType.CMS_PLATFORMTYPE_01,null));
                    }
                }
            }
            super.addPageToModel(model, resultVO);
            model.addAttribute("reqVO", reqVO);
        }catch(Exception err){
            LogUtil.error(MODULE, "查询楼层信息出现异常:",err);
        }
        return url;
    }
    
    /**
     * 
     * loadGoodsInfoByFloor:(根据楼层ID获得楼层下的所有商品). <br/> 
     * 
     * @author gxq 
     * @param model
     * @param ids
     * @return
     * @throws Exception 
     * @since JDK 1.6
     */
    @RequestMapping("/loadGoodsInfoByFloor")
    @ResponseBody
    public JSONObject loadGoodsInfoByFloor(Model model,String ids) throws Exception {
        if(StringUtils.isEmpty(ids)) return null;
        List<GdsInfoDetailRespDTO> gdss = new ArrayList<GdsInfoDetailRespDTO>();
        String[] flrIds = ids.split(",");
        
        for (String id : flrIds) {
            CmsFloorGdsReqDTO req = new CmsFloorGdsReqDTO();
            req.setFloorId(new Long(id));
            List<CmsFloorGdsRespDTO> floorGdsList = cmsFloorGdsRSV.queryCmsFloorGdsList(req);
            for (CmsFloorGdsRespDTO floorgds : floorGdsList) {
                GdsInfoReqDTO gdsreq = new GdsInfoReqDTO();
                gdsreq.setId(floorgds.getGdsId());
                GdsQueryOption[] gdsOptions=new GdsQueryOption[]{
                        GdsQueryOption.BASIC,GdsQueryOption.MAINPIC
                    };
                gdsreq.setGdsQueryOptions(gdsOptions);
                GdsInfoDetailRespDTO dto=CmsGoodsDetailUtil.getGdsDetailByGdsId(gdsreq);//, "80x116!", true, floorgds.getPromId());
                dto = CmsGoodsDetailUtil.extendGdsInfo(dto, null, true, true, "80x116!");
                Long promId = null;
                if(CmsConstants.IsNot.CMS_ISNOT_1.equalsIgnoreCase(floorgds.getIsProm()) && StringUtil.isNotEmpty(floorgds.getPromId())){
                    promId = floorgds.getPromId();
                }
                dto = CmsGoodsDetailUtil.getGdsPromPrice(dto, CmsConstants.PlatformType.CMS_PLATFORMTYPE_01, promId);
                if(null != dto && StringUtil.isNotEmpty(dto.getId())){
                    dto.setUrl(CmsCacheUtil.getCmsSiteCache(1L).getSiteUrl()+dto.getUrl());
                    gdss.add(dto);
                }
            }
        }
        JSONObject obj = new JSONObject();
        obj.put("goodslist", gdss);
        return obj;
    }
    
    
    /**
     * 
     * qryCoupByFloorID:(根据楼层id 查询楼层有效的优惠券). <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author zhanbh 
     * @param FloorId
     * @return
     * @throws Exception 
     * @since JDK 1.6
     */
    @RequestMapping(value = "/queryNewGdsNotice")
    @ResponseBody
    public List<GdsSkuInfoRespDTO> queryNewGdsNotice(Model model,int pageSize) throws Exception{
        GdsSkuInfoReqDTO gdsSkuInfoReqDTO=new GdsSkuInfoReqDTO();
        gdsSkuInfoReqDTO.setPageSize(pageSize);
        gdsSkuInfoReqDTO.setGdsStatus(GdsConstants.GdsInfo.GDS_STATUS_WAITSHELVES);
        
        gdsSkuInfoReqDTO.setSkuQuery(new GdsOption.SkuQueryOption[]{SkuQueryOption.MAINPIC,SkuQueryOption.CAlDISCOUNT});            
        PageResponseDTO<GdsSkuInfoRespDTO> gdsSkuRespDtoList  = gdsSkuInfoQueryRSV.queryGdsSkuInfoListPage(gdsSkuInfoReqDTO);
//      GdsSku2PropPropIdxReqDTO gdsSku2PropPropIdxReqDTO = new GdsSku2PropPropIdxReqDTO();
//      //1.声明参数
//        long publishTimeId = 1005l; // 出版日期id
//      gdsSku2PropPropIdxReqDTO.setPropId(publishTimeId);
//      gdsSku2PropPropIdxReqDTO.setPropId(publishTimeId);
//      gdsSku2PropPropIdxReqDTO.setGdsStatus(GdsConstants.GdsInfo.GDS_STATUS_WAITSHELVES);
//      //忽略propValue,beginTime以及endTime的值查询条件  达到查询出版日期为空的目的
//        gdsSku2PropPropIdxReqDTO.setPropValueNullQuery(true);
//      PageResponseDTO<GdsSkuInfoRespDTO> newBookPageResDto= gdsSkuInfoQueryRSV.queryGdsSkuInfoPaging(gdsSku2PropPropIdxReqDTO);
        
        List<GdsSkuInfoRespDTO> resultList = null; 
        if(gdsSkuRespDtoList != null){
            resultList = gdsSkuRespDtoList.getResult();
            if (!CollectionUtils.isEmpty(resultList)) {
                for (GdsSkuInfoRespDTO dto : resultList) {
                    if (dto != null) {
                        CmsGoodsDetailUtil.extendSkuInfo(dto, null, false, true, "80x116!");
                        CmsGoodsDetailUtil.getSkuPromPrice(dto, CmsConstants.PlatformType.CMS_PLATFORMTYPE_01, null);
                    }
                }
            }
        }
        
        return resultList;
    }
    /**
     * 
     * queryGuessGds:(首页查询猜你喜欢模块数据). <br/> 2222
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author zhanbh 
     * @param model
     * @param reqVO   前台组件的参数
     * @return vm
     * @since JDK 1.6
     */
    @RequestMapping(value = "/queryguessgds")
    public String queryGuessGds (Model model,ComponentReqVO reqVO,HttpServletRequest request){
        LogUtil.info(MODULE, "====首页猜你喜欢======reqVO:" + reqVO.toString() + ";");
        
        String url = "/modular/loading/list/mallhomepage/mall-guess-template";
        //1.声明变量
        String standard = "";//图片规格
        if (StringUtil.isNotEmpty(reqVO.getPlaceWidth())&& StringUtil.isNotEmpty(reqVO.getPlaceHeight())) {
            standard = reqVO.getPlaceWidth() + "x" + reqVO.getPlaceHeight() + "!";
        } 
        if (reqVO.getGdsSize()!= null) {//商品数量
            reqVO.setPageNumber(1);
            reqVO.setPageSize(reqVO.getGdsSize());
        }
        
        Map<String,Object> resultMap = null;
        try {
            if(TRUE.equalsIgnoreCase(reqVO.getIfAnalys())){//取行为分析
                resultMap = getAnalysGuessForWeb(reqVO,standard,request);
            }else{
                resultMap = getGuessYL(reqVO,standard);
            }
        } catch (Exception e) {
            LogUtil.error(MODULE, "获取数据异常！", e);
            model.addAttribute("errorMessage", "获取数据异常！");
        }
        if(resultMap != null){
            model.addAttribute("detailRespDTOs", resultMap.get("detailRespDTOs"));
            model.addAttribute("categoryRespDTOs", resultMap.get("categoryRespDTOs"));
        }
        return URL+url;
    }
    /**
     * 
     * qryGuessForWap:(获取微信首页猜你喜欢数据). <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author zhanbh 
     * @param model
     * @param reqVO
     * @param request
     * @return 
     * @since JDK 1.6
     */
    @SuppressWarnings("unchecked")
    @RequestMapping(value = "/qryguessforwap")
    public String qryGuessForWap(Model model,ComponentReqVO reqVO,HttpServletRequest request){
        if(null == reqVO){
            reqVO = new ComponentReqVO();
        }
        //1.声明变量
        String standard = "";//图片规格
        if (StringUtil.isNotEmpty(reqVO.getPlaceWidth())&& StringUtil.isNotEmpty(reqVO.getPlaceHeight())) {
            standard = reqVO.getPlaceWidth() + "x" + reqVO.getPlaceHeight() + "!";
        }
        Map<String,Object> guessResultMap = null;
        List<Long> propIds = new ArrayList<Long>();
        propIds.add(1001l);
        try {
            guessResultMap = CmsAnalUtil.getAnalysGuessData(5000, propIds, true, reqVO.getPageNumber(), reqVO.getPageSize(), request);
            //getAnalysGuess(reqVO,standard,CmsAnalUtil.getStaffId(request),true);//分页
        } catch (Exception e) {
            LogUtil.error(MODULE, "获取数据异常！", e);
            model.addAttribute("errorMessage", "获取数据异常！");
        }
        List<GdsInfoDetailRespDTO> gdsInfos = null;
        if(null != guessResultMap && guessResultMap.containsKey("pageInfo") && null != guessResultMap.get("pageInfo")){
            EcpBasePageRespVO<GdsInfoDetailRespDTO> gdsPageInfo = null;
            try {
                gdsPageInfo = (EcpBasePageRespVO<GdsInfoDetailRespDTO>) guessResultMap.get("pageInfo");
            } catch (Exception e) {
                LogUtil.error(MODULE, "从行为分析数据map转化为分页数据异常！", e);
            }
            if(null != gdsPageInfo){
                gdsInfos =  gdsPageInfo.getList();
            }
            model.addAttribute("gdsInfos", gdsInfos);
        }
        //扩展数据
        if(CollectionUtils.isNotEmpty(gdsInfos)){
            Map<Long,Long> collectMap = new HashMap<Long, Long>();
            for(GdsInfoDetailRespDTO gds : gdsInfos){
                if(StringUtil.isNotEmpty(gds.getId())){
                    //扩展商品数据
                    CmsGoodsDetailUtil.extendGdsInfo(gds, propIds, false, true, standard);
                    //扩展价格
                    CmsGoodsDetailUtil.getGdsPromPrice(gds, CmsConstants.PlatformType.CMS_PLATFORMTYPE_03, null);
                    //扩展收藏信息
                    GdsCollectRespDTO collectRestDto = null;
                    try {
                        collectRestDto = CmsGoodsDetailUtil.checkCollect(gds.getId());
                    } catch (Exception e) {
                        LogUtil.error(MODULE, "扩展商品收藏情况异常！商品id："+gds.getId(), e);
                    }
                    if(null !=collectRestDto && StringUtil.isNotEmpty(collectRestDto.getId())){
                        collectMap.put(gds.getId(), collectRestDto.getId());
                    }
                }
            }
            model.addAttribute("collectMap", collectMap);
        }
        
        return WAPURL+"/modular/wap/list/guess-list";
    }
    /**
     * 
     * getGuessYL:(通过取商品域猜你喜欢配置 获得猜你喜欢数据). <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author zhanbh 
     * @param reqVO
     * @param standard
     * @return 
     * @since JDK 1.6
     */
    private Map<String, Object> getGuessYL(ComponentReqVO reqVO, String standard) {
        LogUtil.info(MODULE, "调用商品域猜你喜欢开始：");
        //1.声明变量
          Map<String,Object> resultMap = new HashMap<String, Object>();
          
          GdsGuessYLReqDTO   guessDto = new GdsGuessYLReqDTO();//查询猜你喜欢请求参数
          GdsGuessHomePageRespDTO guessHomeRespDto = null;//猜你喜欢服务返回对象
          String errorMassage = null;
          //2.查询猜你喜欢数据
          if (reqVO.getGdsSize()!= null) {//商品数量
              guessDto.setPageNo(1);
              guessDto.setPageSize(reqVO.getGdsSize());
          }
          
          try {
              guessHomeRespDto = gdsGuessYLRSV.queryGdsGuessForHomePage(guessDto);
          } catch (Exception e) {
              e.printStackTrace();
              guessHomeRespDto = null;
              errorMassage = "服务错误，请联系管理员！";
              LogUtil.error(MODULE, "出错啦！请联系管理员:",e);
              resultMap.put("errorMassage", errorMassage);
          }

          if(guessHomeRespDto != null){
              //3.转化商品
              if(CollectionUtils.isNotEmpty(guessHomeRespDto.getDetailRespDTOs())){
                  for (GdsInfoDetailRespDTO gdsInfoDetailRespDTO : guessHomeRespDto.getDetailRespDTOs()){
                      if(gdsInfoDetailRespDTO != null){
                          CmsGoodsDetailUtil.extendGdsInfo(gdsInfoDetailRespDTO, null, false, true, standard);
                          CmsGoodsDetailUtil.getGdsPromPrice(gdsInfoDetailRespDTO, CmsConstants.PlatformType.CMS_PLATFORMTYPE_01, null);
                      }
                  }
                  resultMap.put("detailRespDTOs", guessHomeRespDto.getDetailRespDTOs());
              }
              //转化分类
              List<GdsCategoryRespDTO> catgs = guessHomeRespDto.getCategoryRespDTOs();
              List<List<GdsCategoryRespDTO>> categoryRespDTOLists = new ArrayList<List<GdsCategoryRespDTO>>();
              for(GdsCategoryRespDTO catg : catgs){
                  List<GdsCategoryRespDTO> catgList = new ArrayList<GdsCategoryRespDTO>();
                  catgList.add(catg);
                  categoryRespDTOLists.add(catgList);
              }
              resultMap.put("categoryRespDTOs", categoryRespDTOLists);
          }
          
          return resultMap;
    }
    /**
     * 
     * getMultiFloors:(获取内容位置下多楼层数据). <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author zhanbh 
     * @param model
     * @param reqVO
     * @return 
     * @since JDK 1.6
     */
    @RequestMapping(value = "/getmultifloors")
    @ResponseBody 
    public Map<String,Object> getMultiFloors (Model model,ComponentReqVO reqVO){
        Map<String,Object> result = new HashMap<String,Object>();
        List<CmsFloorRespDTO> floors = null;
        if(null == reqVO || null == reqVO.getPlaceId()){
            result.put("resultFlag", "error");
        }else{
            try {
                floors = this.qryFloorByPlaceId(reqVO);
            } catch (Exception e) {
                result.put("resultFlag", "exception");
                LogUtil.error(MODULE, "查询内容位置id为："+reqVO.getPlaceId()+" 下的楼层数据异常", e);
            }
            if(CollectionUtils.isNotEmpty(floors)){
                result.put("resultFlag", "ok");
                result.put("result", floors);
            }else{
                result.put("resultFlag", "empty");
            }
        }
        return result;
    }
    /**
     * 
     * getTabsOfFloor:(获取楼层下的页签). <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author zhanbh 
     * @param model
     * @param reqVO
     * @return 
     * @since JDK 1.6
     */
    @RequestMapping(value = "/gettabsoffloor")
    @ResponseBody 
    public Map<String,Object> getTabsOfFloor (Model model,ModularCmsFloorReqVO reqVO){
        Map<String,Object> result = new HashMap<String,Object>();
        List<CmsFloorTabRespDTO> tabs = null;
        if(null == reqVO || null == reqVO.getId()){
            result.put("resultFlag", "error");
        }else{
            try {
                tabs = this.qryTabsByFloorId(reqVO.getId());
            } catch (Exception e) {
                result.put("resultFlag", "exception");
                LogUtil.error(MODULE, "查询内容位置id为："+reqVO.getPlaceId()+" 下的楼层数据异常", e);
            }
            if(CollectionUtils.isNotEmpty(tabs)){
                result.put("resultFlag", "ok");
                result.put("result", tabs);
            }else{
                result.put("resultFlag", "empty");
            }
        }
        return result;
    }
    /**
     * 
     * getTabsOfFloor:(获取楼层下的页签). <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author zhanbh 
     * @param model
     * @param reqVO
     * @return 
     * @since JDK 1.6
     */
    @RequestMapping(value = "/getfloorgdsofwap")
    @ResponseBody 
    public Map<String,Object> getFloorGdsOfWap (Model model,ModularCmsFloorReqVO reqVO,String staffId){
        Map<String,Object> result = new HashMap<String,Object>();
        List<GdsInfoDetailRespDTO> gdsInfoList = null;
        Long staffIdLong = null;
        if(CmsGoodsDetailUtil.isNumeric(staffId)){
            staffIdLong = Long.valueOf(staffId); 
        }
        if(null == reqVO || (null == reqVO.getId() && null == reqVO.getTabId())){
            result.put("resultFlag", "error");
        }else{
            result.put("floorId", reqVO.getId());
            result.put("tabId", reqVO.getTabId());
            if(CmsConstants.DataSource.CMS_DATA_SOURCE_02.equalsIgnoreCase(reqVO.getDataSource())){
                //查远程行为分析数据
                CmsFloorRespDTO floorInfo = new CmsFloorRespDTO();
                ObjectCopyUtil.copyObjValue(reqVO, floorInfo, null, false);
                CmsFloorTabRespDTO tabInfo = new CmsFloorTabRespDTO();
                if(null != reqVO.getTabId()){
                    ObjectCopyUtil.copyObjValue(reqVO, tabInfo, null, false);
                    tabInfo.setId(reqVO.getTabId());
                }
                ComponentReqVO componentReqVO = new ComponentReqVO();
                ObjectCopyUtil.copyObjValue(reqVO, componentReqVO, null, false);
                componentReqVO.setPlaceHeight(reqVO.getImgHeight());
                componentReqVO.setPlaceWidth(reqVO.getImgWidth());
                EcpBasePageRespVO<GdsInfoDetailRespDTO> analyGdsPage = null;
                try {
                    analyGdsPage = this.getAnalysGdsRank(componentReqVO , floorInfo, tabInfo,CmsConstants.PlatformType.CMS_PLATFORMTYPE_03,staffIdLong);
                } catch (Exception e) {
                    result.put("resultFlag", "exception");
                    LogUtil.error(MODULE, "远程查询楼层商品数据异常", e);
                }
                if(null != analyGdsPage){
                    gdsInfoList = analyGdsPage.getList();
                    if(CollectionUtils.isNotEmpty(gdsInfoList)){
                        result.put("resultFlag", "ok");
                        result.put("result", gdsInfoList);
                        result.put("pageNumber", analyGdsPage.getPageNumber());
                    }else{
                        result.put("resultFlag", "empty");
                    }
                }
            }else{
                //查配置表数据
                CmsFloorGdsReqDTO floorReqDto = new CmsFloorGdsReqDTO();
                floorReqDto.setTabId(reqVO.getTabId());
                floorReqDto.setFloorId(reqVO.getId());
                floorReqDto.setStatus(CmsConstants.ParamStatus.CMS_PARAMSTATUS_1);
                floorReqDto.setPageSize(reqVO.getPageSize());
                PageResponseDTO<CmsFloorGdsRespDTO> floorGdsPage = null;
                try {
                    floorGdsPage = this.getFloorGdsPage(floorReqDto, true ,reqVO.getStartRowIndex());
                } catch (Exception e) {
                    result.put("resultFlag", "exception");
                    LogUtil.error(MODULE, "查询楼层商品数据异常", e);
                }
                if(null != floorGdsPage){
                    List<CmsFloorGdsRespDTO> floorGdsList = floorGdsPage.getResult();
                    if(CollectionUtils.isNotEmpty(floorGdsList)){
                        List<Long> propIds = new ArrayList<Long>();
                        propIds.add(1001l);//作者
                        propIds.add(1020l);//内容简介
                        String standard = "";//图片规格
                        if (StringUtil.isNotEmpty(reqVO.getImgWidth())&& StringUtil.isNotEmpty(reqVO.getImgHeight())) {
                            standard = reqVO.getImgWidth() + "x" + reqVO.getImgHeight() + "!";
                        } 
                        try {
                            gdsInfoList = this.floorGdsToGdsDetailList(floorGdsList, propIds, standard, CmsConstants.PlatformType.CMS_PLATFORMTYPE_03,staffIdLong);
                            result.put("result", gdsInfoList);
                            result.put("resultFlag", "ok");
                            result.put("pageNumber", floorGdsPage.getPageNo());
                            result.put("lastRowIndex", floorGdsPage.getCount());
                        } catch (Exception e) {
                            result.put("resultFlag", "exception");
                            LogUtil.error(MODULE, "扩展楼层商品数据异常", e);
                        }
                    }else{
                        result.put("resultFlag", "empty");
                    }
                }
            }
            
            //扩展数据  收藏信息以及促销信息 start
            //Map<Long,Long> collectMap = new HashMap<Long, Long>();  //由于跟app公用暂时没有方案
            Map<Long,String> promMap = new HashMap<Long, String>();
            if(CollectionUtils.isNotEmpty(gdsInfoList)){
                for(GdsInfoDetailRespDTO gds : gdsInfoList){
                    if(StringUtil.isNotEmpty(gds.getId())){
                        //扩展收藏信息
                        /*GdsCollectRespDTO collectRestDto = null;
                        try {
                            collectRestDto = CmsGoodsDetailUtil.checkCollect(gds.getId());
                        } catch (Exception e) {
                            LogUtil.error(MODULE, "扩展商品收藏情况异常！商品id："+gds.getId(), e);
                        }
                        if(null !=collectRestDto && StringUtil.isNotEmpty(collectRestDto.getId())){
                            collectMap.put(gds.getId(), collectRestDto.getId());
                        }*/
                        //扩展促销信息
                        String promType = "";
                        //有免邮展示免邮
                        if(CmsConstants.ParamStatus.CMS_PARAMSTATUS_1.equalsIgnoreCase(gds.getIfFree())){
                            promType = "免邮";
                        }else{
                            List<PromListRespDTO> promList = null ;
                            try {
                                promList = CmsGoodsDetailUtil.getPromListForSolr(gds,CmsConstants.PlatformType.CMS_PLATFORMTYPE_03); 
                            } catch (Exception e) {
                                LogUtil.error(MODULE, "扩展商品促销情况异常！商品id："+gds.getId(), e);
                            }  
                            if(CollectionUtils.isNotEmpty(promList)){
                                for(PromListRespDTO prom : promList){
                                    if(null != prom && null != prom.getPromInfoDTO()){
                                        PromInfoDTO promInfo = prom.getPromInfoDTO();
                                        if(null != promInfo && StringUtil.isNotBlank(promInfo.getPromTypeShow())){
                                            promType = promInfo.getPromTypeShow();
                                            break;
                                        }
                                    }
                                }
                            }
                        }
                        promMap.put(gds.getId(), promType);
                    }
                }
            }
            //扩展数据  收藏信息以及促销信息 end
            //result.put("collectMap", collectMap);
            result.put("promMap", promMap);
        }
        return result;
    }
    /*//设置需要的单品属性Id
    List<Long> propIds = new ArrayList<Long>();
    propIds.add(1001l);//作者
    propIds.add(1005l);//出版日期
    propIds.add(1006l);//出版社
    propIds.add(1020l);//内容简介
    propIds.add(1023l);//编辑推荐
    propIds.add(1024l);//专家推荐
    propIds.add(1026l);//在线试读pdf
    propIds.add(1031l);//是否提供试读
     */
}
