package com.zengshi.ecp.order.dubbo.interfaces;

import com.zengshi.ecp.order.dubbo.dto.RBackApplyReq;
import com.zengshi.ecp.order.dubbo.dto.RBackConfirmReq;
import com.zengshi.ecp.order.dubbo.dto.RBackPayInfoResp;
import com.zengshi.ecp.order.dubbo.dto.RBackReviewReq;
import com.zengshi.ecp.order.dubbo.dto.RBackReviewResp;
import com.zengshi.ecp.order.dubbo.dto.ROrderBackDetailResp;
import com.zengshi.ecp.order.dubbo.dto.ROrderBackReq;
import com.zengshi.ecp.order.dubbo.dto.ROrderBackResp;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;

public interface IOrdBackMoneyRSV {

    /** 
     * saveBackMoneyApply:退款申请. <br/> 
     * @author cbl 
     * @param rBackApplyReq
     * @throws BusinessException 
     * @since JDK 1.6 
     */ 
    public void saveBackMoneyApply(RBackApplyReq rBackApplyReq) throws BusinessException;
    
    /** 
     * saveBackMoneyReview:退款审核. <br/> 
     * @author cbl 
     * @param rBackGdsReviewReq
     * @throws BusinessException 
     * @since JDK 1.6 
     */ 
    public void saveBackMoneyReview(RBackReviewReq rBackReviewReq) throws BusinessException;
    /** 
     * saveBackGdsPayed:确认退款. <br/> 
     * @author cbl 
     * @param rBackConfirmReq
     * @throws BusinessException 
     * @since JDK 1.6 
     */ 
    public void saveBackGdsPayed(RBackConfirmReq rBackConfirmReq) throws BusinessException;
    
    /** 
     * queryBackMoneyByStaff:买家退款查询. <br/> 
     * @author cbl 
     * @param rOrderBackReq
     * @return
     * @throws BusinessException 
     * @since JDK 1.6 
     */ 
    public PageResponseDTO<ROrderBackResp> queryBackMoneyByStaff(ROrderBackReq rOrderBackReq) throws BusinessException;
    
    /** 
     * queryBackGdsByShop:卖家退款查询. <br/> 
     * @author cbl 
     * @param rOrderBackReq
     * @return
     * @throws BusinessException 
     * @since JDK 1.6 
     */ 
    public PageResponseDTO<ROrderBackResp> queryBackMoneyByShop(ROrderBackReq rOrderBackReq) throws BusinessException;
    
    /** 
     * queryBackMoneyDetails:退款详情. <br/> 
     * @author cbl 
     * @param rOrderBackReq
     * @return
     * @throws BusinessException 
     * @since JDK 1.6 
     */ 
    public ROrderBackDetailResp queryBackMoneyDetails(ROrderBackReq rOrderBackReq) throws BusinessException;
    
    /** 
     * queryBackGdsReview:退款申请订单信息查询. <br/> 
     * @author cbl 
     * @param rOrderBackReq
     * @return
     * @throws BusinessException 
     * @since JDK 1.6 
     */ 
    public RBackReviewResp queryBackMoneyReview(ROrderBackReq rOrderBackReq) throws BusinessException;
    
    /** 
     * queryBackPayInfo:退款支付信息查询. <br/> 
     * @author cbl 
     * @param rOrderBackReq
     * @return
     * @throws BusinessException 
     * @since JDK 1.6 
     */ 
    public RBackPayInfoResp queryBackPayInfo(ROrderBackReq rOrderBackReq) throws BusinessException;
}

