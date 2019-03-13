package com.zengshi.ecp.aip.third.dubbo.interfaces;

import java.util.HashMap;

import com.zengshi.ecp.aip.third.dubbo.dto.req.GdsStockThirdReqDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;

public interface IStockRSV {
    
    /**
     * 
     * stock:库存修改 <br/> 
     * @author huangjx
     * @param s
     * @return 
     * @since JDK 1.7
     */
	
    public HashMap updateStock(GdsStockThirdReqDTO gdsStockThirdReqDTO)throws BusinessException;
   
}

