package com.zengshi.ecp.order.facade.impl.eventual;

import javax.annotation.Resource;

import com.zengshi.ecp.order.dubbo.dto.ROrdReceiptRequest;
import com.zengshi.ecp.order.dubbo.util.MsgConstants;
import com.zengshi.ecp.order.facade.interfaces.eventual.IOrdReceiptMainSV;
import com.zengshi.ecp.order.service.busi.interfaces.IOrdMainSV;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.paas.utils.LogUtil;
import com.alibaba.fastjson.JSON;
import com.distribute.tx.common.TransactionCallback;
import com.distribute.tx.common.TransactionContext;
import com.distribute.tx.common.TransactionStatus;
import com.distribute.tx.eventual.TransactionManager;

public class OrdReceiptMainSVImpl implements IOrdReceiptMainSV {

    
    @Resource(name="transactionManager1")
    private TransactionManager transactionManager;
    
    @Resource
    private IOrdMainSV ordMainSV;
    
    private static final String MODULE = OrdReceiptMainSVImpl.class.getName();
    
    @Override
    public void orderReceipt(final ROrdReceiptRequest rOrdReceiptRequest) {
        
        final TransactionContext txMsg = new TransactionContext();
        txMsg.setContent( JSON.toJSONString(rOrdReceiptRequest));
        txMsg.setName("business-topic-rec");
        
        transactionManager.startTransaction(txMsg, new TransactionCallback(){
            
            @Override
            public Object doInTransaction(TransactionStatus status) {
                try {
                    ordMainSV.updateOrderReceipt(rOrdReceiptRequest);
                } catch (BusinessException be) {
                    LogUtil.error(MODULE, "===业务异常==="+be);
                    status.setRollbackOnly();
                    throw be;
                } catch (Exception e) {
                    LogUtil.error(MODULE, "===系统异常==="+e);
                    status.setRollbackOnly();
                    throw new BusinessException(MsgConstants.ServiceMsgCode.ORD_SERVER_310021);
                }
                return null;
            }});
    }

}

