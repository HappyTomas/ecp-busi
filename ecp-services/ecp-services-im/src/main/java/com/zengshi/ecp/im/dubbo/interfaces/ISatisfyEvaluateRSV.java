package com.zengshi.ecp.im.dubbo.interfaces;

import com.zengshi.ecp.im.dubbo.dto.CustServSatisfyReqDTO;
import com.zengshi.ecp.im.dubbo.dto.CustServSatisfyResDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;

/**
 * Created by zhangys on 16/11/9.
 */
public interface ISatisfyEvaluateRSV {

    CustServSatisfyResDTO addSatisfyEvaluate(CustServSatisfyReqDTO reqDTO) throws
        BusinessException;

    CustServSatisfyResDTO qrySatisfyEvaluate(CustServSatisfyReqDTO reqDTO) throws
        BusinessException;
}
