package com.zengshi.ecp.app.resp;

import com.zengshi.ecp.busi.im.history.vo.MessageHistoryReqVO;
import com.zengshi.butterfly.app.model.IBody;

/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-web-im <br>
 * Description: <br>
 * Date:2017年1月11日下午4:54:31  <br>
 * Copyright (c) 2017 ZengShi All Rights Reserved <br>
 * 
 * @author linby
 * @version  
 * @since JDK 1.7
 */
public class IM005Resp extends IBody {
//	private String id; //消息id
//	private Long shopId;//店铺ID
//	private String to;//JID
//	private String from;//JID
//	private String body;//消息内容
//	private String messageType;//消息类型
//	private String userCode;//买家OF账号
//	private String csaCode;//客服OF账号
//	private Long businessType;//业务类型：商品咨询，订单咨询
//	private Long businessId;//商品id或订单id
	
	private MessageHistoryReqVO messageHistory;

	public MessageHistoryReqVO getMessageHistory() {
		return messageHistory;
	}

	public void setMessageHistory(MessageHistoryReqVO messageHistory) {
		this.messageHistory = messageHistory;
	}
	
	
}
