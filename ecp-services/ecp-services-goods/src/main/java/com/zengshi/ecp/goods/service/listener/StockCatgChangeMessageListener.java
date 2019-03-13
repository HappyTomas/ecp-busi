package com.zengshi.ecp.goods.service.listener;

import net.sf.json.JSONObject;

import com.zengshi.ecp.frame.context.EcpFrameContextHolder;
import com.zengshi.ecp.goods.dubbo.dto.stock.StockInfoReqDTO;
import com.zengshi.ecp.goods.dubbo.util.GdsMessageUtil;
import com.zengshi.ecp.goods.service.busi.interfaces.IGdsStockSV;
import com.zengshi.paas.message.Message;
import com.zengshi.paas.message.MessageListener;
import com.zengshi.paas.message.MessageStatus;
import com.zengshi.paas.utils.LogUtil;

public class StockCatgChangeMessageListener implements MessageListener {
    private static final String MODULE = StockCatgChangeMessageListener.class.getName();

    @Override
    public void receiveMessage(Message message, MessageStatus status) {
        try {
            if (GdsMessageUtil.TOPIC_STOCKCATG_CHANGE.equals(message.getTopic())) {

                // 接收到增量消息
                LogUtil.info(MODULE, "接收到增量消息：" + (String) message.getMsg());

                this.asyncChangeStockCatg((String) message.getMsg());
            }
        } catch (Exception e) {
            LogUtil.error(MODULE, "异步更新库存分类数据失败！", e);
        }

    }

    public void asyncChangeStockCatg(String message) throws Exception {

        JSONObject json = JSONObject.fromObject(message);
        StockInfoReqDTO stockInfoReqDTO = (StockInfoReqDTO) JSONObject.toBean(json, StockInfoReqDTO.class);
        IGdsStockSV gdsStockSV = EcpFrameContextHolder.getBean("gdsStockSV",
                IGdsStockSV.class);
        gdsStockSV.syncStockCatgInfoByGdsTurn(stockInfoReqDTO);
        
    }
}
