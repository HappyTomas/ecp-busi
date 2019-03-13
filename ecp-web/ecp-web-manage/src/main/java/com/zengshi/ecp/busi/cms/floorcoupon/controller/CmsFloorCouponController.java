package com.zengshi.ecp.busi.cms.floorcoupon.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
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
import com.zengshi.ecp.busi.cms.floorcoupon.vo.CmsFloorCouponBatchVO;
import com.zengshi.ecp.busi.cms.floorcoupon.vo.CmsFloorCouponVO;
import com.zengshi.ecp.cms.dubbo.dto.CmsFloorCouponReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsFloorCouponRespDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsFloorReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsFloorRespDTO;
import com.zengshi.ecp.cms.dubbo.interfaces.ICmsFloorCouponRSV;
import com.zengshi.ecp.cms.dubbo.interfaces.ICmsFloorRSV;
import com.zengshi.ecp.cms.dubbo.interfaces.ICmsPlaceRSV;
import com.zengshi.ecp.cms.dubbo.util.CmsConstants;
import com.zengshi.ecp.cms.dubbo.util.CmsConstants.ParamStatus;
import com.zengshi.ecp.coupon.dubbo.dto.req.CoupInfoReqDTO;
import com.zengshi.ecp.coupon.dubbo.dto.resp.CoupInfoRespDTO;
import com.zengshi.ecp.coupon.dubbo.interfaces.ICoupRSV;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
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
@RequestMapping(value="/floorcoupon")
public class CmsFloorCouponController extends EcpBaseController {
    
    private static String MODULE = CmsFloorCouponController.class.getName();
    
    private String URL = "/cms/floor/coupon/";//返回页面的基本路径 
    private String URL_OPEN = "/cms/floor/coupon/open/";//返回页面的弹出窗路径
    
    @Resource(name="cmsFloorCouponRSV")
    private ICmsFloorCouponRSV cmsFloorCouponRSV;
    @Resource(name="cmsPlaceRSV")
    private ICmsPlaceRSV cmsPlaceRSV;
    @Resource(name="cmsFloorRSV")
    private ICmsFloorRSV cmsFloorRSV;
    @Resource 
    private ICoupRSV coupRSV;

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
        return URL+"coupon-grid";
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
     * @return 
     * @since JDK 1.6 
     */ 
    @RequestMapping(value="/add")
    public String add(Model model,
            @RequestParam("floorId")String floorId,
            @ModelAttribute("searchParams") String searchParams,
            @ModelAttribute("floorSearchParams") String floorSearchParams) throws Exception{
        /*1.通过楼层ID查询楼层*/
        CmsFloorReqDTO reqDTO = new CmsFloorReqDTO();
        if(StringUtils.isNotBlank(floorId)){
            reqDTO.setId(Long.valueOf(floorId));
        }
        /*2.copy对象*/
        CmsFloorRespDTO floorRespDTO = cmsFloorRSV.queryCmsFloor(reqDTO);
        /*3.返回给页面*/
        model.addAttribute("floorRespDTO",floorRespDTO);
        return URL+"coupon-edit";
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
     * @return 
     * @since JDK 1.6 
     */ 
    @RequestMapping(value="/batchadd")
    public String batchadd(Model model,
            @RequestParam("floorId")String floorId,
            @ModelAttribute("searchParams") String searchParams,
            @ModelAttribute("floorSearchParams") String floorSearchParams) throws Exception{
        /*1.通过楼层ID查询楼层*/
        CmsFloorReqDTO reqDTO = new CmsFloorReqDTO();
        if(StringUtils.isNotBlank(floorId)){
            reqDTO.setId(Long.valueOf(floorId));
        }
        /*2.copy对象*/
        CmsFloorRespDTO floorRespDTO = cmsFloorRSV.queryCmsFloor(reqDTO);
        /*3.返回给页面*/
        model.addAttribute("floorRespDTO",floorRespDTO);
        return URL+"coupon-batch-add";
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
        if(StringUtils.isBlank(id)){
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
        model.addAttribute("floorRespDTO",floorRespDTO);
        
        /*4.根据楼层优惠券ID获取优惠券信息*/
        CmsFloorCouponReqDTO floorCouponReqDTO = new CmsFloorCouponReqDTO();
        floorCouponReqDTO.setId(Long.valueOf(id));
        try {
            CmsFloorCouponRespDTO respDTO = cmsFloorCouponRSV.queryCmsFloorCoupon(floorCouponReqDTO);
            //5. 调用优惠券，返回优惠券信息
            if(respDTO != null && respDTO.getCouponId() != null){
                CoupInfoReqDTO coupInfoReqDTO=new CoupInfoReqDTO();
                coupInfoReqDTO.setId(respDTO.getCouponId());
                CoupInfoRespDTO coupInfoRespDTO=coupRSV.queryCoupInfo(coupInfoReqDTO);
                if(coupInfoRespDTO != null){
                    respDTO.setCouponName(coupInfoRespDTO.getCoupName());
                } 
            }
            model.addAttribute("respDTO",respDTO);
        } catch (BusinessException e) {
            // TODO: handle exception
            throw new BusinessException(e.getErrorCode());
        }
        
        /*3.返回信息*/
        return URL+"coupon-edit";
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
            @ModelAttribute("floorSearchParams") String floorSearchParams) throws Exception{
        /*1.验证入参*/
        if(StringUtils.isBlank(id)){
            String[] keyInfos = new String[1];
            keyInfos[0]="floorId";
           throw new BusinessException("cms.common.param.null.error",keyInfos); 
        }
        
        /*2.根据楼层ID获取楼层信息*/
        CmsFloorCouponReqDTO reqDTO = new CmsFloorCouponReqDTO();
        reqDTO.setId(Long.valueOf(id));
        try {
            CmsFloorCouponRespDTO respDTO = cmsFloorCouponRSV.queryCmsFloorCoupon(reqDTO);
            //3. 调用优惠券，返回优惠券信息
            if(respDTO != null && respDTO.getCouponId() != null){
                CoupInfoReqDTO coupInfoReqDTO=new CoupInfoReqDTO();
                coupInfoReqDTO.setId(respDTO.getCouponId());
                CoupInfoRespDTO coupInfoRespDTO=coupRSV.queryCoupInfo(coupInfoReqDTO);
                if(coupInfoRespDTO != null){
                    respDTO.setCouponName(coupInfoRespDTO.getCoupName());
                } 
            }
            model.addAttribute("respDTO",respDTO);
        } catch (BusinessException e) {
            // TODO: handle exception
            throw new BusinessException(e.getErrorCode());
        }
        
        /*3.返回信息*/
        return URL+"coupon-view";
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
    @RequestMapping("/opencoupon")
    public String opencoupon(Model model,@RequestParam("siteId")String siteId) throws Exception{
        model.addAttribute("siteId",siteId);
        return URL_OPEN + "coupon-grid";
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
    @RequestMapping("/openbatchcoupon")
    public String openbatchcoupon(Model model,@RequestParam("siteId")String siteId) throws Exception{
        model.addAttribute("siteId",siteId);
        return URL_OPEN + "batch-coupon-grid";
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
        cmsFloorCouponRSV.changeStatusCmsFloorCouponBatch(list, status);
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
        cmsFloorCouponRSV.deleteCmsFloorCouponBatch(list);
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
     * @param CmsFloorCouponVO
     * @return 
     * @since JDK 1.6 
     */ 
    @RequestMapping(value="/pubsave")
    @ResponseBody
    public CmsFloorCouponRespDTO pubsave(@Valid CmsFloorCouponVO VO) throws Exception{
        LogUtil.info(MODULE,"==========" + JSONObject.toJSONString(VO));
        CmsFloorCouponReqDTO reqDTO = new CmsFloorCouponReqDTO();
        ObjectCopyUtil.copyObjValue(VO, reqDTO, null, true);
        reqDTO.setStatus(CmsConstants.ParamStatus.CMS_PARAMSTATUS_1);//发布
        //如果排序为空，则默认赋值为1.
        if(reqDTO.getSortNo() == null){
            reqDTO.setSortNo(1);
        }
        CmsFloorCouponRespDTO respDTO = new CmsFloorCouponRespDTO();
        if(reqDTO.getId() != null){
        	respDTO= cmsFloorCouponRSV.updateCmsFloorCoupon(reqDTO);
        }else{
        	respDTO= cmsFloorCouponRSV.addCmsFloorCoupon(reqDTO);
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
     * @param CmsFloorCouponVO
     * @return 
     * @since JDK 1.6 
     */ 
    @RequestMapping(value="/save")
    @ResponseBody
    public CmsFloorCouponRespDTO save(@Valid CmsFloorCouponVO VO) throws Exception{
        LogUtil.info(MODULE,"==========" + JSONObject.toJSONString(VO));
        CmsFloorCouponReqDTO reqDTO = new CmsFloorCouponReqDTO();
        ObjectCopyUtil.copyObjValue(VO, reqDTO, null, true);
        reqDTO.setStatus(CmsConstants.ParamStatus.CMS_PARAMSTATUS_0);//未发布
        //如果排序为空，则默认赋值为1.
        if(reqDTO.getSortNo() == null){
            reqDTO.setSortNo(1);
        }
        CmsFloorCouponRespDTO respDTO = new CmsFloorCouponRespDTO();
        if(VO.getId() != null){
            respDTO = cmsFloorCouponRSV.updateCmsFloorCoupon(reqDTO);
        }else{
            respDTO = cmsFloorCouponRSV.addCmsFloorCoupon(reqDTO);
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
    public EcpBaseResponseVO batchSave(@Valid CmsFloorCouponBatchVO VO) throws Exception{
        LogUtil.info(MODULE,"==========" + JSONObject.toJSONString(VO));
        
        List<CmsFloorCouponReqDTO> reqList = VO.getFloorCoupList();
        
        //验证数据
        if(CollectionUtils.isNotEmpty(reqList)){
            for(int i = 0 ; i < reqList.size();i++){
                CmsFloorCouponReqDTO reqDto= reqList.get(i);
                if(StringUtil.isEmpty(reqDto.getFloorId()) || StringUtil.isEmpty(reqDto.getCouponId())){
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
            }
        }
        
        //保存入库
        EcpBaseResponseVO resultVo = new EcpBaseResponseVO();
        try {
            cmsFloorCouponRSV.addCmsFloorCouponBatch(reqList);
            resultVo.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_SUCCESS);
        } catch (Exception e) {
            LogUtil.error(MODULE, "批量新增楼层优惠券失败", e);
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
    public Model gridList(Model model, @ModelAttribute("searchVO") CmsFloorCouponVO searchVO) throws Exception{
        LogUtil.info(MODULE,"==========" + JSONObject.toJSONString(searchVO));
        //1. 调用后场服务所需要的DTO；
        CmsFloorCouponReqDTO reqDTO = searchVO.toBaseInfo(CmsFloorCouponReqDTO.class);
        ObjectCopyUtil.copyObjValue(searchVO, reqDTO, "MODULE", true);
        
        //2. 调用后场服务，将后场返回的分页对象封装为前店所需要的分页对象；
        PageResponseDTO<CmsFloorCouponRespDTO> pageInfo = cmsFloorCouponRSV.queryCmsFloorCouponPage(reqDTO);
        
        //3. 调用商品，返回商品信息
        List<CmsFloorCouponRespDTO> respList = pageInfo.getResult();
        if(CollectionUtils.isNotEmpty(respList)){
            for(CmsFloorCouponRespDTO dto:respList){
                if(dto.getCouponId() != null){
                    CoupInfoReqDTO coupInfoReqDTO=new CoupInfoReqDTO();
                    coupInfoReqDTO.setId(dto.getCouponId());
                    CoupInfoRespDTO coupInfoRespDTO=coupRSV.queryCoupInfo(coupInfoReqDTO);
                    if(coupInfoRespDTO != null){
                        dto.setCouponName(coupInfoRespDTO.getCoupName());
                    } 
                }
            }
        }
        
        EcpBasePageRespVO<Map> respVO = EcpBasePageRespVO.buildByPageResponseDTO(pageInfo);
        return super.addPageToModel(model, respVO);
    }
    /**
     * 
     * getSelectedGds:(这里用一句话描述这个方法的作用). <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author IME 
     * @param VO
     * @return
     * @throws Exception 
     * @since JDK 1.6
     */
    @RequestMapping(value="/getselectedcp")
    @ResponseBody
    public  EcpBaseResponseVO getSelectedCp(CmsFloorCouponVO VO){
      //查找已经选择的商品
        List<CmsFloorCouponRespDTO> selGdsList = null;
        try {
            selGdsList = this.qrySelectedCp(VO.getFloorId());
        } catch (Exception e) {
            LogUtil.error(MODULE, "查询已选择的优惠券方法异常");
            e.printStackTrace();
        }
        String selGdsStrs = ",";
        if(CollectionUtils.isNotEmpty(selGdsList)){
            for(CmsFloorCouponRespDTO floorCp : selGdsList){
                if(StringUtil.isNotEmpty(floorCp.getCouponId())){
                    selGdsStrs += String.valueOf(floorCp.getCouponId())+",";
                }
            }
        }
        
        EcpBaseResponseVO resultVo = new EcpBaseResponseVO();
        resultVo.setResultMsg(selGdsStrs);
        return resultVo;
    }
    /**
     * 
     * qrySelectedGds:(获取楼层已选择的优惠券). <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author IME 
     * @param floorId
     * @param tabId
     * @return 
     * @since JDK 1.6
     */
    private List<CmsFloorCouponRespDTO> qrySelectedCp(Long floorId){
        LogUtil.info(MODULE, "qrySelectedCp获取楼层已选择的优惠券开始：");
        if(StringUtil.isEmpty(floorId)){//参数为空  则返回空
            return new ArrayList<CmsFloorCouponRespDTO>();
        }
        CmsFloorCouponReqDTO reqDto = new CmsFloorCouponReqDTO();
        reqDto.setFloorId(floorId);
        
        //状态为发布以及未发布 都为已选择的
        reqDto.setStatusSet(new HashSet<String>(Arrays.asList(ParamStatus.CMS_PARAMSTATUS_0,ParamStatus.CMS_PARAMSTATUS_1)));
        return this.cmsFloorCouponRSV.queryCmsFloorCouponList(reqDto);
    }

}
