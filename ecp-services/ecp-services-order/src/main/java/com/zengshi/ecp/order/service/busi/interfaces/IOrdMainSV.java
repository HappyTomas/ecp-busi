/**
 * 
 */
package com.zengshi.ecp.order.service.busi.interfaces;

import java.util.List;

import com.zengshi.ecp.general.order.dto.ROrdCartsCommRequest;
import com.zengshi.ecp.order.dao.model.OrdMain;
import com.zengshi.ecp.order.dubbo.dto.RCustomerOrdResponse;
import com.zengshi.ecp.order.dubbo.dto.ROfflinePayRequest;
import com.zengshi.ecp.order.dubbo.dto.ROfflinePayResponse;
import com.zengshi.ecp.order.dubbo.dto.ROrdAddressRequest;
import com.zengshi.ecp.order.dubbo.dto.ROrdAddressResponse;
import com.zengshi.ecp.order.dubbo.dto.ROrdBuyerMsgReq;
import com.zengshi.ecp.order.dubbo.dto.ROrdCountResponse;
import com.zengshi.ecp.order.dubbo.dto.ROrdInvoiceRequest;
import com.zengshi.ecp.order.dubbo.dto.ROrdInvoiceResponse;
import com.zengshi.ecp.order.dubbo.dto.ROrdMainResponse;
import com.zengshi.ecp.order.dubbo.dto.ROrdPayRelReq;
import com.zengshi.ecp.order.dubbo.dto.ROrdReceiptRequest;
import com.zengshi.ecp.order.dubbo.dto.ROrdReturnRefundReq;
import com.zengshi.ecp.order.dubbo.dto.ROrdSellerMsgDTO;
import com.zengshi.ecp.order.dubbo.dto.ROrdSpecialCountResponse;
import com.zengshi.ecp.order.dubbo.dto.ROrderDetailsRequest;
import com.zengshi.ecp.order.dubbo.dto.ROrderDetailsResponse;
import com.zengshi.ecp.order.dubbo.dto.ROrderDetialPrintReq;
import com.zengshi.ecp.order.dubbo.dto.ROrderIdRequest;
import com.zengshi.ecp.order.dubbo.dto.ROrderIdResponse;
import com.zengshi.ecp.order.dubbo.dto.ROrderInvoiceReq;
import com.zengshi.ecp.order.dubbo.dto.ROrderSummaryResponse;
import com.zengshi.ecp.order.dubbo.dto.RPreOrdSubResponse;
import com.zengshi.ecp.order.dubbo.dto.RQueryOrderRequest;
import com.zengshi.ecp.order.dubbo.dto.RShopOrderResponse;
import com.zengshi.ecp.order.dubbo.dto.RSumbitMainsRequest;
import com.zengshi.ecp.order.dubbo.dto.SBaseAndStatusInfo;
import com.zengshi.ecp.order.dubbo.dto.SBaseSplitInfo;
import com.zengshi.ecp.order.dubbo.dto.SOrderDetailsComm;
import com.zengshi.ecp.order.dubbo.dto.SOrderDetailsMain;
import com.zengshi.ecp.order.dubbo.dto.pay.PaySuccInfo;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.server.service.interfaces.IGeneralSQLSV;



/**
 * @author linwei
 *
 */
public interface IOrdMainSV  extends IGeneralSQLSV{
	
	 /** 
     * queryInvoiceByOrderId:订单发票信息查询. <br/> 
     * @author lisp 
     * @param 
     * @return 
     * @since JDK 1.6 
     */ 
	 public SOrderDetailsComm queryInvoiceByOrderId(String orderId);
	 
    /** 
     * saveOrdMain:保存ordMain表信息. <br/> 
     * @author cbl 
     * @param ordMain 
     * @since JDK 1.6 
     */ 
    public void saveOrdMain(OrdMain ordMain);
	
    /** 
     * queryOrderByShopId:待发货订单查询，支持分页查询. <br/> 
     * @author cbl 
     * @param rQueryOrderRequest
     * @return 
     * @since JDK 1.6 
     */ 
    public PageResponseDTO<RShopOrderResponse> queryOrderByShopIdPage(RQueryOrderRequest rQueryOrderRequest);

    /** 
     * queryOrderByOrderId:(根据订单号查询订单详情). <br/> 
     * @author cbl 
     * @param orderId
     * @return 
     * @since JDK 1.6 
     */ 
    public OrdMain queryOrderByOrderId(String orderId);
    
    
    
    
    /** 
     * updateOrderStatus:根据主订单号更新主订单状态，同时更新索引表状态. <br/> 
     * @author cbl 
     * @param status 
     * @since JDK 1.6 
     */ 
    public void updateOrderStatus(SBaseAndStatusInfo sOrderStatusInfo);
    
    /** 
     * updateOrderStatusInfo:根据主订单号更新主订单状态，同时更新索引表状态. <br/> 
     * 购买数字图书馆充值卡支付成功后更新状态
     * @author panjs 
     * @param status 
     * @since JDK 1.6 
     */ 
    public void updateOrderStatusInfo(RPreOrdSubResponse ordsub,SBaseAndStatusInfo sOrderStatusInfo);
    
    
    /** 
     * updateOrderStatus:根据主订单号更新主订单商户订单号，同时更新索引表商户订单号. <br/> 
     * @author panjs 
     * @param status 
     * @since JDK 1.6 
     */ 
    public void updateOrderPayTranNo(ROrdPayRelReq rOrdPayRelReq);

    /**
     * 
     * sumbitOrd:提交订单的方法. <br/> 
     * @author linwei 
     * @param rOrdCartsRequest 
     * @since JDK 1.6
     */
    public List<ROrdMainResponse> saveSubmitOrd(RSumbitMainsRequest rSumbitMainsRequest);

    
    /** 
     * orderReceipt:订单收货确认. <br/> 
     * @author cbl 
     * @param rOrdReceiptRequest 
     * @since JDK 1.6 
     */ 
    public void updateOrderReceipt(ROrdReceiptRequest rOrdReceiptRequest);
    
    /** 
     * queryOrderPage:管理平台查询订单信息. <br/> 
     * @author cbl 
     * @param rQueryOrderRequest
     * @return 
     * @since JDK 1.6 
     */ 
    public PageResponseDTO<RShopOrderResponse> queryOrderManage(RQueryOrderRequest rQueryOrderRequest);
    
    /** 
     * queryOrderDetails:订单详情. <br/> 
     * @author cbl 
     * @param rOrderDetailsRequest
     * @return 
     * @since JDK 1.6 
     */ 
    public ROrderDetailsResponse queryOrderDetails(ROrderDetailsRequest rOrderDetailsRequest);
    
    /** 
     * querOrderOffline:线下支付订单信息查询. <br/> 
     * @author cbl 
     * @param rOfflinePayRequest
     * @return 
     * @since JDK 1.6 
     */ 
    public ROfflinePayResponse querOrderOffline(ROfflinePayRequest rOfflinePayRequest);
    /**
     * 
     * createOrdMainId:(生成主订单id的服务). <br/> 
     * @author linwei 
     * @return 
     * @since JDK 1.6
     */
    public String createOrdMainId();

    /**
     * 
     * findOrdMianByOrdId:根据订单id查询订单主表全部信息. <br/> 
     * @author linwei 
     * @param orderId
     * @return 
     * @since JDK 1.6
     */
    public ROrdMainResponse findOrdMianByOrderId(String orderId);

    /** 
     * queryOrderByStaffIdPage:买家查询订单，支付分页查询. <br/> 
     * @author cbl 
     * @param rCustomerOrdRequest
     * @return 
     * @since JDK 1.6 
     */ 
    public PageResponseDTO<RCustomerOrdResponse> queryOrderByStaffIdPage(RQueryOrderRequest rQueryOrderRequest);
    /**
     * 
     * removeOrdMainInfo:取消订单. <br/> 
     * @author linwei 
     * @param info 
     * @since JDK 1.6
     */
    public void removeOrd(ROrderDetailsRequest info);
    
    /**
     * 
     * updatePaySucc:支付成功处理逻辑. <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author weijq
     * @param paySuccInfo 
     * @since JDK 1.6
     */
    public void savePaySucc(PaySuccInfo paySuccInfo);
    
    /** 
     * queryOrderCountByStaff:查询买家各种状态订单的数量 . <br/> 
     * @author cbl 
     * @param rQueryOrderRequest
     * @return 
     * @since JDK 1.6 
     */ 
    public ROrdCountResponse queryOrderCountByStaff(RQueryOrderRequest rQueryOrderRequest);
    /**
     * queryOrderCountByShop:查询卖家各种状态订单的数量 . <br/>
     * @author cbl
     * @param rQueryOrderRequest
     * @return
     * @since JDK 1.6
     */
    public Long queryOrderCountByShop(RQueryOrderRequest rQueryOrderRequest);
    /** 
     * AssembleParamForProm:组装取消订单调外域入参. <br/> 
     * @author cbl 
     * @param rOrdCartsCommRequest
     * @return 
     * @since JDK 1.6 
     */ 
    public ROrdCartsCommRequest queryAssembleParamForAll(ROrderDetailsRequest rOrderDetailsRequest);
    
    /** 
     * queryOrderDetailsMain:根据订单号查询指定字段. <br/> 
     * @author cbl 
     * @param orderId
     * @return 
     * @since JDK 1.6 
     */ 
    public SOrderDetailsMain queryOrderDetailsMain(String orderId);
    
    /** 
     * queryOrderAddress:订单收货地址查询. <br/> 
     * @author cbl 
     * @param rOrderIdRequest
     * @return
     * @throws BusinessException 
     * @since JDK 1.6 
     */ 
    public ROrdAddressResponse queryOrderAddress(ROrderIdRequest rOrderIdRequest);
    
    /** 
     * updateOrderAddress:订单收货地址修改. <br/> 
     * @author cbl 
     * @param rOrdAddressRequest
     * @throws BusinessException 
     * @since JDK 1.6 
     */ 
    public void updateOrderAddress(ROrdAddressRequest rOrdAddressRequest);
    
    /** 
     * queryOrderInvoiceInfo:查询订单开票信息. <br/> 
     * @author cbl 
     * @param rQueryOrderRequest
     * @return
     * @throws BusinessException 
     * @since JDK 1.6 
     */ 
    public PageResponseDTO<ROrdInvoiceResponse> queryOrderInvoiceInfo(RQueryOrderRequest rQueryOrderRequest) throws BusinessException;
    
    
    /** 
     * updateOrderInvoiceInfo:更新订单开票信息. <br/> 
     * @author cbl 
     * @param rOrdInvoiceRequest
     * @throws BusinessException 
     * @since JDK 1.6 
     */ 
    public void updateOrderInvoiceInfo(ROrdInvoiceRequest rOrdInvoiceRequest) throws BusinessException;
    
    /**
     * 
     * queryNotReceiptOrder:获取已发货超过规定时间而未收货的订单. <br/> 
     * @author weijq
     * @param rOrdReceiptRequest
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public List<ROrdMainResponse> queryNotReceiptOrder(ROrdReceiptRequest rOrdReceiptRequest) throws BusinessException;
    
    public List<ROrdMainResponse> queryNeedCancelOrder(RQueryOrderRequest rQueryOrderRequest) throws BusinessException;
    
    
    /** 
     * queryOrderSummaryData:订单查询汇总信息查询. <br/> 
     * @author cbl 
     * @param queryOrderRequest
     * @throws BusinessException 
     * @since JDK 1.6 
     */ 
    public ROrderSummaryResponse queryOrderSummaryData(RQueryOrderRequest rQueryOrderRequest) throws BusinessException;
    
    /** 
     * queryOrderByIdAndStaff:根据买家ID和订单号查询订单信息. <br/> 
     * @author cbl 
     * @param orderId
     * @return
     * @throws BusinessException 
     * @since JDK 1.6 
     */ 
    public OrdMain queryOrderByIdAndStaff(SBaseSplitInfo sBaseSplitInfo) throws BusinessException;
    
    /**
     * 
     * querynotInAuditTradeCheckOrders:获取已经支付成功但是对账文件中没有的线上支付订单. <br/> 
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
    public PageResponseDTO<ROrdMainResponse> querynotInAuditTradeCheckOrders(RQueryOrderRequest rQueryOrderRequest) throws BusinessException;
    
    /**
     * 
     * updateIsInAuditFile:更新订单IS_IN_AUDIT_FILE字段，对账用. <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author weijq
     * @param orderId
     * @param status 
     * @since JDK 1.6
     */
    public int updateIsInAuditFile(String orderId,String status,String payWay);
    
    /** 
     * updateOrderTwoStatusByOrderId:根据订单号更新订单二级信息. <br/> 
     * @author cbl 
     * @param ordMain 
     * @since JDK 1.6 
     */ 
    public void updateOrderTwoStatusByOrderId(OrdMain ordMain,Long operId);
    
    /** 
     * queryOrderId:导入文件查询订单. <br/> 
     * @author cbl 
     * @param rQueryOrderRequest
     * @return
     * @throws BusinessException 
     * @since JDK 1.6 
     */ 
    public PageResponseDTO<ROrderIdResponse> queryOrderId(RQueryOrderRequest rQueryOrderRequest) throws BusinessException;
    /** 
     * queryOrderByIdAndStaff:根据店铺ID和订单号查询订单信息. <br/> 
     * @author cbl 
     * @param orderId
     * @return
     * @throws BusinessException 
     * @since JDK 1.6 
     */ 
    public OrdMain queryOrderByIdAndShop(SBaseSplitInfo sBaseSplitInfo) throws BusinessException;
    
    /** 
     * queryStaffIdByOrderId:根据订单ID查询买家ID. <br/> 
     * @author lcx
     * @param orderId
     * @return
     * @throws BusinessException 
     * @since JDK 1.7 
     */
    public ROrdMainResponse queryStaffIdByOrderId(ROrderDetailsRequest rOrderDetailsRequest) throws BusinessException;
    
    /** 
     * queryOrderByCompensatePage:调整金额判断. <br/> 
     * @author lwy
     * @param rCustomerOrdRequest
     * @return 
     * @since JDK 1.7 
     */
    public boolean checkBackMoney(ROrdReturnRefundReq req);

    /**
     * 条件可以包含订单状态列表查询
     * @param rQueryOrderRequest
     * @return
     */
    public PageResponseDTO<RCustomerOrdResponse> queryOrderSelectiveStatus(RQueryOrderRequest rQueryOrderRequest);

    /**
     * queryOrderId:导出文件查询订单. <br/>
     * @author cbl
     * @param rQueryOrderRequest
     * @return
     * @throws BusinessException
     * @since JDK 1.6
     */
    public List<OrdMain> queryOrderIdByThread(RQueryOrderRequest rQueryOrderRequest) throws BusinessException;

    
    /**
     * 修改订单发票信息
     * @param req
     * @return
     */
    public void updateInvoice(ROrderInvoiceReq req) throws BusinessException ;
    
    /**
     * queryOrderId:修改订单买家留言. <br/>
     * @author lwy
     * @param req
     * @return
     * @throws BusinessException
     * @since JDK 1.6
     */
    public void updateBuyerMsg(ROrdBuyerMsgReq req) throws BusinessException;
    
    /**
	 * 根据订单编号查询需要批量打印销售明细的订单
	 * @param orderIds
	 * @return
	 */
	public List<ROrderDetailsResponse> findPrintOrderDetails(ROrderDetialPrintReq req) throws BusinessException;
	
	/**
	 * 根据用户id查询一定时间范围内的取消订单数量和退款退货数量
	 *
	 * @param rQueryOrderRequest
	 * @return
	 * @throws BusinessException
	 */
	public ROrdSpecialCountResponse findOrdSpecialCount(RQueryOrderRequest rQueryOrderRequest) throws BusinessException;
	
	/**
	 * 根据用户id查询卖家留言
	 *
	 * @param orderId
	 * @return ROrdSellerMsgDTO
	 * @throws BusinessException
	 */
	public ROrdSellerMsgDTO querySellerMsgById(String orderId) throws BusinessException;
	
	/**
	 * 更新卖家留言
	 *
	 * @param rOrdSellerMsgDTO
	 * @return 
	 * @throws BusinessException
	 */
	public void updateSellerMsg(ROrdSellerMsgDTO rOrdSellerMsgDTO) throws BusinessException;
	
}
