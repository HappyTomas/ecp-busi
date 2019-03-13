package com.zengshi.ecp.order.service.busi.impl.pay;

import javax.annotation.Resource;

import com.zengshi.ecp.order.dao.mapper.busi.PayRepeatMapper;
import com.zengshi.ecp.order.dao.model.PayRepeat;
import com.zengshi.ecp.order.dao.model.PayRepeatKey;
import com.zengshi.ecp.order.dubbo.dto.pay.PayRepeatDTO;
import com.zengshi.ecp.order.dubbo.util.MsgConstants.PayInputMsgCode;
import com.zengshi.ecp.order.service.busi.interfaces.pay.IPayRepeatSV;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.paas.utils.DateUtil;
import com.zengshi.paas.utils.ObjectCopyUtil;
import com.zengshi.paas.utils.StringUtil;

public class PayRepeatSVImpl implements IPayRepeatSV {

    @Resource
    private PayRepeatMapper payRepeatMapper;

    @Override
    public void addPayRepeat(PayRepeatDTO payRepeat) {
        PayRepeat bean = new PayRepeat();
        ObjectCopyUtil.copyObjValue(payRepeat, bean, null, false);
        bean.setCreateTime(DateUtil.getSysDate());
        payRepeatMapper.insert(bean);
    }
    
    @Override
    public PayRepeat getPayRepeatByPayWayTransNo(String payWay,
            String transNo) {
        if(StringUtil.isBlank(payWay)){
            throw new BusinessException(PayInputMsgCode.PAY_INPUT_300002);
        }
        if(StringUtil.isBlank(transNo)){
            throw new BusinessException(PayInputMsgCode.PAY_INPUT_300003);
        }
        PayRepeatKey key = new PayRepeatKey();
        key.setPayWay(payWay);
        key.setPayTransNo(transNo);
        return payRepeatMapper.selectByPrimaryKey(key);
    }
}

