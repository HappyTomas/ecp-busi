package com.zengshi.ecp.aip.third.dubbo.impl;

import com.zengshi.ecp.aip.third.dubbo.dto.req.ShopTopicReqDTO;
import com.zengshi.ecp.aip.third.dubbo.dto.resp.ShopTopicListRespDTO;
import com.zengshi.ecp.aip.third.dubbo.interfaces.IUserPermitRSV;
import com.zengshi.ecp.aip.third.service.busi.permit.interfaces.IUserPermitSV;
import com.zengshi.ecp.server.front.exception.BusinessException;

import javax.annotation.Resource;
public class UserPermitRSVImpl extends AipAbstractRSVImpl implements IUserPermitRSV {
	
	@Resource
	private IUserPermitSV defaultUserPermitSV;
	
	@Override
    public String createUserPerimit(ShopTopicReqDTO shopTopicReqDTO)throws BusinessException{
		return defaultUserPermitSV.createPermit(shopTopicReqDTO);
	}
	// 获得第三方平台 允许调用消息列表
	@Override
    public ShopTopicListRespDTO userPermitList(ShopTopicReqDTO shopTopicReqDTO)throws BusinessException{
		return defaultUserPermitSV.userPermitList(shopTopicReqDTO);
    }
	// 取消用户授权
	@Override
    public String cancelUserPerimit(ShopTopicReqDTO shopTopicReqDTO)throws BusinessException{
		return defaultUserPermitSV.cancelPermit(shopTopicReqDTO);
    }
}

