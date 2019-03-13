package com.zengshi.ecp.busi.main.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.ArrayUtils;
import org.jsoup.Jsoup;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zengshi.ecp.base.controller.EcpBaseController;
import com.zengshi.ecp.base.vo.EcpBasePageRespVO;
import com.zengshi.ecp.busi.main.vo.ComponentReqVO;
import com.zengshi.ecp.busi.pageConfig.pageConfig.modular.utils.CmsAnalUtil;
import com.zengshi.ecp.busi.pageConfig.pageConfig.modular.utils.CmsGoodsDetailUtil;
import com.zengshi.ecp.cms.dubbo.dto.CmsAdvertiseReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsAdvertiseRespDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsFloorGdsReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsFloorGdsRespDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsFloorLabelReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsFloorLabelRespDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsFloorReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsFloorRespDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsFloorTabReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsFloorTabRespDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsPageInfoReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsPageInfoRespDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsPlaceReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsPlaceRespDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsRecommendReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsRecommendRespDTO;
import com.zengshi.ecp.cms.dubbo.interfaces.ICmsAdvertiseRSV;
import com.zengshi.ecp.cms.dubbo.interfaces.ICmsFloorAdvertiseRSV;
import com.zengshi.ecp.cms.dubbo.interfaces.ICmsFloorGdsRSV;
import com.zengshi.ecp.cms.dubbo.interfaces.ICmsFloorLabelRSV;
import com.zengshi.ecp.cms.dubbo.interfaces.ICmsFloorRSV;
import com.zengshi.ecp.cms.dubbo.interfaces.ICmsFloorTabRSV;
import com.zengshi.ecp.cms.dubbo.interfaces.ICmsPageInfoRSV;
import com.zengshi.ecp.cms.dubbo.interfaces.ICmsPlaceRSV;
import com.zengshi.ecp.cms.dubbo.interfaces.ICmsRecommendRSV;
import com.zengshi.ecp.cms.dubbo.interfaces.ICmsSiteRSV;
import com.zengshi.ecp.cms.dubbo.util.CmsConstants;
import com.zengshi.ecp.goods.dubbo.constants.GdsConstants;
import com.zengshi.ecp.goods.dubbo.constants.GdsOption.GdsQueryOption;
import com.zengshi.ecp.goods.dubbo.constants.GdsOption.SkuQueryOption;
import com.zengshi.ecp.goods.dubbo.dto.GdsCategoryRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsEvalReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsEvalRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsGuessHomePageRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsGuessYLReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfo.GdsInfoDetailRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfo.GdsInfoReqDTO;
import com.zengshi.ecp.goods.dubbo.interfaces.IGdsEvalRSV;
import com.zengshi.ecp.goods.dubbo.interfaces.IGdsGuessYLRSV;
import com.zengshi.ecp.goods.dubbo.interfaces.IGdsInfoQueryRSV;
import com.zengshi.ecp.goods.dubbo.interfaces.IGdsSkuInfoQueryRSV;
import com.zengshi.ecp.goods.dubbo.util.GdsUtils;
import com.zengshi.ecp.order.dubbo.interfaces.IOrdMainRSV;
import com.zengshi.ecp.order.dubbo.util.LongUtils;
import com.zengshi.ecp.prom.dubbo.interfaces.IPromQueryRSV;
import com.zengshi.ecp.server.front.dto.BaseParamDTO;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.server.front.util.BaseParamUtil;
import com.zengshi.ecp.staff.dubbo.dto.CustInfoReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.CustInfoResDTO;
import com.zengshi.ecp.staff.dubbo.dto.ShopInfoResDTO;
import com.zengshi.ecp.staff.dubbo.dto.ShopSelectReqDTO;
import com.zengshi.ecp.staff.dubbo.interfaces.ICustInfoRSV;
import com.zengshi.ecp.staff.dubbo.interfaces.IShopInfoRSV;
import com.zengshi.ecp.system.util.ParamsTool;
import com.zengshi.ecp.system.util.ParamsTool.Page;
import com.zengshi.ecp.system.util.ParamsTool.PageTitle;
import com.zengshi.paas.utils.FileUtil;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.StringUtil;

/**
 * 商城-页面: ECP <br>
 * Project Name:ecp-web-manage <br>
 * Description: <br>
 * Date:2015年8月17日下午6:54:49 <br>
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

    private final int GDSDESCCONTENT_LENGTH = Integer.MAX_VALUE;
    
    @Resource(name = "cmsAdvertiseRSV")
    private ICmsAdvertiseRSV cmsAdvertiseRSV;
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
    @Resource(name = "gdsSkuInfoQueryRSV")
    private IGdsSkuInfoQueryRSV gdsSkuInfoQueryRSV;
    @Resource(name = "cmsFloorAdvertiseRSV")
    private ICmsFloorAdvertiseRSV cmsFloorAdvertiseRSV;
    @Resource(name = "cmsFloorLabelRSV")
    private ICmsFloorLabelRSV cmsFloorLabelRSV;
    @Resource(name = "cmsRecommendRSV")
    private ICmsRecommendRSV cmsRecommendRSV;
    @Resource (name = "gdsGuessYLRSV")
    private IGdsGuessYLRSV gdsGuessYLRSV;
    @Resource(name = "cmsSiteRSV")
    private ICmsSiteRSV cmsSiteRSV;
    @Resource
    private IOrdMainRSV orderMainRSV;
    @Resource
    private IGdsEvalRSV iGdsEvalRSV;
    @Resource
    private ICustInfoRSV iCustInfoRSV;
    @Resource
    private IShopInfoRSV shopinfoRSV;
    @Resource(name = "cmsPageInfoRSV")
    private ICmsPageInfoRSV cmsPageInfoRSV;
    @Resource
    private IGdsInfoQueryRSV iGdsInfoQueryRSV;
    @Resource
    private IPromQueryRSV promQueryRSV;
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
    public String init(Model model, 
            HttpSession session,
            @RequestParam(value = "page", required = false) String page) {

        String url = "/main/homepage/homepage-content";// 返回页面
        String title = PageTitle.HOMEPAGE;// 页面title

        if (Page.HOMEPAGE.equals(page)) {// 商城-一级页面-首页
            url = "/main/homepage/homepage-content";
            title = PageTitle.HOMEPAGE;
        } else if (Page.REFERENCEBOOK.equals(page)) {// 商城-二级页面-参考书
            url = "/main/referencebook/reference-content";
            title = PageTitle.REFERENCEBOOK;
        } else if (Page.PAPERBOOK.equals(page)) {// 商城-二级页面-纸质教材
            url = "/main/paperbook/paper-content";
            title = PageTitle.PAPERBOOK;
        } else if (Page.TESTBOOK.equals(page)) {// 商城-二级页面-考试书
            url = "/main/testbook/test-content";
            title = PageTitle.TESTBOOK;
        } else if (Page.SCIENCEBOOK.equals(page)) {// 商城-二级页面-科普书
            url = "/main/sciencebook/science-content";
            title = PageTitle.SCIENCEBOOK;
        } else if (Page.AUDIOBOOK.equals(page)) {// 商城-二级页面-音像制品
            url = "/main/audiobook/audio-content";
            title = PageTitle.AUDIOBOOK;
        } else if (Page.DIGITALPRODUCT.equals(page)) {// 商城-二级页面-数字产品
            url = "/main/digitalproduct/digitalproduct-content";
            title = PageTitle.DIGITALPRODUCT;
        } else if (Page.DIGITALBOOK.equals(page)) {// 商城-二级页面-数字教材
            url = "/main/digitalbook/digital-content";
            title = PageTitle.DIGITALBOOK;
        } else if (Page.ONLINETEST.equals(page)) {// 商城-二级页面-在线考试培训
            url = "/main/onlinetest/onlinetest-content";
            title = PageTitle.ONLINETEST;
        } else if (Page.ELECTRONICBOOK.equals(page)) {// 商城-二级页面-电子书
            url = "/main/electronicbook/electronic-content";
            title = PageTitle.ELECTRONICBOOK;
        }else {// 商城-一级页面-首页
            url = "/main/homepage/homepage-content";
            title = PageTitle.HOMEPAGE;
        }
        
        model.addAttribute("HTML_TITLE", title);
        model.addAttribute("page", page);
        return url;
    }
    
    /** 
     * staticlink:(静态文件链接). <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author jiangzh 
     * @param model
     * @param page
     * @return 
     * @since JDK 1.6 
     */ 
    @RequestMapping(value = "/staticlink")
    public String staticlink(Model model, @RequestParam(value = "page", required = false) String page) {

        String url = "/main/staticlink/";// 返回页面
        if(StringUtil.isNotEmpty(page)){
            url += page.replace(" ", "");
        }
        model.addAttribute("HTML_TITLE", PageTitle.HOMEPAGE);
        model.addAttribute("page", page);
        return url;
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
    public String qryFloorVM(Model model,ComponentReqVO reqVO,HttpServletRequest request) throws Exception {
        LogUtil.info(MODULE, "==========reqVO:" + reqVO.toString() + ";");
        
        // 返回URL
        String url  = "/main/homepage/child/floor";
        
        CmsFloorRespDTO floorRespDTO = null;
        CmsAdvertiseRespDTO cmsAdvertiseRespDTO = null;
        List<CmsFloorLabelRespDTO> floorLabelList = null;
        List<CmsFloorTabRespDTO> floorTabRespList = null;
        
        try{
            // 1 根据内容位置ID查询有效楼层
            floorRespDTO = this.qryFloorByPlaceId(reqVO);
            
            // 2 根据楼层ID查询楼层广告
            List<CmsAdvertiseRespDTO> advertiseList = this.qryAdvertiseList(reqVO,request);
            if(!CollectionUtils.isEmpty(advertiseList)){
                cmsAdvertiseRespDTO = advertiseList.get(0);
            }
            
            // 3 根据内容位置查询楼层标签
            CmsFloorLabelReqDTO cmsFloorLabelReqDTO =  new CmsFloorLabelReqDTO();
            cmsFloorLabelReqDTO.setFloorId(floorRespDTO.getId());// 楼层ID
            cmsFloorLabelReqDTO.setStatus(reqVO.getStatus());
            floorLabelList = cmsFloorLabelRSV.queryCmsFloorLabelList(cmsFloorLabelReqDTO);
        
            // 4 当有效楼层不为空时，根据楼层ID查询页签
            CmsFloorTabReqDTO cmsFloorTabReqDTO = new CmsFloorTabReqDTO();
            cmsFloorTabReqDTO.setFloorId(floorRespDTO.getId());// 楼层ID
            floorTabRespList = this.qryFloorTabByFloorId(reqVO,cmsFloorTabReqDTO);
        }catch(Exception err){
            LogUtil.error(MODULE, "查询楼层信息出现异常:",err);
        }
        
        model.addAttribute("floorRespDTO", floorRespDTO);
        model.addAttribute("floorAdvertise", cmsAdvertiseRespDTO);
        model.addAttribute("floorLabelList", floorLabelList);
        model.addAttribute("floorTabList", floorTabRespList);
        return url;
    }
    
    /** 
     * qryAdvertiseList:(这里用一句话描述这个方法的作用). <br/> 
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
    public List<CmsAdvertiseRespDTO> qryAdvertiseList(ComponentReqVO reqVO,HttpServletRequest request) throws Exception {
        LogUtil.info(MODULE, "==========reqVO:" + reqVO.toString() + ";");
        
        List<CmsAdvertiseRespDTO> respList = null;
        
        try{
            // 1. 判断入参
            if(StringUtil.isNotEmpty(reqVO.getAdPlaceId())){//内容位置
                CmsAdvertiseReqDTO reqDTO = new CmsAdvertiseReqDTO();
                reqDTO.setPlaceId(reqVO.getAdPlaceId());
                reqDTO.setStatus(CmsConstants.ParamStatus.CMS_PARAMSTATUS_1);
                String standard = "194x370!";// 规格
           
                // 2. 调用广告服务，无分页
                respList = cmsAdvertiseRSV.queryCmsAdvertiseList(reqDTO);
                // 3. 返回图片地址及广告链接地址
                String baseUrl = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();
                if (!CollectionUtils.isEmpty(respList)) {
                    for (CmsAdvertiseRespDTO dto : respList) {
                        // 3.1调文件服务器，返回图片
                        if (StringUtil.isNotBlank(dto.getVfsId())) {
                            dto.setVfsUrl(ParamsTool.getImageUrl(dto.getVfsId(),standard));
                        }
                        // 3.2广告地址
                        this.getAdLinkUrl(dto, baseUrl);
                    }
                }
            }
        }catch(Exception err){
            LogUtil.error(MODULE, "查询广告出现异常:",err);
        }
        return respList;
    }
    /**
     * 
     * getLinkUrl:(获取广告的链接地址). <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author zhanbh 
     * @param dto
     * @param baseUrl 
     * @since JDK 1.6
     */
    private void getAdLinkUrl(CmsAdvertiseRespDTO dto,String baseUrl){
        if(StringUtil.isBlank(baseUrl)){
            baseUrl = "";
        }
        if (dto != null && StringUtil.isNotBlank(dto.getLinkUrl())) {
            String linkUrl = dto.getLinkUrl();
            
            if(CmsConstants.LinkType.CMS_LINKTYPE_01.equalsIgnoreCase(dto.getLinkType())){//商品
                if(CmsGoodsDetailUtil.isNumeric(linkUrl)){
                    linkUrl = "/gdsdetail/"+linkUrl+"-";
                }
                linkUrl = baseUrl + linkUrl;
            }else if(CmsConstants.LinkType.CMS_LINKTYPE_02.equalsIgnoreCase(dto.getLinkType())){//公告
                if(CmsGoodsDetailUtil.isNumeric(linkUrl)){
                    linkUrl = "/info/infodetail?id="+ linkUrl;
                }
                linkUrl = baseUrl+linkUrl;
            }else if(CmsConstants.LinkType.CMS_LINKTYPE_03.equalsIgnoreCase(dto.getLinkType())){
                if(CmsGoodsDetailUtil.isNumeric(linkUrl)){//页面
                    CmsPageInfoReqDTO pageInfoReqDTO = new CmsPageInfoReqDTO();
                    pageInfoReqDTO.setId(Long.valueOf(linkUrl.trim()));
                    CmsPageInfoRespDTO cmsPageInfoRespDTO = cmsPageInfoRSV.queryCmsPageInfo(pageInfoReqDTO);
                    if (cmsPageInfoRespDTO != null && StringUtil.isNotEmpty(cmsPageInfoRespDTO.getSiteUrl())) {
                        linkUrl = cmsPageInfoRespDTO.getSiteUrl();
                    } else {
                        linkUrl = "/modularcommon?pageId=" + linkUrl;
                    }
                }
                linkUrl = baseUrl+linkUrl;
            }else if(CmsConstants.LinkType.CMS_LINKTYPE_04.equalsIgnoreCase(dto.getLinkType())){//其他
                String regex = "^([a-zA-Z]+:(/|\\\\){2})|([a-z0-9A-Z]+(?:-[a-z0-9A-Z]+)*\\.){2,}";//常见的 绝对url格式  并非全部
                Pattern p = Pattern.compile(regex);
                Matcher m = p.matcher(linkUrl);
                if(m.lookingAt()){//绝对地址
                    if(linkUrl.indexOf("://") < 0){
                        linkUrl = "http://"+linkUrl; 
                    }
                }else{//相对地址
                    if(!linkUrl.startsWith("/"));{
                        linkUrl = "/"+linkUrl;
                    }
                    linkUrl = baseUrl+linkUrl;
                }
            }
            
            //加adid  用于广告行为分析   add by zhanbh  2016.9.5
            String linkUrlPro = linkUrl;
            if(StringUtil.isNotEmpty(dto.getId())){
                String [] urlParts = linkUrlPro.split("\\?");
                linkUrlPro = "";
                if(urlParts != null && urlParts.length > 0){
                    int len = urlParts.length;
                    String adidStr = "?adid=";
                    adidStr += String.valueOf(dto.getId());
                    if(len > 1){
                        adidStr += "&"; 
                    }
                    
                    for (int i = 0; i < len ; i++){
                        linkUrlPro += urlParts[i];
                        if(i==0){
                            linkUrlPro += adidStr;
                        }else if(i < len - 1){
                            linkUrlPro += "?";
                        }
                    }
                }
                if(StringUtil.isNotBlank(linkUrlPro)){
                    linkUrl =   linkUrlPro;
                }
            }
            
            dto.setLinkUrl(linkUrl);
        }
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
                cmsFloorRespDTO = this.qryFloorByPlaceId(reqVO);
                model.addAttribute("floorInfo", cmsFloorRespDTO);
            }
            // 图片规格
            String standard = "150x150!";
            
            // 调用，并结果返回；从后场返回的分页对象，封装为前店所需要的分页对象；
            EcpBasePageRespVO<GdsInfoDetailRespDTO> respVO = null;
            if(CmsConstants.DataSource.CMS_DATA_SOURCE_02.equalsIgnoreCase(cmsFloorRespDTO.getDataSource())){//去行为分析
                try {
                    respVO = this.getGoodsRank(reqVO,cmsFloorRespDTO,cmsFloorTabRespDTO);
                }catch (BusinessException busE){
                    LogUtil.error(MODULE, "查询行为分析商品出现异常:",busE);
                }catch(Exception err){
                    LogUtil.error(MODULE, "查询楼层商品出现异常:",err);
                }
            }else{
                respVO = this.qryGdsInfoDetailByPage(reqDTO, standard);
            }
            
            super.addPageToModel(model, respVO);
            model.addAttribute("reqVO", reqVO);
        }catch(Exception err){
            LogUtil.error(MODULE, "查询楼层信息出现异常:",err);
        }
        return url;
    }

    /** 
     * qryrankinglist:(查看排行榜列表). <br/> 
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
    @RequestMapping(value = "/qryrankinglist")
    public String qryrankinglist(Model model, ComponentReqVO reqVO) throws Exception {
        String url = "/main/ranking/child/child-list";
        
        CmsFloorGdsReqDTO reqDTO = reqVO.toBaseInfo(CmsFloorGdsReqDTO.class);
        if(StringUtil.isEmpty(reqVO.getPlaceId())&& StringUtil.isEmpty(reqVO.getTabId())){
            throw new Exception("参数placeId与tabId都为空");
        }
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
                cmsFloorRespDTO = this.qryFloorByPlaceId(reqVO);
                model.addAttribute("floorInfo", cmsFloorRespDTO);
            }
            // 图片规格
            String standard = "150x150!";
            
            // 调用，并结果返回；从后场返回的分页对象，封装为前店所需要的分页对象；
            EcpBasePageRespVO<GdsInfoDetailRespDTO> respVO = null;
            if(CmsConstants.DataSource.CMS_DATA_SOURCE_02.equalsIgnoreCase(cmsFloorRespDTO.getDataSource())){//取行为分析
                try {
                    respVO = this.getGoodsRank(reqVO,cmsFloorRespDTO,cmsFloorTabRespDTO);
                }catch (BusinessException busE){
                    LogUtil.error(MODULE, "查询行为分析商品出现异常:",busE);
                }catch(Exception err){
                    LogUtil.error(MODULE, "查询楼层商品出现异常:",err);
                }
            }else{
                respVO = this.qryGdsInfoDetailByPage(reqDTO, standard);
            }
            
            super.addPageToModel(model, respVO);
            model.addAttribute("reqVO", reqVO);
        }catch(Exception err){
            LogUtil.error(MODULE, "查询楼层信息出现异常:",err);
        }
        return url;
    }

    /** 
     * qryFloorByTemplateId:(根据模板ID获取楼层). <br/> 
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
    @RequestMapping(value = "/qryFloorByTemplateId")
    @ResponseBody
    public Map<String, Object> qryFloorByTemplateId(Model model,ComponentReqVO reqVO) throws Exception {
        LogUtil.info(MODULE, "==========reqVO:" + reqVO.toString() + ";");
        
        Map<String, Object> resultMap = new HashMap<String, Object>();

        /*1.验证前店入参*/
        CmsPlaceReqDTO cmsPlaceReqDTO = new CmsPlaceReqDTO();
        if(StringUtil.isEmpty(reqVO.getTemplateId())){//模板ID
            LogUtil.error(MODULE, "入参templateId为空！");
            resultMap.put("floorRespList", null);
            return resultMap;
        }
        cmsPlaceReqDTO.setTemplateId(reqVO.getTemplateId());
        if (StringUtil.isEmpty(reqVO.getPlaceType())) {// 内容位置类型
            LogUtil.error(MODULE, "入参placeType为空！");
            resultMap.put("floorRespList", null);
            return resultMap;
        }
        cmsPlaceReqDTO.setPlaceType(reqVO.getPlaceType());
        if (StringUtil.isNotBlank(reqVO.getStatus())) {//状态
            cmsPlaceReqDTO.setStatus(reqVO.getStatus());
        } else {//默认有效
            cmsPlaceReqDTO.setStatus(CmsConstants.ParamStatus.CMS_PARAMSTATUS_1);
        }
        
        // 2 通过模板ID查询内容位置
        List<CmsPlaceRespDTO> placeRespList = cmsPlaceRSV.queryCmsPlaceList(cmsPlaceReqDTO);
        List<CmsFloorRespDTO> floorRespList = new ArrayList<CmsFloorRespDTO>();

        // 3 迭代内容位置，根据内容位置查询楼层信息，加入LIST。
        if (!CollectionUtils.isEmpty(placeRespList)) {
            for (CmsPlaceRespDTO cmsPlaceRespDTO : placeRespList) {
                CmsFloorReqDTO cmsFloorReqDTO = new CmsFloorReqDTO();
                if (StringUtil.isNotBlank(reqVO.getStatus())) {
                    cmsFloorReqDTO.setStatus(reqVO.getStatus());
                }
                cmsFloorReqDTO.setPlaceId(cmsPlaceRespDTO.getId());
                List<CmsFloorRespDTO> cmsFloorRespDTOList = cmsFloorRSV.queryCmsFloorList(cmsFloorReqDTO);
                if (!CollectionUtils.isEmpty(cmsFloorRespDTOList)) {
                    floorRespList.add(cmsFloorRespDTOList.get(0));
                }
            }
        }

        // 4.返回结果
        resultMap.put("floorRespList", floorRespList);
        return resultMap;
    }
    
    
    /** 
     * qryFloorList:(根据内容位置获取楼层信息). <br/>   2222
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
    @RequestMapping(value = "/qryFloorList")
    @ResponseBody
    public Map<String,Object> qryFloorList(Model model,ComponentReqVO reqVO) throws Exception {
        if(reqVO == null ){
            reqVO = new ComponentReqVO();
        }
        LogUtil.info(MODULE, "==========reqVO:" + reqVO.toString() + ";" );
        if(StringUtil.isNotEmpty(reqVO.getGdsSize())){
            reqVO.setPageSize(reqVO.getGdsSize());
        }
        Map<String,Object> resultMap = new HashMap<String,Object>();
        // 图片规格
        String standard = "";
        if (StringUtil.isNotEmpty(reqVO.getPlaceWidth())&& StringUtil.isNotEmpty(reqVO.getPlaceHeight())) {
            standard = reqVO.getPlaceWidth() + "x" + reqVO.getPlaceHeight() + "!";
        } 
        CmsFloorRespDTO cmsFloorRespDTO = null;
        List<CmsFloorTabRespDTO> floorTabRespList = null;
        List<GdsInfoDetailRespDTO> gdsInfoDetailRespList = null;
        try{
            // 1 根据内容位置ID查询有效楼层
            cmsFloorRespDTO = this.qryFloorByPlaceId(reqVO);
            LogUtil.info(MODULE, "查询楼层返回信息:"+(StringUtil.isEmpty(cmsFloorRespDTO)?cmsFloorRespDTO:cmsFloorRespDTO.toString()));
        }catch (Exception e){
            LogUtil.error(MODULE, "查询楼层出现异常:",e);
            resultMap.put("errorMsg","查询楼层出现异常!");
        }
        if(cmsFloorRespDTO != null && StringUtil.isNotEmpty(cmsFloorRespDTO.getId())){
            // 2 当有效楼层不为空时，根据楼层ID查询页签
            if (reqVO.getTabSize()!=null && reqVO.getTabSize() > 0) {//页签大于0 即启用页签
                CmsFloorTabReqDTO cmsFloorTabReqDTO = new CmsFloorTabReqDTO();
                cmsFloorTabReqDTO.setFloorId(cmsFloorRespDTO.getId());// 楼层ID
                try {
                    floorTabRespList = this.qryFloorTabByFloorId(reqVO, cmsFloorTabReqDTO);
                    LogUtil.info(MODULE, "查询页签返回信息:"+(CollectionUtils.isEmpty(floorTabRespList)?floorTabRespList:floorTabRespList.size()));
                } catch (Exception e) {
                    LogUtil.error(MODULE, "查询楼层页签出现异常:",e);
                    resultMap.put("errorMsg","查询楼层页签出现异常!");
                }
            } 
            // 3 当页签不为空时，获取第一个页签的商品，当页签为空时，获取楼层下的商品
            CmsFloorGdsReqDTO cmsFloorGdsReqDTO = new CmsFloorGdsReqDTO();
            cmsFloorGdsReqDTO.setFloorId(cmsFloorRespDTO.getId());// 楼层ID
            CmsFloorTabRespDTO cmsFloorTabRespDTO = null;
            if(!CollectionUtils.isEmpty(floorTabRespList)){
                cmsFloorTabRespDTO = floorTabRespList.get(0);
                if(cmsFloorTabRespDTO != null && StringUtil.isNotEmpty(cmsFloorTabRespDTO.getId())){
                    cmsFloorGdsReqDTO.setTabId(cmsFloorTabRespDTO.getId());
                }
            }
            if(CmsConstants.DataSource.CMS_DATA_SOURCE_02.equalsIgnoreCase(cmsFloorRespDTO.getDataSource())){//去行为分析
                EcpBasePageRespVO<GdsInfoDetailRespDTO> goodRankPage = null;
                try {
                    goodRankPage = this.getGoodsRank(reqVO,cmsFloorRespDTO,cmsFloorTabRespDTO);
                }catch (BusinessException busE){
                    LogUtil.error(MODULE, "查询行为分析商品出现异常:",busE);
                    resultMap.put("errorMsg", busE.getMessage());
                }catch(Exception err){
                    LogUtil.error(MODULE, "查询楼层商品出现异常:",err);
                    resultMap.put("errorMsg", "出现异常！");
                }
                if(goodRankPage!=null){
                    gdsInfoDetailRespList = goodRankPage.getList();
                }
            }else{
                try {
                    gdsInfoDetailRespList = this.qryFloorGdsList(reqVO,cmsFloorGdsReqDTO,standard);
                } catch (Exception e) {
                    LogUtil.error(MODULE, "查询楼层商品出现异常:",e);
                    resultMap.put("errorMsg", "出现异常！");
                }
                
            }
        }
        // 4.返回结果
        resultMap.put("floorRespDTO", cmsFloorRespDTO);
        resultMap.put("floorTabList", floorTabRespList);
        resultMap.put("gdsList", gdsInfoDetailRespList);
        return resultMap;
    }
    
    /**
     * 
     * getGoodsRank:(调用用户行为分析返回商品数据). <br/> 
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
    private EcpBasePageRespVO<GdsInfoDetailRespDTO> getGoodsRank(ComponentReqVO reqVO,CmsFloorRespDTO cmsFloorRespDTO,
            CmsFloorTabRespDTO cmsFloorTabRespDTO) throws Exception {
        LogUtil.info(MODULE, "调用用户行为分析服务开始:");
        if(cmsFloorRespDTO == null || StringUtil.isEmpty(cmsFloorRespDTO.getId())){
            return null;
        }
        if(cmsFloorTabRespDTO!=null && cmsFloorTabRespDTO.getFloorId().longValue() !=  cmsFloorRespDTO.getId().longValue()){
            return null;
        }
        if(reqVO == null ){
            reqVO = new ComponentReqVO();
        }
        // 图片规格
        String standard = "";
        if (StringUtil.isNotEmpty(reqVO.getPlaceWidth())&& StringUtil.isNotEmpty(reqVO.getPlaceHeight())) {
            standard = reqVO.getPlaceWidth() + "x" + reqVO.getPlaceHeight() + "!";
        }
        
        String catgCode = null;
        if(cmsFloorTabRespDTO!=null && StringUtil.isNotBlank(cmsFloorTabRespDTO.getCatgCode())){//tab页的优先级更高
            catgCode = cmsFloorTabRespDTO.getCatgCode();
        }else{
            catgCode = cmsFloorRespDTO.getCatgCode();
        }
        List<Long> propIds = new ArrayList<Long>();
        propIds.add(1001l);//作者
        propIds.add(1005l);//出版日期
        propIds.add(1006l);//出版社
        propIds.add(1020l);//内容简介
        EcpBasePageRespVO<GdsInfoDetailRespDTO> gdsPageInfo = null;
        List<GdsInfoDetailRespDTO> gdsDetailInfoList = null;
        gdsPageInfo = CmsAnalUtil.getAnalRankDataPage(reqVO.getPageNumber(), reqVO.getPageSize(), cmsFloorRespDTO.getCountType(), catgCode, null, propIds);
        if(null != gdsPageInfo){
            gdsDetailInfoList = gdsPageInfo.getList();
        }
        //扩展信息
        if(!CollectionUtils.isEmpty(gdsDetailInfoList)){
            for(GdsInfoDetailRespDTO gdsInfo : gdsDetailInfoList ){
                CmsGoodsDetailUtil.extendGdsInfo(gdsInfo, propIds, true, true, standard);
                CmsGoodsDetailUtil.getGdsPromPrice(gdsInfo, CmsConstants.PlatformType.CMS_PLATFORMTYPE_01, null);
            }
        }
        return gdsPageInfo;
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
        LogUtil.info(MODULE, "==========reqVO:" + reqVO.toString() + ";");
        
        if(StringUtil.isNotEmpty(reqVO.getGdsSize())){
            reqVO.setPageSize(reqVO.getGdsSize());
        }
        
        Map<String,Object> resultMap = new HashMap<String,Object>();
        List<GdsInfoDetailRespDTO> gdsInfoDetailRespList = null;
        
        // 1. 判断入参
        CmsFloorGdsReqDTO cmsFloorGdsReqDTO = new CmsFloorGdsReqDTO();
        
        CmsFloorTabRespDTO cmsFloorTabRespDTO =null;
        if (StringUtil.isNotEmpty(reqVO.getTabId())) {//页签
            cmsFloorGdsReqDTO.setTabId(reqVO.getTabId());
            
            CmsFloorTabReqDTO floorTabReqDto = new CmsFloorTabReqDTO();
            floorTabReqDto.setId(reqVO.getTabId());
            cmsFloorTabRespDTO= this.cmsFloorTabRSV.queryCmsFloorTab(floorTabReqDto);
            
            if(cmsFloorTabRespDTO == null || StringUtil.isEmpty(cmsFloorTabRespDTO.getFloorId())){
                return null;
            }
            if(StringUtil.isNotEmpty(reqVO.getFloorId())&&cmsFloorTabRespDTO.getFloorId().longValue()!=reqVO.getFloorId().longValue()){
                return null;
            }
            reqVO.setFloorId(cmsFloorTabRespDTO.getFloorId());
        }
        
        //楼层id
        CmsFloorRespDTO cmsFloorRespDTO = null;
        reqVO.setFloorId(cmsFloorTabRespDTO.getFloorId());
        CmsFloorReqDTO floorReqDto = new CmsFloorReqDTO();
        floorReqDto.setId(reqVO.getFloorId());
        cmsFloorRespDTO=this.cmsFloorRSV.queryCmsFloor(floorReqDto);
            
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
            if(CmsConstants.DataSource.CMS_DATA_SOURCE_02.equalsIgnoreCase(cmsFloorRespDTO.getDataSource())){//去行为分析
                EcpBasePageRespVO<GdsInfoDetailRespDTO> goodRankPage = this.getGoodsRank(reqVO,cmsFloorRespDTO,cmsFloorTabRespDTO);
                if(goodRankPage!=null){
                    gdsInfoDetailRespList = goodRankPage.getList();
                }
            }else{
                // 2. 根据楼层页签ID获取商品
                List<CmsFloorGdsRespDTO> respList = new ArrayList<CmsFloorGdsRespDTO>();
                if(cmsFloorGdsReqDTO.getPageSize() != null && cmsFloorGdsReqDTO.getPageSize() != 0){
                  //商品下架或删除商城页面自动不再显示
                    PageResponseDTO<CmsFloorGdsRespDTO> pageInfo = null;
                    for(int size = cmsFloorGdsReqDTO.getPageSize(),respSize = respList.size();respSize < size ;respSize = respList.size()){
                        pageInfo = cmsFloorGdsRSV.queryCmsFloorGdsPage(cmsFloorGdsReqDTO);
                        if(pageInfo != null && !CollectionUtils.isEmpty(pageInfo.getResult())){
                            for(CmsFloorGdsRespDTO dto : pageInfo.getResult()){
                                if(CmsGoodsDetailUtil.isGdsOnShelves(dto.getGdsId())){
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
            }
        }catch(Exception err){
            LogUtil.error(MODULE, "查询查询商品出现异常:",err);
            resultMap.put("errorMsg", err.getMessage());
        }
        
        resultMap.put("gdsList", gdsInfoDetailRespList);
        return resultMap;
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
        
        //楼层id 与 页签id 二者必须有一个
        if(StringUtil.isEmpty(reqVO.getFloorId()) && StringUtil.isEmpty(reqVO.getTabId())){
            model.addAttribute("errMsg", "楼层数据获取错误！");
            return url;
        }
        
        List<GdsInfoDetailRespDTO> gdsInfoDetailRespList = null;

        String standard = "";//规格
        if(StringUtil.isNotEmpty(reqVO.getPlaceWidth()) && StringUtil.isNotEmpty(reqVO.getPlaceHeight())){
            standard = reqVO.getPlaceWidth() + "x" + reqVO.getPlaceHeight() + "!";
        }
        CmsFloorGdsReqDTO cmsFloorGdsReqDTO = new CmsFloorGdsReqDTO();
        if (StringUtil.isNotEmpty(reqVO.getFloorId())) {//楼层
            cmsFloorGdsReqDTO.setFloorId(reqVO.getFloorId());
        }
        if (StringUtil.isNotEmpty(reqVO.getTabId())) {//页签
            cmsFloorGdsReqDTO.setTabId(reqVO.getTabId());
        }
        if (StringUtil.isNotEmpty(reqVO.getStatus())) {//状态
            cmsFloorGdsReqDTO.setStatus(reqVO.getStatus());
        }else{
            cmsFloorGdsReqDTO.setStatus(CmsConstants.ParamStatus.CMS_PARAMSTATUS_1);
        }
        
        if (StringUtil.isNotEmpty(reqVO.getGdsSize())) {//楼层商品数量
            cmsFloorGdsReqDTO.setPageNo(1);
            cmsFloorGdsReqDTO.setPageSize(reqVO.getGdsSize());
        }
        
        // 2. 根据楼层页签ID获取商品
        try{
            List<CmsFloorGdsRespDTO> respList = new ArrayList<CmsFloorGdsRespDTO>();
            if(cmsFloorGdsReqDTO.getPageSize() != null && cmsFloorGdsReqDTO.getPageSize() != 0){
                //商品下架或删除商城页面自动不再显示
                PageResponseDTO<CmsFloorGdsRespDTO> pageInfo = null;
                for(int size = cmsFloorGdsReqDTO.getPageSize(),respSize = respList.size();respSize < size ;respSize = respList.size()){
                    pageInfo = cmsFloorGdsRSV.queryCmsFloorGdsPage(cmsFloorGdsReqDTO);
                    if(pageInfo != null && !CollectionUtils.isEmpty(pageInfo.getResult())){
                        for(CmsFloorGdsRespDTO dto : pageInfo.getResult()){
                            if(CmsGoodsDetailUtil.isGdsOnShelves(dto.getGdsId())){
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
     * queryRecommend:(获取专家推荐类型，形成页面页签). <br/> 
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
    @RequestMapping(value = "/queryRecommend")
    public String queryRecommend(Model model,ComponentReqVO reqVO) throws Exception {
        LogUtil.info(MODULE, "==========reqVO:" + reqVO.toString() + ";");
        
        List<BaseParamDTO> recommendTypeList = BaseParamUtil.fetchParamList("CMS_RECOMMEND_TYPE");
        model.addAttribute("recommendTypeList", recommendTypeList);
        
        return "/main/homepage/child/recommend";
    }
    
    /** 
     * queryRecommendInfo:(根据推荐类型查询推荐信息). <br/> 
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
    @RequestMapping(value = "/queryRecommendInfo")
    public String queryRecommendInfo(Model model,ComponentReqVO reqVO) throws Exception {
        LogUtil.info(MODULE, "==========reqVO:" + reqVO.toString() + ";");
        
        String url = "/main/homepage/child/recommendinfo";
        // 1.验证入参
        CmsRecommendReqDTO cmsRecommendReqDTO = new CmsRecommendReqDTO();
        String standard = "";//规格
        if(StringUtil.isNotEmpty(reqVO.getPlaceWidth()) && StringUtil.isNotEmpty(reqVO.getPlaceHeight())){
            standard = reqVO.getPlaceWidth() + "x" + reqVO.getPlaceHeight() + "!";
        }
        if (StringUtil.isNotEmpty(reqVO.getRecommendType())) {//推荐类型
            cmsRecommendReqDTO.setRecommendType(reqVO.getRecommendType());
        }
        if (StringUtil.isNotEmpty(reqVO.getStatus())) {// 状态
            cmsRecommendReqDTO.setStatus(reqVO.getStatus());
        }else {// 默认有效
            cmsRecommendReqDTO.setStatus(CmsConstants.ParamStatus.CMS_PARAMSTATUS_1);
        }
        
        // 2、查询专家推荐信息
        List<GdsInfoDetailRespDTO> otherProductionList = null;
        List<GdsInfoDetailRespDTO> recommendProductionList = null;
        List<GdsInfoDetailRespDTO> otherLikeList = null;
        GdsInfoDetailRespDTO gdsInfoDetailRespDTO = null;
        CmsRecommendRespDTO cmsRecommendRespDTO = null;
        try{
            List<CmsRecommendRespDTO> recommendRespDTOList = cmsRecommendRSV.queryCmsRecommendList(cmsRecommendReqDTO);
            if(!CollectionUtils.isEmpty(recommendRespDTOList)){
                // 2.1 查询专家推荐信息
                cmsRecommendRespDTO = recommendRespDTOList.get(0);
                if(cmsRecommendRespDTO != null){
                    // 2.2 将该作者其他的作品转换成中文
                    otherProductionList = recommendInfo(cmsRecommendRespDTO.getOtherProduction(),standard);
                    // 2.3 将该作者其他的作品转换成中文
                    recommendProductionList = recommendInfo(cmsRecommendRespDTO.getRecommendProduction(),standard);
                    // 2.4 将喜欢该作者还喜欢转换成中文
                    otherLikeList = recommendInfo(cmsRecommendRespDTO.getOtherLike(),standard);
                    // 2.5 查询专家推荐中的商品
                    gdsInfoDetailRespDTO = this.queryGdsInfoDetail(cmsRecommendRespDTO, standard);
                    // 2.6 调文件服务器，返回图片
                    //if (StringUtil.isNotBlank(cmsRecommendRespDTO.getAuthorImage())) {
                        cmsRecommendRespDTO.setAuthorImageUrl(ParamsTool.getImageUrl(cmsRecommendRespDTO.getAuthorImage(),"196x106!"));
                    //}
                    //2.7 调文件服务器，返回推荐信息
                        
                     // 根据ID读取内容简介内容
                        if(gdsInfoDetailRespDTO != null 
                                && gdsInfoDetailRespDTO.getSkuInfo() != null
                                && gdsInfoDetailRespDTO.getSkuInfo().getAllPropMaps() != null
                                && gdsInfoDetailRespDTO.getSkuInfo().getAllPropMaps().get("1020") != null
                                && gdsInfoDetailRespDTO.getSkuInfo().getAllPropMaps().get("1020").getValues()!=null
                                && gdsInfoDetailRespDTO.getSkuInfo().getAllPropMaps().get("1020").getValues().get(0)!=null){
                            byte[] editRecm = FileUtil.readFile(gdsInfoDetailRespDTO.getSkuInfo().getAllPropMaps().get("1020").getValues().get(0).getPropValue());
                                if (ArrayUtils.isNotEmpty(editRecm)) {
                                    String gdsEditRecm = Jsoup.parse(new String(editRecm)).text();
                                    if (gdsEditRecm.length() > GDSDESCCONTENT_LENGTH) {
                                        gdsEditRecm = gdsEditRecm.substring(0, GDSDESCCONTENT_LENGTH) + "...";
                                    }
                                    gdsInfoDetailRespDTO.getSkuInfo().getAllPropMaps().get("1020").getValues().get(0).setPropValue(gdsEditRecm);
                                }
                        }
                        if(gdsInfoDetailRespDTO!=null){
                            byte[] content = FileUtil.readFile(gdsInfoDetailRespDTO.getGdsDesc());
                            if (ArrayUtils.isNotEmpty(content)) {
                                String gdsDescContent = Jsoup.parse(new String(content)).text();
                                if (gdsDescContent.length() > GDSDESCCONTENT_LENGTH) {
                                    gdsDescContent = gdsDescContent.substring(0, GDSDESCCONTENT_LENGTH) + "...";
                                }
                                gdsInfoDetailRespDTO.setGdsDesc(gdsDescContent);
                            }
                        }
                }
            }
        }catch(Exception err){
            LogUtil.error(MODULE, "查询专家推荐出现异常:",err);
        }
        // 3. 返回结果
        model.addAttribute("cmsRecommendRespDTO", cmsRecommendRespDTO);
        model.addAttribute("gdsInfoDetailRespDTO", gdsInfoDetailRespDTO);
        model.addAttribute("otherProductionList", otherProductionList);
        model.addAttribute("recommendProductionList", recommendProductionList);
        model.addAttribute("otherLikeList", otherLikeList);
        return url;
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
        if(reqVO == null){
            reqVO = new  ComponentReqVO();
        }
        LogUtil.info(MODULE, "====首页猜你喜欢======reqVO:" + reqVO.toString() + ";");
        
        String url = "/main/homeGuessModule/home-guess";
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
            resultMap = getAnalyticeYL(reqVO,standard,request);
            if(resultMap == null || resultMap.get("detailRespDTOs") == null){//行为分析返回空则取配置
                resultMap = getGuessYL(reqVO,standard); 
            } 
            if(resultMap != null){
                model.addAttribute("detailRespDTOs", resultMap.get("detailRespDTOs"));
                model.addAttribute("categoryRespDTOs", resultMap.get("categoryRespDTOs"));
                model.addAttribute("errorMessage", resultMap.get("errorMessage"));
            }
        } catch (Exception e) {
            model.addAttribute("errorMessage", resultMap.get("获取数据异常！"));
        }
        return url;
    }
    
    /**
     * 
     * getGuessYL:(通过取商品域的猜你喜欢配置获取猜你喜欢数据). <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author zhanbh
     * @param model
     * @param reqVO
     * @param standard 
     * @since JDK 1.6
     */
    private Map<String,Object> getGuessYL(ComponentReqVO reqVO, String standard) {
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
            LogUtil.error(MODULE, "出错啦！请联系管理员:",e);
            guessHomeRespDto = null;
            errorMassage = "服务错误，请联系管理员！";
            resultMap.put("errorMassage", errorMassage);
        }
        //3.转化数据
        List<Long> propIds = new ArrayList<Long>();
        propIds.add(1001l);//作者
        if(!CollectionUtils.isEmpty(guessHomeRespDto.getDetailRespDTOs())){
            for (GdsInfoDetailRespDTO gdsInfoDetailRespDTO : guessHomeRespDto.getDetailRespDTOs()){
                if(gdsInfoDetailRespDTO != null){
                    // 3.1.1 商品图片
                    CmsGoodsDetailUtil.extendGdsInfo(gdsInfoDetailRespDTO, propIds, false, true, standard);
                    CmsGoodsDetailUtil.getGdsPromPrice(gdsInfoDetailRespDTO, CmsConstants.PlatformType.CMS_PLATFORMTYPE_01, null);
                }
            }
        }

        //返回数据
        if(guessHomeRespDto != null){
            resultMap.put("detailRespDTOs", guessHomeRespDto.getDetailRespDTOs());
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
     * getAnalyticeYL:(通过行为分析获取猜你喜欢数据). <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author zhanbh 
     * @param model
     * @param reqVO
     * @param standard
     * @param request 
     * @throws Exception 
     * @since JDK 1.6
     */
    @SuppressWarnings("unchecked")
    private Map<String,Object> getAnalyticeYL(ComponentReqVO reqVO, String standard, HttpServletRequest request){
        LogUtil.info(MODULE, "调用猜你喜欢行为分析开始：");
        Map<String,Object> resultMap = new HashMap<String, Object>();
        Map<String,Object> guessResultMap = new HashMap<String, Object>();
        
        List<Long> propIds = new ArrayList<Long>();
        propIds.add(1001l);//作者
        try {
            guessResultMap = CmsAnalUtil.getAnalysGuessData(reqVO.getPageSize(), propIds, request);
        } catch (Exception e) {
            LogUtil.error(MODULE, "获取数据异常！", e);
            return null;
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
        if(!CollectionUtils.isEmpty(gdsInfos)){
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
     * 
     * queryHotShop:(首页热门店铺). <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @param model
     * @param reqVO
     * @return 
     * @since JDK 1.6
     */
    @RequestMapping(value = "/queryhotshop")
    public String queryHotShop (Model model,ComponentReqVO reqVO){
        if(reqVO == null){
            reqVO = new  ComponentReqVO();
        }
        LogUtil.info(MODULE, "====首页热门店铺======reqVO:" + reqVO.toString() + ";");
        
        String url = "/main/hotShopModule/home-hotshop";
        //1.声明变量
        if (reqVO.getGdsSize()!= null) {//店铺数量
            reqVO.setPageNumber(1);
            reqVO.setPageSize(reqVO.getGdsSize());
        }
        
        ShopSelectReqDTO selectReqDTO = new ShopSelectReqDTO();
        //查询热门店铺
        selectReqDTO.setHotShowSupported("1");
        selectReqDTO.setPageNo(1);
        selectReqDTO.setPageSize(reqVO.getGdsSize());
        PageResponseDTO<ShopInfoResDTO> hotShopList = null;
        try {
            hotShopList = shopinfoRSV.listShopInfoByCond(selectReqDTO);
        } catch (Exception e) {
            e.printStackTrace();
            String errorMassage = "服务错误，请联系管理员！";
            LogUtil.error(MODULE, "出错啦！请联系管理员:",e);
            model.addAttribute("errorMassage", errorMassage);
        }
        model.addAttribute("hotShopList", hotShopList.getResult());
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
                            GdsQueryOption.BASIC, GdsQueryOption.MAINPIC 
                            };
                    SkuQueryOption[] skuOptions = new SkuQueryOption[] { 
                            SkuQueryOption.BASIC, SkuQueryOption.PROP,SkuQueryOption.CAlDISCOUNT
                            };
                    //设置需要的单品属性Id
                    List<Long> propIds = new ArrayList<Long>();
                    propIds.add(1001l);//作者
                    propIds.add(1005l);//出版日期
                    propIds.add(1006l);//出版社
                    propIds.add(1020l);//内容简介
                    /*propIds.add(1023l);//编辑推荐
                     propIds.add(1024l);//专家推荐*/                    
                    propIds.add(1026l);//在线试读pdf
                    propIds.add(1031l);//是否提供试读
                    
                    gdsInfoReqDTO.setPropIds(propIds);
                    gdsInfoReqDTO.setGdsQueryOptions(gdsOptions);
                    gdsInfoReqDTO.setSkuQuerys(skuOptions);
                    gdsInfoReqDTO.setId(dto.getGdsId());
                    GdsInfoDetailRespDTO gdsInfoDetailRespDTO =CmsGoodsDetailUtil.getGdsDetailByGdsId(gdsInfoReqDTO);
                    if (gdsInfoDetailRespDTO != null) {
                        CmsGoodsDetailUtil.extendGdsInfo(gdsInfoDetailRespDTO, propIds, true, true, standard);
                        CmsGoodsDetailUtil.getGdsPromPrice(gdsInfoDetailRespDTO, CmsConstants.PlatformType.CMS_PLATFORMTYPE_01, null);
                        gdsInfoDetailRespList.add(gdsInfoDetailRespDTO);
                    }
                }
            }
        }
        return gdsInfoDetailRespList;
    }
    
    /**
     * qryGdsInfoDetailByPage:(查询商品服务返回 EcpBasePageRespVO). <br/>
     * TODO(这里描述这个方法适用条件 – 可选).<br/>
     * TODO(这里描述这个方法的执行流程 – 可选).<br/>
     * TODO(这里描述这个方法的使用方法 – 可选).<br/>
     * TODO(这里描述这个方法的注意事项 – 可选).<br/>
     * 
     * @author zhanbh 2015.10.29
     * @param cmsFloorGdsReqDTO
     * @param standard
     * @return EcpBasePageRespVO
     * @since JDK 1.6
     */
    public EcpBasePageRespVO<GdsInfoDetailRespDTO> qryGdsInfoDetailByPage(CmsFloorGdsReqDTO cmsFloorGdsReqDTO, String standard) throws Exception{
        EcpBasePageRespVO<GdsInfoDetailRespDTO> respVO = new EcpBasePageRespVO<GdsInfoDetailRespDTO>();
        PageResponseDTO<CmsFloorGdsRespDTO> pageInfo = null;
        // 1. 根据楼层ID获取商品
        List<CmsFloorGdsRespDTO> respList = new ArrayList<CmsFloorGdsRespDTO>();
        if (cmsFloorGdsReqDTO.getPageSize() != null && cmsFloorGdsReqDTO.getPageSize() != 0) {
            pageInfo = cmsFloorGdsRSV.queryCmsFloorGdsPage(cmsFloorGdsReqDTO);
            if (pageInfo != null) {
                respList = pageInfo.getResult();
                respVO.setPageNumber(pageInfo.getPageNo());
                respVO.setPageSize(pageInfo.getPageSize());
                respVO.setTotalPage(pageInfo.getPageCount());
                respVO.setTotalRow(pageInfo.getCount());
            }else{
                respVO.setPageNumber(0);
                respVO.setPageSize(8);
                respVO.setTotalPage(0);
                respVO.setTotalRow(0);
            }
        }

        // 2 商品域 查询商品信息（单个）并根据页面要求处理商品
        List<GdsInfoDetailRespDTO> gdsInfoDetailRespList = this.qryGdsDetailList(respList,standard);
        respVO.setList(gdsInfoDetailRespList);
        return respVO;
    }  

    /** 
     * recommendInfo:(根据推荐的商品字符串转换成中文). <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author jiangzh 
     * @param recommendInfo
     * @param standard
     * @return
     * @throws Exception 
     * @since JDK 1.6 
     */ 
    public List<GdsInfoDetailRespDTO> recommendInfo(String recommendInfo,String standard) throws Exception{
        List<GdsInfoDetailRespDTO> otherProductionList = new ArrayList<GdsInfoDetailRespDTO>();
        if(StringUtil.isNotEmpty(recommendInfo)){
            String[] otherProductionArray = recommendInfo.split("、");
            if(otherProductionArray != null && otherProductionArray.length > 0){
                for(int i=0;i<otherProductionArray.length;i++){
                    if(StringUtil.isNotBlank(otherProductionArray[i])){
                        CmsRecommendRespDTO recommendTemp = new CmsRecommendRespDTO();
                        recommendTemp.setRecommendGdsId(Long.parseLong(otherProductionArray[i]));
                        GdsInfoDetailRespDTO gdsInfoDetailTemp = this.queryGdsInfoDetail(recommendTemp, standard);
                        if(gdsInfoDetailTemp != null && GdsConstants.GdsInfo.GDS_STATUS_ONSHELVES.equalsIgnoreCase(gdsInfoDetailTemp.getGdsStatus())){
                            otherProductionList.add(gdsInfoDetailTemp);
                        }   
                    }
                }
            }
        }
        return otherProductionList;
    }
    
    /** 
     * queryGdsInfoDetail:(根据商品ID获取商品信息). <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author jiangzh 
     * @param cmsRecommendRespDTO
     * @param standard
     * @return
     * @throws Exception 
     * @since JDK 1.6 
     */ 
    public GdsInfoDetailRespDTO queryGdsInfoDetail(CmsRecommendRespDTO cmsRecommendRespDTO,String standard) throws Exception {
        GdsInfoDetailRespDTO gdsInfoDetailRespDTO = null;
        if(StringUtil.isNotEmpty(cmsRecommendRespDTO.getRecommendGdsId())){
            GdsInfoReqDTO gdsInfoReqDTO = new GdsInfoReqDTO();
            GdsQueryOption[] gdsOptions=new GdsQueryOption[]{
                    GdsQueryOption.BASIC,GdsQueryOption.MAINPIC
            };
            SkuQueryOption[] skuOptions=new SkuQueryOption[]{
                    SkuQueryOption.BASIC,SkuQueryOption.PROP,SkuQueryOption.CAlDISCOUNT
            };
            //获取指定的属性值  提高效率 start
            List<Long> propIds = new ArrayList<Long>();
            propIds.add(1001l);//作者
            propIds.add(1020l);//内容简介
            gdsInfoReqDTO.setPropIds(propIds);
            //获取指定的属性值   提高效率 end
            
            gdsInfoReqDTO.setGdsQueryOptions(gdsOptions);
            gdsInfoReqDTO.setSkuQuerys(skuOptions);
            gdsInfoReqDTO.setId(cmsRecommendRespDTO.getRecommendGdsId());
            gdsInfoDetailRespDTO = CmsGoodsDetailUtil.getGdsDetailByGdsId(gdsInfoReqDTO);
            if(gdsInfoDetailRespDTO != null){
                CmsGoodsDetailUtil.extendGdsInfo(gdsInfoDetailRespDTO, propIds, true, true, standard);
                CmsGoodsDetailUtil.getGdsPromPrice(gdsInfoDetailRespDTO, CmsConstants.PlatformType.CMS_PLATFORMTYPE_01, null);
            }
        }
        
        return gdsInfoDetailRespDTO;
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
        // 3.1 查询楼层商品
        List<CmsFloorGdsRespDTO> respList = new ArrayList<CmsFloorGdsRespDTO>();
        if(cmsFloorGdsReqDTO.getPageSize() != null && cmsFloorGdsReqDTO.getPageSize() != 0){
            //商品下架或删除商城页面自动不再显示
            PageResponseDTO<CmsFloorGdsRespDTO> pageInfo = null;
            for(int size = cmsFloorGdsReqDTO.getPageSize(),respSize = respList.size();respSize < size ;respSize = respList.size()){
                pageInfo = cmsFloorGdsRSV.queryCmsFloorGdsPage(cmsFloorGdsReqDTO);
                if(pageInfo != null && !CollectionUtils.isEmpty(pageInfo.getResult())){
                    for(CmsFloorGdsRespDTO dto : pageInfo.getResult()){
                        if(CmsGoodsDetailUtil.isGdsOnShelves(dto.getGdsId())){
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
        // 3.2 商品域  查询商品信息（单个）并根据页面要求处理商品
        List<GdsInfoDetailRespDTO> gdsInfoDetailRespList = this.qryGdsDetailList(respList,standard);
        
        return gdsInfoDetailRespList;
    }
    /** 
     * hotComment:(用户热评). <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * 
     * @author zhuqr 
     * @return 
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
}
