package com.zengshi.ecp.order.dubbo.impl.pay;

import javax.annotation.Resource;

import com.zengshi.ecp.general.order.dto.PayQuartzInfoRequest;
import com.zengshi.ecp.general.order.dto.PayQuartzInfoResponse;
import com.zengshi.ecp.general.order.interfaces.IPayQuartzInfoRSV;
import com.zengshi.ecp.order.dubbo.dto.pay.RPayQuartzInfoRequest;
import com.zengshi.ecp.order.dubbo.util.MsgConstants.PayInputMsgCode;
import com.zengshi.ecp.order.dubbo.util.OrdConstants.PayStatus;
import com.zengshi.ecp.order.service.busi.interfaces.pay.IPayQuartzInfoSV;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.paas.utils.StringUtil;

public class PayQuartzInfoRSVImpl implements IPayQuartzInfoRSV {

    private static final String MODULE = PayQuartzInfoRSVImpl.class.getName();

    @Resource
    private IPayQuartzInfoSV payQuartzInfoSV;

    @Override
    public PayQuartzInfoResponse updateScoreDealFlagToDoing(PayQuartzInfoRequest request)
            throws BusinessException {
        if(request==null){
            throw new BusinessException(PayInputMsgCode.PAY_INPUT_300000);
        }
        if(request.getPayQuartzInfoId()<=0){
            throw new BusinessException(PayInputMsgCode.PAY_INPUT_300009);
        }
        RPayQuartzInfoRequest payQuartzInfo = new RPayQuartzInfoRequest();
        payQuartzInfo.setId(request.getPayQuartzInfoId());
        payQuartzInfo.setTaskType(PayStatus.PAY_TASK_TYPE_01);
        payQuartzInfo.setUpdateStaff(request.getStaffId());
        int i = payQuartzInfoSV.updateDealFlag(payQuartzInfo,PayStatus.PAY_DEAL_FLAG_0,PayStatus.PAY_DEAL_FLAG_2);
        PayQuartzInfoResponse response = new PayQuartzInfoResponse();
        if(i>=1){
            response.setStatus(true);
        }else{
            response.setStatus(false);
        }
        return response;
    }

    @Override
    public PayQuartzInfoResponse updateScoreDealFlagToDone(PayQuartzInfoRequest request)
            throws BusinessException {
        if(request==null){
            throw new BusinessException(PayInputMsgCode.PAY_INPUT_300000);
        }
        if(request.getPayQuartzInfoId()<=0){
            throw new BusinessException(PayInputMsgCode.PAY_INPUT_300009);
        }
        RPayQuartzInfoRequest payQuartzInfo = new RPayQuartzInfoRequest();
        payQuartzInfo.setId(request.getPayQuartzInfoId());
        payQuartzInfo.setTaskType(PayStatus.PAY_TASK_TYPE_01);
        payQuartzInfo.setUpdateStaff(request.getStaffId());
        int i = payQuartzInfoSV.updateDealFlag(payQuartzInfo,PayStatus.PAY_DEAL_FLAG_2,PayStatus.PAY_DEAL_FLAG_1);
        PayQuartzInfoResponse response = new PayQuartzInfoResponse();
        if(i>=1){
            payQuartzInfoSV.deletePayQuartzInfo(request.getPayQuartzInfoId());
            response.setStatus(true);
        }else{
            response.setStatus(false);
        }
        return response;
    }

    @Override
    public PayQuartzInfoResponse updateCoupDealFlagToDoing(PayQuartzInfoRequest request)
            throws BusinessException {
        if(request==null){
            throw new BusinessException(PayInputMsgCode.PAY_INPUT_300000);
        }
        if(request.getPayQuartzInfoId()<=0){
            throw new BusinessException(PayInputMsgCode.PAY_INPUT_300009);
        }
        RPayQuartzInfoRequest payQuartzInfo = new RPayQuartzInfoRequest();
        payQuartzInfo.setId(request.getPayQuartzInfoId());
        payQuartzInfo.setTaskType(PayStatus.PAY_TASK_TYPE_02);
        payQuartzInfo.setUpdateStaff(request.getStaffId());
        int i = payQuartzInfoSV.updateDealFlag(payQuartzInfo,PayStatus.PAY_DEAL_FLAG_0,PayStatus.PAY_DEAL_FLAG_2);
        PayQuartzInfoResponse response = new PayQuartzInfoResponse();
        if(i>=1){
            response.setStatus(true);
        }else{
            response.setStatus(false);
        }
        return response;
    }

    @Override
    public PayQuartzInfoResponse updateCoupDealFlagToDone(PayQuartzInfoRequest request)
            throws BusinessException {
        if(request==null){
            throw new BusinessException(PayInputMsgCode.PAY_INPUT_300000);
        }
        if(request.getPayQuartzInfoId()<=0){
            throw new BusinessException(PayInputMsgCode.PAY_INPUT_300009);
        }
        RPayQuartzInfoRequest payQuartzInfo = new RPayQuartzInfoRequest();
        payQuartzInfo.setId(request.getPayQuartzInfoId());
        payQuartzInfo.setTaskType(PayStatus.PAY_TASK_TYPE_02);
        payQuartzInfo.setUpdateStaff(request.getStaffId());
        int i = payQuartzInfoSV.updateDealFlag(payQuartzInfo,PayStatus.PAY_DEAL_FLAG_2,PayStatus.PAY_DEAL_FLAG_1);
        PayQuartzInfoResponse response = new PayQuartzInfoResponse();
        if(i>=1){
            response.setStatus(true);
            payQuartzInfoSV.deletePayQuartzInfo(request.getPayQuartzInfoId());
        }else{
            response.setStatus(false);
        }
        return response;
    }
    
    @Override
    public PayQuartzInfoResponse updateDealFlagToDoing(PayQuartzInfoRequest request)
            throws BusinessException {
        if(request==null){
            throw new BusinessException(PayInputMsgCode.PAY_INPUT_300000);
        }
        if(request.getPayQuartzInfoId()<=0){
            throw new BusinessException(PayInputMsgCode.PAY_INPUT_300009);
        }
        if(StringUtil.isBlank(request.getTaskType())){
            throw new BusinessException(PayInputMsgCode.PAY_INPUT_300010);
        }
        RPayQuartzInfoRequest payQuartzInfo = new RPayQuartzInfoRequest();
        payQuartzInfo.setId(request.getPayQuartzInfoId());
        payQuartzInfo.setTaskType(request.getTaskType());
        payQuartzInfo.setUpdateStaff(request.getStaffId());
        int i = payQuartzInfoSV.updateDealFlag(payQuartzInfo,PayStatus.PAY_DEAL_FLAG_2,PayStatus.PAY_DEAL_FLAG_1);
        PayQuartzInfoResponse response = new PayQuartzInfoResponse();
        if(i>=1){
            response.setStatus(true);
            payQuartzInfoSV.deletePayQuartzInfo(request.getPayQuartzInfoId());
        }else{
            response.setStatus(false);
        }
        return response;
    }
    
    @Override
    public PayQuartzInfoResponse updateDealFlagToDone(PayQuartzInfoRequest request)
            throws BusinessException {
        if(request==null){
            throw new BusinessException(PayInputMsgCode.PAY_INPUT_300000);
        }
        if(request.getPayQuartzInfoId()<=0){
            throw new BusinessException(PayInputMsgCode.PAY_INPUT_300009);
        }
        if(StringUtil.isBlank(request.getTaskType())){
            throw new BusinessException(PayInputMsgCode.PAY_INPUT_300010);
        }
        RPayQuartzInfoRequest payQuartzInfo = new RPayQuartzInfoRequest();
        payQuartzInfo.setId(request.getPayQuartzInfoId());
        payQuartzInfo.setTaskType(request.getTaskType());
        payQuartzInfo.setUpdateStaff(request.getStaffId());
        int i = payQuartzInfoSV.updateDealFlag(payQuartzInfo,PayStatus.PAY_DEAL_FLAG_2,PayStatus.PAY_DEAL_FLAG_1);
        PayQuartzInfoResponse response = new PayQuartzInfoResponse();
        if(i>=1){
            response.setStatus(true);
            payQuartzInfoSV.deletePayQuartzInfo(request.getPayQuartzInfoId());
        }else{
            response.setStatus(false);
        }
        return response;
    }
}
