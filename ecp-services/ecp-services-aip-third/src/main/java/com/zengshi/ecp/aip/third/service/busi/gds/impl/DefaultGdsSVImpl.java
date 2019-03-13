package com.zengshi.ecp.aip.third.service.busi.gds.impl;

import java.util.HashMap;

import org.springframework.util.CollectionUtils;

import com.zengshi.ecp.aip.third.dubbo.dto.req.BaseShopAuthReqDTO;
import com.zengshi.ecp.aip.third.dubbo.dto.req.GdsThirdReqDTO;
import com.zengshi.ecp.aip.third.dubbo.dto.req.ProductThirdReqDTO;
import com.zengshi.ecp.aip.third.service.busi.gds.interfaces.IGdsSV;
import com.zengshi.ecp.server.front.exception.BusinessException;

public class DefaultGdsSVImpl implements IGdsSV{
    
    public static final String MODULE = DefaultGdsSVImpl.class.getName();

    private HashMap<String,IGdsSV> gdsSVMap;
 

	//商品规则
	@Override
	public String getGdsRule(ProductThirdReqDTO productReqDTO)throws BusinessException{
	    return this.getSV(productReqDTO.findBaseShopAuth()).getGdsRule(productReqDTO);
    }
	//商品添加
	@Override
	public String GdsAdd(GdsThirdReqDTO gdsThirdReqDTO)throws BusinessException{
	    return this.getSV(gdsThirdReqDTO.findBaseShopAuth()).GdsAdd(gdsThirdReqDTO);
    }
	private IGdsSV getSV(BaseShopAuthReqDTO baseShopAuthReqDTO)throws BusinessException{
		//参数验证
		baseShopAuthReqDTO.checkParams();
		
		IGdsSV sv=null;
    	String value="";
    	
    	if(CollectionUtils.isEmpty(gdsSVMap)){
    		value="AIPTHIRD.100009";
    		throw new BusinessException(value);
    	}
    	if(!gdsSVMap.containsKey(baseShopAuthReqDTO.getPlatType())){
    		value="AIPTHIRD.100010";
    		throw new BusinessException(value,new String[]{baseShopAuthReqDTO.getPlatType()});
    	}else{
    		sv=gdsSVMap.get(baseShopAuthReqDTO.getPlatType());
    		if(sv==null){
    			value="AIPTHIRD.100011";
        		throw new BusinessException(value);
    		}
    	}
    	return sv;
	}

	public HashMap<String,IGdsSV> getGdsSVMap() {
		return gdsSVMap;
	}

	public void setGdsSVMap(HashMap<String,IGdsSV> gdsSVMap) {
		this.gdsSVMap = gdsSVMap;
	}
}

