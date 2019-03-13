package com.zengshi.ecp.order.service.busi.impl.pay;

import java.util.List;

import javax.annotation.Resource;

import com.zengshi.ecp.order.dao.mapper.busi.PayBindLogMapper;
import com.zengshi.ecp.order.dao.mapper.busi.PayBindMapper;
import com.zengshi.ecp.order.dao.model.PayBind;
import com.zengshi.ecp.order.dao.model.PayBindCriteria;
import com.zengshi.ecp.order.dao.model.PayBindLog;
import com.zengshi.ecp.order.dubbo.dto.pay.PayBindDTO;
import com.zengshi.ecp.order.service.busi.interfaces.pay.IPayBindSV;
import com.zengshi.paas.utils.DateUtil;
import com.zengshi.paas.utils.ObjectCopyUtil;
import com.db.sequence.Sequence;

public class PayBindSVImpl implements IPayBindSV {

    @Resource
    private PayBindMapper payBindMapper;
    
    @Resource
    private PayBindLogMapper payBindLogMapper;
    
    @Resource(name = "seq_pay_bind")
    private Sequence seqPayBind;
    
    @Resource(name = "seq_pay_bind_log")
    private Sequence seqPayBindLog;
    
    
    @Override
    public void dealPayBind(PayBindDTO payBind) {
        PayBindCriteria example = new PayBindCriteria();
        example.createCriteria().andPayWayEqualTo(payBind.getPayWay()).andStaffIdEqualTo(payBind.getStaffId())
        .andBindBankAcctEqualTo(payBind.getBindBankAcct());
        List<PayBind> bindList = payBindMapper.selectByExample(example);
        if(bindList.isEmpty()){
            Long bindSeq = seqPayBind.nextValue();
            PayBind bean = new PayBind();
            ObjectCopyUtil.copyObjValue(payBind, bean, null, false);
            bean.setId(bindSeq);
            bean.setCreateTime(DateUtil.getSysDate());
            payBindMapper.insert(bean);
        }else{
            for(PayBind bind:bindList){
                //记录日志表
                Long bindLogSeq = seqPayBindLog.nextValue();
                PayBindLog log = new PayBindLog();
                ObjectCopyUtil.copyObjValue(bind, log, null, false);
                log.setId(bindLogSeq);
                log.setBindId(bind.getId());
                log.setLogTime(DateUtil.getSysDate());
                payBindLogMapper.insert(log);
                
                //更新状态
                PayBind record = new PayBind();
                record.setBindStatus(payBind.getBindStatus());
                record.setBindCustName(payBind.getBindCustName());
                record.setBindCustPhone(payBind.getBindCustPhone());
                record.setUpdateTime(DateUtil.getSysDate());
                payBindMapper.updateByExampleSelective(record, example);
            }
        }
    }
}

