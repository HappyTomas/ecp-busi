package com.zengshi.ecp.order.dubbo.impl;

import javax.annotation.Resource;

import com.zengshi.ecp.order.dubbo.dto.ROrdCountResponse;
import com.zengshi.ecp.order.dubbo.dto.ROrderSummaryResponse;
import com.zengshi.ecp.order.dubbo.dto.RQueryOrderRequest;
import com.zengshi.ecp.order.dubbo.dto.RShopOrderResponse;
import com.zengshi.ecp.order.dubbo.interfaces.IOrdManageRSV;
import com.zengshi.ecp.order.dubbo.util.MsgConstants;
import com.zengshi.ecp.order.service.busi.interfaces.IOrdMainSV;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.paas.utils.LogUtil;

public class OrdManageRSVImpl implements IOrdManageRSV {

    private static final String MODULE = OrdManageRSVImpl.class.getName();

    @Resource
    private IOrdMainSV ordMainSV;

    @Override
    public PageResponseDTO<RShopOrderResponse> queryOrder(RQueryOrderRequest rQueryOrderRequest) throws BusinessException {
        if (rQueryOrderRequest == null) {
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
        PageResponseDTO<RShopOrderResponse> rdor = null;
        try {
            rdor = this.ordMainSV.queryOrderManage(rQueryOrderRequest);
        } catch (BusinessException be) {
            LogUtil.error(MODULE, "===业务异常===", be);
            throw be;
        } catch (Exception e) {
            LogUtil.error(MODULE, "===系统异常===", e);
            throw new BusinessException(MsgConstants.ServiceMsgCode.ORD_SERVER_310004);
        }
        return rdor;
    }

    @Override
    public ROrderSummaryResponse queryOrderSummaryData(RQueryOrderRequest rQueryOrderRequest)
            throws BusinessException {
        if (rQueryOrderRequest == null) {
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
        ROrderSummaryResponse rdor = null;
        try {
            rdor = this.ordMainSV.queryOrderSummaryData(rQueryOrderRequest);
        } catch (BusinessException be) {
            LogUtil.error(MODULE, "===业务异常===", be);
            throw be;
        } catch (Exception e) {
            LogUtil.error(MODULE, "===系统异常===", e);
            throw new BusinessException(MsgConstants.ServiceMsgCode.ORD_SERVER_310004);
        }
        return rdor;
    }

    @Override
    public Long queryOrderCountByShop(RQueryOrderRequest rQueryOrderRequest) throws BusinessException {
        if(rQueryOrderRequest == null){
            LogUtil.info(MODULE, "入参对象不能为空");
            throw new BusinessException(MsgConstants.InputMsgCode.ORD_INPUT_300000);
        }
        if(rQueryOrderRequest.getStaffId() ==null || rQueryOrderRequest.getStaffId() < 1){
            LogUtil.info(MODULE, "买家id不能为空");
            throw new BusinessException(MsgConstants.InputMsgCode.ORD_INPUT_311003);
        }
        Long rcor = null;
        try {
            rcor = this.ordMainSV.queryOrderCountByShop(rQueryOrderRequest);
        } catch (BusinessException be) {
            LogUtil.error(MODULE, "===业务异常===",be);
            throw be;
        } catch (Exception e) {
            LogUtil.error(MODULE, "===系统异常===",e);
            throw new BusinessException(MsgConstants.ServiceMsgCode.ORD_SERVER_310026);
        }
        return rcor;
    }

}

