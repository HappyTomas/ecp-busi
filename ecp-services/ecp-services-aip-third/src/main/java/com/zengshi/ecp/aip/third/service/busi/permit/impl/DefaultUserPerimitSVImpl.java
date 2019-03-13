package com.zengshi.ecp.aip.third.service.busi.permit.impl;

import java.util.HashMap;
import org.springframework.util.CollectionUtils;

import com.zengshi.ecp.aip.third.dubbo.dto.req.BaseShopAuthReqDTO;
import com.zengshi.ecp.aip.third.dubbo.dto.req.ShopTopicReqDTO;
import com.zengshi.ecp.aip.third.dubbo.dto.resp.ShopTopicListRespDTO;
import com.zengshi.ecp.aip.third.service.busi.permit.interfaces.IUserPermitSV;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.paas.utils.StringUtil;

public class DefaultUserPerimitSVImpl implements IUserPermitSV {

	public static final String MODULE = DefaultUserPerimitSVImpl.class.getName();

	private HashMap<String, IUserPermitSV> userPermitSVMap;
 
	// 获得第三方平台 允许调用消息接口
	@Override
	public String createPermit(ShopTopicReqDTO shopTopicReqDTO)throws BusinessException{
		if (shopTopicReqDTO == null) {
			String value = "AIPTHIRD.100012";
			throw new BusinessException(value);
		}
		if (shopTopicReqDTO.getShopId() == null) {
			String value = "AIPTHIRD.100013";
			throw new BusinessException(value);
		}
		if (StringUtil.isBlank(shopTopicReqDTO.getPlatType())) {
			String value = "AIPTHIRD.100014";
			throw new BusinessException(value);
		}
		String resp= this.getSV(shopTopicReqDTO.findBaseShopAuth()).createPermit(
				shopTopicReqDTO);
		
		return resp;
	}
	// 获得第三方平台 允许调用消息列表
	@Override
    public ShopTopicListRespDTO userPermitList(ShopTopicReqDTO shopTopicReqDTO)throws BusinessException{
		return this.getSV(shopTopicReqDTO.findBaseShopAuth()).userPermitList(
				shopTopicReqDTO);
    }
	@Override
    public String cancelPermit(ShopTopicReqDTO shopTopicReqDTO)throws BusinessException{
		return this.getSV(shopTopicReqDTO.findBaseShopAuth()).cancelPermit(shopTopicReqDTO);
    }
	// 获得sv
	private IUserPermitSV getSV(BaseShopAuthReqDTO baseShopAuthReqDTO)
			throws BusinessException {
		// 验证参数
		baseShopAuthReqDTO.checkParams();

		IUserPermitSV sv = null;
        String value="";
		if (CollectionUtils.isEmpty(userPermitSVMap)) {
			value="AIPTHIRD.100009";
    		throw new BusinessException(value);
		}

		if (!userPermitSVMap.containsKey(baseShopAuthReqDTO.getPlatType())) {
			value="AIPTHIRD.100010";
    		throw new BusinessException(value,new String[]{baseShopAuthReqDTO.getPlatType()});

		} else {
			sv = userPermitSVMap.get(baseShopAuthReqDTO.getPlatType());
			if (sv == null) {
				value="AIPTHIRD.100011";
	    		throw new BusinessException(value);
			}
		}
		return sv;
	}

	public HashMap<String, IUserPermitSV> getUserPermitSVMap() {
		return userPermitSVMap;
	}

	public void setUserPermitSVMap(HashMap<String, IUserPermitSV> userPermitSVMap) {
		this.userPermitSVMap = userPermitSVMap;
	}
}
