package com.zengshi.ecp.aip.third.service.busi.seller.impl;

import java.util.HashMap;
import org.springframework.util.CollectionUtils;

import com.zengshi.ecp.aip.third.dubbo.dto.req.BaseShopAuthReqDTO;
import com.zengshi.ecp.aip.third.dubbo.dto.resp.SellerRespDTO;
import com.zengshi.ecp.aip.third.service.busi.seller.interfaces.ISellerSV;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.paas.utils.StringUtil;

public class DefaultSellerSVImpl implements ISellerSV {

	public static final String MODULE = DefaultSellerSVImpl.class.getName();

	private HashMap<String, ISellerSV> sellerSVMap;
 
	// 获得属于淘宝 还是 天猫
	@Override
	 public SellerRespDTO getSellerInfo(BaseShopAuthReqDTO baseShopAuthReqDTO)throws BusinessException{
		if (baseShopAuthReqDTO == null) {
			String value = "AIPTHIRD.100012";
			throw new BusinessException(value);
		}
		if (baseShopAuthReqDTO.getShopId() == null) {
			String value = "AIPTHIRD.100013";
			throw new BusinessException(value);
		}
		if (StringUtil.isBlank(baseShopAuthReqDTO.getPlatType())) {
			String value = "AIPTHIRD.100014";
			throw new BusinessException(value);
		}
		SellerRespDTO resp= this.getSV(baseShopAuthReqDTO).getSellerInfo(baseShopAuthReqDTO);
		
		return resp;
	}
	// 获得sv
	private ISellerSV getSV(BaseShopAuthReqDTO baseShopAuthReqDTO)
			throws BusinessException {
		// 验证参数
		baseShopAuthReqDTO.checkParams();

		ISellerSV sv = null;
        String value="";
		if (CollectionUtils.isEmpty(sellerSVMap)) {
			value="AIPTHIRD.100009";
    		throw new BusinessException(value);
		}

		if (!sellerSVMap.containsKey(baseShopAuthReqDTO.getPlatType())) {
			value="AIPTHIRD.100010";
    		throw new BusinessException(value,new String[]{baseShopAuthReqDTO.getPlatType()});

		} else {
			sv = sellerSVMap.get(baseShopAuthReqDTO.getPlatType());
			if (sv == null) {
				value="AIPTHIRD.100011";
	    		throw new BusinessException(value);
			}
		}
		return sv;
	}
	public HashMap<String, ISellerSV> getSellerSVMap() {
		return sellerSVMap;
	}
	public void setSellerSVMap(HashMap<String, ISellerSV> sellerSVMap) {
		this.sellerSVMap = sellerSVMap;
	}
}
