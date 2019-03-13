package com.zengshi.ecp.order.service.busi.interfaces;

import java.util.List;

import com.zengshi.ecp.order.dao.model.OrdBackCoupon;
import com.zengshi.ecp.order.dubbo.dto.RBackCouponResp;
import com.zengshi.ecp.order.dubbo.dto.ROrderBackReq;
import com.zengshi.ecp.server.front.exception.BusinessException;

public interface IOrdBackCouponSV {
    public void saveOrdBackCoupon(OrdBackCoupon ordBackCoupon);
    
    public List<RBackCouponResp> queryOrdBackCoupon(ROrderBackReq rOrderBackReq);
    /**
     * queryBackPayInfo:按订单号和申请单号删除数据. <br/>
     * @author cbl
     * @param rOrderBackReq
     * @return
     * @throws BusinessException
     * @since JDK 1.6
     */
    public void deleteOrdBackCouponByBackId(ROrderBackReq rOrderBackReq) throws BusinessException;
}

