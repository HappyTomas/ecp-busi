package com.zengshi.ecp.aip.third.dubbo.impl;
import com.zengshi.ecp.aip.third.dubbo.dto.req.UploadThirdReqDTO;
import com.zengshi.ecp.aip.third.dubbo.dto.resp.UploadThirdRespDTO;
import com.zengshi.ecp.aip.third.dubbo.interfaces.IThirdUploadRSV;
import com.zengshi.ecp.aip.third.service.busi.upload.interfaces.IUploadSV;
import com.zengshi.ecp.server.front.exception.BusinessException;

import javax.annotation.Resource;
/**
 * 
 * 上传文件 <br/> 
 * @author huangjx
 * @return 
 * @since JDK 1.7
 */
public class ThirdUploadRSVImpl extends AipAbstractRSVImpl implements IThirdUploadRSV {
	
	@Resource
	private IUploadSV defaultUploadSV;

	@Override
	 public UploadThirdRespDTO upload(UploadThirdReqDTO uploadThirdReqDTO)throws BusinessException{
	    return defaultUploadSV.upload(uploadThirdReqDTO);
    }
 
}

