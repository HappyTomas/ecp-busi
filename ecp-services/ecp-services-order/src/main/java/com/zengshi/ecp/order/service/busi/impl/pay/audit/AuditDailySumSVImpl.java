package com.zengshi.ecp.order.service.busi.impl.pay.audit;

import javax.annotation.Resource;

import org.springframework.transaction.annotation.Transactional;

import com.zengshi.ecp.order.dao.mapper.common.AuditDailySumMapper;
import com.zengshi.ecp.order.dao.model.AuditDailySum;
import com.zengshi.ecp.order.dao.model.AuditDailySumKey;
import com.zengshi.ecp.order.dubbo.util.MsgConstants.InputMsgCode;
import com.zengshi.ecp.order.dubbo.util.MsgConstants.PayInputMsgCode;
import com.zengshi.ecp.order.service.busi.interfaces.pay.audit.IAuditDailySumSV;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.paas.utils.DateUtil;
import com.zengshi.paas.utils.StringUtil;

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
public class AuditDailySumSVImpl implements IAuditDailySumSV{

    @Resource
    private AuditDailySumMapper auditDailySumMapper;

    @Override
    public void saveAuditDailySum(AuditDailySum auditDailySum) {
        if (auditDailySum == null) {
            throw new BusinessException(PayInputMsgCode.PAY_INPUT_300000);
        }
        if (StringUtil.isBlank(auditDailySum.getPayWay())) {
            throw new BusinessException(PayInputMsgCode.PAY_INPUT_300002);
        }
        if (auditDailySum.getCheckDate()==null) {
            throw new BusinessException(PayInputMsgCode.PAY_INPUT_300013);
        }
        if (auditDailySum.getShopId()<=0) {
            throw new BusinessException(InputMsgCode.ORD_INPUT_311002);
        }
        auditDailySum.setCreateTime(DateUtil.getSysDate());
        auditDailySumMapper.insert(auditDailySum);
    }
    
    @Override
    public void updateAuditDailySum(AuditDailySum auditDailySum) {
        if (auditDailySum == null) {
            throw new BusinessException(PayInputMsgCode.PAY_INPUT_300000);
        }
        if (StringUtil.isBlank(auditDailySum.getPayWay())) {
            throw new BusinessException(PayInputMsgCode.PAY_INPUT_300002);
        }
        if (auditDailySum.getCheckDate()==null) {
            throw new BusinessException(PayInputMsgCode.PAY_INPUT_300013);
        }
        if (auditDailySum.getShopId()<=0) {
            throw new BusinessException(InputMsgCode.ORD_INPUT_311002);
        }
        auditDailySumMapper.updateByPrimaryKeySelective(auditDailySum);
    }
    
    @Override
    @Transactional(readOnly=true)
    public AuditDailySum getAuditDailySum(AuditDailySum auditDailySum) {
        if (auditDailySum == null) {
            throw new BusinessException(PayInputMsgCode.PAY_INPUT_300000);
        }
        if (StringUtil.isBlank(auditDailySum.getPayWay())) {
            throw new BusinessException(PayInputMsgCode.PAY_INPUT_300002);
        }
        if (auditDailySum.getCheckDate()==null) {
            throw new BusinessException(PayInputMsgCode.PAY_INPUT_300013);
        }
        if (auditDailySum.getShopId()<=0) {
            throw new BusinessException(InputMsgCode.ORD_INPUT_311002);
        }
        AuditDailySumKey key = new AuditDailySumKey();
        key.setPayWay(auditDailySum.getPayWay());
        key.setCheckDate(auditDailySum.getCheckDate());
        key.setShopId(auditDailySum.getShopId());
        AuditDailySum bean = auditDailySumMapper.selectByPrimaryKey(key);
        return bean;
    }
}