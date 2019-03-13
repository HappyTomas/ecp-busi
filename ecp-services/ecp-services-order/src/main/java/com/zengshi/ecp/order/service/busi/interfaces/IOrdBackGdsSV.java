package com.zengshi.ecp.order.service.busi.interfaces;

import java.util.List;

import com.zengshi.ecp.order.dao.model.OrdBackGds;
import com.zengshi.ecp.order.dubbo.dto.RBackApplyReq;
import com.zengshi.ecp.order.dubbo.dto.RBackApplyResp;
import com.zengshi.ecp.order.dubbo.dto.RBackGdsResp;
import com.zengshi.ecp.order.dubbo.dto.RBackOrdSubReq;
import com.zengshi.ecp.order.dubbo.dto.ROrderBackReq;
import com.zengshi.ecp.server.front.exception.BusinessException;

public interface IOrdBackGdsSV {
    public void saveOrdBackGds(OrdBackGds ordBackGds);
    
    public List<RBackGdsResp> queryBackGdsByBackId(RBackApplyResp rBackApplyResp);
    
    /** 
     * queryBackGds:查询子订单信息. <br/> 
     * @author cbl 
     * @param rOrderBackReq
     * @return 
     * @since JDK 1.6 
     */ 
    public List<RBackGdsResp> queryBackGds(ROrderBackReq rOrderBackReq);
    
    /** 
     * queryBackGdsByOrderSubId:查询子订单是否在退货流程中. <br/> 
     * @author cbl 
     * @param orderSubId
     * @return 
     * @since JDK 1.6 
     */ 
    public List<OrdBackGds> queryBackGdsStatusByOrderSubId(RBackOrdSubReq rBackOrdSubReq);
    
    
    /** 
     * updateOrdBackGds:更新退货退款明细表. <br/> 
     * @author cbl 
     * @param ordBackGds 
     * @since JDK 1.6 
     */ 
    public void updateOrdBackGdsFromInput(OrdBackGds ordBackGds);
    
    /**
     * 
     * queryOrdBackGds:(根据条件查询退货单据，默认返回第一条记录). <br/> 
     * 
     * @author lwy 
     * @param ordBackGds
     * @return 
     * @since JDK 1.6
     */
    public OrdBackGds queryOrdBackGds(OrdBackGds ordBackGds);
    
    /** 
     * queryBackGdsByOrderSubId:查询子订单已退款的明细. <br/> 
     * @author cbl 
     * @param orderSubId
     * @return 
     * @since JDK 1.6 
     */ 
    public List<OrdBackGds> queryHasBackBackGdsByOrderSubId(RBackOrdSubReq rBackOrdSubReq);
   
}

