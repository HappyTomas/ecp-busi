package com.zengshi.ecp.app.action;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.zengshi.ecp.app.req.IM007Req;
import com.zengshi.ecp.app.resp.IM007Resp;
import com.zengshi.ecp.busi.im.util.BizUtil;
import com.zengshi.ecp.im.dubbo.util.ImConstants;
import com.zengshi.paas.utils.MongoUtil;
import com.zengshi.paas.utils.StringUtil;
import com.zengshi.butterfly.app.annotation.Action;
import com.zengshi.butterfly.core.exception.BusinessException;
import com.zengshi.butterfly.core.exception.DefaultExceptionCode;
import com.zengshi.butterfly.core.exception.SystemException;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.QueryBuilder;

/**
 * 
 * Title: 上一次接待信息 <br>
 * Project Name:ecp-web-im <br>
 * Description: <br>
 * Date:2017年1月11日下午4:54:26  <br>
 * Copyright (c) 2017 ZengShi All Rights Reserved <br>
 * 
 * @author linby
 * @version  
 * @since JDK 1.7
 */
@Service("im007")
@Action(bizcode="im007", userCheck=true)
@Scope("prototype")
public class IM007Action extends AppBaseAction<IM007Req, IM007Resp> {

	@Override
	protected void getResponse() throws BusinessException, SystemException, Exception {
		this.checkRequest(); //校验参数
		
		String userCode = this.request.getUserCode();
		
		DBCollection collection  =MongoUtil.getDBCollection("T_IM_SESSION_HISTORY");
		QueryBuilder queryBuilder = new QueryBuilder();
		
		if(StringUtil.isNotBlank(userCode)){
			queryBuilder.and("userCode").is(userCode);
		}
		
		Long count = collection.count(queryBuilder.get());
		//1.接入次数
		this.response.setCount(count);
		queryBuilder.and("status").is(ImConstants.STATE_0);
		DBCursor cursor = collection.find(queryBuilder.get()).sort(new BasicDBObject("sessionTime",-1));
		try {
			while (cursor.hasNext()) {
				
				DBObject obj = cursor.next();
				//2.客服账号
				this.response.setCsaCode(obj.get("csaCode")+BizUtil.getOfServer());
				//3.会话时间
				this.response.setSessionTime(new Timestamp(new SimpleDateFormat("EEE MMM dd HH:mm:ss Z yyyy", Locale.UK)
						.parse(obj.get("sessionTime").toString()).getTime()));
				break;
			}
		} finally {
			cursor.close();
		}
		
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
		if(this.request.getUserCode() == null){
			//this.setFaild("userCode不能为空");
			throw new BusinessException(new DefaultExceptionCode("1001","userCode不能为空"));
		}
	}

}
