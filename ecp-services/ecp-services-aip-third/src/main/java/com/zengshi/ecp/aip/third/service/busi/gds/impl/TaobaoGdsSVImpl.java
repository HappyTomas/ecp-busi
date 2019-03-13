package com.zengshi.ecp.aip.third.service.busi.gds.impl;


import javax.annotation.Resource;

import com.zengshi.ecp.aip.third.dubbo.dto.req.GdsThirdReqDTO;
import com.zengshi.ecp.aip.third.dubbo.dto.req.ProductThirdReqDTO;
import com.zengshi.ecp.aip.third.service.busi.gds.interfaces.IGdsSV;
import com.zengshi.ecp.aip.third.service.busi.token.interfaces.ITokenSV;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.paas.utils.LogUtil;
import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.request.TmallItemAddSchemaGetRequest;
import com.taobao.api.request.TmallItemSchemaAddRequest;
import com.taobao.api.response.TmallItemAddSchemaGetResponse;
import com.taobao.api.response.TmallItemSchemaAddResponse;

public class TaobaoGdsSVImpl implements IGdsSV{
    
    public static final String MODULE = TaobaoGdsSVImpl.class.getName();

    @Resource
	private ITokenSV defaultTokenSV;
    
    //返回发布商品的规则文档
    @Override
    public String getGdsRule(ProductThirdReqDTO productReqDTO)throws BusinessException{
		String re = null;
		try {

			TaobaoClient client = new DefaultTaobaoClient(
					productReqDTO.getServerUrl(), productReqDTO.getAppkey(),
					productReqDTO.getAppscret());

			TmallItemAddSchemaGetRequest req = new TmallItemAddSchemaGetRequest();
			req.setCategoryId(Long.valueOf(productReqDTO.getOutCatgCode()));//必填
			req.setProductId(productReqDTO.getProductId());//必填
			// 验证参数是否正确
			req.check();
			String token = defaultTokenSV.fetchShopToken(productReqDTO
					.findBaseShopAuth());
			TmallItemAddSchemaGetResponse rsp = client.execute(req, token);
			// 返回结果解析
			if (rsp.isSuccess()) {
				 //解析xml 返回发布商品的规则文档
				re=rsp.getAddItemResult();
				
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
    //返回商品id
    @Override
    public String GdsAdd(GdsThirdReqDTO gdsThirdReqDTO)throws BusinessException{

		String re = null;
		try {

			TaobaoClient client = new DefaultTaobaoClient(
					gdsThirdReqDTO.getServerUrl(), gdsThirdReqDTO.getAppkey(),
					gdsThirdReqDTO.getAppscret());

			TmallItemSchemaAddRequest req = new TmallItemSchemaAddRequest();
			req.setCategoryId(Long.valueOf(gdsThirdReqDTO.getOutCatgCode()));//必填
			req.setProductId(gdsThirdReqDTO.getProductId());//必填
			req.setXmlData(gdsThirdReqDTO.getGdsXml());//必填
			
			// 验证参数是否正确
			req.check();
			String token = defaultTokenSV.fetchShopToken(gdsThirdReqDTO
					.findBaseShopAuth());
			TmallItemSchemaAddResponse rsp = client.execute(req, token);
			// 返回结果解析
			if (rsp.isSuccess()) {
				re=rsp.getAddItemResult();
				
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

