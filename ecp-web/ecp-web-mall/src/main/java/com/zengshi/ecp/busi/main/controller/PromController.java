package com.zengshi.ecp.busi.main.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zengshi.ecp.base.controller.EcpBaseController;
import com.zengshi.ecp.busi.main.vo.CmsPromReqVO;
import com.zengshi.ecp.busi.main.vo.ComponentReqVO;
import com.zengshi.ecp.busi.pageConfig.pageConfig.modular.utils.CmsGoodsDetailUtil;
import com.zengshi.ecp.cms.dubbo.dto.CmsAdvertiseReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsAdvertiseRespDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsFloorCouponReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsFloorCouponRespDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsFloorGdsReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsFloorGdsRespDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsFloorLabelReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsFloorLabelRespDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsFloorReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsFloorRespDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsFloorTabReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsFloorTabRespDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsPlaceReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsPlaceRespDTO;
import com.zengshi.ecp.cms.dubbo.interfaces.ICmsAdvertiseRSV;
import com.zengshi.ecp.cms.dubbo.interfaces.ICmsFloorAdvertiseRSV;
import com.zengshi.ecp.cms.dubbo.interfaces.ICmsFloorCouponRSV;
import com.zengshi.ecp.cms.dubbo.interfaces.ICmsFloorGdsRSV;
import com.zengshi.ecp.cms.dubbo.interfaces.ICmsFloorLabelRSV;
import com.zengshi.ecp.cms.dubbo.interfaces.ICmsFloorRSV;
import com.zengshi.ecp.cms.dubbo.interfaces.ICmsFloorTabRSV;
import com.zengshi.ecp.cms.dubbo.interfaces.ICmsPlaceRSV;
import com.zengshi.ecp.cms.dubbo.interfaces.ICmsSiteRSV;
import com.zengshi.ecp.cms.dubbo.util.CmsConstants;
import com.zengshi.ecp.coupon.dubbo.dto.resp.CoupInfoRespDTO;
import com.zengshi.ecp.coupon.dubbo.interfaces.ICoupRSV;
import com.zengshi.ecp.goods.dubbo.constants.GdsOption.GdsQueryOption;
import com.zengshi.ecp.goods.dubbo.constants.GdsOption.SkuQueryOption;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfo.GdsInfoDetailRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfo.GdsInfoReqDTO;
import com.zengshi.ecp.server.front.dto.BaseInfo;
import com.zengshi.ecp.server.front.dto.BaseStaff;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.system.util.ParamsTool;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.StringUtil;
import com.alibaba.dubbo.common.utils.CollectionUtils;

/**
 * 商城促销-页面: ECP <br>
 * Project Name:ecp-web-mall <br>
 * Description: <br>
 * Date:2015年8月17日下午6:54:49 <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author jiangzh
 * @version
 * @since JDK 1.6
 */
@Controller
@RequestMapping(value = "/prom")
public class PromController extends EcpBaseController {

    private static String MODULE = PromController.class.getName();
    @Resource(name = "cmsAdvertiseRSV")
    private ICmsAdvertiseRSV cmsAdvertiseRSV;
    @Resource(name = "cmsFloorRSV")
    private ICmsFloorRSV cmsFloorRSV;
    @Resource(name = "cmsFloorGdsRSV")
    private ICmsFloorGdsRSV cmsFloorGdsRSV;
    @Resource(name = "cmsFloorTabRSV")
    private ICmsFloorTabRSV cmsFloorTabRSV;
    @Resource(name = "cmsPlaceRSV")
    private ICmsPlaceRSV cmsPlaceRSV;
    @Resource(name = "cmsFloorAdvertiseRSV")
    private ICmsFloorAdvertiseRSV cmsFloorAdvertiseRSV;
    @Resource(name = "cmsFloorLabelRSV")
    private ICmsFloorLabelRSV cmsFloorLabelRSV;
    @Resource(name = "cmsSiteRSV")
    private ICmsSiteRSV cmsSiteRSV;
    @Resource
    private ICmsFloorCouponRSV cmsFloorCouponRSV;
    @Resource
    private ICoupRSV coupRSV;
    
    /** 
     * init:(促销页面初始化). <br/> 
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
    @RequestMapping(value = "/init")
    public String init(Model model, @RequestParam(value = "page", required = false) String page) {
        String url = "/main/prom/prom-content";// 返回页面
        return url;
    }

    /** 
     * qryPromFloorVM:(根据内容位置获取楼层信息且返回指定VM页面). <br/> 
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
    @RequestMapping(value = "/qryPromFloorVM")
    public String qryPromFloorVM(Model model,CmsPromReqVO reqVO,HttpServletRequest request){
        if(reqVO!=null){
            LogUtil.info(MODULE, "==========reqVO:" + reqVO.toString() + ";");
        }
        
        CmsPlaceRespDTO placeRespDto = null;//内容位置数据
        CmsFloorRespDTO floorRespDTO = null;//楼层数据
        CmsAdvertiseRespDTO cmsAdvertiseRespDTO = null;//广告
        List<CmsFloorLabelRespDTO> floorLabelList = null;//楼层标签列表
        List<CoupInfoRespDTO> coupInfoRespDTOList= null; //优惠券列表
        List<CmsFloorTabRespDTO> floorTabRespList = null;//楼层页签列表
        
        String  url = null;
        if(reqVO != null && StringUtil.isNotEmpty(reqVO.getPlaceId())  && StringUtil.isNotBlank(reqVO.getReturnUrl())){
            //1.初始化默认数据
            url = reqVO.getReturnUrl();
            if(StringUtil.isBlank(reqVO.getFloorType())){//默认优惠券与商品都查询   楼层类型 0，纯优惠券  1纯商品  2优惠券加商品
                reqVO.setFloorType("2");
            }
            if(StringUtil.isBlank(reqVO.getStatus())){//默认有效
                reqVO.setStatus(CmsConstants.ParamStatus.CMS_PARAMSTATUS_1);
            }
            //根据内容位置初始化图片规则等信息
            CmsPlaceReqDTO placereqDto = new  CmsPlaceReqDTO();
            placereqDto.setId(reqVO.getPlaceId());
            placereqDto.setStatus(reqVO.getStatus());
            try {
                placeRespDto = cmsPlaceRSV.queryCmsPlace(placereqDto);
            } catch (Exception err) {
                LogUtil.error(MODULE, "查询内容位置出现异常:",err);
            }
            
            if(placeRespDto!=null && StringUtil.isNotEmpty(placeRespDto.getId())){//具有有效内容位置
                if(StringUtil.isNotEmpty(placeRespDto.getPlaceHeight())){
                    reqVO.setPlaceHeight(placeRespDto.getPlaceHeight());
                }
                if(StringUtil.isNotEmpty(placeRespDto.getPlaceWidth())){
                    reqVO.setPlaceWidth(placeRespDto.getPlaceWidth());
                }
                if(StringUtil.isNotEmpty(placeRespDto.getPlaceCount())){
                    reqVO.setGdsSize(placeRespDto.getPlaceCount());
                }
                
                try {
                    //2.查询楼层
                    floorRespDTO = this.qryFloorByPlaceId(reqVO);
                    
                    if(floorRespDTO != null && StringUtil.isNotEmpty(floorRespDTO.getId())){//具有有效楼层
                        // 3. 根据楼层查询楼层标签
                        CmsFloorLabelReqDTO cmsFloorLabelReqDTO =  new CmsFloorLabelReqDTO();
                        cmsFloorLabelReqDTO.setFloorId(floorRespDTO.getId());// 楼层ID
                        cmsFloorLabelReqDTO.setStatus(reqVO.getStatus());
                        floorLabelList = cmsFloorLabelRSV.queryCmsFloorLabelList(cmsFloorLabelReqDTO);
                        
                        //楼层类型 0，纯优惠券  1纯商品  2优惠券加商品
                        if("0".equalsIgnoreCase(reqVO.getFloorType()) || "2".equalsIgnoreCase(reqVO.getFloorType())){
                            //4. 查询优惠券
                            coupInfoRespDTOList = this.qryCoupByFloorID(reqVO.getCoupSize(),floorRespDTO.getId());
                        }
                        
                        //楼层类型 0，纯优惠券  1纯商品  2优惠券加商品
                        if("1".equalsIgnoreCase(reqVO.getFloorType()) || "2".equalsIgnoreCase(reqVO.getFloorType())){
                         // 5. 当有效楼层不为空时，根据楼层ID查询页签
                            floorTabRespList = this.qryFloorTabByFloorId(reqVO,floorRespDTO.getId());
                        }
                    }
                    
                    
                } catch (Exception err) {
                    LogUtil.error(MODULE, "查询楼层信息出现异常:",err);
                    model.addAttribute("errorMsg", "查询楼层信息出现异常!");
                }
            }
        }
        
        // 6 .数据返回
        model.addAttribute("floorRespDTO", floorRespDTO);
        model.addAttribute("floorAdvertise", cmsAdvertiseRespDTO);
        model.addAttribute("floorLabelList", floorLabelList);
        model.addAttribute("coupList", coupInfoRespDTOList);
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
    public List<CmsAdvertiseRespDTO> qryAdvertiseList(CmsPromReqVO reqVO,HttpServletRequest request) throws Exception {
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
                // 3. 调文件服务器，返回图片
                if (!CollectionUtils.isEmpty(respList)) {
                    for (CmsAdvertiseRespDTO dto : respList) {
                        // 3.1调文件服务器，返回图片
                        if (StringUtil.isNotBlank(dto.getVfsId())) {
                            dto.setVfsUrl(ParamsTool.getImageUrl(dto.getVfsId(),standard));
                        }
                        if (StringUtil.isNotBlank(dto.getLinkUrl())) {
                            //当链接类型不为促销、其它时，拼绝对路径
                            if(!"03".equals(dto.getLinkType()) && !"09".equals(dto.getLinkType())){
                                dto.setLinkUrl(request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+dto.getLinkUrl());
                            }
                        }
                    }
                }
            }
        }catch(Exception err){
            LogUtil.error(MODULE, "查询广告出现异常:",err);
        }
        return respList;
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
     * qryFloorList:(根据内容位置获取楼层信息). <br/> 
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
    public Map<String,Object> qryFloorList(Model model,CmsPromReqVO reqVO) throws Exception {
        LogUtil.info(MODULE, "==========reqVO:" + reqVO.toString() + ";" );
        
        Map<String,Object> resultMap = new HashMap<String,Object>();
       
        CmsFloorRespDTO cmsFloorRespDTO = null;
        List<CmsFloorTabRespDTO> floorTabRespList = null;
        List<GdsInfoDetailRespDTO> gdsInfoDetailRespList = null;
        try{
            // 1 根据内容位置ID查询有效楼层
            cmsFloorRespDTO = this.qryFloorByPlaceId(reqVO);
            if(cmsFloorRespDTO != null && StringUtil.isNotEmpty(cmsFloorRespDTO.getId())){
                // 2 当有效楼层不为空时，根据楼层ID查询页签
                if (reqVO.getTabSize()!=null && reqVO.getTabSize() > 0) {//页签大于0 即启用页签
                    floorTabRespList = this.qryFloorTabByFloorId(reqVO, cmsFloorRespDTO.getId());
                } 
                // 3 当页签不为空时，获取第一个页签的商品，当页签为空时，获取楼层下的商品
                CmsFloorGdsReqDTO cmsFloorGdsReqDTO = new CmsFloorGdsReqDTO();
                cmsFloorGdsReqDTO.setFloorId(cmsFloorRespDTO.getId());// 楼层ID
                if(!CollectionUtils.isEmpty(floorTabRespList)){
                    CmsFloorTabRespDTO cmsFloorTabRespDTO = floorTabRespList.get(0);
                    if(cmsFloorTabRespDTO != null && StringUtil.isNotEmpty(cmsFloorTabRespDTO.getId())){
                        cmsFloorGdsReqDTO.setTabId(cmsFloorTabRespDTO.getId());
                    }
                }
                gdsInfoDetailRespList = this.qryFloorGdsList(reqVO,cmsFloorGdsReqDTO);
            }
        }catch(Exception err){
            LogUtil.error(MODULE, "查询商品分类出现异常:",err);
        }
        
        // 4.返回结果
        resultMap.put("floorRespDTO", cmsFloorRespDTO);
        resultMap.put("floorTabList", floorTabRespList);
        resultMap.put("gdsList", gdsInfoDetailRespList);
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
        }catch(Exception err){
            LogUtil.error(MODULE, "查询查询商品出现异常:",err);
            resultMap.put("errorMsg", "查询商品出现异常！");
        }
        
        resultMap.put("gdsList", gdsInfoDetailRespList);
        return resultMap;
    }
    
    /** 
     * queryTabGdsVM:(根据页签ID获取页签下的商品). <br/> 
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
    @RequestMapping(value = "/queryTabGdsVM")
    public String queryTabGdsVM(Model model,CmsPromReqVO reqVO) throws Exception {
        if(reqVO!=null){
            LogUtil.info(MODULE, "==========reqVO:" + reqVO.toString() + ";");
        }
        String url = null;
        List<GdsInfoDetailRespDTO> gdsInfoDetailRespList = null;
        
        if(reqVO != null && StringUtil.isNotEmpty(reqVO.getReturnUrl())&& StringUtil.isNotEmpty(reqVO.getFloorId())){
            
            if(StringUtil.isBlank(reqVO.getStatus())){//默认有效
                reqVO.setStatus(CmsConstants.ParamStatus.CMS_PARAMSTATUS_1);
            }
            
            //根据内容位置初始化图片规则等信息
            CmsPlaceRespDTO placeRespDto = new CmsPlaceRespDTO();
            CmsPlaceReqDTO placereqDto = new  CmsPlaceReqDTO();
            placereqDto.setId(reqVO.getPlaceId());
            placereqDto.setStatus(reqVO.getStatus());
            try {
                placeRespDto = cmsPlaceRSV.queryCmsPlace(placereqDto);
            } catch (Exception err) {
                LogUtil.error(MODULE, "查询内容位置出现异常:",err);
            }
            if(placeRespDto!=null && StringUtil.isNotEmpty(placeRespDto.getId())){//具有有效内容位置
                if(StringUtil.isNotEmpty(placeRespDto.getPlaceHeight())){
                    reqVO.setPlaceHeight(placeRespDto.getPlaceHeight());
                }
                if(StringUtil.isNotEmpty(placeRespDto.getPlaceWidth())){
                    reqVO.setPlaceWidth(placeRespDto.getPlaceWidth());
                }
                if(StringUtil.isNotEmpty(reqVO.getGdsSize()) && StringUtil.isNotEmpty(placeRespDto.getPlaceCount())){
                    reqVO.setGdsSize(placeRespDto.getPlaceCount());
                }
            }
            
            url = reqVO.getReturnUrl();

            String standard = "";//规格
            if(StringUtil.isNotEmpty(reqVO.getPlaceWidth()) && StringUtil.isNotEmpty(reqVO.getPlaceHeight())){
                standard = reqVO.getPlaceWidth() + "x" + reqVO.getPlaceHeight() + "!";
            }
            CmsFloorGdsReqDTO cmsFloorGdsReqDTO = new CmsFloorGdsReqDTO();
            cmsFloorGdsReqDTO.setFloorId(reqVO.getFloorId());//楼层id
            
            if (StringUtil.isNotEmpty(reqVO.getTabId())) {//页签
                cmsFloorGdsReqDTO.setTabId(reqVO.getTabId());
            }
            if (StringUtil.isNotEmpty(reqVO.getStatus())) {//状态
                cmsFloorGdsReqDTO.setStatus(reqVO.getStatus());
            }else{
                cmsFloorGdsReqDTO.setStatus(CmsConstants.ParamStatus.CMS_PARAMSTATUS_1);
            }
            cmsFloorGdsReqDTO.setPageNo(1);
            if (StringUtil.isNotEmpty(reqVO.getGdsSize())) {//楼层商品数量
                cmsFloorGdsReqDTO.setPageSize(reqVO.getGdsSize());
            }else{
                cmsFloorGdsReqDTO.setPageSize(Integer.MAX_VALUE);
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
                model.addAttribute("errorMsg", "查询商品出现异常!");
            }
            
            model.addAttribute("gdsList", gdsInfoDetailRespList);
        }
        
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
    private List<GdsInfoDetailRespDTO> qryGdsDetailList(List<CmsFloorGdsRespDTO> respList,String standard) throws Exception{
        List<GdsInfoDetailRespDTO> gdsInfoDetailRespList = null;
        if (!CollectionUtils.isEmpty(respList)) {
            gdsInfoDetailRespList = new ArrayList<GdsInfoDetailRespDTO>();
            for (CmsFloorGdsRespDTO dto : respList) {
                if (dto != null && StringUtil.isNotEmpty(dto.getGdsId())) {
                    GdsInfoDetailRespDTO gdsInfoDetailRespDTO = null;
                    //设置需要的单品属性Id
                    List<Long> propIds = new ArrayList<Long>();
                    try {
                        gdsInfoDetailRespDTO = this.queryGdsInfoDetailById(dto, propIds, standard);
                    } catch (Exception e) {
                        gdsInfoDetailRespDTO =null;
                        LogUtil.error(MODULE, "查询商品id为:"+dto.getId()+"的商品详情出现异常", e);
                    }
                    
                    if(gdsInfoDetailRespDTO !=null && StringUtil.isNotEmpty(gdsInfoDetailRespDTO.getId())){
                        gdsInfoDetailRespList.add(gdsInfoDetailRespDTO); 
                    }
                }
            }
        }
        return gdsInfoDetailRespList;
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
    private GdsInfoDetailRespDTO queryGdsInfoDetailById(CmsFloorGdsRespDTO floorGdsDto,List<Long> propIds,String standard) throws Exception {
        GdsInfoDetailRespDTO gdsInfoDetailRespDTO = null;
        if(StringUtil.isNotEmpty(floorGdsDto.getGdsId())){
            GdsInfoReqDTO gdsInfoReqDTO = new GdsInfoReqDTO();
            GdsQueryOption[] gdsOptions=new GdsQueryOption[]{
                    GdsQueryOption.BASIC,GdsQueryOption.MAINPIC
            };
            SkuQueryOption[] skuOptions=new SkuQueryOption[]{
                    SkuQueryOption.BASIC,SkuQueryOption.PROP,SkuQueryOption.CAlDISCOUNT
            };
            
            //获取指定的属性值  提高效率 start
            if(propIds == null){
                propIds = new ArrayList<Long>();
                propIds.add(1001l);//作者
                //propIds.add(1005l);//出版日期
                //propIds.add(1006l);//出版社
                //propIds.add(1020l);//内容简介
            }
            gdsInfoReqDTO.setPropIds(propIds);
            //获取指定的属性值   提高效率 end
            
            gdsInfoReqDTO.setGdsQueryOptions(gdsOptions);
            gdsInfoReqDTO.setSkuQuerys(skuOptions);
            gdsInfoReqDTO.setId(floorGdsDto.getGdsId());
            gdsInfoDetailRespDTO = CmsGoodsDetailUtil.getGdsDetailByGdsId(gdsInfoReqDTO);
            if(gdsInfoDetailRespDTO != null){
                Long promId = null;
                //根据促销id 修改价格
                if(CmsConstants.IsNot.CMS_ISNOT_1.equalsIgnoreCase(floorGdsDto.getIsProm()) && StringUtil.isNotEmpty(floorGdsDto.getPromId())){
                    promId = floorGdsDto.getPromId();
                }
                CmsGoodsDetailUtil.extendGdsInfo(gdsInfoDetailRespDTO, propIds, false, true, standard);
                CmsGoodsDetailUtil.getGdsPromPrice(gdsInfoDetailRespDTO, CmsConstants.PlatformType.CMS_PLATFORMTYPE_01, promId);
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
    private CmsFloorRespDTO qryFloorByPlaceId(CmsPromReqVO reqVO) throws Exception{
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
    private List<CmsFloorTabRespDTO> qryFloorTabByFloorId(CmsPromReqVO reqVO,Long floorId) throws Exception{
        CmsFloorTabReqDTO cmsFloorTabReqDTO = new CmsFloorTabReqDTO();
        List<CmsFloorTabRespDTO> floorTabRespList = new ArrayList<CmsFloorTabRespDTO>();
        if(StringUtil.isNotEmpty(floorId)){
            cmsFloorTabReqDTO.setFloorId(floorId);
            if (StringUtil.isNotEmpty(reqVO.getStatus())) {//状态
                cmsFloorTabReqDTO.setStatus(reqVO.getStatus());
            } else {//默认有效
                cmsFloorTabReqDTO.setStatus(CmsConstants.ParamStatus.CMS_PARAMSTATUS_1);
            }
            cmsFloorTabReqDTO.setPageNo(1);
            if (StringUtil.isNotEmpty(reqVO.getTabSize())) {// 楼层页签数量
                cmsFloorTabReqDTO.setPageSize(reqVO.getTabSize());
            }else{
                cmsFloorTabReqDTO.setPageSize(Integer.MAX_VALUE);
            }
            if(cmsFloorTabReqDTO.getPageSize() != null && cmsFloorTabReqDTO.getPageSize() != 0){
                PageResponseDTO<CmsFloorTabRespDTO> floorTabPageInfo = cmsFloorTabRSV.queryCmsFloorTabPage(cmsFloorTabReqDTO);
                if(floorTabPageInfo != null){
                    floorTabRespList = floorTabPageInfo.getResult();
                }
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
    private List<GdsInfoDetailRespDTO> qryFloorGdsList(CmsPromReqVO reqVO,CmsFloorGdsReqDTO cmsFloorGdsReqDTO) throws Exception{
        // 图片规格
        String standard = "";
        if (StringUtil.isNotEmpty(reqVO.getPlaceWidth())&& StringUtil.isNotEmpty(reqVO.getPlaceHeight())) {
            standard = reqVO.getPlaceWidth() + "x" + reqVO.getPlaceHeight() + "!";
        }
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
        }else{
            cmsFloorGdsReqDTO.setPageSize(0);
        }
        // 3. 根据楼层或者页签ID获取商品
        // 3.1 查询楼层商品
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
     * 
     * getStaffInfo:(这里用一句话描述这个方法的作用). <br/> 
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
    @RequestMapping(value = "/getStaffInfo")
    @ResponseBody
    public BaseStaff getStaffInfo(){
        BaseStaff staff = new BaseInfo().getStaff();
        if(staff == null){
            staff = new BaseStaff();
        }
        return staff;
    }
}
