package com.zengshi.ecp.aip.third.service.busi.prop.impl;

import com.zengshi.ecp.aip.third.dubbo.dto.req.CatgReqDTO;
import com.zengshi.ecp.aip.third.dubbo.dto.req.PropReqDTO;
import com.zengshi.ecp.aip.third.dubbo.dto.resp.CatgsRespDTO;
import com.zengshi.ecp.aip.third.dubbo.dto.resp.PropsRespDTO;
import com.zengshi.ecp.aip.third.service.busi.catg.interfaces.ICatgSV;
import com.zengshi.ecp.aip.third.service.busi.prop.interfaces.IPropSV;
import com.zengshi.ecp.server.front.exception.BusinessException;

public class JdPropSVImpl implements IPropSV{
    
    public static final String MODULE = JdPropSVImpl.class.getName();

    @Override
    public PropsRespDTO fetch(PropReqDTO propReqDTO) throws BusinessException { 
    	return null;
    }

    @Override
    public PropsRespDTO fetchPropValue(PropReqDTO propReqDTO) throws BusinessException { 
    	return null;
    }
}

