package com.zengshi.ecp.aip.third.service.busi.catg.impl;

import com.zengshi.ecp.aip.third.dubbo.dto.req.CatgReqDTO;
import com.zengshi.ecp.aip.third.dubbo.dto.resp.CatgsRespDTO;
import com.zengshi.ecp.aip.third.service.busi.catg.interfaces.ICatgSV;
import com.zengshi.ecp.server.front.exception.BusinessException;

public class JdCatgSVImpl implements ICatgSV{
    
    public static final String MODULE = JdCatgSVImpl.class.getName();

    @Override
    public CatgsRespDTO fetch(CatgReqDTO catgReqDTO) throws BusinessException { 
    	return null;
    }

    @Override
    public CatgsRespDTO fetchCatgAndProp(CatgReqDTO catgReqDTO) throws BusinessException { 
    	return null;
    }
}

