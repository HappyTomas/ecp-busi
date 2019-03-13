package com.zengshi.ecp.coupon.service.common.interfaces;

import java.util.List;
import com.zengshi.ecp.coupon.dubbo.dto.req.CoupParamReqDTO;
import com.zengshi.ecp.coupon.dubbo.dto.resp.CoupParamRespDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;

/**
 * Title: AI-ECP <br>
 * Description: <br>
 * Date: 2015-10-4 <br>
 * Copyright (c) 2015 AI <br>
 * 
 * @author huangjx
 */
public interface ICoupParamSV {
    /**
     * 优惠券编码 查询
     * @param ruleCode
     * @return
     * @throws BusinessException
     * @author huangjx
     */
    public CoupParamRespDTO queryCoupParamByCode(String ruleCode)
            throws BusinessException;
    /**
     * 优惠券参数查询 编码必传
     * 
     * @param coupParamReqDTO
     * @return
     * @throws BusinessException
     * @author huangjx
     */
    public CoupParamRespDTO queryCoupParamById(CoupParamReqDTO coupParamReqDTO)
            throws BusinessException;

    /**
     * 优惠券参数列表查询
     * 
     * @param coupParamReqDTO
     * @return
     * @throws BusinessException
     * @author huangjx
     */
    public List<CoupParamRespDTO> queryCoupParamList(CoupParamReqDTO coupParamReqDTO)
            throws BusinessException;
}
