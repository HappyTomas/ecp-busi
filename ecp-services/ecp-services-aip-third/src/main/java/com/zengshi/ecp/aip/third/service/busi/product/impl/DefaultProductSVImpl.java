package com.zengshi.ecp.aip.third.service.busi.product.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.util.CollectionUtils;

import com.zengshi.ecp.aip.third.dubbo.dto.req.BaseShopAuthReqDTO;
import com.zengshi.ecp.aip.third.dubbo.dto.req.CatgReqDTO;
import com.zengshi.ecp.aip.third.dubbo.dto.req.ProductThirdReqDTO;
import com.zengshi.ecp.aip.third.dubbo.dto.resp.ProductStatusThirdRespDTO;
import com.zengshi.ecp.aip.third.service.busi.product.interfaces.IProductSV;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.paas.utils.StringUtil;

public class DefaultProductSVImpl implements IProductSV {

	public static final String MODULE = DefaultProductSVImpl.class.getName();

	private HashMap<String, IProductSV> productSVMap;

	// 产品匹配规则xml
	@Override
	public String getProductMatch(CatgReqDTO catgReqDTO)
			throws BusinessException {
		if (catgReqDTO == null) {
			String value = "AIPTHIRD.100012";
			throw new BusinessException(value);
		}
		if (catgReqDTO.getShopId() == null) {
			String value = "AIPTHIRD.100013";
			throw new BusinessException(value);
		}
		if (StringUtil.isBlank(catgReqDTO.getPlatType())) {
			String value = "AIPTHIRD.100014";
			throw new BusinessException(value);
		}
		String resp = this.getSV(catgReqDTO.findBaseShopAuth())
				.getProductMatch(catgReqDTO);
		return resp;
	}

	// 匹配产品
	@Override
	public List<String> setProductMatch(CatgReqDTO catgReqDTO)
			throws BusinessException {
		if (catgReqDTO == null) {
			String value = "AIPTHIRD.100012";
			throw new BusinessException(value);
		}
		if (catgReqDTO.getShopId() == null) {
			String value = "AIPTHIRD.100013";
			throw new BusinessException(value);
		}
		if (StringUtil.isBlank(catgReqDTO.getPlatType())) {
			String value = "AIPTHIRD.100014";
			throw new BusinessException(value);
		}
		List<String> resp = this.getSV(catgReqDTO.findBaseShopAuth())
				.setProductMatch(catgReqDTO);
		return resp;
	}

	// 获得产品基本状态
	@Override
	public ProductStatusThirdRespDTO getProductStatus(ProductThirdReqDTO productReqDTO)
			throws BusinessException {
		if (productReqDTO == null) {
			String value = "AIPTHIRD.100012";
			throw new BusinessException(value);
		}
		if (productReqDTO.getShopId() == null) {
			String value = "AIPTHIRD.100013";
			throw new BusinessException(value);
		}
		if (StringUtil.isBlank(productReqDTO.getPlatType())) {
			String value = "AIPTHIRD.100014";
			throw new BusinessException(value);
		}
		ProductStatusThirdRespDTO resp = this.getSV(productReqDTO.findBaseShopAuth())
				.getProductStatus(productReqDTO);
		return resp;
	}

	@Override
	public String getProductAddRule(CatgReqDTO catgReqDTO)
			throws BusinessException {
		if (catgReqDTO == null) {
			String value = "AIPTHIRD.100012";
			throw new BusinessException(value);
		}
		if (catgReqDTO.getShopId() == null) {
			String value = "AIPTHIRD.100013";
			throw new BusinessException(value);
		}
		if (StringUtil.isBlank(catgReqDTO.getPlatType())) {
			String value = "AIPTHIRD.100014";
			throw new BusinessException(value);
		}
		String resp = this.getSV(catgReqDTO.findBaseShopAuth())
				.getProductAddRule(catgReqDTO);

		return resp;
	}

	// 产品添加 返回 产品id
	@Override
	public String productAddByRule(ProductThirdReqDTO productReqDTO)
			throws BusinessException {
		if (productReqDTO == null) {
			String value = "AIPTHIRD.100012";
			throw new BusinessException(value);
		}
		if (productReqDTO.getShopId() == null) {
			String value = "AIPTHIRD.100013";
			throw new BusinessException(value);
		}
		if (StringUtil.isBlank(productReqDTO.getPlatType())) {
			String value = "AIPTHIRD.100014";
			throw new BusinessException(value);
		}
		String resp = this.getSV(productReqDTO.findBaseShopAuth())
				.productAddByRule(productReqDTO);
		return resp;
	}

	// 获得sv
	private IProductSV getSV(BaseShopAuthReqDTO baseShopAuthReqDTO)
			throws BusinessException {
		// 验证参数
		baseShopAuthReqDTO.checkParams();

		IProductSV sv = null;
		String value = "";
		if (CollectionUtils.isEmpty(productSVMap)) {
			value = "AIPTHIRD.100009";
			throw new BusinessException(value);
		}

		if (!productSVMap.containsKey(baseShopAuthReqDTO.getPlatType())) {
			value = "AIPTHIRD.100010";
			throw new BusinessException(value,
					new String[] { baseShopAuthReqDTO.getPlatType() });

		} else {
			sv = productSVMap.get(baseShopAuthReqDTO.getPlatType());
			if (sv == null) {
				value = "AIPTHIRD.100011";
				throw new BusinessException(value);
			}
		}
		return sv;
	}

	public HashMap<String, IProductSV> getProductSVMap() {
		return productSVMap;
	}

	public void setProductSVMap(HashMap<String, IProductSV> productSVMap) {
		this.productSVMap = productSVMap;
	}
}
