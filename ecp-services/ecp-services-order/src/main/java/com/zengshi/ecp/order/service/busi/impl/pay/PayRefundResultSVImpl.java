package com.zengshi.ecp.order.service.busi.impl.pay;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.zengshi.ecp.order.dao.mapper.busi.PayRefundResultMapper;
import com.zengshi.ecp.order.dao.model.PayRefundResult;
import com.zengshi.ecp.order.dao.model.PayRefundResultCriteria;
import com.zengshi.ecp.order.dubbo.util.MsgConstants.PayInputMsgCode;
import com.zengshi.ecp.order.dubbo.util.OrdConstants.PayStatus;
import com.zengshi.ecp.order.service.busi.interfaces.pay.IPayRefundResultSV;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.paas.utils.DateUtil;
import com.zengshi.paas.utils.StringUtil;
import com.db.sequence.Sequence;

public class PayRefundResultSVImpl implements IPayRefundResultSV {

    @Resource
    private PayRefundResultMapper payRefundResultMapper;
    
    @Resource(name = "seq_pay_refund_result")
    private Sequence seqPayRefundResult;
    
    @Override
    public void addPayRefundResult(PayRefundResult payResult) {
        payResult.setId(seqPayRefundResult.nextValue());
        payResult.setCreateTime(DateUtil.getSysDate());
        payRefundResultMapper.insertSelective(payResult);
    }
    
    @Override
    public List<PayRefundResult> getPayRefundResultByOrderId(String orderId) {
        if(StringUtil.isBlank(orderId)){
            throw new BusinessException(PayInputMsgCode.PAY_INPUT_300001);
        }
        PayRefundResultCriteria example = new PayRefundResultCriteria();
        example.createCriteria().andOrderIdEqualTo(orderId);
        return payRefundResultMapper.selectByExample(example);
    }

    @Override
    @Transactional(propagation=Propagation.REQUIRES_NEW)
    public PayRefundResult getPayRefundResultByPaywayBackId(String payway,Long backId) {
        if(StringUtil.isBlank(payway)){
            throw new BusinessException(PayInputMsgCode.PAY_INPUT_300002);
        }
        if(backId<=0){
            throw new BusinessException(PayInputMsgCode.PAY_INPUT_300015);
        }
        PayRefundResultCriteria example = new PayRefundResultCriteria();
        example.createCriteria().andBackIdEqualTo(backId).andPayWayEqualTo(payway);
        List<PayRefundResult> list = payRefundResultMapper.selectByExample(example);
        if(list!=null&&!list.isEmpty()){
            return list.get(0);
        }
        return null;
    }
    
    @Override
    public int updateRefund(PayRefundResult payRefundResult) {
        if (payRefundResult == null) {
            throw new BusinessException(PayInputMsgCode.PAY_INPUT_300000);
        }
        payRefundResult.setUpdateTime(DateUtil.getSysDate());
        PayRefundResultCriteria example = new PayRefundResultCriteria();
        example.createCriteria().andIdEqualTo(payRefundResult.getId()).andRefundStatusEqualTo(PayStatus.PAY_REFUND_RESULT_STATUS_1);
        return payRefundResultMapper.updateByExampleSelective(payRefundResult,example);
    }
}

