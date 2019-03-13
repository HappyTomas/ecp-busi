package com.zengshi.ecp.order.facade.impl.eventual;

import javax.annotation.Resource;

import com.zengshi.ecp.order.dubbo.dto.ROrderDetailsRequest;
import com.zengshi.ecp.order.dubbo.util.MsgConstants;
import com.zengshi.ecp.order.facade.interfaces.eventual.IOrdRemoveOrderSV;
import com.zengshi.ecp.order.service.busi.interfaces.IOrdMainSV;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.paas.utils.LogUtil;
import com.alibaba.fastjson.JSON;
import com.distribute.tx.common.TransactionCallback;
import com.distribute.tx.common.TransactionContext;
import com.distribute.tx.common.TransactionStatus;
import com.distribute.tx.eventual.TransactionManager;

public class OrdRemoveOrderSVImpl implements IOrdRemoveOrderSV {
    
    @Resource(name="transactionManager1")
    private TransactionManager transactionManager;
    
    @Resource
    private IOrdMainSV ordMainSV;
    
    private static final String MODULE = OrdRemoveOrderSVImpl.class.getName();

    @Override
    public void dealMethod(final ROrderDetailsRequest rOrderDetailsRequest) {
        
        LogUtil.info(MODULE,"主事务订单域入参："+JSON.toJSONString(rOrderDetailsRequest));
        
        final TransactionContext txMsg = new TransactionContext();
        txMsg.setContent( JSON.toJSONString(rOrderDetailsRequest));
        txMsg.setName("topic-order-remove");
        
        transactionManager.startTransaction(txMsg, new TransactionCallback(){
            
            @Override
            public Object doInTransaction(TransactionStatus status) {
                try {
                    
                    ordMainSV.removeOrd(rOrderDetailsRequest);
                    
                } catch (BusinessException be) {
                    LogUtil.error(MODULE, "===业务异常===",be);
                    status.setRollbackOnly();
                    throw be;
                } catch (Exception e) {
                    status.setRollbackOnly();
                    LogUtil.error(MODULE, "===系统异常===",e);
                    throw new BusinessException(MsgConstants.ServiceMsgCode.ORD_SERVER_350012);
                }
                return null;
            }});
        
    }

}

