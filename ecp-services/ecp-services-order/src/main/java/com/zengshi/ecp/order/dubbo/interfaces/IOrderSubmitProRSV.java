package com.zengshi.ecp.order.dubbo.interfaces;

import com.zengshi.ecp.order.dubbo.dto.ROrdSubmitProReq;

public interface IOrderSubmitProRSV {

	/** 
	 * execute:进行预占处理. <br/> 
	 * @author cbl  
	 * @since JDK 1.6 
	 */ 
	void execute(ROrdSubmitProReq rOrdSubmitProReq);
	/** 
	 * rollback:回退处理. <br/> 
	 * @author cbl  
	 * @since JDK 1.6 
	 */ 
	void rollback(ROrdSubmitProReq rOrdSubmitProReq);
}
