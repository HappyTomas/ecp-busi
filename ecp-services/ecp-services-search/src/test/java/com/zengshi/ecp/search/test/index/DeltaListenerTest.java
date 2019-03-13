package com.zengshi.ecp.search.test.index;

import java.util.ArrayList;
import java.util.List;

import com.zengshi.ecp.search.dubbo.interfaces.IDeltaTransactionMessageRSV;
import org.junit.Test;

import com.zengshi.ecp.general.solr.message.DeltaMessage;
import com.zengshi.ecp.general.solr.message.GdsDeltaMessage;
import com.zengshi.ecp.general.solr.util.DeltaUtils;
import com.zengshi.ecp.general.solr.util.DeltaUtils.EDeltaType;
import com.zengshi.ecp.general.solr.util.DeltaUtils.EOperType;
import com.zengshi.ecp.search.index.delta.DeltaIndexManager;
import com.zengshi.ecp.search.index.delta.DeltaMessageListener;
import com.zengshi.ecp.server.test.EcpServicesTest;
import com.zengshi.paas.message.Message;
import com.alibaba.fastjson.JSON;

import javax.annotation.Resource;

public class DeltaListenerTest extends EcpServicesTest {

    @Resource
    private IDeltaTransactionMessageRSV deltaTransactionMessageRSV;
    
    @Test
    public void rejectPolicyTest() {
        
        for(int i=1;i<=500;i++){
            DeltaIndexManager.submit(i+"");
        }
        
    }
    
    @Test
    public void deltaCate() {
        
        DeltaMessageListener deltaMessageListener=new DeltaMessageListener();

        GdsDeltaMessage deltaMessage=new GdsDeltaMessage();
        deltaMessage.setDeltaType_(EDeltaType.DBOBJECT);
        deltaMessage.setOperType_(EOperType.CATG);
        deltaMessage.setObjectNameEn("T_GDS_INFO");
        deltaMessage.setCatgCodes("1668");
        
        Message message=new Message();
        message.setMsg(JSON.toJSONString(deltaMessage));
        message.setTopic(DeltaUtils.TOPIC_DELTAINDEX_NAME);
        deltaMessageListener.receiveMessage(message, null);

    }
    
    @Test
    public void deltaSite() {
        
        DeltaMessageListener deltaMessageListener=new DeltaMessageListener();

        GdsDeltaMessage deltaMessage=new GdsDeltaMessage();
        deltaMessage.setDeltaType_(EDeltaType.DBOBJECT);
        deltaMessage.setOperType_(EOperType.REFULL);
        deltaMessage.setObjectNameEn("T_GDS_INFO");
        deltaMessage.setSiteId("1");
        
        Message message=new Message();
        message.setMsg(JSON.toJSONString(deltaMessage));
        message.setTopic(DeltaUtils.TOPIC_DELTAINDEX_NAME);
        deltaMessageListener.receiveMessage(message, null);

    }

    @Test
    public void deltaGds() {

        //DeltaMessageListener deltaMessageListener=new DeltaMessageListener();

        // 删除
        DeltaMessage deltaMessage=new DeltaMessage();
        deltaMessage.setDeltaType_(EDeltaType.DBOBJECT);
        deltaMessage.setOperType_(EOperType.DELETE);
        deltaMessage.setObjectNameEn("T_GDS_INFO");
        List<String> ids=new ArrayList<String>();
        ids.add("201964");
        ids.add("201984");
        ids.add("201992");
        deltaMessage.setIds(ids);

        this.deltaTransactionMessageRSV.sendDeltaMessage(deltaMessage);

        /*Message message=new Message();
        message.setMsg(JSON.toJSONString(deltaMessage));
        message.setTopic(DeltaUtils.TOPIC_DELTAINDEX_NAME);
        deltaMessageListener.receiveMessage(message, null);*/

        //异步增量，避免主线程结束框架已开始回收动态注册Bean资源
        while(true){
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
    
    @Test
    public void updateGds() {
        
        DeltaMessageListener deltaMessageListener=new DeltaMessageListener();

        // 更新
        DeltaMessage deltaMessage=new DeltaMessage();
        deltaMessage.setDeltaType_(EDeltaType.DBOBJECT);
        deltaMessage.setOperType_(EOperType.UPDATE);
        deltaMessage.setObjectNameEn("T_GDS_INFO");
        List<String> ids=new ArrayList<String>();
        ids.add("46667");
        deltaMessage.setIds(ids);
        
        Message message=new Message();
        message.setMsg(JSON.toJSONString(deltaMessage));
        message.setTopic(DeltaUtils.TOPIC_DELTAINDEX_NAME);
        deltaMessageListener.receiveMessage(message, null);

        //异步增量，避免主线程结束框架已开始回收动态注册Bean资源
        while(true){
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

}
