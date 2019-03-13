package com.zengshi.ecp.order.service.busi.impl.pay;

import java.util.List;

import javax.annotation.Resource;

import com.zengshi.ecp.order.dao.mapper.busi.PayShopCfgLogMapper;
import com.zengshi.ecp.order.dao.mapper.busi.PayShopCfgMapper;
import com.zengshi.ecp.order.dao.model.PayShopCfg;
import com.zengshi.ecp.order.dao.model.PayShopCfgCriteria;
import com.zengshi.ecp.order.dao.model.PayShopCfgKey;
import com.zengshi.ecp.order.dao.model.PayShopCfgLog;
import com.zengshi.ecp.order.dubbo.dto.pay.PayShopCfgRequest;
import com.zengshi.ecp.order.dubbo.util.OrdConstants.PayStatus;
import com.zengshi.ecp.order.service.busi.interfaces.pay.IPayShopCfgSV;
import com.zengshi.paas.utils.DateUtil;
import com.zengshi.paas.utils.ObjectCopyUtil;
import com.db.sequence.Sequence;

/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-services-order-server <br>
 * Description: 店铺支付通道信息<br>
 * Date:2015年10月8日下午2:59:09  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author weijq
 * @version  
 * @since JDK 1.6
 */
public class PayShopCfgSVImpl implements IPayShopCfgSV {

    @Resource
    private PayShopCfgMapper payShopCfgMapper;
    
    @Resource
    private PayShopCfgLogMapper payShopCfgLogMapper;
    
    @Resource(name = "seq_pay_shop_cfg_log")
    private Sequence seqPayShopCfgLog;
    
    
    @Override
    public List<PayShopCfg> getCfgByPayWay(String payWay) {
        PayShopCfgCriteria payShopCfgCriteria = new PayShopCfgCriteria();
        PayShopCfgCriteria.Criteria criteria = payShopCfgCriteria.createCriteria();
        criteria.andPayWayEqualTo(payWay);
        criteria.andUseFlagEqualTo(PayStatus.PAY_WAY_OPEN);
        payShopCfgCriteria.setOrderByClause(" SHOW_ORDER asc");
        List<PayShopCfg> cfgList = payShopCfgMapper.selectByExample(payShopCfgCriteria);
        return cfgList;
    }
    
    @Override
    public List<PayShopCfg> getCfgByShopId(long shopId) {
        PayShopCfgCriteria payShopCfgCriteria = new PayShopCfgCriteria();
        PayShopCfgCriteria.Criteria criteria = payShopCfgCriteria.createCriteria();
        criteria.andShopIdEqualTo(shopId);
        criteria.andUseFlagEqualTo(PayStatus.PAY_WAY_OPEN);
        List<PayShopCfg> cfgList = payShopCfgMapper.selectByExample(payShopCfgCriteria);
        return cfgList;
    }
    
    @Override
    public PayShopCfg getCfgByShopIdAndPayWay(Long shopId,String payWay) {
        PayShopCfgKey key = new PayShopCfgKey();
        key.setShopId(shopId);
        key.setPayWay(payWay);
        PayShopCfg cfg = payShopCfgMapper.selectByPrimaryKey(key);
        return cfg;
    }
    
    @Override
    public void addCfg(PayShopCfgRequest cfg) {
        PayShopCfg info = new PayShopCfg();
        ObjectCopyUtil.copyObjValue(cfg, info, null, false);
        info.setCreateTime(DateUtil.getSysDate());
        payShopCfgMapper.insert(info);
    }
    
    @Override
    public void editCfg(PayShopCfgRequest cfg) {
        
        Long payShopCfgLogId = seqPayShopCfgLog.nextValue();
        PayShopCfg shopCfgBean = getCfgByShopIdAndPayWay(cfg.getShopId(),cfg.getPayWay());
        
        //记录日志
        PayShopCfgLog logBean = new PayShopCfgLog();
        ObjectCopyUtil.copyObjValue(shopCfgBean, logBean, null, false);
        logBean.setId(payShopCfgLogId);
        logBean.setLogTime(DateUtil.getSysDate());
        payShopCfgLogMapper.insert(logBean);
        
        //更新数据
        PayShopCfgCriteria payShopCfgCriteria = new PayShopCfgCriteria();
        payShopCfgCriteria.createCriteria().andShopIdEqualTo(cfg.getShopId()).andPayWayEqualTo(cfg.getPayWay());
        PayShopCfg bean = new PayShopCfg();
        ObjectCopyUtil.copyObjValue(cfg, bean, null, false);
        cfg.setUpdateTime(DateUtil.getSysDate());
        payShopCfgMapper.updateByExampleSelective(bean, payShopCfgCriteria);
    }
}
