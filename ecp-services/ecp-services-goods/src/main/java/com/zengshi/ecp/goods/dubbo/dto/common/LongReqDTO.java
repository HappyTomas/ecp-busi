/** 
 * Project Name:ecp-services-goods 
 * File Name:LongReqDTO.java 
 * Package Name:com.zengshi.ecp.goods.dubbo.dto.common 
 * Date:2015年8月28日上午9:47:13 
 * Copyright (c) 2015, ZengShi All Rights Reserved. 
 * 
 */ 
package com.zengshi.ecp.goods.dubbo.dto.common;

import com.zengshi.ecp.server.front.dto.BaseInfo;

/**
 * Long类型请求DTO,可用于Long类型单一主键传参. <br>
 * Project Name:ecp-services-goods <br>
 * Description: <br>
 * Date:2015年8月28日上午9:47:13  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author liyong7
 * @version  
 * @since JDK 1.6 
 */
public class LongReqDTO extends BaseInfo {

    /** 
     * @since JDK 1.6 
     */ 
    private static final long serialVersionUID = -2116356633093090837L;
    
    public LongReqDTO() {
    	super();
 	}
    
    public LongReqDTO(Long id) {
		super();
		this.id = id;
	}

	private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    

}

