package com.zengshi.ecp.order.dubbo.interfaces;

import java.util.List;

import com.zengshi.ecp.general.order.dto.ROrdCartsChkResponse;
import com.zengshi.ecp.general.order.dto.ROrdCartsCommRequest;
import com.zengshi.ecp.order.dubbo.dto.ROrdSellerMsgDTO;
import com.zengshi.ecp.order.dubbo.dto.RCustomerOrdResponse;
import com.zengshi.ecp.order.dubbo.dto.RExportExcleResponse;
import com.zengshi.ecp.order.dubbo.dto.ROrdAddressRequest;
import com.zengshi.ecp.order.dubbo.dto.ROrdAddressResponse;
import com.zengshi.ecp.order.dubbo.dto.ROrdBuyerMsgReq;
import com.zengshi.ecp.order.dubbo.dto.ROrdCountResponse;
import com.zengshi.ecp.order.dubbo.dto.ROrdInvoiceRequest;
import com.zengshi.ecp.order.dubbo.dto.ROrdInvoiceResponse;
import com.zengshi.ecp.order.dubbo.dto.ROrdMainResponse;
import com.zengshi.ecp.order.dubbo.dto.ROrdMainsResponse;
import com.zengshi.ecp.order.dubbo.dto.ROrdReturnRefundReq;
import com.zengshi.ecp.order.dubbo.dto.ROrdSpecialCountResponse;
import com.zengshi.ecp.order.dubbo.dto.ROrderDetailsRequest;
import com.zengshi.ecp.order.dubbo.dto.ROrderDetailsResponse;
import com.zengshi.ecp.order.dubbo.dto.ROrderDetialPrintReq;
import com.zengshi.ecp.order.dubbo.dto.ROrderGiftsResponse;
import com.zengshi.ecp.order.dubbo.dto.ROrderIdRequest;
import com.zengshi.ecp.order.dubbo.dto.ROrderInvoiceReq;
import com.zengshi.ecp.order.dubbo.dto.RQueryOrderRequest;
import com.zengshi.ecp.order.dubbo.dto.RShopOrderResponse;
import com.zengshi.ecp.order.dubbo.dto.RSumbitMainsRequest;
import com.zengshi.ecp.order.dubbo.dto.SOrderDetailsComm;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.staff.dubbo.dto.ShopInfoResDTO;

/**
 * Title: ECP <br>
 * Project Name:ecp-services-order <br>
 * Description: <br>
 * Date:2015年8月12日下午8:20:29  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author cbl
 * @version  
 * @since JDK 1.6 
 */  
public interface IOrdMainRSV {
    /** 
     * queryOrderByShopId:待发货订单查询. <br/> 
     * @author cbl 
     * @param sQueryOrderRequest
     * @return 
     * @since JDK 1.6 
     */ 
    public PageResponseDTO<RShopOrderResponse> queryOrderByShopId(RQueryOrderRequest rQueryOrderRequest) throws BusinessException;

    
    /**
     * 
     * sumbitOrd:提交订单的方法. <br/> 
     * @author linwei 
     * @param rOrdCartsRequest 
     * @since JDK 1.6
     */
    public ROrdMainsResponse sumbitOrd(RSumbitMainsRequest info) throws BusinessException;

    /**
     * 
     * removeOrd:取消订单. <br/> 
     * @author linwei 
     * @param info
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public void removeOrd(ROrderDetailsRequest info) throws BusinessException;
 

    
    /** 
     * queryOrderByStaffId:买家订单查询. <br/> 
     * @author cbl 
     * @param rCustomerOrdRequest
     * @return
     * @throws BusinessException 
     * @since JDK 1.6 
     */ 
    public PageResponseDTO<RCustomerOrdResponse> queryOrderByStaffId(RQueryOrderRequest rQueryOrderRequest) throws BusinessException;
    

    /** 
     * queryOrderCount:统计订单各种状态数量. <br/> 
     * @author cbl 
     * @param rQueryOrderRequest
     * @return
     * @throws BusinessException 
     * @since JDK 1.6 
     */ 
    public ROrdCountResponse queryOrderCountByStaff(RQueryOrderRequest rQueryOrderRequest) throws BusinessException;
    
    
    /** 
     * createOrderChk:提交订单检验. <br/> 
     * @author cbl 
     * @param rOrdCartsChkRequest
     * @return
     * @throws BusinessException 
     * @since JDK 1.6 
     */ 
    public ROrdCartsChkResponse submitOrdChk(ROrdCartsCommRequest rOrdCartsCommRequest) throws BusinessException;
    /** 
     * AssembleParamForProm:组装提交订单之前较验入参. <br/> 
     * @author cbl 
     * @param rOrdCartsCommRequest
     * @return 
     * @since JDK 1.6 
     */ 
    public ROrdCartsCommRequest assembleParamForAll(RSumbitMainsRequest rSumbitMainsRequest) throws BusinessException;
    /**
     * 
     * queryOrdOperChk:(判断在某个订单是否能执行某个操作). <br/> 
     * @author linwei 
     * @param info
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public ROrdCartsChkResponse queryOrdOperChk(ROrderDetailsRequest info) throws BusinessException;
   
    
    /** 
     * queryOrderInvoice:订单发票查询. <br/> 
     * @author lisp 
     * @param SOrderDetailsComm
     * @return
     * @throws BusinessException 
     * @since JDK 1.6 
     */ 
    public SOrderDetailsComm queryOrderInvoice(String orderId) throws BusinessException;
    
    /** 
     * queryOrderAddress:订单收货地址查询. <br/> 
     * @author cbl 
     * @param rOrderIdRequest
     * @return
     * @throws BusinessException 
     * @since JDK 1.6 
     */ 
    public ROrdAddressResponse queryOrderAddress(ROrderIdRequest rOrderIdRequest) throws BusinessException;
    
    /** 
     * updateOrderAddress:订单收货地址修改. <br/> 
     * @author cbl 
     * @param rOrdAddressRequest
     * @throws BusinessException 
     * @since JDK 1.6 
     */ 
    public void updateOrderAddress(ROrdAddressRequest rOrdAddressRequest) throws BusinessException;
    
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
     * queryNeedCancelOrder:获取需要取消的订单. <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author weijq
     * @param queryOrderRequest
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public List<ROrdMainResponse> queryNeedCancelOrder(RQueryOrderRequest queryOrderRequest) throws BusinessException;
    /**
     * 
     * queryOrderGift:(查询订单赠品信息). <br/> 
     * @author linwei 
     * @param info
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public ROrderGiftsResponse queryOrderGift(ROrderIdRequest info) throws BusinessException;
    
    /** 
     * exportOrder2Excle:订单导出Excle. <br/> 
     * @author cbl 
     * @param queryOrderRequest
     * @throws BusinessException 
     * @since JDK 1.6 
     */ 
    public RExportExcleResponse exportOrder2Excle(RQueryOrderRequest queryOrderRequest) throws BusinessException;
    
    
    /** 
     * exportOrder2Print:计单导出打印. <br/> 
     * @author cbl 
     * @param queryOrderRequest
     * @return
     * @throws BusinessException 
     * @since JDK 1.6 
     */ 
    public PageResponseDTO<ROrderDetailsResponse> exportOrder2Print(RQueryOrderRequest queryOrderRequest) throws BusinessException;

    /**
     * exportOrderBarCode:计单条码打印. <br/>
     * @author wxq
     * @param queryOrderRequest
     * @return
     * @throws BusinessException
     * @since JDK 1.6
     */
    public RExportExcleResponse exportOrderBarCode(RQueryOrderRequest queryOrderRequest) throws BusinessException;

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
    public PageResponseDTO<ROrdMainResponse> querynotInAuditTradeCheckOrders(RQueryOrderRequest rQueryOrderRequest) 
            throws BusinessException;
    
    
    /** 
     * queryChkStatus:查询校验状态. <br/> 
     * @author sky 
     * @param rOrderDetailsRequest
     * @return
     * @throws BusinessException 
     * @since JDK 1.6 
     */ 
    public ROrdCartsChkResponse queryChkStatus(ROrderDetailsRequest rOrderDetailsRequest) throws BusinessException;
    
    
    /** 
     * queryShopChkStatus:店铺相关校验. <br/> 
     * @author cbl 
     * @param rOrderDetailsRequest
     * @return
     * @throws BusinessException 
     * @since JDK 1.6 
     */ 
    public ROrdCartsChkResponse queryShopChkStatus(ROrderDetailsRequest rOrderDetailsRequest,List<ShopInfoResDTO> shopInfoResDTOs) throws BusinessException;
    
    /** 
     * queryStaffIdByOrderId:根据orderId查询staffId. <br/> 
     * @author lcx 
     * @param OrdMainCriteria
     * @return
     * @throws BusinessException 
     * @since JDK 1.6 
     */ 
    public ROrdMainResponse queryStaffIdByOrderId(ROrderDetailsRequest rOrderDetailsRequest) throws BusinessException;
    
    /**
     * 判断调整后退款是否符合要求
     * @param req
     * @return
     * @throws BusinessException
     */
    public boolean checkBackMoney(ROrdReturnRefundReq req) throws BusinessException;

    /**
     * 条件可以包含订单状态列表查询
     * @param rQueryOrderRequest
     * @return
     * @throws BusinessException
     */
    public PageResponseDTO<RCustomerOrdResponse> queryOrderSelectiveStatus(RQueryOrderRequest rQueryOrderRequest) throws BusinessException;

    /**
     * 修改订单发票信息
     * @param req
     * @return
     * @throws BusinessException
     */
    public void updateInvoice(ROrderInvoiceReq req) throws BusinessException;
    
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
     * ordMainDTO:修改卖家留言. <br/>
     * @author jiangmr
     * @param ordMainDTO
     * @throws BusinessException
     * @since JDK 1.7
     */
    public void updateSellerMsg(ROrdSellerMsgDTO rOrdSellerMsgDTO) throws BusinessException;
    
    /**
     * ordMainDTO:查询订单. <br/>
     * @author jiangmr
     * @param ordMainDTO
     * @return ROrdSellerMsgDTO
     * @throws BusinessException
     * @since JDK 1.7
     */
    public ROrdSellerMsgDTO querySellerMsgByOrderId(ROrdSellerMsgDTO rOrdSellerMsgDTO) throws BusinessException;
}



