package com.zengshi.ecp.order.service.busi.impl;

import com.zengshi.ecp.order.dao.mapper.busi.OrdBackApplyMapper;
import com.zengshi.ecp.order.dao.model.OrdBackApply;
import com.zengshi.ecp.order.dao.model.OrdBackApplyCriteria;
import com.zengshi.ecp.order.dubbo.dto.*;
import com.zengshi.ecp.order.service.busi.interfaces.IOrdBackApplyEtSV;
import com.zengshi.ecp.order.service.busi.interfaces.IOrdBackGdsSV;
import com.zengshi.ecp.order.service.busi.interfaces.IOrdMainSV;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.paas.utils.ObjectCopyUtil;
import com.alibaba.dubbo.common.utils.CollectionUtils;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author: cbl
 * @date: 2016/9/30.
 */
public class OrdBackApplyEtSVImpl implements IOrdBackApplyEtSV {

    @Resource
    private IOrdBackGdsSV ordBackGdsSV;

    @Resource
    private IOrdMainSV ordMainSV;

    @Resource
    private OrdBackApplyMapper ordBackApplyMapper;


    @Override
    public RBackApplyResp queryOrdBackApply(ROrderBackReq rOrderBackReq) throws BusinessException {
        OrdBackApplyCriteria obac = new OrdBackApplyCriteria();
        obac.createCriteria().andIdEqualTo(rOrderBackReq.getBackId()).andOrderIdEqualTo(rOrderBackReq.getOrderId());
        List<OrdBackApply> obs = this.ordBackApplyMapper.selectByExample(obac);
        if(CollectionUtils.isEmpty(obs)){
            return null;
        }
        RBackApplyResp rBackApplyResp = new RBackApplyResp();
        ObjectCopyUtil.copyObjValue(obs.get(0), rBackApplyResp, null, false);
        rBackApplyResp.setBackId(rOrderBackReq.getBackId());

        ROrdMainResponse rOrdMainResponse = this.ordMainSV.findOrdMianByOrderId(rOrderBackReq.getOrderId());
        if(rOrdMainResponse != null){
            rBackApplyResp.setRealMoney(rOrdMainResponse.getRealMoney());
        }
        List<RBackGdsResp> rBackGdsResps = this.ordBackGdsSV.queryBackGds(rOrderBackReq);
        Long orderScore = 0l;
        for(RBackGdsResp rBackGdsResp:rBackGdsResps){
            Long score =  0l;
            if(rBackGdsResp.getScore() != null){
                score = rBackGdsResp.getScore();
            }
            orderScore += score;
        }
        rBackApplyResp.setOrderScore(orderScore);
        return rBackApplyResp;
    }

    @Override
    public RBackReviewResp queryBackIdInfo(ROrderBackReq rOrderBackReq) throws BusinessException {
        RBackReviewResp rBackReviewResp= new RBackReviewResp();
        RBackApplyResp rBackApplyResp  = this.queryOrdBackApply(rOrderBackReq);
        rBackReviewResp.setrBackApplyResp(rBackApplyResp);
        rBackReviewResp.setrBackGdsResps(this.ordBackGdsSV.queryBackGds(rOrderBackReq));
        return rBackReviewResp;
    }
}
