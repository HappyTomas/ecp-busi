package com.zengshi.ecp.order.dubbo.interfaces;

import com.zengshi.ecp.general.order.dto.ROrdCartsChkResponse;
import com.zengshi.ecp.order.dubbo.dto.RBackApplyOrdResp;
import com.zengshi.ecp.order.dubbo.dto.RBackApplyReq;
import com.zengshi.ecp.order.dubbo.dto.RBackApplyResp;
import com.zengshi.ecp.order.dubbo.dto.RBackConfirmReq;
import com.zengshi.ecp.order.dubbo.dto.RBackPayInfoResp;
import com.zengshi.ecp.order.dubbo.dto.RBackReviewReq;
import com.zengshi.ecp.order.dubbo.dto.RBackReviewResp;
import com.zengshi.ecp.order.dubbo.dto.ROrderBackDetailResp;
import com.zengshi.ecp.order.dubbo.dto.ROrderBackReq;
import com.zengshi.ecp.order.dubbo.dto.ROrderBackResp;
import com.zengshi.ecp.order.dubbo.dto.ROrderDetailsRequest;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;

public interface IOrdBackGdsRSV {
    
    /** 
     * queryBackOrder:卖家退货订单查询. <br/> 
     * @author cbl 
     * @param rOrderBackReq
     * @return
     * @throws BusinessException 
     * @since JDK 1.6 
     */ 
    public PageResponseDTO<ROrderBackResp> queryBackGdsByShop(ROrderBackReq rOrderBackReq) throws BusinessException;
    
    /** 
     * queryBackGdsByStaff:买家退货查询. <br/> 
     * @author cbl 
     * @param rOrderBackReq
     * @return
     * @throws BusinessException 
     * @since JDK 1.6 
     */ 
    public PageResponseDTO<ROrderBackResp> queryBackGdsByStaff(ROrderBackReq rOrderBackReq) throws BusinessException;
    
    /** 
     * queryBackOrderSub:查询订单明细. <br/> 
     * @author cbl 
     * @param rOrderBackReq
     * @return
     * @throws BusinessException 
     * @since JDK 1.6 
     */ 
    public RBackApplyOrdResp queryBackOrderSub(ROrderBackReq rOrderBackReq) throws BusinessException;
    
    /** 
     * queryBackGdsReview:退货申请订单信息查询. <br/> 
     * @author cbl 
     * @param rOrderBackReq
     * @return
     * @throws BusinessException 
     * @since JDK 1.6 
     */ 
    public RBackReviewResp queryBackGdsReview(ROrderBackReq rOrderBackReq) throws BusinessException;
    
    /** 
     * queryBackGdsDetails:退货申请单详情. <br/> 
     * @author cbl 
     * @throws BusinessException 
     * @since JDK 1.6 
     */ 
    public ROrderBackDetailResp queryBackGdsDetails(ROrderBackReq rOrderBackReq) throws BusinessException;
    
    /** 
     * saveBackGdsApply:退货申请. <br/> 
     * @author cbl 
     * @throws BusinessException 
     * @since JDK 1.6 
     */ 
    public void saveBackGdsApply(RBackApplyReq rBackApplyReq) throws BusinessException;
    
    /** 
     * saveBackGdsSend:买家确认发货. <br/> 
     * @author cbl 
     * @throws BusinessException 
     * @since JDK 1.6 
     */ 
    public void saveBackGdsSend(RBackConfirmReq rBackConfirmReq) throws BusinessException;
    
    /** 
     * SaveBackGdsReview:退货审核. <br/> 
     * @author cbl 
     * @param rBackGdsReviewReq
     * @throws BusinessException 
     * @since JDK 1.6 
     */ 
    public void saveBackGdsReview(RBackReviewReq rBackReviewReq) throws BusinessException;
    
    /** 
     * SaveBackGdsReceipt:确认收货. <br/> 
     * @author cbl 
     * @throws BusinessException 
     * @since JDK 1.6 
     */ 
    public void saveBackGdsReceipt(RBackConfirmReq rBackConfirmReq) throws BusinessException;
    
    /** 
     * SaveBackGdsPayed:确认付款. <br/> 
     * @author cbl 
     * @throws BusinessException 
     * @since JDK 1.6 
     */ 
    public void saveBackGdsPayed(RBackConfirmReq rBackConfirmReq) throws BusinessException;
    
    /** 
     * queryOrdOperChk:判断订单能否执行某个业务操作. <br/> 
     * @author cbl 
     * @param info
     * @return
     * @throws BusinessException 
     * @since JDK 1.6 
     */ 
    public ROrdCartsChkResponse queryOrdOperChk(ROrderDetailsRequest info) throws BusinessException;
    
    /** 
     * queryBackPayInfo:退款支付信息查询. <br/> 
     * @author cbl 
     * @param rOrderBackReq
     * @return
     * @throws BusinessException 
     * @since JDK 1.6 
     */ 
    public RBackPayInfoResp queryBackPayInfo(ROrderBackReq rOrderBackReq) throws BusinessException;
    
    /**
     * 
     * querynotInAuditTradeCheckOrders:获取已经退款成功但是对账文件中没有的线上退款订单. <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author weijq
     * @param rQueryOrderRequest
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public PageResponseDTO<RBackApplyResp> querynotInAuditTradeCheckOrders(ROrderBackReq orderBackReq) 
            throws BusinessException;
    
    /** 
     * queryOrdBackApplyByBackId:根据申请查询记录信息. <br/> 
     * @author panjs 
     * @param backId
     * @return 
     * @since JDK 1.6 
     */ 
    public RBackApplyResp queryOrdBackApplyByBackId(Long backId) throws BusinessException;

}

