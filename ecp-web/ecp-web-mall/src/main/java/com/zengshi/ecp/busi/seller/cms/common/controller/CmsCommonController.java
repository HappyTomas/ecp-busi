package com.zengshi.ecp.busi.seller.cms.common.controller;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.regex.Pattern;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.zengshi.ecp.base.controller.EcpBaseController;
import com.zengshi.ecp.base.mvc.annotation.NativeJson;
import com.zengshi.ecp.base.vo.EcpBasePageRespVO;
import com.zengshi.ecp.base.vo.EcpBaseResponseVO;
import com.zengshi.ecp.busi.seller.cms.common.vo.CmsGdsVO;
import com.zengshi.ecp.busi.seller.cms.common.vo.CmsPromGdsVO;
import com.zengshi.ecp.busi.seller.coup.coupinfo.vo.QueryCouponInfoVO;
import com.zengshi.ecp.busi.seller.goods.vo.GdsManageVO;
import com.zengshi.ecp.cms.dubbo.dto.CmsPlaceReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsPlaceRespDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsSiteReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsSiteRespDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsTemplateReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsTemplateRespDTO;
import com.zengshi.ecp.cms.dubbo.interfaces.ICmsAdvertiseRSV;
import com.zengshi.ecp.cms.dubbo.interfaces.ICmsFloorRSV;
import com.zengshi.ecp.cms.dubbo.interfaces.ICmsInfoRSV;
import com.zengshi.ecp.cms.dubbo.interfaces.ICmsPlaceRSV;
import com.zengshi.ecp.cms.dubbo.interfaces.ICmsSiteRSV;
import com.zengshi.ecp.cms.dubbo.interfaces.ICmsTemplateRSV;
import com.zengshi.ecp.coupon.dubbo.dto.req.CoupInfoReqDTO;
import com.zengshi.ecp.coupon.dubbo.dto.resp.CoupInfoRespDTO;
import com.zengshi.ecp.coupon.dubbo.interfaces.ICoupRSV;
import com.zengshi.ecp.coupon.dubbo.util.CouponConstants;
import com.zengshi.ecp.goods.dubbo.constants.GdsConstants;
import com.zengshi.ecp.goods.dubbo.constants.GdsOption.GdsQueryOption;
import com.zengshi.ecp.goods.dubbo.constants.GdsOption.SkuQueryOption;
import com.zengshi.ecp.goods.dubbo.dto.GdsMediaRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfo.GdsInfoDetailRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfo.GdsInfoReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfo.GdsInfoRespDTO;
import com.zengshi.ecp.goods.dubbo.interfaces.IGdsInfoQueryRSV;
import com.zengshi.ecp.prom.dubbo.util.PromConstants;
import com.zengshi.ecp.search.dubbo.search.ExtraFieldQueryField;
import com.zengshi.ecp.search.dubbo.search.SearchFacade;
import com.zengshi.ecp.search.dubbo.search.SearchParam;
import com.zengshi.ecp.search.dubbo.search.result.SearchResult;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.server.front.util.BaseParamUtil;
import com.zengshi.ecp.staff.dubbo.dto.FileImportReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.ShopInfoResDTO;
import com.zengshi.ecp.staff.dubbo.interfaces.IShopInfoRSV;
import com.zengshi.ecp.staff.dubbo.util.StaffConstants;
import com.zengshi.ecp.system.util.ConstantTool;
import com.zengshi.ecp.system.util.ParamsTool;
import com.zengshi.paas.utils.FileUtil;
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
@RequestMapping(value="/seller/common")
public class CmsCommonController extends EcpBaseController {
    
    private static String MODULE = CmsCommonController.class.getName();
    
    @Resource(name="cmsAdvertiseRSV")
    private ICmsAdvertiseRSV cmsAdvertiseRSV;
    
    @Resource(name="cmsPlaceRSV")
    private ICmsPlaceRSV cmsPlaceRSV;
    
    @Resource(name="cmsSiteRSV")
    private ICmsSiteRSV cmsSiteRSV;
    
    @Resource(name="cmsTemplateRSV")
    private ICmsTemplateRSV cmsTemplateRSV;
    
    @Resource(name="cmsInfoRSV")
    private ICmsInfoRSV cmsInfoRSV;
    
    @Resource(name="gdsInfoQueryRSV")
    private IGdsInfoQueryRSV gdsInfoQueryRSV;
    
    @Resource(name="shopInfoRSV")
    private IShopInfoRSV shopInfoRSV;

    @Resource(name="cmsFloorRSV")
    private ICmsFloorRSV cmsFloorRSV;
    
    @Resource 
    private ICoupRSV coupRSV;
    
    /** 
     * uploadImage:(上传图片). <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author jiangzh 
     * @param model
     * @param file  
     * @param req
     * @param rep
     * @return
     * @throws Exception 
     * @since JDK 1.6 
     */ 
    @RequestMapping(value="/uploadImage")
    @ResponseBody
    @NativeJson(true)
    public String uploadImage(Model model,
            @RequestParam(value = "uploadFileObj", required = false) MultipartFile uploadFileObj,
            @RequestParam(value = "place_width", required = false) int place_width,
            @RequestParam(value = "place_height", required = false) int place_height,
            @RequestParam(value = "place_size", required = false) int place_size,
            @RequestParam(value = "standard", required = false) String standard,
            HttpServletRequest request, HttpServletResponse response) throws Exception{
    	
        JSONObject obj = new JSONObject();//返回结果
        Map<String, Object> resultMap = new HashMap<String, Object>();
        try {
            if (uploadFileObj == null) {
                obj.put("success", EcpBaseResponseVO.RESULT_FLAG_FAILURE);
                obj.put("message", "请选择上传文件！");
                LogUtil.info(MODULE, "请选择上传文件！");
                return obj.toJSONString();
            }
            String vfsName = uploadFileObj.getOriginalFilename();
            vfsName = vfsName.replace(" ", ""); 
            String extensionName = "." + ConstantTool.getExtensionName(vfsName);
    
            /** 支持文件拓展名：.jpg,.png,.jpeg,.gif,.bmp */
            boolean flag = Pattern.
                    compile("\\.(jpg)$|\\.(png)$|\\.(jpeg)$|\\.(gif)$|\\.(bmp)$")
                    .matcher(extensionName.toLowerCase()).find();
            if (!flag) {
                obj.put("success", EcpBaseResponseVO.RESULT_FLAG_FAILURE);
                obj.put("message", "请选择图片文件(.jpg,.png,.jpeg,.gif,.bmp)！");
                LogUtil.error(MODULE, "上传图片失败,原因---请选择图片文件(.jpg,.png,.jpeg,.gif,.bmp)!");
                return obj.toJSONString();
            }
            
            byte[] datas = ConstantTool.inputStream2Bytes(uploadFileObj.getInputStream());
            if(datas.length>place_size*1024){
                obj.put("success", EcpBaseResponseVO.RESULT_FLAG_FAILURE);
                obj.put("message", "图片上传失败，上传的图片必须小于"+place_size+"  KB!");
                LogUtil.error(MODULE, "图片上传失败，上传的图片必须小于!");
                return obj.toJSONString();
            }
            //判断图片的长宽（像素）
            BufferedImage bi = ImageIO.read(uploadFileObj.getInputStream());;   
            int width = bi.getWidth(); 
            int height = bi.getHeight();
            if(place_width<width || place_height<height){
            	obj.put("success", EcpBaseResponseVO.RESULT_FLAG_FAILURE);
                obj.put("message", "图片上传失败，上传的图片尺寸必须小于"+"  "+place_width+"*"+place_height+"px！");
                LogUtil.error(MODULE, "图片上传失败，上传的图片必须小于!");
                return obj.toJSONString();
            }
//            String vfsId = ImageUtil.upLoadImage(datas, vfsName);
            String vfsId ;
            if(extensionName.equalsIgnoreCase(".png")){
            	vfsId = ImageUtil.saveToRomte(datas, "image", "png");
            }
            else{
            	vfsId = ImageUtil.upLoadImage(datas, "image");
            }
            //resultMap.put("id", id);
            if(StringUtil.isBlank(standard)){
                standard = "150x150!";
            }
            resultMap.put("vfsId", vfsId);
            resultMap.put("vfsName", vfsName);
            resultMap.put("vfsUrlPri", ConstantTool.getImageUrl(vfsId,""));
            resultMap.put("vfsUrl", ConstantTool.getImageUrl(vfsId,standard));
            obj.put("success", EcpBaseResponseVO.RESULT_FLAG_SUCCESS);
            obj.put("message", "图片上传成功!");
            obj.put("resultMap", resultMap);
        } catch (Exception e) {
            LogUtil.info(MODULE,"图片上传失败,原因---"+e.getMessage(), e);
            obj.put("success", EcpBaseResponseVO.RESULT_FLAG_FAILURE);
            obj.put("message", "图片上传失败，图片服务器异常，请联系管理员!");
        }
        return obj.toJSONString();
    }
    
    /** 
     * uploadFile:(上传附件). <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author jiangzh 
     * @param model
     * @param uploadFileObj
     * @param req
     * @param rep
     * @return
     * @throws Exception 
     * @since JDK 1.6 
     */ 
    @RequestMapping(value = "/uploadFile")
    @ResponseBody
    @NativeJson(true)
    public String uploadFile(Model model,@RequestParam(value = "uploadFileObj", required = false) MultipartFile uploadFileObj,
            HttpServletRequest req,HttpServletResponse rep) throws Exception {
        JSONObject obj = new JSONObject();// 返回结果
        Map<String, Object> resultMap = new HashMap<String, Object>();
        try {
            if (uploadFileObj == null) {
                obj.put("success", EcpBaseResponseVO.RESULT_FLAG_FAILURE);
                obj.put("message", "请选择上传文件！");
                LogUtil.info(MODULE, "请选择上传文件！");
                return obj.toJSONString();
            }
            String vfsName = uploadFileObj.getOriginalFilename();// 文件名
            String extensionName = "." + ConstantTool.getExtensionName(vfsName);// 文件后缀
            /** 支持文件拓展名：.doc,.pdf,.zip,.rar */
            boolean flag = Pattern
                    .compile("\\.(doc)$|\\.(docx)$|\\.(pdf)$|\\.(zip)$|\\.(rar)$")
                    .matcher(extensionName.toLowerCase()).find();
            if (!flag) {
                obj.put("success", EcpBaseResponseVO.RESULT_FLAG_FAILURE);
                obj.put("message", "请选择图片文件(.doc,.docx,.pdf,.zip,.rar)！");
                LogUtil.error(MODULE,"上传图片失败,原因---请选择图片文件(.doc,.docx,.pdf,.zip,.rar)!");
                return obj.toJSONString();
            }
            byte[] datas = uploadFileObj.getBytes();
            if (datas.length > 5 * 1024 * 1024) {
                obj.put("success", EcpBaseResponseVO.RESULT_FLAG_FAILURE);
                obj.put("message", "附件上传失败，上传的图片必须小于5M!");
                LogUtil.error(MODULE, "附件上传失败，上传的图片必须小于5M!");
                return obj.toJSONString();
            }

            String vfsId = FileUtil.saveFile(datas, vfsName,uploadFileObj.getContentType());
            resultMap.put("vfsId", vfsId);
            resultMap.put("vfsName", vfsName);
            resultMap.put("vfsUrl", ImageUtil.getStaticDocUrl(vfsId, "doc"));
            obj.put("success", EcpBaseResponseVO.RESULT_FLAG_SUCCESS);
            obj.put("message", "附件上传成功!");
            obj.put("resultMap", resultMap);
        } catch (Exception e) {
            LogUtil.info(MODULE, "附件上传失败,原因---" + e.getMessage(), e);
            obj.put("success", EcpBaseResponseVO.RESULT_FLAG_FAILURE);
            obj.put("message", "附件上传失败，图片服务器异常，请联系管理员!");
        }
        return obj.toJSONString();
    }
    
    
    
    /**
     * 
     * importData:(上传附件). <br/> 
     * 
     * @author huangxm9 
     * @param excel
     * @param model
     * @param request
     * @param response
     * @return 
     * @since JDK 1.6
     */
    @RequestMapping(value="/importdata")
    @ResponseBody
    public JSONObject importData(@RequestParam(value = "excelFile", required = true) MultipartFile excel,
            Model model,HttpServletRequest request, HttpServletResponse response){
    	if(excel==null){
            LogUtil.info(MODULE, "附件不存在");
            throw new BusinessException(StaffConstants.STAFF_NULL_ERROR, new String[]{ "文件不存在" });
        }
        Long maxSize = 5*1024*1024L;
        if(excel.getSize()>maxSize)
        {
        	LogUtil.info(MODULE, "附件大小不能超过5M");
            throw new BusinessException("附件大小不能超过5M", new String[]{ "附件大小不能超过5M" });
        }
        FileImportReqDTO aiReqDto = new FileImportReqDTO();
        String fileId = "";
        String oriFileName = excel.getOriginalFilename();
        String[] fileNamea = oriFileName.split("\\.");
        String fileName =    fileNamea[0]+"_"+UUID.randomUUID();
        String fileExtName = fileNamea[1];
        try {
            fileId = FileUtil.saveFile(excel.getBytes(), fileName, fileExtName);
        } catch (IOException e) {
            LogUtil.error(MODULE, "文件保存失败",e);
            throw new BusinessException("cms.common.param.null.erro", new String[]{ "文件保存失败" });
        }
        aiReqDto.setFileId(fileId);
        aiReqDto.setFileName(fileName);
        
//        EcpBaseResponseVO ebResVO = new EcpBaseResponseVO();
//        
//        try {
////            acctManageRSV.importAcctInfoCapitalIncrementAboutShop(aiReqDto);
//            ebResVO.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_SUCCESS);
//        } catch (BusinessException e) {
//            ebResVO.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_FAILURE);
//            ebResVO.setResultMsg(e.getMessage());
//            LogUtil.error(MODULE, e.getErrorMessage(), e);
//        }
        JSONObject obj = new JSONObject();
        obj.put("fileId", fileId);
        obj.put("fileName", oriFileName);
        
        return obj;
    }
    
    /** 
     * querygds:(公共方法：弹出框选择商品). <br/> 
     * TODO(1、广告选择商品).<br/> 
     * TODO(2、楼层选择商品).<br/> 
     * TODO(3、推荐选择商品).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author jiangzh 
     * @param model
     * @param searchVO
     * @param siteId
     * @return
     * @throws Exception 
     * @since JDK 1.6 
     */ 
    @RequestMapping("/querygds")
    public String querygds(Model model, 
            @ModelAttribute("searchVO") GdsManageVO searchVO,
            @RequestParam(value = "siteId", required = false) String siteId) throws Exception {
        LogUtil.info(MODULE, "==========siteId=" + siteId + JSONObject.toJSONString(searchVO));
        
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
        PageResponseDTO<CmsGdsVO> pageInfo = null;
        List<GdsInfoRespDTO> respListTemp = null;
        PageResponseDTO<GdsInfoRespDTO> pageInfoTemp = gdsInfoQueryRSV.queryGdsInfoListPage(reqDTO);
        List<CmsGdsVO> respList = new ArrayList<CmsGdsVO>();
        if (pageInfoTemp != null) {
            respListTemp = pageInfoTemp.getResult();
            pageInfo = new PageResponseDTO<CmsGdsVO>();
            pageInfo.setCount(pageInfoTemp.getCount());
            pageInfo.setPageCount(pageInfoTemp.getPageCount());
            pageInfo.setPageNo(pageInfoTemp.getPageNo());
            pageInfo.setPageSize(pageInfoTemp.getPageSize());
            pageInfo.setResult(respList);
        }
        if (!CollectionUtils.isEmpty(respListTemp)) {
            for (GdsInfoRespDTO gdsInfoRespDTO : respListTemp) {
                if(gdsInfoRespDTO != null){
                    CmsGdsVO gdsVO = new CmsGdsVO();
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
                            gdsVO.setImageUrl(ParamsTool.getImageUrl(gdsMediaRespDTO.getMediaUuid(), "120x50!"));
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
        super.addPageToModel(model, respVO);
        
        return "/seller/cms/leaflet/models/leaflet-gds-list";
    }
    
    /** 
     * querypromgds:(公共方法：弹出框选择促销商品). <br/> 
     * TODO(1、广告选择商品).<br/> 
     * TODO(2、楼层选择商品).<br/> 
     * TODO(3、推荐选择商品).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author jiangzh 
     * @param model
     * @param searchVO
     * @param siteId
     * @return
     * @throws Exception 
     * @since JDK 1.6 
     */ 
    @RequestMapping("/querypromgds")
    @ResponseBody
    public Model querypromgds(Model model, 
            @ModelAttribute("searchVO") CmsPromGdsVO searchVO) throws Exception {
        LogUtil.info(MODULE, "==========" +  JSONObject.toJSONString(searchVO));
        
        SearchParam searchParam = new SearchParam();
        searchParam.setPageNo(searchVO.getPageNumber());
        searchParam.setPageSize(searchVO.getPageSize());
        searchParam.setIfFuzzyQuery(true);
        searchParam.setCollectionName("promcollection");
        List<ExtraFieldQueryField> extraANDFieldQueryList=new ArrayList<ExtraFieldQueryField>();
        
        //设置查询字段
        if(StringUtil.isNotEmpty(searchVO.getGdsId())){
            ExtraFieldQueryField extraFieldQueryField=new ExtraFieldQueryField();
            extraFieldQueryField.setName("gdsId");
            extraFieldQueryField.setValue(searchVO.getGdsId().toString());
            extraANDFieldQueryList.add(extraFieldQueryField);
        }
        if(StringUtil.isNotEmpty(searchVO.getSiteId())){
            ExtraFieldQueryField extraFieldQueryField=new ExtraFieldQueryField();
            extraFieldQueryField.setName("siteId");
            extraFieldQueryField.setValue(searchVO.getSiteId().toString());
            extraANDFieldQueryList.add(extraFieldQueryField);
        }
        if(StringUtil.isNotBlank(searchVO.getIsbn())){
            ExtraFieldQueryField extraFieldQueryField=new ExtraFieldQueryField();
            extraFieldQueryField.setName("isbn");
            extraFieldQueryField.setValue(SearchFacade.escapeQueryChars(searchVO.getIsbn()));
            extraANDFieldQueryList.add(extraFieldQueryField);
        }
        if(StringUtil.isNotBlank(searchVO.getCatgCode())){
            ExtraFieldQueryField extraFieldQueryField=new ExtraFieldQueryField();
            extraFieldQueryField.setName("catgList");
            extraFieldQueryField.setValue(searchVO.getCatgCode());
            extraANDFieldQueryList.add(extraFieldQueryField);
        }
        if(StringUtil.isNotEmpty(searchVO.getShopId())){
            ExtraFieldQueryField extraFieldQueryField=new ExtraFieldQueryField();
            extraFieldQueryField.setName("shopId");
            extraFieldQueryField.setValue(searchVO.getShopId().toString());
            extraANDFieldQueryList.add(extraFieldQueryField);
        }
        if(StringUtil.isNotBlank(searchVO.getPromTypeCode())){
            ExtraFieldQueryField extraFieldQueryField=new ExtraFieldQueryField();
            extraFieldQueryField.setName("promTypeCode");
            extraFieldQueryField.setValue(searchVO.getPromTypeCode());
            extraANDFieldQueryList.add(extraFieldQueryField);
        }
        if(StringUtil.isNotBlank(searchVO.getStatus())){
            ExtraFieldQueryField extraFieldQueryField=new ExtraFieldQueryField();
            extraFieldQueryField.setName("status");
            extraFieldQueryField.setValue(searchVO.getStatus());
            extraANDFieldQueryList.add(extraFieldQueryField);
        }
        if(StringUtil.isNotBlank(searchVO.getPromTheme())){
            ExtraFieldQueryField extraFieldQueryField=new ExtraFieldQueryField();
            extraFieldQueryField.setName("promTheme");
            extraFieldQueryField.setValue(SearchFacade.escapeQueryChars(searchVO.getPromTheme()));
            extraANDFieldQueryList.add(extraFieldQueryField);
        }
        if(StringUtil.isNotBlank(searchVO.getGdsName())){
            ExtraFieldQueryField extraFieldQueryField=new ExtraFieldQueryField();
            extraFieldQueryField.setName("gdsName");
            extraFieldQueryField.setValue(SearchFacade.escapeQueryChars(searchVO.getGdsName()));
            extraANDFieldQueryList.add(extraFieldQueryField);
        }
        searchParam.setExtraANDFieldQueryList(extraANDFieldQueryList);
        
        SearchResult<Map<String, Object>> result =null;
        try {
            result =SearchFacade.search(searchParam,null,null,null);
        } catch (Exception e) {
            LogUtil.error(MODULE, "调用搜索接口出错");
            e.printStackTrace();
        }
        
        List<Map<String, Object>> resultList =null;
        if(result!=null){
            resultList= result.getResultList();
        }
        
        if(CollectionUtils.isNotEmpty(resultList)){
            for(Map<String, Object> record : resultList){
                try {
                  //获取促销类型名称
                    if(StringUtil.isNotBlank((String)(record.get("promTypeCode")))){
                          record.put("promType", BaseParamUtil.fetchParamValue(PromConstants.PromKey.PROM_TYPE, (String)(record.get("promTypeCode"))));
                    }
                    // 调用店铺，返回店铺信息
                    if (StringUtil.isNotEmpty((Long)record.get("shopId"))) {
                        ShopInfoResDTO shopInfoRespDTO = shopInfoRSV.findShopInfoByShopID((Long)record.get("shopId"));
                        if (shopInfoRespDTO != null) {
                            record.put("shopName",shopInfoRespDTO.getShopName());
                        }
                    }
                    // 获取商品主图
                    if (StringUtil.isNotEmpty((Long)record.get("gdsId"))) {
                        GdsInfoReqDTO gdsInfoReqDTO = new GdsInfoReqDTO();
                        gdsInfoReqDTO.setId((Long)record.get("gdsId"));
                        GdsQueryOption[] gdsOptions = new GdsQueryOption[] { 
                                GdsQueryOption.MAINPIC };
                        gdsInfoReqDTO.setGdsQueryOptions(gdsOptions);
                        
                        GdsInfoRespDTO gdsInfoRespDTO = gdsInfoQueryRSV.queryGdsInfoByOption(gdsInfoReqDTO);
                        if (gdsInfoRespDTO != null) {
                            // 商品图片
                            GdsMediaRespDTO gdsMediaRespDTO = gdsInfoRespDTO.getMainPic();
                            if(gdsMediaRespDTO == null){
                                gdsMediaRespDTO = new GdsMediaRespDTO();
                            }
                            record.put("mainPic",ParamsTool.getImageUrl(gdsMediaRespDTO.getMediaUuid(), "50x50!"));
                        }
                    }
                } catch (Exception e) {
                    LogUtil.error(MODULE, "querypromgds   转化数据出错");
                    e.printStackTrace();
                }
            }
               
        }
        
        // 2. 调用后场服务，将后场返回的分页对象封装为前店所需要的分页对象；
        EcpBasePageRespVO<Map<String,Object>> respVO = new EcpBasePageRespVO<Map<String,Object>>();
        respVO.setList(result.getResultList());
        respVO.setTotalPage(result.getTotallyPage());
        respVO.setTotalRow(result.getNumFound());
        respVO.setPageNumber(searchVO.getPageNumber());
        respVO.setPageSize(searchVO.getPageSize());;
        return super.addPageToModel(model, respVO);
    }
    
    /** 
     * querycoupon:(公共方法：弹出框选择优惠券). <br/> 
     * TODO(1、广告选择商品).<br/> 
     * TODO(2、楼层选择商品).<br/> 
     * TODO(3、推荐选择商品).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author jiangzh 
     * @param model
     * @param searchVO
     * @param siteId
     * @return
     * @throws Exception 
     * @since JDK 1.6 
     */ 
    @SuppressWarnings("rawtypes")
    @RequestMapping("/querycoupon")
    public Model querycoupon(Model model, 
            @ModelAttribute("searchVO") QueryCouponInfoVO searchVO,
            @RequestParam(value = "siteId", required = false) String siteId) throws Exception {
        LogUtil.info(MODULE, "==========siteId=" + siteId + JSONObject.toJSONString(searchVO));
        
        // 1. 调用后场服务所需要的DTO；
        CoupInfoReqDTO queryDTO = searchVO.toBaseInfo(CoupInfoReqDTO.class);
        
        // 组织参数
        ObjectCopyUtil.copyObjValue(searchVO, queryDTO, "", false);
        queryDTO.setStatus(CouponConstants.CoupInfo.STATUS_1);
        
        PageResponseDTO<CoupInfoRespDTO> pageInfo= coupRSV.queryCoupInfoPage(queryDTO);

        EcpBasePageRespVO<Map> respVO = EcpBasePageRespVO.buildByPageResponseDTO(pageInfo);
        // 2. 调用后场服务，将后场返回的分页对象封装为前店所需要的分页对象；
        return super.addPageToModel(model, respVO);
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
    
    /** 
     * initSite:(获取内容位置列表). <br/> 
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
    @RequestMapping(value="/initSite")
    @ResponseBody
    private List<CmsSiteRespDTO> initSite(Model model, @RequestParam(value="status",required=false)String status) throws Exception{
        CmsSiteReqDTO cmsSiteReqDTO = new CmsSiteReqDTO();
        if(StringUtil.isNotBlank(status)){
        	cmsSiteReqDTO.setStatus(status);
        }
        List<CmsSiteRespDTO> cmsSiteRespDTOList = cmsSiteRSV.queryCmsSiteList(cmsSiteReqDTO);
        return cmsSiteRespDTOList;
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
    public List<CmsTemplateRespDTO> changeSite(Model model,
            @RequestParam("siteId")String siteId,
            @RequestParam(value="templateClass",required=false)String templateClass,
            @RequestParam(value="status",required=false)String status) throws Exception{
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
     * @author jiangzh 
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
    public List<CmsPlaceRespDTO> changeTemplate(Model model,
            @RequestParam("templateId")String templateId,
            @RequestParam(value="placeType",required=false)String placeType,
            @RequestParam(value="status",required=false)String status) throws Exception{
        LogUtil.info(MODULE,"==========templateId:"+templateId+";");
        return this.queryPlaceList((StringUtils.isNotBlank(templateId))?Long.valueOf(templateId):null,placeType,status);
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

}


