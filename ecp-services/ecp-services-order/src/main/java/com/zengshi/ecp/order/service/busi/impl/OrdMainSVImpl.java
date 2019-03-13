/**
 * 
 */
package com.zengshi.ecp.order.service.busi.impl;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.zengshi.ecp.frame.vo.BaseCriteria;
import com.zengshi.ecp.general.order.dto.CoupCheckBeanRespDTO;
import com.zengshi.ecp.general.order.dto.CoupDetailRespDTO;
import com.zengshi.ecp.general.order.dto.ROrdCartCommRequest;
import com.zengshi.ecp.general.order.dto.ROrdCartItemCommRequest;
import com.zengshi.ecp.general.order.dto.ROrdCartsCommRequest;
import com.zengshi.ecp.order.dao.mapper.busi.OrdMainMapper;
import com.zengshi.ecp.order.dao.mapper.busi.OrdMainShopIdxMapper;
import com.zengshi.ecp.order.dao.mapper.busi.OrdMainStaffIdxMapper;
import com.zengshi.ecp.order.dao.mapper.busi.manual.OrdMainUalMapper;
import com.zengshi.ecp.order.dao.model.GoodStaffPayedReport;
import com.zengshi.ecp.order.dao.model.OrdBackApply;
import com.zengshi.ecp.order.dao.model.OrdDiscount;
import com.zengshi.ecp.order.dao.model.OrdFileImport;
import com.zengshi.ecp.order.dao.model.OrdGift;
import com.zengshi.ecp.order.dao.model.OrdInvoiceComm;
import com.zengshi.ecp.order.dao.model.OrdInvoiceTax;
import com.zengshi.ecp.order.dao.model.OrdMain;
import com.zengshi.ecp.order.dao.model.OrdMainCriteria;
import com.zengshi.ecp.order.dao.model.OrdMainCriteria.Criteria;
import com.zengshi.ecp.order.dao.model.OrdMainShopIdx;
import com.zengshi.ecp.order.dao.model.OrdMainStaffIdx;
import com.zengshi.ecp.order.dao.model.OrdMainStaffIdxCriteria;
import com.zengshi.ecp.order.dao.model.OrdPresent;
import com.zengshi.ecp.order.dao.model.OrdProm;
import com.zengshi.ecp.order.dao.model.OrdSub;
import com.zengshi.ecp.order.dao.model.OrdSubShare;
import com.zengshi.ecp.order.dao.model.OrdTrack;
import com.zengshi.ecp.order.dao.model.ThingLock;
import com.zengshi.ecp.order.dubbo.dto.RCustomerOrdResponse;
import com.zengshi.ecp.order.dubbo.dto.ROfflineApplyRequest;
import com.zengshi.ecp.order.dubbo.dto.ROfflinePayRequest;
import com.zengshi.ecp.order.dubbo.dto.ROfflinePayResponse;
import com.zengshi.ecp.order.dubbo.dto.ROrdAddressRequest;
import com.zengshi.ecp.order.dubbo.dto.ROrdAddressResponse;
import com.zengshi.ecp.order.dubbo.dto.ROrdBuyerMsgReq;
import com.zengshi.ecp.order.dubbo.dto.ROrdCountResponse;
import com.zengshi.ecp.order.dubbo.dto.ROrdDeliveAddrRequest;
import com.zengshi.ecp.order.dubbo.dto.ROrdExpressDetailsResp;
import com.zengshi.ecp.order.dubbo.dto.ROrdInvoiceCommRequest;
import com.zengshi.ecp.order.dubbo.dto.ROrdInvoiceRequest;
import com.zengshi.ecp.order.dubbo.dto.ROrdInvoiceResponse;
import com.zengshi.ecp.order.dubbo.dto.ROrdInvoiceTaxRequest;
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
import com.zengshi.ecp.order.dubbo.dto.RSumbitMainRequest;
import com.zengshi.ecp.order.dubbo.dto.RSumbitMainsRequest;
import com.zengshi.ecp.order.dubbo.dto.RSumbitSubRequest;
import com.zengshi.ecp.order.dubbo.dto.SBaseAndStatusInfo;
import com.zengshi.ecp.order.dubbo.dto.SBaseSplitInfo;
import com.zengshi.ecp.order.dubbo.dto.SCustomerOrdMain;
import com.zengshi.ecp.order.dubbo.dto.SDelyAmountInfo;
import com.zengshi.ecp.order.dubbo.dto.SOrderDetailsComm;
import com.zengshi.ecp.order.dubbo.dto.SOrderDetailsDelivery;
import com.zengshi.ecp.order.dubbo.dto.SOrderDetailsDiscount;
import com.zengshi.ecp.order.dubbo.dto.SOrderDetailsGift;
import com.zengshi.ecp.order.dubbo.dto.SOrderDetailsMain;
import com.zengshi.ecp.order.dubbo.dto.SOrderDetailsSub;
import com.zengshi.ecp.order.dubbo.dto.SOrderDetailsTax;
import com.zengshi.ecp.order.dubbo.dto.SOrderDetailsTrack;
import com.zengshi.ecp.order.dubbo.dto.SOrderIdx;
import com.zengshi.ecp.order.dubbo.dto.SRefundInfo;
import com.zengshi.ecp.order.dubbo.dto.pay.PaySuccInfo;
import com.zengshi.ecp.order.dubbo.util.CommonConstants;
import com.zengshi.ecp.order.dubbo.util.LongUtils;
import com.zengshi.ecp.order.dubbo.util.MsgConstants;
import com.zengshi.ecp.order.dubbo.util.MsgConstants.PayServiceMsgCode;
import com.zengshi.ecp.order.dubbo.util.MsgConstants.ServiceMsgCode;
import com.zengshi.ecp.order.dubbo.util.OrdConstants;
import com.zengshi.ecp.order.dubbo.util.OrdConstants.NodeDesc;
import com.zengshi.ecp.order.dubbo.util.OrdConstants.Order;
import com.zengshi.ecp.order.dubbo.util.OrdConstants.OrderStatus;
import com.zengshi.ecp.order.dubbo.util.OrdConstants.OrderTwoStatus;
import com.zengshi.ecp.order.dubbo.util.OrdConstants.PayStatus;
import com.zengshi.ecp.order.service.busi.interfaces.IOrdBackApplySV;
import com.zengshi.ecp.order.service.busi.interfaces.IOrdCartSV;
import com.zengshi.ecp.order.service.busi.interfaces.IOrdDeliveryBatchSV;
import com.zengshi.ecp.order.service.busi.interfaces.IOrdDiscountSV;
import com.zengshi.ecp.order.service.busi.interfaces.IOrdFileImportSV;
import com.zengshi.ecp.order.service.busi.interfaces.IOrdGiftSV;
import com.zengshi.ecp.order.service.busi.interfaces.IOrdInvoiceCommSV;
import com.zengshi.ecp.order.service.busi.interfaces.IOrdInvoiceTaxSV;
import com.zengshi.ecp.order.service.busi.interfaces.IOrdMainSV;
import com.zengshi.ecp.order.service.busi.interfaces.IOrdMainShopIdxSV;
import com.zengshi.ecp.order.service.busi.interfaces.IOrdMainStaffIdxSV;
import com.zengshi.ecp.order.service.busi.interfaces.IOrdOfflineSV;
import com.zengshi.ecp.order.service.busi.interfaces.IOrdPayRelSV;
import com.zengshi.ecp.order.service.busi.interfaces.IOrdPresentSV;
import com.zengshi.ecp.order.service.busi.interfaces.IOrdPromSV;
import com.zengshi.ecp.order.service.busi.interfaces.IOrdSubSV;
import com.zengshi.ecp.order.service.busi.interfaces.IOrdSubShareSV;
import com.zengshi.ecp.order.service.busi.interfaces.IOrdTrackSV;
import com.zengshi.ecp.order.service.busi.interfaces.IOrderExpressSV;
import com.zengshi.ecp.order.service.busi.interfaces.IThingLockSV;
import com.zengshi.ecp.order.service.busi.interfaces.report.IGoodStaffPayedSV;
import com.zengshi.ecp.prom.dubbo.dto.CartPromDTO;
import com.zengshi.ecp.prom.dubbo.dto.CartPromItemDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromGiftDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromPresentDTO;
import com.zengshi.ecp.prom.dubbo.util.PromConstants;
import com.zengshi.ecp.server.front.dto.BaseInfo;
import com.zengshi.ecp.server.front.dto.BaseSysCfgRespDTO;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.server.front.util.BaseParamUtil;
import com.zengshi.ecp.server.front.util.SysCfgUtil;
import com.zengshi.ecp.server.service.impl.GeneralSQLSVImpl;
import com.zengshi.ecp.server.service.pagination.PaginationCallback;
import com.zengshi.ecp.staff.dubbo.dto.AcctInfoResDTO;
import com.zengshi.ecp.staff.dubbo.dto.CompanyInfoResDTO;
import com.zengshi.ecp.staff.dubbo.dto.CustInfoReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.CustInfoResDTO;
import com.zengshi.ecp.staff.dubbo.interfaces.ICompanyManageRSV;
import com.zengshi.ecp.staff.dubbo.interfaces.ICustInfoRSV;
import com.zengshi.ecp.staff.dubbo.interfaces.IStaffUnionRSV;
import com.zengshi.paas.utils.DateUtil;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.ObjectCopyUtil;
import com.zengshi.paas.utils.StringUtil;
import com.alibaba.dubbo.common.utils.CollectionUtils;
import com.alibaba.fastjson.JSON;
import com.db.distribute.DistributeRuleAssist;
import com.db.sequence.Sequence;

/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-services-order <br>
 * Description: <br>
 * Date:2015年8月10日下午2:44:54 <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author linwei
 * @version
 * @since JDK 1.6
 */
public class OrdMainSVImpl extends GeneralSQLSVImpl implements IOrdMainSV {
    
    @Autowired(required = false)
    private IOrdBackApplySV ordBackApplySV;

    @Resource
    private OrdMainMapper ordMainMapper;
    
    @Resource
    private OrdMainUalMapper ordMainUalMapper;
    
    @Resource
    private OrdMainShopIdxMapper ordMainShopIdxMapper;
    
    @Resource
    private OrdMainStaffIdxMapper ordMainStaffIdxMapper;

    @Resource
    private IOrdMainShopIdxSV ordMainShopIdxSV;

    @Resource
    private IOrdMainStaffIdxSV ordMainStaffIdxSV;

    @Resource
    private IOrdSubSV ordSubSV;

    @Resource
    private IGoodStaffPayedSV goodStaffPayedSV;

    @Resource
    private IOrdTrackSV ordTrackSV;
    
//    @Resource
//    private IOrdDeliveAddrSV ordDeliveAddrSV;

    @Resource
    private IOrdInvoiceCommSV ordInvoiceCommSV;

    @Resource
    private IOrdInvoiceTaxSV ordInvoiceTaxSV;

    @Resource
    private IOrdGiftSV ordGiftSV;

    @Resource
    private IOrdDiscountSV ordDiscountSV;

    @Resource
    private IOrdPromSV ordPromSV;

    @Resource
    private IOrdPresentSV ordPresentSV;

    @Resource
    private IOrdOfflineSV ordOfflineSV;
    
    @Resource
    private IOrdCartSV ordCartSV;
    
    @Resource
    private IStaffUnionRSV staffUnionRSV;

    @Resource
    private ICustInfoRSV custInfoRSV;

    @Resource
    private ICompanyManageRSV companyManageRSV;
    
//    @Resource
//    private ICustManageRSV  custManageRSV;
    
    @Resource
    private IOrdDeliveryBatchSV ordDeliveryBatchSV;
    
    @Resource
    private IThingLockSV thingLockSV;

    @Resource
    private IOrdPayRelSV ordPayRelSV;

    @Resource(name = "seq_ord_main")
    private Sequence seqOrdMain;
    
    @Resource
    private IOrderExpressSV orderExpressSV;
    
    @Resource
    private IOrdSubShareSV ordSubShareSV;

    @Resource
    private IOrdFileImportSV ordFileImportSV;

    private static final String MODULE = OrdMainSVImpl.class.getName();

    private static Map<Integer, List<OrdMain>> ordMainMap = new HashMap<Integer, List<OrdMain>>();

    @Override
    public void saveOrdMain(OrdMain ordMain) {
        this.ordMainMapper.insert(ordMain);
        OrdMainShopIdx ordMainShopIdx = new OrdMainShopIdx();
        ObjectCopyUtil.copyObjValue(ordMain, ordMainShopIdx, null, false);
        ordMainShopIdx.setOrderId(ordMain.getId());
        this.ordMainShopIdxSV.saveOrdMainShopIdx(ordMainShopIdx);
        OrdMainStaffIdx OrdMainStaffIdx = new OrdMainStaffIdx();
        ObjectCopyUtil.copyObjValue(ordMain, OrdMainStaffIdx, null, false);
        OrdMainStaffIdx.setOrderId(ordMain.getId());
        this.ordMainStaffIdxSV.saveOrdMainStaffIdx(OrdMainStaffIdx);
    }

    @Override
    public OrdMain queryOrderByOrderId(String orderId) {
        OrdMainCriteria omc = new OrdMainCriteria();
        omc.createCriteria().andIdEqualTo(orderId);
        List<OrdMain> ordMains = this.ordMainMapper.selectByExample(omc);
        if (CollectionUtils.isEmpty(ordMains)) {
            return null;
        }
        return ordMains.get(0);
    }

    /**
     * sumOrderSubInfo:统计子订单已发货数量与剩余发货数量. <br/>
     * 
     * @author cbl
     * @param ordSubs
     * @return
     * @since JDK 1.6
     */
    public SDelyAmountInfo sumOrdSubInfo(List<OrdSub> ordSubs) {
        // 剩余发货数量
        Long deliverAmounts = 0l;
        // 已发货数量
        Long remainAmounts = 0l;

        for (OrdSub os : ordSubs) {
            deliverAmounts += os.getDeliverAmount();
            remainAmounts += os.getRemainAmount();
        }
        SDelyAmountInfo sdai = new SDelyAmountInfo();
        sdai.setDeliverAmounts(deliverAmounts);
        sdai.setRemainAmounts(remainAmounts);
        return sdai;
    }

    @Override
    public void updateOrderStatus(SBaseAndStatusInfo sBaseAndStatusInfo) {
        /* 更新主订单表订单状态 */
        OrdMainCriteria omc = new OrdMainCriteria();
        omc.createCriteria().andIdEqualTo(sBaseAndStatusInfo.getOrderId());
        OrdMain om = new OrdMain();
        om.setStatus(sBaseAndStatusInfo.getStatus());
        om.setOrderTwoStatus(sBaseAndStatusInfo.getOrderTwoStatus());
        if (sBaseAndStatusInfo.getDeliverDate() != null) {
            om.setDeliverDate(sBaseAndStatusInfo.getDeliverDate());
        }
        om.setUpdateStaff(sBaseAndStatusInfo.getOperatorId());
        om.setUpdateTime(DateUtil.getSysDate());
        this.ordMainMapper.updateByExampleSelective(om, omc);
        
        
        /* 更新卖家索引表订单状态 */
        this.ordMainShopIdxSV.updateOrderStatus(sBaseAndStatusInfo);
        /* 更新买家索引表订单状态 */
        this.ordMainStaffIdxSV.updateOrderStatus(sBaseAndStatusInfo);
        /* 更新子订单状态 */
        this.ordSubSV.updateStatusByOrderId(sBaseAndStatusInfo);
    }
    
    @Override
    public void updateOrderStatusInfo(RPreOrdSubResponse ordsub,SBaseAndStatusInfo sBaseAndStatusInfo) {
        /* 更新主订单表订单状态 */
        OrdMainCriteria omc = new OrdMainCriteria();
        omc.createCriteria().andIdEqualTo(sBaseAndStatusInfo.getOrderId());
        OrdMain om = new OrdMain();
        om.setStatus(sBaseAndStatusInfo.getStatus());
        om.setOrderTwoStatus(sBaseAndStatusInfo.getOrderTwoStatus());
        if (sBaseAndStatusInfo.getDeliverDate() != null) {
            om.setDeliverDate(sBaseAndStatusInfo.getDeliverDate());
        }
        om.setUpdateStaff(sBaseAndStatusInfo.getOperatorId());
        om.setUpdateTime(DateUtil.getSysDate());
        this.ordMainMapper.updateByExampleSelective(om, omc);
        
        
        /* 更新卖家索引表订单状态 */
        this.ordMainShopIdxSV.updateOrderStatus(sBaseAndStatusInfo);
        /* 更新买家索引表订单状态 */
        this.ordMainStaffIdxSV.updateOrderStatus(sBaseAndStatusInfo);
        /* 更新子订单状态 */
        this.ordSubSV.updateStatusInfo(ordsub,sBaseAndStatusInfo);
    }

    @Override
    public void updateOrderPayTranNo(ROrdPayRelReq rOrdPayRelReq){
        OrdMainCriteria omc = new OrdMainCriteria();
        omc.createCriteria().andIdEqualTo(rOrdPayRelReq.getOrderId());
        OrdMain om = new OrdMain();
        om.setPayTranNo(rOrdPayRelReq.getJoinOrderid());
        om.setUpdateStaff(Long.valueOf(rOrdPayRelReq.getUpdateStaff()));
        om.setUpdateTime(DateUtil.getSysDate());
        
        this.ordMainMapper.updateByExampleSelective(om, omc);
        
        /* 更新卖家索引表订单商户订单号 */
        this.ordMainShopIdxSV.updateOrderPayTranNo(rOrdPayRelReq);
        /* 更新买家索引表订单商户订单号 */
        this.ordMainStaffIdxSV.updateOrderPayTranNo(rOrdPayRelReq);
    }
    
    private RShopOrderResponse queryOrderByIdx(String orderId) {
        RShopOrderResponse sqor = new RShopOrderResponse();
        OrdMain om = queryOrderByOrderId(orderId);
        if (om == null) {
            LogUtil.debug(MODULE, "根据" + orderId + "主订单号查询订单数据为空");
            return null;
        }
        LogUtil.debug(MODULE, "=====================" + om.getId());
        /* 查询T_ORD_SUB-订单子表 */
        List<OrdSub> os = this.ordSubSV.queryOrderSubByOrderId(orderId);
        if (CollectionUtils.isEmpty(os)) {
            LogUtil.debug(MODULE, "根据" + orderId + "主订单号查询子订单数据为空");
            throw new BusinessException(MsgConstants.ProMsgCode.ORD_PRO_312000);
        }
        // 订算已发货数量和剩余发货数量
        SDelyAmountInfo sdai = sumOrdSubInfo(os);

        ObjectCopyUtil.copyObjValue(om, sqor, null, false);
        
//        sqor.setPayTypeName(BaseParamUtil.fetchParamValue("ORD_PAY_TYPE",om.getPayType()));
        
        sqor.setOrderAmounts(om.getOrderAmount());
        sqor.setOrderDate(om.getOrderTime());
        sqor.setOrderTime(om.getOrderTime());
        sqor.setOrderId(om.getId());
        sqor.setJoinOrderid(om.getPayTranNo());
        sqor.setRemainAmounts(sdai.getRemainAmounts());
        sqor.setDeliverAmounts(sdai.getDeliverAmounts());
        
        CustInfoResDTO custInfoResDTO = this.staffUnionRSV.findCustOrAdminByStaffId(om.getStaffId());
        if(custInfoResDTO != null && custInfoResDTO.getStaffCode() != null){
            sqor.setStaffName(custInfoResDTO.getStaffCode());
        } else {
            sqor.setStaffName(om.getStaffId().toString());
        }
        // 订单普通发票相关字段
        SOrderDetailsComm sOrderDetailsComm = this.ordInvoiceCommSV.queryOrderDetailsComm(orderId);
        // 订单增值税发票相关字段
        SOrderDetailsTax sOrderDetailsTax = this.ordInvoiceTaxSV.queryOrderDetailsTax(orderId);
        sqor.setsOrderDetailsComm(sOrderDetailsComm);
        sqor.setsOrderDetailsTax(sOrderDetailsTax);

        return sqor;
    }
    
    public List<RShopOrderResponse> queryOrderByIdx(List<SOrderIdx> sDelyOrderIdxs) {
        List<RShopOrderResponse> sqors = new ArrayList<RShopOrderResponse>();
        for (SOrderIdx sdoi : sDelyOrderIdxs) {
            RShopOrderResponse sqor = queryOrderByIdx(sdoi.getOrderId());
            if(sqor != null){
                sqors.add(sqor);
            }
        }
        return sqors;
    }

    /**
     * 
     * 提交订单的服务.
     * 
     * @see com.zengshi.ecp.order.service.busi.interfaces.IOrdMainSV#saveSubmitOrd(com.zengshi.ecp.order.dubbo.dto.RSumbitMainsRequest)
     */
    @Override
    public List<ROrdMainResponse> saveSubmitOrd(RSumbitMainsRequest info) {
        LogUtil.info(MODULE, "提交订单服务开始:"+JSON.toJSONString(info));
        ThingLock thingLock = new ThingLock();
        thingLock.setId(Long.toString(info.getStaff().getId()));
        thingLock.setType(CommonConstants.LockType.LOCK_TYPE_SUBMIT);
        this.thingLockSV.addThingLock(thingLock);

        if(StringUtil.isBlank(info.getSourceKey())){
            if(!this.ordCartSV.queryBeforOrderCart(info)){
                LogUtil.info(MODULE, "提交的数据与购物车明细不匹配！");
                throw new BusinessException(MsgConstants.ProMsgCode.ORD_PRO_352003);
            }
        }else{
            if(!this.ordCartSV.queryFastBeforOrderCart(info)){
                LogUtil.info(MODULE, "提交的数据与立即购买数据不匹配！");
                throw new BusinessException(MsgConstants.ProMsgCode.ORD_PRO_352003);
            }
        }

        List<RSumbitMainRequest> sumbitMainList = info.getSumbitMainList();
        List<ROrdMainResponse> rOrdMainResponseList=new ArrayList<ROrdMainResponse>();
        // 1 保存订单主表
        for (RSumbitMainRequest sumbitMainRequest : sumbitMainList) {
            LogUtil.info(MODULE, "保存订单主表开始");
            String ordMainId = sumbitMainRequest.getOrderId();
            sumbitMainRequest.setPayType(info.getPayType());
            ROrdMainResponse rOrdMainResponse=saveOrdMainInfo(sumbitMainRequest,info);
            rOrdMainResponseList.add(rOrdMainResponse);
            LogUtil.info(MODULE, "保存订单主表结束");
            if (CollectionUtils.isEmpty(sumbitMainRequest.getPreOrdSubList())) {
                throw new BusinessException(MsgConstants.ProMsgCode.ORD_PRO_352000);
            }
            for (RSumbitSubRequest rSumbitSubRequest : sumbitMainRequest.getPreOrdSubList()) {
                // 2 保存订单子表
                LogUtil.info(MODULE, "保存订单子表开始");
                rSumbitSubRequest.setOrderId(ordMainId);
                ordSubSV.saveOrdSubInfo(rSumbitSubRequest,info,rOrdMainResponse);
                LogUtil.info(MODULE, "保存订单子表结束");
                
                LogUtil.info(MODULE, "保存分享送积分");
                //保存分享送积分
                if(info.getShareMap()!=null){
                	  Long shareStaffId = info.getShareMap().get(rSumbitSubRequest.getGdsId());
                	  if(shareStaffId!=null){
	                	  OrdSubShare ordSubShare = new OrdSubShare();
	                	  ordSubShare.setCreateStaff(rSumbitSubRequest.getStaff().getId());
	                	  ordSubShare.setCreateTime(new Timestamp(System.currentTimeMillis()));
	                	  ordSubShare.setGdsId(rSumbitSubRequest.getGdsId());
	                	  ordSubShare.setShareStaffId(shareStaffId);
	                	  ordSubShare.setStaffId(rSumbitSubRequest.getStaff().getId());
	                	  ordSubShare.setStatus(OrdConstants.ShareStatus.NODO);
	                	  ordSubShare.setSubOrdId(rSumbitSubRequest.getOrderSubId());
	                	  ordSubShare.setOrderId(ordMainId);
	                	  ordSubShareSV.saveOrdSubShare(ordSubShare);
                	  }
                }
              
                LogUtil.info(MODULE, "保存分享送积分结束");
                // 5 保存订单赠品信息
                /*
                 * LogUtil.info(MODULE, "保存订单赠品开始"); saveOrdGiftInfo(ordMainId,
                 * rOrdCartItemRequest);
                 */
                LogUtil.info(MODULE, "保存用户商品报表信息");
            }
            // 3 保存订单收货地址
//            LogUtil.info(MODULE, "保存订单收货地址开始");
//            saveOrdDeliveAddrInfo(ordMainId, info);
            // 4 保存订单发票信息(普票，电子票，增票)
            LogUtil.info(MODULE, "保存订单发票开始");
            saveOrdInvoiceInfo(ordMainId, sumbitMainRequest);
            // 6 保存订单跟踪
            LogUtil.info(MODULE, "保存订单跟踪开始");
            saveOrdTrack(ordMainId, sumbitMainRequest);
            // 7 保存订单优惠信息  --处理资金账户、积分帐户的信息
            LogUtil.info(MODULE, "保存订单优惠开始");
            saveOrdDiscount(info,sumbitMainRequest);
            // 8 保存订单促销信息
            LogUtil.info(MODULE, "保存订单促销开始");
            saveOrdProm(ordMainId, info,sumbitMainRequest);
            // 9 保存订单预赠信息  --在第8点中实现
//            LogUtil.info(MODULE, "保存订单预赠开始");
//            saveOrdPresent(ordMainId, sumbitMainRequest);
            // 10 保存线下支付信息
            LogUtil.info(MODULE, "保存线下支付开始");
            if (!sumbitMainRequest.getPayType().equals("0")) {
                saveOrdOffline(ordMainId, sumbitMainRequest);
            }
            
        }
        //删除购物车相关信息
        if(StringUtil.isBlank(info.getSourceKey())) {
            //删除购物车相关信息
            this.ordCartSV.deleteAfterOrderCart(info);
        }

        this.thingLockSV.removeThingLock(thingLock);
        return rOrdMainResponseList;
    }


    /**
     * 保存用户商品报表信息
     * @author wangxq
     * @param ordsub
     */
    private void saveGoodStaffPayed(RSumbitSubRequest ordsub){
        GoodStaffPayedReport goodStaffPayedReport = new GoodStaffPayedReport();
        goodStaffPayedReport.setStaffId(ordsub.getStaffId());
        goodStaffPayedReport.setGdsId(ordsub.getGdsId());
        goodStaffPayedReport.setSkuId(ordsub.getSkuId());
        goodStaffPayedReport.setBuyNum(ordsub.getOrderAmount());
        goodStaffPayedSV.saveGoodStaffPayed(goodStaffPayedReport);
    }


    @Override
    public PageResponseDTO<RShopOrderResponse> queryOrderByShopIdPage(
            RQueryOrderRequest rQueryOrderRequest) {
        PageResponseDTO<RShopOrderResponse> pageResponse = PageResponseDTO
                .buildByBaseInfo(rQueryOrderRequest, RShopOrderResponse.class);
        pageResponse.setResult(new ArrayList<RShopOrderResponse>());

        
        //店铺订单索引表 获取订单列表 
        PageResponseDTO<SOrderIdx> psdoi = this.ordMainShopIdxSV.queryOrderByShopIdPage(rQueryOrderRequest);
         

        // 从子订单表 获取订单列表
//        PageResponseDTO<SOrderIdx> psdoi = this.ordSubSV.queryOrderByShopIdPage(rQueryOrderRequest);
        pageResponse.setCount(psdoi.getCount());
        pageResponse.setPageCount(psdoi.getPageCount());
        if (CollectionUtils.isEmpty(psdoi.getResult())) {
            LogUtil.info(MODULE, "未找到订单数据");
            pageResponse.setResult(null);
            return pageResponse;
            // throw new BusinessException(MsgConstants.ProMsgCode.ORD_PRO_312000);
        } else {
            List<RShopOrderResponse> sqors = queryOrderByIdx(psdoi.getResult());
            pageResponse.getResult().addAll(sqors);
        }
        return pageResponse;
    }

    // @Override
    // public PageResponseDTO<RShopOrderResponse> queryDelyedOrderByShopIdPage(
    // RDelyOrderRequest rDelyOrderRequest) {
    // PageResponseDTO<RShopOrderResponse> pageResponse = PageResponseDTO.buildByBaseInfo(
    // rDelyOrderRequest, RShopOrderResponse.class);
    // pageResponse.setResult(new ArrayList<RShopOrderResponse>());
    // PageResponseDTO<SOrderIdx> psdoi = this.ordMainShopIdxSV.queryOrderByShopIdPage(
    // rDelyOrderRequest, "0");
    // pageResponse.setCount(psdoi.getCount());
    // pageResponse.setPageCount(psdoi.getPageCount());
    // if (CollectionUtils.isEmpty(psdoi.getResult())) {
    // LogUtil.info(MODULE, "未找到订单数据");
    // return null;
    // // throw new BusinessException(MsgConstants.ProMsgCode.ORD_PRO_312000);
    // } else {
    // List<RShopOrderResponse> sqors = queryOrderByIdx(psdoi.getResult());
    // pageResponse.getResult().addAll(sqors);
    // }
    // return pageResponse;
    // }

    @Override
    public void updateOrderReceipt(ROrdReceiptRequest rOrdReceiptRequest) {
        /*
         * 更新T_ORD_MAIN-订单主表 更新T_ORD_MAIN_SHOP_IDX-店铺订单索引表 更新T_ORD_MAIN_STAFF_IDX-买家订单索引表
         * 更新T_ORD_SUB-订单子表
         */

        OrdMain om = queryOrderByOrderId(rOrdReceiptRequest.getOrderId());
        if (om == null) {
            LogUtil.debug(MODULE, "根据" + rOrdReceiptRequest.getOrderId() + "主订单号查询订单数据为空");
            throw new BusinessException(MsgConstants.ProMsgCode.ORD_PRO_312000);
        }
        SBaseAndStatusInfo sosi = new SBaseAndStatusInfo();
        sosi.setOrderId(rOrdReceiptRequest.getOrderId());
        sosi.setShopId(om.getShopId());
        sosi.setStaffId(om.getStaffId());
        sosi.setStatus(OrdConstants.OrderStatus.ORDER_STATUS_RECEPT); // 收货
        sosi.setOrderTwoStatus(OrdConstants.OrderTwoStatus.STATUS_RECEPT_BUYER);
        sosi.setOperatorId(rOrdReceiptRequest.getStaff().getId());
        updateOrderStatus(sosi);

        // 新增T_ORD_TRAC--各种-订单跟踪表
        dealOrderTrack(sosi);
    }

    /**
     * delOrderTrack:生成订单跟踪表信息. <br/>
     * 
     * @author cbl
     * @since JDK 1.6
     */
    private void dealOrderTrack(SBaseAndStatusInfo sBaseAndStatusInfo) {
        OrdTrack ot = new OrdTrack();
        if(OrdConstants.Common.DEFAULT_STAFFID.equals(sBaseAndStatusInfo.getOperatorId())){
            ot.setNode(OrdConstants.OrderTwoStatus.STATUS_RECEPT_SYSTEM);
            ot.setNodeDesc(OrdConstants.NodeDesc.STATUS_RECEPT_SYSTEM);
        } else {
            ot.setNode(OrdConstants.OrderTwoStatus.STATUS_RECEPT_BUYER);
            ot.setNodeDesc(OrdConstants.NodeDesc.STATUS_RECEPT_BUYER);
        }
        
        ot.setOrderId(sBaseAndStatusInfo.getOrderId());
        ot.setCreateStaff(sBaseAndStatusInfo.getOperatorId());
        ot.setCreateTime(new Timestamp(System.currentTimeMillis()));
        this.ordTrackSV.saveOrdTrack(ot);
    }

    private OrdMainCriteria createCriteria(RQueryOrderRequest rQueryOrderRequest,String callFrom){
        OrdMainCriteria omsic = new OrdMainCriteria();
        omsic.setLimitClauseCount(rQueryOrderRequest.getPageSize());
        omsic.setLimitClauseStart(rQueryOrderRequest.getStartRowIndex());
        omsic.setOrderByClause("id desc");
        OrdMainCriteria.Criteria ca = omsic.createCriteria();
        if(rQueryOrderRequest.getBegDate() != null){
            ca.andOrderTimeGreaterThanOrEqualTo(rQueryOrderRequest.getBegDate());
        }
        if(rQueryOrderRequest.getEndDate() != null){
            ca.andOrderTimeLessThanOrEqualTo(rQueryOrderRequest.getEndDate());
        }
        if(StringUtil.isNotBlank(rQueryOrderRequest.getOrderId())){
            ca.andIdEqualTo(rQueryOrderRequest.getOrderId());
        }
        if (LongUtils.isNotEmpty(rQueryOrderRequest.getStaffId())) {
            ca.andStaffIdEqualTo(rQueryOrderRequest.getStaffId());
        }
        if (LongUtils.isNotEmpty(rQueryOrderRequest.getShopId())) {
            ca.andShopIdEqualTo(rQueryOrderRequest.getShopId());
        }
        if (rQueryOrderRequest.getSiteId() != null) {
            ca.andSiteIdEqualTo(rQueryOrderRequest.getSiteId());
        }
        if (StringUtil.isNotBlank(rQueryOrderRequest.getSysType())) {
            ca.andSysTypeEqualTo(rQueryOrderRequest.getSysType());
        }
        if(StringUtil.isNotBlank(rQueryOrderRequest.getStatus())){
            ca.andStatusEqualTo(rQueryOrderRequest.getStatus());
        }
        if(StringUtil.isNotBlank(rQueryOrderRequest.getContactName())){
            ca.andContactNameEqualTo(rQueryOrderRequest.getContactName());
        }
        if(StringUtil.isNotBlank(rQueryOrderRequest.getContactPhone())){
            ca.andContactPhoneEqualTo(rQueryOrderRequest.getContactPhone());
        }
        if(StringUtil.isNotBlank(rQueryOrderRequest.getPayType())){
            ca.andPayTypeEqualTo(rQueryOrderRequest.getPayType());
        }
        if(StringUtil.isNotBlank(rQueryOrderRequest.getPayWay())){
            ca.andPayWayEqualTo(rQueryOrderRequest.getPayWay());
        }
        if(StringUtil.isNotBlank(rQueryOrderRequest.getInvoiceType())){
            if("2".equals(rQueryOrderRequest.getInvoiceType())){
                ca.andInvoiceTypeEqualTo(rQueryOrderRequest.getInvoiceType());
            } else {
                ca.andInvoiceTypeNotEqualTo("2");
            }
        }
        if(StringUtil.isNotBlank(rQueryOrderRequest.getPayFlag())){
            ca.andPayFlagEqualTo(rQueryOrderRequest.getPayFlag());
        }
        if(StringUtil.isNotBlank(rQueryOrderRequest.getPayTranNo())){
            ca.andPayTranNoEqualTo(rQueryOrderRequest.getPayTranNo());
        }
        
        if("4".equals(callFrom)){   //已支付数量统计
            ca.andPayFlagEqualTo("1");
        }
        return omsic;
    }
    
    /**
     * queryOrderId:全表查询订单主表. <br/>
     * 
     * @author cbl
     * @param rQueryOrderRequest
     * @return
     * @since JDK 1.6
     */
    public PageResponseDTO<ROrderIdResponse> queryOrderId(RQueryOrderRequest rQueryOrderRequest) {
        
        OrdMainCriteria omsic = createCriteria(rQueryOrderRequest,"0");
        
        return super.queryByPagination(rQueryOrderRequest, omsic, true,
                new PaginationCallback<OrdMain, ROrderIdResponse>() {

                    @Override
                    public List<OrdMain> queryDB(BaseCriteria bCriteria) {
                        return ordMainMapper.selectByExample((OrdMainCriteria) bCriteria);
                    }

                    @Override
                    public long queryTotal(BaseCriteria bCriteria) {
                        return ordMainMapper.countByExample((OrdMainCriteria) bCriteria);
                    }

                    @Override
                    public List<Comparator<OrdMain>> defineComparators() {
                        List<Comparator<OrdMain>> ls = new ArrayList<Comparator<OrdMain>>();
                        ls.add(new Comparator<OrdMain>(){

                            @Override
                            public int compare(OrdMain o1, OrdMain o2) {
                                return o2.getId().compareTo(o1.getId());
                            }
                            
                        });
                        return ls;
                    }

                    @Override
                    public ROrderIdResponse warpReturnObject(OrdMain ordMain) {
                        ROrderIdResponse sdoi = new ROrderIdResponse();
                        BeanUtils.copyProperties(ordMain, sdoi);
                        sdoi.setOrderId(ordMain.getId());
                        return sdoi;
                    }
                });
    }

    /**
     * sumOrderScore:计算订单总积分. <br/>
     * 
     * @author cbl
     * @return
     * @since JDK 1.6
     */
    public Long sumOrderScore(RSumbitMainRequest rSumbitMainRequest) {
        Long score = 0l;
        for (RSumbitSubRequest rSumbitSubRequest : rSumbitMainRequest.getPreOrdSubList()) {
            score += rSumbitSubRequest.getOrderScore();
        }
        return score;
    }

    private ROrdMainResponse saveOrdMainInfo(RSumbitMainRequest rSumbitMainRequest,RSumbitMainsRequest info) {

        OrdMain ordMain = new OrdMain();
        // String ordMainId = seqOrdMain.nextValue().toString();
        ObjectCopyUtil.copyObjValue(rSumbitMainRequest, ordMain, null, false);
        ordMain.setOrderTime(DateUtil.getSysDate());
        ordMain.setOrderCode(rSumbitMainRequest.getOrderId());
        ordMain.setStatus(OrdConstants.OrderStatus.ORDER_STATUS_SUBMIT);
        ordMain.setSource(info.getSource());
        ordMain.setOrderType(OrdConstants.Order.ORDER_TYPE_01);
        ordMain.setPayFlag(OrdConstants.Order.ORDER_PAY_FLAG_0);
        ordMain.setDispatchType(rSumbitMainRequest.getDeliverType());
        ordMain.setSiteId(rSumbitMainRequest.getCurrentSiteId());
        ordMain.setSysType(OrdConstants.SysType.SYS_TYPE_BASE);
        ordMain.setInvoiceType(StringUtil.isBlank(ordMain.getInvoiceType()) ? "2" :ordMain.getInvoiceType());
        ordMain.setIsInAuditFile(PayStatus.ORD_MAIN_IS_IN_AUDIT_FILE_0);
        // 订单总积分
        ordMain.setOrderScore(sumOrderScore(rSumbitMainRequest));

        ordMain.setBuyerMsg(rSumbitMainRequest.getBuyerMsg());
        
        ordMain.setBillingFlag(OrdConstants.Common.COMMON_INVALID);





        ROrdDeliveAddrRequest rOrdDeliveAddrRequest = info.getrOrdDeliveAddrRequest();
        ObjectCopyUtil.copyObjValue(rOrdDeliveAddrRequest, ordMain , null, false);
        ordMain.setId(rSumbitMainRequest.getOrderId());
        ordMain.setCreateTime(DateUtil.getSysDate());
        ordMain.setCreateStaff(rSumbitMainRequest.getStaff().getId());
        ordMain.setUpdateStaff(rSumbitMainRequest.getStaff().getId());
        ordMain.setUpdateTime(DateUtil.getSysDate());

        CustInfoReqDTO custInfoReqDTO = new CustInfoReqDTO();
        custInfoReqDTO.setId(ordMain.getStaffId());
        CustInfoResDTO resDTO = custInfoRSV.getCustInfoById(custInfoReqDTO);
        ordMain.setStaffCode(resDTO.getStaffCode());
        ordMain.setStaffName(resDTO.getCustName());
        ordMain.setCompanyId(resDTO.getCompanyId());
        if(StringUtil.isNotEmpty(resDTO.getCompanyId())){
            CompanyInfoResDTO companyInfoResDTO = this.companyManageRSV.findCompanyInfoById(resDTO.getCompanyId());	
            ordMain.setCompanyName(companyInfoResDTO.getCompanyName());
        }
        LogUtil.info(MODULE, "保存订单主表" + JSON.toJSONString(ordMain));
        this.saveOrdMain(ordMain);
        ROrdMainResponse rOrdMainResponse=new ROrdMainResponse();
        ObjectCopyUtil.copyObjValue(ordMain, rOrdMainResponse, null, false);
        
//      ordMain.setStaffId(rSumbitMainRequest.getStaffId());
//      ordMain.setShopId(rSumbitMainRequest.getShopId());
//      ordMain.setShopName(rSumbitMainRequest.getShopName());
//      ordMain.setOrderAmount(rSumbitMainRequest.getOrderAmount());
//      ordMain.setOrderTwoStatus(orderTwoStatus); 
//      ordMain.setOrderMoney(rSumbitMainRequest.getOrderMoney());
//      ordMain.setRealMoney(rSumbitMainRequest.getRealMoney());
//      ordMain.setRealExpressFee(rSumbitMainRequest.getRealExpressFee());
//      ordMain.setPayType(rSumbitMainRequest.getPayType());
//      ordMain.setInvoiceType(rSumbitMainRequest.getInvoiceType());
//      ordMain.setSource(source); ordMain.setBuyerMsg(buyerMsg);
//      ordMain.setDispatchType(dispatchType);
        return rOrdMainResponse;
    }


    /**
     * 
     * saveOrdInvoiceInfo:保存发票信息. <br/>
     * 
     * @author linwei
     * @param ordMainId
     * @param rSumbitMainRequest
     * @since JDK 1.6
     */
    private void saveOrdInvoiceInfo(String ordMainId, RSumbitMainRequest rSumbitMainRequest) {
        String invoiceType = rSumbitMainRequest.getInvoiceType();
        if ("0".equals(invoiceType)) {
            OrdInvoiceComm ordInvoiceComm = new OrdInvoiceComm();
            ROrdInvoiceCommRequest rOrdInvoiceCommRequest = rSumbitMainRequest
                    .getrOrdInvoiceCommRequest();
            if (rOrdInvoiceCommRequest != null) {
                ObjectCopyUtil.copyObjValue(rOrdInvoiceCommRequest, ordInvoiceComm, null, false);
                ordInvoiceComm.setOrderId(ordMainId);
                ordInvoiceComm.setCreateStaff(rSumbitMainRequest.getStaff().getId());
                ordInvoiceComm.setCreateTime(DateUtil.getSysDate());
                ordInvoiceComm.setUpdateStaff(rSumbitMainRequest.getStaff().getId());
                ordInvoiceComm.setUpdateTime(DateUtil.getSysDate());
                ordInvoiceCommSV.saveOrdInvoiceComm(ordInvoiceComm);
            }
        } else if("1".equals(invoiceType)){
            OrdInvoiceTax ordInvoiceTax = new OrdInvoiceTax();
            ROrdInvoiceTaxRequest rOrdInvoiceTaxRequest = rSumbitMainRequest
                    .getrOrdInvoiceTaxRequest();
            if (rOrdInvoiceTaxRequest != null) {
                ObjectCopyUtil.copyObjValue(rOrdInvoiceTaxRequest, ordInvoiceTax, null, false);
                ordInvoiceTax.setOrderId(ordMainId);
                ordInvoiceTax.setShopId(rSumbitMainRequest.getShopId());
                ordInvoiceTax.setCreateStaff(rSumbitMainRequest.getStaffId());
                ordInvoiceTax.setCreateTime(DateUtil.getSysDate());
                ordInvoiceTax.setUpdateStaff(rSumbitMainRequest.getStaffId());
                ordInvoiceTax.setUpdateTime(DateUtil.getSysDate());
                ordInvoiceTaxSV.saveOrdInvoiceTax(ordInvoiceTax);
            }
        }else{
            
        }
    }

    /**
     * 
     * saveOrdTrack:保存订单跟踪表. <br/>
     * 
     * @author linwei
     * @param ordMainId
     * @param sumbitMainRequest
     * @since JDK 1.6
     */
    private void saveOrdTrack(String ordMainId, RSumbitMainRequest sumbitMainRequest) {
        OrdTrack ordTrack = new OrdTrack();
        ordTrack.setOrderId(ordMainId);
        ordTrack.setNode(OrdConstants.OrderTwoStatus.STATUS_SUBMIT);
        ordTrack.setNodeDesc(OrdConstants.NodeDesc.STATUS_SUBMIT);
        ordTrack.setCreateStaff(sumbitMainRequest.getStaff().getId());
        ordTrack.setUpdateStaff(sumbitMainRequest.getStaff().getId());
        ordTrack.setCreateTime(DateUtil.getSysDate());
        ordTrack.setUpdateTime(DateUtil.getSysDate());
        ordTrackSV.saveOrdTrack(ordTrack);
    }

    /**
     * 
     * saveOrdDiscount:保存订单优惠信息. <br/>
     * 
     * @author linwei
     * @param rSumbitMainsRequest
     * @since JDK 1.6
     */
    private void saveOrdDiscount(RSumbitMainsRequest rSumbitMainsRequest,RSumbitMainRequest rs) {
        
            //优惠券
            if(rs.getCoupCheckBean()!=null && rs.getCoupCheckBean().size()>0){
                List<CoupCheckBeanRespDTO> coups = rs.getCoupCheckBean();
                //已优惠券小类做记录区分，一种优惠券记录一条记录
                for(CoupCheckBeanRespDTO coupCheckBean: coups){
                    Long discountPrice = 0l;
                    if(coupCheckBean.getCoupDetails()!=null && coupCheckBean.getCoupDetails().size()>0){
                        for(CoupDetailRespDTO coup : coupCheckBean.getCoupDetails()){
                            discountPrice += coup.getCoupValue();//不做判断，正确状态无异常？
                        }
                    }
                    OrdDiscount ordDiscount = new OrdDiscount();
                    ordDiscount.setRemark(coups.get(0).getCoupId()+"");//由于没有设计优惠券字段，需要添加在remark字段当中
                    ordDiscount.setOrderId(rs.getOrderId());
                    ordDiscount.setDiscountType(OrdConstants.DiscountType.TYPE_COUP_CODE);
                    ordDiscount.setDiscountTitle(OrdConstants.DiscountType.TYPE_COUP_NAME);
                    ordDiscount.setDiscountPrice(discountPrice);
                    ordDiscount.setCreateStaff(rSumbitMainsRequest.getStaff().getId());
                    ordDiscount.setCreateTime(DateUtil.getSysDate());
                    ordDiscount.setUpdateStaff(rSumbitMainsRequest.getStaff().getId());
                    ordDiscount.setUpdateTime(DateUtil.getSysDate());
                    ordDiscountSV.saveOrdDiscount(ordDiscount);
                }
            }
        // 资金帐户
        if(CollectionUtils.isNotEmpty(rs.getOrdAcctInfoList())){
            for (AcctInfoResDTO ar : rs.getOrdAcctInfoList()) {
                if (ar.getDeductOrderMoney() > 0l) {
                    OrdDiscount ordDiscount = new OrdDiscount();
                    ordDiscount.setOrderId(rs.getOrderId());
                    ordDiscount.setDiscountType(OrdConstants.DiscountType.TYPE_CAPITAL_CODE);
                    ordDiscount.setDiscountTitle(OrdConstants.DiscountType.TYPE_CAPITAL_NAME);
                    ordDiscount.setDiscountPrice(ar.getDeductOrderMoney());
                    ordDiscount.setCreateStaff(rSumbitMainsRequest.getStaff().getId());
                    ordDiscount.setCreateTime(DateUtil.getSysDate());
                    ordDiscount.setUpdateStaff(rSumbitMainsRequest.getStaff().getId());
                    ordDiscount.setUpdateTime(DateUtil.getSysDate());
                    ordDiscountSV.saveOrdDiscount(ordDiscount);
                }
            }
        }

        // 积分帐户
        if(CollectionUtils.isNotEmpty(rs.getPreOrdSubList())){
            for (RSumbitSubRequest rSumbitSubRequest : rs.getPreOrdSubList()) {
                if (rSumbitSubRequest.getOrderScore() > 0l) {
                    OrdDiscount ordDiscount = new OrdDiscount();
                    ordDiscount.setOrderId(rs.getOrderId());
                    ordDiscount.setOrderSubId(rSumbitSubRequest.getOrderSubId());
                    ordDiscount.setDiscountType(OrdConstants.DiscountType.TYPE_SCORE_CODE);
                    ordDiscount.setDiscountTitle(OrdConstants.DiscountType.TYPE_SCORE_NAME);
                    ordDiscount.setUseScore(rSumbitSubRequest.getOrderScore());
                    ordDiscount.setCreateStaff(rSumbitMainsRequest.getStaff().getId());
                    ordDiscount.setCreateTime(DateUtil.getSysDate());
                    ordDiscount.setUpdateStaff(rSumbitMainsRequest.getStaff().getId());
                    ordDiscount.setUpdateTime(DateUtil.getSysDate());
                    ordDiscountSV.saveOrdDiscount(ordDiscount);
                }
            }
        }
        
        //优惠码
        if(StringUtils.isNotBlank(rs.getCoupCode())){           
            OrdDiscount ordDiscount = new OrdDiscount();
            ordDiscount.setRemark("优惠码："+rs.getCoupCode()+"已优惠"+rs.getCoupCodeMoney());//由于没有设计优惠券字段，需要添加在remark字段当中
            ordDiscount.setOrderId(rs.getOrderId());
            ordDiscount.setDiscountType(OrdConstants.DiscountType.TYPE_COUPCODE_CODE);
            ordDiscount.setDiscountTitle(OrdConstants.DiscountType.TYPE_COUPCODE_NAME);
            ordDiscount.setDiscountPrice(rs.getCoupCodeMoney());
            ordDiscount.setCreateStaff(rSumbitMainsRequest.getStaff().getId());
            ordDiscount.setCreateTime(DateUtil.getSysDate());
            ordDiscount.setUpdateStaff(rSumbitMainsRequest.getStaff().getId());
            ordDiscount.setUpdateTime(DateUtil.getSysDate());
            ordDiscountSV.saveOrdDiscount(ordDiscount);
        }

    }

    /**
     * 
     * saveOrdProm:保存订单促销信息. <br/>
     * 
     * @author linwei
     * @since JDK 1.6
     */

    private void saveOrdProm(String ordMainId, RSumbitMainsRequest sumbitMainsRequest,RSumbitMainRequest rs) {

        // 保存T_ORD_PROM--订单活动表

        // 订单级别优惠
        Map<Long, CartPromDTO> ordMap = null;

        // 子订单级别优惠
        Map<Long, CartPromItemDTO> ordSubMap = null;

        // 订单级别赠送
        Map<Long, PromPresentDTO> ordPreMap = null;
        // 子订单级别赠送
        Map<Long, PromPresentDTO> ordSubPreMap = null;
        
        if(sumbitMainsRequest.getCartPromRespDTO() != null){
            ordMap = sumbitMainsRequest.getCartPromRespDTO().getCartPromDTOMap();
            ordSubMap = sumbitMainsRequest.getCartPromRespDTO().getCartPromItemDTOMap();
        }
        if(sumbitMainsRequest.getPromPresentRespDTO() != null){
            ordPreMap = sumbitMainsRequest.getPromPresentRespDTO().getCartPromDTOMap();
            ordSubPreMap = sumbitMainsRequest.getPromPresentRespDTO().getCartPromItemDTOMap();
        }

        // 如果该活动为赠品相关 则保存T_ORD_GIFT--订单赠品表
        // 如果该活动为优惠相关 刚保存T_ORD_DISCOUNT-订单优惠表
        // 如果该活动为积分或优惠券相关 则保存T_ORD_PRESENT--订单赠送积分和优惠券
        
        if (ordMap != null) {
            CartPromDTO cpd = ordMap.get(rs.getCartId());
            if (cpd != null && cpd.isIfFulfillProm()) {
                // 主订单活动
                if (cpd.getPromInfoDTO() != null) {
                    OrdProm ordProm = new OrdProm();
                    ordProm.setOrderId(rs.getOrderId());
                    ordProm.setPromId(cpd.getPromInfoDTO().getId());
                    ordProm.setPromTypeCode(cpd.getPromInfoDTO().getPromTypeCode());
                    ordProm.setPromTypeName(cpd.getPromInfoDTO().getPromTypeName());
                    ordProm.setCreateStaff(sumbitMainsRequest.getStaff().getId());
                    ordProm.setCreateTime(DateUtil.getSysDate());
                    ordProm.setUpdateStaff(sumbitMainsRequest.getStaff().getId());
                    ordProm.setUpdateTime(DateUtil.getSysDate());
                    ordPromSV.saveOrdProm(ordProm);
                }
                // 主订单优惠价格等
                if (cpd.getDiscountMoney() != null && (cpd.getDiscountMoney().longValue()
                        + cpd.getDiscountPriceMoney().longValue()) != 0l) {
                    OrdDiscount ordDiscount = new OrdDiscount();
                    ordDiscount.setOrderId(rs.getOrderId());
                    ordDiscount.setDiscountType(OrdConstants.DiscountType.TYPE_ORDER_CODE);
                    ordDiscount.setDiscountTitle(OrdConstants.DiscountType.TYPE_ORDER_NAME);
                    ordDiscount.setDiscountPrice(cpd.getDiscountMoney().longValue()
                            + cpd.getDiscountPriceMoney().longValue());
                    ordDiscount.setCreateStaff(sumbitMainsRequest.getStaff().getId());
                    ordDiscount.setCreateTime(DateUtil.getSysDate());
                    ordDiscount.setUpdateStaff(sumbitMainsRequest.getStaff().getId());
                    ordDiscount.setUpdateTime(DateUtil.getSysDate());
                    ordDiscountSV.saveOrdDiscount(ordDiscount);
                }

                if (ordPreMap != null) {
                    PromPresentDTO pp = ordPreMap.get(rs.getCartId());
                    if (pp != null) {
                        // 主订单赠送积分
                        if (pp.getPoints() != null && pp.getSendType() != null
                                && (pp.getPoints().doubleValue() > 0.0001
                                        || pp.getPoints().doubleValue() < -0.0001)) {

                            OrdPresent ordPresent = new OrdPresent();
                            ordPresent.setOrderId(rs.getOrderId());
                            ordPresent.setPromId(pp.getPromId());
                            if (PromConstants.PromDiscountRule.SEND_POINTS_0
                                    .equals(pp.getSendType())) {
                                ordPresent.setCredits(pp.getPoints().longValue());
                            } else {
                                ordPresent.setCreditTimes(pp.getPoints());
                            }

                            ordPresent.setCreateStaff(sumbitMainsRequest.getStaff().getId());
                            ordPresent.setCreateTime(DateUtil.getSysDate());
                            ordPresent.setUpdateStaff(sumbitMainsRequest.getStaff().getId());
                            ordPresent.setUpdateTime(DateUtil.getSysDate());
                            ordPresentSV.saveOrdPresent(ordPresent);
                        }
                        // 主订单赠送优惠券
                        if (pp.getSendAmount() != null && pp.getSendAmount().longValue() != 0l) {
                            OrdPresent ordPresent = new OrdPresent();
                            ordPresent.setOrderId(rs.getOrderId());
                            ordPresent.setPromId(pp.getPromId());
                            ordPresent.setCouponTypeId(pp.getCoupId());
                            ordPresent.setCouponCnt(pp.getSendAmount().longValue());
                            ordPresent.setCreateStaff(sumbitMainsRequest.getStaff().getId());
                            ordPresent.setCreateTime(DateUtil.getSysDate());
                            ordPresent.setUpdateStaff(sumbitMainsRequest.getStaff().getId());
                            ordPresent.setUpdateTime(DateUtil.getSysDate());
                            ordPresentSV.saveOrdPresent(ordPresent);
                        }
                        // 主订单赠品
                        if (CollectionUtils.isNotEmpty(pp.getPromGiftDTOList())) {
                            for (PromGiftDTO pg : pp.getPromGiftDTOList()) {
                                OrdGift ordGift = new OrdGift();
                                ordGift.setOrderId(rs.getOrderId());
                                ordGift.setFromType(OrdConstants.GiftFromType.TYPE_PROM);
                                ordGift.setGiftId(pg.getGiftId());
                                ordGift.setGiftName(pg.getGiftName());
                                ordGift.setGiftCount(pg.getEveryTimeCnt());
                                ordGift.setCreateStaff(sumbitMainsRequest.getStaff().getId());
                                ordGift.setUpdateStaff(sumbitMainsRequest.getStaff().getId());
                                ordGift.setCreateTime(DateUtil.getSysDate());
                                ordGift.setUpdateTime(DateUtil.getSysDate());
                                ordGiftSV.saveOrdGift(ordGift);
                            }
                        }
                    }

                }

            }
        }

        // 子订单 --start
        if (ordSubMap != null) {
            for (RSumbitSubRequest ssb : rs.getPreOrdSubList()) {
                CartPromItemDTO cpid = ordSubMap.get(ssb.getCartItemId());
                if (cpid != null && !cpid.isIfFulfillProm() && cpid.getOrdPromId() != null
                        && cpid.getOrdPromId() != 0l) {
                    OrdProm ordProm = new OrdProm();
                    ordProm.setOrderId(rs.getOrderId());
                    ordProm.setOrderSubId(ssb.getOrderSubId());
                    ordProm.setPromId(-2l);
                    ordProm.setPromTypeCode("-2");
                    ordProm.setPromTypeName("-2");
                    ordProm.setRelaMainPromId(cpid.getOrdPromId());
                    ordProm.setCreateStaff(sumbitMainsRequest.getStaff().getId());
                    ordProm.setCreateTime(DateUtil.getSysDate());
                    ordProm.setUpdateStaff(sumbitMainsRequest.getStaff().getId());
                    ordProm.setUpdateTime(DateUtil.getSysDate());
                    ordPromSV.saveOrdProm(ordProm);
                }
                if (cpid != null && cpid.isIfFulfillProm()) {
                    // 子订单活动
                    if (cpid.getPromInfoDTO() != null) {
                        OrdProm ordProm = new OrdProm();
                        ordProm.setOrderId(rs.getOrderId());
                        ordProm.setOrderSubId(ssb.getOrderSubId());
                        ordProm.setPromId(cpid.getPromInfoDTO().getId());
                        ordProm.setPromTypeCode(cpid.getPromInfoDTO().getPromTypeCode());
                        ordProm.setPromTypeName(cpid.getPromInfoDTO().getPromTypeName());
                        ordProm.setRelaMainPromId(cpid.getOrdPromId());
                        ordProm.setCreateStaff(sumbitMainsRequest.getStaff().getId());
                        ordProm.setCreateTime(DateUtil.getSysDate());
                        ordProm.setUpdateStaff(sumbitMainsRequest.getStaff().getId());
                        ordProm.setUpdateTime(DateUtil.getSysDate());
                        ordPromSV.saveOrdProm(ordProm);
                    }
                    // 子订单优惠价格等
                    if (cpid.getDiscountMoney() != null
                            && (cpid.getDiscountMoney().longValue() != 0l
                                    || cpid.getDiscountPriceMoney().longValue() != 0l)) {
                        OrdDiscount ordDiscount = new OrdDiscount();
                        ordDiscount.setOrderId(rs.getOrderId());
                        ordDiscount.setOrderSubId(ssb.getOrderSubId());
                        ordDiscount.setDiscountType(OrdConstants.DiscountType.TYPE_ORDER_CODE);
                        ordDiscount.setDiscountTitle(OrdConstants.DiscountType.TYPE_ORDER_NAME);
                        ordDiscount.setDiscountPrice(cpid.getDiscountMoney().longValue()
                                + cpid.getDiscountPriceMoney().longValue());
                        ordDiscount.setCreateStaff(sumbitMainsRequest.getStaff().getId());
                        ordDiscount.setCreateTime(DateUtil.getSysDate());
                        ordDiscount.setUpdateStaff(sumbitMainsRequest.getStaff().getId());
                        ordDiscount.setUpdateTime(DateUtil.getSysDate());
                        ordDiscountSV.saveOrdDiscount(ordDiscount);
                    }

                    if (ordSubPreMap != null) {
                        PromPresentDTO pp = ordSubPreMap.get(ssb.getCartItemId());
                        if (pp != null) {
                            // 子订单赠送积分
                            if (pp.getPoints() != null && pp.getSendType() != null
                                    && (pp.getPoints().doubleValue() > 0.0001
                                            || pp.getPoints().doubleValue() < -0.0001)) {
                                OrdPresent ordPresent = new OrdPresent();
                                ordPresent.setOrderId(rs.getOrderId());
                                ordPresent.setSubOrder(ssb.getOrderSubId());
                                ordPresent.setPromId(pp.getPromId());
                                if (PromConstants.PromDiscountRule.SEND_POINTS_0
                                        .equals(pp.getSendType())) {
                                    ordPresent.setCredits(pp.getPoints().longValue());
                                } else {
                                    ordPresent.setCreditTimes(pp.getPoints());
                                }
                                ordPresent.setCreateStaff(sumbitMainsRequest.getStaff().getId());
                                ordPresent.setCreateTime(DateUtil.getSysDate());
                                ordPresent.setUpdateStaff(sumbitMainsRequest.getStaff().getId());
                                ordPresent.setUpdateTime(DateUtil.getSysDate());
                                ordPresentSV.saveOrdPresent(ordPresent);
                            }
                            // 子订单赠送优惠券
                            if (pp.getSendAmount() != null
                                    && pp.getSendAmount().longValue() != 0l) {
                                OrdPresent ordPresent = new OrdPresent();
                                ordPresent.setOrderId(rs.getOrderId());
                                ordPresent.setSubOrder(ssb.getOrderSubId());
                                ordPresent.setPromId(pp.getPromId());
                                // ordPresent.setCouponTypeId(pp.getCoupId());
                                // ordPresent.setCouponCnt(pp.getSendAmount());
                                ordPresent.setCreateStaff(sumbitMainsRequest.getStaff().getId());
                                ordPresent.setCreateTime(DateUtil.getSysDate());
                                ordPresent.setUpdateStaff(sumbitMainsRequest.getStaff().getId());
                                ordPresent.setUpdateTime(DateUtil.getSysDate());
                                ordPresentSV.saveOrdPresent(ordPresent);
                            }
                            // 子订单赠品
                            if (CollectionUtils.isNotEmpty(pp.getPromGiftDTOList())) {
                                for (PromGiftDTO pg : pp.getPromGiftDTOList()) {
                                    OrdGift ordGift = new OrdGift();
                                    ordGift.setOrderId(rs.getOrderId());
                                    ordGift.setOrdSubId(ssb.getOrderSubId());
                                    ordGift.setFromType(OrdConstants.GiftFromType.TYPE_PROM);
                                    ordGift.setGiftId(pg.getGiftId());
                                    ordGift.setGiftName(pg.getGiftName());
                                    ordGift.setGiftCount(pg.getEveryTimeCnt());
                                    ordGift.setCreateStaff(sumbitMainsRequest.getStaff().getId());
                                    ordGift.setUpdateStaff(sumbitMainsRequest.getStaff().getId());
                                    ordGift.setCreateTime(DateUtil.getSysDate());
                                    ordGift.setUpdateTime(DateUtil.getSysDate());
                                    ordGiftSV.saveOrdGift(ordGift);
                                }
                            }
                        }
                    }
                }
            }
        }
        // 子订单 --end 
        //保存商品域优惠金额（分类折扣优惠）
        for (RSumbitSubRequest ssb : rs.getPreOrdSubList()) {
            if (ordSubMap != null){
                CartPromItemDTO cpid = ordSubMap.get(ssb.getCartItemId());
                if (cpid != null && cpid.isIfFulfillProm()){
                    //有促销活动
                    continue;
                }
            }
            OrdDiscount ordDiscount = new OrdDiscount();
            ordDiscount.setOrderId(rs.getOrderId());
            ordDiscount.setOrderSubId(ssb.getOrderSubId());
            ordDiscount.setDiscountType(OrdConstants.DiscountType.TYPE_GDS_CODE);
            ordDiscount.setDiscountTitle(OrdConstants.DiscountType.TYPE_GDS_NAME);
            ordDiscount.setDiscountPrice((ssb.getBasePrice()-ssb.getBuyPrice())*ssb.getOrderAmount());
            ordDiscount.setCreateStaff(sumbitMainsRequest.getStaff().getId());
            ordDiscount.setCreateTime(DateUtil.getSysDate());
            ordDiscount.setUpdateStaff(sumbitMainsRequest.getStaff().getId());
            ordDiscount.setUpdateTime(DateUtil.getSysDate());
            ordDiscountSV.saveOrdDiscount(ordDiscount);
        }
    }

    private void saveOrdOffline(String ordMainId, RSumbitMainRequest sumbitMainRequest) {
        ROfflineApplyRequest rOfflineApplyRequest = new ROfflineApplyRequest();
        rOfflineApplyRequest.setStaffId(sumbitMainRequest.getStaffId());
        rOfflineApplyRequest.setShopId(sumbitMainRequest.getShopId());
        rOfflineApplyRequest.setOrderId(ordMainId);
        rOfflineApplyRequest.setApplyType(sumbitMainRequest.getPayType());
        rOfflineApplyRequest.getStaff().setId(sumbitMainRequest.getStaff().getId());
        ordOfflineSV.saveOfflineApply(rOfflineApplyRequest);
    }

    @Override
    public PageResponseDTO<RShopOrderResponse> queryOrderManage(
            RQueryOrderRequest rQueryOrderRequest) {
        PageResponseDTO<RShopOrderResponse> pageResponse = PageResponseDTO
                .buildByBaseInfo(rQueryOrderRequest, RShopOrderResponse.class);
        pageResponse.setResult(new ArrayList<RShopOrderResponse>());
        
        PageResponseDTO<ROrderIdResponse> psdoi = queryOrderId(rQueryOrderRequest);
        pageResponse.setCount(psdoi.getCount());
        pageResponse.setPageCount(psdoi.getPageCount());
        if (CollectionUtils.isEmpty(psdoi.getResult())) {
            pageResponse.setResult(null);
            return pageResponse;
        }
        List<SOrderIdx> sdois = new ArrayList<SOrderIdx>();
        for (ROrderIdResponse roi : psdoi.getResult()) {
            SOrderIdx sdo = new SOrderIdx();
            ObjectCopyUtil.copyObjValue(roi, sdo, null, false);
            sdois.add(sdo);
        }

        List<RShopOrderResponse> sqors = queryOrderByIdx(sdois);
        if (CollectionUtils.isEmpty(sqors)) {
        } else {
            pageResponse.getResult().addAll(sqors);
        }

        return pageResponse;
    }
    @Override
    public SOrderDetailsMain queryOrderDetailsMain(String orderId) {
        OrdMain om = queryOrderByOrderId(orderId);
        if (om == null) {
            LogUtil.info(MODULE, "未找到[" + orderId + "]该订单的信息");
            throw new BusinessException(MsgConstants.ProMsgCode.ORD_PRO_312001);
        }
        SOrderDetailsMain sod = new SOrderDetailsMain();
        ObjectCopyUtil.copyObjValue(om, sod, null, false);
        if(sod.getOrderMoney() == null){
            sod.setOrderMoney(0l);
        }
        if(sod.getBuyerMsg() != null){
           sod.setBuyerMsg(sod.getBuyerMsg().replaceAll("\n","<br>"));
        } else {
            sod.setBuyerMsg("暂无内容");
        }
        if(sod.getSellerMsg()!= null){
            sod.setSellerMsg(sod.getSellerMsg().replaceAll("\n","<br>"));
         } else {
             sod.setSellerMsg("暂无内容");
         }
        LogUtil.debug(MODULE,"ordMain="+JSON.toJSONString(sod));
        return sod;
    }

    @Override
    public ROrderDetailsResponse queryOrderDetails(ROrderDetailsRequest rOrderDetailsRequest) {
        ROrderDetailsResponse rodr = new ROrderDetailsResponse();
        // 主订单相关字段
        SOrderDetailsMain sOrderDetailsMain = queryOrderDetailsMain(
                rOrderDetailsRequest.getOrderId());
        // 子订单相关字段
        List<SOrderDetailsSub> sOrderDetailsSubs = this.ordSubSV
                .queryOrderDetailsSub(rOrderDetailsRequest.getOrderId());
        // 订单优惠相关字段
        SOrderDetailsDiscount sOrderDetailsDiscount = this.ordDiscountSV
                .queryOrderDetailsDiscount(rOrderDetailsRequest.getOrderId());
        // 订单跟踪相关字段
        List<SOrderDetailsTrack> sOrderDetailsTracks = this.ordTrackSV
                .queryOrderDetailsTrack(rOrderDetailsRequest.getOrderId());
        // 订单收货地址相关字段
//        SOrderDetailsAddr sOrderDetailsAddr = this.ordDeliveAddrSV
//                .queryOrderDetailsAddr(rOrderDetailsRequest.getOrderId());
        // 订单普通发票相关字段
        SOrderDetailsComm sOrderDetailsComm = this.ordInvoiceCommSV
                .queryOrderDetailsComm(rOrderDetailsRequest.getOrderId());
        // 订单增值税发票相关字段
        SOrderDetailsTax sOrderDetailsTax = this.ordInvoiceTaxSV
                .queryOrderDetailsTax(rOrderDetailsRequest.getOrderId());
        // //付款信息表相关字段--待补充
        
        //物流信息相关字段
        List<SOrderDetailsDelivery> sOrderDetailsDeliverys = this.ordDeliveryBatchSV
                .queryExpressInfoByOrderId(rOrderDetailsRequest.getOrderId());
        List<ROrdExpressDetailsResp> ordExpressDetailsResps = orderExpressSV.queryOrderExpressDetailList(rOrderDetailsRequest.getOrderId());
        

        //赠品信息
        ROrderIdRequest giftReq=new ROrderIdRequest();
        giftReq.setOrderId(rOrderDetailsRequest.getOrderId());
        List<SOrderDetailsGift> sOrderDetailsGifts=ordGiftSV.queryOrdGiftForDetail(giftReq);

        rodr.setsOrderDetailsMain(sOrderDetailsMain);
        rodr.setsOrderDetailsSubs(sOrderDetailsSubs);
        rodr.setsOrderDetailsDiscount(sOrderDetailsDiscount);
        rodr.setsOrderDetailsTracks(sOrderDetailsTracks);
//        rodr.setsOrderDetailsAddr(sOrderDetailsAddr);
        rodr.setsOrderDetailsComm(sOrderDetailsComm);
        rodr.setsOrderDetailsTax(sOrderDetailsTax);
        rodr.setsOrderDetailsDeliverys(sOrderDetailsDeliverys);
        rodr.setOrdExpressDetailsResps(ordExpressDetailsResps);
        rodr.setsOrderDetailsGifts(sOrderDetailsGifts);
        return rodr;
    }

    @Override
    public ROfflinePayResponse querOrderOffline(ROfflinePayRequest rOfflinePayRequest) {
        ROfflinePayResponse ropr = new ROfflinePayResponse();
        OrdMain om = queryOrderByOrderId(rOfflinePayRequest.getOrderId());
        if (om == null) {
            LogUtil.info(MODULE, "未找到[" + rOfflinePayRequest.getOrderId() + "]该订单的信息");
            throw new BusinessException(MsgConstants.ProMsgCode.ORD_PRO_312001);
        }

        ObjectCopyUtil.copyObjValue(om, ropr, null, false);
        LogUtil.info(MODULE, ropr.toString());
        return ropr;
    }

    /*
     * 生成主订单编号的方法
     */
    public String createOrdMainId() {
        DateFormat dfmt = new SimpleDateFormat("yyMMdd");
        String nowDate = dfmt.format(new Date());
        return OrdConstants.Common.RW_CODE + nowDate
                + StringUtil.lPad(seqOrdMain.nextValue().toString(), "0", 8);

    }

    /**
     * 
     * findOrdMianByOrdId:根据订单id查询订单主表全部信息. <br/>
     * 
     * @author linwei
     * @param orderId
     * @return
     * @since JDK 1.6
     */
    public ROrdMainResponse findOrdMianByOrderId(String orderId) {
        OrdMainCriteria omc = new OrdMainCriteria();
        omc.createCriteria().andIdEqualTo(orderId);
        List<OrdMain> ordMains = this.ordMainMapper.selectByExample(omc);
        if (CollectionUtils.isEmpty(ordMains)) {
            return null;
        }
        ROrdMainResponse sod = new ROrdMainResponse();
        ObjectCopyUtil.copyObjValue(ordMains.get(0), sod, null, false);
        return sod;
    }

    /** 
     * getBackStatus:获取退货退款状态. <br/> 
     * @author cbl  
     * @since JDK 1.6 
     */ 
    private void getBackStatus(SCustomerOrdMain sCustomerOrdMain){
        
        if(sCustomerOrdMain.getPayTime() == null){
            sCustomerOrdMain.setRefundStatus("0");//可以退款
            sCustomerOrdMain.setBackGdsStatus("0");//可以退货
            return;
        }
        
        //退款判断
        SRefundInfo sRefundInfo = this.ordBackApplySV.queryRefundStatus(sCustomerOrdMain.getOrderId());
        if(sRefundInfo != null) {
            if("1".equals(sRefundInfo.getRefundStatus())){
                sCustomerOrdMain.setRefundStatus("1");  //退款流程中
                sCustomerOrdMain.setRefundId(sRefundInfo.getRefundId());
            } else if("2".equals(sRefundInfo.getRefundStatus())){
                sCustomerOrdMain.setRefundStatus("2");  //订单含有虚拟产品不允许退款
            }
        } else {
            //获取允许退款最长时间（人卫扩展不需要时间 ）
            String onLineHourStr1 = BaseParamUtil.fetchParamValue("ORD_PAY_LIMIT", "3");
            int onLineHour1 = 0;
            try{
                onLineHour1=Integer.parseInt(onLineHourStr1);
            }catch(Exception e){
                LogUtil.error(MODULE, "设置超时取消时间错误", e);
                throw new BusinessException(ServiceMsgCode.ORD_SERVER_350012);
            }
            Timestamp onLineLimitTime1 = DateUtil.getSysDate();
            Calendar calendar1=Calendar.getInstance();   
            calendar1.setTime(onLineLimitTime1); 
            calendar1.add(Calendar.HOUR, -onLineHour1);
            onLineLimitTime1 = new Timestamp(calendar1.getTimeInMillis());
            //下单时间 
            if(sCustomerOrdMain.getPayTime()!= null && sCustomerOrdMain.getPayTime().compareTo(onLineLimitTime1) < 0 ){
                sCustomerOrdMain.setRefundStatus("3");
            } else {
                sCustomerOrdMain.setRefundStatus("0");  //可以退款
            }
            sCustomerOrdMain.setRefundStatus("0");  //可以退款
        }
        
        //退货判断
        SRefundInfo sbackGdsInfo = this.ordBackApplySV.queryBackGdsStatus(sCustomerOrdMain.getOrderId());
        if(sbackGdsInfo != null){
            if("1".equals(sbackGdsInfo.getRefundStatus())){
                sCustomerOrdMain.setBackGdsStatus("1");  //退货流程中
                sCustomerOrdMain.setBackGdsId(sbackGdsInfo.getRefundId());
            } 
        }else {
            //获取允许退货最长时间
            String onLineHourStr = BaseParamUtil.fetchParamValue("ORD_PAY_LIMIT", "2");
            int onLineHour = 0;
            try{
                onLineHour=Integer.parseInt(onLineHourStr);
            }catch(Exception e){
                LogUtil.error(MODULE, "设置超时取消时间错误", e);
                throw new BusinessException(ServiceMsgCode.ORD_SERVER_350012);
            }
            Timestamp onLineLimitTime = DateUtil.getSysDate();
            Calendar calendar=Calendar.getInstance();   
            calendar.setTime(onLineLimitTime); 
            calendar.add(Calendar.HOUR, -onLineHour);
            onLineLimitTime = new Timestamp(calendar.getTimeInMillis());
            //下单时间 --可以考虑用收货时间
            if(sCustomerOrdMain.getDeliverDate()!= null && sCustomerOrdMain.getDeliverDate().compareTo(onLineLimitTime) < 0 ){
                sCustomerOrdMain.setBackGdsStatus("2");  //超过退货时限
            } else {
                sCustomerOrdMain.setBackGdsStatus("0"); //可以退货
            }
        }
    }
    
    private List<RCustomerOrdResponse> queryByOrderIdForCustomer(List<SOrderIdx> sOrderIdxs) {
        List<RCustomerOrdResponse> rcors = new ArrayList<RCustomerOrdResponse>();
        for (SOrderIdx soi : sOrderIdxs) {
            RCustomerOrdResponse rcor = new RCustomerOrdResponse();

            // 主订单相关字段
            SOrderDetailsMain sodm = queryOrderDetailsMain(soi.getOrderId());
            SCustomerOrdMain sCustomerOrdMain = new SCustomerOrdMain();
            ObjectCopyUtil.copyObjValue(sodm, sCustomerOrdMain, null, false);
            sCustomerOrdMain.setOrderId(soi.getOrderId());
            getBackStatus(sCustomerOrdMain);
            rcor.setsCustomerOrdMain(sCustomerOrdMain);

            // 子订单相关字段
            List<SOrderDetailsSub> sOrderDetailsSubs = this.ordSubSV
                    .queryOrderDetailsSub(soi.getOrderId());
            rcor.setsOrderDetailsSubs(sOrderDetailsSubs);
            rcors.add(rcor);
        }
        return rcors;
    }

    @Override
    public PageResponseDTO<RCustomerOrdResponse> queryOrderByStaffIdPage(
            RQueryOrderRequest rQueryOrderRequest) {

        PageResponseDTO<RCustomerOrdResponse> pageResponse = PageResponseDTO
                .buildByBaseInfo(rQueryOrderRequest, RCustomerOrdResponse.class);
        pageResponse.setResult(new ArrayList<RCustomerOrdResponse>());

        PageResponseDTO<SOrderIdx> psoi = this.ordMainStaffIdxSV
                .queryOrderByStaffIdPage(rQueryOrderRequest);
        pageResponse.setCount(psoi.getCount());
        pageResponse.setPageCount(psoi.getPageCount());
        if (CollectionUtils.isEmpty(psoi.getResult())) {
            LogUtil.info(MODULE, "未找到订单数据");
            pageResponse.setResult(null);
            return pageResponse;
            // throw new BusinessException(MsgConstants.ProMsgCode.ORD_PRO_312000);
        } else {
            List<RCustomerOrdResponse> rcor = queryByOrderIdForCustomer(psoi.getResult());
            pageResponse.getResult().addAll(rcor);
        }
        return pageResponse;
    }

    @Override
    public void removeOrd(ROrderDetailsRequest info) {
        LogUtil.info(MODULE, "取消订单服务开始:"+JSON.toJSONString(info));
        ThingLock thingLock = new ThingLock();
        thingLock.setId(info.getOrderId()); //同一时刻一个订单只能操作一次取消订单操作
        thingLock.setType(CommonConstants.LockType.LOCK_TYPE_REMOVE);
        this.thingLockSV.addThingLock(thingLock);
        
        OrdMain ordMain = queryOrderByOrderId(info.getOrderId());
        if(ordMain == null){
            LogUtil.info(MODULE, "未找到[" + info.getOrderId() + "]该订单的信息");
        }
        
        if(OrdConstants.OrderStatus.ORDER_STATUS_CANCLE.equals(ordMain.getStatus())){
            LogUtil.info(MODULE, "订单已取消");
            throw new BusinessException(MsgConstants.ProMsgCode.ORD_PRO_352005);
        }
        
        SBaseAndStatusInfo sBaseAndStatusInfo=new SBaseAndStatusInfo();
        sBaseAndStatusInfo.setOrderId(info.getOrderId());
        sBaseAndStatusInfo.setStatus(OrdConstants.OrderStatus.ORDER_STATUS_CANCLE);
        sBaseAndStatusInfo.setStaffId(ordMain.getStaffId());
        sBaseAndStatusInfo.setShopId(ordMain.getShopId());
        sBaseAndStatusInfo.setOperatorId(info.getStaff().getId());
        updateOrderStatus(sBaseAndStatusInfo);
        
        OrdTrack ot = new OrdTrack();
        if(OrdConstants.DealFrom.FROM_SHOP.equals(info.getDelFrom())){
            ot.setNode(OrdConstants.OrderTwoStatus.STATUS_PAID_OFFLINE_VERIFY_NOT);
            ot.setNodeDesc(OrdConstants.NodeDesc.STATUS_CLOSE);
        } else if(OrdConstants.DealFrom.FROM_AUTO.equals(info.getDelFrom())){
            ot.setNode(OrdConstants.OrderTwoStatus.STATUS_CANCLE_SYSTEM);
            ot.setNodeDesc(OrdConstants.NodeDesc.STATUS_CANCLE_AUTO);
            sBaseAndStatusInfo.setOperatorId(OrdConstants.Common.DEFAULT_STAFFID);
        } else {
            ot.setNode(OrdConstants.OrderTwoStatus.STATUS_CANCLE_BUYER);
            ot.setNodeDesc(OrdConstants.NodeDesc.STATUS_CANCLE_BUYER);
        }
        
        ot.setOrderId(sBaseAndStatusInfo.getOrderId());
        ot.setCreateStaff(sBaseAndStatusInfo.getOperatorId());
        ot.setCreateTime(DateUtil.getSysDate());
        this.ordTrackSV.saveOrdTrack(ot);
        
        this.ordOfflineSV.deleteOrdOffline(info);
        
        this.thingLockSV.removeThingLock(thingLock);
    }

    /**
     * 
     * 支付成功订单处理逻辑.
     * 
     * @see com.zengshi.ecp.order.service.busi.interfaces.IOrdMainSV#savePaySucc(com.zengshi.ecp.order.dubbo.dto.pay.PaySuccInfo)
     */
    @Override
    public void savePaySucc(PaySuccInfo paySuccInfo) {

        // 判断金额，非线上支付不需要
        OrdMain ordMain = this.queryOrderByOrderId(paySuccInfo.getOrderId());
        if (ordMain == null) {
            throw new BusinessException(MsgConstants.ServiceMsgCode.ORD_SERVER_310001);
        } else {
            if (ordMain.getRealMoney() != paySuccInfo.getPayment()) {

            }
        }

        // 更新订单主表信息
        OrdMain record = new OrdMain();
        record.setPayFlag(Order.ORDER_PAY_FLAG_1);
        record.setPayWay(paySuccInfo.getPayWay());
        record.setPayTime(DateUtil.getSysDate());

        OrdMainCriteria example = new OrdMainCriteria();
        example.createCriteria().andIdEqualTo(paySuccInfo.getOrderId()).andPayFlagEqualTo(Order.ORDER_PAY_FLAG_0)
        .andStatusEqualTo(OrderStatus.ORDER_STATUS_SUBMIT);
        int flag = ordMainMapper.updateByExampleSelective(record, example);
        if (flag == 0) {
            throw new BusinessException(PayServiceMsgCode.PAY_SERVER_310008);
        }

        // 更新状态
        SBaseAndStatusInfo sBaseAndStatusInfo = new SBaseAndStatusInfo();
        sBaseAndStatusInfo.setOrderId(paySuccInfo.getOrderId());
        sBaseAndStatusInfo.setStatus(OrderStatus.ORDER_STATUS_PAID);
        String orderTwoStatus = "";
        String nodeDesc = "";
        if (PayStatus.PAY_TYPE_01.equals(paySuccInfo.getPayType())) {
            orderTwoStatus = OrderTwoStatus.STATUS_PAID_ONLINE;
            nodeDesc = NodeDesc.STATUS_PAID_ONLINE;
        } else {
            orderTwoStatus = OrderTwoStatus.STATUS_PAID_OFFLINE_VERIFY_PASS;
            nodeDesc = NodeDesc.STATUS_PAID_OFFLINE_VERIFY_PASS;
        }
        sBaseAndStatusInfo.setOrderTwoStatus(orderTwoStatus);
        sBaseAndStatusInfo.setStaffId(ordMain.getStaffId());// 更新状态时使用订单表里的staffid更新索引表
        sBaseAndStatusInfo.setShopId(ordMain.getShopId());// 更新状态时使用订单表里的shopid更新索引表
        sBaseAndStatusInfo.setOperatorId(paySuccInfo.getStaffId());
        this.updateOrderStatus(sBaseAndStatusInfo);

        // 记录订单日志表
        OrdTrack ordTrack = new OrdTrack();
        ordTrack.setOrderId(paySuccInfo.getOrderId());
        ordTrack.setOrderSubId("");
        ordTrack.setNode(orderTwoStatus);
        ordTrack.setNodeDesc(nodeDesc);
        ordTrack.setRemark("");
        //ordTrack.setCreateStaff(paySuccInfo.getStaffId());
        ordTrack.setCreateStaff(ordMain.getStaffId());
        ordTrack.setCreateTime(DateUtil.getSysDate());
        ordTrackSV.saveOrdTrack(ordTrack);
    }

    @Override
    public ROrdCountResponse queryOrderCountByStaff(RQueryOrderRequest rQueryOrderRequest) {

        return this.ordMainStaffIdxSV.queryOrderCountByStaff(rQueryOrderRequest);
    }

    @Override
    public Long queryOrderCountByShop(RQueryOrderRequest rQueryOrderRequest) {
        return this.ordMainShopIdxSV.queryOrderCountByShop(rQueryOrderRequest);
    }

    @Override
    public ROrdCartsCommRequest queryAssembleParamForAll(ROrderDetailsRequest rOrderDetailsRequest) {
        ROrdCartsCommRequest rOrdCartsCommRequest = new ROrdCartsCommRequest();
        List<ROrdCartCommRequest> rOrdCartCommRequests = new ArrayList<ROrdCartCommRequest>();
        
        OrdMain ordMain = queryOrderByOrderId(rOrderDetailsRequest.getOrderId());
        if(ordMain == null){
            LogUtil.info(MODULE, "未找到[" + rOrderDetailsRequest.getOrderId() + "]该订单信息");
            throw new BusinessException(MsgConstants.ProMsgCode.ORD_PRO_312001);
        }
        ROrdCartCommRequest  rOrdCartCommRequest = new ROrdCartCommRequest();
        //补齐订单信息
        ObjectCopyUtil.copyObjValue(ordMain, rOrdCartCommRequest, null,false);
        
        
        List<OrdSub> ordSubs = this.ordSubSV.queryOrderSubByOrderId(rOrderDetailsRequest.getOrderId());
        
        if (CollectionUtils.isEmpty(ordSubs)) {
            LogUtil.info(MODULE, "未找到[" + rOrderDetailsRequest.getOrderId() + "]该订单的子订单信息");
            throw new BusinessException(MsgConstants.ProMsgCode.ORD_PRO_312002);
        }
        //补齐明细信息
        List<ROrdCartItemCommRequest> rOrdCartItemCommRequests = new ArrayList<ROrdCartItemCommRequest>();
        for(OrdSub ordSub: ordSubs){
            ROrdCartItemCommRequest rOrdCartItemCommRequest = new ROrdCartItemCommRequest();
            ObjectCopyUtil.copyObjValue(ordSub, rOrdCartItemCommRequest, null, false);
            rOrdCartItemCommRequests.add(rOrdCartItemCommRequest);
        }
        //每个店铺的明细列表
        rOrdCartCommRequest.setOrdCartItemCommList(rOrdCartItemCommRequests);
            
        //每个购物车信息
        rOrdCartCommRequests.add(rOrdCartCommRequest);
        //该买家的购物车信息列表
        rOrdCartsCommRequest.setOrdCartsCommList(rOrdCartCommRequests);
        rOrdCartsCommRequest.setStaffId(rOrderDetailsRequest.getStaff().getId());
        return rOrdCartsCommRequest;
    }

    @Override
    public ROrdAddressResponse queryOrderAddress(ROrderIdRequest rOrderIdRequest) {
        ROrdAddressResponse rOrdAddressResponse = new ROrdAddressResponse();
        OrdMain om = queryOrderByOrderId(rOrderIdRequest.getOrderId());
        ObjectCopyUtil.copyObjValue(om, rOrdAddressResponse, null, false);
        return rOrdAddressResponse;
    }

    @Override
    public void updateOrderAddress(ROrdAddressRequest rOrdAddressRequest) {
        OrdMainCriteria omc = new OrdMainCriteria();
        omc.createCriteria().andIdEqualTo(rOrdAddressRequest.getOrderId());
        OrdMain om = new OrdMain();
        ObjectCopyUtil.copyObjValue(rOrdAddressRequest, om, null, false);
        if(rOrdAddressRequest.getStaff()!=null&&rOrdAddressRequest.getStaff().getId()!=0){
        	om.setUpdateStaff(rOrdAddressRequest.getStaff().getId());
        }else{
        	om.setUpdateStaff(1000L);
        }
        om.setUpdateTime(DateUtil.getSysDate());
        this.ordMainMapper.updateByExampleSelective(om, omc);
    }
    /** 
     * queryOrderIdByInvoiceType:开票信息查询. <br/> 
     * @author cbl 
     * @param rQueryOrderRequest
     * @return 
     * @since JDK 1.6 
     */ 
    private PageResponseDTO<ROrderIdResponse> queryOrderIdByInvoiceType(RQueryOrderRequest rQueryOrderRequest) {
        final OrdMainCriteria omsic = new OrdMainCriteria();
        omsic.setLimitClauseCount(rQueryOrderRequest.getPageSize());
        omsic.setLimitClauseStart(rQueryOrderRequest.getStartRowIndex());
        omsic.setOrderByClause("id desc");
        Criteria ca = omsic.createCriteria();
        ca.andInvoiceTypeEqualTo("0").andPayFlagEqualTo(OrdConstants.Order.ORDER_PAY_FLAG_1);
          
        if(rQueryOrderRequest.getBegDate() != null){
            ca.andOrderTimeGreaterThanOrEqualTo(rQueryOrderRequest.getBegDate());
        }
        if(rQueryOrderRequest.getEndDate() != null){
            ca.andOrderTimeLessThanOrEqualTo(rQueryOrderRequest.getEndDate());
        }
        if(StringUtil.isNotBlank(rQueryOrderRequest.getBillingFlag())){
            ca.andBillingFlagEqualTo(rQueryOrderRequest.getBillingFlag());
        }
        if(StringUtil.isNotBlank(rQueryOrderRequest.getOrderId())){
            ca.andIdEqualTo(rQueryOrderRequest.getOrderId());
        }
        if(rQueryOrderRequest.getShopId() != null){
            ca.andShopIdEqualTo(rQueryOrderRequest.getShopId());
        }
        List<String> status = new ArrayList<String>();
        status.add(OrdConstants.OrderStatus.ORDER_STATUS_BACKGDS);
        status.add(OrdConstants.OrderStatus.ORDER_STATUS_CANCLE);
        status.add(OrdConstants.OrderStatus.ORDER_STATUS_REFUND);
        ca.andStatusNotIn(status);
        //在退款、退货流程中的过滤掉
        List<String> status1 = new ArrayList<String>();
        status1.add(OrdConstants.OrderTwoStatus.STATUS_BACKGDS_YES);
        status1.add(OrdConstants.OrderTwoStatus.STATUS_REFUND_YES);
        ca.andOrderTwoStatusNotIn(status1);
        return super.queryByPagination(rQueryOrderRequest, omsic, true,
                new PaginationCallback<OrdMain, ROrderIdResponse>() {

                    @Override
                    public List<OrdMain> queryDB(BaseCriteria bCriteria) {
                        return ordMainMapper.selectByExample((OrdMainCriteria) bCriteria);
                    }

                    @Override
                    public long queryTotal(BaseCriteria bCriteria) {
                        return ordMainMapper.countByExample((OrdMainCriteria) bCriteria);
                    }

                    @Override
                    public List<Comparator<OrdMain>> defineComparators() {
                        List<Comparator<OrdMain>> ls = new ArrayList<Comparator<OrdMain>>();
                        ls.add(new Comparator<OrdMain>(){

                            @Override
                            public int compare(OrdMain o1, OrdMain o2) {
                                return o2.getId().compareTo(o1.getId());
                            }
                            
                        });
                        return ls;
                    }

                    @Override
                    public ROrderIdResponse warpReturnObject(OrdMain ordMain) {
                        ROrderIdResponse sdoi = new ROrderIdResponse();
                        BeanUtils.copyProperties(ordMain, sdoi);
                        sdoi.setOrderId(ordMain.getId());
                        sdoi.setOrderAmount(ordMain.getOrderAmount());
                        return sdoi;
                    }
                });
    }
    
    private List<ROrdInvoiceResponse> queryInvoiceByOrderId(List<SOrderIdx> sDelyOrderIdxs){
        List<ROrdInvoiceResponse> sqors = new ArrayList<ROrdInvoiceResponse>();
        for (SOrderIdx sdoi : sDelyOrderIdxs) {
            ROrdInvoiceResponse sqor = new ROrdInvoiceResponse();
            OrdMain om = queryOrderByOrderId(sdoi.getOrderId());
            if(om == null){
                LogUtil.info(MODULE, "未找到[" + sdoi.getOrderId() + "]该订单的信息");
                throw new BusinessException(MsgConstants.ProMsgCode.ORD_PRO_312001);
            }
            ObjectCopyUtil.copyObjValue(om, sqor, null, false);
            sqor.setOrderId(sdoi.getOrderId());
            sqor.setOrderAmount(sdoi.getOrderAmount());
            SOrderDetailsComm sOrderDetailsComm = this.ordInvoiceCommSV.queryOrderDetailsComm(sdoi.getOrderId());
            ObjectCopyUtil.copyObjValue(sOrderDetailsComm, sqor, null, false);
            sqors.add(sqor);
        }
        return sqors;
    }
    @Override
    public PageResponseDTO<ROrdInvoiceResponse> queryOrderInvoiceInfo(RQueryOrderRequest rQueryOrderRequest)
            throws BusinessException {
        PageResponseDTO<ROrdInvoiceResponse> pageResponse = PageResponseDTO
                .buildByBaseInfo(rQueryOrderRequest, ROrdInvoiceResponse.class);
        pageResponse.setResult(new ArrayList<ROrdInvoiceResponse>());
        
        PageResponseDTO<ROrderIdResponse> psdoi = queryOrderIdByInvoiceType(rQueryOrderRequest);
        pageResponse.setCount(psdoi.getCount());
        pageResponse.setPageCount(psdoi.getPageCount());
        if (CollectionUtils.isEmpty(psdoi.getResult())) {
            pageResponse.setResult(null);
            return pageResponse;
        }
        List<SOrderIdx> sdois = new ArrayList<SOrderIdx>();
        for (ROrderIdResponse roi : psdoi.getResult()) {
            SOrderIdx sdo = new SOrderIdx();
            ObjectCopyUtil.copyObjValue(roi, sdo, null, false);
            sdois.add(sdo);
        }

        List<ROrdInvoiceResponse> sqors = queryInvoiceByOrderId(sdois);
        if (CollectionUtils.isEmpty(sqors)) {
        } else {
            pageResponse.getResult().addAll(sqors);
        }

        return pageResponse;
    }

    @Override
    public void updateOrderInvoiceInfo(ROrdInvoiceRequest rOrdInvoiceRequest)
            throws BusinessException {
        OrdMainCriteria omc = new OrdMainCriteria();
        omc.createCriteria().andIdEqualTo(rOrdInvoiceRequest.getOrderId());
        OrdMain om = new OrdMain();
        ObjectCopyUtil.copyObjValue(rOrdInvoiceRequest, om, null, false);
        om.setBillingFlag("1"); //已开票
        om.setUpdateStaff(rOrdInvoiceRequest.getStaff().getId());
        om.setUpdateTime(DateUtil.getSysDate());
        this.ordMainMapper.updateByExampleSelective(om, omc);
    }
    
    @Override
    public List<ROrdMainResponse> queryNotReceiptOrder(ROrdReceiptRequest rOrdReceiptRequest)
            throws BusinessException {
        String hourStr = BaseParamUtil.fetchParamValue("ORD_RECEIVE_LIMIT", "0");
        int hour = 0;
        try{
            hour=Integer.parseInt(hourStr);
        }catch(Exception e){
            LogUtil.error(MODULE, "设置超时未收货时间错误", e);
            throw new BusinessException(ServiceMsgCode.ORD_SERVER_310032);
        }
        Timestamp limitTiem = DateUtil.getSysDate();
        Calendar calendar=Calendar.getInstance();   
        calendar.setTime(limitTiem); 
        calendar.add(Calendar.HOUR, -hour);
        limitTiem = new Timestamp(calendar.getTimeInMillis());
        
        //入参目前应该是:0,1,2,3;   会根据这个序列进行分库分表规则的指定
        DistributeRuleAssist.setTableIndex(rOrdReceiptRequest.getTableIndex());
        
        OrdMainCriteria example = new OrdMainCriteria();
        OrdMainCriteria.Criteria criteria = example.createCriteria();
        criteria.andStatusEqualTo(OrderStatus.ORDER_STATUS_SEND_ALL);
        criteria.andDeliverDateLessThanOrEqualTo(limitTiem);
        example.setOrderByClause(" DELIVER_DATE asc ");
        example.setLimitClauseStart(0);
        example.setLimitClauseCount(rOrdReceiptRequest.getCount());
        
        PageResponseDTO<ROrdMainResponse> pageResponseDTO = queryByPagination(rOrdReceiptRequest, example);
        //查询之后，要马上调用：用于清理刚才设定的表的分库分表；
        DistributeRuleAssist.clearTableIndex();
        return pageResponseDTO.getResult();
    }
    
    @Override
    public List<ROrdMainResponse> queryNeedCancelOrder(RQueryOrderRequest rQueryOrderRequest)
            throws BusinessException {
        //获取线上支付超时时间
        String onLineHourStr = BaseParamUtil.fetchParamValue("ORD_PAY_LIMIT", "0");
        int onLineHour = 0;
        try{
            onLineHour=Integer.parseInt(onLineHourStr);
        }catch(Exception e){
            LogUtil.error(MODULE, "设置超时取消时间错误", e);
            throw new BusinessException(ServiceMsgCode.ORD_SERVER_350012);
        }
        Timestamp onLineLimitTime = DateUtil.getSysDate();
        Calendar calendar=Calendar.getInstance();   
        calendar.setTime(onLineLimitTime); 
        calendar.add(Calendar.HOUR, -onLineHour);
        onLineLimitTime = new Timestamp(calendar.getTimeInMillis());
        
        //获取非线上支付超时时间
        String underLineHourStr = BaseParamUtil.fetchParamValue("ORD_PAY_LIMIT", "1");
        int underLineHour = 0;
        try{
            underLineHour=Integer.parseInt(underLineHourStr);
        }catch(Exception e){
            LogUtil.error(MODULE, "设置超时取消时间错误", e);
            throw new BusinessException(ServiceMsgCode.ORD_SERVER_350012);
        }
        Timestamp underLineLimitTime = DateUtil.getSysDate();
        calendar=Calendar.getInstance();   
        calendar.setTime(underLineLimitTime); 
        calendar.add(Calendar.HOUR, -underLineHour);
        underLineLimitTime = new Timestamp(calendar.getTimeInMillis());
        
        //入参目前应该是:0,1,2,3;   会根据这个序列进行分库分表规则的指定
        DistributeRuleAssist.setTableIndex(rQueryOrderRequest.getTableIndex());
        
        OrdMainCriteria example = new OrdMainCriteria();
        OrdMainCriteria.Criteria onLineCriteria = example.createCriteria();
        onLineCriteria.andStatusEqualTo(OrderStatus.ORDER_STATUS_SUBMIT);
        onLineCriteria.andOrderTimeLessThan(onLineLimitTime);
        onLineCriteria.andPayFlagEqualTo(Order.ORDER_PAY_FLAG_0);
        onLineCriteria.andPayTypeEqualTo(Order.ORDER_PAY_TYPE_0);
        
        OrdMainCriteria.Criteria underLineCriteria = example.createCriteria();
        underLineCriteria.andStatusEqualTo(OrderStatus.ORDER_STATUS_SUBMIT);
        underLineCriteria.andOrderTimeLessThan(underLineLimitTime);
        underLineCriteria.andPayFlagEqualTo(Order.ORDER_PAY_FLAG_0);
        underLineCriteria.andPayTypeNotEqualTo(Order.ORDER_PAY_TYPE_0);
        
        example.or(underLineCriteria);
        example.setOrderByClause(" ORDER_TIME asc ");
        example.setLimitClauseStart(0);
        example.setLimitClauseCount(rQueryOrderRequest.getCount());
        
        PageResponseDTO<ROrdMainResponse> pageResponseDTO = queryByPagination(rQueryOrderRequest,example);        
        //查询之后，要马上调用：用于清理刚才设定的表的分库分表；
        DistributeRuleAssist.clearTableIndex();
        return pageResponseDTO.getResult();
    }
    
    private PageResponseDTO<ROrdMainResponse> queryByPagination(BaseInfo baseInfo,BaseCriteria criteria){
      return super.queryByPagination(baseInfo, criteria, true, new PaginationCallback<OrdMain, ROrdMainResponse>() {

          @Override
          public List<OrdMain> queryDB(BaseCriteria bCriteria) {
              return ordMainMapper.selectByExample((OrdMainCriteria)bCriteria) ;
          }

          @Override
          public long queryTotal(BaseCriteria bCriteria) {
              return ordMainMapper.countByExample((OrdMainCriteria)bCriteria);
          }

          @Override
          public List<Comparator<OrdMain>> defineComparators() {
              List<Comparator<OrdMain>> ls = new ArrayList<Comparator<OrdMain>>();
              ls.add(new Comparator<OrdMain>(){

                  @Override
                  public int compare(OrdMain o1, OrdMain o2) {
                      return o2.getOrderTime().compareTo(o1.getOrderTime());
                  }
                  
              });
              return ls;
          }

          @Override
          public ROrdMainResponse warpReturnObject(OrdMain ordMain) {
              ROrdMainResponse sdoi = new ROrdMainResponse();
                  BeanUtils.copyProperties(ordMain, sdoi);
                  return sdoi;
          }

          
      });
    }
    
    private PageResponseDTO<ROrdMainResponse> queryByPagination2(BaseInfo baseInfo,BaseCriteria criteria){
        return super.queryByPagination(baseInfo, criteria, true, new PaginationCallback<OrdMain, ROrdMainResponse>() {

            @Override
            public List<OrdMain> queryDB(BaseCriteria bCriteria) {
                return ordMainMapper.selectByExample((OrdMainCriteria)bCriteria) ;
            }

            @Override
            public long queryTotal(BaseCriteria bCriteria) {
                return ordMainMapper.countByExample((OrdMainCriteria)bCriteria);
            }

            @Override
            public List<Comparator<OrdMain>> defineComparators() {
                List<Comparator<OrdMain>> ls = new ArrayList<Comparator<OrdMain>>();
                ls.add(new Comparator<OrdMain>(){

                    @Override
                    public int compare(OrdMain o1, OrdMain o2) {
                        return o2.getPayTime().compareTo(o1.getPayTime());
                    }
                    
                });
                return ls;
            }

            @Override
            public ROrdMainResponse warpReturnObject(OrdMain ordMain) {
                ROrdMainResponse sdoi = new ROrdMainResponse();
                    BeanUtils.copyProperties(ordMain, sdoi);
                    return sdoi;
            }

            
        });
      }

    


    

    private Long querySumOrderMoney(RQueryOrderRequest rQueryOrderRequest){
        OrdMainCriteria omsic = createCriteria(rQueryOrderRequest,"1");
        return this.ordMainUalMapper.countSumOrderMoneyByExample(omsic);
    }
    
    private Long querySumRealMoney(RQueryOrderRequest rQueryOrderRequest){
        OrdMainCriteria omsic = createCriteria(rQueryOrderRequest,"2");
        return this.ordMainUalMapper.countSumRealMoneyByExample(omsic);
    }
    
    private Long queryOrderCount(RQueryOrderRequest rQueryOrderRequest){
        OrdMainCriteria omsic = createCriteria(rQueryOrderRequest,"3");
        return this.ordMainMapper.countByExample(omsic);
    }
    private Long queryPayedCount(RQueryOrderRequest rQueryOrderRequest){
        OrdMainCriteria omsic = createCriteria(rQueryOrderRequest,"4");
        return this.ordMainMapper.countByExample(omsic);
    }
    
    @Override
    public ROrderSummaryResponse queryOrderSummaryData(RQueryOrderRequest rQueryOrderRequest)
            throws BusinessException {
        ROrderSummaryResponse  rosr = new ROrderSummaryResponse();
        rosr.setOrderCount(this.queryOrderCount(rQueryOrderRequest));
        rosr.setPayedCount(this.queryPayedCount(rQueryOrderRequest));
        rosr.setSumOrderMoney(this.querySumOrderMoney(rQueryOrderRequest));
        rosr.setSumRealMoney(this.querySumRealMoney(rQueryOrderRequest));
        if(rosr.getOrderCount() == 0 || rosr.getPayedCount() == 0){
            rosr.setPayedRate(0l);
        } else {
            rosr.setPayedRate(rosr.getPayedCount()/rosr.getOrderCount());
        }
        return rosr;
    }
    
    @Override
    public OrdMain queryOrderByIdAndStaff(SBaseSplitInfo sBaseSplitInfo) throws BusinessException {
        OrdMainCriteria omc = new OrdMainCriteria();
        omc.createCriteria().andIdEqualTo(sBaseSplitInfo.getOrderId())
                            .andStaffIdEqualTo(sBaseSplitInfo.getStaffId());
        List<OrdMain> ordMains = this.ordMainMapper.selectByExample(omc);
        if (CollectionUtils.isEmpty(ordMains)) {
            return null;
        }
        return ordMains.get(0);
    }

    @Override
    public PageResponseDTO<ROrdMainResponse> querynotInAuditTradeCheckOrders(
            RQueryOrderRequest rQueryOrderRequest) throws BusinessException {
        OrdMainCriteria example = new OrdMainCriteria();
        OrdMainCriteria.Criteria criteria = example.createCriteria();
        if(rQueryOrderRequest.getBegDate()!=null){
            criteria.andPayTimeGreaterThanOrEqualTo(rQueryOrderRequest.getBegDate());
        }
        if(rQueryOrderRequest.getEndDate()!=null){
            criteria.andPayTimeLessThanOrEqualTo(rQueryOrderRequest.getEndDate());
        }
        if(rQueryOrderRequest.getShopId()!=null){
            criteria.andShopIdEqualTo(rQueryOrderRequest.getShopId());
        }        
        criteria.andPayFlagEqualTo(Order.ORDER_PAY_FLAG_1);
        criteria.andPayTypeEqualTo(Order.ORDER_PAY_TYPE_0);
        criteria.andIsInAuditFileEqualTo(PayStatus.ORD_MAIN_IS_IN_AUDIT_FILE_0);
        List<String> payWayList = new ArrayList<String>();
        payWayList.add("9998");
        criteria.andPayWayNotIn(payWayList);
        example.setLimitClauseStart(rQueryOrderRequest.getStartRowIndex());
        example.setLimitClauseCount(rQueryOrderRequest.getPageSize());
        PageResponseDTO<ROrdMainResponse> pageResponseDTO = queryByPagination2(rQueryOrderRequest,example);
        if(CollectionUtils.isNotEmpty(pageResponseDTO.getResult())) {
        	
        	for(ROrdMainResponse ordMainResponse:pageResponseDTO.getResult()){
        		if(ordMainResponse.getStaffId()!=null){
        			CustInfoResDTO custInfoResDTO = this.staffUnionRSV.findCustOrAdminByStaffId(ordMainResponse.getStaffId());
        			if(custInfoResDTO != null && custInfoResDTO.getStaffCode() != null){
        				ordMainResponse.setStaffName(custInfoResDTO.getStaffCode());
        			} else {
        				ordMainResponse.setStaffName(ordMainResponse.getStaffId().toString());
        			}
        		}
        	}
        }
        return pageResponseDTO;
    }
    
    @Override
    public int updateIsInAuditFile(String orderId, String status,String payWay) {
        OrdMain record = new OrdMain();
        record.setId(orderId);
        record.setIsInAuditFile(status);
        OrdMainCriteria example = new OrdMainCriteria();
        example.createCriteria().andIdEqualTo(orderId).andPayWayEqualTo(payWay);
        return ordMainMapper.updateByExampleSelective(record, example);
    }

    @Override
    public void updateOrderTwoStatusByOrderId(OrdMain ordMain,Long operId) {
        OrdMainCriteria omc = new OrdMainCriteria();
        omc.createCriteria().andIdEqualTo(ordMain.getId());
        ordMain.setUpdateStaff(operId);
        ordMain.setUpdateTime(DateUtil.getSysDate());
        this.ordMainMapper.updateByExampleSelective(ordMain, omc);
        this.ordMainShopIdxSV.updateOrderTwoStatusByOrderId(ordMain);
        this.ordMainStaffIdxSV.updateOrderTwoStatusByOrderId(ordMain);
    }
    @Override
    public OrdMain queryOrderByIdAndShop(SBaseSplitInfo sBaseSplitInfo) throws BusinessException {
        OrdMainCriteria omc = new OrdMainCriteria();
        omc.createCriteria().andIdEqualTo(sBaseSplitInfo.getOrderId())
                            .andShopIdEqualTo(sBaseSplitInfo.getShopId());
        List<OrdMain> ordMains = this.ordMainMapper.selectByExample(omc);
        if (CollectionUtils.isEmpty(ordMains)) {
            return null;
        }
        return ordMains.get(0);
    }
    
    @Override
	public ROrdMainResponse queryStaffIdByOrderId(ROrderDetailsRequest rOrderDetailsRequest) throws BusinessException {
    	ROrdMainResponse staffIdResp=new ROrdMainResponse();
		OrdMainCriteria omc = new OrdMainCriteria();
        omc.createCriteria().andIdEqualTo(rOrderDetailsRequest.getOrderId());
        OrdMain ordMains = this.ordMainMapper.selectByPrimaryKey(rOrderDetailsRequest.getOrderId());
        if (ordMains==null) {
            return null;
        }
        staffIdResp.setStaffId(ordMains.getStaffId());
        return staffIdResp;
	}
    
    @Override
    public boolean checkBackMoney(ROrdReturnRefundReq req){
    	boolean canReturn = false;
    	OrdMain ordMain = queryOrderByOrderId(req.getOrderId());
    	long realMoney = ordMain.getRealMoney();
    	OrdBackApply ordBackApply = new OrdBackApply();
    	ordBackApply.setOrderId(req.getOrderId());
    	List<OrdBackApply> ordBackApplys =  ordBackApplySV.queryHasBackOrdBackApplyByOrderID(ordBackApply);
    	long hasbackmoney = 0l;
    	for(OrdBackApply orderBackApply:ordBackApplys){
    		hasbackmoney +=orderBackApply.getBackMoney();
    	}
    	if(req.getModifyBackMoney()<=realMoney-hasbackmoney){
    		canReturn = true;
    	}
    	return canReturn;
    }

    @Override
    public PageResponseDTO<RCustomerOrdResponse> queryOrderSelectiveStatus(RQueryOrderRequest rQueryOrderRequest) {
        PageResponseDTO<RCustomerOrdResponse> pageResponse = PageResponseDTO
                .buildByBaseInfo(rQueryOrderRequest, RCustomerOrdResponse.class);
        pageResponse.setResult(new ArrayList<RCustomerOrdResponse>());

        PageResponseDTO<SOrderIdx> psoi = this.ordMainStaffIdxSV
                .queryOrderSelectiveStatus(rQueryOrderRequest);
        pageResponse.setCount(psoi.getCount());
        pageResponse.setPageCount(psoi.getPageCount());
        if (CollectionUtils.isEmpty(psoi.getResult())) {
            LogUtil.info(MODULE, "未找到订单数据");
            pageResponse.setResult(null);
            return pageResponse;
            // throw new BusinessException(MsgConstants.ProMsgCode.ORD_PRO_312000);
        } else {
            List<RCustomerOrdResponse> rcor = queryByOrderIdForCustomer(psoi.getResult());
            pageResponse.getResult().addAll(rcor);
        }
        return pageResponse;
    }

    @Override
    public List<OrdMain> queryOrderIdByThread(RQueryOrderRequest rQueryOrderRequest) throws BusinessException {


        OrdMainCriteria omsic = createCriteria(rQueryOrderRequest,"0");

        BaseSysCfgRespDTO sysCfg = SysCfgUtil.fetchSysCfg("SYS_TABLE_INDEX_COUNT");
        int cnt = Integer.parseInt(sysCfg.getParaValue());

        List<OrdMain> ordMainList = new ArrayList<>();

        ThreadPoolExecutor executor = new ThreadPoolExecutor(16,32,5, TimeUnit.MILLISECONDS, new ArrayBlockingQueue<Runnable>(64));
        List<Future> fus = new ArrayList<Future>();
        for (int i = 1; i < cnt+1; i++) {
            Future f = executor.submit(new OrdMainSVImpl.DealQueryOrderThread(i,omsic,rQueryOrderRequest.getExportKey()));
            fus.add(f);
        }
        for (Future f : fus) {
            try {
                f.get();
            } catch (InterruptedException e) {
                LogUtil.info(MODULE,"销售明细导出线程查询异常",e);
            } catch (ExecutionException e) {
                LogUtil.info(MODULE,"销售明细导出线程查询异常",e);
            }
        }
        executor.shutdown();
        for (List<OrdMain> v : ordMainMap.values()) {
            LogUtil.info(MODULE,"子列表数量："+v.size());
            ordMainList.addAll(v);
        }
        LogUtil.info(MODULE,"总数量："+ordMainList.size());

        return ordMainList;
    }


    public class DealQueryOrderThread extends Thread  {

        private int tableIndex;

        private OrdMainCriteria ordMainCriteria;

        private Long key;

        public DealQueryOrderThread(int tableIndex,OrdMainCriteria ordMainCriteria,Long key){
            this.tableIndex = tableIndex;
            this.ordMainCriteria = ordMainCriteria;
            this.key = key;
        }

        @Override
        public void run() {
            LogUtil.info(MODULE,"销售明细导出子线程"+tableIndex+":开始处理");
            DistributeRuleAssist.setTableIndex(tableIndex);
            List<OrdMain> ordMains = ordMainMapper.selectByExample(ordMainCriteria);
            ordMainMap.put(tableIndex,ordMains);
            DistributeRuleAssist.clearTableIndex();
            OrdFileImport ordFile = new OrdFileImport();
            ordFile.setId(key);
            ordFile.setShopId(8L);  //文件进度
            ordFileImportSV.updateById(ordFile);
            LogUtil.info(MODULE,"销售明细导出子线程"+tableIndex+":结束处理");
        }
    }


	@Override
	public void updateInvoice(ROrderInvoiceReq req) throws BusinessException {
		// TODO Auto-generated method stub
		if(req.getInvoiceType().equals("0")){
			 //判断原来是否存在发票信息
			Long num = ordInvoiceCommSV.countOrdInvoiceCommByOrderId(req.getOrderId());
			OrdInvoiceComm ordInvoiceComm = new OrdInvoiceComm();
		    ObjectCopyUtil.copyObjValue(req.getOrdInvoiceCommRequest(), ordInvoiceComm, null, false);
            ordInvoiceComm.setOrderId(req.getOrderId());   
            if(req.getStaff()==null&&req.getStaff().getId()==0l){
            	ordInvoiceComm.setUpdateStaff(1000L);
            }else{
            	ordInvoiceComm.setUpdateStaff(req.getStaff().getId());
            }
            ordInvoiceComm.setUpdateTime(DateUtil.getSysDate());
			if(num>0){
				ordInvoiceCommSV.updateOrdInvoiceComm(ordInvoiceComm);
			}else{
				if(req.getStaff()==null&&req.getStaff().getId()==0l){
	            	ordInvoiceComm.setCreateStaff(1000L);
	            	ordInvoiceComm.setUpdateStaff(1000L);
	            }else{
	            	ordInvoiceComm.setCreateStaff(req.getStaff().getId());
	            	ordInvoiceComm.setUpdateStaff(req.getStaff().getId());
	            }
	            ordInvoiceComm.setCreateTime(DateUtil.getSysDate());
				ordInvoiceCommSV.saveOrdInvoiceComm(ordInvoiceComm);
			}
		}else if(req.getInvoiceType().equals("2")){	//不开发票
			ordInvoiceCommSV.deleteInvoiceCommByOrderId(req.getOrderId());
		}else{
			//不支持修改店铺增值税发票
			
		}
		//修改开票状态
		if(StringUtil.isNotBlank(req.getInvoiceType())){
			OrdMain record = new OrdMain();
			record.setId(req.getOrderId());
			record.setInvoiceType(req.getInvoiceType());
			ordMainMapper.updateByPrimaryKeySelective(record);
		}
	}

	@Override
	public void updateBuyerMsg(ROrdBuyerMsgReq req) throws BusinessException{
		OrdMain record = new OrdMain();
		record.setId(req.getOrderId());
		record.setBuyerMsg(req.getBuyMsg());
		ordMainMapper.updateByPrimaryKeySelective(record);
	}
	
	/**
	 * 根据订单编号查询需要批量打印销售明细的订单
	 * @param orderIds
	 * @return
	 */
	@Override
	public List<ROrderDetailsResponse> findPrintOrderDetails(ROrderDetialPrintReq req) throws BusinessException{
		String[] orderIds = req.getOrderIds().split(",");
		List<ROrderDetailsResponse> orders = new ArrayList<ROrderDetailsResponse>();
		for(int i=0;i<orderIds.length;i++){
			ROrderDetailsRequest rOrderDetailsRequest = new ROrderDetailsRequest();
			rOrderDetailsRequest.setOrderId(orderIds[i]);
			ROrderDetailsResponse resp = queryOrderDetails(rOrderDetailsRequest);
			orders.add(resp);
		}
		return orders;
	}

	@Override
	public ROrdSpecialCountResponse findOrdSpecialCount(RQueryOrderRequest req)
			throws BusinessException {
		ROrdSpecialCountResponse resp=new ROrdSpecialCountResponse();
		Long cancleOrdCount = 0l;
        Long backOrdCount=0l;
        int cnt=0;
		BaseSysCfgRespDTO sysCfg = SysCfgUtil.fetchSysCfg("ORD_SPEC_TIMELIMIT");
		if(StringUtil.isNotEmpty(sysCfg) && StringUtil.isNotEmpty(sysCfg.getParaValue())){
	        cnt = Integer.parseInt(sysCfg.getParaValue());			
		}

        //获取取消订单数量
        OrdMainStaffIdxCriteria omc = new OrdMainStaffIdxCriteria();
        OrdMainStaffIdxCriteria.Criteria criteria = omc.createCriteria();
        criteria.andStaffIdEqualTo(req.getStaffId()).andStatusEqualTo(OrdConstants.OrderStatus.ORDER_STATUS_CANCLE);
        if(cnt>0){
        	criteria.andOrderTimeGreaterThanOrEqualTo(DateUtil.getOffsetMonthsTime(DateUtil.getSysDate(), 0-cnt));
        }
        cancleOrdCount= this.ordMainStaffIdxMapper.countByExample(omc);
        //获取退款退货成功数量
        OrdMainStaffIdxCriteria omc2 = new OrdMainStaffIdxCriteria();    
        OrdMainStaffIdxCriteria.Criteria criteria2 = omc2.createCriteria();
        List<String> status = new ArrayList<String>();
        status.add(OrdConstants.OrderStatus.ORDER_STATUS_BACKGDS);  //全部退货
        status.add(OrdConstants.OrderStatus.ORDER_STATUS_REFUND); //退款
        criteria2.andStaffIdEqualTo(req.getStaffId()).andStatusIn(status);
        if(cnt>0){
        	criteria2.andOrderTimeGreaterThanOrEqualTo(DateUtil.getOffsetMonthsTime(DateUtil.getSysDate(), 0-cnt));
        }        
        backOrdCount= this.ordMainStaffIdxMapper.countByExample(omc2);
        resp.setMonthLimit(cnt);
        resp.setCancleOrdCount(cancleOrdCount);
        resp.setBackOrdCount(backOrdCount);
		return resp;
	}

	@Override
	public SOrderDetailsComm queryInvoiceByOrderId(String orderId) {
		 SOrderDetailsComm sOrderDetailsComm = this.ordInvoiceCommSV.queryOrderDetailsComm(orderId);
		return sOrderDetailsComm;
	}

	@Override
	public ROrdSellerMsgDTO querySellerMsgById(String orderId) {
	    OrdMain om = queryOrderByOrderId(orderId);
	    ROrdSellerMsgDTO rOrdSellerMsgDTO = new ROrdSellerMsgDTO();
	    rOrdSellerMsgDTO.setOrderId(om.getId());
	    rOrdSellerMsgDTO.setSellerMsg(om.getSellerMsg());
	    return rOrdSellerMsgDTO;
	}

	@Override
	public void updateSellerMsg(ROrdSellerMsgDTO rOrdSellerMsgDTO) throws BusinessException {
		try{
			OrdMain record = new OrdMain();
			record.setId(rOrdSellerMsgDTO.getOrderId());
			record.setSellerMsg(rOrdSellerMsgDTO.getSellerMsg());
			ordMainMapper.updateByPrimaryKeySelective(record);
			
		}catch (Exception e) {
			System.out.println("");
		}
	}

}
