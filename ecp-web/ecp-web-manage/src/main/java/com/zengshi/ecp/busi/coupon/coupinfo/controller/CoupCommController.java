package com.zengshi.ecp.busi.coupon.coupinfo.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zengshi.ecp.base.controller.EcpBaseController;
import com.zengshi.ecp.base.vo.EcpBaseResponseVO;
import com.zengshi.ecp.busi.coupon.coupinfo.vo.CoupFullLimitVO;
import com.zengshi.ecp.busi.coupon.coupinfo.vo.CoupUseLimitVO;
import com.zengshi.ecp.coupon.dubbo.dto.req.CoupFullLimitReqDTO;
import com.zengshi.ecp.coupon.dubbo.dto.req.CoupUseLimitReqDTO;
import com.zengshi.ecp.coupon.dubbo.dto.resp.CoupFullLimitRespDTO;
import com.zengshi.ecp.coupon.dubbo.dto.resp.CoupUseLimitRespDTO;
import com.zengshi.ecp.coupon.dubbo.interfaces.ICoupLimitRSV;
import com.zengshi.ecp.coupon.dubbo.util.CouponConstants;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.ObjectCopyUtil;

/**
 * Title: ECP <br>
 * Project Name:ecp-web-manage <br>
 * Description: <br>
 * Date:2015-10-26下午2:51:38  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author huangjx
 * @version  
 * @since JDK 1.7 
 */

@Controller
@RequestMapping(value="/coupcomm")
public class CoupCommController extends EcpBaseController {
    
    private static String MODULE = CoupCommController.class.getName();
    
    @Resource
    private ICoupLimitRSV coupLimitRSV;
 
    /**
     * 根据优惠券id获得具体配置信息
     * @param coupId
     * @return
     * @author huangjx
     */
    @RequestMapping(value = "/{type}/{coupId}")
    @ResponseBody
    public EcpBaseResponseVO queryData(@PathVariable("type") String type,@PathVariable("coupId") String coupId) {
        
        EcpBaseResponseVO v=new EcpBaseResponseVO();
        
        switch (type) {
        case "140":
            // 满减规则
            v = this.qryData140(coupId);
            break;
        case "170":
            // 渠道来源规则
            v = this.qryData(coupId, type);
            break;
        case "160":
            // 渠道来源规则
            v = this.qryData(coupId, type);
            break;
        default:
        }
             
          return v;
    }
    /**
     * 满减限制  140
     * @param coupId
     * @return
     * @author huangjx
     */
    private CoupFullLimitVO qryData140(String coupId){

        CoupFullLimitVO v = new CoupFullLimitVO();
        try {
            CoupFullLimitReqDTO dto = new CoupFullLimitReqDTO();
            dto.setCoupId(new Long(coupId));
            dto.setStatus(CouponConstants.CoupSys.status_1);
            List<CoupFullLimitRespDTO> list = coupLimitRSV.queryCoupFull(dto);
            if(!CollectionUtils.isEmpty(list)){
                ObjectCopyUtil.copyObjValue(list.get(0), v, "", false);
            }
            v.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_SUCCESS);

        } catch (BusinessException e) {
            LogUtil.error(MODULE, "报错了啦", e);
            v.setResultMsg(e.getErrorMessage());
            v.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_EXCEPTION);
        }
        return v;
    }
    /**
     * 同个订单使用张数限制限制  160  
     * 渠道使用限制  170
     * @param coupId
     * @return
     * @author huangjx
     */
    private CoupUseLimitVO qryData(String coupId,String useRuleKey){

        CoupUseLimitVO v = new CoupUseLimitVO();
        try {
            CoupUseLimitReqDTO dto = new CoupUseLimitReqDTO();
            dto.setCoupId(new Long(coupId));
            dto.setStatus(CouponConstants.CoupSys.status_1);
            dto.setUseRuleKey(useRuleKey);
            List<CoupUseLimitRespDTO> list = coupLimitRSV.queryCoupUse(dto);
            if(!CollectionUtils.isEmpty(list)){
                ObjectCopyUtil.copyObjValue(list.get(0), v, "", false);
            }
            v.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_SUCCESS);

        } catch (BusinessException e) {
            LogUtil.error(MODULE, "报错了啦", e);
            v.setResultMsg(e.getErrorMessage());
            v.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_EXCEPTION);
        }
        return v;
    }
}


