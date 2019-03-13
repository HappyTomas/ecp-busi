package com.zengshi.ecp.order.service.busi.impl.pay.audit;
import javax.annotation.Resource;

import com.zengshi.ecp.order.dao.mapper.common.AuditFileDealLogMapper;
import com.zengshi.ecp.order.dao.model.AuditFileDealLog;
import com.zengshi.ecp.order.dubbo.util.MsgConstants.PayInputMsgCode;
import com.zengshi.ecp.order.service.busi.interfaces.pay.audit.IAuditFileDealLogSV;
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
public class AuditFileDealLogSVImpl implements IAuditFileDealLogSV{

    @Resource
    private AuditFileDealLogMapper auditFileDealLogMapper;

    @Resource(name = "seq_audit_file_deal_log")
    private Sequence seqAuditFileDealLog;
    
    @Override
    public void saveAuditFileDealLog(AuditFileDealLog auditFileDealLog) {
        if (auditFileDealLog == null) {
            throw new BusinessException(PayInputMsgCode.PAY_INPUT_300000);
        }
        auditFileDealLog.setId(seqAuditFileDealLog.nextValue());
        auditFileDealLog.setCreateTime(DateUtil.getSysDate());
        auditFileDealLogMapper.insert(auditFileDealLog);
    }
}