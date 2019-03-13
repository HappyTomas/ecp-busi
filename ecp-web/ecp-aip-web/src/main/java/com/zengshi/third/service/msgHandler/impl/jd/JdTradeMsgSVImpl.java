package com.zengshi.third.service.msgHandler.impl.jd;

import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.model.JdMsgPushEntity;
import com.zengshi.model.TopMsgReq;
import com.zengshi.third.service.msgHandler.interfaces.IThirdMsgHandlerSV;

public class JdTradeMsgSVImpl implements IThirdMsgHandlerSV {
	//京东 
	@Override
	public void doAction(TopMsgReq req)throws BusinessException{
		//调用dubbo服务 处理各个业务
		JdMsgPushEntity r=(JdMsgPushEntity) req;
	    System.out.println("jd action");
	}
}
