package com.zengshi.ecp.order.service.busi.interfaces;

import java.util.List;

import com.zengshi.ecp.order.dao.model.OrdBackApply;
import com.zengshi.ecp.order.dao.model.OrdSub;
import com.zengshi.ecp.order.dubbo.dto.*;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;

public interface IOrdBackApplySV {
    /** 
     * saveOrdBackApply:保存子订单退货申请表. <br/> 
     * @author cbl 
     * @param ordBackApply 
     * @since JDK 1.6 
     */ 
    public Long saveOrdBackApply(OrdBackApply ordBackApply) throws BusinessException;
    
    /** 
     * queryOrdBackApplyByBackId:根据申请查询记录信息. <br/> 
     * @author cbl 
     * @param backId
     * @return
     * @throws BusinessException 
     * @since JDK 1.6 
     */ 
    public OrdBackApply queryOrdBackApplyByBackId(Long backId) throws BusinessException;
    
    
    /**
     * 
     * queryHasBackOrdBackApplyByOrderI:(根据订单号查询已退款成功的记录). <br/> 
     * 
     * @author lwy 
     * @param ordBackApply
     * @return 
     * @since JDK 1.6
     */
    public List<OrdBackApply> queryHasBackOrdBackApplyByOrderID(OrdBackApply ordBackApply);
    
    /** 
     * queryOrdBackApply:申请表信息查询. <br/> 
     * @author cbl 
     * @param rOrderBackReq
     * @return 
     * @since JDK 1.6 
     */ 
    public RBackApplyResp queryOrdBackApply(ROrderBackReq rOrderBackReq) throws BusinessException;
    
    /** 
     * saveBackGdsApply:退货申请. <br/> 
     * @author cbl 
     * @param rBackApplyReq
     * @throws BusinessException 
     * @since JDK 1.6 
     */ 
    public void saveBackGdsApply(RBackApplyReq rBackApplyReq) throws BusinessException;
    
    /** 
     * queryBackGdsByShop:退货卖家查询. <br/> 
     * @author cbl 
     * @param rOrderBackReq
     * @return
     * @throws BusinessException 
     * @since JDK 1.6 
     */ 
    public PageResponseDTO<ROrderBackResp> queryBackGdsByShop(ROrderBackReq rOrderBackReq) throws BusinessException;
    /** 
     * queryBackGdsByStaff:退货买家查询. <br/> 
     * @author cbl 
     * @param rOrderBackReq
     * @return
     * @throws BusinessException 
     * @since JDK 1.6 
     */ 
    public PageResponseDTO<ROrderBackResp> queryBackGdsByStaff(ROrderBackReq rOrderBackReq) throws BusinessException;
    
    /** 
     * queryBackOrderSub:退货明细查询. <br/> 
     * @author cbl 
     * @param rOrderBackReq
     * @return
     * @throws BusinessException 
     * @since JDK 1.6 
     */ 
    public RBackApplyOrdResp queryBackOrderSub(ROrderBackReq rOrderBackReq) throws BusinessException;
    
    
    /** 
     * queryBackIdInfo:查询退货退款申请信息. <br/> 
     * @author cbl 
     * @param rOrderBackReq
     * @return
     * @throws BusinessException 
     * @since JDK 1.6 
     */ 
    public RBackReviewResp queryBackIdInfo(ROrderBackReq rOrderBackReq) throws BusinessException;
    /** 
     * queryBackReview:退款退货订单审核信息查询. <br/> 
     * @author cbl 
     * @param rOrderBackReq
     * @return
     * @throws BusinessException 
     * @since JDK 1.6 
     */ 
    public RBackReviewResp queryBackReview(ROrderBackReq rOrderBackReq) throws BusinessException;
    
    /** 
     * saveBackGdsReview:审核. <br/> 
     * @author cbl 
     * @param rBackGdsReviewReq
     * @throws BusinessException 
     * @since JDK 1.6 
     */ 
    public void saveBackReview(RBackReviewReq rBackGdsReviewReq) throws BusinessException;
    
    /** 
     * saveBackGdsReceipt:确认收货. <br/> 
     * @author cbl 
     * @param rBackConfirmReq
     * @throws BusinessException 
     * @since JDK 1.6 
     */ 
    public void saveBackGdsReceipt(RBackConfirmReq rBackConfirmReq) throws BusinessException;
    
    /** 
     * saveBackGdsPayed:确认付款. <br/> 
     * @author cbl 
     * @param rBackConfirmReq
     * @throws BusinessException 
     * @since JDK 1.6 
     */ 
    public void saveBackGdsPayed(RBackConfirmReq rBackConfirmReq) throws BusinessException;
    
    /** 
     * saveBackGdsSend:确认发货. <br/> 
     * @author cbl 
     * @param rBackConfirmReq
     * @throws BusinessException 
     * @since JDK 1.6 
     */ 
    public void saveBackGdsSend(RBackConfirmReq rBackConfirmReq) throws BusinessException;
    
    /** 
     * updateBackGdsStatus:更新记录信息. <br/> 
     * @author cbl 
     * @param ordBackApply
     * @throws BusinessException 
     * @since JDK 1.6 
     */ 
    public void updateBackApplyFromInput(OrdBackApply ordBackApply) throws BusinessException;
    
    /** 
     * saveBackMoneyApply:退款申请. <br/> 
     * @author cbl 
     * @param rBackApplyReq
     * @throws BusinessException 
     * @since JDK 1.6 
     */ 
    public void saveBackMoneyApply(RBackApplyReq rBackApplyReq) throws BusinessException;
    
    
    /** 
     * queryBackDetails:售后详情查询. <br/> 
     * @author cbl 
     * @param rOrderBackReq
     * @return
     * @throws BusinessException 
     * @since JDK 1.6 
     */ 
    public ROrderBackDetailResp queryBackDetails(ROrderBackReq rOrderBackReq) throws BusinessException;
    
    /** 
     * queryRefundStatus:查询订单是否在退款状态中. <br/> 
     * @author cbl 
     * @param orderId
     * @return 
     * @since JDK 1.6 
     */ 
    public SRefundInfo queryRefundStatus(String orderId);
    
    /** 
     * queryBackGdsStatus:查询订单是否在退货状态中. <br/> 
     * @author cbl 
     * @param orderId
     * @return 
     * @since JDK 1.6 
     */ 
    public SRefundInfo queryBackGdsStatus(String orderId);
    
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
     * queryBackPayInfo:退款支付信息查询. <br/>
     * @author cbl
     * @param rOrderBackReq
     * @return
     * @throws BusinessException
     * @since JDK 1.6
     */
    public RBackApplyInfoResp queryBackShareInfo(ROrderBackReq rOrderBackReq) throws BusinessException;
    
    /**
     * 
     * updateIsInAuditFile:更新订单IS_IN_AUDIT_FILE字段，对账用. <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author weijq
     * @param
     * @param status
     * @return 
     * @since JDK 1.6
     */
    public int updateIsInAuditFile(Long backId,String status);
    
    /**
     * 
     * querynotInAuditTradeCheckOrders:获取已经退款成功但是对账文件中没有的线上退款订单. <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author weijq
     * @param
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public PageResponseDTO<RBackApplyResp> querynotInAuditTradeCheckOrders(ROrderBackReq orderBackReq);

    /**
     * queryBackPayInfo:退货退款解冻 <br/>
     * @author cbl
     * @param
     * @return
     * @throws BusinessException
     * @since JDK 1.6
     */
    public void backRollback(RBackApplyInfoResp rBackApplyInfoResp,String flag) throws BusinessException;
    
    
    /**
     * 计算非最后一次退货的退款比例
     * @return
     */
    public double calCulateScaleApply(OrdSub ordSub,long num)  throws BusinessException ;
}

