package com.zengshi.ecp.aip.third.service.busi.product.impl;

import java.util.List;

import com.zengshi.ecp.aip.third.dubbo.dto.req.CatgReqDTO;
import com.zengshi.ecp.aip.third.dubbo.dto.req.ProductThirdReqDTO;
import com.zengshi.ecp.aip.third.dubbo.dto.resp.ProductStatusThirdRespDTO;
import com.zengshi.ecp.aip.third.service.busi.product.interfaces.IProductSV;
import com.zengshi.ecp.server.front.exception.BusinessException;

public class JdProductSVImpl implements IProductSV {

	public static final String MODULE = JdProductSVImpl.class.getName();

	@Override
	public String getProductMatch(CatgReqDTO catgReqDTO)
			throws BusinessException {
		return null;
	}

	@Override
	public List<String> setProductMatch(CatgReqDTO catgReqDTO)
			throws BusinessException {
		return null;
	}
	@Override
	 public ProductStatusThirdRespDTO getProductStatus(ProductThirdReqDTO productReqDTO)
			throws BusinessException {
		return null;
	}
	@Override
	public String getProductAddRule(CatgReqDTO catgReqDTO)
			throws BusinessException {
		return null;
	}
	@Override
	public String productAddByRule(ProductThirdReqDTO productReqDTO)
			throws BusinessException {
		return null;
	}
}
