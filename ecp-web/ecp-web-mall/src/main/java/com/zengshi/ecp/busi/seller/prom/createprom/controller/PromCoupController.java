package com.zengshi.ecp.busi.seller.prom.createprom.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zengshi.ecp.base.controller.EcpBaseController;
import com.zengshi.ecp.base.vo.EcpBaseResponseVO;
import com.zengshi.ecp.busi.seller.prom.createprom.vo.CoupInfoVO;
import com.zengshi.ecp.busi.seller.prom.createprom.vo.QueryCouponInfoVO;
import com.zengshi.ecp.coupon.dubbo.dto.req.CoupInfoReqDTO;
import com.zengshi.ecp.coupon.dubbo.dto.resp.CoupInfoRespDTO;
import com.zengshi.ecp.coupon.dubbo.interfaces.ICoupRSV;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.ObjectCopyUtil;
import com.zengshi.paas.utils.StringUtil;

/**
 * Title: ECP <br>
 * Project Name:ecp-web-mall <br>
 * Description: <br>
 * Date:2016-4-20下午7:21:38  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author lisp
 * @version  
 * @since JDK 1.7 
 */
@Controller
@RequestMapping(value="/seller/promcoup")
public class PromCoupController extends EcpBaseController {
	
    private static String MODULE = PromCoupController.class.getName();
    
	  @Resource
	    private ICoupRSV coupRSV;
	
	
	 /**
     * 优惠券选择 按钮 弹出页面
     * @return
     * @throws Exception
     * @author lisp
     */
    @RequestMapping()
    public String select(Model model,Long shopId, QueryCouponInfoVO vo) throws Exception { 
        model.addAttribute("shopId", shopId);
       return "/seller/prom/coupon/coup-select";
    }
    
    
    
    /**
	 * 优惠券 查询和分页
	 * 
	 * @return
	 * @throws Exception
	 * @author lisp
	 */
	@RequestMapping(value = "/couponlist")
	public String couponlist(Model model ,@ModelAttribute("queryCouponInfoVO") QueryCouponInfoVO queryCouponInfoVO)
			throws BusinessException {
		  // 后场服务所需要的DTO；
        CoupInfoReqDTO queryDTO = queryCouponInfoVO.toBaseInfo(CoupInfoReqDTO.class);
        // 组织参数
        ObjectCopyUtil.copyObjValue(queryCouponInfoVO, queryDTO, "", false);
        // 调用服务
         PageResponseDTO<CoupInfoRespDTO> couponPage = coupRSV.queryCoupInfoPage(queryDTO);
         model.addAttribute("coupPage",couponPage );
		return "/seller/prom/coupon/coupon-list";
	}
    /**
     * 优惠券基础信息
     * @param coupId
     * @return
     * @author lisp
     */
    @RequestMapping(value="/coupinfo")
    @ResponseBody
    public CoupInfoVO coupInfo(@RequestParam("coupId")String coupId){
        
        CoupInfoVO vo = new CoupInfoVO();
        try{
           if(StringUtil.isNotEmpty(coupId)){
               CoupInfoReqDTO dto=new CoupInfoReqDTO();
               dto.setId(Long.valueOf(coupId));
               CoupInfoRespDTO respDTO= coupRSV.queryCoupInfo(dto);
               vo.setCoupName(respDTO.getCoupName());
               vo.setCoupTypeId(respDTO.getCoupTypeId());
               if(StringUtil.isNotEmpty(respDTO.getCoupValue())){
                   vo.setCoupValue(Long.valueOf(respDTO.getCoupValue()));
               }
               vo.setId(respDTO.getId());
           }
            vo.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_SUCCESS);
        }catch(BusinessException e){
            LogUtil.error(MODULE, "报错", e);
            vo.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_EXCEPTION);
            vo.setResultMsg(e.getMessage());
        }
        return vo;
    }
}


