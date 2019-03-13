package com.zengshi.ecp.aip.third.dubbo.impl;

import java.util.HashMap;

import com.zengshi.ecp.aip.third.dubbo.dto.req.GdsStockThirdReqDTO;
import com.zengshi.ecp.aip.third.dubbo.interfaces.IStockRSV;
import com.zengshi.ecp.aip.third.service.busi.stock.interfaces.IStockSV;
import com.zengshi.ecp.server.front.exception.BusinessException;

import javax.annotation.Resource;
/**
 * 
 * 库存修改 <br/> 
 * @author huangjx
 * @return 
 * @since JDK 1.7
 */
public class StockRSVImpl extends AipAbstractRSVImpl implements IStockRSV {
	
	@Resource
	private IStockSV defaultStockSV;

	@Override
	public HashMap updateStock(GdsStockThirdReqDTO gdsStockThirdReqDTO)throws BusinessException{
	    return defaultStockSV.updateStock(gdsStockThirdReqDTO);
    }
 
}

