package com.zengshi.ecp.busi.seller.cms.leaflet.controller;

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
import com.zengshi.ecp.busi.main.vo.CmsInfoVO;
import com.zengshi.ecp.busi.seller.cms.leaflet.vo.CmsAdvertiseVO;
import com.zengshi.ecp.busi.seller.goods.vo.GdsManageVO;
import com.zengshi.ecp.cms.dubbo.dto.CmsAdvertiseReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsAdvertiseRespDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsInfoReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsInfoRespDTO;
import com.zengshi.ecp.cms.dubbo.interfaces.ICmsAdvertiseRSV;
import com.zengshi.ecp.cms.dubbo.interfaces.ICmsInfoRSV;
import com.zengshi.ecp.cms.dubbo.interfaces.ICmsPlaceRSV;
import com.zengshi.ecp.cms.dubbo.interfaces.ICmsSiteRSV;
import com.zengshi.ecp.cms.dubbo.interfaces.ICmsTemplateRSV;
import com.zengshi.ecp.cms.dubbo.util.CmsConstants;
import com.zengshi.ecp.goods.dubbo.constants.GdsConstants;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfo.GdsInfoReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfo.GdsInfoRespDTO;
import com.zengshi.ecp.goods.dubbo.interfaces.IGdsInfoQueryRSV;
import com.zengshi.ecp.server.front.dto.BaseInfo;
import com.zengshi.ecp.server.front.dto.BaseSysCfgRespDTO;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.server.front.util.SysCfgUtil;
import com.zengshi.ecp.staff.dubbo.dto.SellerResDTO;
import com.zengshi.ecp.staff.dubbo.dto.ShopInfoResDTO;
import com.zengshi.ecp.staff.dubbo.interfaces.IShopInfoRSV;
import com.zengshi.ecp.system.filter.SellerLocaleUtil;
import com.zengshi.ecp.system.util.ConstantTool;
import com.zengshi.paas.utils.DateUtil;
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
@RequestMapping(value="/seller/leaflet")
public class CmsAdvertiseController extends EcpBaseController {
    
    private static String MODULE = CmsAdvertiseController.class.getName();
    
    private static String PROM_NET_ADDRESS = "PROM_NET_ADDRESS";//促销页面主题KEY
    
    private String URL = "/seller/cms/leaflet/leaflet";//返回页面的基本路径 
    private String URL_OPEN = "/seller/cms/leaflet/models/leaflet";//返回页面的弹出窗路径
    
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

    /** 
     * init:页面初始化，requestMapping如果不写的话，访问地址同：Controller类的 requestmapping的URL
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
    @RequestMapping()
    public String init(Model model,@ModelAttribute("searchParams") String searchParams) throws Exception{
        return grid(model,searchParams);
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
    @SuppressWarnings("rawtypes")
    @RequestMapping(value="/grid")
    public String grid(Model model,@ModelAttribute("searchParams") String searchParams) throws Exception{
        BaseInfo staff = new BaseInfo(); 
        model.addAttribute("siteId", staff.getCurrentSiteId());
        return URL+"-grid";
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
            @ModelAttribute("searchParams") String searchParams)
            throws Exception {
        
        CmsAdvertiseRespDTO cmsAdvertiseRespDTO = new CmsAdvertiseRespDTO();
        cmsAdvertiseRespDTO.setPubTime(DateUtil.getSysDate());
        model.addAttribute("respVO", cmsAdvertiseRespDTO);
        
        return URL + "-edit";
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
           throw new BusinessException("cms.common.param.null.error",new String[] {"id"}); 
        }
        CmsAdvertiseReqDTO reqDTO = new CmsAdvertiseReqDTO();
        reqDTO.setId(Long.valueOf(id));
        try {
            CmsAdvertiseRespDTO cmsAdvertiseRespDTO = cmsAdvertiseRSV.queryCmsAdvertise(reqDTO);
            String vfsUrl = ConstantTool.getImageUrl(cmsAdvertiseRespDTO.getVfsId(),"290x220!");
            cmsAdvertiseRespDTO.setVfsUrl(vfsUrl);
            model.addAttribute("respVO",cmsAdvertiseRespDTO);
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
           throw new BusinessException("cms.common.param.null.error",new String[] {"id"}); 
        }
        CmsAdvertiseReqDTO reqDTO = new CmsAdvertiseReqDTO();
        reqDTO.setId(Long.valueOf(id));
        try {
            CmsAdvertiseRespDTO cmsAdvertiseRespDTO = cmsAdvertiseRSV.queryCmsAdvertise(reqDTO);
            String vfsUrl = ConstantTool.getImageUrl(cmsAdvertiseRespDTO.getVfsId(),"290x220!");
            cmsAdvertiseRespDTO.setVfsUrl(vfsUrl);
            //3.2调用店铺，返回店铺信息
            if(cmsAdvertiseRespDTO.getShopId() != null){
                ShopInfoResDTO shopInfoRespDTO = shopInfoRSV.findShopInfoByShopID(cmsAdvertiseRespDTO.getShopId());
                if(shopInfoRespDTO != null){
                	cmsAdvertiseRespDTO.setShopName(shopInfoRespDTO.getShopName());
                } 
            }
            model.addAttribute("respVO",cmsAdvertiseRespDTO);
        } catch (BusinessException e) {
            // TODO: handle exception
            throw new BusinessException(e.getErrorCode());
        }
       
        return URL+"-view";
    }
    
    
    /** 
     * changestatus:(发布\生效、失效). <br/> 
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
        
        if("1".equalsIgnoreCase(status)){
            status = CmsConstants.ParamStatus.CMS_PARAMSTATUS_1;
        }else if("0".equalsIgnoreCase(status)){
            status = CmsConstants.ParamStatus.CMS_PARAMSTATUS_0;
        }else if("2".equalsIgnoreCase(status)){
            status = CmsConstants.ParamStatus.CMS_PARAMSTATUS_2;
        }
        
        EcpBaseResponseVO vo = new EcpBaseResponseVO();
        try {
            cmsAdvertiseRSV.changeStatusCmsAdvertiseBatch(list, status);
            vo.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_SUCCESS);
        } catch (Exception e) {
            vo.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_EXCEPTION);
            LogUtil.error(MODULE, "更新广告状态失败出现异常！", e);
        }
        return vo;
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
    public EcpBaseResponseVO save(@Valid CmsAdvertiseVO VO) throws Exception{
        LogUtil.info(MODULE,"==========" + JSONObject.toJSONString(VO));
        CmsAdvertiseReqDTO reqDTO = new CmsAdvertiseReqDTO();
        ObjectCopyUtil.copyObjValue(VO, reqDTO, null, true);
        
        if("1".equalsIgnoreCase(VO.getStatus())){
            reqDTO.setStatus(CmsConstants.ParamStatus.CMS_PARAMSTATUS_1);//发布
        }else{
            reqDTO.setStatus(CmsConstants.ParamStatus.CMS_PARAMSTATUS_0);//未发布
        }
        
        if("09".equals(VO.getLinkType())){//其它
            reqDTO.setLinkUrl(VO.getLinkName());
        }else if("03".equals(VO.getLinkType())){//促销
            BaseSysCfgRespDTO sysDTO = SysCfgUtil.fetchSysCfg(PROM_NET_ADDRESS);
            if(sysDTO != null){
                reqDTO.setLinkUrl(sysDTO.getParaValue());
            }
        }
        
        EcpBaseResponseVO respVO = new EcpBaseResponseVO();
        if(VO.getId() != null){
            cmsAdvertiseRSV.updateCmsAdvertise(reqDTO);
        }else{
            cmsAdvertiseRSV.addCmsAdvertise(reqDTO);
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
     * @param searchVO
     * @return
     * @throws Exception 
     * @since JDK 1.6 
     */ 
    @RequestMapping("/gridlist")
    public String gridList(Model model, @ModelAttribute("searchVO") CmsAdvertiseVO searchVO) throws Exception{
        LogUtil.info(MODULE,"==========" + JSONObject.toJSONString(searchVO));
        //1. 调用后场服务所需要的DTO；
        CmsAdvertiseReqDTO reqDTO = searchVO.toBaseInfo(CmsAdvertiseReqDTO.class);
        ObjectCopyUtil.copyObjValue(searchVO, reqDTO, "MODULE", true);
        
        //2. 调用后场服务，将后场返回的分页对象封装为前店所需要的分页对象；
        PageResponseDTO<CmsAdvertiseRespDTO> pageInfo = cmsAdvertiseRSV.queryCmsAdvertisePage(reqDTO);
        
        //3. 调文件服务器，返回图片，调用店铺，返回店铺信息
        List<CmsAdvertiseRespDTO> respList = pageInfo.getResult();
        if(CollectionUtils.isNotEmpty(respList)){
            for(CmsAdvertiseRespDTO dto:respList){
                //3.1调文件服务器，返回图片
                if(StringUtils.isNotBlank(dto.getVfsId())){
                    dto.setVfsUrl(ImageUtil.getImageUrl(dto.getVfsId()+"_"+"120x50!"));
                }
                //3.2调用店铺，返回店铺信息
                if(dto.getShopId() != null){
                    ShopInfoResDTO shopInfoRespDTO = shopInfoRSV.findShopInfoByShopID(dto.getShopId());
                    if(shopInfoRespDTO != null){
                        dto.setShopName(shopInfoRespDTO.getShopName());
                    } 
                }
            }
        }
        
        EcpBasePageRespVO<Map> respVO = EcpBasePageRespVO.buildByPageResponseDTO(pageInfo);
        super.addPageToModel(model, respVO);
        
        return URL_OPEN+"-list";
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
    public String opengds(Model model, 
            @ModelAttribute("searchVO") GdsManageVO searchVO,
            @RequestParam("siteId")String siteId){
    	model.addAttribute("siteId", siteId);
        return URL_OPEN + "-gds";
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
    @RequestMapping("/openinfo")
    public String openinfo(){
        return URL_OPEN + "-info";
    } 
    
    /** 
     * queryinfo:(这里用一句话描述这个方法的作用). <br/> 
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
    @RequestMapping("/queryinfo")
    public String queryinfo(Model model, @ModelAttribute("searchVO") CmsInfoVO searchVO) throws Exception{
        LogUtil.info(MODULE,"==========" + JSONObject.toJSONString(searchVO));
        //1. 调用后场服务所需要的DTO；
        CmsInfoReqDTO reqDTO = new CmsInfoReqDTO();
        ObjectCopyUtil.copyObjValue(searchVO, reqDTO, "MODULE", true);
        reqDTO.setStatus(CmsConstants.ParamStatus.CMS_PARAMSTATUS_1);
        reqDTO.setThisTime(DateUtil.getSysDate());
        
        //2. 调用后场服务，将后场返回的分页对象封装为前店所需要的分页对象；
        PageResponseDTO<CmsInfoRespDTO> pageInfo = cmsInfoRSV.queryCmsInfoPage(reqDTO);
        EcpBasePageRespVO<Map> respVO = EcpBasePageRespVO.buildByPageResponseDTO(pageInfo);
        super.addPageToModel(model, respVO);
        return URL_OPEN+"-info-list";
    }
    
    /**
     * 
     * validShop:(验证店铺是否合法). <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author zhanbh
     * @param shopId
     * @return 
     * @since JDK 1.6
     */
    private boolean validShop(Long shopId){
        if(StringUtil.isNotEmpty(shopId)){
            SellerResDTO sellerResDTO = SellerLocaleUtil.getSeller();
            if(sellerResDTO!=null && CollectionUtils.isNotEmpty(sellerResDTO.getShoplist())){
                for(ShopInfoResDTO dto : sellerResDTO.getShoplist()){
                    if(shopId == dto.getId()){
                        return true;
                    }
                }
            }
        }
        return false;
    }

}


