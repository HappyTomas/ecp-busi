package com.zengshi.ecp.general.prom.util;

import java.util.ArrayList;
import java.util.List;

import com.zengshi.ecp.general.solr.message.DeltaMessage;
import com.zengshi.ecp.general.solr.util.DeltaUtils;
import com.zengshi.ecp.general.solr.util.DeltaUtils.EDeltaType;
import com.zengshi.ecp.general.solr.util.DeltaUtils.EOperType;
import com.zengshi.paas.utils.LogUtil;

/**
 * Title: AI-ECP <br>
 * Description: <br>
 * Date: 2016-03-05 <br>
 * Copyright (c) 2015 AI <br>
 * 
 * @author huangjx
 */
public class PromMsgUtil {
    
    private static String MODULE = PromMsgUtil.class.getName();
    
    /**
     * 促销发送消息
     * @param siteId
     * @param promId
     * @param skuId
     * @param status
     * @param className
     * @return
     * @author huangjx
     */
    public static boolean sendPromIndexMsg(Long siteId, Long promId,Long skuId,String status,String className) {
        
        boolean reValue=true;
        //失效  生效 才发送消息
        if("20".equals(status)|| "10".equals(status)){
            DeltaMessage  msg=new DeltaMessage();
            msg.setDeltaType_(EDeltaType.DBOBJECT);
           
               // 消息
                try {
                    msg.setOperType_(EOperType.UPDATE);
                    //失效 删除
                    if("20".equals(status)){
                        msg.setOperType_(EOperType.DELETE);
                    }
                  /*  if(siteId!=null){
                        msg.setSiteId(String.valueOf(siteId));
                    }*/
                    msg.setObjectNameEn("T_PROM_INFO");
                    if(promId!=null){
                       List<String> ids=new ArrayList<String>();
                       ids.add(String.valueOf(promId));
                       msg.setIds(ids);
                    }
                    
                    if(skuId!=null){
                        List<String> insideIds=new ArrayList<String>();
                        insideIds.add(String.valueOf(skuId));
                        msg.setInsideIds(insideIds);
                    }
                    DeltaUtils.sendDeltaMessage(msg);
                    LogUtil.info(MODULE, "Send promInfo index refull Message,promId is" + promId);
                } catch (Exception e) {
                    reValue=false;
                    LogUtil.error(className, "PromInfo index refull failed! please check you solr server Or check you MQ server!", e);
                }
        }
        return reValue;
    }

}
