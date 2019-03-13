package com.zengshi.ecp.order.facade.impl.eventual;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import net.sf.json.JSONObject;

import com.zengshi.ecp.order.dubbo.dto.REntityInputRequest;
import com.zengshi.ecp.order.facade.interfaces.eventual.IOrdEntitySubSV;
import com.zengshi.ecp.order.service.busi.interfaces.IOrdEntityImportSV;
import com.distribute.tx.common.TransactionStatus;
import com.distribute.tx.eventual.TransactionManager;

public class OrdEntitySubSVImpl implements IOrdEntitySubSV {

    @Resource(name = "transactionManager1")
    private TransactionManager transactionManager;
    
    @Resource
    private IOrdEntityImportSV ordEntityImportSV;

    @Override
    public void joinTransaction(JSONObject message, TransactionStatus status, String transactionName) {
        System.out.println("子事务执行+++++++++++++++++++++++++++++++");
        REntityInputRequest reir = new REntityInputRequest();
        reir.setOrderId("4");
        reir.setOrderSubId("8");
        reir.setShopId(100l);
        reir.setStaffId(88l);
        reir.setSendNum(1l);
        
        List<String> entitys = new ArrayList<String>();
        entitys.add("310000");
        entitys.add("310001");
        reir.setEntitys(entitys);
        ordEntityImportSV.saveEntityInput(reir);
    }


}
