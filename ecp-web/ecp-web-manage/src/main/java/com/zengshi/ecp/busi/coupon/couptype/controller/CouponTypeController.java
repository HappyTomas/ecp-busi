package com.zengshi.ecp.busi.coupon.couptype.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zengshi.ecp.base.controller.EcpBaseController;
import com.zengshi.ecp.base.vo.EcpBasePageRespVO;
import com.zengshi.ecp.base.vo.EcpBaseResponseVO;
import com.zengshi.ecp.busi.coupon.couptype.vo.CouponTypeVO;
import com.zengshi.ecp.busi.coupon.couptype.vo.QueryCouponTypeVO;
import com.zengshi.ecp.coupon.dubbo.dto.req.CoupTypeReqDTO;
import com.zengshi.ecp.coupon.dubbo.dto.resp.CoupTypeRespDTO;
import com.zengshi.ecp.coupon.dubbo.interfaces.ICoupTypeRSV;
import com.zengshi.ecp.coupon.dubbo.util.CouponConstants;
import com.zengshi.ecp.server.front.dto.BaseParamDTO;
import com.zengshi.ecp.server.front.dto.BaseSysCfgRespDTO;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.server.front.param.IBaseSysCfgRSV;
import com.zengshi.ecp.server.front.util.BaseParamUtil;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.ObjectCopyUtil;
import com.zengshi.paas.utils.StringUtil;
import com.zengshi.butterfly.core.util.DateUtils;
import com.alibaba.fastjson.JSON;

/**
 * Title: ECP <br>
 * Project Name:ecp-web-manage <br>
 * Description: <br>
 * Date:2015-10-1下午2:51:38 <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author huangjx
 * @version
 * @since JDK 1.7
 */

@Controller
@RequestMapping(value = "/coupontype")
public class CouponTypeController extends EcpBaseController {

    @Resource
    private ICoupTypeRSV coupTypeRSV;
    
    @Resource
    private IBaseSysCfgRSV baseSysCfgRSV;
    
    /**
     * 促销类型功能
     */
    private static String MODULE = CouponTypeController.class.getName();

    /**
     * 
     * init:页面初始化
     * 
     * @author huangjx
     * @return
     * @since JDK 1.7
     */
    @RequestMapping()
    public String init(Model model) {
        return "/coupon/type/type-grid";
    }

    /**
     * 列表查询
     * @param model
     * @param vo
     * @return
     * @throws Exception
     * @author huangjx
     */
    @RequestMapping("/grid")
    @ResponseBody
    public Model grid(Model model, QueryCouponTypeVO vo) throws Exception {

        // 后场服务所需要的DTO；
        CoupTypeReqDTO queryDTO = vo.toBaseInfo(CoupTypeReqDTO.class);

        // 组织参数
        ObjectCopyUtil.copyObjValue(vo, queryDTO, "", false);
        
        if(!StringUtil.isEmpty(queryDTO.getCreateTimeEnd())){
            queryDTO.setCreateTimeEnd(DateUtils.addDay(queryDTO.getCreateTimeEnd(), 1));
        }

        // 调用服务
        PageResponseDTO<CoupTypeRespDTO> page = coupTypeRSV.queryCoupTypePage(queryDTO);
        
        // 调用，并结果返回；从后场返回的分页对象，封装为前店所需要的分页对象；
        EcpBasePageRespVO<Map> respVO = EcpBasePageRespVO.buildByPageResponseDTO(page);

        return super.addPageToModel(model, respVO);
    }

    /**
     * 新增、编辑链接页面
     * 
     * @param model
     * @return
     * @author huangjx
     */
    @RequestMapping(value = "/add")
    public String add(Model model) {
    	BaseSysCfgRespDTO baseSysCfgRespDTO = baseSysCfgRSV.queryCfgByCode("COUP_CODE_FLAG");
    	if(StringUtil.isNotEmpty(baseSysCfgRespDTO)){
    		if(baseSysCfgRespDTO.getParaValue().equalsIgnoreCase("1")){
    		model.addAttribute("coup_code", "true");
	    	}
    	}
        model.addAttribute("couponTypeVO", new CouponTypeVO());
        //缓存数据获得
        this.queryCacheParam(model);
        return "/coupon/type/form";
    }
    /**
     * 新增、编辑链接页面
     * 
     * @param model
     * @return
     * @author huangjx
     */
    @RequestMapping(value = "/edit")
    public String edit(Model model, @RequestParam("id") String id) {
        
        CouponTypeVO couponTypeVO=new CouponTypeVO();
    
      //优惠码标识
    	BaseSysCfgRespDTO baseSysCfgRespDTO = baseSysCfgRSV.queryCfgByCode("COUP_CODE_FLAG");
    	if(StringUtil.isNotEmpty(baseSysCfgRespDTO)){
    		if(baseSysCfgRespDTO.getParaValue().equalsIgnoreCase("1")){
    		model.addAttribute("coup_code", "true");
	    	}
    	}
        model.addAttribute("couponTypeVO", couponTypeVO);
        //缓存数据获得
        this.queryCacheParam(model);
        try {
            
            CoupTypeReqDTO queryDTO  =new CoupTypeReqDTO();
            queryDTO.setId(new Long(id));
            
            CoupTypeRespDTO dto=new CoupTypeRespDTO();
            
            dto=coupTypeRSV.queryCoupType(queryDTO);
            
            ObjectCopyUtil.copyObjValue(dto, couponTypeVO, "", false);
            if(!StringUtil.isEmpty(couponTypeVO.getUseRuleCode())){
                Map map1 = (Map<String, Object>) JSON.parse(couponTypeVO.getUseRuleCode());
                couponTypeVO.setUseMap(new HashMap(map1));
            }
            if(!StringUtil.isEmpty(couponTypeVO.getGetRuleCode())){
                Map map1 = (Map<String, Object>) JSON.parse(couponTypeVO.getGetRuleCode());
                couponTypeVO.setReceiveMap(new HashMap(map1));
            }
            
            return "/coupon/type/form";

        } catch (BusinessException e) {
            LogUtil.error(MODULE, "报错了啦", e);
            return "/coupon/type/form";
        }
    }

    /**
     * 保存
     * @param couponTypeVO
     * @return
     * @author huangjx
     */
    @RequestMapping(value = "/save")
    @ResponseBody
    public CouponTypeVO save(@Valid @ModelAttribute("couponTypeVO") CouponTypeVO couponTypeVO) {

        try {
            //验证优惠券类型非空
            if(couponTypeVO==null){
                throw new BusinessException("web.coupon.450021");
            }
            if(StringUtil.isBlank(couponTypeVO.getCoupTypeName())){
                //优惠券类型名称不能为空
                throw new BusinessException("web.coupon.450020");
            }
            CoupTypeReqDTO dto=new CoupTypeReqDTO();
            ObjectCopyUtil.copyObjValue(couponTypeVO, dto, "", false);
            if(!CollectionUtils.isEmpty(couponTypeVO.getUseMap())){
                dto.setUseRuleCode(JSON.toJSON(couponTypeVO.getUseMap()).toString());
            }
            if(!CollectionUtils.isEmpty(couponTypeVO.getReceiveMap())){
                dto.setGetRuleCode(JSON.toJSON(couponTypeVO.getReceiveMap()).toString());
            }
           
            dto.setStatus(CouponConstants.CoupType.STATUS_2);
            coupTypeRSV.saveCoupType(dto);
            
            couponTypeVO.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_SUCCESS);
            
        } catch (BusinessException e) {
            LogUtil.error(MODULE, "报错了啦", e);
            couponTypeVO.setResultMsg(e.getErrorMessage());
            couponTypeVO.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_EXCEPTION);
        }
        return couponTypeVO;
    }

    /**
     * 提交
     * @param couponTypeVO
     * @return
     * @author huangjx
     */
    @RequestMapping(value = "/submit")
    @ResponseBody
    public CouponTypeVO submit(@Valid @ModelAttribute("couponTypeVO") CouponTypeVO couponTypeVO) {

        try {
            //验证优惠券类型非空
            if(couponTypeVO==null){
                throw new BusinessException("web.coupon.450021");
            }
            if(StringUtil.isBlank(couponTypeVO.getCoupTypeName())){
                //优惠券类型名称不能为空
                throw new BusinessException("web.coupon.450020");
            }
            CoupTypeReqDTO dto=new CoupTypeReqDTO();
            ObjectCopyUtil.copyObjValue(couponTypeVO, dto, "", false);
            if(!CollectionUtils.isEmpty(couponTypeVO.getUseMap())){
                dto.setUseRuleCode(JSON.toJSON(couponTypeVO.getUseMap()).toString());
            }
            if(!CollectionUtils.isEmpty(couponTypeVO.getReceiveMap())){
                dto.setGetRuleCode(JSON.toJSON(couponTypeVO.getReceiveMap()).toString());
            }
            dto.setStatus(CouponConstants.CoupType.STATUS_1);
            coupTypeRSV.saveCoupType(dto);
            
            couponTypeVO.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_SUCCESS);
            
        } catch (BusinessException e) {
            LogUtil.error(MODULE, "报错了啦", e);
            couponTypeVO.setResultMsg(e.getErrorMessage());
            couponTypeVO.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_EXCEPTION);
        }
        return couponTypeVO;
    }
    /**
     * 失效
     * 
     * @param model
     * @param id
     * @return
     * @author huangjx
     */
    @RequestMapping(value = "/invalid")
    @ResponseBody
    public EcpBaseResponseVO invalid(Model model, @RequestParam("id")
    String id) {

        EcpBaseResponseVO vo = new EcpBaseResponseVO();
        
        CoupTypeReqDTO dto = new CoupTypeReqDTO();

        try {
            if (StringUtil.isEmpty(id)) {
                throw new BusinessException("web.coupon.450001");
            }
            dto.setId(new Long(id));
            coupTypeRSV.invalidCoupType(dto);

            vo.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_SUCCESS);

        } catch (BusinessException e) {
            LogUtil.error(MODULE, "报错了啦", e);
            vo.setResultMsg(e.getErrorMessage());
            vo.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_EXCEPTION);
        }
        return vo;
    }
    /**
     * 生效
     * 
     * @param model
     * @param id
     * @return
     * @author huangjx
     */
    @RequestMapping(value = "/valid")
    @ResponseBody
    public EcpBaseResponseVO valid(Model model, @RequestParam("id")
    String id) {

        EcpBaseResponseVO vo = new EcpBaseResponseVO();
        
        CoupTypeReqDTO dto = new CoupTypeReqDTO();

        try {
            if (StringUtil.isEmpty(id)) {
                throw new BusinessException("web.coupon.450001");
            }
            dto.setId(new Long(id));
            coupTypeRSV.validCoupType(dto);

            vo.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_SUCCESS);

        } catch (BusinessException e) {
            LogUtil.error(MODULE, "报错了啦", e);
            vo.setResultMsg(e.getErrorMessage());
            vo.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_EXCEPTION);
        }
        return vo;
    }
    /**
     * 获得缓存数据
     * @param model
     * @author huangjx
     */
    private void queryCacheParam(Model model){
        List<BaseParamDTO> param1List= BaseParamUtil.fetchParamList(CouponConstants.CoupKey.COUP_PARAM_1);
        List<BaseParamDTO> param2List= BaseParamUtil.fetchParamList(CouponConstants.CoupKey.COUP_PARAM_2);
        
        model.addAttribute("param1List", param1List);
        model.addAttribute("param2List", param2List);
    }
}
