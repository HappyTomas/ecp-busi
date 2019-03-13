package com.zengshi.ecp.aip.third.service.busi.gds.impl;

import java.util.HashMap;

import org.springframework.util.CollectionUtils;

import com.zengshi.ecp.aip.third.dubbo.dto.req.BaseShopAuthReqDTO;
import com.zengshi.ecp.aip.third.dubbo.dto.req.GdsSendThirdReqDTO;
import com.zengshi.ecp.aip.third.service.busi.gds.interfaces.IGdsSendSV;
import com.zengshi.ecp.server.front.exception.BusinessException;

public class DefaultGdsSendSVImpl implements IGdsSendSV{
    
    public static final String MODULE = DefaultGdsSendSVImpl.class.getName();

    private HashMap<String,IGdsSendSV> gdsSendSVMap;
	public HashMap<String, IGdsSendSV> getGdsSendSVMap() {
		return gdsSendSVMap;
	}
	public void setGdsSendSVMap(HashMap<String, IGdsSendSV> gdsSendSVMap) {
		this.gdsSendSVMap = gdsSendSVMap;
	}

	//推送商品
	@Override
    public HashMap send(GdsSendThirdReqDTO gdsSendReqDTO) throws BusinessException { 
	    return this.getSendSV(gdsSendReqDTO.findBaseShopAuth()).send(gdsSendReqDTO);
    }
	
	private IGdsSendSV getSendSV(BaseShopAuthReqDTO baseShopAuthReqDTO)throws BusinessException{
		//参数验证
		baseShopAuthReqDTO.checkParams();
		
		IGdsSendSV sv=null;
    	String value="";
    	
    	if(CollectionUtils.isEmpty(gdsSendSVMap)){
    		value="AIPTHIRD.100009";
    		throw new BusinessException(value);
    	}
    	if(!gdsSendSVMap.containsKey(baseShopAuthReqDTO.getPlatType())){
    		value="AIPTHIRD.100010";
    		throw new BusinessException(value,new String[]{baseShopAuthReqDTO.getPlatType()});
    	}else{
    		sv=gdsSendSVMap.get(baseShopAuthReqDTO.getPlatType());
    		if(sv==null){
    			value="AIPTHIRD.100011";
        		throw new BusinessException(value);
    		}
    	}
    	return sv;
	}
}

