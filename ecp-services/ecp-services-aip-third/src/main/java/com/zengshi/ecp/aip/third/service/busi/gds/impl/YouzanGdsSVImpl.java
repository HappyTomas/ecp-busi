package com.zengshi.ecp.aip.third.service.busi.gds.impl;

import com.zengshi.ecp.aip.third.dubbo.dto.req.GdsThirdReqDTO;
import com.zengshi.ecp.aip.third.dubbo.dto.req.ProductThirdReqDTO;
import com.zengshi.ecp.aip.third.service.busi.gds.interfaces.IGdsSV;
import com.zengshi.ecp.server.front.exception.BusinessException;

public class YouzanGdsSVImpl implements IGdsSV{
    
    public static final String MODULE = YouzanGdsSVImpl.class.getName();

    private static final String method="kdt.item.add";
    
    @Override
    public String getGdsRule(ProductThirdReqDTO productReqDTO)throws BusinessException{
    	return null;
    }
    @Override
    public String GdsAdd(GdsThirdReqDTO gdsThirdReqDTO)throws BusinessException{
    	return null;
    }
}

