package com.zengshi.ecp.aip.third.dubbo.interfaces;

import com.zengshi.ecp.aip.third.dubbo.dto.req.UploadThirdReqDTO;
import com.zengshi.ecp.aip.third.dubbo.dto.resp.UploadThirdRespDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;

public interface IThirdUploadRSV {
    
    /**
     * 
     * upload:上传附件<br/> 
     * @author huangjx
     * @param s
     * @return 
     * @since JDK 1.7
     */
	
    public UploadThirdRespDTO upload(UploadThirdReqDTO uploadThirdReqDTO)throws BusinessException;
   
}

