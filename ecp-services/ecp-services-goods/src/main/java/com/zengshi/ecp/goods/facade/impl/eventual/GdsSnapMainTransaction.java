package com.zengshi.ecp.goods.facade.impl.eventual;

import javax.annotation.Resource;

import com.zengshi.ecp.goods.dubbo.dto.gdsinfo.GdsSkuInfoReqDTO;
import com.zengshi.ecp.goods.facade.interfaces.eventual.IGdsSnapMainTransaction;
import com.zengshi.ecp.goods.service.busi.interfaces.gdsinfoidx.IGdsInfoIDXSV;
import com.zengshi.ecp.goods.service.common.impl.AbstractSVImpl;
import com.alibaba.fastjson.JSON;
import com.distribute.tx.common.TransactionCallback;
import com.distribute.tx.common.TransactionContext;
import com.distribute.tx.common.TransactionStatus;
import com.distribute.tx.eventual.TransactionManager;

public class GdsSnapMainTransaction extends AbstractSVImpl implements IGdsSnapMainTransaction {
    @Resource(name = "transactionManagerSnap")
    private TransactionManager transactionManager;

//    @Resource(name = "transactionTemplate2")
//    private TransactionTemplate transactionTemplate;

    /**
     * 商品索引表操作SV
     */
    @Resource
    private IGdsInfoIDXSV gdsInfoIDXSV;

    @Override
    public void executeSkuOnShelves(GdsSkuInfoReqDTO gdsSkuInfoReqDTO) {

        final TransactionContext txMsg = new TransactionContext();
        txMsg.setName("business-topic-goods-snap");

        txMsg.setContent( JSON.toJSONString(gdsSkuInfoReqDTO));
        Object obj = transactionManager.startTransaction(txMsg, new TransactionCallback() {
            @Override
            public Object doInTransaction(TransactionStatus status) {
                try {
                    return null;
                } catch (Exception e) {
                    throw e;
                }
            }
        });

    }

}
