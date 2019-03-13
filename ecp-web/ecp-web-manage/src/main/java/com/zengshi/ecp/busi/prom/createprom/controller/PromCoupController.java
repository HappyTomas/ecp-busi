package com.zengshi.ecp.busi.prom.createprom.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zengshi.ecp.base.controller.EcpBaseController;
import com.zengshi.ecp.base.vo.EcpBaseResponseVO;
import com.zengshi.ecp.busi.prom.createprom.vo.CoupInfoVO;
import com.zengshi.ecp.coupon.dubbo.dto.req.CoupInfoReqDTO;
import com.zengshi.ecp.coupon.dubbo.dto.resp.CoupInfoRespDTO;
import com.zengshi.ecp.coupon.dubbo.interfaces.ICoupRSV;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.StringUtil;

/**
 * Title: ECP <br>
 * Project Name:ecp-web-manage <br>
 * Description: <br>
 * Date:2016-1-6下午2:51:38  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author huangjx
 * @version  
 * @since JDK 1.7 
 */

@Controller
@RequestMapping(value="/promcoup")
public class PromCoupController extends EcpBaseController {
    
    private static String MODULE = PromCoupController.class.getName();
      
    @Resource
    private ICoupRSV coupRSV;
    /**
     * 优惠券选择 按钮 弹出页面
     * @return
     * @throws Exception
     * @author huangjx
     */
    @RequestMapping("/select")
    public String select(Model model,Long shopId) throws Exception { 
        model.addAttribute("shopId", shopId);
        return "/prom/createprom/coupselect/coup-select-grid";
    }
    /**
     * 优惠券基础信息
     * @param coupId
     * @return
     * @author huangjx
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


