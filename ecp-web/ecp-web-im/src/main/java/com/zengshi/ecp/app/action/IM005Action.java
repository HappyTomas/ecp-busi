package com.zengshi.ecp.app.action;

import java.util.UUID;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.zengshi.ecp.app.req.IM005Req;
import com.zengshi.ecp.app.resp.IM005Resp;
import com.zengshi.ecp.busi.im.history.vo.MessageHistoryReqVO;
import com.zengshi.ecp.busi.im.util.BizUtil;
import com.zengshi.ecp.im.dubbo.util.ImConstants;
import com.zengshi.paas.utils.DateUtil;
import com.zengshi.paas.utils.MongoUtil;
import com.zengshi.paas.utils.StringUtil;
import com.zengshi.butterfly.app.annotation.Action;
import com.zengshi.butterfly.core.exception.BusinessException;
import com.zengshi.butterfly.core.exception.DefaultExceptionCode;
import com.zengshi.butterfly.core.exception.SystemException;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

/**
 * 
 * Title: 保存历史消息 <br>
 * Project Name:ecp-web-im <br>
 * Description: <br>
 * Date:2017年1月11日下午4:54:26  <br>
 * Copyright (c) 2017 ZengShi All Rights Reserved <br>
 * 
 * @author linby
 * @version  
 * @since JDK 1.7
 */
@Service("im005")
@Action(bizcode="im005", userCheck=true)
@Scope("prototype")
public class IM005Action extends AppBaseAction<IM005Req, IM005Resp> {

	@Override
	protected void getResponse() throws BusinessException, SystemException, Exception {
		this.checkRequest();//校验入参
		
		Long shopId = this.request.getShopId();//店铺ID
		String to = this.request.getTo();//JID
		String from = this.request.getFrom();//JID
		String body = this.request.getBody();//消息内容
		String messageType = this.request.getMessageType();//消息类型
		String contentType = this.request.getContentType();//消息内容类型
		String userCode = this.request.getUserCode();//买家OF账号
		String csaCode = this.request.getCsaCode();//客服OF账号
		Long businessType = this.request.getBusinessType();//业务类型：商品咨询，订单咨询
		String businessId = this.request.getBusinessId();//商品id或订单id
		String sessionId = this.request.getSessionId(); //会话id
		
		MessageHistoryReqVO historyReqVO = new MessageHistoryReqVO();
		String uuid = UUID.randomUUID().toString();
		historyReqVO.setId(uuid);
		historyReqVO.setStatus(BizUtil.msg_status_10);
		historyReqVO.setBeginDate(DateUtil.getSysDate());
		historyReqVO.setTo(to);
		historyReqVO.setFrom(from);
		historyReqVO.setShopId(shopId);
		historyReqVO.setBody(body);
		historyReqVO.setMessageType(messageType);
		historyReqVO.setContentType(contentType);
		historyReqVO.setUserCode(userCode);
		historyReqVO.setCsaCode(csaCode);
		historyReqVO.setIssueType(businessType.toString());
		if(businessType.equals(ImConstants.issueType_1)){
			historyReqVO.setOrdId(businessId);
		}else if(businessType.equals(ImConstants.issueType_2)){
			historyReqVO.setGdsId(businessId);
		}
		historyReqVO.setSessionId(sessionId);
		
		JSONObject doc = new JSONObject();
		doc = (JSONObject) JSON.toJSON(historyReqVO);
		MongoUtil.insert("T_IM_MESSAGE_HISTORY", doc);
		
		//响应
		this.response.setMessageHistory(historyReqVO);
	}

	/**
	 * 
	 * checkRequest:(校验入参). <br/> 
	 * 
	 * @author linby
	 * @return
	 * @throws BusinessException 
	 * @since JDK 1.7
	 */
	private void checkRequest() throws BusinessException{
		if(this.request.getShopId() == null){
			//this.setFaild("shopId不能为空");
			throw new BusinessException(new DefaultExceptionCode("1001","shopId不能为空"));
		}
		if(this.request.getSessionId() == null){
			//this.setFaild("from不能为空");
			throw new BusinessException(new DefaultExceptionCode("1002","sessionId不能为空"));
		}
		if(this.request.getTo() == null){
			//this.setFaild("to不能为空");
			throw new BusinessException(new DefaultExceptionCode("1003","to不能为空"));
		}
		if(this.request.getFrom() == null){
			//this.setFaild("from不能为空");
			throw new BusinessException(new DefaultExceptionCode("1004","from不能为空"));
		}
		if(this.request.getBusinessType() == null){
			//this.setFaild("from不能为空");
			throw new BusinessException(new DefaultExceptionCode("1005","businessType不能为空"));
		}
		if(StringUtil.isBlank(this.request.getBusinessId())){
			//this.setFaild("from不能为空");
			throw new BusinessException(new DefaultExceptionCode("1006","businessId不能为空"));
		}
		if(this.request.getBody() == null){
			//this.setFaild("from不能为空");
			throw new BusinessException(new DefaultExceptionCode("1007","body不能为空"));
		}
		if(this.request.getMessageType() == null){
			//this.setFaild("from不能为空");
			throw new BusinessException(new DefaultExceptionCode("1008","messageType不能为空"));
		}
		if(this.request.getUserCode() == null){
			//this.setFaild("from不能为空");
			throw new BusinessException(new DefaultExceptionCode("1009","userCode不能为空"));
		}
		if(this.request.getCsaCode() == null){
			//this.setFaild("from不能为空");
			throw new BusinessException(new DefaultExceptionCode("1010","csaCode不能为空"));
		}
	}
}
