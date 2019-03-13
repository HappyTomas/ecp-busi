package com.zengshi.ecp.order.service.busi.impl.pay;

import javax.annotation.Resource;

import com.zengshi.ecp.order.dao.mapper.busi.PayBindReqMapper;
import com.zengshi.ecp.order.dao.model.PayBindReq;
import com.zengshi.ecp.order.dubbo.dto.pay.PayBindReqDTO;
import com.zengshi.ecp.order.service.busi.interfaces.pay.IPayBindReqSV;
import com.zengshi.paas.utils.DateUtil;
import com.zengshi.paas.utils.ObjectCopyUtil;
import com.db.sequence.Sequence;

public class PayBindReqSVImpl implements IPayBindReqSV {

    @Resource
    private PayBindReqMapper payBindReqMapper;
    
    @Resource(name = "seq_pay_bindreq")
    private Sequence seqPayBindReq;
    
    @Override
    public void addPayBindRequest(PayBindReqDTO request) {
        Long seq = seqPayBindReq.nextValue();
        PayBindReq bean = new PayBindReq();
        ObjectCopyUtil.copyObjValue(request, bean, null, false);
        bean.setId(seq);
        bean.setCreateTime(DateUtil.getSysDate());
        bean.setRequestTime(DateUtil.getSysDate());
        payBindReqMapper.insert(bean);
    }
}

