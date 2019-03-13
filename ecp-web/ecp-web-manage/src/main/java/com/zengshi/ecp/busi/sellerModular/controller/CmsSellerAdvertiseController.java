package com.zengshi.ecp.busi.sellerModular.controller;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zengshi.ecp.base.controller.EcpBaseController;
import com.zengshi.ecp.base.vo.EcpBasePageRespVO;
import com.zengshi.ecp.base.vo.EcpBaseResponseVO;
import com.zengshi.ecp.busi.cms.advertise.vo.CmsAdvertiseVO;
import com.zengshi.ecp.busi.cms.info.vo.CmsInfoVO;
import com.zengshi.ecp.busi.demo.vo.DemoCfgVO;
import com.zengshi.ecp.busi.goods.vo.GdsManageVO;
import com.zengshi.ecp.cms.dubbo.dto.CmsAdvertiseReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsAdvertiseRespDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsInfoReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsInfoRespDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsPlaceReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsPlaceRespDTO;
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
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.staff.dubbo.dto.ShopInfoResDTO;
import com.zengshi.ecp.staff.dubbo.interfaces.IShopInfoRSV;
import com.zengshi.ecp.system.util.ConstantTool;
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
 * Project Name:ecp-busi-ecp-web-manage <br>
 * Description: 卖家中心跳转过来的广告配置广告页面<br>
 * Date:2016年6月16日上午10:28:24  <br>
 * Copyright (c) 2016 ZengShi All Rights Reserved <br>
 * 
 * @author gxq
 * @version  
 * @since JDK 1.6
 */
@RequestMapping(value="/cmsselleradvertise")
@Controller
public class CmsSellerAdvertiseController extends EcpBaseController{
    private static String SHOPFISHING = "shopfishing_";
    private static String MODULE = CmsSellerAdvertiseController.class.getName();
    private String URL = "/pageConfig/seller/selleradvertise/seller-manage-advertise";
    private String URL_OPEN = "/pageConfig/seller/selleradvertise/open/seller-manage-advertise";//返回页面的弹出窗路径
    
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

    @RequestMapping("init")
    public String init(Model model,HttpServletRequest request){
        model.addAttribute("pageName", "advertise");
        model.addAttribute("shopId", request.getSession().getAttribute(SHOPFISHING));
        return URL+"-grid";
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
            @ModelAttribute("searchParams") String searchParams,
            HttpServletRequest request) throws Exception{
        model.addAttribute("shopId", request.getSession().getAttribute(SHOPFISHING));
        model.addAttribute("pageName", "advertise");
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
            @ModelAttribute("searchParams") String searchParams,
            HttpServletRequest request)throws Exception {
        CmsAdvertiseRespDTO cmsAdvertiseRespDTO = new CmsAdvertiseRespDTO();
        cmsAdvertiseRespDTO.setPubTime(DateUtil.getSysDate());
        model.addAttribute("respVO", cmsAdvertiseRespDTO);
        CmsPlaceRespDTO respCmsPlace=new CmsPlaceRespDTO();
        respCmsPlace.setPlaceSize(100L);
        respCmsPlace.setPlaceWidth(600L);
        respCmsPlace.setPlaceHeight(300L);
        model.addAttribute("respCmsPlace",respCmsPlace);
        model.addAttribute("shopId", request.getSession().getAttribute(SHOPFISHING));
        model.addAttribute("pageName", "advertise");
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
            @ModelAttribute("searchParams") String searchParams,
            HttpServletRequest request) throws Exception{
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
            CmsPlaceReqDTO cmsPlaceReqDTO = new CmsPlaceReqDTO();
            cmsPlaceReqDTO.setId(cmsAdvertiseRespDTO.getPlaceId());
            CmsPlaceRespDTO respCmsPlace=cmsPlaceRSV.queryCmsPlace(cmsPlaceReqDTO);
            model.addAttribute("respCmsPlace",respCmsPlace);
        } catch (BusinessException e) {
            // TODO: handle exception
            throw new BusinessException(e.getErrorCode());
        }
        model.addAttribute("shopId", request.getSession().getAttribute(SHOPFISHING));
        model.addAttribute("pageName", "advertise");
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
            @ModelAttribute("searchParams") String searchParams,
            HttpServletRequest request) throws Exception{
        
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
        model.addAttribute("shopId", request.getSession().getAttribute(SHOPFISHING));
        model.addAttribute("pageName", "advertise");
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
        cmsAdvertiseRSV.changeStatusCmsAdvertiseBatch(list, status);
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
            throw new BusinessException("cms.common.param.null.error",new String[] {"ids"}); 
        }
        String[] idsArray = ids.split(",");
        List<String> list = Arrays.asList(idsArray);
        if(CollectionUtils.isEmpty(list)){
            throw new BusinessException("cms.common.param.null.error",new String[] {"ids"}); 
        }
        cmsAdvertiseRSV.deleteCmsAdvertiseBatch(list);
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
     * @author jiangzh 
     * @param cmsAdvertiseVO
     * @return 
     * @since JDK 1.6 
     */ 
    @RequestMapping(value="/pubsave")
    @ResponseBody
    public EcpBaseResponseVO pubsave(@Valid CmsAdvertiseVO VO) throws Exception{
        LogUtil.info(MODULE,"==========" + JSONObject.toJSONString(VO));
        CmsAdvertiseReqDTO reqDTO = new CmsAdvertiseReqDTO();
        ObjectCopyUtil.copyObjValue(VO, reqDTO, null, true);
        reqDTO.setStatus(CmsConstants.ParamStatus.CMS_PARAMSTATUS_1);//发布
        if("09".equals(VO.getLinkType())){//其它
            reqDTO.setLinkUrl(VO.getLinkName());
        }
        /*if("09".equals(VO.getLinkType()) || "03".equals(VO.getLinkType())){//其它\促销
            reqDTO.setLinkUrl(VO.getLinkName());
        }*/
        /*else if("03".equals(VO.getLinkType())){//促销
            BaseSysCfgRespDTO sysDTO = SysCfgUtil.fetchSysCfg(PROM_NET_ADDRESS);
            if(sysDTO != null){
                reqDTO.setLinkUrl(sysDTO.getParaValue());
            }
        }*/
        
        EcpBaseResponseVO respVO = new EcpBaseResponseVO();
        if(reqDTO.getId() != null){
            cmsAdvertiseRSV.updateCmsAdvertise(reqDTO);
        }else{
            cmsAdvertiseRSV.addCmsAdvertise(reqDTO);
        }
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
        reqDTO.setStatus(CmsConstants.ParamStatus.CMS_PARAMSTATUS_0);//未发布
        if("09".equals(VO.getLinkType())){//其它
            reqDTO.setLinkUrl(VO.getLinkName());
        }
        /*else if("03".equals(VO.getLinkType())){//促销
            BaseSysCfgRespDTO sysDTO = SysCfgUtil.fetchSysCfg(PROM_NET_ADDRESS);
            if(sysDTO != null){
                reqDTO.setLinkUrl(sysDTO.getParaValue());
            }
        }*/
        
        EcpBaseResponseVO respVO = new EcpBaseResponseVO();
        if(VO.getId() != null){
            cmsAdvertiseRSV.updateCmsAdvertise(reqDTO);
        }else{
            cmsAdvertiseRSV.addCmsAdvertise(reqDTO);
        }
        respVO.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_SUCCESS);
        return respVO;
    }
    
    @RequestMapping(value="/save2")
    @ResponseBody
    public EcpBaseResponseVO save2(@RequestParam("code")String code,
            @RequestParam("info")String info, 
            @RequestParam("createTime") @DateTimeFormat(pattern="yyyy-MM-dd")Date creatTime){
        LogUtil.info(MODULE,"==========createTime:"+creatTime.getTime()+";info:"+info+";code:"+code);
        EcpBaseResponseVO respVO = new EcpBaseResponseVO();
        respVO.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_SUCCESS);
        return respVO;
    }
    
    @RequestMapping(value="/saveform")
    public String saveForm(Model model,@Valid @ModelAttribute("demoCfg") DemoCfgVO demoCfg, BindingResult result,
            HttpServletRequest request){
        LogUtil.info(this.getClass().getName(), "========saveform");
        model.addAttribute("shopId", request.getSession().getAttribute(SHOPFISHING));
        model.addAttribute("pageName", "advertise");
        if(result.hasErrors()){
            LogUtil.info(this.getClass().getName(), "========hasError");
            return URL+"-form";
        } else {
            return "redirect:/cmsselleradvertise/grid";
        }
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
    public Model gridList(Model model, @ModelAttribute("searchVO") CmsAdvertiseVO searchVO) throws Exception{
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
    public String opengds(Model model, 
            @ModelAttribute("searchVO") GdsManageVO searchVO,
            @RequestParam("siteId")String siteId,
            HttpServletRequest request){
        model.addAttribute("siteId", siteId);
        model.addAttribute("shopId", request.getSession().getAttribute(SHOPFISHING));
        return URL_OPEN + "-gds";
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
    public Model querygds(Model model, 
            @ModelAttribute("searchVO") GdsManageVO searchVO,
            @RequestParam("siteId")String siteId) throws Exception{
        LogUtil.info(MODULE,"==========" + JSONObject.toJSONString(searchVO));
        //1. 调用后场服务所需要的DTO；
        GdsInfoReqDTO reqDTO = searchVO.toBaseInfo(GdsInfoReqDTO.class);
        ObjectCopyUtil.copyObjValue(searchVO, reqDTO, "MODULE", true);
        reqDTO.setGdsStatus(GdsConstants.GdsInfo.GDS_STATUS_ONSHELVES);//已上架
        //选择相应站点的商品
        if(siteId.equalsIgnoreCase("1")){
            reqDTO.setIfScoreGds("0");
        }else{
            reqDTO.setIfScoreGds("1");
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
    public String openinfo(Model model,HttpServletRequest request){
        model.addAttribute("shopId", request.getSession().getAttribute(SHOPFISHING));
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
    @SuppressWarnings("rawtypes")
    @RequestMapping("/queryinfo")
    @ResponseBody
    public Model queryinfo(Model model, @ModelAttribute("searchVO") CmsInfoVO searchVO) throws Exception{
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
}

