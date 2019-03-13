package com.zengshi.ecp.order.dubbo.impl;

import javax.annotation.Resource;

import com.zengshi.ecp.order.dubbo.dto.ROfflineApplyRequest;
import com.zengshi.ecp.order.dubbo.dto.ROfflinePayRequest;
import com.zengshi.ecp.order.dubbo.dto.ROfflinePayResponse;
import com.zengshi.ecp.order.dubbo.dto.ROfflineQueryRequest;
import com.zengshi.ecp.order.dubbo.dto.ROfflineQueryResponse;
import com.zengshi.ecp.order.dubbo.dto.ROfflineReviewRequest;
import com.zengshi.ecp.order.dubbo.dto.ROrderDetailsRequest;
import com.zengshi.ecp.order.dubbo.interfaces.IOrdMainRSV;
import com.zengshi.ecp.order.dubbo.interfaces.IOrdOfflineRSV;
import com.zengshi.ecp.order.dubbo.util.MsgConstants;
import com.zengshi.ecp.order.dubbo.util.OfflineConstants;
import com.zengshi.ecp.order.dubbo.util.OrdConstants;
import com.zengshi.ecp.order.facade.interfaces.eventual.IOrdOfflinePaySV;
import com.zengshi.ecp.order.service.busi.interfaces.IOrdMainSV;
import com.zengshi.ecp.order.service.busi.interfaces.IOrdOfflineChkSV;
import com.zengshi.ecp.order.service.busi.interfaces.IOrdOfflineSV;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.ObjectCopyUtil;
import com.zengshi.paas.utils.StringUtil;
import com.alibaba.fastjson.JSON;

public class OrdOfflineRSVImpl implements IOrdOfflineRSV {

    @Resource
    private IOrdMainSV ordMainSV;

    @Resource
    private IOrdOfflineSV ordOfflineSV;

    @Resource
    private IOrdOfflinePaySV ordOfflinePaySV;

    @Resource
    private IOrdOfflineChkSV ordOfflineChkSV;
    
    @Resource
    private IOrdMainRSV  ordMainRSV;

    private static final String MODULE = OrdOfflineRSVImpl.class.getName();

    @Override
    public ROfflinePayResponse queryOfflinePay(ROfflinePayRequest rOfflinePayRequest)
            throws BusinessException {
        if (rOfflinePayRequest == null) {
            LogUtil.info(MODULE, "入参对象不能为空");
            throw new BusinessException(MsgConstants.InputMsgCode.ORD_INPUT_300000);
        }
        ROfflinePayResponse ropr = null;
        try {
            ropr = this.ordMainSV.querOrderOffline(rOfflinePayRequest);
        } catch (BusinessException be) {
            LogUtil.error(MODULE, "===业务异常==="+be);
            throw be;
        } catch (Exception e) {
            LogUtil.error(MODULE, "===系统异常==="+e);
            throw new BusinessException(MsgConstants.ServiceMsgCode.ORD_SERVER_320000);
        }
        return ropr;
    }

    @Override
    public void offlineApply(ROfflineApplyRequest rOfflineApplyRequest) throws BusinessException {
        if (rOfflineApplyRequest == null) {
            LogUtil.info(MODULE, "入参对象不能为空");
            throw new BusinessException(MsgConstants.InputMsgCode.ORD_INPUT_300000);
        }
        if (StringUtil.isBlank(rOfflineApplyRequest.getOrderId())) {
            LogUtil.info(MODULE, "订单号不能为空");
            throw new BusinessException(MsgConstants.InputMsgCode.ORD_INPUT_311000);
        }
        if (rOfflineApplyRequest.getShopId() == null || rOfflineApplyRequest.getShopId() < 1) {
            LogUtil.info(MODULE, "店铺ID不能为空");
            throw new BusinessException(MsgConstants.InputMsgCode.ORD_INPUT_311002);

        }
        if (rOfflineApplyRequest.getStaffId() == null || rOfflineApplyRequest.getStaffId() < 1) {
            LogUtil.info(MODULE, "买家ID不能为空");
            throw new BusinessException(MsgConstants.InputMsgCode.ORD_INPUT_311003);
        }
        // if (StringUtil.isBlank(rOfflineApplyRequest.getRemark())) {
        // LogUtil.info(MODULE, "备注不能为空");
        // throw new BusinessException(MsgConstants.InputMsgCode.ORD_INPUT_311108);
        // }
        // if (CollectionUtils.isEmpty(rOfflineApplyRequest.getAnnex())) {
        // LogUtil.info(MODULE, "凭证不能为空");
        // throw new BusinessException(MsgConstants.InputMsgCode.ORD_INPUT_311109);
        // }
        if (rOfflineApplyRequest.getApplyType() == null) {
            LogUtil.info(MODULE, "申请类型不能为空");
            throw new BusinessException(MsgConstants.InputMsgCode.ORD_INPUT_311010);
        }
        try {
            this.ordOfflineSV.saveOfflineApply(rOfflineApplyRequest);
        } catch (BusinessException be) {
            LogUtil.error(MODULE, "===业务异常==="+be);
            throw be;
        } catch (Exception e) {
            LogUtil.error(MODULE, "===系统异常==="+e);
            throw new BusinessException(MsgConstants.ServiceMsgCode.ORD_SERVER_320001);
        }
    }

    @Override
    public ROfflineQueryResponse queryOfflineReview(ROfflineQueryRequest rOfflineQueryRequest)
            throws BusinessException {
        if (rOfflineQueryRequest == null) {
            LogUtil.info(MODULE, "入参对象不能为空");
            throw new BusinessException(MsgConstants.InputMsgCode.ORD_INPUT_300000);
        }
        if (StringUtil.isBlank(rOfflineQueryRequest.getOrderId())) {
            LogUtil.info(MODULE, "订单号不能为空");
            throw new BusinessException(MsgConstants.InputMsgCode.ORD_INPUT_311000);
        }
        if (rOfflineQueryRequest.getShopId() == null || rOfflineQueryRequest.getShopId() < 1) {
            LogUtil.info(MODULE, "店铺ID不能为空");
            throw new BusinessException(MsgConstants.InputMsgCode.ORD_INPUT_311002);

        }
        ROfflineQueryResponse roqr = null;
        try {
            roqr = this.ordOfflineSV.queryOfflineReview(rOfflineQueryRequest);
        } catch (BusinessException be) {
            LogUtil.error(MODULE, "===业务异常==="+be);
            throw be;
        } catch (Exception e) {
            LogUtil.error(MODULE, "===系统异常==="+e);
            throw new BusinessException(MsgConstants.ServiceMsgCode.ORD_SERVER_320002);
        }
        return roqr;
    }

    @Override
    public void OfflineReview(ROfflineReviewRequest rOfflineReviewRequest) throws BusinessException {
        
        if (rOfflineReviewRequest == null) {
            LogUtil.info(MODULE, "入参对象不能为空");
            throw new BusinessException(MsgConstants.InputMsgCode.ORD_INPUT_300000);
        }
        if (StringUtil.isBlank(rOfflineReviewRequest.getOrderId())) {
            LogUtil.info(MODULE, "订单号不能为空");
            throw new BusinessException(MsgConstants.InputMsgCode.ORD_INPUT_311000);
        }
        if (rOfflineReviewRequest.getOfflineNo() == null
                || rOfflineReviewRequest.getOfflineNo() < 1) {
            LogUtil.info(MODULE, "线下支付流水不能为空");
            throw new BusinessException(MsgConstants.InputMsgCode.ORD_INPUT_311110);

        }
        if (StringUtil.isBlank(rOfflineReviewRequest.getCheckCont())) {
            LogUtil.info(MODULE, "审核意见不能为空");
            throw new BusinessException(MsgConstants.InputMsgCode.ORD_INPUT_311000);
        }
        LogUtil.info(MODULE, JSON.toJSONString(rOfflineReviewRequest).toString());
//        if (StringUtil.isBlank(rOfflineReviewRequest.getCheckCont())) {
//            LogUtil.info(MODULE, "审核内容不能为空");
//            throw new BusinessException(MsgConstants.InputMsgCode.ORD_INPUT_311111);
//        }
        if (StringUtil.isBlank(rOfflineReviewRequest.getStatus())
                || !(OfflineConstants.Status.STATUS_SHOP_PASS.equals(rOfflineReviewRequest
                        .getStatus()) || OfflineConstants.Status.STATUS_SHOP_NOT
                        .equals(rOfflineReviewRequest.getStatus()))) {
            LogUtil.info(MODULE, "审核状态不能为空或输入的值不对");
            throw new BusinessException(MsgConstants.InputMsgCode.ORD_INPUT_311112);
        }
        try {
            if(OfflineConstants.Status.STATUS_SHOP_PASS.equals(rOfflineReviewRequest.getStatus())) {
                this.ordOfflinePaySV.saveOfflineReviewPass(rOfflineReviewRequest);
            } else if(OfflineConstants.Status.STATUS_SHOP_NOT.equals(rOfflineReviewRequest.getStatus())){
                
                ROrderDetailsRequest  rOrderDetailsRequest = new ROrderDetailsRequest();
                ObjectCopyUtil.copyObjValue(rOfflineReviewRequest, rOrderDetailsRequest, null, false);
                rOrderDetailsRequest.setDelFrom(OrdConstants.DealFrom.FROM_SHOP);
                this.ordMainRSV.removeOrd(rOrderDetailsRequest);
                this.ordOfflineChkSV.saveOfflineReview(rOfflineReviewRequest);
//                this.ordOfflinePaySV.saveOfflineReviewCancle(rOfflineReviewRequest);
            }
            
        } catch (BusinessException be) {
            LogUtil.error(MODULE, "===业务异常==="+be);
            throw be;
        } catch (Exception e) {
            LogUtil.error(MODULE, "===系统异常==="+e);
            throw new BusinessException(MsgConstants.ServiceMsgCode.ORD_SERVER_320003);
        }
    }

    @Override
    public PageResponseDTO<ROfflineQueryResponse> queryOfflineOrder(
            ROfflineQueryRequest rOfflineQueryRequest) throws BusinessException {
        if (rOfflineQueryRequest == null) {
            LogUtil.info(MODULE, "入参对象不能为空");
            throw new BusinessException(MsgConstants.InputMsgCode.ORD_INPUT_300000);
        }
        if (rOfflineQueryRequest.getShopId() == null || rOfflineQueryRequest.getShopId() < 1) {
            LogUtil.info(MODULE, "店铺ID不能为空");
            throw new BusinessException(MsgConstants.InputMsgCode.ORD_INPUT_311002);

        }
        PageResponseDTO<ROfflineQueryResponse> rdors = null;
        try {
            rdors = this.ordOfflineSV.queryOfflineOrder(rOfflineQueryRequest);
        } catch (BusinessException be) {
            LogUtil.error(MODULE, "===业务异常==="+be);
            throw be;
        } catch (Exception e) {
            LogUtil.error(MODULE, "===系统异常==="+e);
            throw new BusinessException(MsgConstants.ServiceMsgCode.ORD_SERVER_320002);
        }
        return rdors;
    }

    @Override
    public PageResponseDTO<ROfflineQueryResponse> queryCheckedOrder(ROfflineQueryRequest rOfflineQueryRequest) throws BusinessException {
        if (rOfflineQueryRequest == null) {
            LogUtil.info(MODULE, "入参对象不能为空");
            throw new BusinessException(MsgConstants.InputMsgCode.ORD_INPUT_300000);
        }
        if (rOfflineQueryRequest.getShopId() == null || rOfflineQueryRequest.getShopId() < 1) {
            LogUtil.info(MODULE, "店铺ID不能为空");
            throw new BusinessException(MsgConstants.InputMsgCode.ORD_INPUT_311002);

        }
        PageResponseDTO<ROfflineQueryResponse> rdors = null;
        try {
            rdors = this.ordOfflineSV.queryCheckedOrder(rOfflineQueryRequest);
        } catch (BusinessException be) {
            LogUtil.error(MODULE, "===业务异常==="+be);
            throw be;
        } catch (Exception e) {
            LogUtil.error(MODULE, "===系统异常==="+e);
            throw new BusinessException(MsgConstants.ServiceMsgCode.ORD_SERVER_320002);
        }
        return rdors;
    }

}
