package com.zengshi.ecp.aip.third.service.busi.gds.impl;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;

import com.zengshi.ecp.aip.third.dubbo.dto.req.CatgReqDTO;
import com.zengshi.ecp.aip.third.dubbo.dto.req.GdsSendThirdReqDTO;
import com.zengshi.ecp.aip.third.dubbo.dto.req.GdsThirdReqDTO;
import com.zengshi.ecp.aip.third.dubbo.dto.req.ProductThirdReqDTO;
import com.zengshi.ecp.aip.third.dubbo.dto.resp.ProductStatusThirdRespDTO;
import com.zengshi.ecp.aip.third.dubbo.dto.resp.SellerRespDTO;
import com.zengshi.ecp.aip.third.service.busi.gds.interfaces.IGdsSV;
import com.zengshi.ecp.aip.third.service.busi.gds.interfaces.IGdsSendSV;
import com.zengshi.ecp.aip.third.service.busi.product.interfaces.IProductSV;
import com.zengshi.ecp.aip.third.service.busi.seller.interfaces.ISellerSV;
import com.zengshi.ecp.aip.third.service.busi.token.interfaces.ITokenSV;
import com.zengshi.ecp.aip.third.service.busi.utils.GetMapValueUtil;
import com.zengshi.ecp.aip.third.service.busi.utils.SchemaUtil;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.ObjectCopyUtil;
import com.alibaba.fastjson.JSON;

public class TaobaoGdsSendSVImpl implements IGdsSendSV {

	public static final String MODULE = TaobaoGdsSendSVImpl.class.getName();

	@Resource
	private ITokenSV defaultTokenSV;

	@Resource
	private ISellerSV taobaoSellerSV;

	@Resource
	private IProductSV taobaoProductSV;

	@Resource
	private IGdsSV taobaoGdsSV;

	@Override
	public HashMap send(GdsSendThirdReqDTO gdsSendReqDTO)
			throws BusinessException {
		HashMap  resultMap=new HashMap();
		try {

			// 1、验证当前用户是否属于 天猫用户
			// 2、根据分类编码调用接口，获得对应的产品规则xml
			// 3、根据2中的xml,调用接口 匹配该规则 。
			// 3.1如果匹配为空，则说明当前需要发布的商品在天猫上还不存在可用产品信息，需要先发布一个新产品 其他情况不能提交商品需要等待；
			// 3.2如果匹配成功，返回产品id列表
			// 4、3步骤通过以后，通过productId获得当前的状态。如果status=1 能发布商品 否则不能 发布商品
			// 5、发布商品
			// 5.1 如果有图片 需要上传图片
			// 5.2组织参数
			// 5.3推送商品

			SellerRespDTO seller = taobaoSellerSV.getSellerInfo(gdsSendReqDTO
					.findBaseShopAuth());

			if (seller == null) {
				LogUtil.error(MODULE, "登录用户  不能获得任何卖家基本信息");
				throw new BusinessException("AIPTHIRD.100023");
			}
			// 天猫卖家
			if ("B".equals(seller.getType())) {

				CatgReqDTO catgReqDTO = new CatgReqDTO();
				ObjectCopyUtil.copyObjValue(gdsSendReqDTO, catgReqDTO, "",
						false);
				catgReqDTO.setOutCatgCode(GetMapValueUtil.getMapValue(gdsSendReqDTO.getGdsInfoMap(), "outCatgCode").toString());// 外部分类编码
				String matchXml = taobaoProductSV.getProductMatch(catgReqDTO);
				String matchValueXml=SchemaUtil.packSchemaXml(matchXml, gdsSendReqDTO.getGdsInfoMap());
				catgReqDTO.setMatchXml(matchValueXml);
				List<String> productIds = taobaoProductSV
						.setProductMatch(catgReqDTO);

				String productId = null;

				// 没有匹配到产品 需要创建产品
				if (CollectionUtils.isEmpty(productIds)) {
					//获得产品匹配规则xml
					String productMatchXML = taobaoProductSV
							.getProductAddRule(catgReqDTO);
					if (StringUtils.isNotBlank(productMatchXML)) {
						
						LogUtil.info(MODULE, "入参hashmap="+JSON.toJSONString(gdsSendReqDTO.getGdsInfoMap()));
						String productXML=SchemaUtil.packSchemaXml(productMatchXML, gdsSendReqDTO.getGdsInfoMap());
						LogUtil.info(MODULE, "产品xml="+productXML);
						ProductThirdReqDTO productReqDTO = new ProductThirdReqDTO();
						ObjectCopyUtil.copyObjValue(catgReqDTO, productReqDTO,
								"", false);
						// 根据产品规则 生成产品
						productReqDTO.setProductXml(productXML);
						productId = taobaoProductSV
								.productAddByRule(productReqDTO);
					} else {
						productId = "0";
					}

				} else {
					productId = productIds.get(0);
				}

				ProductThirdReqDTO productReqDTO = new ProductThirdReqDTO();
				ObjectCopyUtil.copyObjValue(catgReqDTO, productReqDTO, "",
						false);
				productReqDTO.setProductId(Long.valueOf(productId));

				// 3、匹配该规则 结果1可以发布商品 ；
				ProductStatusThirdRespDTO productStatus = taobaoProductSV
						.getProductStatus(productReqDTO);

				//测试
				productStatus.setStatus("1");
				if ("1".equals(productStatus.getStatus())) {
					// 商品发布规则获得
					String gdsMatchXml = taobaoGdsSV.getGdsRule(productReqDTO);
					LogUtil.info(MODULE, "productId="+productId+"product rule xml ="+gdsMatchXml);
					String gdsXml=SchemaUtil.packSchemaXml(gdsMatchXml, gdsSendReqDTO.getGdsInfoMap());
					LogUtil.info(MODULE, "productId="+productId+"gds xml ="+gdsXml);
					GdsThirdReqDTO gdsThirdReqDTO = new GdsThirdReqDTO();
					ObjectCopyUtil.copyObjValue(catgReqDTO, gdsThirdReqDTO, "",
							false);
					gdsThirdReqDTO.setGdsXml(gdsXml);
					gdsThirdReqDTO.setProductId(Long.valueOf(productId));
					String gdsId = taobaoGdsSV.GdsAdd(gdsThirdReqDTO);
					resultMap.put("gdsId", gdsId);
				} else {
					LogUtil.error(MODULE, "等待天猫平台 审批产品 暂时不能创建商品 .....");
					throw new BusinessException("AIPTHIRD.100027");
				}

			} else {
				LogUtil.error(MODULE, "不是天猫 卖家");
				throw new BusinessException("AIPTHIRD.100024");
			}

		} catch (BusinessException ex) {
			LogUtil.error(MODULE, ex.toString());
			throw new BusinessException(ex.getMessage());
		} catch (Exception ex) {
			LogUtil.error(MODULE, ex.toString());
			throw new BusinessException("AIPTHIRD.100026");
		}
		return resultMap;
	}
}
