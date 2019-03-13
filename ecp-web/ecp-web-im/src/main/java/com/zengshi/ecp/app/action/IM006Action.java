package com.zengshi.ecp.app.action;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.zengshi.ecp.app.req.IM006Req;
import com.zengshi.ecp.app.resp.IM006Resp;
import com.zengshi.paas.utils.DateUtil;
import com.zengshi.paas.utils.MongoUtil;
import com.zengshi.butterfly.app.annotation.Action;
import com.zengshi.butterfly.core.exception.BusinessException;
import com.zengshi.butterfly.core.exception.DefaultExceptionCode;
import com.zengshi.butterfly.core.exception.SystemException;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

/**
 * 
 * Title: 修改历史消息状态 <br>
 * Project Name:ecp-web-im <br>
 * Description: <br>
 * Date:2017年1月11日下午4:54:26  <br>
 * Copyright (c) 2017 ZengShi All Rights Reserved <br>
 * 
 * @author linby
 * @version  
 * @since JDK 1.7
 */
@Service("im006")
@Action(bizcode="im006", userCheck=true)
@Scope("prototype")
public class IM006Action extends AppBaseAction<IM006Req, IM006Resp> {

	@Override
	protected void getResponse() throws BusinessException, SystemException, Exception {
		this.checkRequest();//校验参数
		
		String id = this.request.getId();
		String status = this.request.getStatus();
		
		BasicDBObject query = new BasicDBObject();
		query.put("id", id);
		DBObject stuFound = MongoUtil.getDBCollection("T_IM_MESSAGE_HISTORY").findOne(query);
		stuFound.put("status", status==null?"20":status);
		stuFound.put("arriveDate", DateUtil.getSysDate());
		//更新
		MongoUtil.getDBCollection("T_IM_MESSAGE_HISTORY").update(query, stuFound);
		
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
		if(this.request.getId() == null){
			//this.setFaild("id不能为空");
			throw new BusinessException(new DefaultExceptionCode("1001","消息id不能为空"));
		}
		if(this.request.getStatus() == null){
			//this.setFaild("status不能为空");
			throw new BusinessException(new DefaultExceptionCode("1002","消息状态不能为空"));
		}
	}
}
