package com.zengshi.ecp.order.dubbo.impl;

import com.zengshi.ecp.coupon.dubbo.interfaces.ICoupDetailRSV;
import com.zengshi.ecp.order.dubbo.dto.*;
import com.zengshi.ecp.order.dubbo.interfaces.IOrdBackMoneyRSV;
import com.zengshi.ecp.order.dubbo.util.BackConstants;
import com.zengshi.ecp.order.dubbo.util.MsgConstants;
import com.zengshi.ecp.order.facade.interfaces.eventual.IBackPayOrderSV;
import com.zengshi.ecp.order.service.busi.interfaces.IOrdBackApplySV;
import com.zengshi.ecp.order.service.busi.interfaces.IOrdCalculateSV;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.staff.dubbo.interfaces.IStaffUnionRSV;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;

public class OrdBackMoneyRSVImpl implements IOrdBackMoneyRSV {

    @Autowired(required = false)
    private IOrdBackApplySV ordBackApplySV;
    
    @Resource
    private IBackPayOrderSV backPayOrderSV;
    
    @Resource
    private IOrdCalculateSV ordCalculateSV;
    
    @Resource
    private IStaffUnionRSV staffUnionRSV;
    
    @Resource
    private ICoupDetailRSV coupDetailRSV;
    
    private static final String MODULE = OrdBackGdsRSVImpl.class.getName();

    @Override
    public PageResponseDTO<ROrderBackResp> queryBackMoneyByStaff(ROrderBackReq rOrderBackReq)
            throws BusinessException {
        if(rOrderBackReq == null){
            LogUtil.info(MODULE, "入参对象不能为空");
            throw new BusinessException(MsgConstants.InputMsgCode.ORD_INPUT_300000); 
        }
        PageResponseDTO<ROrderBackResp> resp = null;
        try {
            rOrderBackReq.setApplyType(BackConstants.ApplyType.REFUND);
            resp = this.ordBackApplySV.queryBackGdsByStaff(rOrderBackReq);
        } catch (BusinessException be) {
            LogUtil.error(MODULE, "===业务异常===",be);
            throw be;
        } catch (Exception e) {
            LogUtil.error(MODULE, "===系统异常===",e);
            throw new BusinessException(MsgConstants.ServiceMsgCode.ORD_SERVER_310044);
        }
        return resp;
    }
    
    @Override
    public PageResponseDTO<ROrderBackResp> queryBackMoneyByShop(ROrderBackReq rOrderBackReq)
            throws BusinessException {
        //ShopId 不能为空
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
            rOrderBackReq.setApplyType(BackConstants.ApplyType.REFUND);
            resp = this.ordBackApplySV.queryBackGdsByShop(rOrderBackReq);
        } catch (BusinessException be) {
            LogUtil.error(MODULE, "===业务异常===",be);
            throw be;
        } catch (Exception e) {
            LogUtil.error(MODULE, "===系统异常===",e);
            throw new BusinessException(MsgConstants.ServiceMsgCode.ORD_SERVER_310045);
        }
        return resp;
    }
    
    @Override
    public ROrderBackDetailResp queryBackMoneyDetails(ROrderBackReq rOrderBackReq)
            throws BusinessException {
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
            rOrderBackReq.setApplyType(BackConstants.ApplyType.REFUND);
            resp = this.ordBackApplySV.queryBackDetails(rOrderBackReq);
        } catch (BusinessException be) {
            LogUtil.error(MODULE, "===业务异常===",be);
            throw be;
        } catch (Exception e) {
            LogUtil.error(MODULE, "===系统异常===",e);
            throw new BusinessException(MsgConstants.ServiceMsgCode.ORD_SERVER_310046);
        }
        return resp;
    }

    
    @Override
    public void saveBackMoneyReview(RBackReviewReq rBackGdsReviewReq) throws BusinessException {
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
        if(StringUtil.isBlank(rBackGdsReviewReq.getPayType())){
            LogUtil.info(MODULE, "退款方式不能为空");
            throw new BusinessException(MsgConstants.InputMsgCode.ORD_INPUT_311012); 
        }
        try {
            rBackGdsReviewReq.setApplyType(BackConstants.ApplyType.REFUND);
            
            //校验申请状态是否正确
            ROrderBackReq rOrderBackReq = new ROrderBackReq();
            rOrderBackReq.setOrderId(rBackGdsReviewReq.getOrderId());
            rOrderBackReq.setBackId(rBackGdsReviewReq.getBackId());
            RBackApplyResp  chkResp =this.ordBackApplySV.queryOrdBackApply(rOrderBackReq);
            if(chkResp == null || !(BackConstants.ChkStatus.CHK_REFUND_REVIEW.contains(chkResp.getStatus()))){
                LogUtil.info(MODULE, "申请单状态不对"+rBackGdsReviewReq.getBackId()+chkResp.getStatus()+BackConstants.ChkStatus.CHK_REFUND_REVIEW);
                throw new BusinessException(MsgConstants.ChkMsgCode.CHK_REFUND_REVIEW);
            }
            this.ordBackApplySV.saveBackReview(rBackGdsReviewReq);

        } catch (BusinessException be) {
            LogUtil.error(MODULE, "===业务异常===",be);
            throw be;
        } catch (Exception e) {
            LogUtil.error(MODULE, "===系统异常===",e);
            throw new BusinessException(MsgConstants.ServiceMsgCode.ORD_SERVER_310043);
        }
    }
    
    @Override
    public void saveBackMoneyApply(RBackApplyReq rBackApplyReq) throws BusinessException {
        
        if(rBackApplyReq == null){
            LogUtil.info(MODULE, "入参对象不能为空");
            throw new BusinessException(MsgConstants.InputMsgCode.ORD_INPUT_300000); 
        }
        if(StringUtil.isBlank(rBackApplyReq.getOrderId())){
            LogUtil.info(MODULE, "订单编号不能为空");
            throw new BusinessException(MsgConstants.InputMsgCode.ORD_INPUT_311000); 
        }
        try {
            rBackApplyReq.setApplyType(BackConstants.ApplyType.REFUND);
            this.ordBackApplySV.saveBackMoneyApply(rBackApplyReq);
        } catch (BusinessException be) {
            LogUtil.error(MODULE, "===业务异常===",be);
            throw be;
        } catch (Exception e) {
            LogUtil.error(MODULE, "===系统异常===",e);
            throw new BusinessException(MsgConstants.ServiceMsgCode.ORD_SERVER_310042);
        }
    }

    @Override
    public RBackReviewResp queryBackMoneyReview(ROrderBackReq rOrderBackReq)
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
            rOrderBackReq.setApplyType(BackConstants.ApplyType.REFUND);
            resp = this.ordBackApplySV.queryBackReview(rOrderBackReq);
        } catch (BusinessException be) {
            LogUtil.error(MODULE, "===业务异常===",be);
            throw be;
        } catch (Exception e) {
            LogUtil.error(MODULE, "===系统异常===",e);
            throw new BusinessException(MsgConstants.ServiceMsgCode.ORD_SERVER_310048);
        }
        return resp;
    }

    @Override
    public void saveBackGdsPayed(RBackConfirmReq rBackConfirmReq) throws BusinessException {
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
            if(chkResp == null || !(BackConstants.ChkStatus.CHK_REFUND_REFUND.contains(chkResp.getStatus()))){
                LogUtil.info(MODULE, "申请单状态不对"+rBackConfirmReq.getBackId()+chkResp.getStatus()+BackConstants.ChkStatus.CHK_REFUND_REFUND);
                throw new BusinessException(MsgConstants.ChkMsgCode.CHK_REFUND_REFUND);
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
    
}

