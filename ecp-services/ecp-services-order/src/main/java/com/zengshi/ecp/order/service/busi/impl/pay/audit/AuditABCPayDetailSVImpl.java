package com.zengshi.ecp.order.service.busi.impl.pay.audit;

import java.sql.Timestamp;
import java.util.List;

import javax.annotation.Resource;

import com.zengshi.ecp.order.dao.mapper.common.AuditABCPayDetailLogMapper;
import com.zengshi.ecp.order.dao.mapper.common.AuditABCPayDetailMapper;
import com.zengshi.ecp.order.dao.model.AuditABCPayDetail;
import com.zengshi.ecp.order.dao.model.AuditABCPayDetailCriteria;
import com.zengshi.ecp.order.dao.model.AuditABCPayDetailLog;
import com.zengshi.ecp.order.dubbo.util.MsgConstants.PayInputMsgCode;
import com.zengshi.ecp.order.service.busi.interfaces.pay.audit.IAuditABCPayDetailSV;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.paas.utils.DateUtil;
import com.zengshi.paas.utils.ObjectCopyUtil;
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
public class AuditABCPayDetailSVImpl implements IAuditABCPayDetailSV{

    @Resource
    private AuditABCPayDetailMapper auditABCPayDetailMapper;
    
    @Resource
    private AuditABCPayDetailLogMapper auditABCPayDetailLogMapper;
    
    @Resource(name = "seq_audit_abcpay_detail")
    private Sequence seqAuditABCPayDetail;
    
    @Resource(name = "seq_audit_abcpay_detail_log")
    private Sequence seqAuditABCPayDetailLog;

    @Override
    public void saveAuditABCPayDetail(AuditABCPayDetail auditABCPayDetail) {
        if (auditABCPayDetail == null) {
            throw new BusinessException(PayInputMsgCode.PAY_INPUT_300000);
        }
        auditABCPayDetail.setId(seqAuditABCPayDetail.nextValue());
        auditABCPayDetail.setCreateTime(DateUtil.getSysDate());
        auditABCPayDetailMapper.insert(auditABCPayDetail);
    }
    
    @Override
    public List<AuditABCPayDetail> getAuditABCPayDetailByCheckDate(Timestamp qsDate) {
        if (qsDate==null) {
            throw new BusinessException(PayInputMsgCode.PAY_INPUT_300013);
        }
        AuditABCPayDetailCriteria example = new AuditABCPayDetailCriteria();
        example.createCriteria().andCheckDateEqualTo(qsDate);
        List<AuditABCPayDetail> list= auditABCPayDetailMapper.selectByExample(example);
        return list;
    }
    
    @Override
    public void deleteAuditABCPayDetail(long id) {
        AuditABCPayDetail bean = auditABCPayDetailMapper.selectByPrimaryKey(id);
        AuditABCPayDetailLog logBean = new AuditABCPayDetailLog();
        ObjectCopyUtil.copyObjValue(bean, logBean, null, false);
        logBean.setId(seqAuditABCPayDetailLog.nextValue());
        logBean.setOriginalId(bean.getId());
        auditABCPayDetailLogMapper.insert(logBean);
        auditABCPayDetailMapper.deleteByPrimaryKey(id);
    }
}