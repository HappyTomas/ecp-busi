package com.zengshi.ecp.aip.third.service.busi.upload.impl;

import java.util.HashMap;
import org.springframework.util.CollectionUtils;

import com.zengshi.ecp.aip.third.dubbo.dto.req.BaseShopAuthReqDTO;
import com.zengshi.ecp.aip.third.dubbo.dto.req.UploadThirdReqDTO;
import com.zengshi.ecp.aip.third.dubbo.dto.resp.UploadThirdRespDTO;
import com.zengshi.ecp.aip.third.service.busi.upload.interfaces.IUploadSV;
import com.zengshi.ecp.server.front.exception.BusinessException;

public class DefaultUploadSVImpl implements IUploadSV {

	public static final String MODULE = DefaultUploadSVImpl.class.getName();

	private HashMap<String, IUploadSV> uploadSVMap;
 

	// 上传
	@Override
	public UploadThirdRespDTO upload(UploadThirdReqDTO uploadThirdReqDTO)
			throws BusinessException { 
		return this.getSV(uploadThirdReqDTO.findBaseShopAuth()).upload(uploadThirdReqDTO);
	}
	// 获得sv
	private IUploadSV getSV(BaseShopAuthReqDTO baseShopAuthReqDTO)
			throws BusinessException {
		// 验证参数
		baseShopAuthReqDTO.checkParams();

		IUploadSV sv = null;
        String value="";
		if (CollectionUtils.isEmpty(uploadSVMap)) {
			value="AIPTHIRD.100009";
    		throw new BusinessException(value);
		}

		if (!uploadSVMap.containsKey(baseShopAuthReqDTO.getPlatType())) {
			value="AIPTHIRD.100010";
    		throw new BusinessException(value,new String[]{baseShopAuthReqDTO.getPlatType()});

		} else {
			sv = uploadSVMap.get(baseShopAuthReqDTO.getPlatType());
			if (sv == null) {
				value="AIPTHIRD.100011";
	    		throw new BusinessException(value);
			}
		}
		return sv;
	}

	public HashMap<String, IUploadSV> getUploadSVMap() {
		return uploadSVMap;
	}

	public void setUploadSVMap(HashMap<String, IUploadSV> uploadSVMap) {
		this.uploadSVMap = uploadSVMap;
	}
}
