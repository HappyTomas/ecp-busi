/** 
 * Project Name:xhs-services-extend-server 
 * File Name:GdsInfoCacheClearTask.java 
 * Package Name:com.zengshi.ecp.xhsextend.facade.impl.eventual.thread 
 * Date:2017年2月8日下午3:31:27 
 * Copyright (c) 2017, ZengShi All Rights Reserved. 
 * 
 */ 
package com.zengshi.ecp.goods.service.thread;

import org.apache.commons.collections.CollectionUtils;

import com.zengshi.ecp.frame.context.EcpFrameContextHolder;
import com.zengshi.ecp.general.prom.dto.GdsPromDTO;
import com.zengshi.ecp.general.prom.interfaces.IPromMsg2SolrRSV;
import com.zengshi.ecp.general.prom.util.PromMsgUtil;
import com.zengshi.ecp.goods.dubbo.util.GdsUtils;
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
public class PromMessageTask implements Runnable {
    
    private static final String MODULE = PromMessageTask.class.getName();
    
    private GdsInfoMessageDTO gdsInfoMessageDTO;
    
    
    public PromMessageTask(GdsInfoMessageDTO gdsInfoMsgDTO) {
        this.gdsInfoMessageDTO = gdsInfoMsgDTO;
    }


    /** 
     * TODO 简单描述该方法的实现功能（可选）. 
     * @see java.lang.Runnable#run() 
     */
    @Override
    public void run() {
        try {
            Thread.sleep(3000);
            LogUtil.info(MODULE, "[商品域异步线程]异步发起促销增量请求,请求参数gdsInfoMsgDTO=" + JSONObject.toJSONString(this.gdsInfoMessageDTO));
            IPromMsg2SolrRSV promMsg2SolrRSV = EcpFrameContextHolder.getBean("promMsg2SolrRSV", IPromMsg2SolrRSV.class);
            if (GdsUtils.isOffShelves(gdsInfoMessageDTO.getGdsStatus())) {
                if(CollectionUtils.isNotEmpty(gdsInfoMessageDTO.getSkuIds())){
                    for(Long skuId : gdsInfoMessageDTO.getSkuIds()){
                        PromMsgUtil.sendPromIndexMsg(null, null, skuId, "20",MODULE);
                    }
                }
            } else if (GdsUtils.isOnShelves(gdsInfoMessageDTO.getGdsStatus())) {
                
                if(CollectionUtils.isNotEmpty(gdsInfoMessageDTO.getSkuIds())){
                    for(Long skuId : gdsInfoMessageDTO.getSkuIds()){
                       //新增
                        GdsPromDTO queryProm=new GdsPromDTO();
                        queryProm.setGdsId(gdsInfoMessageDTO.getGdsId());
                        queryProm.setShopId(gdsInfoMessageDTO.getShopId());
                        queryProm.setSkuId(skuId);
                        try{
                            promMsg2SolrRSV.sendMsg2SolrByNewSku(queryProm);
                            PromMsgUtil.sendPromIndexMsg(null, null, skuId, "20",MODULE);
                            LogUtil.debug(MODULE, "[商品域异步线程]成功发送促销索引" + JSONObject.toJSONString(queryProm));
                        }catch(Exception e){
                            LogUtil.debug(MODULE, "[商品域异步线程]发送促销索引失败!" + JSONObject.toJSONString(queryProm));
                        }
                    }
                }
            }
        } catch (Exception e) {
            LogUtil.error(MODULE, "[商品域异步线程]稿件信息异步消息发送线程执行异常!"+JSONObject.toJSONString(this.gdsInfoMessageDTO),e);
        }
    }

}

