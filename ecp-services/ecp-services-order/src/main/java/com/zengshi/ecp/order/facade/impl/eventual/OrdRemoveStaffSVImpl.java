package com.zengshi.ecp.order.facade.impl.eventual;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import net.sf.json.JSONObject;

import com.zengshi.ecp.general.order.dto.ROrdCartCommRequest;
import com.zengshi.ecp.general.order.dto.ROrdCartItemCommRequest;
import com.zengshi.ecp.general.order.dto.ROrdCartsCommRequest;
import com.zengshi.ecp.order.dao.model.OrdDiscount;
import com.zengshi.ecp.order.dao.model.OrdMain;
import com.zengshi.ecp.order.dao.model.OrdProm;
import com.zengshi.ecp.order.dao.model.OrdSub;
import com.zengshi.ecp.order.dubbo.dto.ROrderDetailsRequest;
import com.zengshi.ecp.order.dubbo.dto.SBaseAndSubInfo;
import com.zengshi.ecp.order.dubbo.util.MsgConstants;
import com.zengshi.ecp.order.dubbo.util.OrdConstants;
import com.zengshi.ecp.order.facade.interfaces.eventual.IOrdRemoveStaffSV;
import com.zengshi.ecp.order.service.busi.interfaces.IOrdDiscountSV;
import com.zengshi.ecp.order.service.busi.interfaces.IOrdMainSV;
import com.zengshi.ecp.order.service.busi.interfaces.IOrdPromSV;
import com.zengshi.ecp.order.service.busi.interfaces.IOrdSubSV;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.staff.dubbo.dto.ListReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.ScoreExchangeReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.TransactionContentReqDTO;
import com.zengshi.ecp.staff.dubbo.interfaces.IStaffUnionRSV;
import com.zengshi.ecp.staff.dubbo.util.StaffConstants;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.ObjectCopyUtil;
import com.alibaba.dubbo.common.utils.CollectionUtils;
import com.alibaba.fastjson.JSON;
import com.distribute.tx.common.TransactionStatus;

public class OrdRemoveStaffSVImpl implements IOrdRemoveStaffSV {
    
    @Resource
    private IOrdMainSV ordMainSV;
    
    @Resource
    private IOrdSubSV ordSubSV;
    
    @Resource
    private IOrdPromSV  ordPromSV;
    
    @Resource
    private IOrdDiscountSV ordDiscountSV;
    
    @Resource
    private IStaffUnionRSV staffUnionRSV;
    
    private static final String MODULE = OrdRemoveStaffSVImpl.class.getName();

    @Override
    public void joinTransaction(JSONObject message, TransactionStatus status, String transactionName) {
        try {

            final ROrderDetailsRequest rOrderDetailsRequest = JSON.parseObject(message.toString(),ROrderDetailsRequest.class);
            LogUtil.info(MODULE, "取消订单入参OrdRemoveStaffSVImpl=============" + message.toString());

            dealMethod(rOrderDetailsRequest);
        } catch (BusinessException be) {
            LogUtil.error(MODULE, "取消订单客户域接口处理业务异常", be);
            status.setRollbackOnly();
            throw new BusinessException(be.getErrorCode());
        } catch (Exception e) {
            LogUtil.error(MODULE, "取消订单客户域接口处理系统异常", e);
            status.setRollbackOnly();
            throw new BusinessException(MsgConstants.OtherSysMsgCode.CALL_STAFF_SERVER_342000);
        }
    }

    @Override
    public void dealMethod(ROrderDetailsRequest rOrderDetailsRequest) {
        
        //0 进行接口入参的封装
        ROrdCartsCommRequest rOrdCartsCommRequest = new ROrdCartsCommRequest();
        List<ROrdCartCommRequest> rOrdCartCommRequests = new ArrayList<ROrdCartCommRequest>();
        
        OrdMain ordMain = this.ordMainSV.queryOrderByOrderId(rOrderDetailsRequest.getOrderId());
        if(ordMain == null){
            LogUtil.info(MODULE, "未找到[" + rOrderDetailsRequest.getOrderId() + "]该订单信息");
            throw new BusinessException(MsgConstants.ProMsgCode.ORD_PRO_312001);
        }
        ROrdCartCommRequest  rOrdCartCommRequest = new ROrdCartCommRequest();
        //补齐订单信息
        ObjectCopyUtil.copyObjValue(ordMain, rOrdCartCommRequest, null,false);
        rOrdCartCommRequest.setOrderId(ordMain.getId());

        //补齐订单促销
        SBaseAndSubInfo sBaseAndSubInfo = new SBaseAndSubInfo();
        sBaseAndSubInfo.setOrderId(rOrderDetailsRequest.getOrderId());
        OrdProm op = this.ordPromSV.queryOrdProm(sBaseAndSubInfo);
        if(op != null){
            rOrdCartCommRequest.setPromId(op.getPromId());
        }
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
            rOrdCartItemCommRequest.setOrderSubId(ordSub.getId());
            
            //补齐promId
            SBaseAndSubInfo sBaseAndSubInfo1 = new SBaseAndSubInfo();
            sBaseAndSubInfo1.setOrderId(rOrderDetailsRequest.getOrderId());
            sBaseAndSubInfo1.setOrderSubId(rOrdCartItemCommRequest.getOrderSubId());
            OrdProm opsub = this.ordPromSV.queryOrdProm(sBaseAndSubInfo1);
            if(opsub != null){
                rOrdCartItemCommRequest.setPromId(opsub.getPromId());
                rOrdCartItemCommRequest.setOrdPromId(opsub.getRelaMainPromId());
            }
            
            rOrdCartItemCommRequests.add(rOrdCartItemCommRequest);
        }
        rOrdCartCommRequest.setOrdCartItemCommList(rOrdCartItemCommRequests);
        rOrdCartCommRequests.add(rOrdCartCommRequest);
        rOrdCartsCommRequest.setOrdCartsCommList(rOrdCartCommRequests);
        rOrdCartsCommRequest.setStaffId(ordMain.getStaffId());
        
        //进行用户资金张合和积分的补尝
        List<ScoreExchangeReqDTO> scoreExchangeReqDTOs = new ArrayList<ScoreExchangeReqDTO>();
        List<TransactionContentReqDTO> trList=new ArrayList<TransactionContentReqDTO>();
        ListReqDTO<ScoreExchangeReqDTO> scoreReq = new ListReqDTO<ScoreExchangeReqDTO>();
        
        List<OrdDiscount> ordDiscounts = this.ordDiscountSV.queryOrdDiscountByOrderId(rOrderDetailsRequest.getOrderId());
        
        if(CollectionUtils.isNotEmpty(ordDiscounts)){
            for(OrdDiscount ordDiscount:ordDiscounts){
                if(ordDiscount.getOrderId() != null && ordDiscount.getOrderSubId() == null
                        && ordDiscount.getDiscountType().equals(OrdConstants.DiscountType.TYPE_CAPITAL_CODE)
                        && ordDiscount.getDiscountTitle().equals(OrdConstants.DiscountType.TYPE_CAPITAL_NAME)){
                    TransactionContentReqDTO tr=new TransactionContentReqDTO();
                    tr.setStaffId(ordMain.getStaffId());
                    tr.setShopId(ordMain.getShopId());
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
        scoreReq.setCurrentSiteId(rOrderDetailsRequest.getCurrentSiteId());
        ListReqDTO<TransactionContentReqDTO> listReqDto = new ListReqDTO<TransactionContentReqDTO>();
        listReqDto.setList(trList);
        listReqDto.setCurrentSiteId(rOrderDetailsRequest.getCurrentSiteId());
        if(CollectionUtils.isNotEmpty(scoreExchangeReqDTOs) || CollectionUtils.isNotEmpty(trList) ){
            LogUtil.info(MODULE,"调用客户域入参"+JSON.toJSONString(rOrdCartsCommRequest));
            this.staffUnionRSV.saveStaffRelInfoForOrderRollBack(scoreReq,listReqDto);
        } else {
            LogUtil.info(MODULE,"调用客户域入参:无客户域的下单信息不调用客户域");
        }
    }

}

