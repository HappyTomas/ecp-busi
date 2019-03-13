package com.zengshi.third.service.msg.interfaces;

import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.model.ThirdMsgReq;


public interface IThirdGetMsgHandlerSV {
	//启动
    public void init(final ThirdMsgReq thirdMsgReq)throws BusinessException;
   //注销
    public void destroy(final ThirdMsgReq thirdMsgReq)throws BusinessException;
}

