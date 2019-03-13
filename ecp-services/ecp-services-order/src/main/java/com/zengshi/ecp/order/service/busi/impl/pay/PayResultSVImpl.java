package com.zengshi.ecp.order.service.busi.impl.pay;

import java.util.List;

import javax.annotation.Resource;

import com.zengshi.ecp.order.dao.mapper.busi.PayResultMapper;
import com.zengshi.ecp.order.dao.model.PayResult;
import com.zengshi.ecp.order.dao.model.PayResultCriteria;
import com.zengshi.ecp.order.dao.model.PayResultKey;
import com.zengshi.ecp.order.dubbo.dto.pay.PayResultDTO;
import com.zengshi.ecp.order.dubbo.util.MsgConstants.PayInputMsgCode;
import com.zengshi.ecp.order.service.busi.interfaces.pay.IPayResultSV;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.paas.utils.DateUtil;
import com.zengshi.paas.utils.ObjectCopyUtil;
import com.zengshi.paas.utils.StringUtil;

public class PayResultSVImpl implements IPayResultSV {

    @Resource
    private PayResultMapper payResultMapper;
    
    @Override
    public void addPayResult(PayResultDTO payResult) {
        PayResult bean = new PayResult();
        ObjectCopyUtil.copyObjValue(payResult, bean, null, false);
        bean.setCreateTime(DateUtil.getSysDate());
        payResultMapper.insert(bean);
    }
    
    @Override
    public List<PayResult> getPayResultByOrderId(String orderId) {
        if(StringUtil.isBlank(orderId)){
            throw new BusinessException(PayInputMsgCode.PAY_INPUT_300001);
        }
        PayResultCriteria example = new PayResultCriteria();
        example.createCriteria().andOrderIdEqualTo(orderId);
        return payResultMapper.selectByExample(example);
    }
}

