package com.zengshi.third.service.msg.impl.jd;

import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.model.ThirdMsgReq;
import com.zengshi.third.service.msg.interfaces.IThirdGetMsgHandlerSV;

public class JdGetMsgSVImpl implements IThirdGetMsgHandlerSV {
	//京东 监听开始
	@Override
	public void init(final ThirdMsgReq thirdMsgReq)throws BusinessException{
		//开发时再定
	}
	@Override
	public void destroy(final ThirdMsgReq thirdMsgReq)throws BusinessException{
		 
	}
}
