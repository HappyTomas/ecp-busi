package com.zengshi.ecp.coupon.dubbo.interfaces;

import java.util.List;

import com.zengshi.ecp.coupon.dubbo.dto.req.CoupParamReqDTO;
import com.zengshi.ecp.coupon.dubbo.dto.resp.CoupParamRespDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;

/**
 * Title: AI-ECP <br>
 * Description: <br>
 * Date: 2015-10-1 <br>
 * Copyright (c) 2015 AI <br>
 * 
 * @author huangjx
 */
public interface ICoupParamRSV {
    /**
     *  优惠券参数查询  编码必传
     * @param coupTypeReqDTO
     * @return
     * @throws BusinessException
     * @author huangjx
     */
    public CoupParamRespDTO queryCoupParam(CoupParamReqDTO coupParamReqDTO)
            throws BusinessException;
    /**
     * 优惠券参数 查询列表
     * @param coupTypeReqDTO
     * @return
     * @throws BusinessException
     * @author huangjx
     */
    public List<CoupParamRespDTO> queryCoupParamList(CoupParamReqDTO coupParamReqDTO) throws BusinessException;
}
