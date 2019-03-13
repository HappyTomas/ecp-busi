package com.zengshi.ecp.order.service.busi.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import com.zengshi.ecp.order.dao.mapper.busi.OrdBackGiftMapper;
import com.zengshi.ecp.order.dao.model.OrdBackGift;
import com.zengshi.ecp.order.dao.model.OrdBackGiftCriteria;
import com.zengshi.ecp.order.dubbo.dto.RBackGiftResp;
import com.zengshi.ecp.order.dubbo.dto.ROrderBackReq;
import com.zengshi.ecp.order.service.busi.interfaces.IOrdBackGiftSV;
import com.zengshi.paas.utils.ObjectCopyUtil;
import com.alibaba.dubbo.common.utils.CollectionUtils;
import com.db.sequence.Sequence;

public class OrdBackGiftSVImpl implements IOrdBackGiftSV {
    
    @Resource
    private OrdBackGiftMapper ordBackGiftMapper;
    
    @Resource(name = "seq_ord_back_gift")
    private Sequence seqOrdBackGift;

    private static final String MODULE = OrdBackGiftSVImpl.class.getName();

    @Override
    public void saveOrdBackGift(OrdBackGift ordBackGift) {
    }

    @Override
    public List<RBackGiftResp> queryOrdBackGift(ROrderBackReq rOrderBackReq) {
        OrdBackGiftCriteria obgc = new OrdBackGiftCriteria();
        obgc.createCriteria().andBackIdEqualTo(rOrderBackReq.getBackId()).andOrderIdEqualTo(rOrderBackReq.getOrderId());
        List<OrdBackGift> obgs = this.ordBackGiftMapper.selectByExample(obgc);
        if(CollectionUtils.isEmpty(obgs)){
            return null;
        }
        List<RBackGiftResp> rbgs = new ArrayList<RBackGiftResp>();
        for(OrdBackGift obg :obgs){
            RBackGiftResp rbg = new RBackGiftResp();
            ObjectCopyUtil.copyObjValue(obg, rbg, null, false);
            rbgs.add(rbg);
        }
        return rbgs;
    }

}

