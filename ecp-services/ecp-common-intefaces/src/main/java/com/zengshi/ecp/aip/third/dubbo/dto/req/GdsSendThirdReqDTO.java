package com.zengshi.ecp.aip.third.dubbo.dto.req;

import java.util.Map;

/**
 * 
 * @version  
 * @since JDK 1.7
 */
public class GdsSendThirdReqDTO extends BaseShopAuthReqDTO{

	private static final long serialVersionUID = 1L;
	 
	private Map<String,  Object> gdsInfoMap;

    public Map<String, Object> getGdsInfoMap() {
        return gdsInfoMap;
    }

    public void setGdsInfoMap(Map<String, Object> gdsInfoMap) {
        this.gdsInfoMap = gdsInfoMap;
    }
	
}

