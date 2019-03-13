package com.zengshi.ecp.order.dubbo.impl;

import java.util.List;

import javax.annotation.Resource;

import com.zengshi.ecp.order.dubbo.dto.ROrdEvaluatedRequest;
import com.zengshi.ecp.order.dubbo.dto.ROrdEvaluationRequest;
import com.zengshi.ecp.order.dubbo.dto.ROrdEvaluationResponse;
import com.zengshi.ecp.order.dubbo.interfaces.IOrdEvaluationRSV;
import com.zengshi.ecp.order.dubbo.util.MsgConstants;
import com.zengshi.ecp.order.service.busi.interfaces.IOrdSubSV;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.paas.utils.LogUtil;

public class OrdEvaluationRSVImpl implements IOrdEvaluationRSV {

    
    @Resource
    private IOrdSubSV ordSubSV;
    
    private static final String MODULE = OrdEvaluationRSVImpl.class.getName();
    
    @Override
    public PageResponseDTO<ROrdEvaluationResponse> queryEvaluationWait(
            ROrdEvaluationRequest rOrdEvaluationRequest) {
        if(rOrdEvaluationRequest == null){
            LogUtil.info(MODULE, "入参对象不能为空");
            throw new BusinessException(MsgConstants.InputMsgCode.ORD_INPUT_300000); 
        }
        return this.ordSubSV.queryEvaluationWait(rOrdEvaluationRequest);
    }

    @Override
    public void updateEvaluated(List<ROrdEvaluatedRequest> rOrdEvaluatedRequests) {
        if(rOrdEvaluatedRequests == null){
            LogUtil.info(MODULE, "入参对象不能为空");
            throw new BusinessException(MsgConstants.InputMsgCode.ORD_INPUT_300000); 
        }
        this.ordSubSV.updateEvaluated(rOrdEvaluatedRequests);
    }

    @Override
    public Long queryEvaluationWaitCount(ROrdEvaluationRequest rOrdEvaluationRequest) {
        if(rOrdEvaluationRequest == null){
            LogUtil.info(MODULE, "入参对象不能为空");
            throw new BusinessException(MsgConstants.InputMsgCode.ORD_INPUT_300000); 
        }
        return this.ordSubSV.queryEvaluationWaitCount(rOrdEvaluationRequest);
    }

}

