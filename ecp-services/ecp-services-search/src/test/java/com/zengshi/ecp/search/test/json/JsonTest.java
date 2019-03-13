package com.zengshi.ecp.search.test.json;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.zengshi.ecp.general.solr.message.DeltaMessage;
import com.zengshi.ecp.general.solr.util.DeltaUtils.EDeltaType;
import com.zengshi.ecp.general.solr.util.DeltaUtils.EOperType;
import com.alibaba.fastjson.JSON;

public class JsonTest {
    
    public static void main(String[] args) {
        DeltaMessage deltaMessage=new DeltaMessage();
        deltaMessage.setDeltaType_(EDeltaType.DBOBJECT);
        deltaMessage.setOperType_(EOperType.UPDATE);
        List<String> ids=new ArrayList<String>();
        ids.add("1");
        deltaMessage.setIds(ids);
        deltaMessage.setObjectNameEn("test");
        List<Map<String,Object>> dataList=new ArrayList<Map<String,Object>>();
        Map<String, Object> dataMap=new HashMap<String,Object>();
        dataMap.put("k1", 1);
        dataMap.put("k2", "2");
        dataList.add(dataMap);
        deltaMessage.setDataList(dataList);
        String message=JSON.toJSONString(deltaMessage);
        System.out.println(message);
        
        DeltaMessage deltaMessage2=JSON.parseObject(message, DeltaMessage.class);
        System.out.println(deltaMessage2.getDeltaType());
        System.out.println(deltaMessage2.getIds());
        System.out.println(deltaMessage2.getObjectNameEn());
        System.out.println(deltaMessage2.getOperType());
        System.out.println(deltaMessage2.getDataList());
    }

}

