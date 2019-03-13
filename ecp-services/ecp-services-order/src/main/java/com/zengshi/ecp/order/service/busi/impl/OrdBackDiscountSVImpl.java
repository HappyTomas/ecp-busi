package com.zengshi.ecp.order.service.busi.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import com.zengshi.ecp.order.dao.mapper.busi.OrdBackDiscountMapper;
import com.zengshi.ecp.order.dao.model.OrdBackDiscount;
import com.zengshi.ecp.order.dao.model.OrdBackDiscountCriteria;
import com.zengshi.ecp.order.dubbo.dto.RBackDiscountReq;
import com.zengshi.ecp.order.dubbo.dto.RBackDiscountResp;
import com.zengshi.ecp.order.dubbo.dto.ROrderBackReq;
import com.zengshi.ecp.order.service.busi.interfaces.IOrdBackDiscountSV;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.paas.utils.ObjectCopyUtil;
import com.zengshi.paas.utils.StringUtil;
import com.alibaba.dubbo.common.utils.CollectionUtils;
import com.db.sequence.Sequence;

public class OrdBackDiscountSVImpl implements IOrdBackDiscountSV {
    
    @Resource
    private OrdBackDiscountMapper ordBackDiscountMapper;
    
    @Resource(name = "seq_ord_back_discount")
    private Sequence seqOrdBackDiscount;

    private static final String MODULE = OrdBackDiscountSVImpl.class.getName();

    @Override
    public void saveOrdBackDiscount(OrdBackDiscount ordBackDiscount) {
        ordBackDiscount.setId(this.seqOrdBackDiscount.nextValue());
        this.ordBackDiscountMapper.insert(ordBackDiscount);
        
    }

    @Override
    public List<RBackDiscountResp> queryOrdBackDiscount(ROrderBackReq rOrderBackReq) {
        OrdBackDiscountCriteria obdc = new OrdBackDiscountCriteria();
        obdc.createCriteria().andBackIdEqualTo(rOrderBackReq.getBackId()).andOrderIdEqualTo(rOrderBackReq.getOrderId());
        List<OrdBackDiscount> obds = this.ordBackDiscountMapper.selectByExample(obdc);
        if(CollectionUtils.isEmpty(obds)){
            return null;
        }
        List<RBackDiscountResp> rbdrs = new ArrayList<RBackDiscountResp>();
        for(OrdBackDiscount obd :obds){
            RBackDiscountResp rbdr = new RBackDiscountResp();
            ObjectCopyUtil.copyObjValue(obd, rbdr, null, false);
            rbdrs.add(rbdr);
        }
        return rbdrs;
    }

    @Override
    public void deleteOrdBackDiscountByBackId(ROrderBackReq rOrderBackReq) throws BusinessException {
        OrdBackDiscountCriteria obdc = new OrdBackDiscountCriteria();
        obdc.createCriteria().andBackIdEqualTo(rOrderBackReq.getBackId()).andOrderIdEqualTo(rOrderBackReq.getOrderId());
        this.ordBackDiscountMapper.deleteByExample(obdc);
    }

    @Override
    public List<RBackDiscountResp> queryOrdBackDiscountByOrderId(ROrderBackReq rOrderBackReq) {
        OrdBackDiscountCriteria obdc = new OrdBackDiscountCriteria();
        obdc.createCriteria().andOrderIdEqualTo(rOrderBackReq.getOrderId());
        List<OrdBackDiscount> obds = this.ordBackDiscountMapper.selectByExample(obdc);
        if(CollectionUtils.isEmpty(obds)){
            return null;
        }
        List<RBackDiscountResp> rbdrs = new ArrayList<RBackDiscountResp>();
        for(OrdBackDiscount obd :obds){
            RBackDiscountResp rbdr = new RBackDiscountResp();
            ObjectCopyUtil.copyObjValue(obd, rbdr, null, false);
            rbdrs.add(rbdr);
        }
        return rbdrs;
    }

    @Override
    public List<RBackDiscountResp> queryOrdBackDiscount(RBackDiscountReq rBackDiscountReq) {
        OrdBackDiscountCriteria obdc = new OrdBackDiscountCriteria();
        OrdBackDiscountCriteria.Criteria ca =  obdc.createCriteria().andBackIdEqualTo(rBackDiscountReq.getBackId()).andOrderIdEqualTo(rBackDiscountReq.getOrderId());
        if(StringUtil.isNotBlank(rBackDiscountReq.getDiscountType())){
            ca.andDiscountTypeEqualTo(rBackDiscountReq.getDiscountType());
        }
        if(StringUtil.isNotBlank(rBackDiscountReq.getProcType())){
            ca.andProcTypeEqualTo(rBackDiscountReq.getProcType());
        }
        List<OrdBackDiscount> obds = this.ordBackDiscountMapper.selectByExample(obdc);
        if(CollectionUtils.isEmpty(obds)){
            return null;
        }
        List<RBackDiscountResp> rbdrs = new ArrayList<RBackDiscountResp>();
        for(OrdBackDiscount obd :obds){
            RBackDiscountResp rbdr = new RBackDiscountResp();
            ObjectCopyUtil.copyObjValue(obd, rbdr, null, false);
            rbdrs.add(rbdr);
        }
        return rbdrs;
    }
}

