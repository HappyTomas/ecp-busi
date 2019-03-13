package com.zengshi.ecp.order.service.busi.workplat.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import com.zengshi.ecp.order.dao.mapper.busi.OrdBackApplyMapper;
import com.zengshi.ecp.order.dao.mapper.busi.OrdMainMapper;
import com.zengshi.ecp.order.dao.mapper.busi.OrdOfflineMapper;
import com.zengshi.ecp.order.dao.model.OrdBackApplyCriteria;
import com.zengshi.ecp.order.dao.model.OrdMainCriteria;
import com.zengshi.ecp.order.dao.model.OrdMainCriteria.Criteria;
import com.zengshi.ecp.order.service.busi.workplat.interfaces.IOrderWorkPlatSV;
import com.zengshi.ecp.order.dao.model.OrdOfflineCriteria;

public class OrderWorkPlatSVImpl implements IOrderWorkPlatSV {

    @Resource
    private OrdMainMapper orderMainMapper;
    
    @Resource
    private OrdOfflineMapper orderOfflineMapper;
    
    @Resource
    private OrdBackApplyMapper orderBackAppleMapper;
        
    
    @Override
    public long catchPlatDeliverCount() {
        
        long count = 0;
        
        OrdMainCriteria criteria = new OrdMainCriteria();
        Criteria condition =  criteria.createCriteria();
        
        //查询待发货订单总数条件待发货取订单主表t_ord_main的订单总数，状态(status)为02.03.04的
        List<String> cds = new ArrayList<String>();
        cds.add("02");
        cds.add("03");
        cds.add("04");
        condition.andStatusIn(cds);
        
        try {
            count = orderMainMapper.countByExample(criteria);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            throw e;
        }
        
        
        return count;
    }

    @Override
    public long catchPlatOfflineCheckCount() {
        
        long count = 0;
        
        OrdOfflineCriteria criteria = new OrdOfflineCriteria();
        //待审核线下支付，取线下支付审核表t_ord_offline的订单总数，状态(status)为0并且有效性(is_vilid)为1的
        criteria.createCriteria().andStatusEqualTo("0").andIsValidEqualTo("1");

        try {
            count = orderOfflineMapper.countByExample(criteria);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            throw e;
        }
        
        
        
        return count;
    }

    @Override
    public long catchPlatBackGdsOrderCount() {
        
        long count = 0;
        
        OrdBackApplyCriteria criteria = new OrdBackApplyCriteria();
        List<String> statusList = new ArrayList<String>();
        statusList.add("00");
        statusList.add("06");
        statusList.add("07");
        
      //待退货，取退款/退货申请表t_ord_back_apply表，apply_type=1,并且状态(status)为00的，订单总数
        criteria.createCriteria().andApplyTypeEqualTo("1").andStatusIn(statusList);
        
        try {
            count = orderBackAppleMapper.countByExample(criteria);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            throw e;
        }
        
        
        
        return count;
    }

    @Override
    public long catchPlatBackMoneyOrderCount() {
        
        long count = 0;
        
        OrdBackApplyCriteria criteria = new OrdBackApplyCriteria();
        List<String> statusList = new ArrayList<String>();
        statusList.add("00");
        statusList.add("06");
        statusList.add("07");
        
        //待退款，取退款/退货申请表t_ord_back_apply表，apply_type=0,并且状态(status)为00的，订单总数
        criteria.createCriteria().andApplyTypeEqualTo("0").andStatusIn(statusList);
        
        try {
            count = orderBackAppleMapper.countByExample(criteria);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            throw e;
        }
        
        
        
        return count;
    }

}

