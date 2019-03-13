package com.zengshi.ecp.aip.third.service.busi.upload.interfaces;

import com.zengshi.ecp.aip.third.dubbo.dto.req.UploadThirdReqDTO;
import com.zengshi.ecp.aip.third.dubbo.dto.resp.UploadThirdRespDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;


public interface IUploadSV {

    /**
     * 
     * token:获得当前店铺的token <br/> 
     * @author huangjx
     * @param s
     * @return 
     * @since JDK 1.7
     */
    public UploadThirdRespDTO upload(UploadThirdReqDTO uploadThirdReqDTO)throws BusinessException;
    
}

