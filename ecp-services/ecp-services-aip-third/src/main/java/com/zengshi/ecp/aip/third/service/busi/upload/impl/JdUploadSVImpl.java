package com.zengshi.ecp.aip.third.service.busi.upload.impl;

import com.zengshi.ecp.aip.third.dubbo.dto.req.UploadThirdReqDTO;
import com.zengshi.ecp.aip.third.dubbo.dto.resp.UploadThirdRespDTO;
import com.zengshi.ecp.aip.third.service.busi.upload.interfaces.IUploadSV;
import com.zengshi.ecp.server.front.exception.BusinessException;

public class JdUploadSVImpl implements IUploadSV{
    
    public static final String MODULE = JdUploadSVImpl.class.getName();

    @Override
    public UploadThirdRespDTO upload(UploadThirdReqDTO uploadThirdReqDTO)throws BusinessException{
    	return null;
    }
	 
}

