package com.zengshi.ecp.order.service.busi.impl.pay.audit;

import java.sql.Timestamp;
import java.util.List;

import javax.annotation.Resource;

import com.zengshi.ecp.order.dao.mapper.common.AuditHongpayDetailLogMapper;
import com.zengshi.ecp.order.dao.mapper.common.AuditHongpayDetailMapper;
import com.zengshi.ecp.order.dao.model.AuditHongpayDetail;
import com.zengshi.ecp.order.dao.model.AuditHongpayDetailCriteria;
import com.zengshi.ecp.order.dao.model.AuditHongpayDetailLog;
import com.zengshi.ecp.order.dubbo.util.MsgConstants.PayInputMsgCode;
import com.zengshi.ecp.order.service.busi.interfaces.pay.audit.IAuditHongpayDetailSV;
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
public class AuditHongpayDetailSVImpl implements IAuditHongpayDetailSV{

    @Resource
    private AuditHongpayDetailMapper auditHongpayDetailMapper;
    
    @Resource
    private AuditHongpayDetailLogMapper auditHongpayDetailLogMapper;
    
    @Resource(name = "seq_audit_hongpay_detail")
    private Sequence seqAuditHongpayDetail;
    
    @Resource(name = "seq_audit_hongpay_detail_log")
    private Sequence seqAuditHongpayDetailLog;

    @Override
    public void saveAuditHongpayDetail(AuditHongpayDetail auditHongpayDetail) {
        if (auditHongpayDetail == null) {
            throw new BusinessException(PayInputMsgCode.PAY_INPUT_300000);
        }
        auditHongpayDetail.setId(seqAuditHongpayDetail.nextValue());
        auditHongpayDetail.setCreateTime(DateUtil.getSysDate());
        auditHongpayDetailMapper.insert(auditHongpayDetail);
    }
    
    @Override
    public List<AuditHongpayDetail> getAuditHongpayDetailByCheckDate(Timestamp qsDate) {
        if (qsDate==null) {
            throw new BusinessException(PayInputMsgCode.PAY_INPUT_300013);
        }
        AuditHongpayDetailCriteria example = new AuditHongpayDetailCriteria();
        example.createCriteria().andCheckDateEqualTo(qsDate);
        List<AuditHongpayDetail> list= auditHongpayDetailMapper.selectByExample(example);
        return list;
    }
    
    @Override
    public void deleteAuditHongpayDetail(long id) {
        AuditHongpayDetail bean = auditHongpayDetailMapper.selectByPrimaryKey(id);
        AuditHongpayDetailLog logBean = new AuditHongpayDetailLog();
        ObjectCopyUtil.copyObjValue(bean, logBean, null, false);
        logBean.setId(seqAuditHongpayDetailLog.nextValue());
        logBean.setOriginalId(bean.getId());
        auditHongpayDetailLogMapper.insert(logBean);
        auditHongpayDetailMapper.deleteByPrimaryKey(id);
    }
}