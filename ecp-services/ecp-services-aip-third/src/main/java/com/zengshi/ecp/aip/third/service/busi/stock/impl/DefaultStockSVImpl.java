package com.zengshi.ecp.aip.third.service.busi.stock.impl;

import java.util.HashMap;

import org.springframework.util.CollectionUtils;

import com.zengshi.ecp.aip.third.dubbo.dto.req.BaseShopAuthReqDTO;
import com.zengshi.ecp.aip.third.dubbo.dto.req.GdsStockThirdReqDTO;
import com.zengshi.ecp.aip.third.service.busi.stock.interfaces.IStockSV;
import com.zengshi.ecp.server.front.exception.BusinessException;

public class DefaultStockSVImpl implements IStockSV{
    
    public static final String MODULE = DefaultStockSVImpl.class.getName();

    private HashMap<String,IStockSV> stockSVMap;
 

	//商品库存修改
	@Override
	public HashMap updateStock(GdsStockThirdReqDTO gdsStockThirdReqDTO)throws BusinessException{
	    return this.getSendSV(gdsStockThirdReqDTO.findBaseShopAuth()).updateStock(gdsStockThirdReqDTO);
    }
	
	private IStockSV getSendSV(BaseShopAuthReqDTO baseShopAuthReqDTO)throws BusinessException{
		//参数验证
		baseShopAuthReqDTO.checkParams();
		
		IStockSV sv=null;
    	String value="";
    	
    	if(CollectionUtils.isEmpty(stockSVMap)){
    		value="AIPTHIRD.100009";
    		throw new BusinessException(value);
    	}
    	if(!stockSVMap.containsKey(baseShopAuthReqDTO.getPlatType())){
    		value="AIPTHIRD.100010";
    		throw new BusinessException(value,new String[]{baseShopAuthReqDTO.getPlatType()});
    	}else{
    		sv=stockSVMap.get(baseShopAuthReqDTO.getPlatType());
    		if(sv==null){
    			value="AIPTHIRD.100011";
        		throw new BusinessException(value);
    		}
    	}
    	return sv;
	}

	public HashMap<String,IStockSV> getStockSVMap() {
		return stockSVMap;
	}

	public void setStockSVMap(HashMap<String,IStockSV> stockSVMap) {
		this.stockSVMap = stockSVMap;
	}
}

