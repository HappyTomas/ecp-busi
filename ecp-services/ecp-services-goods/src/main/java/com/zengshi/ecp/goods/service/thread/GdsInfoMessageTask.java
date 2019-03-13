/** 
 * Project Name:xhs-services-extend-server 
 * File Name:GdsInfoCacheClearTask.java 
 * Package Name:com.zengshi.ecp.xhsextend.facade.impl.eventual.thread 
 * Date:2017年2月8日下午3:31:27 
 * Copyright (c) 2017, ZengShi All Rights Reserved. 
 * 
 */ 
package com.zengshi.ecp.goods.service.thread;

import java.util.concurrent.TimeUnit;

import com.zengshi.ecp.frame.context.EcpFrameContextHolder;
import com.zengshi.ecp.goods.dubbo.util.GdsUtils;
import com.zengshi.ecp.goods.service.busi.interfaces.gdsinfo.IGdsInfoManageSV;
import com.zengshi.paas.utils.LogUtil;
import com.alibaba.fastjson.JSONObject;

/**
 * Title: ECP <br>
 * Project Name:xhs-services-extend-server <br>
 * Description: 稿件信息消息服务，清缓存，发增量，发newsDone消息<br>
 * Date:2017年2月8日下午3:31:27  <br>
 * Copyright (c) 2017 ZengShi All Rights Reserved <br>
 * 
 * @author liyong7
 * @version  
 * @since JDK 1.7 
 */
public class GdsInfoMessageTask implements Runnable {
    
    private static final String MODULE = GdsInfoMessageTask.class.getName();
    
    private GdsInfoMessageDTO gdsInfoMessageDTO;
    
    
    public GdsInfoMessageTask(GdsInfoMessageDTO gdsInfoMsgDTO) {
        this.gdsInfoMessageDTO = gdsInfoMsgDTO;
    }


    /** 
     * TODO 简单描述该方法的实现功能（可选）. 
     * @see java.lang.Runnable#run() 
     */
    @Override
    public void run() {
        try {
            TimeUnit.SECONDS.sleep(2);
            LogUtil.info(MODULE, "[商品域异步线程]异步发起增量索引请求,请求参数gdsInfoMsgDTO=" + JSONObject.toJSONString(this.gdsInfoMessageDTO));
            IGdsInfoManageSV gdsManageSV = EcpFrameContextHolder.getBean("gdsInfoManageSV", IGdsInfoManageSV.class);
            
            // 清理缓存.
            gdsManageSV.delAllCache(gdsInfoMessageDTO.getGdsId(), gdsInfoMessageDTO.getSkuIds());
            
            // solr增量索引自身.
            if(null != gdsInfoMessageDTO.getGdsId()){
                GdsUtils.sendGdsIndexMsg(gdsInfoMessageDTO.getGdsStatus(), "T_GDS_INFO",
                        MODULE, gdsInfoMessageDTO.getGdsId(),
                        gdsInfoMessageDTO.getCatlogId());
                        //Long.valueOf(GdsConstants.GdsCategory.CATG_TYPE_1));
                LogUtil.info(MODULE, "[商品域异步线程]成功发送产品增量索引请求,请求参数gdsInfoMsgDTO=" + JSONObject.toJSONString(this.gdsInfoMessageDTO));
                
                if(gdsInfoMessageDTO.isSendPromMsg()){
                   ThreadPoolExecutorUtil.commitTask(new PromMessageTask(gdsInfoMessageDTO));
                }
            }
            
            
            
            
            
        } catch (Exception e) {
            LogUtil.error(MODULE, "[商品域异步线程]稿件信息异步消息发送线程执行异常!"+JSONObject.toJSONString(this.gdsInfoMessageDTO),e);
        }
    }

}

