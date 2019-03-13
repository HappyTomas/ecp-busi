package com.zengshi.ecp.aip.third.service.busi.prop.impl;

import java.util.HashMap;

import org.springframework.util.CollectionUtils;

import com.zengshi.ecp.aip.third.dubbo.dto.req.BaseShopAuthReqDTO;
import com.zengshi.ecp.aip.third.dubbo.dto.req.PropReqDTO;
import com.zengshi.ecp.aip.third.dubbo.dto.resp.PropsRespDTO;
import com.zengshi.ecp.aip.third.service.busi.prop.interfaces.IPropSV;
import com.zengshi.ecp.server.front.exception.BusinessException;

public class DefaultPropSVImpl implements IPropSV{
    
    public static final String MODULE = DefaultPropSVImpl.class.getName();

    private HashMap<String,IPropSV> propSVMap;
 
	public HashMap<String, IPropSV> getPropSVMap() {
		return propSVMap;
	}

	public void setPropSVMap(HashMap<String, IPropSV> propSVMap) {
		this.propSVMap = propSVMap;
	}

	//分类属性获得
	@Override
    public PropsRespDTO fetch(PropReqDTO propReqDTO)throws BusinessException{
	    return this.getSV(propReqDTO.findBaseShopAuth()).fetch(propReqDTO);
    }
	
	//分类属性值获得
	@Override
	  public PropsRespDTO fetchPropValue(PropReqDTO propReqDTO)throws BusinessException{
	    return this.getSV(propReqDTO.findBaseShopAuth()).fetchPropValue(propReqDTO);
    }
	
	private IPropSV getSV(BaseShopAuthReqDTO baseShopAuthReqDTO)throws BusinessException{
		//参数验证
		baseShopAuthReqDTO.checkParams();
		
		IPropSV sv=null;
    	String value="";
    	
    	if(CollectionUtils.isEmpty(propSVMap)){
    		value="AIPTHIRD.100009";
    		throw new BusinessException(value);
    	}
    	if(!propSVMap.containsKey(baseShopAuthReqDTO.getPlatType())){
    		value="AIPTHIRD.100010";
    		throw new BusinessException(value,new String[]{baseShopAuthReqDTO.getPlatType()});
    	}else{
    		sv=propSVMap.get(baseShopAuthReqDTO.getPlatType());
    		if(sv==null){
    			value="AIPTHIRD.100011";
        		throw new BusinessException(value);
    		}
    	}
    	return sv;
	}


}

