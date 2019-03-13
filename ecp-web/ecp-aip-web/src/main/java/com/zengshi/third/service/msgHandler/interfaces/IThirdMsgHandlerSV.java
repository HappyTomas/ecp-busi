package com.zengshi.third.service.msgHandler.interfaces;

import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.model.TopMsgReq;

public interface IThirdMsgHandlerSV {
	//处理
    public void doAction(TopMsgReq req)throws BusinessException;
}

