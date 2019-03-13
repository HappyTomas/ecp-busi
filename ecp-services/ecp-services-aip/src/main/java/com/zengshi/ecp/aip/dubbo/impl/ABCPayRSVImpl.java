package com.zengshi.ecp.aip.dubbo.impl;

import com.zengshi.ecp.aip.dubbo.constants.AipConstants;
import com.zengshi.ecp.aip.dubbo.dto.*;
import com.zengshi.ecp.aip.dubbo.interfaces.IABCPayRSV;
import com.zengshi.ecp.aip.service.common.pay.interfaces.ABCPaySV;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.StringUtil;

import javax.annotation.Resource;

public class ABCPayRSVImpl extends AipAbstractRSVImpl implements IABCPayRSV {
	
	@Resource
	private ABCPaySV abcPaySV;

    @Override
    public ABCPaySettleResponse querySettle(ABCPaySettleRequest request) throws BusinessException {
        if(request == null){
            LogUtil.info(MODULE, "入参对象不能为空");
            throw new BusinessException(AipConstants.ErrorCode.PAY_INPUT_300001);
        }
        
        if(StringUtil.isBlank(request.getSettleDate())){
            LogUtil.info(MODULE, "对账日期不能为空");
            throw new BusinessException(AipConstants.ErrorCode.PAY_INPUT_300002);
        }
        if(StringUtil.isBlank(request.getZIP())){
            LogUtil.info(MODULE, "对账是否压缩标识不能为空");
            throw new BusinessException(AipConstants.ErrorCode.PAY_INPUT_300003);
        }
        
        if(StringUtil.isBlank(request.getMerchantID())){
            LogUtil.info(MODULE, "商户号不能为空");
            throw new BusinessException(AipConstants.ErrorCode.PAY_INPUT_300012);
        }
        ABCPaySettleResponse response = null;
        try {
            response = this.abcPaySV.querySettle(request);
        } catch (BusinessException be) {
            LogUtil.error(MODULE, "===业务异常===",be);
            throw be;
        } catch (Exception e) {
            LogUtil.error(MODULE, "===系统异常===",e);
            throw new BusinessException(AipConstants.ErrorCode.PAY_SERVER_300001);
        }
        return response;
    }

    @Override
    public ABCPayRefundResponse refund(ABCPayRefundRequest request) throws BusinessException {
        if(request == null){
            LogUtil.info(MODULE, "入参对象不能为空");
            throw new BusinessException(AipConstants.ErrorCode.PAY_INPUT_300001);
        }
        
        if(StringUtil.isBlank(request.getOrderDate())){
            LogUtil.info(MODULE, "订单日期不能为空");
            throw new BusinessException(AipConstants.ErrorCode.PAY_INPUT_300004);
        }
        if(StringUtil.isBlank(request.getOrderTime())){
            LogUtil.info(MODULE, "订单时间不能为空");
            throw new BusinessException(AipConstants.ErrorCode.PAY_INPUT_300005);
        }
        if(StringUtil.isBlank(request.getOrderNo())){
            LogUtil.info(MODULE, "原交易编号不能为空");
            throw new BusinessException(AipConstants.ErrorCode.PAY_INPUT_300006);
        }
        if(StringUtil.isBlank(request.getNewOrderNo())){
            LogUtil.info(MODULE, "交易编号不能为空");
            throw new BusinessException(AipConstants.ErrorCode.PAY_INPUT_300007);
        }
        if(StringUtil.isBlank(request.getCurrencyCode())){
            LogUtil.info(MODULE, "交易币种不能为空");
            throw new BusinessException(AipConstants.ErrorCode.PAY_INPUT_300008);
        }
        if(StringUtil.isBlank(request.getTrxAmount())){
            LogUtil.info(MODULE, "退货金额 不能为空");
            throw new BusinessException(AipConstants.ErrorCode.PAY_INPUT_300009);
        }
        if(StringUtil.isBlank(request.getMerchantID())){
            LogUtil.info(MODULE, "商户号不能为空");
            throw new BusinessException(AipConstants.ErrorCode.PAY_INPUT_300012);
        }
        ABCPayRefundResponse response = null;
        try {
            response = this.abcPaySV.refund(request);
        } catch (BusinessException be) {
            LogUtil.error(MODULE, "===业务异常===",be);
            throw be;
        } catch (Exception e) {
            LogUtil.error(MODULE, "===系统异常===",e);
            throw new BusinessException(AipConstants.ErrorCode.PAY_SERVER_300002);
        }
        return response;
    }

    @Override
    public ABCPayQueryOrderResponse queryOrder(ABCPayQueryOrderRequest request)
            throws BusinessException {
        if(request == null){
            LogUtil.info(MODULE, "入参对象不能为空");
            throw new BusinessException(AipConstants.ErrorCode.PAY_INPUT_300001);
        }
        
        if(StringUtil.isBlank(request.getPayTypeID())){
            LogUtil.info(MODULE, "交易类型不能为空");
            throw new BusinessException(AipConstants.ErrorCode.PAY_INPUT_300010);
        }
        if(StringUtil.isBlank(request.getOrderNo())){
            LogUtil.info(MODULE, "订单编号不能为空");
            throw new BusinessException(AipConstants.ErrorCode.PAY_INPUT_300007);
        }
        if(StringUtil.isBlank(request.getQueryTpye())){
            LogUtil.info(MODULE, "查询方式不能为空");
            throw new BusinessException(AipConstants.ErrorCode.PAY_INPUT_300011);
        }
        if(StringUtil.isBlank(request.getMerchantID())){
            LogUtil.info(MODULE, "商户号不能为空");
            throw new BusinessException(AipConstants.ErrorCode.PAY_INPUT_300012);
        }
        ABCPayQueryOrderResponse response = null;
        try {
            response = this.abcPaySV.queryOrder(request);
        } catch (BusinessException be) {
            LogUtil.error(MODULE, "===业务异常===",be);
            throw be;
        } catch (Exception e) {
            LogUtil.error(MODULE, "===系统异常===",e);
            throw new BusinessException(AipConstants.ErrorCode.PAY_SERVER_300003);
        }
        return response;
    }
}

