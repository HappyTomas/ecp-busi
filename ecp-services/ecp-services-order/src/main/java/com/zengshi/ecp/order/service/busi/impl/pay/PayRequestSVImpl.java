package com.zengshi.ecp.order.service.busi.impl.pay;

import javax.annotation.Resource;

import com.zengshi.ecp.order.dao.mapper.busi.PayRequestMapper;
import com.zengshi.ecp.order.dao.model.PayRequest;
import com.zengshi.ecp.order.dubbo.dto.pay.PayRequestDTO;
import com.zengshi.ecp.order.service.busi.interfaces.pay.IPayRequestSV;
import com.zengshi.paas.utils.DateUtil;
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
public class PayRequestSVImpl implements IPayRequestSV {

    @Resource
    private PayRequestMapper payRequestMapper;
    
    @Resource(name = "seq_pay_request")
    private Sequence seqPayRequest;
    
    @Override
    public void addPayRequest(PayRequestDTO payRequest) {
        
        Long seq = seqPayRequest.nextValue();
        PayRequest logBean = new PayRequest();
        ObjectCopyUtil.copyObjValue(payRequest, logBean, null, false);
        logBean.setId(seq);
        logBean.setCreateTime(DateUtil.getSysDate());
        logBean.setRequestTime(DateUtil.getSysDate());
        payRequestMapper.insert(logBean);
    }
}
