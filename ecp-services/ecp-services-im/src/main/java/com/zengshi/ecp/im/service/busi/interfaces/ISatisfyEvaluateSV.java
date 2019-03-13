package com.zengshi.ecp.im.service.busi.interfaces;

import com.zengshi.ecp.im.dubbo.dto.CustServSatisfyReqDTO;
import com.zengshi.ecp.im.dubbo.dto.CustServSatisfyResDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;

/**
 * Created by zhangys on 16/11/9.
 */
public interface ISatisfyEvaluateSV {

    CustServSatisfyResDTO addSatisfyEvaluate(CustServSatisfyReqDTO reqDTO) throws
        BusinessException;

    CustServSatisfyResDTO qrySatisfyEvaluate(CustServSatisfyReqDTO reqDTO) throws
        BusinessException;
}
