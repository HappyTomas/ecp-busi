package com.zengshi.ecp.goods.dubbo.dto.common;

import com.zengshi.ecp.server.front.dto.BaseResponseDTO;

public class LongRespDTO extends BaseResponseDTO{

	/** 
	 * serialVersionUID:TODO(用一句话描述这个变量表示什么). 
	 * @since JDK 1.6 
	 */ 
	private static final long serialVersionUID = 6319606422428122723L;
	
	
	private Long value;


	public Long getValue() {
		return value;
	}


	public void setValue(Long value) {
		this.value = value;
	}
	
	
	
     
}

