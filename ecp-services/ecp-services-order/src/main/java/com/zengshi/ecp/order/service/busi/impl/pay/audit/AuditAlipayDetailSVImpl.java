package com.zengshi.ecp.order.service.busi.impl.pay.audit;

import java.sql.Timestamp;
import java.util.List;

import javax.annotation.Resource;

import com.zengshi.ecp.order.dao.mapper.common.AuditAlipayDetailLogMapper;
import com.zengshi.ecp.order.dao.mapper.common.AuditAlipayDetailMapper;
import com.zengshi.ecp.order.dao.model.AuditAlipayDetail;
import com.zengshi.ecp.order.dao.model.AuditAlipayDetailCriteria;
import com.zengshi.ecp.order.dao.model.AuditAlipayDetailLog;
import com.zengshi.ecp.order.dubbo.util.MsgConstants.PayInputMsgCode;
import com.zengshi.ecp.order.service.busi.interfaces.pay.audit.IAuditAlipayDetailSV;
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
public class AuditAlipayDetailSVImpl implements IAuditAlipayDetailSV{

    @Resource
    private AuditAlipayDetailMapper auditAlipayDetailMapper;
    
    @Resource
    private AuditAlipayDetailLogMapper auditAlipayDetailLogMapper;
    
    @Resource(name = "seq_audit_alipay_detail")
    private Sequence seqAuditAlipayDetail;
    
    @Resource(name = "seq_audit_alipay_detail_log")
    private Sequence seqAuditAlipayDetailLog;

    @Override
    public void saveAuditAlipayDetail(AuditAlipayDetail auditAlipayDetail) {
        if (auditAlipayDetail == null) {
            throw new BusinessException(PayInputMsgCode.PAY_INPUT_300000);
        }
        auditAlipayDetail.setId(seqAuditAlipayDetail.nextValue());
        auditAlipayDetail.setCreateTime(DateUtil.getSysDate());
        auditAlipayDetailMapper.insert(auditAlipayDetail);
    }
    
    @Override
    public List<AuditAlipayDetail> getAuditAlipayDetailByCheckDate(Timestamp qsDate) {
        if (qsDate==null) {
            throw new BusinessException(PayInputMsgCode.PAY_INPUT_300013);
        }
        AuditAlipayDetailCriteria example = new AuditAlipayDetailCriteria();
        example.createCriteria().andCheckDateEqualTo(qsDate);
        List<AuditAlipayDetail> list= auditAlipayDetailMapper.selectByExample(example);
        return list;
    }
    
    @Override
    public void deleteAuditAlipayDetail(long id) {
        AuditAlipayDetail bean = auditAlipayDetailMapper.selectByPrimaryKey(id);
        AuditAlipayDetailLog logBean = new AuditAlipayDetailLog();
        ObjectCopyUtil.copyObjValue(bean, logBean, null, false);
        logBean.setId(seqAuditAlipayDetailLog.nextValue());
        logBean.setOriginalId(bean.getId());
        auditAlipayDetailLogMapper.insert(logBean);
        auditAlipayDetailMapper.deleteByPrimaryKey(id);
    }
}