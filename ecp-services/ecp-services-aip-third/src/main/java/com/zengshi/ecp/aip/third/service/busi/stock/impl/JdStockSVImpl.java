package com.zengshi.ecp.aip.third.service.busi.stock.impl;

import java.util.HashMap;

import com.zengshi.ecp.aip.third.dubbo.dto.req.GdsStockThirdReqDTO;
import com.zengshi.ecp.aip.third.service.busi.stock.interfaces.IStockSV;
import com.zengshi.ecp.server.front.exception.BusinessException;
 

public class JdStockSVImpl implements IStockSV{
    
    public static final String MODULE = JdStockSVImpl.class.getName();

    @Override
    public HashMap updateStock(GdsStockThirdReqDTO gdsStockThirdReqDTO)throws BusinessException{
    	return null;
    }
}

