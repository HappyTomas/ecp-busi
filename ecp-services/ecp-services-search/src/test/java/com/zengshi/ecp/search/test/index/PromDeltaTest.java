package com.zengshi.ecp.search.test.index;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.zengshi.ecp.general.solr.message.DeltaMessage;
import com.zengshi.ecp.general.solr.util.DeltaUtils;
import com.zengshi.ecp.general.solr.util.DeltaUtils.EDeltaType;
import com.zengshi.ecp.general.solr.util.DeltaUtils.EOperType;
import com.zengshi.ecp.search.dubbo.dto.SecConfigReqDTO;
import com.zengshi.ecp.search.index.IndexManager;
import com.zengshi.ecp.search.index.delta.DeltaMessageListener;
import com.zengshi.ecp.server.test.EcpServicesTest;
import com.zengshi.paas.message.Message;
import com.alibaba.fastjson.JSON;

public class PromDeltaTest extends EcpServicesTest {
    
    @Test
    public void clean(){
        SecConfigReqDTO req=new SecConfigReqDTO();
        req.setId(4l);
        IndexManager.cleanIndex(req);    
    }
    
    @Test
    public void deltaGds() {
        
        // 删除
        DeltaMessage deltaMessage=new DeltaMessage();
        deltaMessage.setDeltaType_(EDeltaType.DBOBJECT);
        deltaMessage.setOperType_(EOperType.DELETE);
        deltaMessage.setObjectNameEn("T_PROM_INFO");
        List<String> ids=new ArrayList<String>();
        ids.add("3420");
        deltaMessage.setIds(ids);
        
        Message message=new Message();
        message.setMsg(JSON.toJSONString(deltaMessage));
        message.setTopic(DeltaUtils.TOPIC_DELTAINDEX_NAME);
        
        DeltaUtils.sendDeltaMessage(deltaMessage);
        
//        DeltaMessageListener deltaMessageListener=new DeltaMessageListener();
//        deltaMessageListener.receiveMessage(message, null);

    }
    
    @Test
    public void updateGds() {
        
        // 删除
        DeltaMessage deltaMessage=new DeltaMessage();
        deltaMessage.setDeltaType_(EDeltaType.DBOBJECT);
        deltaMessage.setOperType_(EOperType.UPDATE);
        deltaMessage.setObjectNameEn("T_PROM_INFO");
        List<String> ids=new ArrayList<String>();
        ids.add("4626");
        deltaMessage.setIds(ids);
        
        Message message=new Message();
        message.setMsg(JSON.toJSONString(deltaMessage));
        message.setTopic(DeltaUtils.TOPIC_DELTAINDEX_NAME);
        
//        DeltaUtils.sendDeltaMessage(deltaMessage);
        
        DeltaMessageListener deltaMessageListener=new DeltaMessageListener();
        deltaMessageListener.receiveMessage(message, null);

    }

}
