package com.zengshi.ecp.order.service.busi.impl.pay;

import javax.annotation.Resource;

import com.zengshi.ecp.order.dao.mapper.busi.PayIntfNotifyLogMapper;
import com.zengshi.ecp.order.dao.model.PayIntfNotifyLogWithBLOBs;
import com.zengshi.ecp.order.dubbo.dto.pay.PayIntfNotifyLogDTO;
import com.zengshi.ecp.order.service.busi.interfaces.pay.IPayIntfNotifyLogSV;
import com.zengshi.paas.utils.ObjectCopyUtil;
import com.db.sequence.Sequence;

public class PayIntfNotifyLogSVImpl implements IPayIntfNotifyLogSV {

    @Resource
    private PayIntfNotifyLogMapper payIntfNotifyLogMapper;
    
    @Resource(name = "seq_pay_intf_notifylog")
    private Sequence seqPayIntfNotifyLog;

    @Override
    public void addPayNotifyLog(PayIntfNotifyLogDTO log) {
        Long seq = seqPayIntfNotifyLog.nextValue();
        PayIntfNotifyLogWithBLOBs bean= new PayIntfNotifyLogWithBLOBs();
        ObjectCopyUtil.copyObjValue(log, bean, null, false);
        bean.setId(seq);
        payIntfNotifyLogMapper.insert(bean);
    }
}

