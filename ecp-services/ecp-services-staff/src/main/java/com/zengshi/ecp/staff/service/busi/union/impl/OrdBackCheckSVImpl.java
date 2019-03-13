package com.zengshi.ecp.staff.service.busi.union.impl;

import javax.annotation.Resource;

import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.staff.dao.mapper.busi.OrderBackCheckMapper;
import com.zengshi.ecp.staff.dao.model.OrderBackCheck;
import com.zengshi.ecp.staff.dubbo.dto.OrderBackCheckReqDTO;
import com.zengshi.ecp.staff.service.busi.union.interfaces.IOrdBackCheckSV;
import com.zengshi.paas.utils.DateUtil;
import com.zengshi.paas.utils.ObjectCopyUtil;
import com.db.sequence.Sequence;

public class OrdBackCheckSVImpl implements IOrdBackCheckSV{

    
    @Resource(name = "seq_staff_ord_back_check_id")
    private Sequence seq_staff_ord_back_check_id;
    
    @Resource
    private OrderBackCheckMapper ordBackCheckMapper;
    
    @Override
    public boolean checkOrdBack(long backId) throws BusinessException {
        OrderBackCheck check = ordBackCheckMapper.selectByPrimaryKey(backId);
        return check == null ? false : true;
    }

    @Override
    public long saveOraBackCheck(OrderBackCheckReqDTO req) throws BusinessException {
        OrderBackCheck check = new OrderBackCheck();
        ObjectCopyUtil.copyObjValue(req, check, null, false);
        check.setCreateStaff(req.getStaff().getId());
        check.setCreateTime(DateUtil.getSysDate());
        check.setId(seq_staff_ord_back_check_id.nextValue());
        ordBackCheckMapper.insertSelective(check);
        return check.getId();
    }

}

