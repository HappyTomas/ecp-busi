package com.zengshi.ecp.aip.third.service.busi.utils;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;


public class GetMapValueUtil {

	public static Object getMapValue(Map<String,Object> map,String key) throws Exception {
		
	    if(map==null || StringUtils.isBlank(key)){
	    	return "";
	    }
	    if(map.get(key)==null){
	    	return "";
	    }
	    return map.get(key);
	     
	}
	 
}
