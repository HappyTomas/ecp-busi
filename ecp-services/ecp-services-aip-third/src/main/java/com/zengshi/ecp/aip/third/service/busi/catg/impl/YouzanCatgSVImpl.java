package com.zengshi.ecp.aip.third.service.busi.catg.impl;

import com.zengshi.ecp.aip.third.dubbo.dto.resp.Categories;
import com.zengshi.ecp.aip.third.dubbo.dto.req.CatgReqDTO;
import com.zengshi.ecp.aip.third.dubbo.dto.resp.*;
import com.zengshi.ecp.aip.third.service.busi.catg.interfaces.ICatgSV;
import com.zengshi.ecp.aip.third.service.busi.client.youzan.KdtApiClient;
import com.zengshi.ecp.aip.third.service.busi.utils.SchemaUtil;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.paas.utils.LogUtil;
import com.alibaba.dubbo.common.utils.CollectionUtils;
import com.alibaba.fastjson.JSON;
import org.apache.http.HttpResponse;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class YouzanCatgSVImpl implements ICatgSV{
    
    public static final String MODULE = YouzanCatgSVImpl.class.getName();

    private static final String method="kdt.itemcategories.get";
    
    @Override
    public CatgsRespDTO fetch(CatgReqDTO catgReqDTO) throws BusinessException {/* 
    	
    	String value="这个是有赞分类接口";
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("price", "999.01");
		params.put("title", "测试商品");
		params.put("desc", "这是一个号商铺");
		params.put("is_virtual", "0");
		params.put("post_fee", "10.01");
		params.put("sku_properties", "");
		params.put("sku_quantities", "");
		params.put("sku_prices", "");
		params.put("sku_outer_ids", "");
		String fileKey = "images[]";
		List<String> filePaths = new ArrayList<String>();
		filePaths.add("/Users/xuexiaozhe/Desktop/1.png");
		filePaths.add("/Users/xuexiaozhe/Desktop/2.png");
		
		KdtApiClient kdtApiClient;
		HttpResponse response;
		
		try {
			kdtApiClient = new KdtApiClient(APP_ID, APP_SECRET);
			kdtApiClient = new KdtApiClient(baseShopAuthReqDTO.getAppKey(), baseShopAuthReqDTO.getAppScret());
			response = kdtApiClient.post(method, params, filePaths, fileKey);
			response = kdtApiClient.post(method, params, null, null);
			System.out.println("Response Code : " + response.getStatusLine().getStatusCode());
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
			StringBuffer result = new StringBuffer();
			String line = "";
			while ((line = bufferedReader.readLine()) != null) {
				result.append(line);
			}
			System.out.println(result.toString());
			value=result.toString();
		} catch (Exception e) {
			LogUtil.error(MODULE, e.toString());
			
		}
    	return value;
    */return null;
    	}
    @Override
    public CatgsRespDTO fetchCatgAndProp(CatgReqDTO catgReqDTO) throws BusinessException {


        CatgsRespDTO catgsRespDTO = null;

        Map<String, Object> params = new HashMap<>();

        KdtApiClient kdtApiClient;
        HttpResponse response;

        try {
            LogUtil.error(MODULE, "====-----====");
            kdtApiClient = new KdtApiClient(catgReqDTO.getAppkey(), catgReqDTO.getAppscret());
//            kdtApiClient = new KdtApiClient(baseShopAuthReqDTO.getAppKey(), baseShopAuthReqDTO.getAppScret());
//            response = kdtApiClient.post(method, params, filePaths, fileKey);
            response = kdtApiClient.post(method, params, null, null);
            if(200 == response.getStatusLine().getStatusCode()){
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(response.getEntity().getContent(),"utf-8"));
                StringBuffer result = new StringBuffer();
                String line = "";
                while ((line = bufferedReader.readLine()) != null) {
                    result.append(line);
                }
                YZCateResp yzResp = JSON.parseObject(SchemaUtil.decodeUnicode(result.toString()), YZCateResp.class);
                LogUtil.error(MODULE,"result:"+ JSON.toJSONString(yzResp));
                if(yzResp.getResponse() != null && CollectionUtils.isNotEmpty(yzResp.getResponse().getCategories())){
                    catgsRespDTO = new CatgsRespDTO();
                    List<CatgRespDTO> catgRespDTOList = new ArrayList<>();
                    for(Categories categories :yzResp.getResponse().getCategories()){
                        CatgRespDTO catgRespDTO = new CatgRespDTO();
                        catgRespDTO.setCatgCode(String.valueOf(categories.getCid()));
                        catgRespDTO.setName(categories.getName());
                        catgRespDTO.setIfParent(categories.getIs_parent());
                        catgRespDTO.setParentCatgCode(String.valueOf(categories.getParent_cid()));
                        catgRespDTOList.add(catgRespDTO);
                        if(CollectionUtils.isNotEmpty(categories.getSub_categories())){
                            resolveMothed(categories.getSub_categories(),catgRespDTOList);
                        }
                    }
                    catgsRespDTO.setCatgs(catgRespDTOList);
                } else if(yzResp.getError_response() != null){
                    throw  new BusinessException("AIPTHIRD.ERRORS.100001",new String[]{yzResp.getError_response().getMsg()});
                } else {
                    throw  new BusinessException("AIPTHIRD.ERRORS.100001",new String[]{"查询为空"});
                }
            }
        } catch (Exception e) {
            LogUtil.error(MODULE,"", e);

        }

    	return catgsRespDTO;
    }

    protected void resolveMothed(List<ItemCategory> itemCategoryList,List<CatgRespDTO> catgRespDTOList){

        if(CollectionUtils.isEmpty(itemCategoryList)){
            return;
        }
        for(ItemCategory itemCategory: itemCategoryList){
            CatgRespDTO catgRespDTO = new CatgRespDTO();
            catgRespDTO.setCatgCode(String.valueOf(itemCategory.getCid()));
            catgRespDTO.setName(itemCategory.getName());
            catgRespDTO.setIfParent(itemCategory.getIs_parent());
            catgRespDTO.setParentCatgCode(String.valueOf(itemCategory.getParent_cid()));
            catgRespDTOList.add(catgRespDTO);
            if(CollectionUtils.isNotEmpty(itemCategory.getSub_categories())){
                resolveMothed(itemCategory.getSub_categories(),catgRespDTOList);
            }
        }

    }

}

