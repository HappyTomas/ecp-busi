/**
 * 
 */
package com.zengshi.ecp.order.service.busi.interfaces;

import java.util.List;

import com.zengshi.ecp.order.dao.model.OrdEntityImportGroup;
import com.zengshi.ecp.order.dao.model.OrdSub;
import com.zengshi.ecp.order.dubbo.dto.*;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.server.service.interfaces.IGeneralSQLSV;

/**
 * @author linwei
 *
 */
public interface IOrdSubSV  extends IGeneralSQLSV{
    
    
    /** 
     * saveOrdSub:保存ordsub对象信息. <br/> 
     * @author cbl 
     * @param ordSub 
     * @since JDK 1.6 
     */ 
    public void saveOrdSub(OrdSub ordSub,String payType);
    
    /** 
     * queryOrderSubInfoByOrderId:(根据订单号查询子订单明细). <br/> 
     * @author cbl 
     * @param orderID
     * @return 
     * @since JDK 1.6 
     */ 
    public List<OrdSub> queryOrderSubByOrderId(String orderID);
    
    /** 
     * listOrderSubInfoByOrderId:订单发货查询子订单相关信息. <br/> 
     * @author cbl 
     * @param sQueryOrderSubRequest
     * @return 
     * @since JDK 1.6 
     */ 
    public List<RShowOrdSubResponse> listOrderSubInfoByOrderId(RShowOrdSubRequest sQueryOrderSubRequest);
    
    /** 
     * findByOrderSubId:根据子订单号查询子订单信息. <br/> 
     * @author cbl 
     * @param orderSubId
     * @return 
     * @since JDK 1.6 
     */ 
    public OrdSub findByOrderSubId(SBaseAndSubInfo sOrderAOrderSubInfo);
    
    /** 
     * updateOrderSubAmount:更新子订单发货数量，剩余发货数量. <br/> 
     * @author cbl 
     * @param rConfirmSubInfos 
     * @since JDK 1.6 
     */ 
    public void updateOrderSubDeliveryInfo(SBaseAndStatusInfo sOrderStatusInfo,List<RConfirmSubInfo> rConfirmSubInfos);
    
    /** 
     * queryOrderByOrderIdPage:订单发货查询子订单相关信息. <br/> 
     * @author cbl 
     * @param rShowOrdSubRequest
     * @return 
     * @since JDK 1.6 
     */ 
    public PageResponseDTO<RShowOrdSubResponse> queryOrderByOrderIdPage(RShowOrdSubRequest rShowOrdSubRequest);

    
    /**
     * 
     * saveOrdSubInfo:保存订单字表信息. <br/> 
     * @author linwei 
     * @param rOrdCartItemRequest 
     * @since JDK 1.6
     */
    public void saveOrdSubInfo(RSumbitSubRequest rSumbitSubRequest,RSumbitMainsRequest rSumbitMainsRequest,ROrdMainResponse rOrdMainResponse);

    
    
    /** 
     * countByOrdSubId:根据子订单号查询记录数. <br/> 
     * @author cbl 
     * @return 
     * @since JDK 1.6 
     */ 
    public Long queryCountByOrdSubId(OrdEntityImportGroup ordEntityImportGroup);

    
    /** 
     * updateStatusByOrderId:更新订单状态 <br/> 
     * @author cbl 
     * @param SBaseAndStatusInfo 
     * @since JDK 1.6 
     */ 
    public void updateStatusByOrderId(SBaseAndStatusInfo sBaseAndStatusInfo);
    
    /** 
     * updateStatusInfo:更新订单状态. <br/> 
     * 购买数字图书馆充值卡支付成功后更新状态
     * @param sBaseAndStatusInfo 
     * @since JDK 1.6 
     */ 
    public void updateStatusInfo(RPreOrdSubResponse ordsub,SBaseAndStatusInfo sBaseAndStatusInfo);
    
    /** 
     * queryOrderDetailsSub:订单详情查询子订单表相关字段. <br/> 
     * @author cbl 
     * @param rOrderDetailsRequest
     * @return 
     * @since JDK 1.6 
     */ 
    public List<SOrderDetailsSub> queryOrderDetailsSub(String orderId);
    
    /** 
     * queryOrderDetailsSubBySubId:子订单详情查询. <br/> 
     * @author cbl 
     * @param sOrderAOrderSubInfo
     * @return 
     * @since JDK 1.6 
     */ 
    public SOrderDetailsSub queryOrderDetailsSubBySubId(SBaseAndSubInfo sOrderAOrderSubInfo);
    
    /** 
     * queryReceiptInfo:根据订单号查询收货明细. <br/> 
     * @author cbl
     * @param rOrdReceiptRequest
     * @return 
     * @since JDK 1.6 
     */ 
    public List<SReceiptInfo> queryReceiptInfo(ROrdReceiptRequest rOrdReceiptRequest);
    
    /** 
     * queryCountRemainAmount:统计订单的剩余发货数量. <br/> 
     * @author cbl 
     * @param orderId
     * @return 
     * @since JDK 1.6 
     */ 
    public Long queryCountRemainAmount(String orderId);
    /** 
     * querySumDiscountMoney:统计订单的总的实际明细金额. <br/> 
     * @author linwei
     * @param orderId
     * @return 
     * @since JDK 1.6 
     */ 
    public Long querySumDiscountMoney(String orderId);    
    /**
     * 
     * createOrdMainId:(生成子订单id的服务). <br/> 
     * @author linwei 
     * @return 
     * @since JDK 1.6
     */
    public String createOrdSubId();
    /** 
     * queryOrderDetailsSub:订单详情查询子订单表相关字段. <br/> 
     * @author cbl 
     * @param rOrderDetailsRequest
     * @return 
     * @since JDK 1.6 
     */ 
    public List<RPreOrdSubResponse> findOrdSubByOrderId(String orderId);
    
    /** 
     * queryEvaluationWait:待评价子订单查询. <br/> 
     * @author cbl 
     * @param rOrdEvaluationRequest
     * @return 
     * @since JDK 1.6 
     */ 
    public PageResponseDTO<ROrdEvaluationResponse> queryEvaluationWait(ROrdEvaluationRequest rOrdEvaluationRequest);
    /** 
     * queryEvaluationWaitCount:待评价子订单数量. <br/> 
     * @author cbl 
     * @param rOrdEvaluationRequest
     * @return 
     * @since JDK 1.6 
     */ 
    public Long queryEvaluationWaitCount(ROrdEvaluationRequest rOrdEvaluationRequest);
    /** 
     * updateEvaluated:买家评价回调. <br/> 
     * @author cbl 
     * @param rOrdEvaluatedRequests 
     * @since JDK 1.6 
     */ 
    public void updateEvaluated(List<ROrdEvaluatedRequest> rOrdEvaluatedRequests);
    
    
    /** 
     * queryOrderByShopIdPage:待发货，已发货订单查询. <br/> 
     * @author cbl 
     * @param rDelyOrderRequest
     * @return 
     * @since JDK 1.6 
     */ 
    public PageResponseDTO<SOrderIdx> queryOrderByShopIdPage(RQueryOrderRequest rQueryOrderRequest);
    
    /** 
     * queryOrderSubByStaffId:根据买家ID查询子订单信息. <br/> 
     * @author cbl 
     * @param rQueryOrderRequest
     * @return 
     * @since JDK 1.6 
     */ 
    public PageResponseDTO<RShowOrdSubResponse> queryOrderSubByStaffId(RQueryOrderRequest rQueryOrderRequest);

    /**
     *
     * queryGoodSaleInfo:“商品销售明细”列表. <br/>
     * TODO(这里描述这个方法适用条件 – 可选).<br/>
     * TODO(这里描述这个方法的执行流程 – 可选).<br/>
     * TODO(这里描述这个方法的使用方法 – 可选).<br/>
     * TODO(这里描述这个方法的注意事项 – 可选).<br/>
     *
     * @author wangxq
     * @param rGoodSaleRequest
     * @since JDK 1.6
     */
    public PageResponseDTO<RGoodSaleResponse> queryGoodSaleInfo(RGoodSaleRequest rGoodSaleRequest);

    /**
     *
     * queryGoodSaleInfo:“商品销售明细”列表. <br/>
     * TODO(这里描述这个方法适用条件 – 可选).<br/>
     * TODO(这里描述这个方法的执行流程 – 可选).<br/>
     * TODO(这里描述这个方法的使用方法 – 可选).<br/>
     * TODO(这里描述这个方法的注意事项 – 可选).<br/>
     *
     * @author wangxq
     * @param rGoodSaleRequest
     * @since JDK 1.6
     */
    public RGoodSaleSumResp queryGoodSaleSum(RGoodSaleRequest rGoodSaleRequest);


    
    /**
     * 
     * @param rGoodSaleRequest查询销售明细信息
     * @return
     */
    public List<RGoodSaleResponse> queryGoodSaleExport(RGoodSaleRequest rGoodSaleRequest); 


    /**
     * queryOrderSubByStaffId:根据买家ID和skuId查询子订单信息. <br/>
     * @author cbl
     * @param rOrdSubStaffIdxReq
     * @return
     * @since JDK 1.6
     */
    public List<ROrdSubStaffIdxResp> queryOrderSubByStaffIdAndSkuid(ROrdSubStaffIdxReq rOrdSubStaffIdxReq) throws BusinessException;

    /**
     * 
     * querySumOrderAmount:(查询订单订购数量). <br/> 
     * @author lwy 
     * @param orderId
     * @return 
     * @since JDK 1.6
     */
    public Long querySumOrderAmount(String orderId);
    
    /** 
     * updateOrderSubAmount:更新子订单已退货数量. <br/> 
     * @author cbl 
     * @param rConfirmSubInfos 
     * @since JDK 1.6 
     */ 
    public void updateOrderSubHasBackNum(OrdSub ordSub);

    /**
     * 销售明细导出重写
     * @param rGoodSaleRequest
     * @return
     */
    public List<RGoodSaleResponse> queryGoodSaleExportNew(RGoodSaleRequest rGoodSaleRequest);
}
