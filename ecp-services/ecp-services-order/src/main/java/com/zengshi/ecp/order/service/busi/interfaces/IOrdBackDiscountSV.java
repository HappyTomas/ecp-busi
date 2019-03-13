package com.zengshi.ecp.order.service.busi.interfaces;

import java.util.List;

import com.zengshi.ecp.order.dao.model.OrdBackDiscount;
import com.zengshi.ecp.order.dubbo.dto.RBackDiscountReq;
import com.zengshi.ecp.order.dubbo.dto.RBackDiscountResp;
import com.zengshi.ecp.order.dubbo.dto.ROrderBackReq;
import com.zengshi.ecp.server.front.exception.BusinessException;

public interface IOrdBackDiscountSV {
    public void saveOrdBackDiscount(OrdBackDiscount ordBackDiscount);
    
    /** 
     * queryOrdBackDiscount:根据申请单号和订单号查询相关信息. <br/> 
     * @author cbl 
     * @param rOrderBackReq
     * @return 
     * @since JDK 1.6 
     */ 
    public List<RBackDiscountResp> queryOrdBackDiscount(ROrderBackReq rOrderBackReq);

    /**
     * queryOrdBackDiscount:根据申请单号和订单号和其它条件查询相关信息. <br/>
     * @author cbl
     * @param rBackDiscountReq
     * @return
     * @since JDK 1.6
     */
    public List<RBackDiscountResp> queryOrdBackDiscount(RBackDiscountReq rBackDiscountReq);

    /** 
     * queryOrdBackDiscountByOrderId:根据订单号查询相关信息. <br/> 
     * @author cbl 
     * @param rOrderBackReq
     * @return 
     * @since JDK 1.6 
     */ 
    public List<RBackDiscountResp> queryOrdBackDiscountByOrderId(ROrderBackReq rOrderBackReq);


    /**
     * queryBackPayInfo:按订单号和申请单号删除数据. <br/>
     * @author cbl
     * @param rOrderBackReq
     * @return
     * @throws BusinessException
     * @since JDK 1.6
     */
    public void deleteOrdBackDiscountByBackId(ROrderBackReq rOrderBackReq) throws BusinessException;
}

