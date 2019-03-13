package com.zengshi.ecp.order.dubbo.impl.pay;

import javax.annotation.Resource;

import com.zengshi.ecp.order.dubbo.dto.pay.RAuditTradeCheckRequest;
import com.zengshi.ecp.order.dubbo.dto.pay.RAuditTradeCheckResponse;
import com.zengshi.ecp.order.dubbo.dto.pay.RAuditTradeCheckTotalResponse;
import com.zengshi.ecp.order.dubbo.interfaces.pay.IAuditTradeCheckRSV;
import com.zengshi.ecp.order.dubbo.util.MsgConstants;
import com.zengshi.ecp.order.service.busi.interfaces.pay.audit.IAuditTradeCheckSV;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.StringUtil;

public class AuditTradeCheckRSVImpl implements IAuditTradeCheckRSV{
    private static final String MODULE = AuditTradeCheckRSVImpl.class.getName();
    
    @Resource
    private IAuditTradeCheckSV auditTradeCheckSV;

    @Override
    public PageResponseDTO<RAuditTradeCheckResponse> getTAuditTradeChecks(
            RAuditTradeCheckRequest auditTradeCheckRequest) throws BusinessException {
        if(auditTradeCheckRequest == null){
            LogUtil.info(MODULE, "入参对象不能为空");
            throw new BusinessException(MsgConstants.PayInputMsgCode.PAY_INPUT_300000); 
        }
        if(auditTradeCheckRequest.getStartTime()==null){
            LogUtil.info(MODULE, "入参对象不能为空");
            throw new BusinessException(MsgConstants.PayInputMsgCode.PAY_INPUT_300016); 
        }
        if(auditTradeCheckRequest.getEndTime()==null){
            LogUtil.info(MODULE, "入参对象不能为空");
            throw new BusinessException(MsgConstants.PayInputMsgCode.PAY_INPUT_300017); 
        }
        if(StringUtil.isBlank(auditTradeCheckRequest.getAuditType())){
            LogUtil.info(MODULE, "入参对象不能为空");
            throw new BusinessException(MsgConstants.PayInputMsgCode.PAY_INPUT_300018); 
        }
        LogUtil.info(MODULE, auditTradeCheckRequest.toString());
        try {
            return auditTradeCheckSV.getTAuditTradeChecks(auditTradeCheckRequest);
            
        } catch (BusinessException be) {
            LogUtil.error(MODULE, "===业务异常===",be);
            throw be;
        } catch (Exception e) {
            LogUtil.error(MODULE, "===系统异常===",e);
            throw new BusinessException(MsgConstants.PayServiceMsgCode.PAY_SERVER_310023);
        }
    }
    
    @Override
    public RAuditTradeCheckTotalResponse getTAuditTradeChecksTotal(
            RAuditTradeCheckRequest auditTradeCheckRequest) throws BusinessException {
        if(auditTradeCheckRequest == null){
            LogUtil.info(MODULE, "入参对象不能为空");
            throw new BusinessException(MsgConstants.PayInputMsgCode.PAY_INPUT_300000); 
        }
        if(auditTradeCheckRequest.getStartTime()==null){
            LogUtil.info(MODULE, "入参对象不能为空");
            throw new BusinessException(MsgConstants.PayInputMsgCode.PAY_INPUT_300016); 
        }
        if(auditTradeCheckRequest.getEndTime()==null){
            LogUtil.info(MODULE, "入参对象不能为空");
            throw new BusinessException(MsgConstants.PayInputMsgCode.PAY_INPUT_300017); 
        }
        if(StringUtil.isBlank(auditTradeCheckRequest.getAuditType())){
            LogUtil.info(MODULE, "入参对象不能为空");
            throw new BusinessException(MsgConstants.PayInputMsgCode.PAY_INPUT_300018); 
        }
        LogUtil.info(MODULE, auditTradeCheckRequest.toString());
        try {
            return auditTradeCheckSV.getTAuditTradeChecksTotal(auditTradeCheckRequest);
            
        } catch (BusinessException be) {
            LogUtil.error(MODULE, "===业务异常===",be);
            throw be;
        } catch (Exception e) {
            LogUtil.error(MODULE, "===系统异常===",e);
            throw new BusinessException(MsgConstants.PayServiceMsgCode.PAY_SERVER_310023);
        }
    }

}

