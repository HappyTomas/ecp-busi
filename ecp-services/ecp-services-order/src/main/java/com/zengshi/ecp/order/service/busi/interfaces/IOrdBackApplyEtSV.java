package com.zengshi.ecp.order.service.busi.interfaces;

import com.zengshi.ecp.order.dubbo.dto.RBackApplyResp;
import com.zengshi.ecp.order.dubbo.dto.RBackReviewResp;
import com.zengshi.ecp.order.dubbo.dto.ROrderBackReq;
import com.zengshi.ecp.server.front.exception.BusinessException;

/**
 * @author: cbl
 * @date: 2016/9/30.
 */
public interface IOrdBackApplyEtSV {

    /**
     * queryOrdBackApply:申请表信息查询. <br/>
     * @author cbl
     * @param rOrderBackReq
     * @return
     * @since JDK 1.6
     */
    public RBackApplyResp queryOrdBackApply(ROrderBackReq rOrderBackReq) throws BusinessException;
    /**
     * queryBackIdInfo:查询退货退款申请信息. <br/>
     * @author cbl
     * @param rOrderBackReq
     * @return
     * @throws BusinessException
     * @since JDK 1.6
     */
    public RBackReviewResp queryBackIdInfo(ROrderBackReq rOrderBackReq) throws BusinessException;
}
