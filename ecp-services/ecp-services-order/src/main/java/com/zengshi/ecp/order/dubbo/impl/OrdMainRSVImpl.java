package com.zengshi.ecp.order.dubbo.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.zengshi.ecp.coupon.dubbo.interfaces.ICoupDetailRSV;
import com.zengshi.ecp.general.order.dto.CoupCheckBeanRespDTO;
import com.zengshi.ecp.general.order.dto.CoupCheckInfoRespDTO;
import com.zengshi.ecp.general.order.dto.ROrdCartCommRequest;
import com.zengshi.ecp.general.order.dto.ROrdCartItemCommRequest;
import com.zengshi.ecp.general.order.dto.ROrdCartsChkResponse;
import com.zengshi.ecp.general.order.dto.ROrdCartsCommRequest;
import com.zengshi.ecp.general.order.interfaces.IOrderChkRSV;
import com.zengshi.ecp.goods.dubbo.dto.stock.StockInfoForGdsReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.stock.StockInfoRespDTO;
import com.zengshi.ecp.goods.dubbo.interfaces.IGdsInfoExternalRSV;
import com.zengshi.ecp.goods.dubbo.interfaces.IGdsStockRSV;
import com.zengshi.ecp.order.dao.model.OrdGift;
import com.zengshi.ecp.order.dubbo.dto.RCustomerOrdResponse;
import com.zengshi.ecp.order.dubbo.dto.RExportExcleResponse;
import com.zengshi.ecp.order.dubbo.dto.ROfflineReviewRequest;
import com.zengshi.ecp.order.dubbo.dto.ROrdAddressRequest;
import com.zengshi.ecp.order.dubbo.dto.ROrdAddressResponse;
import com.zengshi.ecp.order.dubbo.dto.ROrdBuyerMsgReq;
import com.zengshi.ecp.order.dubbo.dto.ROrdCountResponse;
import com.zengshi.ecp.order.dubbo.dto.ROrdInvoiceRequest;
import com.zengshi.ecp.order.dubbo.dto.ROrdInvoiceResponse;
import com.zengshi.ecp.order.dubbo.dto.ROrdMainResponse;
import com.zengshi.ecp.order.dubbo.dto.ROrdMainsResponse;
import com.zengshi.ecp.order.dubbo.dto.ROrdReturnRefundReq;
import com.zengshi.ecp.order.dubbo.dto.ROrdSellerMsgDTO;
import com.zengshi.ecp.order.dubbo.dto.ROrdSpecialCountResponse;
import com.zengshi.ecp.order.dubbo.dto.ROrdSubmitProReq;
import com.zengshi.ecp.order.dubbo.dto.ROrderDetailsRequest;
import com.zengshi.ecp.order.dubbo.dto.ROrderDetailsResponse;
import com.zengshi.ecp.order.dubbo.dto.ROrderDetialPrintReq;
import com.zengshi.ecp.order.dubbo.dto.ROrderGiftResponse;
import com.zengshi.ecp.order.dubbo.dto.ROrderGiftsResponse;
import com.zengshi.ecp.order.dubbo.dto.ROrderIdRequest;
import com.zengshi.ecp.order.dubbo.dto.ROrderInvoiceReq;
import com.zengshi.ecp.order.dubbo.dto.RQueryOrderRequest;
import com.zengshi.ecp.order.dubbo.dto.RShopOrderResponse;
import com.zengshi.ecp.order.dubbo.dto.RSumbitMainRequest;
import com.zengshi.ecp.order.dubbo.dto.RSumbitMainsRequest;
import com.zengshi.ecp.order.dubbo.dto.RSumbitSubRequest;
import com.zengshi.ecp.order.dubbo.dto.SOrderDetailsComm;
import com.zengshi.ecp.order.dubbo.interfaces.IOrdMainRSV;
import com.zengshi.ecp.order.dubbo.interfaces.IOrderSubmitProInitRSV;
import com.zengshi.ecp.order.dubbo.interfaces.IOrderSubmitProRSV;
import com.zengshi.ecp.order.dubbo.util.LongUtils;
import com.zengshi.ecp.order.dubbo.util.MsgConstants;
import com.zengshi.ecp.order.dubbo.util.OrdConstants;
import com.zengshi.ecp.order.facade.interfaces.eventual.IOrdRemoveOrderSV;
import com.zengshi.ecp.order.facade.interfaces.eventual.IOrdZreoSV;
import com.zengshi.ecp.order.service.busi.interfaces.IOrdDiscountSV;
import com.zengshi.ecp.order.service.busi.interfaces.IOrdExportFileSV;
import com.zengshi.ecp.order.service.busi.interfaces.IOrdGiftSV;
import com.zengshi.ecp.order.service.busi.interfaces.IOrdMainSV;
import com.zengshi.ecp.order.service.busi.interfaces.IOrdOperChkSV;
import com.zengshi.ecp.order.service.busi.interfaces.IOrdPresentSV;
import com.zengshi.ecp.order.service.busi.interfaces.IOrdPromSV;
import com.zengshi.ecp.order.service.busi.interfaces.IOrdSubSV;
import com.zengshi.ecp.order.service.busi.interfaces.IOrdTrackSV;
import com.zengshi.ecp.prom.dubbo.dto.CartPromDTO;
import com.zengshi.ecp.prom.dubbo.dto.CartPromItemDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromPresentRespDTO;
import com.zengshi.ecp.prom.dubbo.interfaces.IPromQueryRSV;
import com.zengshi.ecp.prom.dubbo.interfaces.IPromRSV;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.staff.dubbo.dto.AcctInfoResDTO;
import com.zengshi.ecp.staff.dubbo.dto.CustInfoReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.CustInfoResDTO;
import com.zengshi.ecp.staff.dubbo.dto.ListReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.ScoreExchangeReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.ShopInfoResDTO;
import com.zengshi.ecp.staff.dubbo.dto.ShopStaffGroupReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.TransactionContentReqDTO;
import com.zengshi.ecp.staff.dubbo.interfaces.IAcctManageRSV;
import com.zengshi.ecp.staff.dubbo.interfaces.ICustInfoRSV;
import com.zengshi.ecp.staff.dubbo.interfaces.IShopManageRSV;
import com.zengshi.ecp.staff.dubbo.interfaces.IStaffUnionRSV;
import com.zengshi.ecp.staff.dubbo.util.StaffConstants;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.ObjectCopyUtil;
import com.zengshi.paas.utils.StringUtil;
import com.alibaba.dubbo.common.utils.CollectionUtils;
import com.alibaba.fastjson.JSON;

/**
 * Title: ECP <br>
 * Project Name:ecp-services-order <br>
 * Description: <br>
 * Date:2015年8月12日下午8:22:28 <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author cbl
 * @version
 * @since JDK 1.6
 */
public class OrdMainRSVImpl implements IOrdMainRSV {

    @Resource
    private IOrdMainSV ordMainSV;
    
    @Resource
    private IOrdSubSV ordSubSV;
    
    @Resource
    private IGdsStockRSV gdsStockRSV;
    
    @Resource
    private IPromRSV promRSV;
    
    @Resource
    private IPromQueryRSV promQueryRSV;
    
    @Resource
    private IAcctManageRSV acctManageRSV;    
    
    @Resource
    private IGdsInfoExternalRSV gdsInfoExternalRSV; 
    
    @Resource
    private IStaffUnionRSV staffUnionRSV;
    
    private List<IOrderChkRSV>  orderChkRSVs;
    
    
    @Resource
    private IOrdPresentSV ordPresentSV;
    
    @Resource
    private IOrdPromSV  ordPromSV;
    
    @Resource
    private IOrdDiscountSV ordDiscountSV;
    
    @Resource
    private IOrdTrackSV ordTrackSV;
    
    @Resource
    private IOrderChkRSV  gdsOrdSubChkRSV;
    @Resource
    private IOrderChkRSV  staffOrdSubChkRSV;
    @Resource
    private IOrderChkRSV  promOrdChkRSV;
    
    @Resource
    private ICustInfoRSV  custInfoRSV;
    @Resource
    private IShopManageRSV shopManageRSV;
    
    @Resource
    private IOrdOperChkSV ordOperChkSV;   
    
    @Resource
    private IOrdZreoSV ordZreoSV;
    
    @Resource
    private IOrdGiftSV ordGiftSV;
    
    @Resource
    private IOrdMainRSV ordMainRSV;

    @Resource
    private ICoupDetailRSV iCoupDetailRSV;
    
    @Resource
    private IOrdRemoveOrderSV ordRemoveOrderSV;
    
    @Autowired(required=false)
    private IOrderSubmitProInitRSV orderSubmitProInitRSV;
    
    @Autowired(required=false)
    private IOrdExportFileSV ordExportFileSV;
    
    public List<IOrderChkRSV> getOrderChkRSVs() {
        return orderChkRSVs;
    }

    @Resource
    public void setOrderChkRSVs(List<IOrderChkRSV> orderChkRSVs) {
        this.orderChkRSVs = orderChkRSVs;
    }

    private static final String MODULE = OrdMainRSVImpl.class.getName();

    @Override
    public PageResponseDTO<RShopOrderResponse> queryOrderByShopId(
            RQueryOrderRequest rQueryOrderRequest) throws BusinessException {
        if(rQueryOrderRequest == null){
            LogUtil.info(MODULE, "入参对象不能为空");
            throw new BusinessException(MsgConstants.InputMsgCode.ORD_INPUT_300000); 
        }
/*        if(rQueryOrderRequest.getBegDate() == null){
            LogUtil.info(MODULE, "开始时间不能为空");
            throw new BusinessException(MsgConstants.InputMsgCode.ORD_INPUT_311004); 
        }
        if(rQueryOrderRequest.getEndDate() == null){
            LogUtil.info(MODULE, "结束时间不能为空");
            throw new BusinessException(MsgConstants.InputMsgCode.ORD_INPUT_311004); 
        }*/
        if(rQueryOrderRequest.getShopId() ==null || rQueryOrderRequest.getShopId() < 1){
            LogUtil.info(MODULE, "店铺id");
            throw new BusinessException(MsgConstants.InputMsgCode.ORD_INPUT_311002);
        }
        //支持按产品分类查询订单
/*        if(CollectionUtils.isEmpty(rQueryOrderRequest.getCategoryCodes())){
            LogUtil.info(MODULE, "产品分类查询条件不能为空");
            throw new BusinessException(MsgConstants.InputMsgCode.ORD_INPUT_311009);
        }*/
        PageResponseDTO<RShopOrderResponse> rdors = null;
        try {
            rdors = this.ordMainSV.queryOrderByShopIdPage(rQueryOrderRequest);
        } catch (BusinessException be) {
            LogUtil.error(MODULE, "===业务异常===",be);
            throw be;
        } catch (Exception e) {
            LogUtil.error(MODULE, "===系统异常===",e);
            throw new BusinessException(MsgConstants.ServiceMsgCode.ORD_SERVER_310000);
        }
        
        return rdors;
        
    }

    /**
     * 
     * 提交订单. 
     * @see com.zengshi.ecp.order.dubbo.interfaces.IOrdMainRSV#sumbitOrd(com.zengshi.ecp.order.dubbo.dto.RSumbitMainsRequest)
     */
    @Override
    public ROrdMainsResponse sumbitOrd(RSumbitMainsRequest info) throws BusinessException {
        
        if(info==null){
            LogUtil.info(MODULE, "入参不能为空");
            throw new BusinessException(MsgConstants.InputMsgCode.ORD_INPUT_300000); 
        }
        if(info.getStaffId()==null){
            LogUtil.info(MODULE, "买家id不能为空");
            throw new BusinessException(MsgConstants.InputMsgCode.ORD_INPUT_311003);            
        }
        if(CollectionUtils.isEmpty(info.getSumbitMainList())){
            LogUtil.info(MODULE, "预订单列表不能为空");
            throw new BusinessException(MsgConstants.InputMsgCode.ORD_INPUT_351001);            
        }
        if(StringUtil.isEmpty(info.getPayType())){
            LogUtil.info(MODULE, "支付方式不能为空");
            throw new BusinessException(MsgConstants.InputMsgCode.ORD_INPUT_351001);            
        }       
        try {
            //0 跨域事务的入参封装---库存，活动，客户
            //主订单号和子订单号先预生成，以便跨域调用入参
            boolean isProm = false;
            boolean isCoup = false;
            LogUtil.info(MODULE, "提交订单入参："+JSON.toJSONString(info).toString());
            LogUtil.info(MODULE, "========校验开始============");
            // 前端传递过来的信息进行充分性的补全(与下面方法并不同步)
            ROrdCartsCommRequest rOrdCartsCommRequestChk = assembleParamForAll(info);
            LogUtil.info(MODULE, "========校验入参============"+JSON.toJSONString(rOrdCartsCommRequestChk).toString());
            ROrdCartsChkResponse rOrdCartsChkResponse = submitOrdChk(rOrdCartsCommRequestChk);
            
            if(rOrdCartsChkResponse != null){
                LogUtil.info(MODULE, "========异常============"+rOrdCartsChkResponse.getMsg());
                if(!rOrdCartsChkResponse.isStatus()){
                    throw new BusinessException(rOrdCartsChkResponse.getMsg());
                }
            }

            //继续补全入参信息
            List<ScoreExchangeReqDTO> scoreExchangeReqDTOs = new ArrayList<ScoreExchangeReqDTO>();
            List<TransactionContentReqDTO> trList=new ArrayList<TransactionContentReqDTO>();
            
            List<RSumbitMainRequest> sumbitMainList=info.getSumbitMainList();
            ROrdCartsCommRequest rOrdCartsCommRequest=new ROrdCartsCommRequest();
            rOrdCartsCommRequest.setStaffId(info.getStaffId());
            List<ROrdCartCommRequest> ordCartsCommList=new ArrayList<ROrdCartCommRequest>();
            // 订单级别优惠
            Map<Long, CartPromDTO> ordMap = null;
            // 子订单级别优惠
            Map<Long, CartPromItemDTO> ordSubMap = null;
            if(info.getCartPromRespDTO() != null){
                ordMap = info.getCartPromRespDTO().getCartPromDTOMap();
                ordSubMap = info.getCartPromRespDTO().getCartPromItemDTOMap();
            }

            //优惠券
            Map<Long,CoupCheckInfoRespDTO> coupIdskuIdMap = info.getCoupIdskuIdMap();
            if(coupIdskuIdMap!=null)rOrdCartsCommRequest.setCoupIdskuIdMap(coupIdskuIdMap);

            List<CoupCheckBeanRespDTO> coupPlatfBean = info.getCoupPlatfBean();
            if(coupPlatfBean!=null && coupPlatfBean.size()>0)rOrdCartsCommRequest.setCoupPlatfBean(coupPlatfBean);
            //优惠券

            
            for(RSumbitMainRequest sumbitMainRequest:sumbitMainList){
                //生成OrderId
                String orderId=ordMainSV.createOrdMainId();
                sumbitMainRequest.setOrderId(orderId);
                
                ROrdCartCommRequest rOrdCartCommRequest=new ROrdCartCommRequest();
                ObjectCopyUtil.copyObjValue(sumbitMainRequest, rOrdCartCommRequest, null, false);
                
                // 公共入参客户域信息
                CustInfoReqDTO custInfoReqDTO = new CustInfoReqDTO();
                custInfoReqDTO.setId(info.getStaff().getId());
                CustInfoResDTO custInfoResDTO = custInfoRSV.getCustInfoById(custInfoReqDTO);
                ShopStaffGroupReqDTO shopStaffGroupReqDTO = new ShopStaffGroupReqDTO();
                shopStaffGroupReqDTO.setStaffId(custInfoResDTO.getId());
                shopStaffGroupReqDTO.setShopId(rOrdCartCommRequest.getShopId());
                // 客户组id
                String custGroupValue = null;
                if (custInfoResDTO != null && custInfoResDTO.getId() != null
                        && custInfoResDTO.getId() != 0) {
                    custGroupValue = shopManageRSV.queryShopStaffGroup(shopStaffGroupReqDTO);
                }
                // 客户基本信息
                rOrdCartCommRequest.setCustGroupValue(custGroupValue);
                rOrdCartCommRequest.setCustLevelValue(custInfoResDTO.getCustLevelCode());
                rOrdCartCommRequest.setAreaValue(custInfoResDTO.getProvinceCode());
                //web  渠道
                rOrdCartCommRequest.setChannelValue(info.getSource());

                //促销信息
                if(ordMap != null){
                    CartPromDTO cpd = ordMap.get(sumbitMainRequest.getCartId());
                    if (cpd != null && cpd.isIfFulfillProm()) {
                        isProm = true;
                        rOrdCartCommRequest.setId(sumbitMainRequest.getCartId());
                        rOrdCartCommRequest.setPromId(cpd.getPromInfoDTO().getId());
                        rOrdCartCommRequest.setIfFulfillProm(cpd.isIfFulfillProm());
                    }
                }

                // 补齐优惠券
                List<CoupCheckBeanRespDTO> coupCheckBeanRespDTOs = sumbitMainRequest.getCoupCheckBean();
                if(coupCheckBeanRespDTOs!=null && coupCheckBeanRespDTOs.size()>0){
                    rOrdCartCommRequest.setCoupCheckBean(coupCheckBeanRespDTOs);
                    isCoup = true;
                }
                
                //补齐优惠码
                if(StringUtils.isNotBlank(sumbitMainRequest.getCoupCode())){
                    isCoup=true;
                }

                List<ROrdCartItemCommRequest> ordCartItemCommList=new ArrayList<ROrdCartItemCommRequest>();
                List<RSumbitSubRequest> preOrdSubList=sumbitMainRequest.getPreOrdSubList();
                //获取订单使用的资金账户信息List
                List<AcctInfoResDTO> ordAcctInfoList=sumbitMainRequest.getOrdAcctInfoList();                
                
                if(!CollectionUtils.isEmpty(ordAcctInfoList)){
                    for(AcctInfoResDTO acct:ordAcctInfoList){
                        TransactionContentReqDTO tr=new TransactionContentReqDTO();
                        tr.setStaffId(acct.getStaffId());
                        tr.setShopId(acct.getShopId());
                        tr.setAdaptType(acct.getAdaptType());
                        tr.setAcctType(acct.getAcctType());
                        tr.setDebitCredit(StaffConstants.Trade.ACCT_DC_SPEND); //支出
                        tr.setTradeType(StaffConstants.Trade.TRADE_TYPE_PAY);//支付
                        tr.setTradeMoney(acct.getDeductOrderMoney());
                        tr.setOrderId(orderId);
                        trList.add(tr);
                    }  
                    
                }
                for(RSumbitSubRequest rSumbitSubRequest:preOrdSubList){
                    String orderSubId=ordSubSV.createOrdSubId();
                    rSumbitSubRequest.setOrderSubId(orderSubId);
                    rSumbitSubRequest.setOrderId(orderId);
                    
                    
                    ROrdCartItemCommRequest rOrdCartItemCommRequest=new ROrdCartItemCommRequest();
                    ObjectCopyUtil.copyObjValue(rSumbitSubRequest, rOrdCartItemCommRequest, null, false);
                    if(isCoup) {
                        rOrdCartItemCommRequest.setId(rSumbitSubRequest.getCartItemId());
                        rOrdCartItemCommRequest.setCurrentSiteId(info.getCurrentSiteId());
                    }
                    if(ordSubMap != null){
                        CartPromItemDTO cpid = ordSubMap.get(rSumbitSubRequest.getCartItemId());
                        if(cpid != null && ! cpid.isIfFulfillProm() && cpid.getOrdPromId() != null &&   cpid.getOrdPromId() >0l ){
                            rOrdCartItemCommRequest.setOrdPromId(cpid.getOrdPromId());
                        }
                        if (cpid != null && cpid.isIfFulfillProm()) {
                            isProm = true;
                            rOrdCartItemCommRequest.setId(rSumbitSubRequest.getCartItemId());
                            rOrdCartItemCommRequest.setPromId(cpid.getPromInfoDTO().getId());
                            rOrdCartItemCommRequest.setIfFulfillProm(cpid.isIfFulfillProm());
                            rOrdCartItemCommRequest.setOrdPromId(cpid.getOrdPromId());
                        }
                    }
                    StockInfoForGdsReqDTO stockInfoForGdsDTO = new StockInfoForGdsReqDTO();
                    stockInfoForGdsDTO.setShopId(rSumbitSubRequest.getShopId());
                    stockInfoForGdsDTO.setGdsId(rSumbitSubRequest.getGdsId());
                    stockInfoForGdsDTO.setSkuId(rSumbitSubRequest.getSkuId());
                    stockInfoForGdsDTO.setTypeId(rSumbitSubRequest.getGdsType());
                    StockInfoRespDTO stockInfoDTO =gdsInfoExternalRSV.getStockAmount(stockInfoForGdsDTO);
                    Long repCode=stockInfoDTO.getRepCode();
                    Long stockId=stockInfoDTO.getId();
                    rSumbitSubRequest.setRepCode(repCode);
                    rSumbitSubRequest.setStockId(stockId);
                    rOrdCartItemCommRequest.setRepCode(repCode);
                    rOrdCartItemCommRequest.setStockId(stockId);
                    ordCartItemCommList.add(rOrdCartItemCommRequest);
                    
                    Long score = rSumbitSubRequest.getScore() * rSumbitSubRequest.getOrderAmount();
                    if(score > 0){
                        ScoreExchangeReqDTO scoreExchangeReqDTO = new ScoreExchangeReqDTO();
                        scoreExchangeReqDTO.setStaffId(sumbitMainRequest.getStaff().getId());
                        scoreExchangeReqDTO.setOrderId(orderId);
                        scoreExchangeReqDTO.setSubOrderId(orderSubId);
                        scoreExchangeReqDTO.setScoreUsing(score);
                        scoreExchangeReqDTOs.add(scoreExchangeReqDTO);
                    }
                }
                rOrdCartCommRequest.setOrdCartItemCommList(ordCartItemCommList);
                ordCartsCommList.add(rOrdCartCommRequest);
                
           
            }
            rOrdCartsCommRequest.setOrdCartsCommList(ordCartsCommList);
            
            LogUtil.info(MODULE, "提交订单外域入参:"+JSON.toJSONString(rOrdCartsCommRequest));
            
            ListReqDTO<ScoreExchangeReqDTO> scoreReq = new ListReqDTO<ScoreExchangeReqDTO>();
            scoreReq.setList(scoreExchangeReqDTOs);
            ListReqDTO<TransactionContentReqDTO> listReqDto = new ListReqDTO<TransactionContentReqDTO>();
            listReqDto.setList(trList);
            ROrdSubmitProReq rOrdSubmitProReq = new ROrdSubmitProReq();
            rOrdSubmitProReq.setrOrdCartsCommRequest(rOrdCartsCommRequest);
            rOrdSubmitProReq.setScoreExchangeReqDTOs(scoreReq);
            rOrdSubmitProReq.setTransactionContentReqDTOs(listReqDto);
            rOrdSubmitProReq.setProm(isProm);
            rOrdSubmitProReq.setCoup(isCoup);
            List<IOrderSubmitProRSV> rollbackRSVs = new ArrayList<IOrderSubmitProRSV>();
            for(IOrderSubmitProRSV ordSubmitProRSV: orderSubmitProInitRSV.getOrderSubmitProRSVs()){
                try {
                    ordSubmitProRSV.execute(rOrdSubmitProReq);
                    rollbackRSVs.add(ordSubmitProRSV);
                } catch (BusinessException be) {
                    LogUtil.error(MODULE, "===业务异常===",be);
                    if(CollectionUtils.isNotEmpty(rollbackRSVs)){
                        for(IOrderSubmitProRSV rollbackRSV:rollbackRSVs){
                            rollbackRSV.rollback(rOrdSubmitProReq);
                        }
                    }
                    throw be;
                }catch (Exception e) {
                    LogUtil.error(MODULE, "===系统异常===",e);
                    if(CollectionUtils.isNotEmpty(rollbackRSVs)){
                        for(IOrderSubmitProRSV rollbackRSV:rollbackRSVs){
                            rollbackRSV.rollback(rOrdSubmitProReq);
                        }
                    }
                    throw new BusinessException(MsgConstants.OtherSysMsgCode.CALL_GOODS_SERVER_340000);
                }   
            }
            
            //2 促销赠送
            PromPresentRespDTO ppr = this.promQueryRSV.promPresent(rOrdCartsCommRequest);
            info.setPromPresentRespDTO(ppr);
            //4 提交订单
            List<ROrdMainResponse> rOrdMainResponse = null;
            try {
                LogUtil.info(MODULE, "提交订单:"+JSON.toJSONString(info));
                rOrdMainResponse=this.ordMainSV.saveSubmitOrd(info);
            } catch (BusinessException be) {
                LogUtil.error(MODULE, "===业务异常===",be);
                if(CollectionUtils.isNotEmpty(rollbackRSVs)){
                    for(IOrderSubmitProRSV rollbackRSV:rollbackRSVs){
                        rollbackRSV.rollback(rOrdSubmitProReq);
                    }
                }
                throw be;
            }catch (Exception e) {
                LogUtil.error(MODULE, "===系统异常===",e);
                if(CollectionUtils.isNotEmpty(rollbackRSVs)){
                    for(IOrderSubmitProRSV rollbackRSV:rollbackRSVs){
                        rollbackRSV.rollback(rOrdSubmitProReq);
                    }
                }
                throw new BusinessException(MsgConstants.ServiceMsgCode.ORD_SERVER_350003);
            }
            
            ROrdMainsResponse rOrdMainsResponse=new ROrdMainsResponse();
            rOrdMainsResponse.setOrdMainList(rOrdMainResponse);
            rOrdMainsResponse.setStaffId(info.getStaffId());
            LogUtil.info(MODULE, "提交订单入参："+JSON.toJSONString(info).toString());
            
            //0元订单处理
            for(RSumbitMainRequest rSumbitMainRequest:info.getSumbitMainList()){
                if(rSumbitMainRequest.getRealMoney()==0l){
                    LogUtil.info(MODULE, "0元订单开始处理");
                    ROfflineReviewRequest rOfflineReviewRequest  = new ROfflineReviewRequest();
                    rOfflineReviewRequest.setOrderId(rSumbitMainRequest.getOrderId());
                    rOfflineReviewRequest.getStaff().setId(info.getStaff().getId());
                    this.ordZreoSV.payZeroOrder(rOfflineReviewRequest);
                    LogUtil.info(MODULE, "0元订单处理结束");
                }
            }
            
            return rOrdMainsResponse;
        } catch (BusinessException be) {
            LogUtil.error(MODULE, "===业务异常===",be);
            throw be;
        } catch (Exception e) {
            LogUtil.error(MODULE, "===系统异常===",e);
            throw new BusinessException(MsgConstants.ServiceMsgCode.ORD_SERVER_350011);
        }        
    }
    /**
     * 
     * removeOrd:取消订单. <br/> 
     * @author linwei 
     * @param info
     * @throws BusinessException 
     * @since JDK 1.6
     */
    @Override
    public void removeOrd(ROrderDetailsRequest info) throws BusinessException{
        if(info==null){
            LogUtil.info(MODULE, "入参不能为空");
            throw new BusinessException(MsgConstants.InputMsgCode.ORD_INPUT_300000); 
        }
        if(info.getOrderId()==null){
            LogUtil.info(MODULE, "订单id不能为空");
            throw new BusinessException(MsgConstants.InputMsgCode.ORD_INPUT_311003);            
        }
        if(info.getStaffId()==null){
            LogUtil.info(MODULE, "用户id不能为空");
            throw new BusinessException(MsgConstants.InputMsgCode.ORD_INPUT_311003);            
        }
        
        try {
            
            //调用取消订单主事务
            this.ordRemoveOrderSV.dealMethod(info);
            
            /*  //改造成一致性事务处理
            boolean isProm = false;
            //0 进行接口入参的封装
            ROrdCartsCommRequest rOrdCartsCommRequest = new ROrdCartsCommRequest();
            List<ROrdCartCommRequest> rOrdCartCommRequests = new ArrayList<ROrdCartCommRequest>();
            
            OrdMain ordMain = this.ordMainSV.queryOrderByOrderId(info.getOrderId());
            if(ordMain == null){
                LogUtil.info(MODULE, "未找到[" + info.getOrderId() + "]该订单信息");
                throw new BusinessException(MsgConstants.ProMsgCode.ORD_PRO_312001);
            }
            ROrdCartCommRequest  rOrdCartCommRequest = new ROrdCartCommRequest();
            //补齐订单信息
            ObjectCopyUtil.copyObjValue(ordMain, rOrdCartCommRequest, null,false);
            rOrdCartCommRequest.setOrderId(ordMain.getId());

            //补齐订单促销
            SBaseAndSubInfo sBaseAndSubInfo = new SBaseAndSubInfo();
            sBaseAndSubInfo.setOrderId(info.getOrderId());
            OrdProm op = this.ordPromSV.queryOrdProm(sBaseAndSubInfo);
            if(op != null){
                isProm = true;
                rOrdCartCommRequest.setPromId(op.getPromId());
            }
            List<OrdSub> ordSubs = this.ordSubSV.queryOrderSubByOrderId(info.getOrderId());
            
            if (CollectionUtils.isEmpty(ordSubs)) {
                LogUtil.info(MODULE, "未找到[" + info.getOrderId() + "]该订单的子订单信息");
                throw new BusinessException(MsgConstants.ProMsgCode.ORD_PRO_312002);
            }
            //补齐明细信息
            List<ROrdCartItemCommRequest> rOrdCartItemCommRequests = new ArrayList<ROrdCartItemCommRequest>();
            for(OrdSub ordSub: ordSubs){
                ROrdCartItemCommRequest rOrdCartItemCommRequest = new ROrdCartItemCommRequest();
                ObjectCopyUtil.copyObjValue(ordSub, rOrdCartItemCommRequest, null, false);
                rOrdCartItemCommRequest.setOrderSubId(ordSub.getId());
                
                //补齐promId
                SBaseAndSubInfo sBaseAndSubInfo1 = new SBaseAndSubInfo();
                sBaseAndSubInfo1.setOrderId(info.getOrderId());
                sBaseAndSubInfo1.setOrderSubId(rOrdCartItemCommRequest.getOrderSubId());
                OrdProm opsub = this.ordPromSV.queryOrdProm(sBaseAndSubInfo1);
                if(opsub != null){
                    isProm = true;
                    rOrdCartItemCommRequest.setPromId(opsub.getPromId());
                    rOrdCartItemCommRequest.setOrdPromId(opsub.getRelaMainPromId());
                }
                
                rOrdCartItemCommRequests.add(rOrdCartItemCommRequest);
            }
            rOrdCartCommRequest.setOrdCartItemCommList(rOrdCartItemCommRequests);
            rOrdCartCommRequests.add(rOrdCartCommRequest);
            rOrdCartsCommRequest.setOrdCartsCommList(rOrdCartCommRequests);
            rOrdCartsCommRequest.setStaffId(ordMain.getStaffId());
            
            //库存补尝
            this.gdsInfoExternalRSV.batchCancleStockPreOccupy(rOrdCartsCommRequest);
            
            //活动补尝
            if(isProm){
                this.promRSV.promOrdSaveRollBack(rOrdCartsCommRequest);
            }

            //优惠券补偿
            this.iCoupDetailRSV.deleteOrdCoup(rOrdCartsCommRequest);
            
            //进行用户资金张合和积分的补尝
            List<ScoreExchangeReqDTO> scoreExchangeReqDTOs = new ArrayList<ScoreExchangeReqDTO>();
            List<TransactionContentReqDTO> trList=new ArrayList<TransactionContentReqDTO>();
            ListReqDTO<ScoreExchangeReqDTO> scoreReq = new ListReqDTO<ScoreExchangeReqDTO>();
            
            List<OrdDiscount> ordDiscounts = this.ordDiscountSV.queryOrdDiscountByOrderId(info.getOrderId());
            
            if(CollectionUtils.isNotEmpty(ordDiscounts)){
                for(OrdDiscount ordDiscount:ordDiscounts){
                    if(ordDiscount.getOrderId() != null && ordDiscount.getOrderSubId() == null
                            && ordDiscount.getDiscountType().equals(OrdConstants.DiscountType.TYPE_CAPITAL_CODE)
                            && ordDiscount.getDiscountTitle().equals(OrdConstants.DiscountType.TYPE_CAPITAL_NAME)){
                        
                        TransactionContentReqDTO tr=new TransactionContentReqDTO();
                        tr.setStaffId(ordMain.getStaffId());
                        tr.setShopId(ordMain.getShopId());
//                        tr.setAdaptType(ordDiscount.get);
//                        tr.setAcctType(acct.getAcctType());
                        tr.setDebitCredit(StaffConstants.Trade.ACCT_DC_INCOME); //加减
                        tr.setTradeType(StaffConstants.Trade.TRADE_TYPE_CANCEL);//下单使用
                        tr.setTradeMoney(ordDiscount.getDiscountPrice());
                        tr.setOrderId(ordDiscount.getOrderId());
                        trList.add(tr);
                        
                    }
                    
                    if(ordDiscount.getOrderId() != null && ordDiscount.getOrderSubId() != null
                            && ordDiscount.getDiscountType().equals(OrdConstants.DiscountType.TYPE_SCORE_CODE)
                            && ordDiscount.getDiscountTitle().equals(OrdConstants.DiscountType.TYPE_SCORE_NAME)){
                        
                        ScoreExchangeReqDTO scoreExchangeReqDTO = new ScoreExchangeReqDTO();
                        scoreExchangeReqDTO.setStaffId(ordMain.getStaffId());
                        scoreExchangeReqDTO.setOrderId(ordDiscount.getOrderId());
                        scoreExchangeReqDTO.setSubOrderId(ordDiscount.getOrderSubId());
                        scoreExchangeReqDTO.setScoreUsing(ordDiscount.getUseScore());
                        scoreExchangeReqDTOs.add(scoreExchangeReqDTO);
                    }
                }
                
                
            }
            
            
            scoreReq.setList(scoreExchangeReqDTOs);
            scoreReq.setCurrentSiteId(info.getCurrentSiteId());
            ListReqDTO<TransactionContentReqDTO> listReqDto = new ListReqDTO<TransactionContentReqDTO>();
            listReqDto.setList(trList);
            listReqDto.setCurrentSiteId(info.getCurrentSiteId());
            if(CollectionUtils.isNotEmpty(scoreExchangeReqDTOs) || CollectionUtils.isNotEmpty(trList) ){
                this.staffUnionRSV.saveStaffRelInfoForOrderRollBack(scoreReq,listReqDto);
            } 
            
            
            //4 进行订单模块回退
            this.ordMainSV.removeOrd(info);
            */
        } catch (BusinessException be) {
            LogUtil.error(MODULE, "===业务异常===",be);
            throw be;
        } catch (Exception e) {
            LogUtil.error(MODULE, "===系统异常===",e);
            throw new BusinessException(MsgConstants.ServiceMsgCode.ORD_SERVER_350012);
        }        
            
    }


    @Override
    public PageResponseDTO<RCustomerOrdResponse> queryOrderByStaffId(
            RQueryOrderRequest rQueryOrderRequest) throws BusinessException {
        if(rQueryOrderRequest == null){
            LogUtil.info(MODULE, "入参对象不能为空");
            throw new BusinessException(MsgConstants.InputMsgCode.ORD_INPUT_300000); 
        }
//        if(rCustomerOrdRequest.getBegDate() == null){
//            LogUtil.info(MODULE, "开始时间不能为空");
//            throw new BusinessException(MsgConstants.InputMsgCode.ORD_INPUT_311004); 
//        }
//        if(rCustomerOrdRequest.getEndDate() == null){
//            LogUtil.info(MODULE, "结束时间不能为空");
//            throw new BusinessException(MsgConstants.InputMsgCode.ORD_INPUT_311004); 
//        }
        if(rQueryOrderRequest.getStaffId() ==null || rQueryOrderRequest.getStaffId() < 1){
            LogUtil.info(MODULE, "买家id不能为空");
            throw new BusinessException(MsgConstants.InputMsgCode.ORD_INPUT_311003);
        }
        if(rQueryOrderRequest.getSiteId() == null ){
            LogUtil.info(MODULE, "所属站点不能为空");
            throw new BusinessException(MsgConstants.InputMsgCode.ORD_INPUT_300001);
        }
//        if(StringUtil.isBlank(rQueryOrderRequest.getSysType())){
//            LogUtil.info(MODULE, "所属系统不能为空");
//            throw new BusinessException(MsgConstants.InputMsgCode.ORD_INPUT_300002);
//        }
        
//        if(rQueryOrderRequest.getStatus() == null){
//            LogUtil.info(MODULE, "订单状态不能为空");
//            throw new BusinessException(MsgConstants.InputMsgCode.ORD_INPUT_311008);
//        }
        
        rQueryOrderRequest.setSysType(OrdConstants.SysType.SYS_TYPE_BASE);
        PageResponseDTO<RCustomerOrdResponse> rcor = null;
        try {
            rcor = this.ordMainSV.queryOrderByStaffIdPage(rQueryOrderRequest);
        } catch (BusinessException be) {
            LogUtil.error(MODULE, "===业务异常===",be);
            throw be;
        } catch (Exception e) {
            LogUtil.error(MODULE, "===系统异常===",e);
            throw new BusinessException(MsgConstants.ServiceMsgCode.ORD_SERVER_310001);
        }
        
        return rcor;
    }

    @Override
    public ROrdCountResponse queryOrderCountByStaff(RQueryOrderRequest rQueryOrderRequest)
            throws BusinessException {
        if(rQueryOrderRequest == null){
            LogUtil.info(MODULE, "入参对象不能为空");
            throw new BusinessException(MsgConstants.InputMsgCode.ORD_INPUT_300000); 
        }
        if(rQueryOrderRequest.getStaffId() ==null || rQueryOrderRequest.getStaffId() < 1){
            LogUtil.info(MODULE, "买家id不能为空");
            throw new BusinessException(MsgConstants.InputMsgCode.ORD_INPUT_311003);
        }
        ROrdCountResponse rcor = null;
        try {
            rcor = this.ordMainSV.queryOrderCountByStaff(rQueryOrderRequest);
        } catch (BusinessException be) {
            LogUtil.error(MODULE, "===业务异常===",be);
            throw be;
        } catch (Exception e) {
            LogUtil.error(MODULE, "===系统异常===",e);
            throw new BusinessException(MsgConstants.ServiceMsgCode.ORD_SERVER_310026);
        }
        return rcor;
    }

    @Override
    public ROrdCartsChkResponse submitOrdChk(ROrdCartsCommRequest rOrdCartsCommRequest)
            throws BusinessException {
        
        ROrdCartsChkResponse roc =null;
        LogUtil.info(MODULE, "====调用商品域开始======");
        roc = this.gdsOrdSubChkRSV.checkOrder(rOrdCartsCommRequest);
        if(roc != null && roc.isStatus() ){
            LogUtil.info(MODULE, "====调用商品域成功======"+roc.getMsg());
        } else {
            LogUtil.info(MODULE, "====调用商品域异常======"+roc.getMsg());
            return roc;
        }
        
        LogUtil.info(MODULE, "====调用促销域开始======");
        roc = this.promOrdChkRSV.checkOrder(rOrdCartsCommRequest);
        
        if(roc != null && roc.isStatus() ){
            LogUtil.info(MODULE, "====调用促销域成功======"+roc.getMsg());
        } else {
            LogUtil.info(MODULE, "====调用促销域异常======"+roc.getMsg());
            return roc;
        }

        LogUtil.info(MODULE, "====调用优惠券域开始======");
        roc = this.iCoupDetailRSV.checkOrder(rOrdCartsCommRequest);

        if(roc != null && roc.isStatus() ){
            LogUtil.info(MODULE, "====调用优惠券域成功======"+roc.getMsg());
        } else {
            LogUtil.info(MODULE, "====调用优惠券域异常======"+roc.getMsg());
            return roc;
        }

        
        LogUtil.info(MODULE, "====调用客户域开始======");
        
        roc = this.staffOrdSubChkRSV.checkOrder(rOrdCartsCommRequest);
        if(roc != null && roc.isStatus() ){
            LogUtil.info(MODULE, "====调用客户域成功======"+roc.getMsg());
        } else {
            LogUtil.info(MODULE, "====调用客户域异常======"+roc.getMsg());
            return roc;
        }
        
        return roc;
    }

    @Override
    public ROrdCartsCommRequest assembleParamForAll(RSumbitMainsRequest rSumbitMainsRequest)
            throws BusinessException {
        ROrdCartsCommRequest rOrdCartsCommRequest = new ROrdCartsCommRequest();
        List<ROrdCartCommRequest> rOrdCartCommRequests = new ArrayList<ROrdCartCommRequest>();
        rOrdCartsCommRequest.setSource(rSumbitMainsRequest.getSource());
        //优惠券
        Map<Long,CoupCheckInfoRespDTO> coupIdskuIdMap = rSumbitMainsRequest.getCoupIdskuIdMap();
        rOrdCartsCommRequest.setCoupIdskuIdMap(coupIdskuIdMap);

        List<CoupCheckBeanRespDTO> coupPlatfBean = rSumbitMainsRequest.getCoupPlatfBean();
        rOrdCartsCommRequest.setCoupPlatfBean(coupPlatfBean);
        //优惠券

        for (RSumbitMainRequest rSumbitMainRequest : rSumbitMainsRequest.getSumbitMainList()) {
            ROrdCartCommRequest rOrdCartCommRequest = new ROrdCartCommRequest();
            rOrdCartCommRequest.setId(rSumbitMainRequest.getCartId());

            // 补齐购物车信息
            ObjectCopyUtil.copyObjValue(rSumbitMainRequest, rOrdCartCommRequest, null, false);

            // 补齐优惠券
            rOrdCartCommRequest.setCoupCheckBean(rSumbitMainRequest.getCoupCheckBean());
            //添加优惠码信息
            rOrdCartCommRequest.setCoupCode(rSumbitMainRequest.getCoupCode());
            CustInfoReqDTO custInfoReqDTO = new CustInfoReqDTO();
            custInfoReqDTO.setId(rSumbitMainsRequest.getStaff().getId());
            CustInfoResDTO custInfoResDTO = custInfoRSV.getCustInfoById(custInfoReqDTO);
            ShopStaffGroupReqDTO shopStaffGroupReqDTO = new ShopStaffGroupReqDTO();
            shopStaffGroupReqDTO.setStaffId(custInfoResDTO.getId());
            shopStaffGroupReqDTO.setShopId(rSumbitMainRequest.getShopId());
            // 客户组id
            String custGroupValue = null;
            if (custInfoResDTO != null && custInfoResDTO.getId() != null
                    && custInfoResDTO.getId() != 0) {
                custGroupValue = shopManageRSV.queryShopStaffGroup(shopStaffGroupReqDTO);
            }
            // 客户基本信息
            rOrdCartCommRequest.setCustGroupValue(custGroupValue);
            rOrdCartCommRequest.setCustLevelValue(custInfoResDTO.getCustLevelCode());
            rOrdCartCommRequest.setAreaValue(custInfoResDTO.getProvinceCode());
            //web  渠道
            rOrdCartCommRequest.setChannelValue(rSumbitMainsRequest.getSource()); 
            

            // 补齐购物车明细信息
            List<ROrdCartItemCommRequest> rOrdCartItemCommRequests = new ArrayList<ROrdCartItemCommRequest>();
            for (RSumbitSubRequest rSumbitSubRequest : rSumbitMainRequest.getPreOrdSubList()) {
                ROrdCartItemCommRequest rOrdCartItemCommRequest = new ROrdCartItemCommRequest();
                rOrdCartItemCommRequest.setId(rSumbitSubRequest.getCartItemId());

                ObjectCopyUtil.copyObjValue(rSumbitSubRequest, rOrdCartItemCommRequest, null, false);

                rOrdCartItemCommRequests.add(rOrdCartItemCommRequest);
            }
            // 每个店铺的明细列表
            rOrdCartCommRequest.setOrdCartItemCommList(rOrdCartItemCommRequests);

            // 每个购物车信息
            rOrdCartCommRequests.add(rOrdCartCommRequest);
        }
        // 该买家的购物车信息列表
        rOrdCartsCommRequest.setOrdCartsCommList(rOrdCartCommRequests);
        rOrdCartsCommRequest.setStaffId(rSumbitMainsRequest.getStaff().getId());
        return rOrdCartsCommRequest;
    }
    
    @Override
    public ROrdCartsChkResponse queryOrdOperChk(
            ROrderDetailsRequest info) throws BusinessException {
        if(info == null){
            LogUtil.info(MODULE, "入参对象不能为空");
            throw new BusinessException(MsgConstants.InputMsgCode.ORD_INPUT_300000); 
        }
        if(StringUtil.isBlank(info.getOrderId())){
            LogUtil.info(MODULE, "订单编号不能为空");
            throw new BusinessException(MsgConstants.InputMsgCode.ORD_INPUT_311000); 
        }
        if(StringUtil.isBlank(info.getOper())){
            LogUtil.info(MODULE, "操作类型不能为空");
            throw new BusinessException(MsgConstants.InputMsgCode.ORD_INPUT_311002);
        }
        ROrdCartsChkResponse rep = null;
        try {
            rep = this.ordOperChkSV.queryOrdOperChk(info);
        } catch (BusinessException be) {
            LogUtil.error(MODULE, "===业务异常===",be);
            throw be;
        } catch (Exception e) {
            LogUtil.error(MODULE, "===系统异常===",e);
            throw new BusinessException(MsgConstants.ServiceMsgCode.ORD_SERVER_310000);
        }
        
        return rep;
        
    }


	@Override
	public SOrderDetailsComm queryOrderInvoice(String orderId) throws BusinessException {
		if(StringUtil.isBlank(orderId)){
            LogUtil.info(MODULE, "订单编号不能为空");
            throw new BusinessException(MsgConstants.InputMsgCode.ORD_INPUT_311000); 
        }
		SOrderDetailsComm  sOrderDetailsComm  = this.ordMainSV.queryInvoiceByOrderId(orderId);
		if(StringUtil.isNotEmpty(sOrderDetailsComm)){
			return sOrderDetailsComm;
		}
		return null;
	}
	
    @Override
    public ROrdAddressResponse queryOrderAddress(ROrderIdRequest rOrderIdRequest)
            throws BusinessException {
        if(rOrderIdRequest == null){
            LogUtil.info(MODULE, "入参对象不能为空");
            throw new BusinessException(MsgConstants.InputMsgCode.ORD_INPUT_300000); 
        }
        if(StringUtil.isBlank(rOrderIdRequest.getOrderId())){
            LogUtil.info(MODULE, "订单编号不能为空");
            throw new BusinessException(MsgConstants.InputMsgCode.ORD_INPUT_311000); 
        }
        ROrdAddressResponse rep = null;
        try {
            rep = this.ordMainSV.queryOrderAddress(rOrderIdRequest);
        } catch (BusinessException be) {
            LogUtil.error(MODULE, "===业务异常===",be);
            throw be;
        } catch (Exception e) {
            LogUtil.error(MODULE, "===系统异常===",e);
            throw new BusinessException(MsgConstants.ServiceMsgCode.ORD_SERVER_310028);
        }
        return rep;
    }

    @Override
    public void updateOrderAddress(ROrdAddressRequest rOrdAddressRequest) throws BusinessException {
        if(rOrdAddressRequest == null){
            LogUtil.info(MODULE, "入参对象不能为空");
            throw new BusinessException(MsgConstants.InputMsgCode.ORD_INPUT_300000); 
        }
        if(StringUtil.isBlank(rOrdAddressRequest.getOrderId())){
            LogUtil.info(MODULE, "订单编号不能为空");
            throw new BusinessException(MsgConstants.InputMsgCode.ORD_INPUT_311000); 
        }
        try {
            this.ordMainSV.updateOrderAddress(rOrdAddressRequest);
        } catch (BusinessException be) {
            LogUtil.error(MODULE, "===业务异常===",be);
            throw be;
        } catch (Exception e) {
            LogUtil.error(MODULE, "===系统异常===",e);
            throw new BusinessException(MsgConstants.ServiceMsgCode.ORD_SERVER_310029);
        }
    }

    @Override
    public PageResponseDTO<ROrdInvoiceResponse> queryOrderInvoiceInfo(RQueryOrderRequest rQueryOrderRequest)
            throws BusinessException {
        if(rQueryOrderRequest == null){
            LogUtil.info(MODULE, "入参对象不能为空");
            throw new BusinessException(MsgConstants.InputMsgCode.ORD_INPUT_300000); 
        }
        if (rQueryOrderRequest.getBegDate() == null) {
            LogUtil.info(MODULE, "开始时间不能为空");
            throw new BusinessException(MsgConstants.InputMsgCode.ORD_INPUT_311004);
        }
        if (rQueryOrderRequest.getEndDate() == null) {
            LogUtil.info(MODULE, "结束时间不能为空");
            throw new BusinessException(MsgConstants.InputMsgCode.ORD_INPUT_311004);
        }
        PageResponseDTO<ROrdInvoiceResponse> rcor = null;
        try {
            rcor = this.ordMainSV.queryOrderInvoiceInfo(rQueryOrderRequest);
        } catch (BusinessException be) {
            LogUtil.error(MODULE, "===业务异常===",be);
            throw be;
        } catch (Exception e) {
            LogUtil.error(MODULE, "===系统异常===",e);
            throw new BusinessException(MsgConstants.ServiceMsgCode.ORD_SERVER_310030);
        }
        
        return rcor;
    }

    @Override
    public void updateOrderInvoiceInfo(ROrdInvoiceRequest rOrdInvoiceRequest)
            throws BusinessException {
        if(rOrdInvoiceRequest == null){
            LogUtil.info(MODULE, "入参对象不能为空");
            throw new BusinessException(MsgConstants.InputMsgCode.ORD_INPUT_300000); 
        }
        if(StringUtil.isBlank(rOrdInvoiceRequest.getOrderId())){
            LogUtil.info(MODULE, "订单编号不能为空");
            throw new BusinessException(MsgConstants.InputMsgCode.ORD_INPUT_311000); 
        }
        try {
            this.ordMainSV.updateOrderInvoiceInfo(rOrdInvoiceRequest);
        } catch (BusinessException be) {
            LogUtil.error(MODULE, "===业务异常===",be);
            throw be;
        } catch (Exception e) {
            LogUtil.error(MODULE, "===系统异常===",e);
            throw new BusinessException(MsgConstants.ServiceMsgCode.ORD_SERVER_310031);
        }
    }
    
    @Override
    public List<ROrdMainResponse> queryNeedCancelOrder(RQueryOrderRequest queryOrderRequest)
            throws BusinessException {
        if(queryOrderRequest == null){
            LogUtil.info(MODULE, "入参对象不能为空");
            throw new BusinessException(MsgConstants.InputMsgCode.ORD_INPUT_300000); 
        }
        if(queryOrderRequest.getCount()<1){
            LogUtil.info(MODULE, "订单编号不能为空");
            throw new BusinessException(MsgConstants.InputMsgCode.ORD_INPUT_311000); 
        }
        if(queryOrderRequest.getTableIndex()<0){
            LogUtil.info(MODULE, "订单编号不能为空");
            throw new BusinessException(MsgConstants.InputMsgCode.ORD_INPUT_311000); 
        }
        try {
            return this.ordMainSV.queryNeedCancelOrder(queryOrderRequest);
        } catch (BusinessException be) {
            LogUtil.error(MODULE, "===业务异常===",be);
            throw be;
        } catch (Exception e) {
            LogUtil.error(MODULE, "===系统异常===",e);
            throw new BusinessException(MsgConstants.ServiceMsgCode.ORD_SERVER_310031);
        }
    }

    @Override
    public ROrderGiftsResponse queryOrderGift(ROrderIdRequest info)
            throws BusinessException {
        if(info == null){
            LogUtil.info(MODULE, "入参对象不能为空");
            throw new BusinessException(MsgConstants.InputMsgCode.ORD_INPUT_300000); 
        }
        if(StringUtil.isBlank(info.getOrderId())){
            LogUtil.info(MODULE, "订单编号不能为空");
            throw new BusinessException(MsgConstants.InputMsgCode.ORD_INPUT_311000); 
        }
        try {
            List<OrdGift> ordGifts=this.ordGiftSV.queryOrdGift(info);
            if(CollectionUtils.isEmpty(ordGifts)){
                return null;
            }
            List<ROrderGiftResponse> results=new ArrayList<ROrderGiftResponse>();
            for(OrdGift ordGift:ordGifts){
                ROrderGiftResponse result=new ROrderGiftResponse();
                ObjectCopyUtil.copyObjValue(ordGift, result, null, false);
                results.add(result);
            }
            ROrderGiftsResponse re=new ROrderGiftsResponse();
            re.setOrderGifts(results);
            return re;
        } catch (BusinessException be) {
            LogUtil.error(MODULE, "===业务异常===",be);
            throw be;
        } catch (Exception e) {
            LogUtil.error(MODULE, "===系统异常===",e);
            throw new BusinessException(MsgConstants.ServiceMsgCode.ORD_SERVER_310031);
        }
    }

    @Override
    public RExportExcleResponse exportOrder2Excle(RQueryOrderRequest rQueryOrderRequest)
            throws BusinessException {
        if(rQueryOrderRequest == null){
            LogUtil.info(MODULE, "入参对象不能为空");
            throw new BusinessException(MsgConstants.InputMsgCode.ORD_INPUT_300000); 
        }
        
        if(rQueryOrderRequest.getBegDate() == null){
            LogUtil.info(MODULE, "开始时间不能为空");
            throw new BusinessException(MsgConstants.InputMsgCode.ORD_INPUT_311004); 
        }
        if(rQueryOrderRequest.getEndDate() == null){
            LogUtil.info(MODULE, "结束时间不能为空");
            throw new BusinessException(MsgConstants.InputMsgCode.ORD_INPUT_311004); 
        }
        try {
            return this.ordExportFileSV.queryOrder2Excle(rQueryOrderRequest);
        } catch (BusinessException be) {
            LogUtil.error(MODULE, "===业务异常==="+be);
            throw be;
        } catch (Exception e) {
            LogUtil.error(MODULE, "===系统异常==="+e);
            throw new BusinessException(MsgConstants.ServiceMsgCode.ORD_SERVER_310004);
        }
        
    }

    @Override
    public PageResponseDTO<ROrderDetailsResponse> exportOrder2Print(
            RQueryOrderRequest queryOrderRequest) throws BusinessException {
        if(queryOrderRequest == null){
            LogUtil.info(MODULE, "入参对象不能为空");
            throw new BusinessException(MsgConstants.InputMsgCode.ORD_INPUT_300000); 
        }
        
        if(queryOrderRequest.getBegDate() == null){
            LogUtil.info(MODULE, "开始时间不能为空");
            throw new BusinessException(MsgConstants.InputMsgCode.ORD_INPUT_311004); 
        }
        if(queryOrderRequest.getEndDate() == null){
            LogUtil.info(MODULE, "结束时间不能为空");
            throw new BusinessException(MsgConstants.InputMsgCode.ORD_INPUT_311004); 
        }
        try {
            return this.ordExportFileSV.exportOrder2Print(queryOrderRequest);
        } catch (BusinessException be) {
            LogUtil.error(MODULE, "===业务异常==="+be);
            throw be;
        } catch (Exception e) {
            LogUtil.error(MODULE, "===系统异常==="+e);
            throw new BusinessException(MsgConstants.ServiceMsgCode.ORD_SERVER_310004);
        }
    }

    @Override
    public RExportExcleResponse exportOrderBarCode(RQueryOrderRequest rQueryOrderRequest)
            throws BusinessException {
        if(rQueryOrderRequest == null){
            LogUtil.info(MODULE, "入参对象不能为空");
            throw new BusinessException(MsgConstants.InputMsgCode.ORD_INPUT_300000);
        }

        if(rQueryOrderRequest.getBegDate() == null){
            LogUtil.info(MODULE, "开始时间不能为空");
            throw new BusinessException(MsgConstants.InputMsgCode.ORD_INPUT_311004);
        }
        if(rQueryOrderRequest.getEndDate() == null){
            LogUtil.info(MODULE, "结束时间不能为空");
            throw new BusinessException(MsgConstants.InputMsgCode.ORD_INPUT_311004);
        }
        try {
            return this.ordExportFileSV.queryOrderBarCode(rQueryOrderRequest);
        } catch (BusinessException be) {
            LogUtil.error(MODULE, "===业务异常==="+be);
            throw be;
        } catch (Exception e) {
            LogUtil.error(MODULE, "===系统异常==="+e);
            throw new BusinessException(MsgConstants.ServiceMsgCode.ORD_SERVER_310004);
        }

    }

    @Override
    public PageResponseDTO<ROrdMainResponse> querynotInAuditTradeCheckOrders(
            RQueryOrderRequest rQueryOrderRequest) throws BusinessException {
        if(rQueryOrderRequest == null){
            LogUtil.info(MODULE, "入参对象不能为空");
            throw new BusinessException(MsgConstants.PayInputMsgCode.PAY_INPUT_300000); 
        }
        if(rQueryOrderRequest.getEndDate()==null){
            LogUtil.info(MODULE, "入参对象不能为空");
            throw new BusinessException(MsgConstants.PayInputMsgCode.PAY_INPUT_300017); 
        }
        LogUtil.info(MODULE, rQueryOrderRequest.toString());
        try {
            return ordMainSV.querynotInAuditTradeCheckOrders(rQueryOrderRequest);
            
        } catch (BusinessException be) {
            LogUtil.error(MODULE, "===业务异常==="+be);
            throw be;
        } catch (Exception e) {
            LogUtil.error(MODULE, "===系统异常==="+e);
            throw new BusinessException(MsgConstants.PayServiceMsgCode.PAY_SERVER_310023);
        }
    }

    @Override
    public ROrdCartsChkResponse queryChkStatus(ROrderDetailsRequest rOrderDetailsRequest)
            throws BusinessException {
        if(rOrderDetailsRequest == null){
            LogUtil.info(MODULE, "入参对象不能为空");
            throw new BusinessException(MsgConstants.InputMsgCode.ORD_INPUT_300000); 
        }
        if(StringUtil.isBlank(rOrderDetailsRequest.getOrderId())){
            LogUtil.info(MODULE, "订单编号不能为空");
            throw new BusinessException(MsgConstants.InputMsgCode.ORD_INPUT_311000); 
        }
        if(StringUtil.isBlank(rOrderDetailsRequest.getOper())){
            LogUtil.info(MODULE, "操作类型不能为空");
            throw new BusinessException(MsgConstants.InputMsgCode.ORD_INPUT_311002);
        }
        ROrdCartsChkResponse rep = null;
        try {
            rep = this.ordOperChkSV.queryChkStatus(rOrderDetailsRequest);
        } catch (BusinessException be) {
            LogUtil.error(MODULE, "===业务异常===",be);
            throw be;
        } catch (Exception e) {
            LogUtil.error(MODULE, "===系统异常===",e);
            throw new BusinessException(MsgConstants.ServiceMsgCode.ORD_SERVER_310000);
        }
        
        return rep;
    }
    @Override
    public ROrdCartsChkResponse queryShopChkStatus(ROrderDetailsRequest rOrderDetailsRequest,
            List<ShopInfoResDTO> shopInfoResDTOs) throws BusinessException {
        if(rOrderDetailsRequest == null){
            LogUtil.info(MODULE, "入参对象不能为空");
            throw new BusinessException(MsgConstants.InputMsgCode.ORD_INPUT_300000); 
        }
        if(StringUtil.isBlank(rOrderDetailsRequest.getOrderId())){
            LogUtil.info(MODULE, "订单编号不能为空");
            throw new BusinessException(MsgConstants.InputMsgCode.ORD_INPUT_311000); 
        }
        if(StringUtil.isBlank(rOrderDetailsRequest.getOper())){
            LogUtil.info(MODULE, "操作类型不能为空");
            throw new BusinessException(MsgConstants.InputMsgCode.ORD_INPUT_311002);
        }
        ROrdCartsChkResponse rep = null;
        try {
            rep = this.ordOperChkSV.queryShopChkStatus(rOrderDetailsRequest, shopInfoResDTOs);
        } catch (BusinessException be) {
            LogUtil.error(MODULE, "===业务异常===",be);
            throw be;
        } catch (Exception e) {
            LogUtil.error(MODULE, "===系统异常===",e);
            throw new BusinessException(MsgConstants.ServiceMsgCode.ORD_SERVER_310000);
        }
        
        return rep;
    }
    
    @Override
	public ROrdMainResponse queryStaffIdByOrderId(ROrderDetailsRequest rOrderDetailsRequest) throws BusinessException {
    	if(rOrderDetailsRequest==null){
			LogUtil.info(MODULE, "订单编号不能为空");
            throw new BusinessException(MsgConstants.InputMsgCode.ORD_INPUT_311000);
		}
		ROrdMainResponse staffIdResp = null;
		try {
			staffIdResp=this.ordMainSV.queryStaffIdByOrderId(rOrderDetailsRequest);
        } catch (BusinessException be) {
            LogUtil.error(MODULE, "===业务异常===",be);
            throw be;
        } catch (Exception e) {
            LogUtil.error(MODULE, "===系统异常===",e);
            throw new BusinessException(MsgConstants.ServiceMsgCode.ORD_SERVER_310000);
        }
		
		return staffIdResp;
	}

    @Override
    public boolean checkBackMoney(ROrdReturnRefundReq req) throws BusinessException{
    	if(req == null){
            LogUtil.info(MODULE, "入参对象不能为空");
            throw new BusinessException(MsgConstants.InputMsgCode.ORD_INPUT_300000); 
        }
    	
    	if(StringUtil.isBlank(req.getOrderId())){
            LogUtil.info(MODULE, "入参对象不能为空");
            throw new BusinessException(MsgConstants.InputMsgCode.ORD_INPUT_311000); 
        }
    	
    	return ordMainSV.checkBackMoney(req);
    }

    @Override
    public PageResponseDTO<RCustomerOrdResponse> queryOrderSelectiveStatus(RQueryOrderRequest rQueryOrderRequest) throws BusinessException {
            if(rQueryOrderRequest == null){
                LogUtil.info(MODULE, "入参对象不能为空");
                throw new BusinessException(MsgConstants.InputMsgCode.ORD_INPUT_300000);
            }
            if(rQueryOrderRequest.getStaffId() ==null || rQueryOrderRequest.getStaffId() < 1){
                LogUtil.info(MODULE, "买家id不能为空");
                throw new BusinessException(MsgConstants.InputMsgCode.ORD_INPUT_311003);
            }
            if(rQueryOrderRequest.getSiteId() == null ){
                LogUtil.info(MODULE, "所属站点不能为空");
                throw new BusinessException(MsgConstants.InputMsgCode.ORD_INPUT_300001);
            }
            rQueryOrderRequest.setSysType(OrdConstants.SysType.SYS_TYPE_BASE);
            PageResponseDTO<RCustomerOrdResponse> rcor = null;
            try {
                rcor = this.ordMainSV.queryOrderSelectiveStatus(rQueryOrderRequest);
            } catch (BusinessException be) {
                LogUtil.error(MODULE, "===业务异常===",be);
                throw be;
            } catch (Exception e) {
                LogUtil.error(MODULE, "===系统异常===",e);
                throw new BusinessException(MsgConstants.ServiceMsgCode.ORD_SERVER_310001);
            }
            return rcor;
    }

	@Override
	public void updateInvoice(ROrderInvoiceReq req) throws BusinessException {
		// TODO Auto-generated method stub
		 if(req == null){
             LogUtil.info(MODULE, "入参对象不能为空");
             throw new BusinessException(MsgConstants.InputMsgCode.ORD_INPUT_300000);
         } 
		 if(req.getInvoiceType()==null){
             LogUtil.info(MODULE, "发票类型不能为空");
             throw new BusinessException(MsgConstants.InputMsgCode.ORD_INPUT_311003);
         }
		 try{
			 ordMainSV.updateInvoice(req);
		 }catch (BusinessException be) {
             LogUtil.error(MODULE, "===业务异常===",be);
             throw be;
         }  catch (Exception e) {
             LogUtil.error(MODULE, "===系统异常===",e);
             throw new BusinessException(MsgConstants.ServiceMsgCode.ORD_SERVER_310001);
         }
	}

	@Override
	public void updateBuyerMsg(ROrdBuyerMsgReq req) throws BusinessException {
		// TODO Auto-generated method stub
		 if(req == null){
             LogUtil.info(MODULE, "入参对象不能为空");
             throw new BusinessException(MsgConstants.InputMsgCode.ORD_INPUT_300000);
         } 		
		 try{
			 ordMainSV.updateBuyerMsg(req);
		 }catch (BusinessException be) {
             LogUtil.error(MODULE, "===业务异常===",be);
             throw be;
         }  catch (Exception e) {
             LogUtil.error(MODULE, "===系统异常===",e);
             throw new BusinessException(MsgConstants.ServiceMsgCode.ORD_SERVER_310001);
         }
	}

	@Override
	public List<ROrderDetailsResponse> findPrintOrderDetails(ROrderDetialPrintReq req) throws BusinessException {
		// TODO Auto-generated method stub
		 if(req == null){
			 LogUtil.info(MODULE, "入参对象不能为空");
             throw new BusinessException(MsgConstants.InputMsgCode.ORD_INPUT_300000);
		 }
		 if(StringUtil.isBlank(req.getOrderIds())){
			 LogUtil.info(MODULE, "OrderIds不能为空");
             throw new BusinessException(MsgConstants.InputMsgCode.ORD_INPUT_311015);
		 }
		 try{
			 return ordMainSV.findPrintOrderDetails(req);
		 }catch (BusinessException be) {
            LogUtil.error(MODULE, "===业务异常===",be);
            throw be;
        }  catch (Exception e) {
            LogUtil.error(MODULE, "===系统异常===",e);
            throw new BusinessException(MsgConstants.ServiceMsgCode.ORD_SERVER_310001);
        }
	}
	@Override
	public ROrdSpecialCountResponse findOrdSpecialCount(RQueryOrderRequest req) throws BusinessException {
		 if(req == null){
			 LogUtil.info(MODULE, "入参对象不能为空");
             throw new BusinessException(MsgConstants.InputMsgCode.ORD_INPUT_300000);
		 }
		 if(LongUtils.isEmpty(req.getStaffId())){
			 LogUtil.info(MODULE, "买家id不能为空");
	         throw new BusinessException(MsgConstants.InputMsgCode.ORD_INPUT_311003);           
		 }
		 try{
			 return ordMainSV.findOrdSpecialCount(req);
		 }catch (BusinessException be) {
            LogUtil.error(MODULE, "===业务异常===",be);
            throw be;
        }  catch (Exception e) {
            LogUtil.error(MODULE, "===系统异常===",e);
            throw new BusinessException(MsgConstants.ServiceMsgCode.ORD_SERVER_310001);
        }
	}

	@Override
	public void updateSellerMsg(ROrdSellerMsgDTO rOrdSellerMsgDTO) throws BusinessException {
		ordMainSV.updateSellerMsg(rOrdSellerMsgDTO);
	}

	@Override
	public ROrdSellerMsgDTO querySellerMsgByOrderId(ROrdSellerMsgDTO rOrdSellerMsgDTO) throws BusinessException {
		return ordMainSV.querySellerMsgById(rOrdSellerMsgDTO.getOrderId());	
	}

}
