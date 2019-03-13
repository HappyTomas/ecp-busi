package com.zengshi.ecp.aip.third.service.busi.gds.impl;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.zengshi.ecp.aip.third.dubbo.dto.resp.YZCateResp;
import com.zengshi.ecp.aip.third.dubbo.dto.resp.YZGdsResp;
import com.zengshi.ecp.aip.third.service.busi.utils.SchemaUtil;
import com.zengshi.paas.utils.StringUtil;
import com.alibaba.dubbo.common.utils.CollectionUtils;
import com.alibaba.fastjson.JSON;
import org.apache.http.HttpResponse;

import com.zengshi.ecp.aip.third.dubbo.dto.req.GdsSendThirdReqDTO;
import com.zengshi.ecp.aip.third.service.busi.client.youzan.KdtApiClient;
import com.zengshi.ecp.aip.third.service.busi.gds.interfaces.IGdsSendSV;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.paas.utils.LogUtil;

public class YouzanGdsSendSVImpl implements IGdsSendSV{
    
    public static final String MODULE = YouzanGdsSendSVImpl.class.getName();

    private static final String method="kdt.item.add";
    
    @Override
    public HashMap send(GdsSendThirdReqDTO gdsSendReqDTO) throws BusinessException { 
    	
    	String value="这个是有赞同步接口";
		Map<String, Object> params = new HashMap<String, Object>();

		//商品标题。不能超过100字，受违禁词控制
		params.put("title", ""+gdsSendReqDTO.getGdsInfoMap().get("title"));
		//商品价格。取值范围 0.01-100000000 精确到2位小数 单位元。需要在Sku价格所决定的的区间内
		params.put("price", ""+gdsSendReqDTO.getGdsInfoMap().get("price"));
		//运费。取值范围 0.00-999.00 精确到2位小数 单位元
		String postFee = "0.00";
		if(StringUtil.isNotBlank(""+gdsSendReqDTO.getGdsInfoMap().get("post"))){
			postFee = ""+gdsSendReqDTO.getGdsInfoMap().get("post");
		}
		params.put("post_fee", postFee);
		if(gdsSendReqDTO.getGdsInfoMap().get("postage_id") != null && StringUtil.isNotBlank(""+gdsSendReqDTO.getGdsInfoMap().get("postage_id"))){
			//运费模版id
			params.put("delivery_template_id", ""+gdsSendReqDTO.getGdsInfoMap().get("postage_id"));
		}

		//商品描述。字数要大于5个字符，小于25000个字符 ，受违禁词控制
		params.put("desc", "");
		//商品总库存。当商品没有Sku的时候有效，商品有Sku时，总库存会自动按所有Sku库存之和计算
		params.put("quantity", ""+gdsSendReqDTO.getGdsInfoMap().get("quantity"));
		if("0".equals(String.valueOf(gdsSendReqDTO.getGdsInfoMap().get("item_status")))){
			//默认1 上架商品，设置为0 不上架商品
			params.put("is_display", "1");
		} else {
			//默认1 上架商品，设置为0 不上架商品
			params.put("is_display", "0");
		}
		//商品货号（商家为商品设置的外部编号）
		params.put("outer_id",""+gdsSendReqDTO.getGdsInfoMap().get("outer_id"));

//		商品描述
//		Map<String, Object> descModMap = (Map<String, Object>) gdsSendReqDTO.getGdsInfoMap().get("description");
//		for (Object v : descModMap.values()) {
//			filePaths.add(v);
//		}

		//是否是虚拟商品。0为否，1为是。目前不支持虚拟商品
//		params.put("is_virtual", "");
//		params.put("sku_properties", "");
//		params.put("sku_quantities", "");
//		params.put("sku_prices", "");
//		params.put("sku_outer_ids", "");

//		params.put("price", "999.01");
//		params.put("title", "测试商品");
//		params.put("desc", "这是一个号商铺");
//		params.put("is_virtual", "0");
//		params.put("post_fee", "10.01");
//		params.put("sku_properties", "");
//		params.put("sku_quantities", "");
//		params.put("sku_prices", "");
//		params.put("sku_outer_ids", "");
//		params.put("quantity", "10");
//		params.put("is_display", "0"); //默认1 上架商品，设置为0 不上架商品
		String fileKey = "images[]";
		List<String> filePaths = new ArrayList<String>();
		Map<String, String> imagesMap = (Map<String, String>) gdsSendReqDTO.getGdsInfoMap().get("images_uuids");
		if(imagesMap != null){
			int count = 0;
			for (String key : imagesMap.keySet()) {
				filePaths.add(imagesMap.get("image_uuid_"+count));
				count ++;
			}
//			for (String v : imagesMap.values()) {
//				filePaths.add(v);
//			}
		}

//		filePaths.add("D:/Download/1.jpg");

		
		KdtApiClient kdtApiClient;
		HttpResponse response;
		
		try {
			LogUtil.error(MODULE, "====-----===="+JSON.toJSONString(gdsSendReqDTO));
			LogUtil.error(MODULE, "====--+---===="+JSON.toJSONString(params));
			LogUtil.error(MODULE, "====--+---===="+JSON.toJSONString(filePaths)+JSON.toJSONString(fileKey));
			/*kdtApiClient = new KdtApiClient(APP_ID, APP_SECRET);*/
			kdtApiClient = new KdtApiClient(gdsSendReqDTO.getAppkey(), gdsSendReqDTO.getAppscret());
			/*response = kdtApiClient.post(method, params, filePaths, fileKey);*/
			response = kdtApiClient.post(method, params, filePaths, fileKey);
			LogUtil.error(MODULE,"Response Code : " + response.getStatusLine().getStatusCode());
			if(200 == response.getStatusLine().getStatusCode()){
				BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
				StringBuffer result = new StringBuffer();
				String line = "";
				while ((line = bufferedReader.readLine()) != null) {
					result.append(line);
				}
				YZGdsResp resp = JSON.parseObject(SchemaUtil.decodeUnicode(result.toString()), YZGdsResp.class);
				LogUtil.error(MODULE,"result:"+ JSON.toJSONString(resp));
				if(resp.getError_response() != null) {
					LogUtil.error(MODULE,"error_response: ");
					throw  new BusinessException("AIPTHIRD.100028",new String[]{resp.getError_response().getMsg()});
				} else {
					LogUtil.error(MODULE,"response: " + result.toString());
					if(resp.getResponse() != null){
						HashMap  resultMap=new HashMap();
						resultMap.put("gdsId",resp.getResponse().getItem().getNum_iid());
						return  resultMap;
					} else {
						throw  new BusinessException("AIPTHIRD.100028",new String[]{resp.getError_response().getMsg()});
					}
				}
			}

		} catch (Exception e) {
			LogUtil.error(MODULE, e.toString());
			throw new BusinessException("AIPTHIRD.100028",new String[]{e.getMessage()});
		}
    	return null;
    }
}

