package com.zengshi.ecp.order.service.busi.impl.pay;

import javax.annotation.Resource;

import com.zengshi.ecp.order.dao.mapper.busi.PayIntfReqLogMapper;
import com.zengshi.ecp.order.dao.model.PayIntfReqLogWithBLOBs;
import com.zengshi.ecp.order.dubbo.dto.pay.PayIntfReqLogDTO;
import com.zengshi.ecp.order.dubbo.util.OrdConstants.PayStatus;
import com.zengshi.ecp.order.service.busi.interfaces.pay.IPayIntfReqLogSV;
import com.zengshi.paas.utils.ObjectCopyUtil;
import com.db.sequence.Sequence;

/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-services-order-server <br>
 * Description: 支付请求日志<br>
 * Date:2015年10月9日下午5:33:30  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author weijq
 * @version  
 * @since JDK 1.6
 */
public class PayIntfReqLogSVImpl implements IPayIntfReqLogSV {

    @Resource
    private PayIntfReqLogMapper payIntfReqLogMapper;
    
    @Resource(name = "seq_pay_intf_reqlog")
    private Sequence seqPayIntfReqLog;

    @Override
    public void addPayIntfReqLog(PayIntfReqLogDTO info) {
        Long seq = seqPayIntfReqLog.nextValue();
        PayIntfReqLogWithBLOBs bean = new PayIntfReqLogWithBLOBs();
        ObjectCopyUtil.copyObjValue(info, bean, null, false);
        bean.setId(seq);
        payIntfReqLogMapper.insert(bean);
    }
    
    @Override
    public void addPayZYDigitalLog(PayIntfReqLogDTO info) {
        Long seq = seqPayIntfReqLog.nextValue();
        info.setTypeCode(PayStatus.PAY_LOG_TYPE_CODE_06);
        PayIntfReqLogWithBLOBs bean = new PayIntfReqLogWithBLOBs();
        ObjectCopyUtil.copyObjValue(info, bean, null, false);
        bean.setId(seq);
        payIntfReqLogMapper.insert(bean);
    }
    
    @Override
    public void addPayZYExaminationLog(PayIntfReqLogDTO info) {
        Long seq = seqPayIntfReqLog.nextValue();
        info.setTypeCode(PayStatus.PAY_LOG_TYPE_CODE_07);
        PayIntfReqLogWithBLOBs bean = new PayIntfReqLogWithBLOBs();
        ObjectCopyUtil.copyObjValue(info, bean, null, false);
        bean.setId(seq);
        payIntfReqLogMapper.insert(bean);
    }
    
}
