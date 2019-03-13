package com.zengshi.ecp.aip.third.service.busi.permit.impl;

import com.zengshi.ecp.aip.third.dubbo.dto.req.ShopTopicReqDTO;
import com.zengshi.ecp.aip.third.dubbo.dto.resp.ShopTopicListRespDTO;
import com.zengshi.ecp.aip.third.service.busi.permit.interfaces.IUserPermitSV;
import com.zengshi.ecp.server.front.exception.BusinessException;

public class JdUserPermitSVImpl implements IUserPermitSV{
    
    public static final String MODULE = JdUserPermitSVImpl.class.getName();

    @Override
    public String createPermit(ShopTopicReqDTO shopTopicReqDTO)throws BusinessException{
    	return "没有实现接口";
    }
    
	// 获得第三方平台 允许调用消息列表
	@Override
    public ShopTopicListRespDTO userPermitList(ShopTopicReqDTO shopTopicReqDTO)throws BusinessException{
    	return null;
    }
	@Override
    public String cancelPermit(ShopTopicReqDTO shopTopicReqDTO)throws BusinessException{
    	return null;
    }
}

