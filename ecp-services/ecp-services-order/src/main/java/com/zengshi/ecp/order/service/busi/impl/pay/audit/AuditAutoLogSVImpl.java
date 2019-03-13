package com.zengshi.ecp.order.service.busi.impl.pay.audit;

import javax.annotation.Resource;

import com.zengshi.ecp.order.dao.mapper.common.AuditAutoLogMapper;
import com.zengshi.ecp.order.dao.model.AuditAutoLog;
import com.zengshi.ecp.order.dubbo.util.MsgConstants.PayInputMsgCode;
import com.zengshi.ecp.order.service.busi.interfaces.pay.audit.IAuditAutoLogSV;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.paas.utils.DateUtil;
import com.db.sequence.Sequence;

/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-services-order-server <br>
 * Description: <br>
 * Date:2015年10月8日下午2:58:25  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author weijq
 * @version  
 * @since JDK 1.6
 */
public class AuditAutoLogSVImpl implements IAuditAutoLogSV{

    @Resource
    private AuditAutoLogMapper auditAutoLogMapper;

    @Resource(name = "seq_audit_auto_log")
    private Sequence seqAuditAutoLog;
    
    @Override
    public void saveAuditAutoLog(AuditAutoLog auditAutoLog) {
        if (auditAutoLog == null) {
            throw new BusinessException(PayInputMsgCode.PAY_INPUT_300000);
        }
        auditAutoLog.setId(seqAuditAutoLog.nextValue());
        auditAutoLog.setExecuteTime(DateUtil.getSysDate());
        auditAutoLogMapper.insert(auditAutoLog);
    }
}