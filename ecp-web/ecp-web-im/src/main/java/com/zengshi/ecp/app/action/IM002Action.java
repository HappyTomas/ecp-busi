package com.zengshi.ecp.app.action;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.zengshi.ecp.app.req.IM002Req;
import com.zengshi.ecp.app.resp.IM002Resp;
import com.zengshi.ecp.im.dubbo.dto.CustServSatisfyReqDTO;
import com.zengshi.ecp.im.dubbo.interfaces.ISatisfyEvaluateRSV;
import com.zengshi.butterfly.app.annotation.Action;
import com.zengshi.butterfly.core.exception.BusinessException;
import com.zengshi.butterfly.core.exception.DefaultExceptionCode;
import com.zengshi.butterfly.core.exception.SystemException;

/**
 * 
 * Title: 满意度评价 <br>
 * Project Name:ecp-web-im <br>
 * Description: <br>
 * Date:2017年1月11日下午4:54:26  <br>
 * Copyright (c) 2017 ZengShi All Rights Reserved <br>
 * 
 * @author linby
 * @version  
 * @since JDK 1.7
 */
@Service("im002")
@Action(bizcode="im002", userCheck=true)
@Scope("prototype")
public class IM002Action extends AppBaseAction<IM002Req, IM002Resp> {

	@Resource
    private ISatisfyEvaluateRSV satisfyEvaluateRSV;
	
	@Override
	protected void getResponse() throws BusinessException, SystemException, Exception {
		this.checkRequest();//校验入参
		
		Long shopId = this.request.getShopId();
		String userCode = this.request.getUserCode();
		String csaCode = this.request.getCsaCode();
		String sessionId = this.request.getSessionId();
		String satisfyType = this.request.getSatisfyType();
		String notSatisfyType = this.request.getNotSatisfyType();
		String notSatisfyReason = this.request.getNotSatisfyReason();

		//保存评价
		CustServSatisfyReqDTO custServSatisfyReqDTO = new CustServSatisfyReqDTO();
		custServSatisfyReqDTO.setShopId(shopId);
		custServSatisfyReqDTO.setOfStaffCode(userCode);
		custServSatisfyReqDTO.setCsaCode(csaCode);
		custServSatisfyReqDTO.setSessionId(sessionId);
		custServSatisfyReqDTO.setSatisfyType(satisfyType);
		custServSatisfyReqDTO.setNotSatisfyType(notSatisfyType==null?"":notSatisfyType);
		custServSatisfyReqDTO.setNotSatisfyReason(notSatisfyReason==null?"":notSatisfyReason);
		satisfyEvaluateRSV.addSatisfyEvaluate(custServSatisfyReqDTO);
		
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
		if(this.request.getShopId()==null){//店铺id
			throw new BusinessException(new DefaultExceptionCode("1001","店铺id不能为空"));
		}
		if(this.request.getUserCode()==null){//买家OF账号
			throw new BusinessException(new DefaultExceptionCode("1002","买家OF账号不能为空"));
		}
		if(this.request.getCsaCode()==null){//客服OF账号
			throw new BusinessException(new DefaultExceptionCode("1003","客服OF账号不能为空"));
		}
		if(this.request.getSessionId()==null){//发起聊天记录ID
			throw new BusinessException(new DefaultExceptionCode("1004","发起聊天记录ID不能为空"));
		}
		if(this.request.getSatisfyType()==null){//满意度类型
			throw new BusinessException(new DefaultExceptionCode("1005","满意度类型不能为空"));
		}
		//不满意原因
		if(this.request.getNotSatisfyType()!=null && this.request.getNotSatisfyReason()==null){
			throw new BusinessException(new DefaultExceptionCode("1006","不满意原因不能为空"));
		}
	}

}
