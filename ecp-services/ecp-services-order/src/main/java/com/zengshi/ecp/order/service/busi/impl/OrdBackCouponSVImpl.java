package com.zengshi.ecp.order.service.busi.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import com.zengshi.ecp.order.dao.mapper.busi.OrdBackCouponMapper;
import com.zengshi.ecp.order.dao.model.OrdBackCoupon;
import com.zengshi.ecp.order.dao.model.OrdBackCouponCriteria;
import com.zengshi.ecp.order.dubbo.dto.RBackCouponResp;
import com.zengshi.ecp.order.dubbo.dto.ROrderBackReq;
import com.zengshi.ecp.order.service.busi.interfaces.IOrdBackCouponSV;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.paas.utils.ObjectCopyUtil;
import com.alibaba.dubbo.common.utils.CollectionUtils;
import com.db.sequence.Sequence;

public class OrdBackCouponSVImpl implements IOrdBackCouponSV {
    
    @Resource
    private OrdBackCouponMapper ordBackCouponMapper;
    
    @Resource(name = "seq_ord_back_coupon")
    private Sequence seqOrdBackCoupon;

    private static final String MODULE = OrdBackCouponSVImpl.class.getName();

    @Override
    public void saveOrdBackCoupon(OrdBackCoupon ordBackCoupon) {
        ordBackCoupon.setId(this.seqOrdBackCoupon.nextValue());
        this.ordBackCouponMapper.insert(ordBackCoupon);
    }

    @Override
    public List<RBackCouponResp> queryOrdBackCoupon(ROrderBackReq rOrderBackReq) {
        OrdBackCouponCriteria obcc = new OrdBackCouponCriteria();
        obcc.createCriteria().andBackIdEqualTo(rOrderBackReq.getBackId()).andOrderIdEqualTo(rOrderBackReq.getOrderId());
        List<OrdBackCoupon> obcs = this.ordBackCouponMapper.selectByExample(obcc);
        if(CollectionUtils.isEmpty(obcs)){
            return null;
        }
        List<RBackCouponResp> rbcrs = new ArrayList<RBackCouponResp>();
        for(OrdBackCoupon obc:obcs){
            RBackCouponResp rbcr = new RBackCouponResp();
            ObjectCopyUtil.copyObjValue(obc, rbcr, null, false);
            rbcrs.add(rbcr);
        }
        return rbcrs;
    }

    @Override
    public void deleteOrdBackCouponByBackId(ROrderBackReq rOrderBackReq) throws BusinessException {
        OrdBackCouponCriteria obcc = new OrdBackCouponCriteria();
        obcc.createCriteria().andBackIdEqualTo(rOrderBackReq.getBackId()).andOrderIdEqualTo(rOrderBackReq.getOrderId());
        this.ordBackCouponMapper.deleteByExample(obcc);
    }
}

