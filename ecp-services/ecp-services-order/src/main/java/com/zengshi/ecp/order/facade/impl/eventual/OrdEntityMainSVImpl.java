package com.zengshi.ecp.order.facade.impl.eventual;

import javax.annotation.Resource;

import net.sf.json.JSONObject;

import com.zengshi.ecp.order.dubbo.dto.REntityChgImportRequest;
import com.zengshi.ecp.order.dubbo.dto.REntityCodeChgRequest;
import com.zengshi.ecp.order.dubbo.dto.RShowImportChgInfoResponse;
import com.zengshi.ecp.order.dubbo.util.MsgConstants;
import com.zengshi.ecp.order.facade.interfaces.eventual.IOrdEntityMainSV;
import com.zengshi.ecp.order.service.busi.interfaces.IOrdDeliveryEntitySV;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.paas.utils.LogUtil;
import com.distribute.tx.common.TransactionCallback;
import com.distribute.tx.common.TransactionContext;
import com.distribute.tx.common.TransactionStatus;
import com.distribute.tx.eventual.TransactionManager;

/**
 * Title: ECP <br>
 * Project Name:ecp-services-order <br>
 * Description: <br>
 * Date:2015年9月1日下午3:48:27  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author cbl
 * @version  
 * @since JDK 1.6 
 */  
public class OrdEntityMainSVImpl implements IOrdEntityMainSV {

    @Resource(name="transactionManager1")
    private TransactionManager transactionManager;
    
    @Resource
    private IOrdDeliveryEntitySV ordDeliveryEntitySV;
    
    private static final String MODULE = OrdEntityMainSVImpl.class.getName();
    
    @Override
    public void updateEntityCodeFromWeb(final REntityCodeChgRequest rEntityCodeChgRequest) {
        TransactionContext txMsg = new TransactionContext();
        txMsg.setContent(JSONObject.fromObject(rEntityCodeChgRequest).toString());
        txMsg.setName("business-topic-ent");
        
        transactionManager.startTransaction(txMsg, new TransactionCallback(){

            @Override
            public Object doInTransaction(TransactionStatus status) {
                RShowImportChgInfoResponse rsi = null;
                try {
                    rsi= ordDeliveryEntitySV.updateEntityCodeChangeFromWeb(rEntityCodeChgRequest);
                } catch (BusinessException be) {
                    LogUtil.error(MODULE, "===业务异常==="+be);
                    status.setRollbackOnly();
                    throw be;
                } catch (Exception e) {
                    LogUtil.error(MODULE, "===系统异常==="+e);
                    status.setRollbackOnly();
                    throw new BusinessException(MsgConstants.ServiceMsgCode.ORD_SERVER_310021);
                }
                return rsi;
            }});
    }

    @Override
    public void updateEntityCodeFromFile(REntityChgImportRequest rEntityChgImportRequest) {
    }

}

