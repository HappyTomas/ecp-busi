package com.zengshi.ecp.im.dubbo.impl;

import com.zengshi.ecp.im.dubbo.dto.CustServSatisfyReqDTO;
import com.zengshi.ecp.im.dubbo.dto.CustServSatisfyResDTO;
import com.zengshi.ecp.im.dubbo.interfaces.ISatisfyEvaluateRSV;
import com.zengshi.ecp.im.service.busi.interfaces.ISatisfyEvaluateSV;
import com.zengshi.ecp.server.front.exception.BusinessException;

import javax.annotation.Resource;

/**
 * Created by zhangys on 16/11/9.
 */
public class SatisfyEvaluateRSVImpl implements ISatisfyEvaluateRSV {

    @Resource
    private ISatisfyEvaluateSV satisfyEvaluateSV;


    @Override
    public CustServSatisfyResDTO addSatisfyEvaluate( CustServSatisfyReqDTO reqDTO) throws
        BusinessException {

        return satisfyEvaluateSV.addSatisfyEvaluate(reqDTO);
    }

    @Override public CustServSatisfyResDTO qrySatisfyEvaluate(
        CustServSatisfyReqDTO reqDTO) throws BusinessException {
        return satisfyEvaluateSV.qrySatisfyEvaluate(reqDTO);
    }
}
