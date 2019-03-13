package com.zengshi.ecp.aip.third.service.busi.catg.impl;

import java.util.HashMap;

import org.springframework.util.CollectionUtils;

import com.zengshi.ecp.aip.third.dubbo.dto.req.BaseShopAuthReqDTO;
import com.zengshi.ecp.aip.third.dubbo.dto.req.CatgReqDTO;
import com.zengshi.ecp.aip.third.dubbo.dto.resp.CatgsRespDTO;
import com.zengshi.ecp.aip.third.service.busi.catg.interfaces.ICatgSV;
import com.zengshi.ecp.server.front.exception.BusinessException;

public class DefaultCatgSVImpl implements ICatgSV{
    
    public static final String MODULE = DefaultCatgSVImpl.class.getName();

    private HashMap<String,ICatgSV> catgSVMap;
 
	public HashMap<String,ICatgSV> getCatgSVMap() {
		return catgSVMap;
	}

	public void setCatgSVMap(HashMap<String,ICatgSV> catgSVMap) {
		this.catgSVMap = catgSVMap;
	}
	
	//分类获得
	@Override
    public CatgsRespDTO fetch(CatgReqDTO catgReqDTO)throws BusinessException{
	    return this.getSV(catgReqDTO.findBaseShopAuth()).fetch(catgReqDTO);
    }
	
	//分类获得 并且获得属性
	@Override
    public CatgsRespDTO fetchCatgAndProp(CatgReqDTO catgReqDTO)throws BusinessException{
	    return this.getSV(catgReqDTO.findBaseShopAuth()).fetchCatgAndProp(catgReqDTO);
    }
	
	private ICatgSV getSV(BaseShopAuthReqDTO baseShopAuthReqDTO)throws BusinessException{
		//参数验证
		baseShopAuthReqDTO.checkParams();
		
		ICatgSV sv=null;
    	String value="";
    	
    	if(CollectionUtils.isEmpty(catgSVMap)){
    		value="AIPTHIRD.100009";
    		throw new BusinessException(value);
    	}
    	if(!catgSVMap.containsKey(baseShopAuthReqDTO.getPlatType())){
    		value="AIPTHIRD.100010";
    		throw new BusinessException(value,new String[]{baseShopAuthReqDTO.getPlatType()});
    	}else{
    		sv=catgSVMap.get(baseShopAuthReqDTO.getPlatType());
    		if(sv==null){
    			value="AIPTHIRD.100011";
        		throw new BusinessException(value);
    		}
    	}
    	return sv;
	}


}

