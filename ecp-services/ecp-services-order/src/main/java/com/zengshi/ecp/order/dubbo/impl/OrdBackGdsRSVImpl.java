package com.zengshi.ecp.order.dubbo.impl;

import com.zengshi.ecp.coupon.dubbo.interfaces.ICoupDetailRSV;
import com.zengshi.ecp.general.order.dto.ROrdCartsChkResponse;
import com.zengshi.ecp.order.dao.model.OrdBackApply;
import com.zengshi.ecp.order.dubbo.dto.*;
import com.zengshi.ecp.order.dubbo.interfaces.IOrdBackGdsRSV;
import com.zengshi.ecp.order.dubbo.util.BackConstants;
import com.zengshi.ecp.order.dubbo.util.MsgConstants;
import com.zengshi.ecp.order.dubbo.util.OrderUtils;
import com.zengshi.ecp.order.facade.interfaces.eventual.IBackPayOrderSV;
import com.zengshi.ecp.order.service.busi.interfaces.IOrdBackApplySV;
import com.zengshi.ecp.order.service.busi.interfaces.IOrdCalculateSV;
import com.zengshi.ecp.order.service.busi.interfaces.IOrdOperChkSV;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.staff.dubbo.interfaces.IStaffUnionRSV;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.ObjectCopyUtil;
import com.zengshi.paas.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;

public class OrdBackGdsRSVImpl implements IOrdBackGdsRSV {
    
    @Autowired(required = false)
    private IOrdBackApplySV ordBackApplySV;
    
    @Resource
    private IBackPayOrderSV backPayOrderSV; 
    
    @Resource
    private IOrdOperChkSV ordOperChkSV;
    
    @Resource
    private IOrdCalculateSV ordCalculateSV;
    
    @Resource
    private IStaffUnionRSV staffUnionRSV;
    
    @Resource
    private ICoupDetailRSV coupDetailRSV;
    
    private static final String MODULE = OrdBackGdsRSVImpl.class.getName();

    @Override
    public PageResponseDTO<ROrderBackResp> queryBackGdsByShop(ROrderBackReq rOrderBackReq)
            throws BusinessException {
        
        if(rOrderBackReq == null){
            LogUtil.info(MODULE, "入参对象不能为空");
            throw new BusinessException(MsgConstants.InputMsgCode.ORD_INPUT_300000); 
        }
        if(rOrderBackReq.getShopId() ==null || rOrderBackReq.getShopId() < 1){
            LogUtil.info(MODULE, "店铺id");
            throw new BusinessException(MsgConstants.InputMsgCode.ORD_INPUT_311002);
        }
        PageResponseDTO<ROrderBackResp> resp = null;
        try {
            rOrderBackReq.setApplyType(BackConstants.ApplyType.BACK_GDS);
            resp = this.ordBackApplySV.queryBackGdsByShop(rOrderBackReq);
        } catch (BusinessException be) {
            LogUtil.error(MODULE, "===业务异常===",be);
            throw be;
        } catch (Exception e) {
            LogUtil.error(MODULE, "===系统异常===",e);
            throw new BusinessException(MsgConstants.ServiceMsgCode.ORD_SERVER_310033);
        }
        return resp;
    }
    
    @Override
    public PageResponseDTO<ROrderBackResp> queryBackGdsByStaff(ROrderBackReq rOrderBackReq)
            throws BusinessException {
        if(rOrderBackReq == null){
            LogUtil.info(MODULE, "入参对象不能为空");
            throw new BusinessException(MsgConstants.InputMsgCode.ORD_INPUT_300000); 
        }
        PageResponseDTO<ROrderBackResp> resp = null;
        try {
            rOrderBackReq.setApplyType(BackConstants.ApplyType.BACK_GDS);
            resp = this.ordBackApplySV.queryBackGdsByStaff(rOrderBackReq);
        } catch (BusinessException be) {
            LogUtil.error(MODULE, "===业务异常===",be);
            throw be;
        } catch (Exception e) {
            LogUtil.error(MODULE, "===系统异常===",e);
            throw new BusinessException(MsgConstants.ServiceMsgCode.ORD_SERVER_310034);
        }
        return resp;
    }
    
    @Override
    public RBackApplyOrdResp queryBackOrderSub(ROrderBackReq rOrderBackReq)
            throws BusinessException {
        if(rOrderBackReq == null){
            LogUtil.info(MODULE, "入参对象不能为空");
            throw new BusinessException(MsgConstants.InputMsgCode.ORD_INPUT_300000); 
        }
        if(StringUtil.isBlank(rOrderBackReq.getOrderId())){
            LogUtil.info(MODULE, "订单编号不能为空");
            throw new BusinessException(MsgConstants.InputMsgCode.ORD_INPUT_311000); 
        }
        RBackApplyOrdResp resp = null;
        try {
            resp = this.ordBackApplySV.queryBackOrderSub(rOrderBackReq);
        } catch (BusinessException be) {
            LogUtil.error(MODULE, "===业务异常===",be);
            throw be;
        } catch (Exception e) {
            LogUtil.error(MODULE, "===系统异常===",e);
            throw new BusinessException(MsgConstants.ServiceMsgCode.ORD_SERVER_310035);
        }
        return resp;
    }
    
    @Override
    public ROrderBackDetailResp queryBackGdsDetails(ROrderBackReq rOrderBackReq) throws BusinessException {
        if(rOrderBackReq == null){
            LogUtil.info(MODULE, "入参对象不能为空");
            throw new BusinessException(MsgConstants.InputMsgCode.ORD_INPUT_300000); 
        }
        if(StringUtil.isBlank(rOrderBackReq.getOrderId())){
            LogUtil.info(MODULE, "订单编号不能为空");
            throw new BusinessException(MsgConstants.InputMsgCode.ORD_INPUT_311000); 
        }
        if(rOrderBackReq.getBackId() ==null){
            LogUtil.info(MODULE, "退款或退货申请单号不能为空");
            throw new BusinessException(MsgConstants.InputMsgCode.ORD_INPUT_311011); 
        }
        ROrderBackDetailResp resp = null;
        try {
            resp = this.ordBackApplySV.queryBackDetails(rOrderBackReq);
        } catch (BusinessException be) {
            LogUtil.error(MODULE, "===业务异常===",be);
            throw be;
        } catch (Exception e) {
            LogUtil.error(MODULE, "===系统异常===",e);
            throw new BusinessException(MsgConstants.ServiceMsgCode.ORD_SERVER_310036);
        }
        return resp;
    }
    
    @Override
    public void saveBackGdsApply(RBackApplyReq rBackApplyReq) throws BusinessException {
        //orderId 不能为空
        //明细列表不能为空
        if(rBackApplyReq == null){
            LogUtil.info(MODULE, "入参对象不能为空");
            throw new BusinessException(MsgConstants.InputMsgCode.ORD_INPUT_300000); 
        }
        if(StringUtil.isBlank(rBackApplyReq.getOrderId())){
            LogUtil.info(MODULE, "订单编号不能为空");
            throw new BusinessException(MsgConstants.InputMsgCode.ORD_INPUT_311000); 
        }
        try {
            rBackApplyReq.setApplyType(BackConstants.ApplyType.BACK_GDS);
            this.ordBackApplySV.saveBackGdsApply(rBackApplyReq);           
        } catch (BusinessException be) {
            LogUtil.error(MODULE, "===业务异常===",be);
            throw be;
        } catch (Exception e) {
            LogUtil.error(MODULE, "===系统异常===",e);
            throw new BusinessException(MsgConstants.ServiceMsgCode.ORD_SERVER_310037);
        }
    }
    
    @Override
    public void saveBackGdsReview(RBackReviewReq rBackGdsReviewReq) throws BusinessException {
        //审核状态 值
        //orderId
        //backId
        //支付方式不能为空
    	LogUtil.info(MODULE, "退货审核开始");
        if(rBackGdsReviewReq == null){
            LogUtil.info(MODULE, "入参对象不能为空");
            throw new BusinessException(MsgConstants.InputMsgCode.ORD_INPUT_300000); 
        }
        if(StringUtil.isBlank(rBackGdsReviewReq.getOrderId())){
            LogUtil.info(MODULE, "订单编号不能为空");
            throw new BusinessException(MsgConstants.InputMsgCode.ORD_INPUT_311000); 
        }
        if(rBackGdsReviewReq.getBackId() ==null){
            LogUtil.info(MODULE, "退款或退货申请单号不能为空");
            throw new BusinessException(MsgConstants.InputMsgCode.ORD_INPUT_311011); 
        }
        if(StringUtil.isBlank(rBackGdsReviewReq.getStatus())){
            LogUtil.info(MODULE, "审核状态值不能为空");
            throw new BusinessException(MsgConstants.InputMsgCode.ORD_INPUT_311112); 
        }
        if(rBackGdsReviewReq.getStatus().trim().equals(BackConstants.Status.REVIEW_PASS) 
                && StringUtil.isBlank(rBackGdsReviewReq.getPayType())){
            LogUtil.info(MODULE, "退款方式不能为空");
            throw new BusinessException(MsgConstants.InputMsgCode.ORD_INPUT_311012); 
        }
        try {
            rBackGdsReviewReq.setApplyType(BackConstants.ApplyType.BACK_GDS);
            ROrderBackReq rOrderBackReq = new ROrderBackReq();
            rOrderBackReq.setOrderId(rBackGdsReviewReq.getOrderId());
            rOrderBackReq.setBackId(rBackGdsReviewReq.getBackId());
            RBackApplyResp  chkResp =this.ordBackApplySV.queryOrdBackApply(rOrderBackReq);
            if(chkResp == null || !(BackConstants.ChkStatus.CHK_BACKGDS_REVIEW.contains(chkResp.getStatus()))){
                LogUtil.info(MODULE, "申请单状态不对"+rBackGdsReviewReq.getBackId()+chkResp.getStatus()+BackConstants.ChkStatus.CHK_BACKGDS_REVIEW);
                throw new BusinessException(MsgConstants.ChkMsgCode.CHK_BACKGDS_REVIEW);
            }
            this.ordBackApplySV.saveBackReview(rBackGdsReviewReq);
            LogUtil.info(MODULE, "退货审核结束");
        } catch (BusinessException be) {
            LogUtil.error(MODULE, "===业务异常===",be);
            throw be;
        } catch (Exception e) {
            LogUtil.error(MODULE, "===系统异常===",e);
            throw new BusinessException(MsgConstants.ServiceMsgCode.ORD_SERVER_310039);
        }
    }

    @Override
    public void saveBackGdsReceipt(RBackConfirmReq rBackConfirmReq) throws BusinessException {
        //orderId
        //backId
        
        if(rBackConfirmReq == null){
            LogUtil.info(MODULE, "入参对象不能为空");
            throw new BusinessException(MsgConstants.InputMsgCode.ORD_INPUT_300000); 
        }
        if(StringUtil.isBlank(rBackConfirmReq.getOrderId())){
            LogUtil.info(MODULE, "订单编号不能为空");
            throw new BusinessException(MsgConstants.InputMsgCode.ORD_INPUT_311000); 
        }
        if(rBackConfirmReq.getBackId() ==null){
            LogUtil.info(MODULE, "退款或退货申请单号不能为空");
            throw new BusinessException(MsgConstants.InputMsgCode.ORD_INPUT_311011); 
        }
        try {
            this.ordBackApplySV.saveBackGdsReceipt(rBackConfirmReq);
        } catch (BusinessException be) {
            LogUtil.error(MODULE, "===业务异常===",be);
            throw be;
        } catch (Exception e) {
            LogUtil.error(MODULE, "===系统异常===",e);
            throw new BusinessException(MsgConstants.ServiceMsgCode.ORD_SERVER_310040);
        }
    }

    @Override
    public void saveBackGdsPayed(RBackConfirmReq rBackConfirmReq) throws BusinessException {
        //orderId
        //backId
        
        
        if(rBackConfirmReq == null){
            LogUtil.info(MODULE, "入参对象不能为空");
            throw new BusinessException(MsgConstants.InputMsgCode.ORD_INPUT_300000); 
        }
        if(StringUtil.isBlank(rBackConfirmReq.getOrderId())){
            LogUtil.info(MODULE, "订单编号不能为空");
            throw new BusinessException(MsgConstants.InputMsgCode.ORD_INPUT_311000); 
        }
        if(rBackConfirmReq.getBackId() ==null){
            LogUtil.info(MODULE, "退款或退货申请单号不能为空");
            throw new BusinessException(MsgConstants.InputMsgCode.ORD_INPUT_311011); 
        }
        try {
//            this.ordBackApplySV.saveBackGdsPayed(rBackConfirmReq);
            ROrderBackReq rOrderBackReq = new ROrderBackReq();
            rOrderBackReq.setOrderId(rBackConfirmReq.getOrderId());
            rOrderBackReq.setBackId(rBackConfirmReq.getBackId());
            RBackApplyResp  chkResp =this.ordBackApplySV.queryOrdBackApply(rOrderBackReq);
            if(chkResp == null || !(BackConstants.ChkStatus.CHK_BACKGDS_REFUND.contains(chkResp.getStatus()))){
                LogUtil.info(MODULE, "申请单状态不对"+rBackConfirmReq.getBackId()+chkResp.getStatus()+BackConstants.ChkStatus.CHK_BACKGDS_REVIEW);
                throw new BusinessException(MsgConstants.ChkMsgCode.CHK_BACKGDS_REFUND);
            }
            this.backPayOrderSV.dealMethod(rBackConfirmReq);
        } catch (BusinessException be) {
            LogUtil.error(MODULE, "===业务异常===",be);
            throw be;
        } catch (Exception e) {
            LogUtil.error(MODULE, "===系统异常===",e);
            throw new BusinessException(MsgConstants.ServiceMsgCode.ORD_SERVER_310041);
        }
        
    }
    
    @Override
    public void saveBackGdsSend(RBackConfirmReq rBackConfirmReq) throws BusinessException {
        //orderId
        //backId
        
        if(rBackConfirmReq == null){
            LogUtil.info(MODULE, "入参对象不能为空");
            throw new BusinessException(MsgConstants.InputMsgCode.ORD_INPUT_300000); 
        }
        if(StringUtil.isBlank(rBackConfirmReq.getOrderId())){
            LogUtil.info(MODULE, "订单编号不能为空");
            throw new BusinessException(MsgConstants.InputMsgCode.ORD_INPUT_311000); 
        }
        if(rBackConfirmReq.getBackId() ==null){
            LogUtil.info(MODULE, "退款或退货申请单号不能为空");
            throw new BusinessException(MsgConstants.InputMsgCode.ORD_INPUT_311011); 
        }
        try {
            this.ordBackApplySV.saveBackGdsSend(rBackConfirmReq);
        } catch (BusinessException be) {
            LogUtil.error(MODULE, "===业务异常===",be);
            throw be;
        } catch (Exception e) {
            LogUtil.error(MODULE, "===系统异常===",e);
            throw new BusinessException(MsgConstants.ServiceMsgCode.ORD_SERVER_310038);
        }
    }

    @Override
    public RBackReviewResp queryBackGdsReview(ROrderBackReq rOrderBackReq)
            throws BusinessException {
        if(rOrderBackReq == null){
            LogUtil.info(MODULE, "入参对象不能为空");
            throw new BusinessException(MsgConstants.InputMsgCode.ORD_INPUT_300000); 
        }
        if(StringUtil.isBlank(rOrderBackReq.getOrderId())){
            LogUtil.info(MODULE, "订单编号不能为空");
            throw new BusinessException(MsgConstants.InputMsgCode.ORD_INPUT_311000); 
        }
        RBackReviewResp resp = null;
        try {
            rOrderBackReq.setApplyType(BackConstants.ApplyType.BACK_GDS);
            resp = this.ordBackApplySV.queryBackReview(rOrderBackReq);
        } catch (BusinessException be) {
            LogUtil.error(MODULE, "===业务异常===",be);
            throw be;
        } catch (Exception e) {
            LogUtil.error(MODULE, "===系统异常===",e);
            throw new BusinessException(MsgConstants.ServiceMsgCode.ORD_SERVER_310047);
        }
        return resp;
    }

    @Override
    public ROrdCartsChkResponse queryOrdOperChk(ROrderDetailsRequest info)
            throws BusinessException {
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
            throw new BusinessException(MsgConstants.InputMsgCode.ORD_INPUT_311010);
        }
        ROrdCartsChkResponse rep = null;
        try {
            rep = this.ordOperChkSV.queryBackOperChk(info);
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
    public RBackPayInfoResp queryBackPayInfo(ROrderBackReq rOrderBackReq) throws BusinessException {
        if(rOrderBackReq == null){
            LogUtil.info(MODULE, "入参对象不能为空");
            throw new BusinessException(MsgConstants.InputMsgCode.ORD_INPUT_300000); 
        }
        if(StringUtil.isBlank(rOrderBackReq.getOrderId())){
            LogUtil.info(MODULE, "订单编号不能为空");
            throw new BusinessException(MsgConstants.InputMsgCode.ORD_INPUT_311000); 
        }
        if(rOrderBackReq.getBackId() ==null){
            LogUtil.info(MODULE, "退款或退货申请单号不能为空");
            throw new BusinessException(MsgConstants.InputMsgCode.ORD_INPUT_311011); 
        }
        try {
            return this.ordBackApplySV.queryBackPayInfo(rOrderBackReq);
        } catch (BusinessException be) {
            LogUtil.error(MODULE, "===业务异常===",be);
            throw be;
        } catch (Exception e) {
            LogUtil.error(MODULE, "===系统异常===",e);
            throw new BusinessException(MsgConstants.ServiceMsgCode.ORD_SERVER_310040);
        }
        
    }
    
    @Override
    public PageResponseDTO<RBackApplyResp> querynotInAuditTradeCheckOrders(
    		ROrderBackReq orderBackReq) throws BusinessException {
    	if(orderBackReq == null){
            LogUtil.info(MODULE, "入参对象不能为空");
            throw new BusinessException(MsgConstants.PayInputMsgCode.PAY_INPUT_300000); 
        }
        if(orderBackReq.getEndDate()==null){
            LogUtil.info(MODULE, "入参对象不能为空");
            throw new BusinessException(MsgConstants.PayInputMsgCode.PAY_INPUT_300017); 
        }
        LogUtil.info(MODULE, orderBackReq.toString());
        try {
            return ordBackApplySV.querynotInAuditTradeCheckOrders(orderBackReq);
            
        } catch (BusinessException be) {
            LogUtil.error(MODULE, "===业务异常==="+be);
            throw be;
        } catch (Exception e) {
            LogUtil.error(MODULE, "===系统异常==="+e);
            throw new BusinessException(MsgConstants.PayServiceMsgCode.PAY_SERVER_310023);
        }
    }
    
    @Override
    public RBackApplyResp queryOrdBackApplyByBackId(Long backId) throws BusinessException{
        try {
            RBackApplyResp backApplyResp = new RBackApplyResp();
            OrdBackApply ordBackApply = this.ordBackApplySV.queryOrdBackApplyByBackId(backId);
            if(StringUtil.isNotEmpty(ordBackApply)){
                if(ordBackApply.getStatus().equals(BackConstants.Status.REFUNDED)){
                    ObjectCopyUtil.copyObjValue(ordBackApply, backApplyResp, null, false);
                }
            }
            return backApplyResp;
        } catch (BusinessException be) {
            LogUtil.error(MODULE, "===业务异常===",be);
            throw be;
        } catch (Exception e) {
            LogUtil.error(MODULE, "===系统异常===",e);
            throw new BusinessException(MsgConstants.ServiceMsgCode.ORD_SERVER_310040);
        }
    }
}

