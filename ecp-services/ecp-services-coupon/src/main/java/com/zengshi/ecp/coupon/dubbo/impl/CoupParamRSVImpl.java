package com.zengshi.ecp.coupon.dubbo.impl;

import java.util.List;

import javax.annotation.Resource;

import com.zengshi.ecp.coupon.dubbo.dto.req.CoupParamReqDTO;
import com.zengshi.ecp.coupon.dubbo.dto.resp.CoupParamRespDTO;
import com.zengshi.ecp.coupon.dubbo.interfaces.ICoupParamRSV;
import com.zengshi.ecp.coupon.service.common.interfaces.ICoupParamSV;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.paas.utils.StringUtil;

/**
 * Title: AI-ECP <br>
 * Description: <br>
 * Date: 2015-10-1 <br>
 * Copyright (c) 2015 AI <br>
 * 
 * @author huangjx
 */
public class CoupParamRSVImpl implements ICoupParamRSV {

    private static final String MODULE = CoupParamRSVImpl.class.getName();
    
    @Resource
    private ICoupParamSV coupParamSV;
    /**
     *  优惠券参数查询  编码必传
     * @param coupTypeReqDTO
     * @return
     * @throws BusinessException
     * @author huangjx
     */
    public CoupParamRespDTO queryCoupParam(CoupParamReqDTO coupParamReqDTO)
            throws BusinessException{
        
        if(coupParamReqDTO==null){
            throw new BusinessException("coupon.450004");
        }
        if(StringUtil.isEmpty(coupParamReqDTO.getRuleCode())){
            //优惠券参数id为空
            throw new BusinessException("coupon.450005");
        }
        
        return coupParamSV.queryCoupParamById(coupParamReqDTO);
        
    }
    /**
     * 优惠券参数查询
     * @param coupTypeReqDTO
     * @return
     * @throws BusinessException
     * @author huangjx
     */
    public List<CoupParamRespDTO> queryCoupParamList(CoupParamReqDTO coupParamReqDTO) throws BusinessException{
        if(coupParamReqDTO==null){
            throw new BusinessException("coupon.450004");
        }
        return coupParamSV.queryCoupParamList(coupParamReqDTO);
    } 

}
