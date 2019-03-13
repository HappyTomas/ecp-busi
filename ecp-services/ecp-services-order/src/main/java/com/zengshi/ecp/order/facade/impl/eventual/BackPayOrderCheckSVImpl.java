package com.zengshi.ecp.order.facade.impl.eventual;

import com.zengshi.ecp.order.dubbo.dto.RBackConfirmReq;
import com.zengshi.ecp.order.dubbo.dto.RBackReviewResp;
import com.zengshi.ecp.order.dubbo.dto.ROrderBackReq;
import com.zengshi.ecp.order.dubbo.util.BackConstants;
import com.zengshi.ecp.order.facade.interfaces.eventual.IBackPayOrderCheckSV;
import com.zengshi.ecp.order.service.busi.interfaces.IOrdBackApplySV;
import com.zengshi.paas.utils.LogUtil;
import com.alibaba.fastjson.JSON;
import com.distribute.tx.common.TransactionContext;
import com.distribute.tx.common.TransactionStatus;
import org.springframework.beans.factory.annotation.Autowired;

public class BackPayOrderCheckSVImpl implements IBackPayOrderCheckSV {
    
    @Autowired(required = false)
    private IOrdBackApplySV ordBackApplySV;
    
    private static final String MODULE = BackPayOrderCheckSVImpl.class.getName();

    @Override
    public void checkTransaction(TransactionContext paramTransactionContext, TransactionStatus status) {
        RBackConfirmReq rBackConfirmReq =JSON.parseObject(paramTransactionContext.getContent(), RBackConfirmReq.class);
        LogUtil.info(MODULE,"BackPayOrderCheckSVImpl============="+rBackConfirmReq.toString());
        ROrderBackReq rOrderBackReq  = new ROrderBackReq();
        rOrderBackReq.setOrderId(rBackConfirmReq.getOrderId());
        rOrderBackReq.setBackId(rBackConfirmReq.getBackId());
        RBackReviewResp rBackReviewResp = this.ordBackApplySV.queryBackIdInfo(rOrderBackReq);
        if(BackConstants.Status.REFUNDED.equals(rBackReviewResp.getrBackApplyResp().getStatus().trim())){
            LogUtil.info(MODULE,"验证成功"+JSON.toJSON(rBackReviewResp.toString()));
        } else {
            status.setRollbackOnly();
            LogUtil.info(MODULE,"验证失败"+JSON.toJSON(rBackReviewResp.toString()));
        }
    }

}

