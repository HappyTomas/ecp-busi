package com.zengshi.ecp.busi.pageConfig.utils.controller;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zengshi.ecp.base.controller.EcpBaseController;
import com.zengshi.ecp.base.vo.EcpBasePageRespVO;
import com.zengshi.ecp.busi.pageConfig.utils.vo.CmsLinkGdsCategoryTreeVO;
import com.zengshi.ecp.busi.pageConfig.utils.vo.CmsLinkGdsCategoryVO;
import com.zengshi.ecp.busi.pageConfig.utils.vo.CmsLinkGdsVO;
import com.zengshi.ecp.busi.pageConfig.utils.vo.CmsLinkInfoVO;
import com.zengshi.ecp.busi.pageConfig.utils.vo.CmsLinkInputUtilVO;
import com.zengshi.ecp.busi.pageConfig.utils.vo.CmsLinkManageVO;
import com.zengshi.ecp.busi.pageConfig.utils.vo.CmsLinkPageInfoVO;
import com.zengshi.ecp.cms.dubbo.dto.CmsGdsCategoryReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsGdsCategoryRespDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsInfoReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsInfoRespDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsPageInfoReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsPageInfoRespDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsPageTypeReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsPageTypeRespDTO;
import com.zengshi.ecp.cms.dubbo.interfaces.ICmsGdsCategoryRSV;
import com.zengshi.ecp.cms.dubbo.interfaces.ICmsInfoRSV;
import com.zengshi.ecp.cms.dubbo.interfaces.ICmsPageInfoRSV;
import com.zengshi.ecp.cms.dubbo.interfaces.ICmsPageTypeRSV;
import com.zengshi.ecp.cms.dubbo.util.CmsConstants;
import com.zengshi.ecp.goods.dubbo.constants.GdsConstants;
import com.zengshi.ecp.goods.dubbo.constants.GdsErrorConstants;
import com.zengshi.ecp.goods.dubbo.constants.GdsOption.GdsQueryOption;
import com.zengshi.ecp.goods.dubbo.constants.GdsOption.SkuQueryOption;
import com.zengshi.ecp.goods.dubbo.dto.GdsMediaRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfo.GdsInfoDetailRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfo.GdsInfoReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfo.GdsInfoRespDTO;
import com.zengshi.ecp.goods.dubbo.interfaces.IGdsInfoQueryRSV;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.staff.dubbo.dto.ShopInfoResDTO;
import com.zengshi.ecp.staff.dubbo.interfaces.IShopInfoRSV;
import com.zengshi.paas.utils.DateUtil;
import com.zengshi.paas.utils.ImageUtil;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.ObjectCopyUtil;
import com.zengshi.paas.utils.StringUtil;
import com.alibaba.dubbo.common.utils.CollectionUtils;
import com.alibaba.fastjson.JSONObject;

/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-web-manage <br>
 * Description: 模板化 链接工具 如果想在商城也可以用，则必须数据获取不依赖页面配置意外的Controller 如分类等<br>
 * Date:2016年3月28日下午3:59:40  <br>
 * Copyright (c) 2016 ZengShi All Rights Reserved <br>
 * 
 * @author zhanbh
 * @version  
 * @since JDK 1.6
 */
@Controller
@RequestMapping(value="/cmslinkinpututil")
public class CmsLinkInputUtilController extends EcpBaseController {
    
    private static String MODULE = CmsLinkInputUtilController.class.getName();
    
    private String URL = "/pageConfig/utils/linkInput/";//返回页面的基本路径 
    
    public static final String CATLOG_ID_PREFIX = "";
    
    @Resource(name="cmsInfoRSV")
    private ICmsInfoRSV cmsInfoRSV;
    @Resource(name="cmsPageInfoRSV")
    private ICmsPageInfoRSV cmsPageInfoRSV;
    @Resource(name = "cmsPageTypeRSV")
    private ICmsPageTypeRSV cmsPageTypeRSV;
    @Resource(name="gdsInfoQueryRSV")
    private IGdsInfoQueryRSV gdsInfoQueryRSV;
    @Resource(name="shopInfoRSV")
    private IShopInfoRSV shopInfoRSV;
    @Resource
    private ICmsGdsCategoryRSV iCmsGdsCategoryRSV;
    /**
     * 
     * init:(页面初始化). <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author zhanbh 
     * @return  
     * @throws Exception 
     * @since JDK 1.6
     * info,prom,good,catg
     */
    @RequestMapping()
    public String init(Model model,String types) throws Exception{
        List<JSONObject> typeJsons = null;
        if(StringUtil.isNotBlank(types)){
            String[] typeStrs = types.split(",");
            if(typeStrs!=null && typeStrs.length > 0){
                typeJsons = new ArrayList<JSONObject>();
                for(String type : typeStrs){
                    JSONObject typeJson = this.getTypeInfo(type);
                    if(typeJson != null){
                        typeJsons.add(typeJson);
                    }
                }
            }
            model.addAttribute("types", typeJsons);
        }
        return URL+"index";
    }
    
    /**
     * 
     * getTypeInfo:(将链接类型扩展未{code，name，zhName，title}). <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author zhanbh 
     * @param type  链接类型
     * @return 
     * @since JDK 1.6
     */
    private JSONObject getTypeInfo(String type){
        JSONObject json = null;
        if(StringUtil.isNotBlank(type)){
            json = new JSONObject();
            type = type.toLowerCase();
            switch(type){
            case "info":
                json.put("code", CmsConstants.LinkType.CMS_LINKTYPE_02);
                json.put("name", type);
                json.put("zhName", "公告");
                break;
            case "prom":
                json.put("code", CmsConstants.LinkType.CMS_LINKTYPE_03);
                json.put("name", type);
                json.put("zhName", "促销");
                break;
            case "good":
                json.put("code", CmsConstants.LinkType.CMS_LINKTYPE_01);
                json.put("name", type);
                json.put("zhName", "商品");
                break;
            case "catg":
                json.put("code", "04");
                json.put("name", type);
                json.put("zhName", "分类");
                break;
            case "homepage":
                json.put("code", "05");
                json.put("name", type);
                json.put("zhName", "首页");
                break;
            case "secondpage":
                json.put("code", "06");
                json.put("name", type);
                json.put("zhName", "二级页");
                break;
            case "sitenav":
                json.put("code", "99");
                json.put("name", type);
                json.put("zhName", "站内导航");
                break;
            }
        }
        
        return json;
    }
    /**
     * 
     * getLinkTypeVm:(获取对应链接类型的返回vm). <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author zhanbh 
     * @param linkName
     * @return 
     * @since JDK 1.6
     */
    private String getLinkTypeVm(String linkName){
        if(StringUtil.isBlank(linkName)){
            return null;
        }
        String vmPath = null;
        switch(linkName){
        case "info":
            vmPath = "open/info";
            break;
        case "good":
            vmPath = "open/good";
            break;
        case "catg":
            vmPath = "open/category-select";
            break;
        case "prom":
            vmPath = "open/prom";
            break;
        case "homepage":
            vmPath = "open/homepage";
            break;
        case "secondpage":
            vmPath = "open/secondpage";
            break;
        case "sitenav":
            vmPath = "open/sitenav";
            break;
        }
        return vmPath;
    }
    /**
     * getImageUrl:(根据上传到mongoDB的图片ID 从mongoDB上获取图片路径). <br/>
     * TODO(这里描述这个方法适用条件 – 可选).<br/>
     * TODO(这里描述这个方法的执行流程 – 可选).<br/>
     * TODO(这里描述这个方法的使用方法 – 可选).<br/>
     * TODO(这里描述这个方法的注意事项 – 可选).<br/>
     * 
     * @author jiangzh
     * @param vfsId
     *            图片ID
     * @param param
     *            图片规格
     * @return
     * @since JDK 1.6
     */
    private String getImageUrl(String vfsId, String param) {
        StringBuilder sb = new StringBuilder();
      //入参的图片ID为空，那么使用默认图片；
        if(StringUtil.isBlank(vfsId)){
            sb.append(ImageUtil.getDefaultImageId());
        } else {
            sb.append(vfsId);
        }
        if (StringUtil.isNotBlank(param)) {
            sb.append("_");
            sb.append(param);
        }
        return ImageUtil.getImageUrl(sb.toString());
    }
    /** 
     * gdsProp:(获取属性值). <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author jiangzh 
     * @param gdsInfoDetailRespDTO
     * @param propName
     * @return 
     * @since JDK 1.6 
     */ 
    private String gdsProp(GdsInfoDetailRespDTO gdsInfoDetailRespDTO,String propName){
        String gdsProp = "";
        if(gdsInfoDetailRespDTO != null 
                && gdsInfoDetailRespDTO.getSkuInfo() != null
                && gdsInfoDetailRespDTO.getSkuInfo().getAllPropMaps() != null
                && gdsInfoDetailRespDTO.getSkuInfo().getAllPropMaps().get(propName) != null
                && gdsInfoDetailRespDTO.getSkuInfo().getAllPropMaps().get(propName).getValues()!=null
                && gdsInfoDetailRespDTO.getSkuInfo().getAllPropMaps().get(propName).getValues().get(0)!=null){
            gdsProp = gdsInfoDetailRespDTO.getSkuInfo().getAllPropMaps().get(propName).getValues().get(0).getPropValue();
        } 
        return gdsProp;
    }
    //链接列表请求s------------------------------------------------//
    /** 选择信息弹出框
     * openinfo:(这里用一句话描述这个方法的作用). <br/> 
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
    @RequestMapping("/open")
    public String open(Model model,@ModelAttribute("reqVO") CmsLinkInputUtilVO reqVO) throws Exception{
        String path = this.getLinkTypeVm(reqVO.getTypeName());
        if(StringUtil.isBlank(path)){
            LogUtil.error(MODULE, "未配置对应链接类型返回的Vm页面路径！");
            throw new Exception("未配置对应链接类型返回的Vm页面路径！");
        }
        return URL + path;
    }
  //链接列表请求e------------------------------------------------//
  //链接列表数据请求s------------------------------------------------//
    /** 选择信息数据
     * infogrid:(这里用一句话描述这个方法的作用). <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author zhanbh 
     * @return 
     * @since JDK 1.6 
     */ 
    @SuppressWarnings("rawtypes")
    @RequestMapping("/infogrid")
    @ResponseBody
    public Model queryinfo(Model model, @ModelAttribute("searchVO") CmsLinkInfoVO searchVO) throws Exception{
        LogUtil.info(MODULE,"==========" + JSONObject.toJSONString(searchVO));
        //1. 调用后场服务所需要的DTO；
        CmsInfoReqDTO reqDTO = new CmsInfoReqDTO();
        ObjectCopyUtil.copyObjValue(searchVO, reqDTO, "MODULE", true);
        reqDTO.setStatus(searchVO.getStatus());
        reqDTO.setThisTime(DateUtil.getSysDate());
        
        //2. 调用后场服务，将后场返回的分页对象封装为前店所需要的分页对象；
        PageResponseDTO<CmsInfoRespDTO> pageInfo = cmsInfoRSV.queryCmsInfoPage(reqDTO);
        EcpBasePageRespVO<Map> respVO = EcpBasePageRespVO.buildByPageResponseDTO(pageInfo);
        return super.addPageToModel(model, respVO);
    }
    
    /** 选择页面数据
     * infogrid:(这里用一句话描述这个方法的作用). <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author zhanbh 
     * @return 
     * @since JDK 1.6 
     */ 
    @SuppressWarnings("rawtypes")
    @RequestMapping("/pagegrid")
    @ResponseBody
    public Model querypage(Model model, @ModelAttribute("searchVO") CmsLinkPageInfoVO searchVO) throws Exception{
        LogUtil.info(MODULE,"==========" + JSONObject.toJSONString(searchVO));
        //1. 调用后场服务所需要的DTO；
        CmsPageInfoReqDTO reqDTO = new CmsPageInfoReqDTO();
        ObjectCopyUtil.copyObjValue(searchVO, reqDTO, "MODULE", true);
        reqDTO.setStatus(CmsConstants.ParamStatus.CMS_PARAMSTATUS_1);
        //2. 调用后场服务，将后场返回的分页对象封装为前店所需要的分页对象；
        PageResponseDTO<CmsPageInfoRespDTO> pageInfo = cmsPageInfoRSV.queryCmsPageInfoPage(reqDTO);
        if(pageInfo!=null){
            this.extendPageInfoList(pageInfo.getResult());
        }
        EcpBasePageRespVO<Map> respVO = EcpBasePageRespVO.buildByPageResponseDTO(pageInfo);
        return super.addPageToModel(model, respVO);
    }
    
    /** 
     * goodgrid:(这里用一句话描述这个方法的作用). <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author zhanbh 
     * @param model
     * @return
     * @throws Exception 
     * @since JDK 1.6 
     */ 
    @SuppressWarnings("rawtypes")
    @RequestMapping("/goodgrid")
    public Model goodGrid(Model model, 
            @ModelAttribute("searchVO") CmsLinkManageVO searchVO,
            @RequestParam("siteId")String siteId) throws Exception{
        LogUtil.info(MODULE,"==========" + JSONObject.toJSONString(searchVO));
     // 1. 调用后场服务所需要的DTO；
        GdsInfoReqDTO reqDTO = searchVO.toBaseInfo(GdsInfoReqDTO.class);
        ObjectCopyUtil.copyObjValue(searchVO, reqDTO, "MODULE", true);
        reqDTO.setGdsStatus(GdsConstants.GdsInfo.GDS_STATUS_ONSHELVES);// 已上架
        if (StringUtil.isNotEmpty(searchVO.getCatgCode())) {
            reqDTO.setPlatCatgs(searchVO.getCatgCode());
        }
        // 如果是siteId则直接查找是否是积分商品
        if ("2".equalsIgnoreCase(siteId)) {
            reqDTO.setIfScoreGds("1");//积分
        } else {
            reqDTO.setIfScoreGds("0");//商城
        }
        //时间
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        if (StringUtil.isNotEmpty(searchVO.getBegUpdateTime())) {
            String beg = sdf1.format(searchVO.getBegUpdateTime());
            reqDTO.setBegUpdateTime(Timestamp.valueOf(beg));
        }
        if(StringUtil.isNotEmpty(searchVO.getEndUpdateTime())){
            String end = sdf1.format(searchVO.getEndUpdateTime());
            reqDTO.setEndUpdateTime(Timestamp.valueOf(end));
        }
        PageResponseDTO<CmsLinkGdsVO> pageInfo = null;
        List<GdsInfoRespDTO> respListTemp = null;
        PageResponseDTO<GdsInfoRespDTO> pageInfoTemp = gdsInfoQueryRSV.queryGdsInfoListPage(reqDTO);
        List<CmsLinkGdsVO> respList = new ArrayList<CmsLinkGdsVO>();
        if (pageInfoTemp != null) {
            respListTemp = pageInfoTemp.getResult();
            pageInfo = new PageResponseDTO<CmsLinkGdsVO>();
            pageInfo.setCount(pageInfoTemp.getCount());
            pageInfo.setPageCount(pageInfoTemp.getPageCount());
            pageInfo.setPageNo(pageInfoTemp.getPageNo());
            pageInfo.setPageSize(pageInfoTemp.getPageSize());
            pageInfo.setResult(respList);
        }
        if (!CollectionUtils.isEmpty(respListTemp)) {
            for (GdsInfoRespDTO gdsInfoRespDTO : respListTemp) {
                if(gdsInfoRespDTO != null){
                    CmsLinkGdsVO gdsVO = new CmsLinkGdsVO();
                    gdsVO.setId(gdsInfoRespDTO.getId());
                    gdsVO.setGdsName(gdsInfoRespDTO.getGdsName());
                    gdsVO.setGdsTypeName(gdsInfoRespDTO.getGdsTypeName());
                    gdsVO.setUrl(gdsInfoRespDTO.getUrl());
                    gdsVO.setUpdateTime(gdsInfoRespDTO.getUpdateTime());
                    // 调用店铺，返回店铺信息
                    if (StringUtil.isNotEmpty(gdsInfoRespDTO.getShopId())) {
                        ShopInfoResDTO shopInfoRespDTO = shopInfoRSV.findShopInfoByShopID(gdsInfoRespDTO.getShopId());
                        if (shopInfoRespDTO != null) {
                            gdsVO.setShopName(shopInfoRespDTO.getShopName());
                        }
                    }
                    // 调用商品属性
                    if (StringUtil.isNotEmpty(gdsInfoRespDTO.getId())) {
                        GdsInfoReqDTO gdsInfoReqDTO = new GdsInfoReqDTO();
                        gdsInfoReqDTO.setId(gdsInfoRespDTO.getId());
                        GdsQueryOption[] gdsOptions = new GdsQueryOption[] { 
                                GdsQueryOption.MAINPIC };
                        SkuQueryOption[] skuOptions = new SkuQueryOption[] {
                                SkuQueryOption.PROP };
                        gdsInfoReqDTO.setGdsQueryOptions(gdsOptions);
                        gdsInfoReqDTO.setSkuQuerys(skuOptions);
                        
                      //设置需要的单品属性Id
                        List<Long> propIds = new ArrayList<Long>();
                        propIds.add(1001l);//作者
                        propIds.add(1005l);//出版日期
                        propIds.add(1010l);//版次
                        propIds.add(1002l);//idbn
                        
                        gdsInfoReqDTO.setPropIds(propIds);
                        
                        GdsInfoDetailRespDTO gdsInfoDetailRespDTO = gdsInfoQueryRSV.queryGdsInfoDetail(gdsInfoReqDTO);
                        if (gdsInfoDetailRespDTO != null) {
                            // 商品图片
                            GdsMediaRespDTO gdsMediaRespDTO = gdsInfoDetailRespDTO.getMainPic();
                            if(gdsMediaRespDTO == null){
                                gdsMediaRespDTO = new GdsMediaRespDTO();
                            }
                            gdsVO.setImageUrl(getImageUrl(gdsMediaRespDTO.getMediaUuid(), "120x50!"));
                            // 作者
                            gdsVO.setProp1001(this.gdsProp(gdsInfoDetailRespDTO,"1001"));
                            // ISBN
                            gdsVO.setProp1002(this.gdsProp(gdsInfoDetailRespDTO,"1002"));
                            // 出版日期
                            gdsVO.setProp1005(this.gdsProp(gdsInfoDetailRespDTO,"1005"));
                            // 版次
                            gdsVO.setProp1010(this.gdsProp(gdsInfoDetailRespDTO,"1010"));
                        }
                    }
                    respList.add(gdsVO);
                }
            }
        }
        EcpBasePageRespVO<Map> respVO = EcpBasePageRespVO.buildByPageResponseDTO(pageInfo);
        // 2. 调用后场服务，将后场返回的分页对象封装为前店所需要的分页对象；
        return super.addPageToModel(model, respVO);
    }
    
    @RequestMapping({ "/asyncData/selector/getNodes" })
    @ResponseBody
    public String getSelectorNodes(Model model, CmsLinkGdsCategoryVO reqVO,
            HttpServletResponse response) throws Exception {
        LogUtil.info(MODULE, "获取分类树型节点,参数" + ToStringBuilder.reflectionToString(reqVO));
        List<CmsLinkGdsCategoryTreeVO> trees = new ArrayList<CmsLinkGdsCategoryTreeVO>();

        CmsGdsCategoryReqDTO reqDTO = new CmsGdsCategoryReqDTO();
        ObjectCopyUtil.copyObjValue(reqVO, reqDTO, null, true);
        // String id = reqVO.getCatgId();

        if ((StringUtil.isBlank(reqVO.getId())) && (reqVO.getShowRoot() == 0)) {
            List<CmsGdsCategoryRespDTO> catgList = null;
            CmsGdsCategoryReqDTO dto = new CmsGdsCategoryReqDTO();
            dto.setCatgName(reqVO.getCatgName());
            dto.setStatus(CmsConstants.ParamStatus.CMS_PARAMSTATUS_1);
            if(StringUtil.isNotBlank(reqVO.getSiteId())){
                dto.setSiteId(Long.valueOf(reqVO.getSiteId()));
            }else{
                dto.setSiteId(Long.valueOf(reqVO.getCurrentSiteId()));
            }
            catgList = iCmsGdsCategoryRSV.queryCmsCategoryInfo(dto);
            
            convert2TreeList(trees, catgList);
        } else {
            List<CmsGdsCategoryRespDTO> catgList = null;
            catgList = this.iCmsGdsCategoryRSV.queryCmsCategorySons(reqDTO);
            convert2TreeList(trees, catgList);
        }

        String json = JSONObject.toJSONString(trees);
        LogUtil.info(MODULE, String.format("返回JSON数据：%s", new Object[] { json }));
        return json;
    }
    @RequestMapping({ "/asyncData/selector/catgsearch" })
    @ResponseBody
    public String selectorCmsGdsCategorySearch(Model model, CmsLinkGdsCategoryVO reqVO)
            throws Exception {
        LogUtil.info(MODULE, "搜索树型节点,参数" + ToStringBuilder.reflectionToString(reqVO));
        paramCheck(new Object[] { reqVO.getCatgName() }, new String[] { "catgName" });
        CmsGdsCategoryReqDTO dto = new CmsGdsCategoryReqDTO();
        dto.setCatgName(reqVO.getCatgName());
        if(StringUtil.isNotBlank(reqVO.getSiteId())){
            dto.setSiteId(Long.valueOf(reqVO.getSiteId()));
        }else{
            dto.setSiteId(Long.valueOf(reqVO.getCurrentSiteId()));
        }
        List<CmsGdsCategoryRespDTO> page = iCmsGdsCategoryRSV.queryCmsCategoryInfo(dto);
        List<CmsLinkGdsCategoryTreeVO> trees = new ArrayList<CmsLinkGdsCategoryTreeVO>();
        if (page != null && page.size() > 0) {
            for (CmsGdsCategoryRespDTO cmsGdsCategoryRespDTO : page) {
                CmsLinkGdsCategoryTreeVO vo = new CmsLinkGdsCategoryTreeVO();
                vo.setId(cmsGdsCategoryRespDTO.getId());
                vo.setName(cmsGdsCategoryRespDTO.getCatgName());
                vo.setCatgCode(cmsGdsCategoryRespDTO.getCatgCode());
                vo.setIsRoot(false);
                vo.setCatgLevel(cmsGdsCategoryRespDTO.getCatgLevel());
                vo.setIsParent(false);
                trees.add(vo);
            }
        }
        String json = JSONObject.toJSONString(trees);
        LogUtil.info(MODULE, String.format("返回JSON数据：%s", json));
        return json;
    }
    /*
     * 
     * convert2TreeList:分类信息转换成分类树列表. <br/>
     * 
     * @author huangxm9
     * 
     * @param trees
     * 
     * @param catgLst
     * 
     * @since JDK 1.6
     */
    private void convert2TreeList(List<CmsLinkGdsCategoryTreeVO> trees,
            List<CmsGdsCategoryRespDTO> catgLst) {
        if (CollectionUtils.isNotEmpty(catgLst)) {
            for (CmsGdsCategoryRespDTO respDTO : catgLst) {
                CmsLinkGdsCategoryTreeVO vo = new CmsLinkGdsCategoryTreeVO();
                vo.setId(respDTO.getId());
                vo.setName(respDTO.getCatgName());
                vo.setCatgCode(respDTO.getCatgCode());
                vo.setIsRoot(false);
                if (StringUtil.isBlank(respDTO.getCatgParent())) {
                    vo.setpId(CATLOG_ID_PREFIX + respDTO.getId());
                } else {
                    vo.setpId(respDTO.getCatgParent());
                }
                // }
                vo.setCatgLevel(respDTO.getCatgLevel());
                vo.setIsParent(GdsConstants.GdsCategory.IF_LEAF_0.equals(respDTO.getIfLeaf()) ? true : false);
                trees.add(vo);
            }
        }
    }
    /**
     * 
     * 必传参数检测，仅对普通参数进行判空处理，抛出异常信息为:必传参数{0}缺失!<br/>
     * 会对嵌套对象，如果外围对外为空，一并传入判断那么该方法会报空。<br/>
     * 举例：当外围对象为reqDTO为空时如果按以下格式调用则会报空:
     * 其中两个参数的params与msgs的数组长度要一致。
     * 
     * @author liyong7
     * @param params
     * @param msgs
     * @since JDK 1.6
     */
    protected void paramCheck(Object[] params, String[] msgs) {
        if (null != params && null != msgs && params.length == msgs.length) {
               StringBuffer errorMsg = new StringBuffer();
               for(int i = 0; i < params.length; ++ i){
                   Object obj = params[i];
                   if(obj instanceof String){
                       if(StringUtil.isBlank((String)obj)){
                           errorMsg.append(msgs[i]);
                           errorMsg.append(",");
                       }
                   }else if(obj instanceof Object[]){
                       if(obj == null || ((Object[])obj).length == 0){
                           errorMsg.append(msgs[i]);
                           errorMsg.append(",");
                       }
                   }else if(obj instanceof Collection<?>){
                       if(obj == null || CollectionUtils.isEmpty((Collection<?>)obj)){
                           errorMsg.append(msgs[i]);
                           errorMsg.append(",");
                       }
                   }else{
                       if(null == obj){
                           errorMsg.append(msgs[i]);
                           errorMsg.append(",");
                       }
                   }
               }
               if(0 < errorMsg.length()){
                   errorMsg.deleteCharAt(errorMsg.length() - 1);
                   throw new BusinessException(GdsErrorConstants.Commons.ERROR_GOODS_200100,new String[]{errorMsg.toString()});
               }
        } else {
            LogUtil.error(MODULE, "参数检测方法执行异常！请保证params与msgs不为空，并且两个参数数组长度一致");
            throw new BusinessException(GdsErrorConstants.Commons.ERROR_GOODS_200099,
                    new String[] { Thread.currentThread().getStackTrace()[1].getMethodName() });
        }
    }
  //链接列表数据请求e------------------------------------------------//
    
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
        }
    }
    /**
     * 
     * queryPageType:(查询页面类型). <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author zhanbh 
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
}

