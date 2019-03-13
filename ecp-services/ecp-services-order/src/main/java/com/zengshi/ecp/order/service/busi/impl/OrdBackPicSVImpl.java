package com.zengshi.ecp.order.service.busi.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import com.zengshi.ecp.order.dao.mapper.busi.OrdBackPicMapper;
import com.zengshi.ecp.order.dao.model.OrdBackPic;
import com.zengshi.ecp.order.dao.model.OrdBackPicCriteria;
import com.zengshi.ecp.order.dubbo.dto.RBackPicResp;
import com.zengshi.ecp.order.dubbo.dto.ROrderBackReq;
import com.zengshi.ecp.order.service.busi.interfaces.IOrdBackPicSV;
import com.zengshi.paas.utils.ObjectCopyUtil;
import com.alibaba.dubbo.common.utils.CollectionUtils;
import com.db.sequence.Sequence;

public class OrdBackPicSVImpl implements IOrdBackPicSV {
    
    @Resource
    private OrdBackPicMapper ordBackPicMapper;
    
    @Resource(name = "seq_ord_back_pic")
    private Sequence seqOrdBackPic;

    private static final String MODULE = OrdBackPicSVImpl.class.getName();

    @Override
    public void saveOrdBackPic(OrdBackPic ordBackPic) {
        ordBackPic.setId(this.seqOrdBackPic.nextValue());
        this.ordBackPicMapper.insert(ordBackPic);
    }

    @Override
    public List<RBackPicResp> queryOrdBackPic(ROrderBackReq rOrderBackReq) {
        OrdBackPicCriteria obpc = new OrdBackPicCriteria();
        obpc.createCriteria().andBackIdEqualTo(rOrderBackReq.getBackId()).andOrderIdEqualTo(rOrderBackReq.getOrderId());
        List<OrdBackPic> obps = this.ordBackPicMapper.selectByExample(obpc);
        if(CollectionUtils.isEmpty(obps)){
            return null;
        }
        List<RBackPicResp> rbps = new ArrayList<RBackPicResp>();
        for(OrdBackPic obp :obps){
            RBackPicResp rbp = new RBackPicResp();
            ObjectCopyUtil.copyObjValue(obp, rbp, null, false);
            rbps.add(rbp);
        }
        return rbps;
    }

}

