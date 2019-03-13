package com.zengshi.ecp.goods.service.listener;

import net.sf.json.JSONObject;

import com.zengshi.ecp.frame.context.EcpFrameContextHolder;
import com.zengshi.ecp.goods.dubbo.dto.GdsBrowseHisReqDTO;
import com.zengshi.ecp.goods.dubbo.util.GdsMessageUtil;
import com.zengshi.ecp.goods.service.busi.interfaces.IGdsBrowseHisSV;
import com.zengshi.paas.message.Message;
import com.zengshi.paas.message.MessageListener;
import com.zengshi.paas.message.MessageStatus;
import com.zengshi.paas.utils.LogUtil;

public class GdsBrowseMessageListener implements MessageListener {
    private static final String MODULE = GdsBrowseMessageListener.class.getName();

    @Override
    public void receiveMessage(Message message, MessageStatus status) {
        try {
            if (GdsMessageUtil.TOPIC_GDSBROWSE.equals(message.getTopic())) {

                // 接收到增量消息
                LogUtil.info(MODULE, "接收到增量消息：" + (String) message.getMsg());

                this.asyncSaveGdsBrowseHis((String) message.getMsg());
            }
        } catch (Exception e) {
            LogUtil.error(MODULE, "异步增加商品浏览记录失败！", e);
        }

    }

    public void asyncSaveGdsBrowseHis(String message) throws Exception {

        JSONObject json = JSONObject.fromObject(message);
        GdsBrowseHisReqDTO browseHisReqDTO = (GdsBrowseHisReqDTO) JSONObject.toBean(json, GdsBrowseHisReqDTO.class);
        IGdsBrowseHisSV browseHisSV = EcpFrameContextHolder.getBean("gdsBrowseHisSV",
                IGdsBrowseHisSV.class);
        browseHisSV.addGdsBrowseHis(browseHisReqDTO);
        
    }
}
