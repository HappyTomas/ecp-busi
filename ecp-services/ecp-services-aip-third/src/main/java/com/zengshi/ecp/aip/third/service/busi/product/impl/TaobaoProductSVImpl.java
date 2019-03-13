package com.zengshi.ecp.aip.third.service.busi.product.impl;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;

import com.zengshi.ecp.aip.third.dubbo.dto.req.CatgReqDTO;
import com.zengshi.ecp.aip.third.dubbo.dto.req.ProductThirdReqDTO;
import com.zengshi.ecp.aip.third.dubbo.dto.resp.ProductStatusThirdRespDTO;
import com.zengshi.ecp.aip.third.service.busi.product.interfaces.IProductSV;
import com.zengshi.ecp.aip.third.service.busi.token.interfaces.ITokenSV;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.paas.utils.LogUtil;
import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.request.TmallProductAddSchemaGetRequest;
import com.taobao.api.request.TmallProductMatchSchemaGetRequest;
import com.taobao.api.request.TmallProductSchemaAddRequest;
import com.taobao.api.request.TmallProductSchemaGetRequest;
import com.taobao.api.request.TmallProductSchemaMatchRequest;
import com.taobao.api.response.TmallProductAddSchemaGetResponse;
import com.taobao.api.response.TmallProductMatchSchemaGetResponse;
import com.taobao.api.response.TmallProductSchemaAddResponse;
import com.taobao.api.response.TmallProductSchemaGetResponse;
import com.taobao.api.response.TmallProductSchemaMatchResponse;
import com.taobao.top.schema.factory.SchemaReader;
import com.taobao.top.schema.field.Field;
import com.taobao.top.schema.field.InputField;

public class TaobaoProductSVImpl implements IProductSV {

	public static final String MODULE = TaobaoProductSVImpl.class.getName();

	@Resource
	private ITokenSV defaultTokenSV;

	// 获得匹配规则
	@Override
	public String getProductMatch(CatgReqDTO catgReqDTO)
			throws BusinessException {
		String re = null;
		try {

			TaobaoClient client = new DefaultTaobaoClient(
					catgReqDTO.getServerUrl(), catgReqDTO.getAppkey(),
					catgReqDTO.getAppscret());

			TmallProductMatchSchemaGetRequest req = new TmallProductMatchSchemaGetRequest();
			req.setCategoryId(Long.valueOf(catgReqDTO.getOutCatgCode()));// 必填

			// 验证参数是否正确
			req.check();
			String token = defaultTokenSV.fetchShopToken(catgReqDTO
					.findBaseShopAuth());
			TmallProductMatchSchemaGetResponse rsp = client.execute(req, token);

			// 返回结果解析
			if (rsp.isSuccess()) {
				// <itemRule><field id="prop_20000" name="品牌"
				// type="singleCheck"><rules><rule name="requiredRule"
				// value="true"/></rules></field></itemRule>
				re = rsp.getMatchResult();
			} else {
				LogUtil.error(MODULE, rsp.getBody().toString());
				throw new BusinessException("AIPTHIRD.ERRORS.100001",
						new String[] { rsp.getBody().toString() });
			}

		} catch (Exception e) {
			LogUtil.error(MODULE, e.toString());
			throw new BusinessException("AIPTHIRD.ERRORS.100002",
					new String[] { e.toString() });
		}

		return re;

	}

	// 匹配
	@Override
	public List<String> setProductMatch(CatgReqDTO catgReqDTO)
			throws BusinessException {
		List<String> re = null;
		try {

			TaobaoClient client = new DefaultTaobaoClient(
					catgReqDTO.getServerUrl(), catgReqDTO.getAppkey(),
					catgReqDTO.getAppscret());

			TmallProductSchemaMatchRequest req = new TmallProductSchemaMatchRequest();
			req.setCategoryId(Long.valueOf(catgReqDTO.getOutCatgCode()));// 必填
			req.setPropvalues(catgReqDTO.getMatchXml());// 必填

			// 验证参数是否正确
			req.check();
			String token = defaultTokenSV.fetchShopToken(catgReqDTO
					.findBaseShopAuth());
			TmallProductSchemaMatchResponse rsp = client.execute(req, token);

			// 返回结果解析
			if (rsp.isSuccess()) {
				if (StringUtils.isNotBlank(rsp.getMatchResult())) {
					re = Arrays.asList(rsp.getMatchResult().split(","));
				}
			} else {
				LogUtil.error(MODULE, rsp.getBody().toString());
				throw new BusinessException("AIPTHIRD.ERRORS.100001",
						new String[] { rsp.getBody().toString() });
			}

		} catch (Exception e) {
			LogUtil.error(MODULE, e.toString());
			throw new BusinessException("AIPTHIRD.ERRORS.100002",
					new String[] { e.toString() });
		}

		return re;

	}

	// 获得产品基本状态
	@Override
	public ProductStatusThirdRespDTO getProductStatus(ProductThirdReqDTO productReqDTO)
			throws BusinessException {
		ProductStatusThirdRespDTO re = new ProductStatusThirdRespDTO();
		try {

			TaobaoClient client = new DefaultTaobaoClient(
					productReqDTO.getServerUrl(), productReqDTO.getAppkey(),
					productReqDTO.getAppscret());

			TmallProductSchemaGetRequest req = new TmallProductSchemaGetRequest();
			req.setProductId(productReqDTO.getProductId());
			// 验证参数是否正确
			req.check();
			String token = defaultTokenSV.fetchShopToken(productReqDTO
					.findBaseShopAuth());
			TmallProductSchemaGetResponse rsp = client.execute(req, token);
             //查询产品状态，如果返回true则可以直接发布商品，否则需要等待； 
			// 返回结果解析
			if (rsp.isSuccess()) {
			 String resultString= rsp.getGetProductResult();
			/*	{
				    "tmall_product_schema_get_response":{
				        "get_product_result":"<itemRule> <field id=\"can_publish_item\" name=\"能否发布商品\" type=\"input\"> \t<rules> \t\t<rule name=\"tipRule\" value=\"可以发布商品\"\/> \t<\/rules> \t<default-value>true<\/default-value> <\/field> <\/itemRule>"
				    }
				}*/
			 
			 if(StringUtils.isNotBlank(resultString)){
				  //json解析
				 Map<String, Field> map=SchemaReader.readXmlForMap(resultString);
				 if(map!=null){
					  re = new ProductStatusThirdRespDTO();
					  InputField f = (InputField)map.get("can_publish_item");
					  re.setStatus(f.getValue());
				 }
				 
			 } 
				
			} else {
				LogUtil.error(MODULE, rsp.getBody().toString());
				throw new BusinessException("AIPTHIRD.ERRORS.100001",
						new String[] { rsp.getBody().toString() });
			}

		} catch (Exception e) {
			LogUtil.error(MODULE, e.toString());
			throw new BusinessException("AIPTHIRD.ERRORS.100002",
					new String[] { e.toString() });
		}

		return re;

	}
	//获得产品添加规则 xml
	@Override
	public String getProductAddRule(CatgReqDTO catgReqDTO)
			throws BusinessException {
		String re = null;
		try {

			TaobaoClient client = new DefaultTaobaoClient(
					catgReqDTO.getServerUrl(), catgReqDTO.getAppkey(),
					catgReqDTO.getAppscret());

			TmallProductAddSchemaGetRequest req = new TmallProductAddSchemaGetRequest();
			req.setCategoryId(Long.valueOf(catgReqDTO.getOutCatgCode()));// 必填
			if(StringUtils.isNotBlank(catgReqDTO.getBrandId())){
				req.setBrandId(Long.valueOf(catgReqDTO.getBrandId()));
			}
			// 验证参数是否正确
			req.check();
			String token = defaultTokenSV.fetchShopToken(catgReqDTO
					.findBaseShopAuth());
			TmallProductAddSchemaGetResponse rsp = client.execute(req, token);

			// 返回结果解析
			if (rsp.isSuccess()) {
				 re=rsp.getAddProductRule();
			} else {
				LogUtil.error(MODULE, rsp.getBody().toString());
				throw new BusinessException("AIPTHIRD.ERRORS.100001",
						new String[] { rsp.getBody().toString() });
			}

		} catch (Exception e) {
			LogUtil.error(MODULE, e.toString());
			throw new BusinessException("AIPTHIRD.ERRORS.100002",
					new String[] { e.toString() });
		}

		return re;

	}
	//添加产品  返回产品id
	@Override
	public String productAddByRule(ProductThirdReqDTO productReqDTO)
			throws BusinessException {
		String re = null;
		try {

			TaobaoClient client = new DefaultTaobaoClient(
					productReqDTO.getServerUrl(), productReqDTO.getAppkey(),
					productReqDTO.getAppscret());

			TmallProductSchemaAddRequest req = new TmallProductSchemaAddRequest();
			if(StringUtils.isNotBlank(productReqDTO.getBrandId())){
				req.setBrandId(Long.valueOf(productReqDTO.getBrandId()));//非必填
			}
			req.setCategoryId(Long.valueOf(productReqDTO.getOutCatgCode()));//必填
			req.setXmlData(productReqDTO.getProductXml());//必填
			// 验证参数是否正确
			req.check();
			String token = defaultTokenSV.fetchShopToken(productReqDTO
					.findBaseShopAuth());
			TmallProductSchemaAddResponse rsp = client.execute(req, token);
			// 返回结果解析
			if (rsp.isSuccess()) {
			/*	{
				    "tmall_product_schema_add_response":{
				        "add_product_result":"<itemrule> <field id=\"product_id\" name=\"产品ID\" type=\"input \">1221<\/field> <field id=\"create_time\" name=\"创建时间\" type=\"input \">2014-01-01 00:00:00<\/field> <itemrule>"
				    }
				}*/
				 //解析xml 返回产品id
				 if(StringUtils.isNotBlank(rsp.getAddProductResult())){
					  //json解析
					 Map<String, Field> map=SchemaReader.readXmlForMap(rsp.getAddProductResult());
					 if(map!=null){
						 InputField f = (InputField)map.get("product_id");
						 re=f.getValue();
					 }
				 } 
				
			} else {
				LogUtil.error(MODULE, rsp.getBody().toString());
				throw new BusinessException("AIPTHIRD.ERRORS.100001",
						new String[] { rsp.getBody().toString() });
			}

		} catch (Exception e) {
			LogUtil.error(MODULE, e.toString());
			throw new BusinessException("AIPTHIRD.ERRORS.100002",
					new String[] { e.toString() });
		}

		return re;

	}
}
