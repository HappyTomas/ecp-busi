package com.zengshi.ecp.order.facade.impl.eventual;

import com.zengshi.ecp.order.dubbo.dto.RBackConfirmReq;
import com.zengshi.ecp.order.dubbo.util.MsgConstants;
import com.zengshi.ecp.order.facade.interfaces.eventual.IBackPayOrderSV;
import com.zengshi.ecp.order.service.busi.interfaces.IOrdBackApplySV;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.paas.utils.LogUtil;
import com.alibaba.fastjson.JSON;
import com.distribute.tx.common.TransactionCallback;
import com.distribute.tx.common.TransactionContext;
import com.distribute.tx.common.TransactionStatus;
import com.distribute.tx.eventual.TransactionManager;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;

public class BackPayOrderSVImpl implements IBackPayOrderSV {
    
//    @Resource(name="backPayManager")
    @Resource(name="transactionManager1")
    private TransactionManager transactionManager;
    
    @Autowired(required = false)
    private IOrdBackApplySV ordBackApplySV;
    
    private static final String MODULE = BackPayOrderSVImpl.class.getName();

    @Override
    public void dealMethod(final RBackConfirmReq rBackConfirmReq) {
        
        
        final TransactionContext txMsg = new TransactionContext();
        txMsg.setContent( JSON.toJSONString(rBackConfirmReq));
        txMsg.setName("topic-back-pay");
        
        transactionManager.startTransaction(txMsg, new TransactionCallback(){
            
            @Override
            public Object doInTransaction(TransactionStatus status) {
                try {
                    
                    ordBackApplySV.saveBackGdsPayed(rBackConfirmReq);
                    
                } catch (BusinessException be) {
                    LogUtil.error(MODULE, "===业务异常===",be);
                    status.setRollbackOnly();
                    throw be;
                } catch (Exception e) {
                    status.setRollbackOnly();
                    LogUtil.error(MODULE, "===系统异常===",e);
                    throw new BusinessException(MsgConstants.ServiceMsgCode.ORD_SERVER_310041);
                }
                return null;
            }});
    }

}

